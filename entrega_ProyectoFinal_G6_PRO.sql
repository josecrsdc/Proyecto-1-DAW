CREATE DATABASE  IF NOT EXISTS `bcn_v5` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `bcn_v5`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: bcn_v5
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `actividades`
--

DROP TABLE IF EXISTS `actividades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actividades` (
  `idActividad` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `descripcion` longtext NOT NULL,
  `url` varchar(400) DEFAULT NULL,
  `imagen` varchar(100) DEFAULT 'icons8_new_york_80px.png',
  `codigoSubtipo` varchar(4) NOT NULL,
  `distanciaCentro` decimal(4,1) DEFAULT NULL,
  `puntuacion` enum('1','2','3','4','5') NOT NULL,
  PRIMARY KEY (`idActividad`),
  KEY `fk_actividades_subtipos1_idx` (`codigoSubtipo`),
  CONSTRAINT `fk_actividades_subtipos` FOREIGN KEY (`codigoSubtipo`) REFERENCES `subtipos` (`codigoSubtipo`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actividades`
--

LOCK TABLES `actividades` WRITE;
/*!40000 ALTER TABLE `actividades` DISABLE KEYS */;
INSERT INTO `actividades` VALUES (1,'HOTEL CIUTAT DE BARCELONA','El Ciutat de Barcelona se encuentra en el barrio gótico de Barcelona, a solo 100 metros del Museo Picasso. Además, cuenta con una piscina al aire libre en la azotea y habitaciones insonorizadas con aire acondicionado, WiFi gratuita y TV de pantalla plana. Las habitaciones del Ciutat Barcelona Hotel también cuentan con calefacción, escritorio y baño privado con secador de pelo y artículos de aseo de Pure Eco. El Ciutat alberga un restaurante de tapas que sirve desayunos buffet, almuerzos y cenas.','https://www.ciutatbarcelona.com','ID-1-HOTE.jpg','HOTE',10.0,'5'),(2,'SANTA RITA EXPERIENCE','Santa Rita uno de los proyectos pioneros de la escena supperclub en Barcelona que nació en 2010 en casa del chef Vasco Xabi Bonilla, ahora en su nueva ubicación a pie de calle santa rita continua fiel a la experiencia intima y acogedora de casa, su nuevo espacio es una cocina elegante sin barreras en la que los invitados pueden moverse libremente, Porque la cocina es el lugar más íntimo y a la vez más compartido de un hogar, el lugar donde la vida se hace o los sueños se alimentan, el espacio reservado solamente a los de casa.','https://www.eltenedor.es/restaurante/santa-rita-experience-r459391','ID-2-COME.jpg','REST',30.0,'5'),(3,'CATALONIA ATENAS','El Atenas Catalonia está situado a 3 paradas de metro del centro de Barcelona y tiene una piscina en la azotea con vistas a la cercana torre Agbar y a la Sagrada Familia. Ofrece WiFi gratuita y alberga un gimnasio y un spa con baño turco.','https://www.cataloniahotels.com/es/hotel/catalonia-atenas','ID-3-HOTE.jpg','HOTE',20.0,'4'),(4,'ACTA VORAPORT','El Acta Voraport está situado en Barcelona, a 650 metros de la playa de Bogatell, y dispone de restaurante, aparcamiento privado, piscina al aire libre y bar.','https://www.hotelvoraport.com','ID-4-HOST.jpg','HOST',10.0,'5'),(5,'HOSTAL SANS','El Hostal Sans se encuentra en la plaza de Sants, a 5 minutos a pie de la estación de tren de Sants y a poco más de 1 km de la plaza de España. Ofrece habitaciones con TV y una recepción 24 horas.','http://www.hostalsans.com','ID-5-HOST.jpg','HOST',30.0,'1'),(6,'CATALONIA BARCELONA PLAZA','El Hotel Catalonia Barcelona Plaza se encuentra en la plaza de España de Barcelona y alberga una piscina de temporada en la azotea y una terraza con vistas de 360º a la ciudad.','https://www.cataloniahotels.com/es/hotel/catalonia-barcelona-plaza','ID-6-HOTE.jpg','HOTE',15.0,'5'),(7,'HOTEL SB ICARIA BARCELONA','El Hotel SB Icaria Barcelona, situado a 300 metros de la playa Nova Icaria de Barcelona, ​​ofrece gimnasio, sauna y piscina al aire libre con solárium y bañera de hidromasaje.','https://www.hotelicariabarcelona.com','ID-7-HOTE.jpg','HOTE',6.0,'4'),(8,'MUSEO NACIONAL D\'ART','Es uno de los más ricos en arte románico (los frescos de Sant Climent de Taüll) y gótico del mundo. También muestra obras del Renacimiento y el Barroco, así como fotografías, dibujos y carteles del XIX e inicios del XX. Se aloja en el Palau Nacional de Montjuïc.','https://www.museunacional.cat/es','ID-8-MUSE.jpg','MUSE',11.0,'4'),(9,'MUSEO HERMITAGE','Es uno de los más ricos en arte románico (los frescos de Sant Climent de Taüll) y gótico del mundo. También muestra obras del Renacimiento y el Barroco, así como fotografías, dibujos y carteles del XIX e inicios del XX. Se aloja en el Palau Nacional de Montjuïc.','https://www.barcelonaturisme.com/wv3/es/','ID-9-MUSE.jpg','MUSE',1.0,'2'),(10,'MUSEO MODERNO','Es uno de los más ricos en arte románico (los frescos de Sant Climent de Taüll) y gótico del mundo. También muestra obras del Renacimiento y el Barroco, así como fotografías, dibujos y carteles del XIX e inicios del XX. Se aloja en el Palau Nacional de Montjuïc.','https://www.barcelonaturisme.com/wv3/es/','ID-10-MUSE.jpg','MUSE',11.0,'1'),(11,'MUSEO ANTIGUO','Es uno de los más ricos en arte románico (los frescos de Sant Climent de Taüll) y gótico del mundo. También muestra obras del Renacimiento y el Barroco, así como fotografías, dibujos y carteles del XIX e inicios del XX. Se aloja en el Palau Nacional de Montjuïc.','https://www.barcelonaturisme.com/wv3/es/','ID-11-MUSE.jpg','MUSE',6.0,'3'),(12,'ARCO DE TRIUNFO','Es un monolito erigido para constituir el acceso principal en ocasión de celebrarse en la ciudad de Barcelona la Exposición Universal de Barcelona en el año 1888.','https://sitiosturisticos.com/arco-de-triunfo-de-barcelona/','ID-12-MONU.jpg','MONU',15.0,'5'),(13,'PALACIO GÜELL','El Palacio Güell, considerado como una verdadera joya de la arquitectura modernista, se localiza en el barrio llamado El Raval, Barcelona a poca distancia del puerto de la ciudad en Carrer Nou de la Rambla y fue declarado Patrimonio de la Humanidad por la UNESCO en el año 1984.','https://sitiosturisticos.com/palacio-guell/','ID-13-MONU.jpg','MONU',14.0,'4'),(14,'CASTILLO DE MONTJUIC','El Castillo de Montjuic es una antigua fortaleza que actualmente funciona como museo militar y se encuentra ubcado en la montaña de Montjuïc de la ciudad de Barcelona. Fue erigido en el sitio que originariamente era ocupado por una atalaya (torre de uso militar que tenía la finalidad de dar aviso cuando se aproximaban los barcos, sobre la cima de la montaña que lleva el mismo nombre) y que albergaba el Museo Militar.','https://sitiosturisticos.com/castillo-de-montjuic/','ID-14-MONU.jpg','MONU',13.0,'3'),(15,'BELLEBUON','Los más cafeteros seguro que os pasáis la vida buscando buenos sitios donde tomar un buen café. Os mentiría si os digo que sé cuál es el mejor sitio donde encontrar esta bebida, pero os puedo decir al que yo voy siempre y nunca me defrauda: Cafés El Magnífico. No tienen mesas para sentarse, pero es ideal para coger un buen café, llevarlo donde quieras y seguir con tu día.','https://www.manorota.com','ID-15-REST.jpg','REST',12.0,'4'),(16,'CHAKA KHAN','En los últimos años el centro de Barcelona se ha convertido en un universo cosmopolita donde todas las personas de diferente cultura se enriquecen unas de otras. Un lugar con una variedad abrumante de comidas exóticas, tan solo al alcance de un paseo. Por eso, a día de hoy, preservar una pizca lo auténtico supone todo un logro. ','https://www.manorota.com','ID-16-BAR.jpg','BAR',2.0,'2'),(17,'BODEGA BIARRITZ','Adentrándonos aún más en el barrio del Born, Les Dues Sicilies supone una de esas inesperadas sorpresas gastronómicas que, literalmente, te dejan con un exquisito sabor de boca. Si pasáis por delante de su escaparate probablemente quedéis hipnotizados por la buena pinta de sus pizzas.','https://www.manorota.com','ID-17-BAR.jpg','BAR',5.0,'3'),(18,'LA GASTRONÓMICA','Si mi abuela fuera de Japón y no de Albacete probablemente haría el sushi como lo hacen en Ziqi. En este pequeño y acogedor restaurante de Sant Antoni, mezclan lo mejor de la cocina japonesa con un carácter auténtico y cercano, como si estuvieras comiendo en la mismísima habitación de un adolescente tokiota.','https://www.manorota.com','ID-18-BAR.jpg','BAR',7.0,'2');
/*!40000 ALTER TABLE `actividades` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tr_BIActividadesMayusc` BEFORE INSERT ON `actividades` FOR EACH ROW BEGIN
    SET NEW.nombre = UPPER(NEW.nombre);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tr_AILogActividades` AFTER INSERT ON `actividades` FOR EACH ROW BEGIN
INSERT INTO logActividades (idActividad, tipoAccion, nombreActividad, codigoSubtipo, detalles, idUsuario) 
					VALUES (NEW.idActividad, 'INSERT', NEW.nombre, NEW.codigoSubtipo, 'Actividad Nueva', CURRENT_USER()); 
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tr_BUActividadesMayusc` BEFORE UPDATE ON `actividades` FOR EACH ROW BEGIN
	SET NEW.nombre = UPPER(NEW.nombre);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tr_AULogActividades` AFTER UPDATE ON `actividades` FOR EACH ROW BEGIN
DECLARE v_detalles VARCHAR(200) DEFAULT NULL;

SET v_detalles = 'Cambios: ';

IF OLD.nombre <> NEW.nombre THEN
		SET v_detalles = CONCAT(v_detalles ,'nombre ');
END IF; 
    
IF OLD.descripcion <> NEW.descripcion THEN
        SET v_detalles = CONCAT(v_detalles ,' descripcion ');
END IF;

IF OLD.url <> NEW.url THEN
        SET v_detalles = CONCAT(v_detalles ,' URL ');
END IF;
IF OLD.imagen <> NEW.imagen THEN
        SET v_detalles = CONCAT(v_detalles ,' imagen ');
END IF;     
IF OLD.codigoSubtipo <> NEW.codigoSubtipo THEN
        SET v_detalles = CONCAT(v_detalles ,' codigoSubtipo ');
END IF; 
IF OLD.distanciaCentro <> NEW.distanciaCentro THEN
        SET v_detalles = CONCAT(v_detalles ,' distanciaCentro ');
END IF;
IF OLD.puntuacion <> NEW.puntuacion THEN
        SET v_detalles = CONCAT(v_detalles ,' puntuacion ');
END IF; 
 
INSERT INTO logActividades (idActividad, tipoAccion, nombreActividad, codigoSubtipo, detalles, idUsuario) 
					VALUES (NEW.idActividad, 'UPDATE',OLD.nombre, OLD.codigoSubtipo, v_Detalles, CURRENT_USER());

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tr_ADLogActividades` AFTER DELETE ON `actividades` FOR EACH ROW BEGIN
INSERT INTO logActividades (idActividad, tipoAccion, nombreActividad, codigoSubtipo, detalles, idUsuario) 
					VALUES (OLD.idActividad, 'DELETE', OLD.nombre, OLD.codigoSubtipo, 'Actividad Eliminada', CURRENT_USER()); 
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tr_ADActividadesEliminadas` AFTER DELETE ON `actividades` FOR EACH ROW BEGIN
INSERT INTO actividadesEliminadas (idActividad, nombre, descripcion, url, imagen, codigoSubtipo, distanciaCentro, puntuacion) 
							VALUES (OLD.idActividad, OLD.nombre, OLD.descripcion, OLD.url, OLD.imagen, OLD.codigoSubtipo, OLD.distanciaCentro, OLD.puntuacion);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `actividadeseliminadas`
--

DROP TABLE IF EXISTS `actividadeseliminadas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actividadeseliminadas` (
  `idActividad` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `descripcion` longtext NOT NULL,
  `url` varchar(400) DEFAULT NULL,
  `imagen` varchar(100) DEFAULT NULL,
  `codigoSubtipo` varchar(4) NOT NULL,
  `distanciaCentro` decimal(4,1) DEFAULT NULL,
  `puntuacion` enum('1','2','3','4','5') NOT NULL,
  PRIMARY KEY (`idActividad`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actividadeseliminadas`
--

LOCK TABLES `actividadeseliminadas` WRITE;
/*!40000 ALTER TABLE `actividadeseliminadas` DISABLE KEYS */;
/*!40000 ALTER TABLE `actividadeseliminadas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detallepacks`
--

DROP TABLE IF EXISTS `detallepacks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detallepacks` (
  `idPack` int NOT NULL,
  `linea` int unsigned NOT NULL,
  `idActividad` int NOT NULL,
  `precio` double(6,2) NOT NULL,
  `fechaHoraInicio` datetime NOT NULL,
  `fechaHoraFin` datetime DEFAULT NULL,
  `duracion` time DEFAULT NULL,
  `numPlazas` int NOT NULL,
  `precioTotalActividad` decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (`idPack`,`linea`),
  KEY `fk_packs_actividades_packs1_idx` (`idPack`),
  KEY `fk_packs_actividades_actividades1_idx` (`idActividad`),
  CONSTRAINT `fk_packs_actividades_actividades` FOREIGN KEY (`idActividad`) REFERENCES `actividades` (`idActividad`),
  CONSTRAINT `fk_packs_actividades_packs` FOREIGN KEY (`idPack`) REFERENCES `packs` (`idPack`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detallepacks`
--

LOCK TABLES `detallepacks` WRITE;
/*!40000 ALTER TABLE `detallepacks` DISABLE KEYS */;
/*!40000 ALTER TABLE `detallepacks` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tr_BIActualizarPrecioTotal` BEFORE INSERT ON `detallepacks` FOR EACH ROW BEGIN
DECLARE numDias INT DEFAULT 0;
SET numDias = TIMESTAMPDIFF(DAY, NEW.fechaHoraInicio, NEW.fechaHoraFin);
    IF numDias > 0 THEN
        SET NEW.precioTotalActividad = ROUND(NEW.precio * numDias * NEW.numPlazas,2);
    ELSEIF numDias <= 0 THEN
        SET NEW.precioTotalActividad = ROUND(NEW.precio * NEW.numPlazas,2);
    END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tr_BUActualizarPrecioTotal` BEFORE UPDATE ON `detallepacks` FOR EACH ROW BEGIN
DECLARE numDias INT DEFAULT 0;
SET numDias = TIMESTAMPDIFF(DAY, NEW.fechaHoraInicio, NEW.fechaHoraFin);
        IF numDias > 0 THEN
            SET NEW.precioTotalActividad = ROUND(NEW.precio * numDias * NEW.numPlazas,2);
        ELSEIF numDias <= 0 THEN
            SET NEW.precioTotalActividad = ROUND(NEW.precio * NEW.numPlazas,2);
        END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tr_BUvalorNegativoNulo` BEFORE UPDATE ON `detallepacks` FOR EACH ROW BEGIN
IF NEW.precio < 0 THEN
SET NEW.precio = 0;
END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `logactividades`
--

DROP TABLE IF EXISTS `logactividades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `logactividades` (
  `idLog` int NOT NULL AUTO_INCREMENT,
  `idActividad` int NOT NULL,
  `tipoAccion` enum('INSERT','UPDATE','DELETE') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `nombreActividad` varchar(45) COLLATE utf8_bin NOT NULL,
  `codigoSubtipo` varchar(4) COLLATE utf8_bin NOT NULL,
  `detalles` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `fechaHora` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `idUsuario` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idLog`)
) ENGINE=InnoDB AUTO_INCREMENT=197 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logactividades`
--

LOCK TABLES `logactividades` WRITE;
/*!40000 ALTER TABLE `logactividades` DISABLE KEYS */;
/*!40000 ALTER TABLE `logactividades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `packs`
--

DROP TABLE IF EXISTS `packs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `packs` (
  `idPack` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) NOT NULL,
  `fechaValidez` datetime NOT NULL,
  `fechaCreacion` datetime NOT NULL,
  `dni` varchar(9) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idPack`),
  KEY `fk_packs_usuarios_idx` (`dni`),
  CONSTRAINT `fk_packs_usuarios` FOREIGN KEY (`dni`) REFERENCES `usuarios` (`dni`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `packs`
--

LOCK TABLES `packs` WRITE;
/*!40000 ALTER TABLE `packs` DISABLE KEYS */;
/*!40000 ALTER TABLE `packs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subtipos`
--

DROP TABLE IF EXISTS `subtipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subtipos` (
  `codigoSubtipo` varchar(4) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `codigoTipo` varchar(4) NOT NULL,
  PRIMARY KEY (`codigoSubtipo`),
  KEY `fk_subtipos_tipos1_idx` (`codigoTipo`),
  CONSTRAINT `fk_subtipos_tipos` FOREIGN KEY (`codigoTipo`) REFERENCES `tipos` (`codigoTipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subtipos`
--

LOCK TABLES `subtipos` WRITE;
/*!40000 ALTER TABLE `subtipos` DISABLE KEYS */;
INSERT INTO `subtipos` VALUES ('BAR','bar','COME'),('HOST','hostal','DORM'),('HOTE','hotel','DORM'),('MONU','monumento','VISI'),('MUSE','museo','VISI'),('REST','restaurante','COME');
/*!40000 ALTER TABLE `subtipos` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tr_BISubtiposMayusc` BEFORE INSERT ON `subtipos` FOR EACH ROW BEGIN
    SET NEW.nombre = UPPER(NEW.nombre);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `tipos`
--

DROP TABLE IF EXISTS `tipos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipos` (
  `codigoTipo` varchar(4) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`codigoTipo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos`
--

LOCK TABLES `tipos` WRITE;
/*!40000 ALTER TABLE `tipos` DISABLE KEYS */;
INSERT INTO `tipos` VALUES ('COME','donde_comer'),('DORM','donde_dormir'),('VISI','que_ver');
/*!40000 ALTER TABLE `tipos` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tr_BITiposMayusc` BEFORE INSERT ON `tipos` FOR EACH ROW BEGIN
    SET NEW.nombre = UPPER(NEW.nombre);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `dni` varchar(9) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `rol` enum('Administrador','Cliente') NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `direccion` varchar(150) NOT NULL,
  `ciudad` varchar(45) NOT NULL,
  `pais` varchar(45) NOT NULL,
  `codigoPostal` int NOT NULL,
  `email` varchar(50) NOT NULL,
  `telefono` int NOT NULL,
  `contrasenya` varchar(10) NOT NULL,
  PRIMARY KEY (`dni`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES ('19751266P','Cliente','Jesus','Calle Corta 1-9','Avila','España',47014,'jesus@gmail.com',645123874,'********'),('29751266P','Cliente','Isabel','Calle ser 1-9','Madrid','España',47014,'isabel@gmail.com',645114874,'********'),('39751266P','Cliente','Alvaro','Calle Lar 1-9','Asturias','España',47014,'alvaro@gmail.com',638124874,'********'),('49751266P','Cliente','Maite','Calle Mar 1-9','Castellon','España',47014,'maite@gmail.com',699124874,'********'),('49757866P','Cliente','Ivan','Calle Larga 1-9','Castellon','España',47014,'ivan@gmail.com',645124874,'********'),('53274644L','Cliente','Jose','Calle falsa 1-2','Valencia','España',46014,'jose@gmail.com',697451458,'********');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `tr_BI_dniMayusc` BEFORE INSERT ON `usuarios` FOR EACH ROW BEGIN
    SET NEW.dni = UPPER(NEW.dni);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-02 10:27:01
