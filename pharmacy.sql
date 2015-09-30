-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Sep 30, 2015 at 05:23 PM
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

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE IF NOT EXISTS `bill` (
  `billNo` varchar(255) NOT NULL,
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
  `billNo` varchar(255) NOT NULL,
  `billItemNo` varchar(255) NOT NULL,
  `productID` varchar(10) NOT NULL,
  `unitPrice` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `amount` double NOT NULL,
  PRIMARY KEY (`billNo`,`billItemNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `distri`
--

CREATE TABLE IF NOT EXISTS `distri` (
  `DistriCode` varchar(255) NOT NULL,
  `DistriName` varchar(255) DEFAULT NULL,
  `DistriAdd` varchar(255) DEFAULT NULL,
  `DistriTel` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`DistriCode`),
  KEY `DistriCode` (`DistriCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `distri`
--

INSERT INTO `distri` (`DistriCode`, `DistriName`, `DistriAdd`, `DistriTel`) VALUES
('PR', 'Pradeep Pharmacy', 'No. 392/B, Nainamadama (West) Nainamadama.', '(077) 836-5520');

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

--
-- Dumping data for table `distributor`
--

INSERT INTO `distributor` (`dCode`, `dName`, `dAddress`, `dTelephone`) VALUES
('a', '', '', ''),
('b', '', '', ''),
('c', '', '', ''),
('d', '', '', ''),
('e', '', '', ''),
('f', '', '', ''),
('g', '', '', ''),
('h', '', '', ''),
('i', '', '', ''),
('j', '', '', ''),
('k', '', '', ''),
('l', '', '', ''),
('m', '', '', ''),
('n', '', '', ''),
('o', '', '', ''),
('p', '', '', ''),
('q', '', '', ''),
('r', '', '', ''),
('s', '', '', ''),
('t', '', '', ''),
('u', '', '', ''),
('v', '', '', ''),
('w', '', '', ''),
('x', '', '', ''),
('y', '', '', ''),
('z', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `initem`
--

CREATE TABLE IF NOT EXISTS `initem` (
  `InItemID` int(10) NOT NULL,
  `InvoiceID` varchar(255) DEFAULT NULL,
  `ProductID` varchar(255) DEFAULT NULL,
  `InItemPackSize` int(10) DEFAULT NULL,
  `InItemQty` int(10) DEFAULT NULL,
  `InItemFree` int(10) DEFAULT NULL,
  `InItemPrice` decimal(19,4) DEFAULT NULL,
  `InItemMargin` int(10) DEFAULT NULL,
  `InItemExpiry` datetime DEFAULT NULL,
  `InItemDisc` decimal(19,4) DEFAULT NULL,
  `InItemSold` int(10) DEFAULT NULL,
  PRIMARY KEY (`InItemID`),
  KEY `InvoiceInItem` (`InvoiceID`),
  KEY `InvoiceId` (`InvoiceID`),
  KEY `ProductInItem` (`ProductID`),
  KEY `ProductId` (`ProductID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE IF NOT EXISTS `invoice` (
  `InvoiceID` varchar(255) NOT NULL,
  `DistriCode` varchar(255) DEFAULT NULL,
  `InvoiceDate` datetime DEFAULT NULL,
  `InvoiceNote` longtext,
  `InvoicePayment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`InvoiceID`),
  KEY `DistriInvoice` (`DistriCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE IF NOT EXISTS `product` (
  `ProductID` varchar(255) NOT NULL,
  `ProductName` varchar(255) DEFAULT NULL,
  `ProductDiscription` varchar(255) DEFAULT NULL,
  `ProductBrand` varchar(255) DEFAULT NULL,
  `ProductStrength` int(10) DEFAULT NULL,
  `ProductStype` varchar(255) DEFAULT NULL,
  `ProductStock` int(10) DEFAULT NULL,
  `ProductMinStock` double DEFAULT NULL,
  PRIMARY KEY (`ProductID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
