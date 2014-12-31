-- MySQL dump 10.11
--
-- Host: localhost    Database: tablet_menu
-- ------------------------------------------------------
-- Server version	5.0.95

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
-- Table structure for table `attachment`
--

DROP TABLE IF EXISTS `attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attachment` (
  `_id` int(10) unsigned NOT NULL auto_increment,
  `id_domain` int(10) unsigned NOT NULL,
  `path` varchar(255) NOT NULL default '',
  `filename` varchar(100) NOT NULL default '',
  `filetype` varchar(4) NOT NULL default '',
  `width` int(10) unsigned default NULL,
  `height` int(10) unsigned default NULL,
  `size` bigint(20) unsigned NOT NULL default '0',
  `filedata` longblob,
  `creation_date` timestamp NULL default NULL,
  `update_date` timestamp NULL default NULL,
  `deleted` timestamp NULL default NULL,
  PRIMARY KEY  (`_id`),
  KEY `FK_attachment_domain` (`id_domain`),
  CONSTRAINT `FK_attachment_domain` FOREIGN KEY (`id_domain`) REFERENCES `domain` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attachment`
--

LOCK TABLES `attachment` WRITE;
/*!40000 ALTER TABLE `attachment` DISABLE KEYS */;
INSERT INTO `attachment` VALUES (2,2,'20120726','9.jpg','jpg',150,150,5238,NULL,'2012-07-26 11:20:30',NULL,'2012-07-26 11:52:01'),(3,2,'20120729','Anse Source d\'Argent.jpg','jpg',150,150,8793,NULL,'2012-07-28 22:07:26',NULL,NULL),(4,2,'20120829','dessert.jpg','jpg',340,150,41754,NULL,'2012-08-09 15:09:57','2012-08-29 07:40:58',NULL),(5,2,'20120829','antipasti.jpg','jpg',340,150,21838,NULL,'2012-08-09 15:12:17','2012-08-29 06:58:33',NULL),(6,2,'20120809','banane-flambe.jpg','jpg',150,150,4383,NULL,'2012-08-09 15:30:05',NULL,NULL),(7,2,'20120829','spaghetti-al-pomodoro-del-mazzo.jpg','jpg',340,150,26408,NULL,'2012-08-09 15:31:24','2012-08-29 07:47:54',NULL),(8,2,'20120810','sacher.jpg','jpg',150,150,12327,NULL,'2012-08-10 08:36:29',NULL,NULL),(9,2,'20120810','tortamele.jpg','jpg',150,150,6244,NULL,'2012-08-10 08:44:50',NULL,NULL),(10,2,'20120810','crostata meringata al limone.jpg','jpg',150,150,19252,NULL,'2012-08-10 08:49:09',NULL,NULL),(11,2,'20120810','cheesecake1.jpg','jpg',150,150,36381,NULL,'2012-08-10 08:49:58',NULL,NULL),(12,2,'20120810','seafoodsoup.jpg','jpg',150,150,16852,NULL,'2012-08-10 09:48:17',NULL,NULL),(13,2,'20120810','creme-caramel-1.jpg','jpg',150,150,10066,NULL,'2012-08-10 09:50:24',NULL,NULL),(14,2,'20120810','mousse-di-cioccolato.jpg','jpg',150,150,7952,NULL,'2012-08-10 09:51:18',NULL,NULL),(15,2,'20120810','Tiramisu.gif','gif',150,150,16292,NULL,'2012-08-10 09:52:43',NULL,NULL),(16,2,'20120810','chilisausage.jpg','jpg',150,150,27065,NULL,'2012-08-10 09:53:41',NULL,NULL),(17,2,'20120810','tuna-carpaccio.jpg','jpg',150,150,7546,NULL,'2012-08-10 09:54:34',NULL,NULL),(18,2,'20120810','lamb.JPG','jpg',150,150,10474,NULL,'2012-08-10 09:55:38',NULL,NULL),(19,2,'20120810','linguine.jpg','jpg',150,150,9625,NULL,'2012-08-10 09:57:16',NULL,NULL),(20,2,'20120810','trotalimone.jpg','jpg',150,150,8631,NULL,'2012-08-10 09:58:51',NULL,NULL),(21,2,'20120810','polloavocado.jpg','jpg',150,150,10555,NULL,'2012-08-10 10:00:15',NULL,NULL),(22,2,'20120810','tortillagambas.jpg','jpg',150,150,5580,NULL,'2012-08-10 10:11:22',NULL,NULL),(23,2,'20120810','bocadillo_york_queso.jpg','jpg',150,150,46673,NULL,'2012-08-10 10:15:55',NULL,NULL),(24,2,'20120823','piattounico.JPG','jpg',340,150,23034,NULL,'2012-08-23 14:17:07',NULL,NULL),(25,2,'20120823','panino.jpg','jpg',340,150,8034,NULL,'2012-08-23 14:19:25',NULL,NULL),(26,2,'20120823','tunasalad.jpg','jpg',480,360,45599,NULL,'2012-08-23 14:23:47',NULL,NULL),(27,2,'20120823','salmonsalad.jpg','jpg',480,360,29891,NULL,'2012-08-23 14:25:09',NULL,NULL),(28,2,'20120823','tunasandwich.jpg','jpg',480,360,214429,NULL,'2012-08-23 14:47:16',NULL,NULL);
/*!40000 ALTER TABLE `attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attachment_deleted`
--

DROP TABLE IF EXISTS `attachment_deleted`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attachment_deleted` (
  `_id` int(10) unsigned NOT NULL auto_increment,
  `id_domain` int(10) unsigned NOT NULL,
  `path` varchar(255) NOT NULL default '',
  `filename` varchar(100) NOT NULL default '',
  `deleted` timestamp NULL default NULL,
  PRIMARY KEY  (`_id`),
  KEY `FK_attachment_deleted1` (`id_domain`),
  CONSTRAINT `FK_attachment_deleted1` FOREIGN KEY (`id_domain`) REFERENCES `domain` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attachment_deleted`
--

LOCK TABLES `attachment_deleted` WRITE;
/*!40000 ALTER TABLE `attachment_deleted` DISABLE KEYS */;
INSERT INTO `attachment_deleted` VALUES (2,2,'20120809','piatto-pasta-300.jpg','2012-08-29 07:47:54');
/*!40000 ALTER TABLE `attachment_deleted` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `_id` int(10) unsigned NOT NULL auto_increment,
  `id_menu` int(10) unsigned NOT NULL,
  `img` int(10) unsigned default NULL,
  `active` tinyint(3) unsigned NOT NULL default '1',
  `creation_date` timestamp NULL default NULL,
  `update_date` timestamp NULL default NULL,
  `deleted` timestamp NULL default NULL,
  `item_order` smallint(6) default NULL,
  PRIMARY KEY  (`_id`),
  KEY `FK_category1` (`id_menu`),
  KEY `FK_category2` (`img`),
  CONSTRAINT `FK_category1` FOREIGN KEY (`id_menu`) REFERENCES `menu` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_category2` FOREIGN KEY (`img`) REFERENCES `attachment` (`_id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,3,5,1,'2012-07-18 18:25:21','2012-08-29 06:58:33',NULL,1),(2,3,24,1,'2012-07-18 18:25:30','2012-08-23 14:17:07',NULL,2),(3,3,NULL,1,'2012-07-18 18:25:41',NULL,'2012-07-22 09:47:49',4),(8,3,NULL,0,'2012-07-19 20:48:36','2012-07-20 20:27:55','2012-07-22 09:49:47',3),(9,3,NULL,0,'2012-07-22 10:48:01',NULL,'2012-07-22 09:49:43',5),(10,3,NULL,0,'2012-07-22 10:49:54',NULL,'2012-07-22 10:52:48',6),(11,3,NULL,0,'2012-07-22 10:53:45',NULL,'2012-07-22 10:52:48',7),(12,3,NULL,0,'2012-07-22 10:55:33',NULL,'2012-07-22 10:52:48',8),(13,3,NULL,0,'2012-07-22 10:58:26',NULL,'2012-07-22 10:52:48',9),(14,3,NULL,0,'2012-07-22 10:59:49',NULL,'2012-07-22 10:52:48',10),(15,3,NULL,0,'2012-07-22 11:51:32','2012-08-10 09:33:06','2012-08-10 10:02:02',11),(16,3,4,1,'2012-08-09 15:09:57','2012-08-29 07:40:58',NULL,12),(17,3,7,1,'2012-08-09 15:21:00','2012-08-29 07:47:54',NULL,13),(18,3,25,1,'2012-08-10 09:41:07','2012-08-23 14:19:25',NULL,14);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category_2_language`
--

DROP TABLE IF EXISTS `category_2_language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category_2_language` (
  `_id` int(10) unsigned NOT NULL auto_increment,
  `id_category` int(10) unsigned NOT NULL,
  `lang` varchar(5) NOT NULL,
  `title` varchar(50) NOT NULL,
  `subtitle` text NOT NULL,
  `body` text NOT NULL,
  `creation_date` timestamp NULL default NULL,
  `update_date` timestamp NULL default NULL,
  `deleted` timestamp NULL default NULL,
  PRIMARY KEY  (`_id`),
  KEY `FK_category_2_language1` (`lang`),
  KEY `FK_category_2_language2` (`id_category`),
  CONSTRAINT `FK_category_2_language1` FOREIGN KEY (`lang`) REFERENCES `language` (`label`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_category_2_language2` FOREIGN KEY (`id_category`) REFERENCES `category` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category_2_language`
--

LOCK TABLES `category_2_language` WRITE;
/*!40000 ALTER TABLE `category_2_language` DISABLE KEYS */;
INSERT INTO `category_2_language` VALUES (1,1,'en_EN','Starters','Starting <b>tasty</b>',' ','2012-07-18 18:26:16','2012-08-23 14:48:37',NULL),(2,1,'it_IT','Antipasti','Per iniziare con <b>gusto</b>',' ','2012-07-18 18:26:37','2012-08-23 14:48:57',NULL),(3,1,'es_ES','Entrantes','Para empezar con <b>gusto</b>',' ','2012-07-18 18:27:01','2012-08-23 14:48:48',NULL),(4,2,'en_EN','Main Course',' ',' ','2012-07-19 08:04:48',NULL,NULL),(5,1,'de_DE','SprichMatz','bla ','bla','2012-07-22 09:36:15',NULL,NULL),(6,8,'en_EN','Special','','','2012-07-22 09:37:41',NULL,NULL),(7,14,'en_EN','dddd','','','2012-07-22 09:59:56',NULL,NULL),(8,15,'en_EN','awcac','','','2012-07-22 10:51:35',NULL,NULL),(9,16,'en_EN','Desserts','<i><span style=\"color: rgb(49, 45, 46); font-family: Arial; line-height: 18px; text-align: justify; \">The freshest and finest cakes and delights</span>&nbsp;</i><br>','<br>','2012-08-09 15:10:10','2012-08-23 14:59:36',NULL),(10,16,'it_IT','Dolci','<i>Le delizie più fresche e buone</i><br>','<br>','2012-08-09 15:11:13','2012-08-23 15:02:48',NULL),(11,17,'en_EN','Pasta','A lot of different pastas with <u>the tastiest sauces</u>&nbsp;and blalnlallanhddnh mnanhhddbvhdb, nnfchhh &nbsp;kiughvbdbd. aggagagagshshhsh, mjhcgcbvcvgdhdbbsbb hfg mfhhdb kj!!&nbsp;','<br>','2012-08-09 15:21:06','2012-08-23 15:09:22',NULL),(12,17,'it_IT','Pasta','Tanti tipi di pasta con le più <u>deliziose salse</u>&nbsp;\r\nand blalnlallanhddnh mnanhhddbvhdb, nnfchhh &nbsp;kiughvbdbd. aggagagagshshhsh, mjhcgcbvcvgdhdbbsbb hfg mfhhdb kj!!&nbsp;','<br>','2012-08-09 15:26:44','2012-08-23 15:10:09',NULL),(13,17,'es_ES','Pasta','Muchos tipos de pastas con&nbsp;<u>las salsas màs sabrosas</u>&nbsp;\r\nand blalnlallanhddnh mnanhhddbvhdb, nnfchhh &nbsp;kiughvbdbd. aggagagagshshhsh, mjhcgcbvcvgdhdbbsbb hfg mfhhdb kj!!&nbsp;','<br>','2012-08-09 15:26:55','2012-08-23 15:09:52',NULL),(14,16,'es_ES','Postres','<i>Los dulces màs frescos y gustosos</i>','<br>','2012-08-09 15:27:09','2012-08-23 15:06:35',NULL),(15,18,'en_EN','Sandwiches','<font face=\"impact\">Big tasty sandwiches</font>','<br>','2012-08-10 09:41:17','2012-08-23 15:13:14',NULL),(16,18,'it_IT','Panini','<span style=\"font-family: impact; \">Big tasty sandwiches</span>&nbsp;<br>','<br>','2012-08-10 09:41:32','2012-08-23 15:13:39',NULL),(17,18,'es_ES','Bocadillos','<span style=\"font-family: impact; \">Big tasty sandwiches</span>&nbsp;<br>','<br>','2012-08-10 09:41:44','2012-08-23 15:13:31',NULL),(18,2,'it_IT','Piatto unico','<br>','<br>','2012-08-23 14:14:26',NULL,NULL),(19,2,'es_ES','Platos principales','<br>','<br>','2012-08-23 14:22:13',NULL,NULL);
/*!40000 ALTER TABLE `category_2_language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device`
--

DROP TABLE IF EXISTS `device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `domain` int(10) unsigned NOT NULL,
  `device_id` varchar(200) NOT NULL,
  `creation_date` timestamp NULL default NULL,
  `version` int(10) unsigned default NULL,
  `last_update` timestamp NULL default NULL,
  `md5_key` varchar(10) NOT NULL default '',
  `deleted` timestamp NULL default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `device_id` (`device_id`),
  KEY `FK_device1` (`domain`),
  KEY `FK_device2` (`version`),
  CONSTRAINT `FK_device1` FOREIGN KEY (`domain`) REFERENCES `domain` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_device2` FOREIGN KEY (`version`) REFERENCES `menu_version` (`_id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device`
--

LOCK TABLES `device` WRITE;
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
INSERT INTO `device` VALUES (1,2,'APA91bE9203BFxZYcO6v5aHn7V__ld5UVDPKkCRJ5_z89tCnalw6ARWv7v1tt8_fvVexBnaVzvOXwdzyCeG8sjRu5AIoYYuUZ02G84-gOpoVQuGqOKz9CqO6k0KsQyNmDUBunpomyh8vK9hzUvfzKoATLkoFHWkEvQ','2012-09-05 19:28:26',NULL,'2012-09-05 19:28:26','dpThy7Xu2h',NULL);
/*!40000 ALTER TABLE `device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `device_action`
--

DROP TABLE IF EXISTS `device_action`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `device_action` (
  `id` bigint(20) unsigned NOT NULL auto_increment,
  `id_device` int(10) unsigned NOT NULL,
  `action` tinyint(4) NOT NULL,
  `action_date` timestamp NULL default NULL,
  `custom_data` varchar(20) NOT NULL default '',
  `success` tinyint(3) unsigned default '0',
  PRIMARY KEY  (`id`),
  KEY `FK_device_action1` (`id_device`),
  CONSTRAINT `FK_device_action1` FOREIGN KEY (`id_device`) REFERENCES `device` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `device_action`
--

LOCK TABLES `device_action` WRITE;
/*!40000 ALTER TABLE `device_action` DISABLE KEYS */;
INSERT INTO `device_action` VALUES (1,1,1,'2012-09-05 19:28:26','',1),(2,1,2,'2012-09-05 19:28:26','0|1',1),(3,1,5,'2012-09-05 19:28:27','1_deploy.sql',1),(4,1,5,'2012-09-05 19:28:27','1_attach.zip',1),(5,1,5,'2012-09-05 19:28:28','1_cmd.txt',1),(6,1,4,'2012-09-05 19:37:29','',1),(7,1,1,'2012-09-05 19:40:04','',1),(8,1,2,'2012-09-05 19:40:04','0|1',1),(9,1,5,'2012-09-05 19:40:05','1_deploy.sql',1),(10,1,5,'2012-09-05 19:40:05','1_attach.zip',1),(11,1,5,'2012-09-05 19:40:05','1_cmd.txt',1),(12,1,4,'2012-09-05 19:41:07','',1),(13,1,1,'2012-09-05 19:42:05','',1),(14,1,2,'2012-09-05 19:42:05','0|1',1),(15,1,5,'2012-09-05 19:42:06','1_deploy.sql',1),(16,1,5,'2012-09-05 19:42:06','1_attach.zip',1),(17,1,5,'2012-09-05 19:42:07','1_cmd.txt',1),(18,1,4,'2012-09-05 19:44:29','',1),(19,1,1,'2012-09-05 19:44:48','',1),(20,1,2,'2012-09-05 19:44:48','0|1',1),(21,1,5,'2012-09-05 19:44:49','1_deploy.sql',1),(22,1,5,'2012-09-05 19:44:50','1_attach.zip',1),(23,1,5,'2012-09-05 19:44:50','1_cmd.txt',1),(24,1,4,'2012-09-05 19:50:13','',1),(25,1,1,'2012-09-05 19:50:25','',1),(26,1,2,'2012-09-05 19:50:25','0|1',1),(27,1,5,'2012-09-05 19:50:28','1_deploy.sql',1),(28,1,5,'2012-09-05 19:50:28','1_attach.zip',1),(29,1,5,'2012-09-05 19:50:28','1_cmd.txt',1);
/*!40000 ALTER TABLE `device_action` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `domain`
--

DROP TABLE IF EXISTS `domain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `domain` (
  `_id` int(10) unsigned NOT NULL auto_increment,
  `label` varchar(5) NOT NULL,
  `description` varchar(50) NOT NULL default '',
  `system` tinyint(3) unsigned NOT NULL default '0',
  `active` tinyint(4) unsigned NOT NULL default '0',
  `creation_date` timestamp NULL default NULL,
  `device_key` varchar(6) NOT NULL default '',
  `unlock_key` varchar(9) NOT NULL default '',
  PRIMARY KEY  (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `domain`
--

LOCK TABLES `domain` WRITE;
/*!40000 ALTER TABLE `domain` DISABLE KEYS */;
INSERT INTO `domain` VALUES (1,'SYS','System Domain Shared',1,1,'2012-07-12 14:56:31','',''),(2,'OXY','OxyBay Consulting S.L.',0,1,'2012-07-12 14:56:31','fvhssi','9687'),(3,'DOT','Dotland Srl',0,0,'2012-07-12 16:44:11','','');
/*!40000 ALTER TABLE `domain` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `language` (
  `label` varchar(5) NOT NULL,
  `description` varchar(50) NOT NULL default '',
  `active` tinyint(4) NOT NULL default '0',
  `application` tinyint(4) NOT NULL default '0',
  `_id` int(10) NOT NULL auto_increment,
  PRIMARY KEY  (`label`),
  UNIQUE KEY `_id` (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` VALUES ('cs_CS','Česky',1,0,1),('da_DA','Dansk',1,0,2),('de_DE','Deutsch',1,1,3),('en_EN','English',1,1,4),('es_ES','Español',1,1,5),('fi_FI','Suomi',1,0,6),('fr_FR','Français',1,1,7),('hu_HU','Hrvatski',1,0,8),('it_IT','Italiano',1,1,9),('nl_NL','Nederlands',1,0,10),('no_NO','Norsk',1,0,11),('pt_PT','Português',1,1,12),('ru_ru','Русский',1,0,14),('sv_SV','Svenska',1,0,13);
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu` (
  `_id` int(10) unsigned NOT NULL auto_increment,
  `domain` int(10) unsigned NOT NULL,
  `title` varchar(100) NOT NULL,
  `subtitle` text NOT NULL,
  `body` text NOT NULL,
  `logo` int(10) unsigned default NULL,
  `currency` enum('USD','EUR','GBP') NOT NULL,
  `active` tinyint(3) unsigned NOT NULL default '1',
  `creation_date` timestamp NULL default NULL,
  `update_date` timestamp NULL default NULL,
  `deleted` timestamp NULL default NULL,
  `last_version` int(10) unsigned default NULL,
  `main_lang` varchar(5) NOT NULL,
  PRIMARY KEY  (`_id`),
  KEY `FK_menu1` (`domain`),
  KEY `FK2_menu2` (`last_version`),
  KEY `FK_menu3` (`logo`),
  KEY `FK_menu4` (`main_lang`),
  CONSTRAINT `FK2_menu2` FOREIGN KEY (`last_version`) REFERENCES `menu_version` (`_id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `FK_menu1` FOREIGN KEY (`domain`) REFERENCES `domain` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_menu3` FOREIGN KEY (`logo`) REFERENCES `attachment` (`_id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `FK_menu4` FOREIGN KEY (`main_lang`) REFERENCES `language` (`label`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (3,2,'Menu senza titolo','<br>','<br>',3,'EUR',1,'2012-08-01 17:34:37','2012-08-12 08:44:25',NULL,3,'en_EN');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_2_language`
--

DROP TABLE IF EXISTS `menu_2_language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu_2_language` (
  `_id` int(10) unsigned NOT NULL auto_increment,
  `id_menu` int(10) unsigned NOT NULL,
  `lang` varchar(5) NOT NULL,
  PRIMARY KEY  (`_id`),
  KEY `FK_menu_2_language1` (`id_menu`),
  KEY `FK_menu_2_language2` (`lang`),
  CONSTRAINT `FK_menu_2_language1` FOREIGN KEY (`id_menu`) REFERENCES `menu` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_menu_2_language2` FOREIGN KEY (`lang`) REFERENCES `language` (`label`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_2_language`
--

LOCK TABLES `menu_2_language` WRITE;
/*!40000 ALTER TABLE `menu_2_language` DISABLE KEYS */;
INSERT INTO `menu_2_language` VALUES (16,3,'it_IT'),(17,3,'es_ES'),(18,3,'en_EN');
/*!40000 ALTER TABLE `menu_2_language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu_version`
--

DROP TABLE IF EXISTS `menu_version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menu_version` (
  `_id` int(10) unsigned NOT NULL auto_increment,
  `id_menu` int(10) unsigned NOT NULL,
  `id_domain` int(10) unsigned NOT NULL,
  `version` int(10) unsigned NOT NULL,
  `file_sql` varchar(50) default NULL,
  `file_sql_size` int(10) unsigned NOT NULL default '0',
  `file_zip` varchar(50) default NULL,
  `file_zip_size` int(10) unsigned NOT NULL default '0',
  `file_op` varchar(50) default NULL,
  `file_op_size` int(10) unsigned NOT NULL default '0',
  `creation_date` timestamp NULL default NULL,
  `deploy_end` timestamp NULL default NULL,
  PRIMARY KEY  (`_id`),
  KEY `FK_menu_version1` (`id_menu`),
  KEY `FK_menu_version2` (`id_domain`),
  CONSTRAINT `FK_menu_version1` FOREIGN KEY (`id_menu`) REFERENCES `menu` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_menu_version2` FOREIGN KEY (`id_domain`) REFERENCES `domain` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu_version`
--

LOCK TABLES `menu_version` WRITE;
/*!40000 ALTER TABLE `menu_version` DISABLE KEYS */;
INSERT INTO `menu_version` VALUES (3,3,2,1,'1_deploy.sql',57828,'1_attach.zip',605316,'1_cmd.txt',32,'2012-09-05 19:39:41','2012-09-05 19:39:41');
/*!40000 ALTER TABLE `menu_version` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `_id` int(10) unsigned NOT NULL auto_increment,
  `id_menu` int(10) unsigned NOT NULL,
  `img` int(10) unsigned default NULL,
  `active` tinyint(3) unsigned NOT NULL default '1',
  `price` decimal(10,2) unsigned NOT NULL default '0.00',
  `creation_date` timestamp NULL default NULL,
  `update_date` timestamp NULL default NULL,
  `deleted` timestamp NULL default NULL,
  `item_order` smallint(6) default NULL,
  PRIMARY KEY  (`_id`),
  KEY `FK_product1` (`id_menu`),
  KEY `FK_product2` (`img`),
  CONSTRAINT `FK_product1` FOREIGN KEY (`id_menu`) REFERENCES `menu` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_product2` FOREIGN KEY (`img`) REFERENCES `attachment` (`_id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,3,11,1,'6.50','2012-07-22 20:02:43','2012-09-05 08:54:34',NULL,1),(2,3,16,1,'11.00','2012-07-22 20:03:01','2012-09-05 12:53:31',NULL,2),(3,3,6,1,'6.00','2012-07-23 14:19:55','2012-08-09 15:30:05',NULL,3),(4,3,17,1,'9.00','2012-08-09 15:18:29','2012-08-10 09:54:34',NULL,4),(5,3,19,1,'8.50','2012-08-09 15:21:30','2012-09-05 11:58:59',NULL,5),(6,3,21,1,'10.00','2012-08-09 15:25:17','2012-08-10 10:00:15',NULL,6),(7,3,26,1,'8.00','2012-08-09 15:32:07','2012-08-23 14:23:47',NULL,7),(8,3,14,1,'6.00','2012-08-09 15:33:55','2012-08-10 09:51:18',NULL,8),(9,3,15,1,'6.00','2012-08-10 08:23:05','2012-08-10 09:52:43',NULL,9),(10,3,8,1,'6.00','2012-08-10 08:33:05','2012-08-10 08:36:29',NULL,10),(11,3,9,1,'6.00','2012-08-10 08:42:05','2012-08-10 08:44:50',NULL,11),(12,3,10,1,'6.00','2012-08-10 08:45:32','2012-08-10 08:49:09',NULL,12),(13,3,18,1,'8.50','2012-08-10 08:50:59','2012-08-10 09:55:38',NULL,13),(14,3,20,1,'9.00','2012-08-10 09:02:48','2012-08-10 09:58:51',NULL,14),(15,3,NULL,0,'9.00','2012-08-10 09:13:15','2012-08-10 09:40:06','2012-08-10 10:05:19',15),(16,3,27,1,'9.00','2012-08-10 09:15:09','2012-08-23 14:25:09',NULL,16),(17,3,13,1,'6.00','2012-08-10 09:44:36','2012-08-10 09:50:24',NULL,17),(18,3,12,0,'7.00','2012-08-10 09:46:04','2012-09-04 18:40:58',NULL,18),(19,3,22,1,'6.00','2012-08-10 10:01:34','2012-08-10 10:11:22',NULL,19),(20,3,23,1,'6.00','2012-08-10 10:14:33','2012-08-23 14:34:29',NULL,20),(21,3,28,1,'6.50','2012-08-23 14:41:55','2012-08-23 14:47:16',NULL,21);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_2_category`
--

DROP TABLE IF EXISTS `product_2_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_2_category` (
  `id_product` int(10) unsigned NOT NULL,
  `id_category` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`id_product`,`id_category`),
  KEY `FK_product_2_category1` (`id_category`),
  CONSTRAINT `FK_product_2_category1` FOREIGN KEY (`id_category`) REFERENCES `category` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_product_2_category2` FOREIGN KEY (`id_product`) REFERENCES `product` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_2_category`
--

LOCK TABLES `product_2_category` WRITE;
/*!40000 ALTER TABLE `product_2_category` DISABLE KEYS */;
INSERT INTO `product_2_category` VALUES (4,1),(7,1),(15,1),(16,1),(18,1),(19,1),(6,2),(13,2),(14,2),(1,16),(8,16),(9,16),(10,16),(11,16),(12,16),(17,16),(5,17),(20,18),(21,18);
/*!40000 ALTER TABLE `product_2_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_2_language`
--

DROP TABLE IF EXISTS `product_2_language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product_2_language` (
  `_id` int(10) unsigned NOT NULL auto_increment,
  `id_product` int(10) unsigned NOT NULL,
  `lang` varchar(5) NOT NULL,
  `title` varchar(50) NOT NULL,
  `subtitle` text NOT NULL,
  `body` text NOT NULL,
  `creation_date` timestamp NULL default NULL,
  `update_date` timestamp NULL default NULL,
  `deleted` timestamp NULL default NULL,
  PRIMARY KEY  (`_id`),
  KEY `FK_product_2_language1` (`lang`),
  KEY `FK_product_2_language2` (`id_product`),
  CONSTRAINT `FK_product_2_language1` FOREIGN KEY (`lang`) REFERENCES `language` (`label`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_product_2_language2` FOREIGN KEY (`id_product`) REFERENCES `product` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_2_language`
--

LOCK TABLES `product_2_language` WRITE;
/*!40000 ALTER TABLE `product_2_language` DISABLE KEYS */;
INSERT INTO `product_2_language` VALUES (9,1,'en_EN','CheeseCake',' ',' ','2012-07-22 20:04:01',NULL,NULL),(10,1,'it_IT','Cheese Cake',' ',' ','2012-07-22 20:04:25','2012-08-10 08:25:41',NULL),(11,2,'en_EN','Chili Sausage',' ',' ','2012-07-22 20:05:10',NULL,NULL),(12,3,'en_EN','Banana Flambe','aa','dd','2012-07-23 13:30:30',NULL,NULL),(13,3,'it_IT','Banane alla fiamma','<br>','<br>','2012-08-02 17:12:25',NULL,NULL),(14,4,'en_EN','Tuna Carpaccio','<br>','<br>','2012-08-09 15:18:39',NULL,NULL),(15,4,'it_IT','Carpaccio di tonno','<br>','<br>','2012-08-09 15:19:01',NULL,NULL),(16,4,'es_ES','Carpaccio de atun','<br>','<br>','2012-08-09 15:19:40',NULL,NULL),(17,5,'en_EN','Mushroom Linguini','<br>','<br>','2012-08-09 15:21:53',NULL,NULL),(18,5,'it_IT','Linguine ai funghi','<br>','<br>','2012-08-09 15:22:12','2012-08-09 15:22:17',NULL),(19,6,'en_EN','Avocado Chicken','<br>','<br>','2012-08-09 15:25:27',NULL,NULL),(20,6,'it_IT','Pollo con avogado','<br>','<br>','2012-08-09 15:25:50',NULL,NULL),(21,7,'en_EN','Tuna Salad','<br>','<br>','2012-08-09 15:32:17',NULL,NULL),(22,7,'it_IT','Insalata di tonno','<br>','<br>','2012-08-09 15:32:33',NULL,NULL),(23,7,'es_ES','Ensalada de atun','<br>','<br>','2012-08-09 15:32:53',NULL,NULL),(24,8,'en_EN','Chocolate Mousse','<br>','<br>','2012-08-09 15:34:03',NULL,NULL),(25,8,'it_IT','Mousse al cioccolato','<br>','<br>','2012-08-09 15:34:19',NULL,NULL),(26,9,'en_EN','Homemade Tiramisu','<br>','<br>','2012-08-10 08:23:22',NULL,NULL),(27,9,'it_IT','Tiramisu ','<br>','<br>','2012-08-10 08:24:02',NULL,NULL),(28,8,'es_ES','Mousse de chocolate','<br>','<br>','2012-08-10 08:24:38',NULL,NULL),(29,2,'it_IT','Salsiccia al chili','<br>','<br>','2012-08-10 08:28:46',NULL,NULL),(30,10,'en_EN','Sacher Torte','<br>','<br>','2012-08-10 08:34:46',NULL,NULL),(31,10,'it_IT','Sacher Torte','<br>','<br>','2012-08-10 08:35:03',NULL,NULL),(32,10,'es_ES','Sacher Torte','<br>','<br>','2012-08-10 08:35:16',NULL,NULL),(33,11,'en_EN','Apple Pie','<br>','<br>','2012-08-10 08:42:12',NULL,NULL),(34,11,'it_IT','Torta di mele','<br>','<br>','2012-08-10 08:42:50',NULL,NULL),(35,11,'es_ES','Tarta de manzana','<br>','<br>','2012-08-10 08:43:49',NULL,NULL),(36,12,'en_EN','Lemon meringue','<br>','<br>','2012-08-10 08:45:38',NULL,NULL),(37,12,'it_IT','Meringata al limone','<br>','<br>','2012-08-10 08:45:54',NULL,NULL),(38,12,'es_ES','Tarta merengue y limòn','<br>','<br>','2012-08-10 08:47:45',NULL,NULL),(39,13,'en_EN','Lamb in a mint sauce','<br>','<br>','2012-08-10 08:51:14',NULL,NULL),(40,13,'it_IT','Agnello in salsa di menta','<br>','<br>','2012-08-10 08:52:09',NULL,NULL),(41,13,'es_ES','Cordero en salsa de menta','<br>','<br>','2012-08-10 08:54:34',NULL,NULL),(42,1,'es_ES','Tarta de queso','<br>','<br>','2012-08-10 08:55:22',NULL,NULL),(43,5,'es_ES','Linguini con setas','<br>','<br>','2012-08-10 08:56:14',NULL,NULL),(44,6,'es_ES','Pollo con aguacate','<br>','<br>','2012-08-10 08:57:55',NULL,NULL),(45,9,'es_ES','Tiramisu ','<br>','<br>','2012-08-10 08:58:14',NULL,NULL),(46,3,'es_ES','Plàtano flambe','<br>','<br>','2012-08-10 08:59:45',NULL,NULL),(47,14,'en_EN','Fresh Trout in a lemon sauce','<br>','<br>','2012-08-10 09:02:54','2012-08-10 09:04:37',NULL),(48,14,'es_ES','Trucha fresca con salsa de limòn','<br>','<br>','2012-08-10 09:04:11',NULL,NULL),(49,14,'it_IT','Trota fresca in salsa di limone','<br>','<br>','2012-08-10 09:05:00',NULL,NULL),(50,16,'en_EN','Smoked salmon salad','<br>','<br>','2012-08-10 09:15:19',NULL,NULL),(51,16,'it_IT','Salmone affumicato in insalata','<br>','<br>','2012-08-10 09:34:53',NULL,NULL),(52,16,'es_ES','Ensalada de salmòn ahumado','<br>','<br>','2012-08-10 09:35:54',NULL,NULL),(53,2,'es_ES','Salchicha con pimientos','<br>','<br>','2012-08-10 09:39:52',NULL,NULL),(54,17,'en_EN','Crème caramel','<br>','<br>','2012-08-10 09:44:44',NULL,NULL),(55,17,'it_IT','Crème caramel','<br>','<br>','2012-08-10 09:44:58',NULL,NULL),(56,17,'es_ES','Flan','<br>','<br>','2012-08-10 09:45:10',NULL,NULL),(57,18,'en_EN','Seafood soup','<br>','<br>','2012-08-10 09:46:12',NULL,NULL),(58,18,'es_ES','Sopa de marisco','<br>','<br>','2012-08-10 09:46:35',NULL,NULL),(59,18,'it_IT','Zuppa di pesce','<br>','<br>','2012-08-10 09:46:49',NULL,NULL),(60,19,'en_EN','Prawns omelet','<br>','<br>','2012-08-10 10:01:43',NULL,NULL),(61,19,'es_ES','Tortilla de gambas','<br>','<br>','2012-08-10 10:02:05',NULL,NULL),(62,19,'it_IT','Frittata di gamberi','<br>','<br>','2012-08-10 10:10:27',NULL,NULL),(63,20,'en_EN','Amanda','Ham and cheese<br>','<b>Lorem ipsum </b>dolor sit amet, consectetur adipisicing elit, sed do eiusmod\r\n tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim \r\nveniam, quis nostrud exercitation ullamco <i>laboris </i>nisi ut aliquip ex ea \r\ncommodo consequat. Duis aute irure dolor in reprehenderit in voluptate \r\nvelit esse cillum dolore eu fugiat nulla <u>pariatur</u>. Excepteur sint \r\noccaecat cupidatat non proident, sunt in culpa qui officia deserunt \r\nmollit anim id est laborum','2012-08-10 10:14:45','2012-08-23 15:15:06',NULL),(64,20,'it_IT','Amanda','Prosciutto cotto e formaggio<br>','<b>Lorem ipsum </b>dolor sit amet, consectetur adipisicing elit, sed do eiusmod\r\n tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim \r\nveniam, quis nostrud exercitation ullamco <i>laboris </i>nisi ut aliquip ex ea \r\ncommodo consequat. Duis aute irure dolor in reprehenderit in voluptate \r\nvelit esse cillum dolore eu fugiat nulla <u>pariatur</u>. Excepteur sint \r\noccaecat cupidatat non proident, sunt in culpa qui officia deserunt \r\nmollit anim id est laborum','2012-08-10 10:15:07','2012-08-23 15:15:29',NULL),(65,20,'es_ES','Amanda','York y queso<br>','<b>Lorem ipsum </b>dolor sit amet, consectetur adipisicing elit, sed do eiusmod\r\n tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim \r\nveniam, quis nostrud exercitation ullamco <i>laboris </i>nisi ut aliquip ex ea \r\ncommodo consequat. Duis aute irure dolor in reprehenderit in voluptate \r\nvelit esse cillum dolore eu fugiat nulla <u>pariatur</u>. Excepteur sint \r\noccaecat cupidatat non proident, sunt in culpa qui officia deserunt \r\nmollit anim id est laborum','2012-08-10 10:15:31','2012-08-23 15:15:22',NULL),(66,21,'en_EN','Amelie','Tuna, cheese and tomato','Sed ut perspiciatis unde omnis iste natus error sit voluptatem \r\naccusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab\r\n illo inventore veritatis et quasi architecto beatae vitae dicta sunt \r\nexplicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut \r\nodit aut fugit, sed quia consequuntur magni dolores eos qui ratione \r\nvoluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum \r\nquia dolor sit amet, consectetur, adipisci velit, sed quia non numquam \r\neius modi tempora incidunt ut labore et dolore magnam aliquam quaerat \r\nvoluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam \r\ncorporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur?\r\n Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse \r\nquam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo \r\nvoluptas nulla pariatur','2012-08-23 14:43:50','2012-08-23 15:15:51',NULL),(67,21,'es_ES','Amelie','Tuna, queso y tomate','Sed ut perspiciatis unde omnis iste natus error sit voluptatem \r\naccusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab\r\n illo inventore veritatis et quasi architecto beatae vitae dicta sunt \r\nexplicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut \r\nodit aut fugit, sed quia consequuntur magni dolores eos qui ratione \r\nvoluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum \r\nquia dolor sit amet, consectetur, adipisci velit, sed quia non numquam \r\neius modi tempora incidunt ut labore et dolore magnam aliquam quaerat \r\nvoluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam \r\ncorporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur?\r\n Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse \r\nquam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo \r\nvoluptas nulla pariatur','2012-08-23 14:44:14','2012-08-23 15:16:00',NULL),(68,21,'it_IT','Amelie','Tonno, formaggio e pomodoro','Sed ut perspiciatis unde omnis iste natus error sit voluptatem \r\naccusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab\r\n illo inventore veritatis et quasi architecto beatae vitae dicta sunt \r\nexplicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut \r\nodit aut fugit, sed quia consequuntur magni dolores eos qui ratione \r\nvoluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum \r\nquia dolor sit amet, consectetur, adipisci velit, sed quia non numquam \r\neius modi tempora incidunt ut labore et dolore magnam aliquam quaerat \r\nvoluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam \r\ncorporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur?\r\n Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse \r\nquam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo \r\nvoluptas nulla pariatur','2012-08-23 14:44:43','2012-08-23 15:16:08',NULL);
/*!40000 ALTER TABLE `product_2_language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `temp`
--

DROP TABLE IF EXISTS `temp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temp` (
  `id` int(10) NOT NULL auto_increment,
  `text1` varchar(50) default '0',
  `text2` varbinary(50) default '0',
  `text3` blob,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `temp`
--

LOCK TABLES `temp` WRITE;
/*!40000 ALTER TABLE `temp` DISABLE KEYS */;
INSERT INTO `temp` VALUES (1,'Русский','Русский','Русский'),(2,'Česky','Česky','Česky');
/*!40000 ALTER TABLE `temp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `_id` int(10) unsigned NOT NULL auto_increment,
  `email` varchar(250) NOT NULL,
  `pwd` varchar(32) NOT NULL,
  `timezone` varchar(35) default NULL,
  `nickname` varchar(20) NOT NULL,
  `creation_date` timestamp NULL default NULL,
  `update_date` timestamp NULL default NULL,
  `id_lang` varchar(5) default NULL,
  `deleted` timestamp NULL default NULL,
  `firstname` varchar(50) NOT NULL default '',
  `lastname` varchar(50) NOT NULL default '',
  `type` tinyint(4) default '0',
  `active` tinyint(3) unsigned NOT NULL default '0',
  `id_domain` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`_id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `nickname` (`nickname`),
  KEY `FK_user_language` (`id_lang`),
  KEY `FK_user2` (`id_domain`),
  CONSTRAINT `FK_user2` FOREIGN KEY (`id_domain`) REFERENCES `domain` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_user_language` FOREIGN KEY (`id_lang`) REFERENCES `language` (`label`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3,'admin@oxybay.com','21232f297a57a5a743894a0e4a801fc3','Europe/London','SysAdmin','2012-04-20 22:48:42','2012-07-26 13:06:31','it_IT',NULL,'Pippo','De Pini',10,1,1),(4,'admin2@oxybay.com','21232f297a57a5a743894a0e4a801fc3','Europe/Berlin','admin1','2012-06-18 08:52:12','2012-07-05 12:54:20','en_EN',NULL,'Paolosdad asd ads ad ada dsads','Gioppona da da das ad asda dsa d',0,1,2),(5,'admin3@oxybay.com','86106102400fdcdfc5eee1f24c8f0da7',NULL,'admin2','2012-06-18 16:27:59','2012-07-11 17:49:04','it_IT',NULL,'Flavio Paolo','Micheli',0,1,2),(6,'simone.bonacina@oxybay.com','47eb752bac1c08c75e30d9624b3e58b7',NULL,'simone','2012-06-27 08:26:37','2012-06-27 16:13:37','en_EN',NULL,'Simone','Bonacina',20,1,2),(8,'user1@email.com','24c9e15e52afc47c225b757e7bee1f9d',NULL,'nick1','2012-06-28 13:05:05',NULL,NULL,NULL,'','',0,1,2),(9,'user2@email.com','7e58d63b60197ceb55a1c487989a3720',NULL,'nick2','2012-06-28 13:05:05',NULL,NULL,NULL,'','',0,1,2),(10,'user3@email.com','92877af70a45fd6a2ed7fe81e1236b78',NULL,'nick3','2012-06-28 13:05:05',NULL,NULL,'2012-06-29 12:26:27','','',0,1,2),(11,'user4@email.com','3f02ebe3d7929b091e3d8ccfde2f3bc6',NULL,'nick4','2012-06-28 13:05:05',NULL,NULL,'2012-06-29 12:26:37','','',0,1,2),(12,'user5@email.com','0a791842f52a0acfbb3a783378c066b8',NULL,'nick5','2012-06-28 13:05:05',NULL,NULL,NULL,'','',0,1,2),(13,'user6@email.com','affec3b64cf90492377a8114c86fc093',NULL,'nick6','2012-06-28 13:05:05',NULL,NULL,'2012-06-29 12:26:37','','',0,1,2),(14,'user7@email.com','3e0469fb134991f8f75a2760e409c6ed',NULL,'nick7','2012-06-28 13:05:05',NULL,NULL,NULL,'','',0,1,2),(15,'user8@email.com','7668f673d5669995175ef91b5d171945',NULL,'nick8','2012-06-28 13:05:05',NULL,NULL,NULL,'','',0,1,2),(16,'user9@email.com','8808a13b854c2563da1a5f6cb2130868',NULL,'nick9','2012-06-28 13:05:05',NULL,NULL,'2012-06-29 14:34:59','','',0,1,2),(17,'user10@email.com','990d67a9f94696b1abe2dccf06900322',NULL,'nick10','2012-06-28 13:05:05',NULL,NULL,'2012-06-29 14:32:19','','',0,1,2),(18,'gala1@mailinator.com','4c61cb1773ec5d477f47835b345ad994','Europe/Amsterdam','Gala1','2012-07-11 09:29:21','2012-07-11 09:29:45','en_EN',NULL,'Gala1','Pippone',0,0,2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_permission`
--

DROP TABLE IF EXISTS `user_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_permission` (
  `user_id` int(10) unsigned NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY  (`user_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_permission`
--

LOCK TABLES `user_permission` WRITE;
/*!40000 ALTER TABLE `user_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `view_active_attachment`
--

DROP TABLE IF EXISTS `view_active_attachment`;
/*!50001 DROP VIEW IF EXISTS `view_active_attachment`*/;
/*!50001 CREATE TABLE `view_active_attachment` (
  `img` int(11) unsigned,
  `id_menu` int(11) unsigned
) ENGINE=MyISAM */;

--
-- Table structure for table `zz_valid_connect`
--

DROP TABLE IF EXISTS `zz_valid_connect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zz_valid_connect` (
  `label` varchar(1) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zz_valid_connect`
--

LOCK TABLES `zz_valid_connect` WRITE;
/*!40000 ALTER TABLE `zz_valid_connect` DISABLE KEYS */;
INSERT INTO `zz_valid_connect` VALUES ('1');
/*!40000 ALTER TABLE `zz_valid_connect` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'tablet_menu'
--
DELIMITER ;;
DELIMITER ;

--
-- Final view structure for view `view_active_attachment`
--

/*!50001 DROP TABLE `view_active_attachment`*/;
/*!50001 DROP VIEW IF EXISTS `view_active_attachment`*/;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY INVOKER */
/*!50001 VIEW `view_active_attachment` AS select `menu`.`logo` AS `img`,`menu`.`_id` AS `id_menu` from `menu` where ((`menu`.`logo` is not null) and isnull(`menu`.`deleted`) and (`menu`.`active` = 1)) union select `category`.`img` AS `img`,`category`.`id_menu` AS `id_menu` from `category` where ((`category`.`img` is not null) and isnull(`category`.`deleted`) and (`category`.`active` = 1)) union select `b`.`img` AS `img`,`b`.`id_menu` AS `id_menu` from ((`product` `b` join `product_2_category` `d` on((`b`.`_id` = `d`.`id_product`))) join `category` `e` on(((`d`.`id_category` = `e`.`_id`) and (`e`.`active` = 1) and isnull(`e`.`deleted`)))) where ((`b`.`img` is not null) and (`b`.`active` = 1) and isnull(`b`.`deleted`)) */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-12-31 11:07:38
