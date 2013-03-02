-- MySQL dump 10.13  Distrib 5.5.27, for Linux (i686)
--
-- Host: localhost    Database: museum_server_database
-- ------------------------------------------------------
-- Server version	5.5.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `allowedDevices`
--

USE museum_server_database;

DROP TABLE IF EXISTS `allowedDevices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `allowedDevices` (
  `macAddress` varchar(17) NOT NULL,
  PRIMARY KEY (`macAddress`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allowedDevices`
--

LOCK TABLES `allowedDevices` WRITE;
/*!40000 ALTER TABLE `allowedDevices` DISABLE KEYS */;
INSERT INTO `allowedDevices` VALUES ('ab:cd:ef:12:34:56'),('af:af:af:af:af:af'),('ed:ed:ed:ed:ed:ed'),('fa:fa:fa:fa:fa:fa');
/*!40000 ALTER TABLE `allowedDevices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(100) NOT NULL,
  `questionId` int(11) NOT NULL,
  `value` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `questionId` (`questionId`),
  CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`questionId`) REFERENCES `question` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `audio`
--

DROP TABLE IF EXISTS `audio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `audio` (
  `AudioID` int(11) NOT NULL AUTO_INCREMENT,
  `AudioName` varchar(50) NOT NULL,
  `AudioLocation` varchar(200) NOT NULL,
  PRIMARY KEY (`AudioID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `audio`
--

LOCK TABLES `audio` WRITE;
/*!40000 ALTER TABLE `audio` DISABLE KEYS */;
INSERT INTO `audio` VALUES (5,'Test','/home/lolliver/NetBeansProjects/server/MuseumServer/build/web/audio/test.mp3'),(6,'Test53','/home/lolliver/NetBeansProjects/server/MuseumServer/build/web/audio/test.mp3'),(7,'Test99','/home/lolliver/NetBeansProjects/server/MuseumServer/build/web/audio/test.mp3'),(8,'Level 53 nicey good time','/home/lolliver/NetBeansProjects/server/MuseumServer/build/web/audio/test.mp3');
/*!40000 ALTER TABLE `audio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exhibits`
--

DROP TABLE IF EXISTS `exhibits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exhibits` (
  `ExhibitID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(200) DEFAULT NULL,
  `Description` varchar(200) NOT NULL,
  `AudioLevel1ID` int(11) NOT NULL,
  `AudioLevel2ID` int(11) NOT NULL,
  `AudioLevel3ID` int(11) NOT NULL,
  `AudioLevel4ID` int(11) NOT NULL,
  PRIMARY KEY (`ExhibitID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exhibits`
--

LOCK TABLES `exhibits` WRITE;
/*!40000 ALTER TABLE `exhibits` DISABLE KEYS */;
INSERT INTO `exhibits` VALUES (1,'Test','Test!',5,6,7,6),(2,'Test','Test!',5,6,7,6),(3,'Test','Test!',5,6,7,6),(4,'Test','Test!',5,6,7,6),(5,'Test','Test!',5,6,7,6),(6,'test','55',5,5,5,5),(7,'This exhibit is an introduction to ','This exhibit is an introduction to the bippidy slip slappa whappa bip bop, happa bappa whippa whappa',5,5,5,5);
/*!40000 ALTER TABLE `exhibits` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) NOT NULL,
  `questionSetId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `questionSetId` (`questionSetId`),
  CONSTRAINT `question_ibfk_1` FOREIGN KEY (`questionSetId`) REFERENCES `questionset` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questionset`
--

DROP TABLE IF EXISTS `questionset`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questionset` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questionset`
--

LOCK TABLES `questionset` WRITE;
/*!40000 ALTER TABLE `questionset` DISABLE KEYS */;
/*!40000 ALTER TABLE `questionset` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tours`
--

DROP TABLE IF EXISTS `tours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tours` (
  `TourID` int(11) NOT NULL AUTO_INCREMENT,
  `TourName` varchar(50) NOT NULL,
  `TourDescription` varchar(200) NOT NULL,
  PRIMARY KEY (`TourID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tours`
--

LOCK TABLES `tours` WRITE;
/*!40000 ALTER TABLE `tours` DISABLE KEYS */;
/*!40000 ALTER TABLE `tours` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `toursExhibitsLink`
--

DROP TABLE IF EXISTS `toursExhibitsLink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `toursExhibitsLink` (
  `TourID` int(11) NOT NULL,
  `ExhibitID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `toursExhibitsLink`
--

LOCK TABLES `toursExhibitsLink` WRITE;
/*!40000 ALTER TABLE `toursExhibitsLink` DISABLE KEYS */;
/*!40000 ALTER TABLE `toursExhibitsLink` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userID` int(3) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) COLLATE utf8_bin NOT NULL,
  `password` varchar(32) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`userID`),
  KEY `userID` (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Bob','0cc175b9c0f1b6a831c399e269772661');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usertype`
--

DROP TABLE IF EXISTS `usertype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usertype` (
  `typeID` int(3) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(25) COLLATE utf8_bin NOT NULL,
  `accessibleFunctions` varchar(100) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`typeID`),
  KEY `typeID` (`typeID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usertype`
--

LOCK TABLES `usertype` WRITE;
/*!40000 ALTER TABLE `usertype` DISABLE KEYS */;
INSERT INTO `usertype` VALUES (1,'MAINTAINER','maintainer'),(2,'ADMINISTRATOR','admin, kiosk, maintainer, manager'),(3,'CLIENTHANDSET','handset'),(4,'KIOSK','kiosk'),(5,'MANAGER','manager');
/*!40000 ALTER TABLE `usertype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usertypelink`
--

DROP TABLE IF EXISTS `usertypelink`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usertypelink` (
  `userID` int(3) NOT NULL,
  `typeID` int(3) NOT NULL,
  KEY `userID` (`userID`),
  KEY `typeID` (`typeID`),
  CONSTRAINT `usertypelink_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `usertypelink_ibfk_2` FOREIGN KEY (`typeID`) REFERENCES `usertype` (`typeID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usertypelink`
--

LOCK TABLES `usertypelink` WRITE;
/*!40000 ALTER TABLE `usertypelink` DISABLE KEYS */;
INSERT INTO `usertypelink` VALUES (1,1),(1,2),(1,3);
/*!40000 ALTER TABLE `usertypelink` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2013-03-02 15:38:46
