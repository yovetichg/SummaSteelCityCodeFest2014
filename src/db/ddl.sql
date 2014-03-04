CREATE TABLE `question_response` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `question` (
  `question_response_id` int(11) NOT NULL,
  `question` varchar(200) DEFAULT NULL,
  `is_first` tinyint(1) NOT NULL DEFAULT '0',
  `yes_id` int(11) DEFAULT NULL,
  `no_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`question_response_id`),
  KEY `question_response_idx` (`question_response_id`),
  KEY `yes_id` (`yes_id`),
  KEY `no_id` (`no_id`),
  CONSTRAINT `question_ibfk_1` FOREIGN KEY (`question_response_id`) REFERENCES `question_response` (`id`) ON DELETE CASCADE,
  CONSTRAINT `question_ibfk_2` FOREIGN KEY (`yes_id`) REFERENCES `question_response` (`id`) ON DELETE NO ACTION,
  CONSTRAINT `question_ibfk_3` FOREIGN KEY (`no_id`) REFERENCES `question_response` (`id`) ON DELETE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `event_category` (
  `question_response_id` int(11) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `transition_text` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`question_response_id`),
  KEY `question_response_idx` (`question_response_id`),
  CONSTRAINT `event_category_ibfk_1` FOREIGN KEY (`question_response_id`) REFERENCES `question_response` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `provider` (
  `provider_id` int(11) NOT NULL,
  `name` varchar(60) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `address_1` varchar(45) NOT NULL,
  `address_2` varchar(45) DEFAULT NULL,
  `city` varchar(45) NOT NULL,
  `state` varchar(2) DEFAULT NULL,
  `zip` varchar(10) DEFAULT NULL,
  `pre_employment` bit(1) DEFAULT b'0',
  `emp_support` bit(1) DEFAULT b'0',
  `job_training` bit(1) DEFAULT b'0',
  `job_retention` bit(1) DEFAULT b'0',
  `support_services` bit(1) DEFAULT b'0',
  `programs` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`provider_id`),
  UNIQUE KEY `provider_id_UNIQUE` (`provider_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `event` (
  `event_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(5000) DEFAULT NULL,
  `start_dt` datetime DEFAULT NULL,
  `end_dt` datetime DEFAULT NULL,
  `location` varchar(1000) DEFAULT NULL,
  `description` varchar(5000) DEFAULT NULL,
  `provider_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  KEY `provider_id_idx` (`provider_id`),
  CONSTRAINT `provider_id` FOREIGN KEY (`provider_id`) REFERENCES `provider` (`provider_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4791 DEFAULT CHARSET=latin1;

CREATE TABLE `event_category_association` (
  `event_category_association_id` int(11) NOT NULL AUTO_INCREMENT,
  `event_category_id` int(11) DEFAULT NULL,
  `event_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`event_category_association_id`),
  KEY `event_category_association_event_id_idx` (`event_id`),
  KEY `event_category_association_event_category_id_idx` (`event_category_id`),
  CONSTRAINT `event_category_association_event_id` FOREIGN KEY (`event_id`) REFERENCES `event` (`event_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `event_category_association_event_category_id` FOREIGN KEY (`event_category_id`) REFERENCES `event_category` (`question_response_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `provider_category_association` (
  `provider_category_association_id` int(11) NOT NULL AUTO_INCREMENT,
  `provider_id` int(11) DEFAULT NULL,
  `event_category_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`provider_category_association_id`),
  KEY `provider_category_association_id_idx` (`provider_id`),
  KEY `provider_category_association_event_category_id_idx` (`event_category_id`),
  CONSTRAINT `provider_category_association_provider_id` FOREIGN KEY (`provider_id`) REFERENCES `provider` (`provider_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `provider_category_association_event_category_id` FOREIGN KEY (`event_category_id`) REFERENCES `event_category` (`question_response_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


