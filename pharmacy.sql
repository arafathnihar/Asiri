-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 26, 2015 at 11:23 AM
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

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`billNo`, `billDate`, `billNote`, `billAmount`) VALUES
(123, '0000-00-00', 'testing', 0);

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

--
-- Dumping data for table `billitem`
--

INSERT INTO `billitem` (`billItemNo`, `billNo`, `productID`, `unitPrice`, `quantity`, `amount`) VALUES
('item123', 123, 'ACS086', 0, 4, 0);

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
('AJA00', 'Nibh Donec Associates', '5649 Amet Street', '(803) 794-3071'),
('ANU70', 'Nec Luctus Felis Industries', 'Ap #429-1649 Non Ave', '(345) 734-3176'),
('ASI03', 'Lorem Vitae Odio Consulting', 'Ap #266-1074 Nam St.', '(732) 217-9000'),
('ATK21', 'Non Corporation', '413-5798 Ligula. Street', '(716) 662-5056'),
('BCI40', 'Egestas Lacinia Sed Foundation', '914-3952 Morbi Av.', '(897) 584-7081'),
('BMA09', 'Mi PC', '5966 Euismod Street', '(248) 212-7355'),
('BOE20', 'Lobortis PC', 'Ap #669-6855 Dui St.', '(626) 648-3119'),
('BPR22', 'Sed Corporation', '102-4916 Integer Avenue', '(239) 711-1076'),
('BQZ38', 'Eu Accumsan LLP', '804-7572 Amet Av.', '(862) 733-3285'),
('BUA71', 'Ut Odio Industries', '1371 Etiam Avenue', '(989) 521-6136'),
('BVU85', 'Id Company', '3253 Odio Ave', '(996) 261-1436'),
('BZA37', 'Mauris Erat Eget Ltd', '837 Enim Avenue', '(213) 870-0092'),
('CPM86', 'Aliquet Industries', '3412 Feugiat. St.', '(873) 340-9450'),
('DMR13', 'Morbi Ltd', 'P.O. Box 168, 4930 Nullam Road', '(274) 307-1544'),
('DMS96', 'Eu Dui LLC', 'P.O. Box 640, 1865 Magna Street', '(679) 779-8417'),
('DVM28', 'Diam Vel Arcu Foundation', '578-1682 Mauris Road', '(769) 197-5322'),
('EPB23', 'Fermentum Ltd', 'Ap #529-3440 Dolor Street', '(699) 950-3276'),
('FDD19', 'Morbi Corporation', '9730 Arcu. Avenue', '(353) 601-2426'),
('FEC32', 'Odio Phasellus At Corp.', '235-6257 Mollis. St.', '(532) 945-1412'),
('FGV94', 'Ligula Limited', '642-2237 Augue Rd.', '(178) 205-9916'),
('FHD37', 'Mi Lacinia Mattis Company', 'P.O. Box 368, 3610 Dui Ave', '(948) 271-0227'),
('FHM49', 'Eros Nec Inc.', 'P.O. Box 928, 6918 Enim Rd.', '(599) 149-7311'),
('FNN22', 'Dui In Sodales Industries', 'P.O. Box 993, 3389 Donec Rd.', '(512) 598-8337'),
('FTY44', 'Natoque Penatibus Institute', '2678 Adipiscing St.', '(348) 273-2667'),
('GAY30', 'Amet Massa Quisque PC', 'Ap #635-9798 Ut Rd.', '(480) 443-1604'),
('GGR02', 'Fermentum Limited', '2743 Nibh Av.', '(482) 592-8554'),
('GHY13', 'Dolor Nulla Limited', 'P.O. Box 805, 1729 Ante. Rd.', '(366) 964-6952'),
('GPL57', 'Nec Ante Company', 'Ap #379-5703 Elit. St.', '(742) 289-2230'),
('GUE44', 'Adipiscing Elit Curabitur LLC', 'Ap #592-8343 Mauris Avenue', '(798) 143-0214'),
('GVQ46', 'A LLP', '7130 Erat. St.', '(372) 274-0068'),
('GVY92', 'Laoreet Libero Et Company', '6194 Aptent Rd.', '(875) 550-3902'),
('HAN95', 'Lorem Ltd', 'P.O. Box 118, 3335 Ipsum St.', '(846) 519-4814'),
('HGP63', 'Fermentum Risus Incorporated', '706-1709 Risus. Road', '(986) 992-1107'),
('HIY26', 'Dui Lectus Corporation', 'P.O. Box 831, 3923 Aenean Ave', '(559) 834-6621'),
('HVT67', 'Cras Eget Nisi Limited', 'Ap #855-3279 Sed St.', '(893) 583-3030'),
('IQH31', 'Eleifend Egestas Corporation', 'P.O. Box 693, 9473 Risus. Ave', '(294) 463-3234'),
('IQI16', 'Vel Turpis Aliquam Company', 'Ap #454-1540 Mauris Av.', '(268) 737-1862'),
('IRZ39', 'Proin Sed Industries', '3227 Ullamcorper Street', '(897) 594-6355'),
('JIR26', 'Proin Incorporated', 'P.O. Box 935, 8745 A St.', '(527) 817-9244'),
('JMP82', 'Quisque Libero Lacus PC', '4302 Quisque Street', '(260) 957-2306'),
('JRV15', 'Sed Foundation', '180-2797 Proin St.', '(513) 726-2396'),
('KBC16', 'Nec Ante Blandit Industries', '7166 Risus. Ave', '(214) 916-8447'),
('KLD99', 'Donec Felis Consulting', 'Ap #971-5281 Tristique Avenue', '(731) 417-4708'),
('KMA20', 'Diam Institute', '728 Dictum Rd.', '(386) 178-4326'),
('KMB05', 'Ut Corp.', '235-3489 Semper. Rd.', '(676) 466-7634'),
('KXX80', 'Vitae Erat Vivamus Institute', '5448 Suspendisse Ave', '(282) 914-8590'),
('LBM32', 'Auctor Odio Institute', '7290 Nec, Road', '(102) 688-6365'),
('LGY91', 'Mauris Corp.', '440 Mattis St.', '(121) 791-6132'),
('LME86', 'Proin Vel Incorporated', '6253 Euismod Road', '(605) 122-2560'),
('LMW37', 'Etiam Ligula Limited', '283-1898 Non, Ave', '(723) 297-0371'),
('LZO71', 'Justo Praesent LLP', 'Ap #489-3068 Diam. Ave', '(383) 362-1433'),
('MFF59', 'Mauris Associates', 'Ap #740-7386 Scelerisque Street', '(509) 583-0871'),
('NBW73', 'Mattis Limited', '5661 Tellus. Road', '(289) 900-8545'),
('NUI73', 'Nunc Corporation', 'Ap #331-7772 Luctus Av.', '(636) 978-8915'),
('NXZ75', 'Varius Nam Porttitor Consulting', '779 At, Street', '(766) 217-5511'),
('OIP96', 'Turpis Egestas Fusce Associates', 'P.O. Box 168, 3548 Euismod St.', '(706) 595-4604'),
('OIR17', 'Nisi Sem Semper PC', '849-982 Imperdiet Rd.', '(148) 511-6329'),
('OPR75', 'Congue Turpis In Company', '853-8998 Dapibus Av.', '(914) 342-8936'),
('OXZ07', 'Erat Volutpat Nulla Foundation', 'P.O. Box 894, 6680 Enim Street', '(888) 276-4562'),
('PAK84', 'Dui Suspendisse Limited', '913-2992 Mi St.', '(232) 779-8559'),
('PBC88', 'Metus Eu Ltd', '1901 Felis Rd.', '(635) 838-0631'),
('PQJ74', 'Accumsan Inc.', '792-1818 Neque Road', '(947) 674-4681'),
('PRR21', 'Duis Mi Enim Company', 'Ap #206-5532 Neque. Rd.', '(496) 382-6350'),
('PUB38', 'Quisque Fringilla Euismod Institute', 'P.O. Box 778, 4313 Donec Ave', '(298) 868-9406'),
('PYC40', 'Dictum Ltd', '735-9407 Pede Ave', '(558) 173-8524'),
('QMO52', 'Dictum Magna Ut Industries', 'P.O. Box 797, 9690 Dolor. Road', '(267) 529-5422'),
('RQL91', 'Lectus Nullam Suscipit PC', '275-4379 Nunc Street', '(307) 113-3117'),
('RUP09', 'Ultricies Dignissim Lacus Ltd', '1520 Arcu. St.', '(527) 408-2801'),
('RVA08', 'Vitae LLC', '752-5858 Mollis. Ave', '(475) 354-7515'),
('RYT32', 'Quisque LLC', 'Ap #914-6642 Vitae, Street', '(138) 271-2959'),
('SDM70', 'Sit Amet Consectetuer Incorporated', 'P.O. Box 860, 2309 Molestie St.', '(751) 756-6037'),
('SHO34', 'Morbi Corp.', '3828 Pellentesque St.', '(476) 995-6851'),
('SLW47', 'Risus Quis Diam PC', '882-9902 Duis Road', '(621) 550-0791'),
('SXP96', 'Aenean Eget Corp.', '2391 Massa. Av.', '(375) 467-6373'),
('TNR72', 'Pede Cum Sociis Corporation', '284-773 Faucibus. Rd.', '(123) 727-4075'),
('TTM78', 'Facilisis Vitae Orci Consulting', '7332 Sed St.', '(161) 954-8631'),
('TVL56', 'Magna Sed LLC', 'Ap #975-9298 Sit Rd.', '(885) 889-4577'),
('UEZ52', 'Eget Metus LLC', 'Ap #849-3037 Vestibulum St.', '(623) 482-6557'),
('UFY10', 'Semper Erat In Foundation', 'Ap #690-7654 Ultrices. Road', '(705) 998-3199'),
('UHM39', 'Nullam Lobortis Quam Limited', '169-7107 Amet St.', '(688) 895-3675'),
('UJW19', 'In Associates', '795-8806 Id Avenue', '(845) 770-4982'),
('UOX81', 'Nisi Dictum Limited', '765 Suspendisse Avenue', '(617) 579-8818'),
('UUL88', 'Neque Incorporated', 'P.O. Box 836, 8536 Proin Rd.', '(463) 364-7761'),
('VHC74', 'Enim Company', 'P.O. Box 783, 1104 Aenean St.', '(488) 790-3220'),
('VJG38', 'Mauris LLC', 'P.O. Box 660, 1325 Eu Rd.', '(638) 556-9189'),
('VLK36', 'Egestas Rhoncus Consulting', 'Ap #595-6052 Nec Ave', '(380) 584-5532'),
('WIT42', 'Tortor Limited', 'P.O. Box 906, 8324 Fermentum Ave', '(953) 938-3028'),
('WJF00', 'Mauris Quis Turpis Incorporated', '8832 Ut Rd.', '(199) 828-8452'),
('WLG42', 'Semper LLP', 'P.O. Box 123, 1461 Aliquam Street', '(534) 968-8967'),
('WNT28', 'Purus Duis Company', 'Ap #334-287 Porttitor Road', '(930) 162-1464'),
('XNM36', 'Non Cursus Non LLC', '780-4816 Egestas. Rd.', '(196) 281-7810'),
('YAO85', 'Gravida Mauris Ut Inc.', '386-6259 Lobortis St.', '(454) 946-1576'),
('YEM27', 'Malesuada Id Associates', 'Ap #617-6587 Orci. Avenue', '(650) 614-8415'),
('YMJ64', 'Pede Associates', '717-3060 Tellus. Rd.', '(385) 895-1599'),
('YNH74', 'Blandit Viverra Associates', 'P.O. Box 884, 976 Ac Rd.', '(635) 296-3457'),
('YNL98', 'Ipsum Primis LLC', 'Ap #988-9476 Tincidunt Ave', '(103) 442-0970'),
('ZCL26', 'Risus Duis Corp.', 'Ap #108-6793 Fringilla St.', '(340) 887-4688'),
('ZCR94', 'Volutpat Ornare Facilisis Inc.', '8281 Erat Ave', '(191) 465-7757'),
('ZDF88', 'Nec Cursus A Incorporated', 'Ap #412-1871 Etiam St.', '(504) 301-9147'),
('ZIZ98', 'Commodo Auctor Incorporated', 'Ap #449-3106 Hendrerit Street', '(840) 373-7611');

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

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`invoiceID`, `dCode`, `invoiceDate`, `invoiceNote`, `invoicePayMode`, `invoiceTotal`) VALUES
('11', 'ASI03', '2016-01-04', '', '', 0),
('AA5540', 'KXX80', '2016-03-12', 'orci. Donec nibh. Quisque nonummy ipsum non arcu.', 'cheque', 6146.17),
('AN7897', 'LBM32', '2015-12-11', 'non justo. Proin non massa non ante', 'cheque', 7431.44),
('AU2507', 'GHY13', '2015-05-22', 'et malesuada fames ac turpis egestas. Aliquam fringilla cursus', 'credit', 5187.01),
('AZ5250', 'YAO85', '2016-11-09', 'sapien. Nunc pulvinar arcu', 'paid', 7344.41),
('BH0582', 'NBW73', '2016-04-06', 'elit pede, malesuada vel,', 'cash', 8504.65),
('BL9236', 'YNL98', '2015-12-23', 'eget odio. Aliquam vulputate ullamcorper magna. Sed eu eros.', 'cheque', 1810.52),
('CJ4035', 'CPM86', '2016-04-17', 'sem mollis', 'cheque', 5620.21),
('CM6382', 'RQL91', '2015-04-16', 'urna suscipit nonummy. Fusce fermentum fermentum arcu. Vestibulum ante ipsum', 'cash', 4112.3),
('CQ8581', 'VJG38', '2016-07-09', 'ipsum sodales purus, in molestie tortor nibh', 'cash', 9576.64),
('CT3652', 'JMP82', '2015-01-24', 'neque venenatis lacus. Etiam bibendum fermentum metus. Aenean sed pede', 'cheque', 9127.76),
('CZ4083', 'DVM28', '2016-03-27', 'rutrum. Fusce dolor quam,', 'cheque', 3600.04),
('DD3554', 'NBW73', '2015-12-06', 'eu turpis. Nulla', 'cash', 5738.55),
('DI1368', 'KXX80', '2015-08-17', 'amet risus. Donec egestas. Aliquam nec enim. Nunc', 'cash', 6898.28),
('DO0258', 'IQI16', '2015-08-15', 'augue', 'paid', 9425.26),
('DT8149', 'TVL56', '2015-06-16', 'Donec feugiat metus sit amet', 'cash', 6645.97),
('DU2787', 'FEC32', '2016-11-22', 'purus ac tellus. Suspendisse sed', 'cash', 4111.8),
('DW6253', 'BMA09', '2016-02-20', 'massa. Mauris vestibulum, neque', 'credit', 6490.24),
('EG5562', 'AJA00', '2016-07-18', 'neque. In ornare sagittis', 'paid', 6270.85),
('EI0604', 'LGY91', '2016-11-29', 'tellus justo sit amet', 'paid', 3023.36),
('EL9342', 'PQJ74', '2015-02-02', 'felis, adipiscing fringilla, porttitor vulputate, posuere vulputate, lacus.', 'cash', 2974.32),
('EQ8174', 'OXZ07', '2016-11-17', 'facilisis lorem tristique aliquet. Phasellus fermentum convallis ligula.', 'credit', 6987.98),
('ET2778', 'KBC16', '2015-06-17', 'metus urna convallis erat, eget tincidunt dui', 'paid', 2798.46),
('FH5096', 'YEM27', '2016-08-06', 'adipiscing fringilla,', 'cheque', 1934.26),
('FL2184', 'SHO34', '2016-01-14', 'orci. Donec nibh. Quisque nonummy ipsum non arcu.', 'cash', 4854.9),
('FV0113', 'IRZ39', '2016-01-31', 'Duis', 'cheque', 3028.18),
('GF2330', 'TNR72', '2016-04-15', 'at augue id ante', 'cash', 4290.33),
('GG5186', 'PQJ74', '2016-04-13', 'malesuada fames ac turpis egestas. Fusce aliquet magna', 'paid', 8065.96),
('GH1830', 'HIY26', '2015-10-17', 'pretium aliquet, metus urna convallis erat, eget tincidunt dui', 'cheque', 8345.72),
('GV2155', 'GVQ46', '2015-04-21', 'egestas lacinia. Sed congue,', 'credit', 6514.03),
('HB0711', 'VLK36', '2016-08-15', 'dui. Fusce aliquam, enim nec tempus scelerisque, lorem', 'cheque', 4845.19),
('HN0929', 'OXZ07', '2016-07-27', 'pellentesque, tellus sem mollis dui, in sodales elit erat vitae', 'credit', 3853.86),
('HT8396', 'LMW37', '2015-08-22', 'pede. Praesent eu dui. Cum sociis natoque', 'cheque', 7001.55),
('IL4275', 'YNL98', '2015-06-21', 'massa. Mauris vestibulum, neque sed', 'credit', 6146.52),
('IR5637', 'HAN95', '2015-07-23', 'in, hendrerit consectetuer,', 'cheque', 4129.98),
('JE8224', 'VJG38', '2015-01-26', 'inceptos hymenaeos.', 'cheque', 1235.07),
('KL0614', 'LMW37', '2015-01-14', 'nunc. In at pede.', 'cheque', 5380.69),
('KL8453', 'KXX80', '2016-04-08', 'penatibus et magnis dis parturient montes,', 'credit', 8051.99),
('KQ7356', 'LZO71', '2016-06-01', 'Fusce', 'paid', 3957.88),
('KX6461', 'IRZ39', '2016-02-02', 'in, hendrerit consectetuer, cursus et, magna. Praesent', 'cash', 7781.78),
('KY7647', 'GUE44', '2015-02-27', 'senectus et', 'credit', 4958.49),
('LA3169', 'ZDF88', '2015-02-25', 'vel, venenatis vel, faucibus id, libero.', 'cheque', 6315.26),
('LJ9218', 'PYC40', '2016-10-15', 'justo faucibus lectus, a', 'cash', 4054.73),
('LZ8825', 'FGV94', '2016-08-16', 'non lorem vitae odio sagittis', 'credit', 5926.9),
('MH7226', 'WIT42', '2016-08-06', 'vitae velit egestas lacinia. Sed', 'cash', 2383.86),
('MV6361', 'VHC74', '2015-03-03', 'Pellentesque ut ipsum ac mi eleifend egestas. Sed pharetra, felis', 'credit', 3613.38),
('NA1658', 'ZIZ98', '2016-01-26', 'lacus. Etiam bibendum fermentum', 'cash', 3809.17),
('ND3983', 'DMS96', '2016-11-24', 'tortor, dictum', 'cash', 1939.46),
('NL1018', 'TVL56', '2015-05-23', 'molestie orci tincidunt adipiscing. Mauris molestie pharetra', 'credit', 8160.73),
('NN9858', 'EPB23', '2016-12-01', 'imperdiet ornare. In faucibus.', 'paid', 6445.03),
('NS4032', 'PQJ74', '2016-04-08', 'Donec at arcu.', 'paid', 2950.13),
('NS7957', 'UEZ52', '2015-05-27', 'eu, ultrices sit amet, risus. Donec nibh enim, gravida sit', 'cheque', 8501.42),
('ON3793', 'YEM27', '2015-11-21', 'montes,', 'cash', 8469.79),
('OO8798', 'PQJ74', '2016-05-20', 'sit amet lorem semper auctor. Mauris', 'cash', 1957.13),
('OY9155', 'XNM36', '2015-09-14', 'felis eget varius ultrices, mauris', 'credit', 1314.76),
('PB0616', 'SDM70', '2016-09-25', 'sed orci lobortis augue scelerisque', 'cheque', 3229.08),
('PP5100', 'GHY13', '2014-12-09', 'eleifend', 'paid', 2231.38),
('PV5845', 'LME86', '2015-06-02', 'eget nisi dictum augue malesuada', 'credit', 6618.62),
('PZ1363', 'DMR13', '2016-02-20', 'enim, gravida', 'cash', 3061.07),
('QD4257', 'WNT28', '2016-10-24', 'lorem lorem, luctus ut, pellentesque eget, dictum placerat, augue.', 'cheque', 7518.62),
('RC5576', 'BCI40', '2015-08-17', 'pretium et, rutrum', 'cash', 2717.49),
('RG9347', 'BMA09', '2015-11-27', 'Phasellus dapibus quam quis diam. Pellentesque habitant morbi tristique', 'paid', 4731.6),
('RL0040', 'LBM32', '2015-04-03', 'penatibus et magnis dis parturient montes, nascetur', 'credit', 6478.04),
('RN4411', 'RYT32', '2016-03-06', 'condimentum. Donec at arcu. Vestibulum ante', 'cheque', 5767.74),
('RO3832', 'KMB05', '2015-11-04', 'felis purus ac', 'paid', 7128.11),
('RQ9183', 'KBC16', '2016-03-05', 'et ultrices', 'cash', 4066.29),
('RW0000', 'SDM70', '2015-01-27', 'in faucibus orci luctus et', 'credit', 7942.43),
('SC4649', 'QMO52', '2015-02-19', 'Morbi accumsan laoreet ipsum. Curabitur consequat,', 'credit', 7401.44),
('SD6249', 'JRV15', '2015-06-26', 'ultrices iaculis odio. Nam interdum enim non nisi. Aenean eget', 'paid', 6712.24),
('SH9761', 'KLD99', '2015-04-30', 'dui quis accumsan convallis, ante lectus convallis', 'cheque', 9475),
('SJ6676', 'YAO85', '2015-01-13', 'Donec nibh enim,', 'cash', 3002.96),
('SK4421', 'DMR13', '2015-02-20', 'non, vestibulum nec, euismod in, dolor. Fusce feugiat.', 'cheque', 8764.58),
('SM3913', 'GPL57', '2015-01-08', 'eu tellus. Phasellus', 'credit', 3780.1),
('SN5663', 'VHC74', '2015-04-09', 'sociis natoque penatibus et magnis dis parturient montes, nascetur', 'cheque', 3456.75),
('SU4239', 'BUA71', '2016-06-19', 'Cras vulputate velit eu sem. Pellentesque ut ipsum ac mi', 'paid', 8637.77),
('SX3796', 'PYC40', '2015-11-06', 'auctor', 'credit', 7837.98),
('TE0984', 'DMS96', '2016-08-11', 'sapien. Aenean massa. Integer vitae', 'cash', 7822.19),
('TI5244', 'BCI40', '2015-04-13', 'tellus lorem eu metus. In lorem. Donec', 'cheque', 2811.01),
('TM5307', 'WIT42', '2016-07-15', 'pulvinar arcu et pede. Nunc sed orci', 'credit', 2581.9),
('TT4614', 'GGR02', '2016-09-29', 'purus. Maecenas libero est,', 'cheque', 2478.05),
('TW7914', 'VJG38', '2016-10-16', 'varius ultrices, mauris ipsum', 'cheque', 2545.94),
('TZ8186', 'CPM86', '2015-03-08', 'ut nisi a odio semper cursus. Integer mollis. Integer', 'credit', 8315.45),
('UE3897', 'BCI40', '2015-11-15', 'lacus pede sagittis augue, eu', 'paid', 2900.47),
('VC6293', 'FEC32', '2016-04-03', 'enim, condimentum eget, volutpat ornare, facilisis eget, ipsum. Donec sollicitudin', 'cash', 8620.15),
('VW0525', 'DVM28', '2015-09-25', 'cursus et, eros. Proin ultrices. Duis', 'cheque', 6745.1),
('VX7564', 'KLD99', '2015-08-13', 'tellus. Phasellus elit pede,', 'credit', 3915.54),
('WQ9072', 'TTM78', '2016-02-22', 'velit egestas lacinia. Sed congue,', 'cheque', 6014.66),
('WX3765', 'HGP63', '2015-11-22', 'sagittis placerat. Cras dictum ultricies ligula. Nullam', 'cheque', 2847.37),
('XB6956', 'VHC74', '2015-06-25', 'nulla vulputate dui, nec tempus mauris erat eget', 'paid', 4171.56),
('XM3877', 'YAO85', '2016-01-13', 'auctor. Mauris vel turpis.', 'cheque', 9936.62),
('XP0317', 'RYT32', '2015-06-25', 'iaculis, lacus pede', 'paid', 4983.72),
('XS8059', 'BPR22', '2015-10-25', 'vitae dolor. Donec', 'cheque', 2170.03),
('XT5024', 'WNT28', '2016-11-18', 'aliquam eros turpis non enim. Mauris quis turpis vitae', 'cheque', 7783.56),
('YC2469', 'DVM28', '2015-05-20', 'Aliquam', 'cash', 3248.93),
('YJ7527', 'BPR22', '2015-02-17', 'vel lectus. Cum sociis natoque penatibus et magnis dis parturient', 'cash', 9798.61),
('YO5238', 'BZA37', '2015-03-16', 'eleifend nec, malesuada ut,', 'paid', 3473.38),
('YR0292', 'UOX81', '2016-09-25', 'est mauris, rhoncus id, mollis nec, cursus a, enim.', 'credit', 1891.47),
('YR0732', 'PBC88', '2016-04-21', 'sed libero. Proin sed turpis', 'cheque', 7812.33),
('ZA1192', 'BQZ38', '2016-08-14', 'facilisis vitae, orci. Phasellus dapibus quam quis diam. Pellentesque habitant', 'credit', 8825.41),
('ZK7176', 'RYT32', '2015-03-26', 'est ac facilisis facilisis, magna tellus faucibus leo, in lobortis', 'credit', 8257.55),
('ZW7439', 'IRZ39', '2015-04-01', 'mi tempor lorem, eget', 'paid', 9785.69);

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

--
-- Dumping data for table `invoiceitem`
--

INSERT INTO `invoiceitem` (`invoiceItemID`, `invoiceID`, `productID`, `packSize`, `quantity`, `free`, `price`, `margin`, `expireDate`, `discount`, `sold`) VALUES
('02431', 'YR0292', 'MUI907', 5, 183, 9, 291.79, 9, '2016-04-11', 25.15, 85),
('03613', 'DU2787', 'MXN098', 30, 95, 3, 294.36, 10, '2015-08-19', 46.66, 47),
('05601', 'GV2155', 'MUI907', 40, 158, 3, 40.7, 7, '2015-08-05', 22.3, 29),
('06084', 'SJ6676', 'LOY975', 40, 8, 9, 209.72, 4, '2016-07-05', 38.88, 26),
('06712', 'XS8059', 'MGD166', 50, 141, 1, 132.15, 8, '2015-08-15', 13.75, 50),
('07816', 'GV2155', 'SPE445', 10, 112, 7, 236.99, 7, '2016-10-01', 7.79, 83),
('09700', 'TI5244', 'BVK194', 10, 69, 7, 234.19, 7, '2015-01-29', 93.59, 23),
('10063', 'SM3913', 'TAZ604', 40, 133, 2, 282.8, 3, '2016-04-17', 67.75, 11),
('10147', 'HT8396', 'CUP149', 20, 151, 2, 98.95, 9, '2016-10-24', 5.52, 64),
('10444', 'SU4239', 'IHY812', 30, 109, 2, 242.97, 9, '2014-12-25', 8.09, 27),
('11097', 'PV5845', 'LIJ155', 20, 11, 10, 177.33, 11, '2015-08-01', 18.23, 85),
('11117', 'KX6461', 'WRK431', 10, 67, 10, 4.4, 7, '2016-10-05', 23.27, 83),
('12387', 'SC4649', 'MUI907', 5, 37, 5, 142.23, 12, '2016-01-10', 90.46, 34),
('13766', 'ZW7439', 'CBC658', 10, 56, 6, 486.38, 12, '2015-03-08', 20.86, 9),
('14104', 'TW7914', 'EVM620', 50, 183, 5, 68.59, 6, '2016-05-26', 32.06, 25),
('14515', 'KY7647', 'AOU150', 20, 181, 8, 117.72, 2, '2016-01-26', 54.57, 85),
('15537', 'VW0525', 'JGD849', 40, 90, 6, 334.25, 12, '2015-03-19', 81, 68),
('15541', 'SU4239', 'LIJ155', 30, 178, 2, 302.95, 7, '2015-02-15', 38.86, 60),
('15561', 'NL1018', 'FSA889', 20, 61, 4, 250.93, 20, '2015-12-20', 36.17, 52),
('15831', 'LZ8825', 'DWS415', 10, 83, 6, 280.78, 9, '2016-04-17', 19.27, 27),
('16010', 'LZ8825', 'WIC547', 50, 25, 2, 350.28, 5, '2014-12-07', 40.76, 9),
('17548', 'VW0525', 'IKO718', 20, 191, 4, 405.52, 9, '2016-06-22', 9.14, 79),
('19358', 'SD6249', 'IKO718', 5, 74, 1, 7.77, 3, '2016-05-06', 31.4, 13),
('19979', 'RO3832', 'XJK330', 30, 93, 6, 167.5, 14, '2016-01-30', 73.14, 25),
('21847', 'FV0113', 'UMX932', 10, 150, 1, 431.19, 13, '2016-11-17', 6.96, 87),
('22657', 'YJ7527', 'EVM620', 20, 17, 6, 264.18, 8, '2015-07-06', 69.97, 7),
('22741', 'NA1658', 'ROF150', 40, 95, 1, 115.55, 7, '2014-12-18', 10.9, 68),
('25290', 'RG9347', 'TKT738', 40, 141, 6, 213.67, 14, '2016-02-21', 28.52, 18),
('26230', 'DU2787', 'WHP449', 30, 75, 3, 79.54, 11, '2016-07-02', 99.27, 61),
('26712', 'LA3169', 'AOQ236', 30, 21, 1, 385.11, 20, '2015-05-11', 87.82, 4),
('26763', 'DU2787', 'WFJ819', 40, 62, 4, 268.89, 1, '2015-07-17', 89.86, 28),
('27036', 'ND3983', 'ZVW610', 5, 63, 4, 319.62, 6, '2014-12-08', 20.91, 87),
('28306', 'VC6293', 'ZCC371', 5, 113, 7, 48.92, 9, '2015-02-09', 19.11, 32),
('29951', 'LJ9218', 'LIJ155', 5, 195, 10, 379.62, 19, '2016-06-25', 74.73, 18),
('31266', 'GH1830', 'WGB126', 10, 194, 7, 488.15, 15, '2015-06-22', 15.9, 58),
('31750', 'WX3765', 'KJZ472', 20, 154, 9, 192.72, 3, '2016-03-05', 46.95, 57),
('32866', 'WX3765', 'ZCC371', 50, 107, 3, 445.54, 10, '2015-11-03', 6.44, 26),
('33219', 'OO8798', 'FPJ580', 40, 158, 10, 463.72, 13, '2015-11-04', 22.1, 45),
('34231', 'LJ9218', 'OBC738', 10, 166, 9, 14.05, 18, '2015-01-30', 12.9, 39),
('35005', 'GV2155', 'DOD206', 50, 102, 5, 93.2, 3, '2015-06-14', 47.82, 56),
('37909', 'DD3554', 'NUO966', 40, 101, 10, 85.65, 11, '2016-02-28', 26.04, 9),
('39719', 'SC4649', 'JNK822', 30, 49, 2, 390.89, 14, '2016-01-27', 76.49, 11),
('41751', 'ET2778', 'VLR524', 10, 107, 1, 178.19, 13, '2016-09-13', 27.86, 88),
('42013', 'BH0582', 'QTG960', 50, 157, 5, 233.94, 13, '2016-07-01', 37.51, 12),
('42690', 'PZ1363', 'IOX618', 30, 126, 8, 369.29, 16, '2016-06-12', 49.19, 55),
('42967', 'QD4257', 'EBP104', 30, 78, 10, 50.79, 13, '2016-06-10', 98.56, 75),
('44207', 'SX3796', 'BLP728', 20, 52, 7, 459.41, 4, '2015-10-31', 58.93, 79),
('46167', 'RL0040', 'ACS086', 20, 67, 5, 412.33, 14, '2016-01-15', 73.83, 96),
('47644', 'YR0292', 'WIC547', 10, 81, 2, 146.85, 6, '2016-09-20', 24.96, 33),
('48570', 'EG5562', 'WHP449', 50, 54, 6, 148.17, 10, '2015-05-30', 95.53, 75),
('51098', 'WX3765', 'DWS415', 10, 145, 7, 314.86, 11, '2016-04-28', 51.28, 24),
('53826', 'KQ7356', 'ACS086', 50, 45, 9, 346.51, 15, '2015-04-27', 63.96, 36),
('53843', 'BH0582', 'FSA889', 30, 54, 3, 117.54, 5, '2015-07-23', 90.29, 4),
('54317', 'RW0000', 'LCS932', 30, 164, 8, 117.91, 12, '2016-04-05', 30.36, 2),
('54982', 'XM3877', 'DTW117', 50, 119, 1, 249.37, 10, '2016-05-31', 61.86, 3),
('55596', 'NL1018', 'XYH753', 5, 147, 3, 309.19, 14, '2016-01-16', 24.25, 32),
('55870', 'AZ5250', 'SWG490', 40, 51, 1, 361.54, 17, '2015-10-19', 15.8, 4),
('59056', 'DD3554', 'WHP449', 50, 184, 8, 466.23, 3, '2015-09-26', 35.83, 24),
('59446', 'GH1830', 'CAG787', 10, 91, 6, 80.03, 5, '2015-05-03', 76.65, 64),
('59671', 'YC2469', 'MXN098', 10, 130, 7, 304.63, 15, '2015-11-11', 75.12, 55),
('60075', 'TE0984', 'VEY250', 20, 179, 2, 454.65, 16, '2015-11-22', 38.12, 6),
('62109', 'TZ8186', 'ZLE655', 40, 72, 6, 443.79, 5, '2016-07-11', 95.42, 97),
('62924', 'DO0258', 'CWJ743', 30, 102, 7, 242.04, 13, '2015-04-02', 15.42, 75),
('63197', 'UE3897', 'UIX015', 10, 51, 6, 482.65, 3, '2015-06-30', 94.39, 13),
('65831', 'SX3796', 'UIX015', 30, 74, 4, 232.29, 7, '2015-03-03', 76.5, 74),
('70899', 'RL0040', 'ZLE655', 20, 43, 7, 22.79, 9, '2015-01-20', 40.91, 54),
('71458', 'GH1830', 'MXN098', 50, 44, 2, 228.17, 12, '2015-05-27', 22.35, 19),
('72383', 'SC4649', 'XWD154', 30, 189, 1, 84.79, 12, '2015-01-11', 73.75, 21),
('72699', 'KX6461', 'XWD154', 40, 155, 10, 408.96, 9, '2015-04-08', 67.9, 75),
('73476', 'NL1018', 'CUP149', 10, 99, 10, 186.63, 1, '2016-10-25', 29.43, 11),
('73951', 'LZ8825', 'VEY250', 40, 76, 7, 45.61, 9, '2015-03-31', 64.81, 16),
('73954', 'NS7957', 'QTC220', 40, 28, 1, 103.37, 16, '2014-12-22', 34.08, 67),
('74334', 'HT8396', 'ALQ441', 40, 156, 5, 226.89, 13, '2016-01-30', 46.73, 51),
('74938', 'ET2778', 'PPQ560', 50, 27, 6, 476.36, 6, '2016-01-20', 39.26, 43),
('75457', 'EI0604', 'DTW117', 10, 77, 2, 93.13, 11, '2015-10-20', 81.16, 67),
('76931', 'RN4411', 'ACS086', 5, 32, 3, 237.71, 6, '2015-04-06', 88.63, 74),
('78103', 'PZ1363', 'OBC738', 5, 99, 1, 221.21, 9, '2016-06-20', 6.02, 74),
('79479', 'LJ9218', 'TUL176', 40, 11, 8, 194.15, 2, '2016-06-29', 84.35, 10),
('80957', 'DU2787', 'IHY812', 5, 170, 1, 447.85, 16, '2015-04-21', 71.51, 51),
('81474', 'ND3983', 'KIL769', 30, 157, 2, 93.56, 14, '2016-05-25', 33.93, 100),
('81649', 'QD4257', 'UCA535', 40, 18, 5, 492.02, 11, '2016-05-22', 11.1, 44),
('81900', 'CM6382', 'XKL438', 10, 169, 8, 152.67, 13, '2015-07-16', 83.71, 22),
('82269', 'RL0040', 'WGB126', 5, 86, 10, 283.77, 10, '2016-10-27', 43.53, 90),
('84145', 'SU4239', 'BOV784', 20, 147, 2, 42.55, 12, '2016-10-25', 14.71, 76),
('84894', 'PZ1363', 'XTG214', 30, 127, 2, 373.19, 13, '2016-05-09', 53.76, 10),
('86294', 'EI0604', 'ROF150', 50, 20, 2, 183.82, 18, '2016-06-24', 77.37, 80),
('87575', 'YR0732', 'EAC894', 30, 183, 2, 54.56, 18, '2015-08-20', 55, 58),
('87632', 'RL0040', 'DOD206', 10, 93, 10, 393.51, 13, '2015-12-31', 83.84, 94),
('92484', 'TT4614', 'TKT738', 5, 170, 5, 473.53, 20, '2015-04-07', 5.26, 100),
('93001', 'SD6249', 'BOV784', 30, 73, 3, 427.53, 1, '2016-08-15', 76.16, 74),
('94438', 'SH9761', 'NUO966', 40, 19, 2, 110.98, 1, '2015-12-03', 25.34, 75),
('94754', 'YO5238', 'CUP149', 50, 106, 2, 36.18, 3, '2015-05-12', 9.19, 48),
('95163', 'SX3796', 'EBP104', 10, 140, 5, 379.51, 9, '2016-07-11', 51.86, 42),
('95196', 'DT8149', 'EBP104', 40, 39, 3, 15.78, 15, '2016-01-21', 51.82, 44),
('95699', 'SU4239', 'BNW517', 5, 49, 7, 404.16, 9, '2015-07-10', 75.35, 22),
('96227', 'SH9761', 'CBC658', 40, 65, 10, 32.66, 12, '2016-09-12', 82.62, 72),
('96408', 'XP0317', 'XJK330', 10, 77, 10, 45.58, 19, '2015-04-26', 72.29, 29),
('97520', 'KL8453', 'BVK194', 10, 170, 9, 153.87, 19, '2016-02-16', 32.51, 41),
('98896', 'ET2778', 'LCS932', 10, 85, 5, 329.8, 2, '2016-05-21', 27.32, 47),
('99779', 'SH9761', 'QQF569', 50, 121, 9, 251.48, 19, '2016-05-28', 89.87, 6);

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
-- Dumping data for table `product`
--

INSERT INTO `product` (`productID`, `productName`, `productDescription`, `productBrand`, `productStrength`, `productType`, `productStock`, `productMinStock`, `unitPrice`) VALUES
('ACS086', 'Famotidine', 'vulputate ullamcorper magna. Sed eu eros. Nam consequat dolor vitae dolor. Donec fringilla. Donec feugiat', 'Mauris Ipsum Associates', 808, 'seven', 290, 90, 8720.2),
('ALQ441', 'Lorazepam', 'In mi pede, nonummy ut, molestie in, tempus eu, ligula. Aenean euismod mauris eu elit.', 'Convallis Incorporated', 851, 'two', 112, 20, 5665.08),
('AOQ236', 'Simvastatin', 'sollicitudin adipiscing ligula. Aenean gravida nunc sed pede. Cum sociis natoque penatibus et magnis dis', 'Amet Risus Donec Associates', 770, 'seven', 379, 90, 9569.46),
('AOU150', 'Digoxin', 'id nunc interdum feugiat. Sed nec metus facilisis lorem tristique aliquet. Phasellus fermentum convallis ligula.', 'Vestibulum Neque Consulting', 590, 'three', 249, 10, 7546.03),
('AUC383', 'Lisinopril', 'ornare. Fusce mollis. Duis sit amet diam eu dolor egestas rhoncus. Proin nisl sem, consequat', 'Convallis Erat Eget LLC', 496, 'one', 374, 20, 8066.61),
('BBN318', 'Vytorin', 'tempus eu, ligula. Aenean euismod mauris eu elit. Nulla facilisi. Sed neque. Sed eget lacus.', 'Nunc Quisque Ornare Corporation', 507, 'ten', 56, 30, 3247.35),
('BLP728', 'Amlodipine Besylate', 'a, aliquet vel, vulputate eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris', 'Ac Incorporated', 278, 'five', 356, 10, 6624.2),
('BNW517', 'Prednisone', 'euismod ac, fermentum vel, mauris. Integer sem elit, pharetra ut, pharetra sed, hendrerit a, arcu.', 'Mauris Corporation', 957, 'eight', 405, 10, 3953.57),
('BOV784', 'Losartan Potassium', 'magna, malesuada vel, convallis in, cursus et, eros. Proin ultrices. Duis volutpat nunc sit amet', 'Consequat Auctor Nunc LLP', 607, 'seven', 408, 50, 8893.07),
('BVK194', 'Alendronate Sodium', 'dictum augue malesuada malesuada. Integer id magna et ipsum cursus vestibulum. Mauris magna. Duis dignissim', 'Ac Libero Nec PC', 240, 'eight', 477, 10, 7027.08),
('CAG787', 'Sertraline HCl', 'amet, dapibus id, blandit at, nisi. Cum sociis natoque penatibus et magnis dis parturient montes,', 'Elit Elit Inc.', 961, 'five', 473, 70, 3061.85),
('CBC658', 'Benicar HCT', 'lorem ipsum sodales purus, in molestie tortor nibh sit amet orci. Ut sagittis lobortis mauris.', 'Urna Et Arcu Associates', 24, 'eight', 177, 50, 4937.43),
('CUP149', 'Amlodipine Besylate', 'eros nec tellus. Nunc lectus pede, ultrices a, auctor non, feugiat nec, diam. Duis mi', 'Nec Mauris Blandit Associates', 136, 'one', 353, 40, 6526.48),
('CWJ743', 'Doxycycline Hyclate', 'Curae; Phasellus ornare. Fusce mollis. Duis sit amet diam eu dolor egestas rhoncus. Proin nisl', 'Placerat Velit Ltd', 18, 'one', 87, 70, 5410.88),
('DOD206', 'Metformin HCl', 'nascetur ridiculus mus. Aenean eget magna. Suspendisse tristique neque venenatis lacus. Etiam bibendum fermentum metus.', 'Integer Vulputate Risus Corporation', 21, 'eight', 59, 90, 3822.8),
('DTW117', 'Ciprofloxacin HCl', 'dolor. Fusce feugiat. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aliquam auctor, velit eget', 'Dolor Dapibus Gravida Institute', 741, 'three', 282, 30, 2866.97),
('DWS415', 'Lidoderm', 'amet lorem semper auctor. Mauris vel turpis. Aliquam adipiscing lobortis risus. In mi pede, nonummy', 'Euismod Mauris Consulting', 257, 'nine', 492, 70, 1529.04),
('EAC894', 'Atenolol', 'consectetuer euismod est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis', 'A Incorporated', 525, 'one', 414, 20, 5332.45),
('EBG949', 'Vytorin', 'Mauris molestie pharetra nibh. Aliquam ornare, libero at auctor ullamcorper, nisl arcu iaculis enim, sit', 'Mattis Cras Inc.', 536, 'six', 354, 30, 4587.36),
('EBP104', 'Gianvi', 'ultrices. Duis volutpat nunc sit amet metus. Aliquam erat volutpat. Nulla facilisis. Suspendisse commodo tincidunt', 'Egestas Hendrerit Neque Inc.', 810, 'nine', 171, 40, 9664.67),
('EHF831', 'Niaspan', 'eu metus. In lorem. Donec elementum, lorem ut aliquam iaculis, lacus pede sagittis augue, eu', 'Nonummy LLC', 33, 'five', 12, 10, 8152.15),
('EQP946', 'Enalapril Maleate', 'odio. Nam interdum enim non nisi. Aenean eget metus. In nec orci. Donec nibh. Quisque', 'Nec Diam Inc.', 388, 'eight', 292, 30, 4748.66),
('EVM620', 'Clindamycin HCl', 'vitae, orci. Phasellus dapibus quam quis diam. Pellentesque habitant morbi tristique senectus et netus et', 'Adipiscing Fringilla Porttitor Institute', 455, 'ten', 140, 40, 8252.2),
('FPJ580', 'Januvia', 'vel arcu eu odio tristique pharetra. Quisque ac libero nec ligula consectetuer rhoncus. Nullam velit', 'Cursus Diam Consulting', 104, 'four', 131, 40, 8685.47),
('FSA889', 'APAP/Codeine', 'blandit. Nam nulla magna, malesuada vel, convallis in, cursus et, eros. Proin ultrices. Duis volutpat', 'Nunc Industries', 423, 'ten', 199, 40, 5809.72),
('GAT557', 'Plavix', 'fringilla purus mauris a nunc. In at pede. Cras vulputate velit eu sem. Pellentesque ut', 'Libero Dui Nec Company', 718, 'one', 84, 10, 9497.65),
('GEQ475', 'Gabapentin', 'mus. Proin vel nisl. Quisque fringilla euismod enim. Etiam gravida molestie arcu. Sed eu nibh', 'Integer Vulputate PC', 122, 'six', 71, 30, 8891.32),
('HFC067', 'Amoxicillin', 'Fusce mollis. Duis sit amet diam eu dolor egestas rhoncus. Proin nisl sem, consequat nec,', 'Phasellus Elit Pede LLC', 879, 'one', 170, 10, 3121.94),
('IFB929', 'Simvastatin', 'penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec dignissim magna a tortor. Nunc', 'Urna Suscipit Nonummy Corporation', 903, 'ten', 232, 60, 5663.56),
('IHY812', 'Diovan HCT', 'luctus et ultrices posuere cubilia Curae; Phasellus ornare. Fusce mollis. Duis sit amet diam eu', 'Varius Ultrices Mauris Limited', 834, 'one', 333, 80, 9203.06),
('IJJ179', 'Zyprexa', 'pede, malesuada vel, venenatis vel, faucibus id, libero. Donec consectetuer mauris id sapien. Cras dolor', 'In LLP', 391, 'four', 332, 90, 6354.92),
('IKO718', 'Vitamin D (Rx)', 'montes, nascetur ridiculus mus. Proin vel nisl. Quisque fringilla euismod enim. Etiam gravida molestie arcu.', 'Mauris Ut Mi Foundation', 869, 'two', 448, 10, 4120.27),
('IOX618', 'Cialis', 'metus. Aenean sed pede nec ante blandit viverra. Donec tempus, lorem fringilla ornare placerat, orci', 'Donec Porttitor Tellus LLC', 919, 'two', 368, 90, 3129.77),
('ISO590', 'Oxycontin', 'nec enim. Nunc ut erat. Sed nunc est, mollis non, cursus non, egestas a, dui.', 'Morbi Quis Urna PC', 826, 'nine', 453, 70, 3010.81),
('JBB699', 'Glipizide', 'Mauris vestibulum, neque sed dictum eleifend, nunc risus varius orci, in consequat enim diam vel', 'Sociis Natoque LLP', 90, 'nine', 91, 70, 7123.1),
('JGD849', 'Hydrocodone/APAP', 'eu, eleifend nec, malesuada ut, sem. Nulla interdum. Curabitur dictum. Phasellus in felis. Nulla tempor', 'Eget Volutpat Corp.', 514, 'three', 248, 40, 2974.67),
('JNK822', 'Triamterene/Hydrochlorothiazide', 'nibh enim, gravida sit amet, dapibus id, blandit at, nisi. Cum sociis natoque penatibus et', 'At Pede Foundation', 553, 'three', 176, 10, 1958.71),
('KIL769', 'Alprazolam', 'et risus. Quisque libero lacus, varius et, euismod et, commodo at, libero. Morbi accumsan laoreet', 'Semper Corporation', 943, 'six', 448, 20, 6960.77),
('KJZ472', 'Simvastatin', 'purus mauris a nunc. In at pede. Cras vulputate velit eu sem. Pellentesque ut ipsum', 'Faucibus Ut Nulla Ltd', 315, 'three', 371, 30, 3510.83),
('KLE347', 'Amoxicillin Trihydrate/Potassium Clavulanate', 'faucibus id, libero. Donec consectetuer mauris id sapien. Cras dolor dolor, tempus non, lacinia at,', 'Venenatis A Ltd', 316, 'nine', 65, 90, 2107.39),
('LCS932', 'Metoprolol Succinatee', 'magna et ipsum cursus vestibulum. Mauris magna. Duis dignissim tempor arcu. Vestibulum ut eros non', 'Tincidunt Congue Turpis Associates', 931, 'nine', 398, 60, 2417.62),
('LIJ155', 'Diovan HCT', 'lectus pede et risus. Quisque libero lacus, varius et, euismod et, commodo at, libero. Morbi', 'Ante Maecenas Mi Company', 976, 'nine', 99, 50, 8107.72),
('LOY975', 'Fluconazole', 'Nulla dignissim. Maecenas ornare egestas ligula. Nullam feugiat placerat velit. Quisque varius. Nam porttitor scelerisque', 'Placerat Company', 930, 'seven', 360, 70, 3593.09),
('LRN930', 'Lovaza', 'diam nunc, ullamcorper eu, euismod ac, fermentum vel, mauris. Integer sem elit, pharetra ut, pharetra', 'Et LLP', 609, 'nine', 224, 30, 7396.69),
('MBO758', 'Cymbalta', 'Aenean sed pede nec ante blandit viverra. Donec tempus, lorem fringilla ornare placerat, orci lacus', 'Elit A Feugiat Ltd', 443, 'five', 200, 50, 9683.72),
('MFA789', 'Potassium Chloride', 'tortor, dictum eu, placerat eget, venenatis a, magna. Lorem ipsum dolor sit amet, consectetuer adipiscing', 'Ac Limited', 797, 'four', 235, 10, 7495.97),
('MGD166', 'Cephalexin', 'consectetuer euismod est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis', 'Augue Ut Industries', 565, 'four', 214, 50, 8374.13),
('MUI907', 'Metformin HCl', 'enim. Curabitur massa. Vestibulum accumsan neque et nunc. Quisque ornare tortor at risus. Nunc ac', 'Tellus Lorem Inc.', 897, 'four', 450, 40, 9476.8),
('MXN098', 'Furosemide', 'tempor lorem, eget mollis lectus pede et risus. Quisque libero lacus, varius et, euismod et,', 'Curae; Phasellus Corporation', 927, 'two', 485, 90, 5995.55),
('MZE422', 'Clonazepam', 'mi. Duis risus odio, auctor vitae, aliquet nec, imperdiet nec, leo. Morbi neque tellus, imperdiet', 'Erat Corporation', 46, 'four', 65, 90, 5338.09),
('NNF546', 'Amphetamine Salts', 'cubilia Curae; Phasellus ornare. Fusce mollis. Duis sit amet diam eu dolor egestas rhoncus. Proin', 'Feugiat Tellus Corporation', 91, 'eight', 419, 40, 4916.73),
('NUO966', 'Lipitor', 'mattis velit justo nec ante. Maecenas mi felis, adipiscing fringilla, porttitor vulputate, posuere vulputate, lacus.', 'Egestas A Corp.', 392, 'eight', 180, 30, 8754.37),
('NZM693', 'Hydrocodone/APAP', 'ullamcorper viverra. Maecenas iaculis aliquet diam. Sed diam lorem, auctor quis, tristique ac, eleifend vitae,', 'Ridiculus Mus Foundation', 905, 'six', 496, 80, 1943.75),
('OBC738', 'Lovastatin', 'felis. Donec tempor, est ac mattis semper, dui lectus rutrum urna, nec luctus felis purus', 'Vivamus Company', 194, 'eight', 219, 40, 1783.55),
('OCZ795', 'Triamterene/Hydrochlorothiazide', 'quis, pede. Suspendisse dui. Fusce diam nunc, ullamcorper eu, euismod ac, fermentum vel, mauris. Integer', 'Felis Ullamcorper Incorporated', 209, 'eight', 396, 50, 6133.9),
('OEK831', 'Metoprolol Succinate', 'nibh. Quisque nonummy ipsum non arcu. Vivamus sit amet risus. Donec egestas. Aliquam nec enim.', 'Felis LLC', 698, 'five', 191, 70, 6109.92),
('OLX474', 'Lorazepam', 'gravida molestie arcu. Sed eu nibh vulputate mauris sagittis placerat. Cras dictum ultricies ligula. Nullam', 'Mollis Dui Associates', 656, 'seven', 44, 20, 3976.99),
('PPQ560', 'Atenolol', 'enim diam vel arcu. Curabitur ut odio vel est tempor bibendum. Donec felis orci, adipiscing', 'Non Ltd', 444, 'seven', 399, 60, 3963.05),
('PYY586', 'Omeprazole (Rx)', 'nunc, ullamcorper eu, euismod ac, fermentum vel, mauris. Integer sem elit, pharetra ut, pharetra sed,', 'Proin Sed Turpis LLP', 927, 'three', 462, 90, 4189.89),
('QJL775', 'Suboxone', 'ligula. Aliquam erat volutpat. Nulla dignissim. Maecenas ornare egestas ligula. Nullam feugiat placerat velit. Quisque', 'Magna Ut Corp.', 708, 'six', 195, 60, 5346.81),
('QQF569', 'Pantoprazole Sodium', 'adipiscing lobortis risus. In mi pede, nonummy ut, molestie in, tempus eu, ligula. Aenean euismod', 'Lectus Inc.', 125, 'seven', 211, 80, 2578.92),
('QTC220', 'Flovent HFA', 'consectetuer ipsum nunc id enim. Curabitur massa. Vestibulum accumsan neque et nunc. Quisque ornare tortor', 'Sed Eu Company', 638, 'seven', 313, 70, 4650.08),
('QTG960', 'Lorazepam', 'eros non enim commodo hendrerit. Donec porttitor tellus non magna. Nam ligula elit, pretium et,', 'Cum PC', 893, 'one', 154, 30, 1080.72),
('QZQ520', 'Alprazolam', 'et magnis dis parturient montes, nascetur ridiculus mus. Donec dignissim magna a tortor. Nunc commodo', 'Elit Pede PC', 470, 'five', 173, 90, 6697.33),
('ROF150', 'Azithromycin', 'non, hendrerit id, ante. Nunc mauris sapien, cursus in, hendrerit consectetuer, cursus et, magna. Praesent', 'Netus Et Foundation', 682, 'seven', 349, 90, 5493.84),
('SPE445', 'Zyprexa', 'nunc risus varius orci, in consequat enim diam vel arcu. Curabitur ut odio vel est', 'Auctor Velit PC', 67, 'eight', 271, 90, 9985.67),
('SWG490', 'Metformin HCl', 'aliquet. Phasellus fermentum convallis ligula. Donec luctus aliquet odio. Etiam ligula tortor, dictum eu, placerat', 'Tempor Diam Dictum PC', 811, 'two', 425, 20, 5765.44),
('TAZ604', 'Pantoprazole Sodium', 'a, auctor non, feugiat nec, diam. Duis mi enim, condimentum eget, volutpat ornare, facilisis eget,', 'Bibendum Ullamcorper Institute', 345, 'ten', 119, 70, 3513.29),
('TKT738', 'Levaquin', 'sem magna nec quam. Curabitur vel lectus. Cum sociis natoque penatibus et magnis dis parturient', 'Sed Dictum Proin Corp.', 169, 'ten', 322, 50, 9267.41),
('TUL176', 'Lantus', 'erat. Vivamus nisi. Mauris nulla. Integer urna. Vivamus molestie dapibus ligula. Aliquam erat volutpat. Nulla', 'Orci Ut Sagittis PC', 795, 'six', 337, 70, 7807.47),
('UAE352', 'Ventolin HFA', 'sed, facilisis vitae, orci. Phasellus dapibus quam quis diam. Pellentesque habitant morbi tristique senectus et', 'Risus Quis Institute', 656, 'eight', 83, 60, 5854.49),
('UBK822', 'Hydrocodone/APAP', 'amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et', 'Vulputate Dui Nec LLP', 494, 'seven', 97, 70, 5442.01),
('UCA535', 'Carvedilol', 'gravida sit amet, dapibus id, blandit at, nisi. Cum sociis natoque penatibus et magnis dis', 'Aenean Industries', 64, 'two', 434, 90, 8977.68),
('UIX015', 'Paroxetine HCl', 'viverra. Donec tempus, lorem fringilla ornare placerat, orci lacus vestibulum lorem, sit amet ultricies sem', 'Ut Pharetra Sed LLP', 830, 'nine', 117, 20, 5715.98),
('UMX932', 'Simvastatin', 'Donec vitae erat vel pede blandit congue. In scelerisque scelerisque dui. Suspendisse ac metus vitae', 'Libero Est Congue Institute', 223, 'four', 325, 50, 6384.1),
('UUE764', 'Hydrochlorothiazide', 'sed orci lobortis augue scelerisque mollis. Phasellus libero mauris, aliquam eu, accumsan sed, facilisis vitae,', 'Egestas Industries', 823, 'eight', 136, 80, 7415.46),
('UYW252', 'Amoxicillin Trihydrate/Potassium Clavulanate', 'fermentum convallis ligula. Donec luctus aliquet odio. Etiam ligula tortor, dictum eu, placerat eget, venenatis', 'Consequat Incorporated', 468, 'three', 444, 80, 4661.34),
('VEY250', 'Simvastatin', 'molestie tellus. Aenean egestas hendrerit neque. In ornare sagittis felis. Donec tempor, est ac mattis', 'Diam Eu Dolor Corporation', 791, 'seven', 79, 10, 1587.78),
('VLR524', 'Diazepam', 'fringilla purus mauris a nunc. In at pede. Cras vulputate velit eu sem. Pellentesque ut', 'Auctor Quis Corporation', 109, 'one', 48, 30, 6782.46),
('VPT609', 'Penicillin VK', 'iaculis enim, sit amet ornare lectus justo eu arcu. Morbi sit amet massa. Quisque porttitor', 'Molestie Sed Associates', 472, 'one', 482, 70, 6565.68),
('VWW933', 'Omeprazole (Rx)', 'Vivamus non lorem vitae odio sagittis semper. Nam tempor diam dictum sapien. Aenean massa. Integer', 'Fermentum Arcu Limited', 133, 'four', 222, 60, 5832.52),
('WFJ819', 'Levaquin', 'felis. Donec tempor, est ac mattis semper, dui lectus rutrum urna, nec luctus felis purus', 'Eget Ltd', 372, 'four', 187, 70, 5850.31),
('WGB126', 'Azithromycin', 'nec, malesuada ut, sem. Nulla interdum. Curabitur dictum. Phasellus in felis. Nulla tempor augue ac', 'Molestie PC', 464, 'one', 45, 50, 5448.69),
('WHP449', 'Premarin', 'Quisque nonummy ipsum non arcu. Vivamus sit amet risus. Donec egestas. Aliquam nec enim. Nunc', 'Sed Consulting', 903, 'eight', 500, 10, 7434.37),
('WIC547', 'Levothyroxine Sodium', 'adipiscing, enim mi tempor lorem, eget mollis lectus pede et risus. Quisque libero lacus, varius', 'Nascetur LLP', 635, 'eight', 284, 60, 6581.72),
('WRK431', 'Simvastatin', 'Quisque imperdiet, erat nonummy ultricies ornare, elit elit fermentum risus, at fringilla purus mauris a', 'Integer Vitae Nibh LLC', 540, 'ten', 186, 10, 7289.21),
('WZY941', 'Glipizide', 'pede et risus. Quisque libero lacus, varius et, euismod et, commodo at, libero. Morbi accumsan', 'Mi Duis Risus Institute', 107, 'nine', 441, 80, 1759.39),
('XDV060', 'Sertraline HCl', 'pede. Nunc sed orci lobortis augue scelerisque mollis. Phasellus libero mauris, aliquam eu, accumsan sed,', 'Malesuada Vel Convallis Corporation', 198, 'four', 335, 40, 4778.33),
('XFR776', 'Atenolol', 'dolor, nonummy ac, feugiat non, lobortis quis, pede. Suspendisse dui. Fusce diam nunc, ullamcorper eu,', 'Tellus Institute', 972, 'nine', 154, 20, 5957.62),
('XJK330', 'Furosemide', 'et, rutrum non, hendrerit id, ante. Nunc mauris sapien, cursus in, hendrerit consectetuer, cursus et,', 'Faucibus Orci Industries', 262, 'one', 478, 80, 9103.35),
('XKL438', 'Ciprofloxacin HCl', 'libero. Integer in magna. Phasellus dolor elit, pellentesque a, facilisis non, bibendum sed, est. Nunc', 'Est Ac Foundation', 425, 'nine', 247, 50, 3203.8),
('XTG214', 'Lisinopril', 'Aliquam nisl. Nulla eu neque pellentesque massa lobortis ultrices. Vivamus rhoncus. Donec est. Nunc ullamcorper,', 'Morbi Consulting', 206, 'four', 157, 20, 9979.02),
('XWD154', 'Gabapentin', 'Proin velit. Sed malesuada augue ut lacus. Nulla tincidunt, neque vitae semper egestas, urna justo', 'Vitae Associates', 116, 'ten', 331, 40, 2526.99),
('XYH753', 'Simvastatin', 'feugiat. Sed nec metus facilisis lorem tristique aliquet. Phasellus fermentum convallis ligula. Donec luctus aliquet', 'Sed Dictum Eleifend Associates', 591, 'seven', 44, 50, 7428.77),
('YPL754', 'Diovan', 'netus et malesuada fames ac turpis egestas. Aliquam fringilla cursus purus. Nullam scelerisque neque sed', 'Sodales Associates', 11, 'five', 453, 30, 2109.28),
('ZCC371', 'Crestor', 'torquent per conubia nostra, per inceptos hymenaeos. Mauris ut quam vel sapien imperdiet ornare. In', 'Nisl Company', 907, 'eight', 199, 20, 9783.01),
('ZKC643', 'Glyburide', 'ullamcorper, nisl arcu iaculis enim, sit amet ornare lectus justo eu arcu. Morbi sit amet', 'Justo Inc.', 672, 'two', 273, 70, 9647.12),
('ZLE655', 'Promethazine HCl', 'quam quis diam. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis', 'Urna Et Inc.', 85, 'two', 391, 20, 1234.81),
('ZSZ661', 'Furosemide', 'bibendum. Donec felis orci, adipiscing non, luctus sit amet, faucibus ut, nulla. Cras eu tellus', 'Quisque Fringilla Euismod Institute', 98, 'four', 73, 50, 1833.06),
('ZVW610', 'Venlafaxine HCl ER', 'parturient montes, nascetur ridiculus mus. Donec dignissim magna a tortor. Nunc commodo auctor velit. Aliquam', 'Tristique Ac Eleifend Company', 477, 'three', 130, 10, 5626.45);

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
