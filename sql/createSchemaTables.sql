CREATE DATABASE `quadcbank` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
CREATE TABLE `account` (
  `accountId` int(11) NOT NULL AUTO_INCREMENT,
  `accountNumber` varchar(45) NOT NULL,
  `balance` decimal(15,2) DEFAULT NULL,
  `accountType` varchar(45) DEFAULT NULL,
  `customerId` int(11) NOT NULL,
  PRIMARY KEY (`accountId`),
  UNIQUE KEY `accountId_UNIQUE` (`accountId`),
  KEY `customer_fk_idx` (`customerId`),
  CONSTRAINT `customer_fk1` FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `address` (
  `addressId` int(11) NOT NULL AUTO_INCREMENT,
  `line1` varchar(255) DEFAULT NULL,
  `line2` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `zip` varchar(45) DEFAULT NULL,
  `customerId` int(11) NOT NULL,
  PRIMARY KEY (`addressId`),
  UNIQUE KEY `addressId_UNIQUE` (`addressId`),
  KEY `customer_fk_idx` (`customerId`),
  CONSTRAINT `customer_fk` FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `customer` (
  `customerId` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(128) NOT NULL,
  `role` tinyint(1) NOT NULL DEFAULT '2',
  `loginStatus` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`customerId`),
  UNIQUE KEY `userId_UNIQUE` (`customerId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
CREATE TABLE `transaction` (
  `transactionId` int(11) NOT NULL AUTO_INCREMENT,
  `amount` decimal(15,2) NOT NULL,
  `transactionType` varchar(45) NOT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `transactionDate` date NOT NULL,
  `accountId` int(11) DEFAULT NULL,
  PRIMARY KEY (`transactionId`),
  UNIQUE KEY `transactionId_UNIQUE` (`transactionId`),
  KEY `account_fk_idx` (`accountId`),
  CONSTRAINT `account_fk` FOREIGN KEY (`accountId`) REFERENCES `account` (`accountId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
