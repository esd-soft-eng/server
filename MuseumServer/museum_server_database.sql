-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Mar 14, 2013 at 05:31 PM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

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
-- Table structure for table `alloweddevices`
--

CREATE TABLE IF NOT EXISTS `alloweddevices` (
  `macAddress` varchar(17) NOT NULL,
  PRIMARY KEY (`macAddress`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `alloweddevices`
--

INSERT INTO `alloweddevices` (`macAddress`) VALUES
('ab:cd:ef:12:34:56'),
('af:af:af:af:af:af'),
('ed:ed:ed:ed:ed:ed'),
('fa:fa:fa:fa:fa:fa');

-- --------------------------------------------------------

--
-- Table structure for table `answer`
--

CREATE TABLE IF NOT EXISTS `answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(100) NOT NULL,
  `questionId` int(11) NOT NULL,
  `value` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `questionId` (`questionId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Table structure for table `audio`
--

CREATE TABLE IF NOT EXISTS `audio` (
  `AudioID` int(11) NOT NULL AUTO_INCREMENT,
  `AudioName` varchar(50) NOT NULL,
  `AudioLocation` varchar(200) NOT NULL,
  PRIMARY KEY (`AudioID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `audio`
--

INSERT INTO `audio` (`AudioID`, `AudioName`, `AudioLocation`) VALUES
(5, 'Test', '/home/lolliver/NetBeansProjects/server/MuseumServer/build/web/audio/test.mp3'),
(6, 'Test53', '/home/lolliver/NetBeansProjects/server/MuseumServer/build/web/audio/test.mp3'),
(7, 'Test99', '/home/lolliver/NetBeansProjects/server/MuseumServer/build/web/audio/test.mp3');

-- --------------------------------------------------------

--
-- Table structure for table `exhibits`
--

CREATE TABLE IF NOT EXISTS `exhibits` (
  `ExhibitID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(200) DEFAULT NULL,
  `Description` varchar(200) NOT NULL,
  `AudioLevel1ID` int(11) NOT NULL,
  `AudioLevel2ID` int(11) NOT NULL,
  `AudioLevel3ID` int(11) NOT NULL,
  `AudioLevel4ID` int(11) NOT NULL,
  PRIMARY KEY (`ExhibitID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `exhibits`
--

INSERT INTO `exhibits` (`ExhibitID`, `Name`, `Description`, `AudioLevel1ID`, `AudioLevel2ID`, `AudioLevel3ID`, `AudioLevel4ID`) VALUES
(1, 'Test', 'Test!', 5, 6, 7, 6),
(2, 'Test', 'Test!', 5, 6, 7, 6),
(3, 'Test', 'Test!', 5, 6, 7, 6),
(4, 'Test', 'Test!', 5, 6, 7, 6),
(5, 'Test', 'Test!', 5, 6, 7, 6),
(6, 'test', '55', 5, 5, 5, 5),
(7, 'This exhibit is an introduction to ', 'This exhibit is an introduction to the bippidy slip slappa whappa bip bop, happa bappa whippa whappa', 5, 5, 5, 5);

-- --------------------------------------------------------

--
-- Table structure for table `question`
--

CREATE TABLE IF NOT EXISTS `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) NOT NULL,
  `questionSetId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `questionSetId` (`questionSetId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Table structure for table `questionset`
--

CREATE TABLE IF NOT EXISTS `questionset` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

-- --------------------------------------------------------

--
-- Table structure for table `router`
--

CREATE TABLE IF NOT EXISTS `router` (
  `MacID` varchar(12) NOT NULL,
  `AudioLocation` varchar(70) NOT NULL,
  `Description` varchar(150) NOT NULL,
  PRIMARY KEY (`MacID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `router`
--

INSERT INTO `router` (`MacID`, `AudioLocation`, `Description`) VALUES
('001122334455', 'test', 'tester'),
('223344556677', 'quickquick', 'badgerbadger');

-- --------------------------------------------------------

--
-- Table structure for table `tours`
--

CREATE TABLE IF NOT EXISTS `tours` (
  `TourID` int(11) NOT NULL AUTO_INCREMENT,
  `TourName` varchar(50) NOT NULL,
  `TourDescription` varchar(200) NOT NULL,
  `QuestionSetID` int(11) NOT NULL,
  PRIMARY KEY (`TourID`),
  UNIQUE KEY `QuestionSetID` (`QuestionSetID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

-- --------------------------------------------------------

--
-- Table structure for table `toursexhibitslink`
--

CREATE TABLE IF NOT EXISTS `toursexhibitslink` (
  `TourID` int(11) NOT NULL,
  `ExhibitID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

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
  `accessibleFunctions` varchar(100) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`typeID`),
  KEY `typeID` (`typeID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=6 ;

--
-- Dumping data for table `usertype`
--

INSERT INTO `usertype` (`typeID`, `typeName`, `accessibleFunctions`) VALUES
(1, 'MAINTAINER', 'maintainer'),
(2, 'ADMINISTRATOR', 'admin, kiosk, maintainer, manager'),
(3, 'CLIENTHANDSET', 'handset'),
(4, 'KIOSK', 'kiosk'),
(5, 'MANAGER', 'manager');

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
-- Constraints for table `answer`
--
ALTER TABLE `answer`
  ADD CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`questionId`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `question`
--
ALTER TABLE `question`
  ADD CONSTRAINT `question_ibfk_1` FOREIGN KEY (`questionSetId`) REFERENCES `questionset` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tours`
--
ALTER TABLE `tours`
  ADD CONSTRAINT `tours_ibfk_1` FOREIGN KEY (`QuestionSetID`) REFERENCES `question` (`questionSetId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `usertypelink`
--
ALTER TABLE `usertypelink`
  ADD CONSTRAINT `usertypelink_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `usertypelink_ibfk_2` FOREIGN KEY (`typeID`) REFERENCES `usertype` (`typeID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
