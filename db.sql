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
  `DataIscrizione` date NOT NULL,
  PRIMARY KEY (`CF`),
  KEY `AllievoCorso_idx` (`CodiceCorso`,`LivelloCorso`),
  CONSTRAINT `AllievoCorso` FOREIGN KEY (`CodiceCorso`, `LivelloCorso`) REFERENCES `corso` (`Codice`, `Livello`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `allievo`
--

LOCK TABLES `allievo` WRITE;
/*!40000 ALTER TABLE `allievo` DISABLE KEYS */;
INSERT INTO `allievo` VALUES ('faa','Mark','8999',0,7,'b','2024-07-04'),('fxx','Alex','78777',0,7,'b','2024-07-04');
/*!40000 ALTER TABLE `allievo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assenza`
--

DROP TABLE IF EXISTS `assenza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assenza` (
  `CodiceCorso` int NOT NULL,
  `LivelloCorso` varchar(45) NOT NULL,
  `Giorno` varchar(3) NOT NULL,
  `OraInizio` time NOT NULL,
  `OraFine` time NOT NULL,
  `Allievo` varchar(16) NOT NULL,
  `DataAssenza` date NOT NULL,
  PRIMARY KEY (`CodiceCorso`,`LivelloCorso`,`Giorno`,`OraInizio`,`OraFine`,`Allievo`,`DataAssenza`),
  KEY `AssenzaLezione_idx1` (`OraInizio`,`OraFine`,`Giorno`),
  KEY `AssenzaAllievo_idx` (`Allievo`),
  CONSTRAINT `AssenzaAllievo` FOREIGN KEY (`Allievo`) REFERENCES `allievo` (`CF`),
  CONSTRAINT `AssenzaLezione` FOREIGN KEY (`CodiceCorso`, `LivelloCorso`, `Giorno`, `OraInizio`, `OraFine`) REFERENCES `lezione` (`CodiceCorso`, `LivelloCorso`, `Giorno`, `OraInizio`, `OraFine`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assenza`
--

LOCK TABLES `assenza` WRITE;
/*!40000 ALTER TABLE `assenza` DISABLE KEYS */;
/*!40000 ALTER TABLE `assenza` ENABLE KEYS */;
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
  `DataDiAttivazione` date NOT NULL,
  `NumeroAllievi` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`Codice`,`Livello`),
  KEY `CorsoLivello_idx` (`Livello`),
  CONSTRAINT `CorsoLivello` FOREIGN KEY (`Livello`) REFERENCES `livello` (`Nome`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `corso`
--

LOCK TABLES `corso` WRITE;
/*!40000 ALTER TABLE `corso` DISABLE KEYS */;
INSERT INTO `corso` VALUES (7,'b','2024-06-05',2),(8,'c','2024-06-06',0),(9,'c','2024-06-06',0);
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
INSERT INTO `insegnante` VALUES ('a','a','a','a'),('b','b','b','b'),('d','guglielmo','p','africano'),('ffx','Guglielmo','Via del','ita'),('v','v','v','v');
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
  `Giorno` varchar(3) NOT NULL,
  `OraInizio` time NOT NULL,
  `OraFine` time NOT NULL,
  `Insegnante` varchar(16) NOT NULL,
  PRIMARY KEY (`CodiceCorso`,`LivelloCorso`,`Giorno`,`OraInizio`,`OraFine`),
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
INSERT INTO `lezione` VALUES (7,'b','lun','12:00:00','13:00:00','a'),(7,'b','mar','12:00:00','13:00:00','a'),(7,'b','lun','10:00:00','11:00:00','ffx'),(7,'b','lun','16:00:00','17:00:00','ffx'),(7,'b','lun','17:00:00','18:00:00','ffx'),(7,'b','mar','15:00:00','16:00:00','ffx'),(7,'b','mer','12:00:00','13:00:00','ffx');
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
INSERT INTO `livello` VALUES ('a','a',1),('b','b',1),('c','c',1);
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
INSERT INTO `utenti` VALUES ('amministratore','e792cd9665119b1244e8afcf36fb5f48','Amministrazione'),('ffx','b52967cc88dc9ce2824dd87dcf7575d2','Insegnante'),('segreteria','f549bf508de5e6c5277cd12d89e6f3c9','Segreteria');
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

-- Dump completed on 2024-07-24 16:21:50
