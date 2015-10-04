-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 03, 2015 at 07:00 PM
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
  `distibutorCode` varchar(255) NOT NULL,
  `invoiceDate` date NOT NULL,
  `invoiceNote` varchar(255) DEFAULT NULL,
  `invoicePayMode` varchar(255) DEFAULT NULL,
  `invoiceTotal` double NOT NULL,
  PRIMARY KEY (`invoiceID`)
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
  PRIMARY KEY (`invoiceItemID`,`invoiceID`)
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
  `productMinStock` double DEFAULT NULL,
  PRIMARY KEY (`productID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

