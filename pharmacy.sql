-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 28, 2015 at 05:40 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `pharmacy`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `Get_All_Distributors`()
BEGIN
  SELECT * FROM distributor;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `Get_All_Products`()
BEGIN
SELECT * FROM product;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE IF NOT EXISTS `bill` (
  `billNo` int(10) NOT NULL,
  `billDate` date NOT NULL,
  `billNote` varchar(255) DEFAULT NULL,
  `billAmount` double NOT NULL,
  PRIMARY KEY (`billNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `billitem`
--

CREATE TABLE IF NOT EXISTS `billitem` (
  `billItemNo` varchar(255) NOT NULL,
  `billNo` int(10) NOT NULL,
  `productID` varchar(10) NOT NULL,
  `unitPrice` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `amount` double NOT NULL,
  PRIMARY KEY (`billItemNo`),
  KEY `fk_billitem_product1_idx` (`productID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `distributor`
--

CREATE TABLE IF NOT EXISTS `distributor` (
  `dCode` varchar(5) NOT NULL,
  `dName` varchar(255) NOT NULL,
  `dAddress` varchar(255) NOT NULL,
  `dTelephone` varchar(255) NOT NULL,
  PRIMARY KEY (`dCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE IF NOT EXISTS `invoice` (
  `invoiceID` varchar(255) NOT NULL,
  `dCode` varchar(5) NOT NULL,
  `invoiceDate` date NOT NULL,
  `invoiceNote` varchar(255) DEFAULT NULL,
  `invoicePayMode` varchar(255) DEFAULT NULL,
  `invoiceTotal` double NOT NULL,
  PRIMARY KEY (`invoiceID`),
  KEY `fk_invoice_distributor1_idx` (`dCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `invoiceitem`
--

CREATE TABLE IF NOT EXISTS `invoiceitem` (
  `invoiceItemID` varchar(255) NOT NULL,
  `invoiceID` varchar(255) NOT NULL,
  `productID` varchar(255) NOT NULL,
  `packSize` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `free` int(11) NOT NULL,
  `price` double NOT NULL,
  `margin` int(11) NOT NULL,
  `expireDate` date NOT NULL,
  `discount` double NOT NULL,
  `sold` int(11) NOT NULL,
  PRIMARY KEY (`invoiceItemID`),
  KEY `fk_invoiceitem_invoice_idx` (`invoiceID`),
  KEY `fk_invoiceitem_product1_idx` (`productID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE IF NOT EXISTS `product` (
  `productID` varchar(255) NOT NULL,
  `productName` varchar(255) DEFAULT NULL,
  `productDescription` varchar(255) DEFAULT NULL,
  `productBrand` varchar(255) DEFAULT NULL,
  `productStrength` int(10) DEFAULT NULL,
  `productType` varchar(255) DEFAULT NULL,
  `productStock` int(10) DEFAULT NULL,
  `productMinStock` int(10) DEFAULT NULL,
  `unitPrice` double NOT NULL,
  PRIMARY KEY (`productID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `billitem`
--
ALTER TABLE `billitem`
  ADD CONSTRAINT `fk_billitem_bill1` FOREIGN KEY (`billNo`) REFERENCES `bill` (`billNo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_billitem_product1` FOREIGN KEY (`productID`) REFERENCES `product` (`productID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `fk_invoice_distributor1` FOREIGN KEY (`dCode`) REFERENCES `distributor` (`dCode`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `invoiceitem`
--
ALTER TABLE `invoiceitem`
  ADD CONSTRAINT `fk_invoiceitem_invoice` FOREIGN KEY (`invoiceID`) REFERENCES `invoice` (`invoiceID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_invoiceitem_product1` FOREIGN KEY (`productID`) REFERENCES `product` (`productID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
