SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

DELIMITER $$

CREATE DEFINER=`root`@`localhost` PROCEDURE `daysrangebillreport`(IN `date1` DATE, IN `date2` DATE)
BEGIN
SELECT *
FROM bill
WHERE billDate between date1 and date2
ORDER BY billNo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `daysrangeinvoicereport`(IN `date1` DATE, IN `date2` DATE)
BEGIN
SELECT *
FROM invoice
WHERE invoiceDate between date1 and date2
ORDER BY invoiceID;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `expirenotification`()
BEGIN
SELECT productID, (SELECT productName FROM product WHERE productID = invoiceitem.productID) AS productName, quantity, expireDate 
FROM invoiceitem 
WHERE quantity > 0 and 
expireDate between CURDATE() and DATE_ADD(CURDATE(), INTERVAL 1 MONTH)
ORDER BY expireDate;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getallproducts`()
BEGIN
SELECT * FROM product;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getcurrentstock`(IN `productidparam` VARCHAR(255), OUT `stockreturn` INT)
BEGIN
  SELECT Sum(stock) 
  INTO stockreturn
  FROM (SELECT quantity - sold AS stock 
  FROM   pharmacy.invoiceitem 
  WHERE  productid = productidparam ) stock; 
 END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getproductscurrentstock`()
BEGIN
SELECT 
oc.productID, SUM(oc.quantity) Amount
FROM (
  SELECT productID,COALESCE(quantity,0) quantity FROM invoiceitem
    UNION ALL
    SELECT productID,COALESCE(-quantity,0) quantity FROM billitem
) oc
GROUP BY oc.productID;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `getunitprice`(IN `productidparam` VARCHAR(255), OUT `pricereturn` DOUBLE)
BEGIN 
  SELECT price
  INTO pricereturn
  FROM invoiceitem
  WHERE invoiceitemid = (
  SELECT Max(invoiceitemid)
  FROM invoiceitem 
  WHERE productid = productidparam); 
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `givendaybillreport`(IN `date1` DATE)
BEGIN
SELECT *
FROM bill
WHERE billDate = date1
ORDER BY billNo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `givendayinvoicereport`(IN `date1` DATE)
BEGIN
SELECT *
FROM invoice
WHERE invoiceDate = date1
ORDER BY invoiceID;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `minstocknotification`(IN `productidparam` VARCHAR(255), IN `currentstockparam` INT)
BEGIN
SELECT productID, productName, productMinStock 
FROM product 
WHERE productID = productidparam AND productMinStock >= currentstockparam;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `monthlybillsum`()
BEGIN
SELECT billDate, SUM(billAmount) AS total
FROM bill
WHERE billDate between DATE_SUB(CURDATE(), INTERVAL 1 MONTH) and CURDATE()
GROUP BY billDate;
END$$

DELIMITER ;

CREATE TABLE IF NOT EXISTS `bill` (
  `billNo` int(11) NOT NULL AUTO_INCREMENT,
  `billDate` date NOT NULL,
  `billNote` varchar(255) DEFAULT NULL,
  `billAmount` double NOT NULL,
  PRIMARY KEY (`billNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `billitem` (
  `billItemNo` int(11) NOT NULL AUTO_INCREMENT,
  `billNo` int(11) NOT NULL,
  `productID` varchar(255) NOT NULL,
  `unitPrice` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `amount` double NOT NULL,
  PRIMARY KEY (`billItemNo`),
  KEY `fk_billitem_bill1_idx` (`billNo`),
  KEY `fk_billitem_product1_idx` (`productID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `distributor` (
  `dCode` varchar(255) NOT NULL,
  `dName` varchar(255) NOT NULL,
  `dAddress` varchar(255) DEFAULT NULL,
  `dTelephone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`dCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS `invoice` (
  `invoiceID` int(11) NOT NULL AUTO_INCREMENT,
  `dCode` varchar(255) NOT NULL,
  `invoiceDate` date NOT NULL,
  `invoiceNote` varchar(255) DEFAULT NULL,
  `invoicePayMode` varchar(255) DEFAULT NULL,
  `invoiceTotal` double NOT NULL,
  PRIMARY KEY (`invoiceID`),
  KEY `fk_invoice_distributor1_idx` (`dCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `invoiceitem` (
  `invoiceItemID` int(11) NOT NULL AUTO_INCREMENT,
  `invoiceID` int(11) NOT NULL,
  `productID` varchar(255) NOT NULL,
  `packSize` int(11) DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `free` int(11) DEFAULT NULL,
  `price` double NOT NULL,
  `margin` double NOT NULL,
  `expireDate` date NOT NULL,
  `discount` double DEFAULT NULL,
  PRIMARY KEY (`invoiceItemID`),
  KEY `fk_invoiceitem_invoice1_idx` (`invoiceID`),
  KEY `fk_invoiceitem_product1_idx` (`productID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

CREATE TABLE IF NOT EXISTS `product` (
  `productID` varchar(255) NOT NULL,
  `productName` varchar(255) NOT NULL,
  `productDescription` varchar(255) DEFAULT NULL,
  `productBrand` varchar(255) DEFAULT NULL,
  `productStrength` int(11) DEFAULT NULL,
  `productType` varchar(255) DEFAULT NULL,
  `productMinStock` int(11) NOT NULL,
  `customPrice` double NOT NULL,
  PRIMARY KEY (`productID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE `billitem`
  ADD CONSTRAINT `fk_billitem_product1` FOREIGN KEY (`productID`) REFERENCES `product` (`productID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `invoice`
  ADD CONSTRAINT `fk_invoice_distributor1` FOREIGN KEY (`dCode`) REFERENCES `distributor` (`dCode`) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE `invoiceitem`
  ADD CONSTRAINT `fk_invoiceitem_product1` FOREIGN KEY (`productID`) REFERENCES `product` (`productID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

