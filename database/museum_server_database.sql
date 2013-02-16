-- phpMyAdmin SQL Dump
-- version 3.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 16, 2013 at 02:10 PM
-- Server version: 5.5.25a
-- PHP Version: 5.4.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `museum_server_database`
--

-- --------------------------------------------------------

--
-- Table structure for table `user`
--
USE `museum_server_database`

CREATE TABLE IF NOT EXISTS `user` (
  `userID` int(3) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) COLLATE utf8_bin NOT NULL,
  `password` varchar(32) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`userID`),
  KEY `userID` (`userID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=2 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userID`, `userName`, `password`) VALUES
(1, 'Bob', '0cc175b9c0f1b6a831c399e269772661');

-- --------------------------------------------------------

--
-- Table structure for table `usertype`
--

CREATE TABLE IF NOT EXISTS `usertype` (
  `typeID` int(3) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(25) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`typeID`),
  KEY `typeID` (`typeID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=6 ;

--
-- Dumping data for table `usertype`
--

INSERT INTO `usertype` (`typeID`, `typeName`) VALUES
(1, 'MAINTAINER'),
(2, 'ADMINISTRATOR'),
(3, 'CLIENTHANDSET'),
(4, 'KIOSK'),
(5, 'MANAGER');

-- --------------------------------------------------------

--
-- Table structure for table `usertypelink`
--

CREATE TABLE IF NOT EXISTS `usertypelink` (
  `userID` int(3) NOT NULL,
  `typeID` int(3) NOT NULL,
  KEY `userID` (`userID`),
  KEY `typeID` (`typeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `usertypelink`
--

INSERT INTO `usertypelink` (`userID`, `typeID`) VALUES
(1, 1),
(1, 2),
(1, 3);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `usertypelink`
--
ALTER TABLE `usertypelink`
  ADD CONSTRAINT `usertypelink_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `usertypelink_ibfk_2` FOREIGN KEY (`typeID`) REFERENCES `usertype` (`typeID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
