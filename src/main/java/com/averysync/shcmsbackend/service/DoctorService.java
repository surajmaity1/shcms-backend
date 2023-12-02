package com.averysync.shcmsbackend.service;

import com.averysync.shcmsbackend.dao.AppointmentHistoryRepository;
import com.averysync.shcmsbackend.dao.AppointmentRepository;
import com.averysync.shcmsbackend.dao.DoctorRepository;
import com.averysync.shcmsbackend.dao.PaymentRepository;
import com.averysync.shcmsbackend.entity.Appointment;
import com.averysync.shcmsbackend.entity.AppointmentHistory;
import com.averysync.shcmsbackend.entity.Doctor;
import com.averysync.shcmsbackend.entity.Payment;
import com.averysync.shcmsbackend.responsemodels.AppointmentResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class DoctorService {
    private DoctorRepository doctorRepository;
    private AppointmentRepository appointmentRepository;
    private AppointmentHistoryRepository appointmentHistoryRepository;
    private PaymentRepository paymentRepository;

    public DoctorService(
            DoctorRepository doctorRepository,
            AppointmentRepository appointmentRepository,
            AppointmentHistoryRepository appointmentHistoryRepository,
            PaymentRepository paymentRepository
    ) {
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
        this.appointmentHistoryRepository = appointmentHistoryRepository;
        this.paymentRepository = paymentRepository;
    }

    public Doctor appointmentDoctor(String userEmail, Long doctorId) throws Exception {
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);
        Appointment validateAppointment = appointmentRepository.findByUserEmailAndDoctorId(userEmail, doctorId);

        if (!doctor.isPresent() || validateAppointment != null || doctor.get().getAppointmentsAvailable() <= 0) {
            throw new Exception("Doctor not present OR Appointment Full. Book later");
        }

        List<Appointment> currentDoctorsAppointment = appointmentRepository.findDoctorsByUserEmail(userEmail);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        boolean doctorNeedsReturn = false;

        for (Appointment appointment: currentDoctorsAppointment) {
            Date d1 = sdf.parse(appointment.getCancelDate());
            Date d2 = sdf.parse(LocalDate.now().toString());

            TimeUnit time = TimeUnit.DAYS;

            double differenceInTime = time.convert(d1.getTime() - d2.getTime(), TimeUnit.MILLISECONDS);

            if (differenceInTime < 0) {
                doctorNeedsReturn = true;
                break;
            }
        }

        Payment userPayment = paymentRepository.findByUserEmail(userEmail);

        if ((userPayment != null && userPayment.getAmount() > 0) || (userPayment != null && doctorNeedsReturn)) {
            throw new Exception("DUE FEES");
        }

        if (userPayment == null) {
            Payment payment = new Payment();
            payment.setAmount(00.00);
            payment.setUserEmail(userEmail);
            paymentRepository.save(payment);
        }

        doctor.get().setAppointmentsAvailable(doctor.get().getAppointmentsAvailable() - 1);
        doctorRepository.save(doctor.get());

        Appointment appointment = new Appointment(
                userEmail, LocalDate.now().toString(), LocalDate.now().plusDays(1).toString(), doctor.get().getId()
        );

        appointmentRepository.save(appointment);
        return doctor.get();
    }

    public Boolean appointmentDoctorByUser(String userEmail, Long doctorId) {
        Appointment validateAppointment = appointmentRepository.findByUserEmailAndDoctorId(userEmail, doctorId);
        return validateAppointment != null;
    }

    public int currentAppointmentsCount(String userEmail) {
        return appointmentRepository.findDoctorsByUserEmail(userEmail).size();
    }

    public List<AppointmentResponse> currentAppointments (String userEmail) throws Exception {
        List<AppointmentResponse> appointmentResponses = new ArrayList<>();
        List<Appointment> appointmentList = appointmentRepository.findDoctorsByUserEmail(userEmail);
        List<Long> doctorIdLists = new ArrayList<>();

        for (Appointment appointment: appointmentList) {
            doctorIdLists.add(appointment.getDoctorId());
        }

        List<Doctor> doctorLists = doctorRepository.findDoctorsByDoctorIds(doctorIdLists);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (Doctor doctor : doctorLists) {
            Optional<Appointment> appointment = appointmentList.stream()
                    .filter(item -> item.getDoctorId() == doctor.getId()).findFirst();

            if (appointment.isPresent()) {

                Date d1 = sdf.parse(appointment.get().getCancelDate());
                Date d2 = sdf.parse(LocalDate.now().toString());
                TimeUnit time = TimeUnit.DAYS;

                long difference_In_Time = time.convert(d1.getTime() - d2.getTime(),
                        TimeUnit.MILLISECONDS);

                appointmentResponses.add(new AppointmentResponse(doctor, (int) difference_In_Time));
            }
        }
        return appointmentResponses;
    }

    public void cancelDoctor (String userEmail, Long doctorId) throws Exception {
        Optional<Doctor> doctor = doctorRepository.findById(doctorId);
        Appointment validateAppointment = appointmentRepository.findByUserEmailAndDoctorId(userEmail, doctorId);

        if (doctor.isEmpty() || validateAppointment == null) {
            throw new Exception("DOCTOR DOES NOT EXIST or USER DIDN'T BOOK DOCTOR APPOINTMENT");
        }

        doctor.get().setAppointmentsAvailable(doctor.get().getAppointmentsAvailable() + 1);
        doctorRepository.save(doctor.get());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date d1 = sdf.parse(validateAppointment.getCancelDate());
        Date d2 = sdf.parse(LocalDate.now().toString());

        TimeUnit time = TimeUnit.DAYS;

        double differenceInTime = time.convert(d1.getTime() - d2.getTime(), TimeUnit.MILLISECONDS);

        if (differenceInTime < 0) {
            Payment payment = paymentRepository.findByUserEmail(userEmail);

            payment.setAmount(payment.getAmount() + (differenceInTime * -1));
            paymentRepository.save(payment);
        }

        appointmentRepository.deleteById(validateAppointment.getId());

        // Save appointment_cancel_history in appointment_history table
        AppointmentHistory appointmentHistory = new AppointmentHistory(
                userEmail, validateAppointment.getAppointmentDate(), LocalDate.now().toString(),
                doctor.get().getFirstName(), doctor.get().getLastName(),
                doctor.get().getRole(), doctor.get().getImg()
                );
        appointmentHistoryRepository.save(appointmentHistory);
    }

    public void rescheduleAppointment (String userEmail, Long doctorId) throws Exception {
        Appointment validateAppointment = appointmentRepository.findByUserEmailAndDoctorId(userEmail, doctorId);

        if (validateAppointment == null) {
            throw new Exception("DOCTOR DOES NOT EXIST or USER DIDN'T BOOK DOCTOR APPOINTMENT");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date d1 = sdf.parse(validateAppointment.getCancelDate());
        Date d2 = sdf.parse(LocalDate.now().toString());

        if (d1.compareTo(d2) >= 0) {
            validateAppointment.setCancelDate(LocalDate.now().plusDays(1).toString());
            appointmentRepository.save(validateAppointment);
        }
    }
}
