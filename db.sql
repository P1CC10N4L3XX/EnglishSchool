-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: istituto_inglese
-- ------------------------------------------------------
-- Server version	8.0.37

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
-- Table structure for table `allievo`
--

DROP TABLE IF EXISTS `allievo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `allievo` (
  `CF` varchar(16) NOT NULL,
  `Nome` varchar(45) NOT NULL,
  `NumeroTelefono` varchar(45) NOT NULL,
  `NumeroAssenze` int NOT NULL DEFAULT '0',
  `CodiceCorso` int NOT NULL,
  `LivelloCorso` varchar(45) NOT NULL,
  `DataIscrizione` datetime NOT NULL,
  PRIMARY KEY (`CF`),
  KEY `AllievoCorso_idx` (`CodiceCorso`,`LivelloCorso`),
  CONSTRAINT `AllievoCorso` FOREIGN KEY (`CodiceCorso`, `LivelloCorso`) REFERENCES `corso` (`Codice`, `Livello`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allievo`
--

LOCK TABLES `allievo` WRITE;
/*!40000 ALTER TABLE `allievo` DISABLE KEYS */;
/*!40000 ALTER TABLE `allievo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assegnazione`
--

DROP TABLE IF EXISTS `assegnazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assegnazione` (
  `CodiceCorso` int NOT NULL,
  `LivelloCorso` varchar(45) NOT NULL,
  `Insegnante` varchar(16) NOT NULL,
  PRIMARY KEY (`CodiceCorso`,`LivelloCorso`,`Insegnante`),
  KEY `AssegnazioneInsegnante_idx` (`Insegnante`),
  CONSTRAINT `AssegnazioneCorso` FOREIGN KEY (`CodiceCorso`, `LivelloCorso`) REFERENCES `corso` (`Codice`, `Livello`),
  CONSTRAINT `AssegnazioneInsegnante` FOREIGN KEY (`Insegnante`) REFERENCES `insegnante` (`CF`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assegnazione`
--

LOCK TABLES `assegnazione` WRITE;
/*!40000 ALTER TABLE `assegnazione` DISABLE KEYS */;
/*!40000 ALTER TABLE `assegnazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `corso`
--

DROP TABLE IF EXISTS `corso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `corso` (
  `Codice` int NOT NULL AUTO_INCREMENT,
  `Livello` varchar(45) NOT NULL,
  `DataDiAttivazione` datetime NOT NULL,
  `NumeroAllievi` int NOT NULL,
  PRIMARY KEY (`Codice`,`Livello`),
  KEY `CorsoLivello_idx` (`Livello`),
  CONSTRAINT `CorsoLivello` FOREIGN KEY (`Livello`) REFERENCES `livello` (`Nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `corso`
--

LOCK TABLES `corso` WRITE;
/*!40000 ALTER TABLE `corso` DISABLE KEYS */;
/*!40000 ALTER TABLE `corso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `insegnante`
--

DROP TABLE IF EXISTS `insegnante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `insegnante` (
  `CF` varchar(16) NOT NULL,
  `Nome` varchar(45) NOT NULL,
  `Indirizzo` varchar(45) NOT NULL,
  `Nazionalita` varchar(45) NOT NULL,
  PRIMARY KEY (`CF`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insegnante`
--

LOCK TABLES `insegnante` WRITE;
/*!40000 ALTER TABLE `insegnante` DISABLE KEYS */;
/*!40000 ALTER TABLE `insegnante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lezione`
--

DROP TABLE IF EXISTS `lezione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lezione` (
  `CodiceCorso` int NOT NULL,
  `LivelloCorso` varchar(45) NOT NULL,
  `Insegnante` varchar(16) NOT NULL,
  `Giorno` datetime NOT NULL,
  `OraInizio` varchar(5) NOT NULL,
  `OraFine` varchar(5) NOT NULL,
  PRIMARY KEY (`CodiceCorso`,`LivelloCorso`,`Insegnante`,`Giorno`,`OraInizio`,`OraFine`),
  KEY `LezioneInsegnante_idx` (`Insegnante`),
  CONSTRAINT `LezioneCorso` FOREIGN KEY (`CodiceCorso`, `LivelloCorso`) REFERENCES `corso` (`Codice`, `Livello`),
  CONSTRAINT `LezioneInsegnante` FOREIGN KEY (`Insegnante`) REFERENCES `insegnante` (`CF`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lezione`
--

LOCK TABLES `lezione` WRITE;
/*!40000 ALTER TABLE `lezione` DISABLE KEYS */;
/*!40000 ALTER TABLE `lezione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livello`
--

DROP TABLE IF EXISTS `livello`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `livello` (
  `Nome` varchar(45) NOT NULL,
  `Libro` varchar(45) NOT NULL,
  `Esame` tinyint(1) NOT NULL,
  PRIMARY KEY (`Nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livello`
--

LOCK TABLES `livello` WRITE;
/*!40000 ALTER TABLE `livello` DISABLE KEYS */;
/*!40000 ALTER TABLE `livello` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utenti`
--

DROP TABLE IF EXISTS `utenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utenti` (
  `Username` varchar(45) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `Ruolo` enum('Amministrazione','Segreteria','Insegnante') NOT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utenti`
--

LOCK TABLES `utenti` WRITE;
/*!40000 ALTER TABLE `utenti` DISABLE KEYS */;
/*!40000 ALTER TABLE `utenti` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-31 16:30:47
