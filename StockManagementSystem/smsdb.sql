-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Oct 26, 2018 at 04:10 AM
-- Server version: 5.7.21
-- PHP Version: 7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `smsdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `bike`
--

DROP TABLE IF EXISTS `bike`;
CREATE TABLE IF NOT EXISTS `bike` (
  `B_ID` int(11) NOT NULL AUTO_INCREMENT,
  `B_bikeMake` varchar(10) DEFAULT NULL,
  `B_bikeModel` varchar(50) DEFAULT NULL,
  `B_bikeValue` float DEFAULT NULL,
  `B_bikeColor` varchar(20) DEFAULT NULL,
  `B_bikeChassicNumber` varchar(30) DEFAULT NULL,
  `B_bikeEngineNumber` varchar(30) DEFAULT NULL,
  `B_customerID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`B_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bike`
--

INSERT INTO `bike` (`B_ID`, `B_bikeMake`, `B_bikeModel`, `B_bikeValue`, `B_bikeColor`, `B_bikeChassicNumber`, `B_bikeEngineNumber`, `B_customerID`) VALUES
(1, 'Hero Honda', 'HUNK DOUBLE DISC', 350000, 'Red', 'MBLKC13ERGGF00241', 'KC13EFGGF00193', NULL),
(2, 'Hero Honda', 'DASH VX', 250000, 'Black', 'MBLJF16EUGGE00643', 'JF16EFGGE01296', NULL),
(3, 'Hero Honda', 'DASH VX', 220000, 'Metalic Blue', 'MBLJF16EUFGM00548', 'JF16EFFGM00538', NULL),
(4, 'Hero Honda', 'PLEASURE METAL SHEET WHEEL', 350000, 'White', 'MBLJF16EHFGH01508', 'JF16ECFGH01549', '971248563v'),
(5, 'Hero Honda', 'DASH VX', 250000, 'Blue', 'MBLJF16EUFGL00226', 'JF16EFFGL00219', NULL),
(6, 'Hero Honda', 'PLEASURE METAL SHEET WHEEL', 220000, 'Red', 'MBLJF16EMFGL00553', 'JF16EEFGL00332', '961234578v'),
(7, 'Hero Honda', 'PLEASURE METAL SHEET WHEEL', 350000, 'Black', 'MBLJF16EMFGG12371', 'JF16EEFGG14739', '745896321v'),
(8, 'Hero Honda', 'HUNK DOUBLE DISC', 250000, 'Metalic Blue', 'MBLKC13ERGGG00107', 'KC13EFGGG00072', NULL),
(9, 'Hero Honda', 'DASH LX', 220000, 'White', 'MBLJF16ERGGE00230', 'JF16EFGGE00234', NULL),
(10, 'Hero Honda', 'DASH VX', 350000, 'Blue', 'MBLJF16EUGGA01132', 'JF16EFFGM02706', NULL),
(11, 'Hero Honda', 'DASH VX', 250000, 'Red', 'MBLJF16EUGGE00421', 'JF16EFGGE00974', NULL),
(12, 'Hero Honda', 'DASH VX', 220000, 'Black', 'MBLJF16EUGGB01538', 'JF16EFGGB01935', NULL),
(13, 'Hero Honda', 'DASH VX', 350000, 'Metalic Blue', 'MBLJF16EUGGC00147', 'JF16EFGGC00086', NULL),
(14, 'Hero Honda', 'DASH VX', 250000, 'White', 'MBLJF16EUGGE00609', 'JF16EFGGE01269', NULL),
(15, 'Hero Honda', 'DASH VX', 220000, 'Blue', 'MBLJF16EUGGB02806', 'JF16EFGGB02556', NULL),
(16, 'Hero Honda', 'DASH VX', 350000, 'Red', 'MBLJF16EUFGL00437', 'JF16EFFGL00281', NULL),
(17, 'Hero Honda', 'PLEASURE METAL SHEET WHEEL', 250000, 'Black', 'MBLJF16EMFGL00553', 'JF16EEFGL00332', '961234578v'),
(18, 'Hero Honda', 'PLEASURE METAL SHEET WHEEL', 220000, 'Metalic Blue', 'MBLJF16EMFGJ02805', 'JF16EEFGJ15638', '3685741296v'),
(19, 'Hero Honda', 'DASH VX', 350000, 'White', 'MBLJF16EUFGM00356', 'JF16EFFGM00214', NULL),
(20, 'Hero Honda', 'PLEASURE METAL SHEET WHEEL', 250000, 'Blue', 'MBLJF16EMFGM12142', 'JF16EEFGM28468', '123524786v'),
(21, 'Hero Honda', 'HF DELUXE CAST KICK', 220000, 'Red', 'MBLHA11ARF9F00880', 'HA11EHF9F01203', NULL),
(22, 'Hero Honda', 'DASH VX', 350000, 'Black', 'MBLJF16EUFGL01194', 'JF16EFFGL01116', NULL),
(23, 'Hero Honda', 'DASH VX', 250000, 'Metalic Blue', 'MBLJF16EUFGL00458', 'JF16EFFGL00019', NULL),
(24, 'Hero Honda', 'DASH VX', 220000, 'White', 'MBLJF16EUGGA03720', 'JF16EFGGA05089', NULL),
(25, 'Hero Honda', 'PLEASURE METAL SHEET WHEEL', 350000, 'Blue', 'MBLJF16EMFGJ01874', 'JF16EEFGJ01045', '961234578v'),
(26, 'Hero Honda', 'DASH VX', 250000, 'Red', 'MBLJF16EUFGM00756', 'JF16EFFGM01013', ''),
(27, 'Hero Honda', 'DASH VX', 220000, 'Black', 'MBLJF16EUGGA04785', 'JF16EFGGA06366', ''),
(28, 'Hero Honda', 'DASH VX', 350000, 'Metalic Blue', 'MBLJF16EUFGL01773', 'JF16EFFGL01938', ''),
(29, 'Hero Honda', 'PLEASURE LX', 250000, 'White', 'MBLJF16EMFGM08550', 'JF16EEFGM07296', ''),
(30, 'Hero Honda', 'DASH VX', 220000, 'Blue', 'MBLJF16EUGGA00619', 'JF16EFGGA01161', ''),
(31, 'Hero Honda', 'DASH VX', 350000, 'Red', 'MBLJF16EUGGA02553', 'JF16EFGGA03857', ''),
(32, 'Hero Honda', 'DASH VX', 250000, 'Black', 'MBLJF16EUGGA00924', 'JF16EFGGA01545', ''),
(33, 'Hero Honda', 'DASH VX', 220000, 'Metalic Blue', 'MBLJF16EUGGA04774', 'JF16EFGGA06400', ''),
(34, 'Hero Honda', 'DASH VX', 350000, 'White', 'MBLJF16EUGGB01068', 'JF16EFGGB01370', ''),
(35, 'Hero Honda', 'DASH LX', 250000, 'Blue', 'MBLJF16ERFGJ00142', 'JF16EFFGJ00164', ''),
(36, 'Hero Honda', 'XTREME SPORTS', 220000, 'Red', 'MBLKC12ELFGC00179', 'KC12EFFGC00295', ''),
(37, 'Hero Honda', 'DASH VX', 350000, 'Black', 'MBLJF16EUGGB01022', 'JF16EFGGB01312', ''),
(38, 'Hero Honda', 'DASH VX', 250000, 'Metalic Blue', 'MBLJF16EUGGB03102', 'JF16EFGGB03294', ''),
(39, 'Hero Honda', 'DASH LX', 220000, 'White', 'MBLJF16ERFGK00610', 'JF16EFFGK00298', '');

-- --------------------------------------------------------

--
-- Table structure for table `cover_note`
--

DROP TABLE IF EXISTS `cover_note`;
CREATE TABLE IF NOT EXISTS `cover_note` (
  `CN_number` int(11) NOT NULL AUTO_INCREMENT,
  `CN_issuedDate` date DEFAULT NULL,
  `CN_companyName` varchar(50) DEFAULT NULL,
  `CN_schemeAmount` int(11) NOT NULL,
  `CN_accountNumber` int(11) DEFAULT NULL,
  PRIMARY KEY (`CN_number`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cover_note`
--

INSERT INTO `cover_note` (`CN_number`, `CN_issuedDate`, `CN_companyName`, `CN_schemeAmount`, `CN_accountNumber`) VALUES
(1, '2018-08-15', 'Continental Insurance', 1000, 1),
(2, '2018-10-15', 'Janashakthi Insurance', 4000, 2);

-- --------------------------------------------------------

--
-- Table structure for table `customer_account`
--

DROP TABLE IF EXISTS `customer_account`;
CREATE TABLE IF NOT EXISTS `customer_account` (
  `Account_number` int(11) NOT NULL AUTO_INCREMENT,
  `Account_cusNIC` varchar(15) NOT NULL,
  `Account_bikeValue` float DEFAULT NULL,
  `Account_downPayment` float DEFAULT NULL,
  `Account_balance` float DEFAULT NULL,
  `Account_createDate` date DEFAULT NULL,
  `Account_status` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Account_number`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer_account`
--

INSERT INTO `customer_account` (`Account_number`, `Account_cusNIC`, `Account_bikeValue`, `Account_downPayment`, `Account_balance`, `Account_createDate`, `Account_status`) VALUES
(1, '961234578v', 250000, 70000, 180000, '2018-08-15', 1),
(2, '971248563v', 350000, 70000, 280000, '2018-10-15', 1);

-- --------------------------------------------------------

--
-- Table structure for table `executive`
--

DROP TABLE IF EXISTS `executive`;
CREATE TABLE IF NOT EXISTS `executive` (
  `Exe_epfNo` int(11) NOT NULL,
  `Exe_fName` varchar(20) DEFAULT NULL,
  `Exe_lName` varchar(20) DEFAULT NULL,
  `Exe_email` varchar(30) DEFAULT NULL,
  `Exe_password` varchar(50) NOT NULL,
  `Exe_access` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`Exe_epfNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `executive`
--

INSERT INTO `executive` (`Exe_epfNo`, `Exe_fName`, `Exe_lName`, `Exe_email`, `Exe_password`, `Exe_access`) VALUES
(15, 'Dulangi', 'Milanthika', 'dulangi@gmail.com', '457237812', 1),
(16, 'Keshani', 'Bhagya', 'keshani@gmail.com', '457237812', 1),
(55, 'Gayani', 'Udeshika', 'gayani@gmail.com', '457237812', 1);

-- --------------------------------------------------------

--
-- Table structure for table `full_payment_customer`
--

DROP TABLE IF EXISTS `full_payment_customer`;
CREATE TABLE IF NOT EXISTS `full_payment_customer` (
  `FPC_NIC` varchar(20) NOT NULL,
  `FPC_fName` varchar(20) DEFAULT NULL,
  `FPC_lName` varchar(20) DEFAULT NULL,
  `FPC_address` varchar(50) DEFAULT NULL,
  `FPC_city` varchar(20) NOT NULL,
  `FPC_telNumber` int(11) DEFAULT NULL,
  `FPC_date` date DEFAULT NULL,
  PRIMARY KEY (`FPC_NIC`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `full_payment_customer`
--

INSERT INTO `full_payment_customer` (`FPC_NIC`, `FPC_fName`, `FPC_lName`, `FPC_address`, `FPC_city`, `FPC_telNumber`, `FPC_date`) VALUES
('123524786v', 'Ananda', 'Bawan', 'Ragama', 'Ja-Ela', 712356874, '2018-10-15'),
('3685741296v', 'Rosi', 'Senanayake', 'Raddoluwa', 'Minuwangoda', 145278963, '2018-10-15'),
('745896321v', 'Chandika', 'Hathurusignhe', 'Negombo', 'Ja-Ela', 741258963, '2018-10-15'),
('961234578v', 'Ashen', 'Sheranga', 'Kotugoda', 'Gampaha', 777122560, '2018-10-15');

-- --------------------------------------------------------

--
-- Table structure for table `hire_purchesed_customer`
--

DROP TABLE IF EXISTS `hire_purchesed_customer`;
CREATE TABLE IF NOT EXISTS `hire_purchesed_customer` (
  `HC_CID` int(11) NOT NULL AUTO_INCREMENT,
  `HC_fName` varchar(20) DEFAULT NULL,
  `HC_lName` varchar(20) DEFAULT NULL,
  `HC_address` varchar(50) DEFAULT NULL,
  `HC_city` varchar(30) NOT NULL,
  `HC_nic` varchar(20) DEFAULT NULL,
  `HC_telNo` int(11) DEFAULT NULL,
  `HC_accountNumber` int(11) DEFAULT NULL,
  `GName1` varchar(30) NOT NULL,
  `GAddress1` varchar(50) NOT NULL,
  `GNIC1` varchar(20) NOT NULL,
  `GName2` varchar(30) NOT NULL,
  `GAddress2` varchar(50) NOT NULL,
  `GNIC2` varchar(20) NOT NULL,
  `GName3` varchar(30) NOT NULL,
  `GAddress3` varchar(50) NOT NULL,
  `GNIC3` varchar(20) NOT NULL,
  PRIMARY KEY (`HC_CID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `hire_purchesed_customer`
--

INSERT INTO `hire_purchesed_customer` (`HC_CID`, `HC_fName`, `HC_lName`, `HC_address`, `HC_city`, `HC_nic`, `HC_telNo`, `HC_accountNumber`, `GName1`, `GAddress1`, `GNIC1`, `GName2`, `GAddress2`, `GNIC2`, `GName3`, `GAddress3`, `GNIC3`) VALUES
(1, 'Nisansala', 'Perera', 'Maradana', 'Gampaha', '961234578v', 71852963, 1, 'abc1', 'ddd', 'ddd', 'abc2', 'fff', 'fff', 'abc3', 'ddd', 'ddd'),
(2, 'Ashaka', 'Induja', 'Nittamnuwa', 'Diulapitiya', '971248563v', 771485236, 2, 'Dewsri', 'Kotogoda', '5214785211', 'Madhusha', 'Gampaha', '62584503', 'Sahan', 'Wennapuwa', '4851230');

-- --------------------------------------------------------

--
-- Table structure for table `insurance_company`
--

DROP TABLE IF EXISTS `insurance_company`;
CREATE TABLE IF NOT EXISTS `insurance_company` (
  `IC_code` int(11) NOT NULL AUTO_INCREMENT,
  `IC_companyName` varchar(40) DEFAULT NULL,
  `IC_bikeValue` float DEFAULT NULL,
  `IC_schemeM3` float DEFAULT NULL,
  `IC_schemeM6` float DEFAULT NULL,
  `IC_schemeM9` float DEFAULT NULL,
  `IC_schemeM12` float DEFAULT NULL,
  `IC_schemeM24` float DEFAULT NULL,
  `IC_schemeM36` float DEFAULT NULL,
  PRIMARY KEY (`IC_code`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `insurance_company`
--

INSERT INTO `insurance_company` (`IC_code`, `IC_companyName`, `IC_bikeValue`, `IC_schemeM3`, `IC_schemeM6`, `IC_schemeM9`, `IC_schemeM12`, `IC_schemeM24`, `IC_schemeM36`) VALUES
(1, 'Janashakthi Insurance', 220000, 6000, 5000, 4000, 3000, 2000, 1000),
(2, 'Janashakthi Insurance', 250000, 6000, 5000, 4000, 3000, 2000, 1000),
(3, 'Janashakthi Insurance', 350000, 6000, 5000, 4000, 3000, 2000, 1000),
(4, 'Continental Insurance', 220000, 6000, 5000, 4000, 3000, 2000, 1000),
(5, 'Continental Insurance', 250000, 6000, 5000, 4000, 3000, 2000, 1000),
(6, 'Continental Insurance', 350000, 6000, 5000, 4000, 3000, 2000, 1000);

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
CREATE TABLE IF NOT EXISTS `invoice` (
  `Invoice_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Invoice_Number` varchar(20) NOT NULL,
  `Invoice_issuedDate` date DEFAULT NULL,
  `Invoice_Amount` float DEFAULT NULL,
  `Invoice_accountNumber` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Invoice_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`Invoice_ID`, `Invoice_Number`, `Invoice_issuedDate`, `Invoice_Amount`, `Invoice_accountNumber`) VALUES
(1, 'Inv0', '2018-10-15', 350000, '961234578v'),
(2, 'Inv2', '2018-10-15', 250000, '123524786v'),
(3, 'Inv3', '2018-10-15', 220000, '3685741296v'),
(4, 'Inv4', '2018-10-15', 350000, '745896321v'),
(5, 'Inv5', '2018-08-15', 70000, '1'),
(6, 'Inv6', '2018-10-15', 70000, '2');

-- --------------------------------------------------------

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
CREATE TABLE IF NOT EXISTS `manager` (
  `Manager_epf` int(11) NOT NULL,
  `Manager_fName` varchar(20) DEFAULT NULL,
  `Manager_lName` varchar(20) DEFAULT NULL,
  `Manager_password` varchar(50) NOT NULL,
  `Manager_email` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`Manager_epf`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `manager`
--

INSERT INTO `manager` (`Manager_epf`, `Manager_fName`, `Manager_lName`, `Manager_password`, `Manager_email`) VALUES
(2, 'Madhusha', 'Sesath', '457237812', 'm.s.rockers2@gmail.com'),
(29, 'Dewsri', 'De Mel', '-1424436592', 'dewzdemel4477@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `monthly_installment`
--

DROP TABLE IF EXISTS `monthly_installment`;
CREATE TABLE IF NOT EXISTS `monthly_installment` (
  `MI_ID` int(11) NOT NULL AUTO_INCREMENT,
  `MI_amount` float DEFAULT NULL,
  `MI_paymentDate` date DEFAULT NULL,
  `MI_paymentDueDate` date DEFAULT NULL,
  `MI_nextPaymentDate` date DEFAULT NULL,
  `MI_accountNumber` int(11) DEFAULT NULL,
  `MI_invoiceNumber` varchar(20) NOT NULL,
  PRIMARY KEY (`MI_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `monthly_installment`
--

INSERT INTO `monthly_installment` (`MI_ID`, `MI_amount`, `MI_paymentDate`, `MI_paymentDueDate`, `MI_nextPaymentDate`, `MI_accountNumber`, `MI_invoiceNumber`) VALUES
(1, 70000, '2018-08-15', NULL, '2018-09-15', 1, 'Inv5'),
(2, 70000, '2018-10-15', NULL, '2018-11-15', 2, 'Inv6');

-- --------------------------------------------------------

--
-- Table structure for table `order_stock`
--

DROP TABLE IF EXISTS `order_stock`;
CREATE TABLE IF NOT EXISTS `order_stock` (
  `orderID` int(11) NOT NULL AUTO_INCREMENT,
  `orderDate` date NOT NULL,
  `orderBrand` varchar(20) NOT NULL,
  `orderModel` varchar(30) NOT NULL,
  `orderColor` varchar(20) NOT NULL,
  `orderQty` int(11) NOT NULL,
  `orderStatus` varchar(10) NOT NULL,
  PRIMARY KEY (`orderID`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `order_stock`
--

INSERT INTO `order_stock` (`orderID`, `orderDate`, `orderBrand`, `orderModel`, `orderColor`, `orderQty`, `orderStatus`) VALUES
(1, '2018-10-15', 'Hero Honda', 'DASH LX', 'White', 3, 'Ordered'),
(2, '2018-10-15', 'Hero Honda', 'HF DELUXE CAST KICK', 'Red', 5, 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `showroom_stock`
--

DROP TABLE IF EXISTS `showroom_stock`;
CREATE TABLE IF NOT EXISTS `showroom_stock` (
  `SS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `SS_bikeMake` varchar(10) DEFAULT NULL,
  `SS_bikeModel` varchar(50) DEFAULT NULL,
  `SS_bikeColor` varchar(30) NOT NULL,
  `SS_stockQuantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`SS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `showroom_stock`
--

INSERT INTO `showroom_stock` (`SS_ID`, `SS_bikeMake`, `SS_bikeModel`, `SS_bikeColor`, `SS_stockQuantity`) VALUES
(1, 'Hero Honda', 'DASH LX', 'White', 2),
(2, 'Hero Honda', 'DASH LX', 'Blue', 1),
(3, 'Hero Honda', 'DASH VX', 'Black', 6),
(4, 'Hero Honda', 'DASH VX', 'Blue', 4),
(5, 'Hero Honda', 'DASH VX', 'Metalic Blue', 6),
(6, 'Hero Honda', 'DASH VX', 'Red', 4),
(7, 'Hero Honda', 'DASH VX', 'White', 4),
(8, 'Hero Honda', 'HF DELUXE CAST KICK', 'Red', 1),
(9, 'Hero Honda', 'HUNK DOUBLE DISC', 'Red', 1),
(10, 'Hero Honda', 'HUNK DOUBLE DISC', 'Metalic Blue', 1),
(11, 'Hero Honda', 'PLEASURE LX', 'White', 1),
(12, 'Hero Honda', 'PLEASURE METAL SHEET WHEEL', 'White', 0),
(13, 'Hero Honda', 'PLEASURE METAL SHEET WHEEL', 'Red', 1),
(14, 'Hero Honda', 'PLEASURE METAL SHEET WHEEL', 'Black', 0),
(15, 'Hero Honda', 'PLEASURE METAL SHEET WHEEL', 'Metalic Blue', 0),
(16, 'Hero Honda', 'PLEASURE METAL SHEET WHEEL', 'Blue', 0);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
