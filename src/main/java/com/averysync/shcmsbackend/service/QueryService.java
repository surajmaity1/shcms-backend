package com.averysync.shcmsbackend.service;


import com.averysync.shcmsbackend.dao.QueryRepository;
import com.averysync.shcmsbackend.entity.Query;
import com.averysync.shcmsbackend.requestmodels.AdminQuestionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class QueryService {

    private QueryRepository queryRepository;

    @Autowired
    public QueryService(QueryRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    public void sendQuery(Query queryRequest, String userEmail) {
        Query query = new Query(queryRequest.getQuery(), queryRequest.getDescription());
        query.setUserEmail(userEmail);
        queryRepository.save(query);
    }

    public void responseQuery(AdminQuestionRequest adminQuestionRequest, String userEmail) throws Exception {
        Optional<Query> query = queryRepository.findById(adminQuestionRequest.getId());
        if (!query.isPresent()) {
            throw new Exception("QUERY DOES NOT EXIST");
        }

        query.get().setAdminEmail(userEmail);
        query.get().setResponse(adminQuestionRequest.getResponse());
        query.get().setClosed(true);
        queryRepository.save(query.get());
    }
}
