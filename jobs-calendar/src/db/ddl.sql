CREATE DATABASE `codefest` /*!40100 DEFAULT CHARACTER SET latin1 */;

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

CREATE TABLE `event` (
  `event_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(5000) DEFAULT NULL,
  `start_dt` datetime DEFAULT NULL,
  `end_dt` datetime DEFAULT NULL,
  `location` varchar(1000) DEFAULT NULL,
  `description` varchar(5000) DEFAULT NULL,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4791 DEFAULT CHARSET=latin1;

CREATE TABLE `event_category` (
  `question_response_id` int(11) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`question_response_id`),
  KEY `question_response_idx` (`question_response_id`),
  CONSTRAINT `event_category_ibfk_1` FOREIGN KEY (`question_response_id`) REFERENCES `question_response` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `event_category_association` (
  `event_category_association_id` int(11) NOT NULL AUTO_INCREMENT,
  `event_category_id` int(11) DEFAULT NULL,
  `event_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`event_category_association_id`),
  KEY `event_id_idx` (`event_id`),
  KEY `event_category_id_idx` (`event_category_id`),
  CONSTRAINT `event_id` FOREIGN KEY (`event_id`) REFERENCES `event` (`event_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `event_category_id` FOREIGN KEY (`event_category_id`) REFERENCES `event_category` (`question_response_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
