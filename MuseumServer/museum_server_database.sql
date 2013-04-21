-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 16, 2013 at 01:02 PM
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `answer`
--

INSERT INTO `answer` (`id`, `text`, `questionId`, `value`) VALUES
(2, '123', 2, 12);

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
-- Table structure for table `audiolog`
--

CREATE TABLE IF NOT EXISTS `audiolog` (
  `LogID` int(11) NOT NULL AUTO_INCREMENT,
  `loginCode` int(11) NOT NULL,
  `audioID` int(11) NOT NULL,
  `logDate` varchar(20) NOT NULL,
  `logTime` varchar(20) NOT NULL,
  PRIMARY KEY (`LogID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `exhibitlog`
--

CREATE TABLE IF NOT EXISTS `exhibitlog` (
  `LogID` int(11) NOT NULL AUTO_INCREMENT,
  `action` varchar(20) NOT NULL,
  `exhibitID` int(11) NOT NULL,
  `exhibitName` varchar(200) NOT NULL,
  `maintainerUsername` varchar(50) NOT NULL,
  `logDate` varchar(20) NOT NULL,
  `logTime` varchar(20) NOT NULL,
  PRIMARY KEY (`LogID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `exhibitlog`
--

INSERT INTO `exhibitlog` (`LogID`, `action`, `exhibitID`, `exhibitName`, `maintainerUsername`, `logDate`, `logTime`) VALUES
(1, 'ADD', 8, 'Testing', 'Bob', '16/03/2013', '16:17:38'),
(2, 'REMOVE', 5, 'Test', 'null', '16/03/2013', '16:22:24'),
(3, 'MODIFY', 4, 'Test', 'null', '16/03/2013', '16:22:49');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `exhibits`
--

INSERT INTO `exhibits` (`ExhibitID`, `Name`, `Description`, `AudioLevel1ID`, `AudioLevel2ID`, `AudioLevel3ID`, `AudioLevel4ID`) VALUES
(1, 'Test', 'Test!', 5, 6, 7, 6),
(2, 'Test', 'Test!', 5, 6, 7, 6),
(3, 'Test', 'Test!', 5, 6, 7, 6),
(4, 'Test', 'Test', 5, 6, 7, 6),
(7, 'This exhibit is an introduction to ', 'This exhibit is an introduction to the bippidy slip slappa whappa bip bop, happa bappa whippa whappa', 5, 5, 5, 5);

-- --------------------------------------------------------

--
-- Table structure for table `handsetlog`
--

CREATE TABLE IF NOT EXISTS `handsetlog` (
  `LogID` int(11) NOT NULL AUTO_INCREMENT,
  `action` varchar(20) NOT NULL,
  `MAC` varchar(20) NOT NULL,
  `maintainerUsername` varchar(50) NOT NULL,
  `logDate` varchar(20) NOT NULL,
  `logTime` varchar(20) NOT NULL,
  PRIMARY KEY (`LogID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `loginlog`
--

CREATE TABLE IF NOT EXISTS `loginlog` (
  `LogID` int(11) NOT NULL AUTO_INCREMENT,
  `loginCode` int(11) NOT NULL,
  `logDate` varchar(20) NOT NULL,
  `logTime` varchar(20) NOT NULL,
  PRIMARY KEY (`LogID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `question`
--

INSERT INTO `question` (`id`, `text`, `questionSetId`) VALUES
(2, 'What is a hieroglyph?', 3),
(3, '', -1),
(4, 'What is the largest invertebrate mammal?', 3),
(5, 'How many arms does a squid have?', 3),
(6, 'What are ceratopsians known for?', 3),
(7, 'What period was the triceratops most prevalent?', 3),
(8, 'What are native american indian head dresses made of?', 3);

-- --------------------------------------------------------

--
-- Table structure for table `questionset`
--

CREATE TABLE IF NOT EXISTS `questionset` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `questionset`
--

INSERT INTO `questionset` (`id`, `title`) VALUES
(-1, ''),
(3, 'Peabody');

-- --------------------------------------------------------

--
-- Table structure for table `registerlog`
--

CREATE TABLE IF NOT EXISTS `registerlog` (
  `LogID` int(11) NOT NULL AUTO_INCREMENT,
  `usernameOfUser` varchar(50) NOT NULL,
  `levelOfUser` varchar(50) NOT NULL,
  `loginCode` int(11) NOT NULL,
  `logDate` varchar(20) NOT NULL,
  `logTime` varchar(20) NOT NULL,
  PRIMARY KEY (`LogID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

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
-- Table structure for table `routerlog`
--

CREATE TABLE IF NOT EXISTS `routerlog` (
  `LogID` int(11) NOT NULL AUTO_INCREMENT,
  `action` varchar(20) NOT NULL,
  `MAC` varchar(20) NOT NULL,
  `maintainerUsername` varchar(50) DEFAULT NULL,
  `logDate` varchar(20) NOT NULL,
  `logTime` varchar(20) NOT NULL,
  PRIMARY KEY (`LogID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tourgroup`
--

CREATE TABLE IF NOT EXISTS `tourgroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `leader` int(4) DEFAULT NULL,
  `tourId` int(11) NOT NULL,
  `startDate` datetime NOT NULL,
  `expiryDate` datetime NOT NULL,
  `active` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `tourId` (`tourId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `tourlog`
--

CREATE TABLE IF NOT EXISTS `tourlog` (
  `LogID` int(11) NOT NULL AUTO_INCREMENT,
  `action` varchar(20) NOT NULL,
  `tourID` int(11) NOT NULL,
  `tourName` varchar(200) NOT NULL,
  `maintainerUsername` varchar(50) NOT NULL,
  `logDate` varchar(20) NOT NULL,
  `logTime` varchar(20) NOT NULL,
  PRIMARY KEY (`LogID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `tourlog`
--

INSERT INTO `tourlog` (`LogID`, `action`, `tourID`, `tourName`, `maintainerUsername`, `logDate`, `logTime`) VALUES
(1, 'ADD', 23, 'Testin', 'Bob', '16/03/2013', '15:58:19'),
(2, 'REMOVE', 7, 'Tooster', 'Bob', '16/03/2013', '16:10:40'),
(3, 'MODIFY', 20, 'Testin', 'Bob', '16/03/2013', '16:16:04');

-- --------------------------------------------------------

--
-- Table structure for table `tours`
--

CREATE TABLE IF NOT EXISTS `tours` (
  `TourID` int(11) NOT NULL AUTO_INCREMENT,
  `TourName` varchar(50) NOT NULL,
  `TourDescription` varchar(200) NOT NULL,
  `QuestionSetID` int(11) NOT NULL,
  PRIMARY KEY (`TourID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=24 ;

--
-- Dumping data for table `tours`
--

INSERT INTO `tours` (`TourID`, `TourName`, `TourDescription`, `QuestionSetID`) VALUES
(14, 'Testin', 'test', 3),
(18, 'Testin', 'test', -1),
(19, 'Testin', 'test', -1),
(20, 'Testin', 'test', 3),
(21, 'Testin', 'test', 3),
(22, 'Testin', 'test', 3),
(23, 'Testin', 'test', 3);

-- --------------------------------------------------------

--
-- Table structure for table `toursexhibitslink`
--

CREATE TABLE IF NOT EXISTS `toursexhibitslink` (
  `TourID` int(11) NOT NULL,
  `ExhibitID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `toursexhibitslink`
--

INSERT INTO `toursexhibitslink` (`TourID`, `ExhibitID`) VALUES
(14, 1),
(14, 2),
(14, 3),
(18, 1),
(18, 2),
(18, 3),
(19, 1),
(19, 2),
(19, 3),
(21, 1),
(21, 2),
(21, 3),
(22, 1),
(22, 2),
(22, 3),
(23, 1),
(23, 2),
(23, 3),
(20, 1),
(20, 2),
(20, 3);

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=4 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userID`, `userName`, `password`) VALUES
(1, 'Bob', '0cc175b9c0f1b6a831c399e269772661'),
(3, 'Test', '098f6bcd4621d373cade4e832627b4f6');

-- --------------------------------------------------------

--
-- Table structure for table `userlog`
--

CREATE TABLE IF NOT EXISTS `userlog` (
  `LogID` int(11) NOT NULL AUTO_INCREMENT,
  `action` varchar(20) NOT NULL,
  `usernameOfUser` varchar(50) NOT NULL,
  `maintainerUsername` varchar(50) NOT NULL,
  `logDate` varchar(20) NOT NULL,
  `logTime` varchar(20) NOT NULL,
  PRIMARY KEY (`LogID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `userlog`
--

INSERT INTO `userlog` (`LogID`, `action`, `usernameOfUser`, `maintainerUsername`, `logDate`, `logTime`) VALUES
(1, 'ADD', 'Test', 'null', '16/03/2013', '16:31:40'),
(2, 'REMOVE', 'Test', 'null', '16/03/2013', '16:32:26'),
(3, 'ADD', 'Test', 'null', '16/03/2013', '16:32:36'),
(4, 'MODIFY', 'Test', 'null', '16/03/2013', '16:32:58');

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
(1, 3),
(3, 1),
(3, 2),
(3, 3);

-- --------------------------------------------------------

--
-- Table structure for table `visitor`
--

CREATE TABLE IF NOT EXISTS `visitor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `forename` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  `title` varchar(5) NOT NULL,
  `age` tinyint(4) NOT NULL,
  `level` varchar(50) NOT NULL,
  `pin` int(4) NOT NULL,
  `tourGroup` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `tourGroup` (`tourGroup`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `wifilog`
--

CREATE TABLE IF NOT EXISTS `wifilog` (
  `LogID` int(11) NOT NULL AUTO_INCREMENT,
  `loginCode` int(11) NOT NULL,
  `MAC` varchar(20) NOT NULL,
  `logDate` varchar(20) NOT NULL,
  `logTime` varchar(20) NOT NULL,
  PRIMARY KEY (`LogID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

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
-- Constraints for table `tourgroup`
--
ALTER TABLE `tourgroup`
  ADD CONSTRAINT `tourGroup_ibfk_1` FOREIGN KEY (`tourId`) REFERENCES `tours` (`TourID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `usertypelink`
--
ALTER TABLE `usertypelink`
  ADD CONSTRAINT `usertypelink_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `usertypelink_ibfk_2` FOREIGN KEY (`typeID`) REFERENCES `usertype` (`typeID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `visitor`
--
ALTER TABLE `visitor`
  ADD CONSTRAINT `visitor_ibfk_1` FOREIGN KEY (`tourGroup`) REFERENCES `tourgroup` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
