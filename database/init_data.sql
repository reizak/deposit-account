-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.26 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for installment_saving
CREATE DATABASE IF NOT EXISTS `installment_saving` /*!40100 DEFAULT CHARACTER SET latin1 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `installment_saving`;

-- Dumping structure for function installment_saving.generateAccountNumber
DELIMITER //
CREATE FUNCTION `generateAccountNumber`() RETURNS int
    DETERMINISTIC
BEGIN
  DECLARE select_var VARCHAR(10);
  INSERT INTO seq_account_number () VALUES (); 
  SET select_var = (SELECT MAX(id) FROM seq_account_number);
  RETURN select_var;
END//
DELIMITER ;

-- Dumping structure for function installment_saving.generateCustomerNumber
DELIMITER //
CREATE FUNCTION `generateCustomerNumber`() RETURNS int
    DETERMINISTIC
BEGIN
  DECLARE select_var VARCHAR(10);
  INSERT INTO seq_customer_number () VALUES (); 
  SET select_var = (SELECT MAX(id) FROM seq_customer_number);
  RETURN select_var;
END//
DELIMITER ;

-- Dumping structure for table installment_saving.seq_account_number
CREATE TABLE IF NOT EXISTS `seq_account_number` (
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1000000001 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table installment_saving.seq_customer_number
CREATE TABLE IF NOT EXISTS `seq_customer_number` (
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2000000001 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table installment_saving.tb_account
CREATE TABLE IF NOT EXISTS `tb_account` (
  `account_number` varchar(10) NOT NULL,
  `account_category` varchar(5) DEFAULT NULL,
  `customer_number` varchar(10) DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`account_number`),
  KEY `FK4ub1ckjk8lusvxut4rmksj650` (`customer_number`),
  CONSTRAINT `FK4ub1ckjk8lusvxut4rmksj650` FOREIGN KEY (`customer_number`) REFERENCES `tb_customer` (`customer_number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table installment_saving.tb_customer
CREATE TABLE IF NOT EXISTS `tb_customer` (
  `customer_number` varchar(10) NOT NULL,
  `customer_name` varchar(100) DEFAULT NULL,
  `date_of_birth` varchar(15) DEFAULT NULL,
  `legal_id_number` varchar(30) DEFAULT NULL,
  `legal_id_type` varchar(30) DEFAULT NULL,
  `place_of_birth` varchar(255) DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  `mobile_phone` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`customer_number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table installment_saving.tb_deposit
CREATE TABLE IF NOT EXISTS `tb_deposit` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tenor` varchar(5) DEFAULT NULL,
  `account_number` varchar(50) DEFAULT NULL,
  `first_deposit_amount` varchar(50) DEFAULT NULL,
  `monthly_deposit_amount` varchar(50) DEFAULT NULL,
  `saving_purpose` varchar(100) DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `created_date` timestamp NULL DEFAULT NULL,
  `modified_by` varchar(50) DEFAULT NULL,
  `modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9d9myguvgg5n1ue76mr1oqkr1` (`account_number`),
  CONSTRAINT `FK9d9myguvgg5n1ue76mr1oqkr1` FOREIGN KEY (`account_number`) REFERENCES `tb_account` (`account_number`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

-- Dumping structure for table installment_saving.tb_parameter
CREATE TABLE IF NOT EXISTS `tb_parameter` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(50) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `value` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

INSERT INTO `installment_saving`.`tb_parameter` (`code`, `name`, `value`) VALUES ('MIN_FIRST_DEPOSIT_AMOUNT', 'Minimal First Deposit Amount', '10000000');
INSERT INTO `installment_saving`.`tb_parameter` (`code`, `name`, `value`) VALUES ('MIN_MONTHLY_DEPOSIT_AMOUNT', 'Monthly deposit amount', '100000');
INSERT INTO `installment_saving`.`tb_parameter` (`code`, `name`, `value`) VALUES ('TENOR', 'Saving tenor (total months)', '1,3,6,12,24');
INSERT INTO `installment_saving`.`tb_parameter` (`code`, `name`, `value`) VALUES ('RATE_DEPOSIT', 'Rate', '6');
INSERT INTO `installment_saving`.`tb_parameter` (`code`, `name`, `value`) VALUES ('DEPOSIT_ACCOUNT_CATEGORY', 'Deposit Account Category', '1001');
