DROP SCHEMA IF EXISTS `projectCRM`;

CREATE SCHEMA `projectCRM`;

use `projectCRM`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `customer_address`;

CREATE TABLE `customer_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `house` varchar(45) DEFAULT NULL,
  `street` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `postCode` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `customer_one`;

CREATE TABLE `customer_one` (
`id`	int(11) NOT NULL AUTO_INCREMENT,
`first_name` varchar(45) DEFAULT NULL,
`last_name` varchar(45) DEFAULT NULL,
`email` varchar(45) DEFAULT NULL,
`phone` varchar(45) DEFAULT NULL,
`customer_address_id` int(11)DEFAULT NULL,
`registration_date` varchar(45) DEFAULT NULL,
 PRIMARY KEY (`id`),
 KEY `FK_DETAIL_idx` (`customer_address_id`),
 CONSTRAINT `FK_DETAIL` FOREIGN KEY (`customer_address_id`) 
 REFERENCES `customer_address` (`id`) 
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;
