CREATE DATABASE  IF NOT EXISTS `shcms_backend` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `shcms_backend`;


DROP TABLE IF EXISTS `doctor`;

CREATE TABLE `doctor` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `role` text DEFAULT NULL,
  `appointments` int(11) DEFAULT NULL,
  `appointments_avl` int(11) DEFAULT NULL,
  `dept` varchar(11) DEFAULT NULL,
  `img` MEDIUMBLOB  DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

/*TODO: INSERT doctor data*/

DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_email` varchar(45) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `rating` decimal (3,2) DEFAULT NULL,
  `doctor_id` BIGINT(20) DEFAULT NULL,
  `review_description` text DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


INSERT INTO `review` VALUES 
	(1, 'user1@email.com', NOW(), 4, 1, 'Good'),
	(2, 'user2@email.com', NOW(), 4.5, 2, 'Helpful');


DROP TABLE IF EXISTS `appointment`;

CREATE TABLE `appointment` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_email` varchar(45) DEFAULT NULL,
  `appointment_date` varchar(45) DEFAULT NULL,
  `cancel_date` varchar(45) DEFAULT NULL,
  `doctor_id` BIGINT(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `messages`;

CREATE TABLE `queries` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_email` varchar(45) DEFAULT NULL,
  `query` varchar(45) DEFAULT NULL,
  `description` text DEFAULT NULL,
  `admin_email` varchar(45) DEFAULT NULL,
  `response` text DEFAULT NULL,
  `closed` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `appointment_history`;

CREATE TABLE `appointment_history` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `user_email` varchar(45) DEFAULT NULL,
  `appointment_date` varchar(45) DEFAULT NULL,
  `cancel_date` varchar(45) DEFAULT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `role` text DEFAULT NULL,
  `img` MEDIUMBLOB  DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `user_email` VARCHAR(45) DEFAULT NULL,
    `amount` DECIMAL(10,2) DEFAULT NULL,
    PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


UNLOCK TABLES;







