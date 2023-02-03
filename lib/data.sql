CREATE DATABASE  IF NOT EXISTS `users_db` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `users_db`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: users_db
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `adresse`
--

DROP TABLE IF EXISTS `adresse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adresse` (
  `id` int NOT NULL AUTO_INCREMENT,
  `numero` varchar(20) DEFAULT NULL,
  `rue` varchar(45) DEFAULT NULL,
  `ville` varchar(45) DEFAULT NULL,
  `code_postal` varchar(45) DEFAULT NULL,
  `employee_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_adresse_user_idx` (`employee_id`),
  CONSTRAINT `fk_adresse_user` FOREIGN KEY (`employee_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adresse`
--

LOCK TABLES `adresse` WRITE;
/*!40000 ALTER TABLE `adresse` DISABLE KEYS */;
INSERT INTO `adresse` VALUES (6,'14','paris','Paris','78955',12),(7,'88','lyon','Lyon','70000',12);
/*!40000 ALTER TABLE `adresse` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salaire`
--

DROP TABLE IF EXISTS `salaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salaire` (
  `idSalaire` int NOT NULL AUTO_INCREMENT,
  `ANNEE` int NOT NULL,
  `JANVIER` float DEFAULT '0',
  `FEVRIER` float DEFAULT '0',
  `MARS` float DEFAULT '0',
  `AVRIL` float DEFAULT '0',
  `MAI` float DEFAULT '0',
  `JUIN` float DEFAULT '0',
  `JUILLET` float DEFAULT '0',
  `AOUT` float DEFAULT '0',
  `SEPTEMBRE` float DEFAULT '0',
  `OCTOBRE` float DEFAULT '0',
  `NOVEMBRE` float DEFAULT '0',
  `DECEMBRE` float DEFAULT '0',
  `user_id` int NOT NULL,
  PRIMARY KEY (`idSalaire`),
  UNIQUE KEY `UQ_salaire_annee_user_id` (`ANNEE`,`user_id`),
  KEY `fk_Salaire_user1_idx` (`user_id`),
  CONSTRAINT `fk_Salaire_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salaire`
--

LOCK TABLES `salaire` WRITE;
/*!40000 ALTER TABLE `salaire` DISABLE KEYS */;
INSERT INTO `salaire` VALUES (2,2022,1234,1234,1234,1234,1234,1234,1234,1234,1234,1234,1234,1234,12),(3,2023,1234,1234,1234,1234,1234,1234,1234,1234,1234,1234,1234,1234,12),(4,2021,1234,1234,1234,1234,1234,1234,1234,1234,1234,1234,1234,1234,12),(7,2020,1234,0,0,1234,1234,1234,0,0,0,1234,1234,1234,12),(8,2019,1234,0,0,1234,1234,1234,0,0,0,0,0,1234,12),(9,2018,1234,0,0,1234,1234,1234,0,0,0,0,0,1234,12);
/*!40000 ALTER TABLE `salaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `telephone` varchar(45) DEFAULT NULL,
  `genre` varchar(45) NOT NULL,
  `date_naissance` date DEFAULT NULL,
  `date_entree` date NOT NULL,
  `date_sortie` date DEFAULT NULL,
  `titre` varchar(45) NOT NULL,
  `salaire_base` float NOT NULL,
  `email` varchar(100) NOT NULL,
  `status` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (12,'Victor','Hugo','011111111','H','2023-02-02','2023-02-02','2023-02-02','manager',3000,'hugo@gmail.com','Manager');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-03 15:34:58
