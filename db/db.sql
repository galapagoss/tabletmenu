-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.0.91-community-nt - MySQL Community Edition (GPL)
-- Server OS:                    Win32
-- HeidiSQL version:             7.0.0.4053
-- Date/time:                    2012-09-26 10:59:12
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;

-- Dumping database structure for tablet_menu
DROP DATABASE IF EXISTS `tablet_menu`;
CREATE DATABASE IF NOT EXISTS `tablet_menu` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tablet_menu`;


-- Dumping structure for table tablet_menu.attachment
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE IF NOT EXISTS `attachment` (
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

-- Dumping data for table tablet_menu.attachment: ~27 rows (approximately)
/*!40000 ALTER TABLE `attachment` DISABLE KEYS */;
INSERT INTO `attachment` (`_id`, `id_domain`, `path`, `filename`, `filetype`, `width`, `height`, `size`, `filedata`, `creation_date`, `update_date`, `deleted`) VALUES
	(2, 2, '20120726', '9.jpg', 'jpg', 150, 150, 5238, NULL, '2012-07-26 13:20:30', NULL, '2012-07-26 13:52:01'),
	(3, 2, '20120729', 'Anse Source d\'Argent.jpg', 'jpg', 150, 150, 8793, NULL, '2012-07-29 00:07:26', NULL, NULL),
	(4, 2, '20120829', 'dessert.jpg', 'jpg', 340, 150, 41754, NULL, '2012-08-09 17:09:57', '2012-08-29 09:40:58', NULL),
	(5, 2, '20120829', 'antipasti.jpg', 'jpg', 340, 150, 21838, NULL, '2012-08-09 17:12:17', '2012-08-29 08:58:33', NULL),
	(6, 2, '20120809', 'banane-flambe.jpg', 'jpg', 150, 150, 4383, NULL, '2012-08-09 17:30:05', NULL, NULL),
	(7, 2, '20120829', 'spaghetti-al-pomodoro-del-mazzo.jpg', 'jpg', 340, 150, 26408, NULL, '2012-08-09 17:31:24', '2012-08-29 09:47:54', NULL),
	(8, 2, '20120810', 'sacher.jpg', 'jpg', 150, 150, 12327, NULL, '2012-08-10 10:36:29', NULL, NULL),
	(9, 2, '20120810', 'tortamele.jpg', 'jpg', 150, 150, 6244, NULL, '2012-08-10 10:44:50', NULL, NULL),
	(10, 2, '20120810', 'crostata meringata al limone.jpg', 'jpg', 150, 150, 19252, NULL, '2012-08-10 10:49:09', NULL, NULL),
	(11, 2, '20120810', 'cheesecake1.jpg', 'jpg', 150, 150, 36381, NULL, '2012-08-10 10:49:58', NULL, NULL),
	(12, 2, '20120810', 'seafoodsoup.jpg', 'jpg', 150, 150, 16852, NULL, '2012-08-10 11:48:17', NULL, NULL),
	(13, 2, '20120810', 'creme-caramel-1.jpg', 'jpg', 150, 150, 10066, NULL, '2012-08-10 11:50:24', NULL, NULL),
	(14, 2, '20120810', 'mousse-di-cioccolato.jpg', 'jpg', 150, 150, 7952, NULL, '2012-08-10 11:51:18', NULL, NULL),
	(15, 2, '20120810', 'Tiramisu.gif', 'gif', 150, 150, 16292, NULL, '2012-08-10 11:52:43', NULL, NULL),
	(16, 2, '20120810', 'chilisausage.jpg', 'jpg', 150, 150, 27065, NULL, '2012-08-10 11:53:41', NULL, NULL),
	(17, 2, '20120810', 'tuna-carpaccio.jpg', 'jpg', 150, 150, 7546, NULL, '2012-08-10 11:54:34', NULL, NULL),
	(18, 2, '20120810', 'lamb.JPG', 'jpg', 150, 150, 10474, NULL, '2012-08-10 11:55:38', NULL, NULL),
	(19, 2, '20120810', 'linguine.jpg', 'jpg', 150, 150, 9625, NULL, '2012-08-10 11:57:16', NULL, NULL),
	(20, 2, '20120810', 'trotalimone.jpg', 'jpg', 150, 150, 8631, NULL, '2012-08-10 11:58:51', NULL, NULL),
	(21, 2, '20120810', 'polloavocado.jpg', 'jpg', 150, 150, 10555, NULL, '2012-08-10 12:00:15', NULL, NULL),
	(22, 2, '20120810', 'tortillagambas.jpg', 'jpg', 150, 150, 5580, NULL, '2012-08-10 12:11:22', NULL, NULL),
	(23, 2, '20120810', 'bocadillo_york_queso.jpg', 'jpg', 150, 150, 46673, NULL, '2012-08-10 12:15:55', NULL, NULL),
	(24, 2, '20120823', 'piattounico.JPG', 'jpg', 340, 150, 23034, NULL, '2012-08-23 16:17:07', NULL, NULL),
	(25, 2, '20120823', 'panino.jpg', 'jpg', 340, 150, 8034, NULL, '2012-08-23 16:19:25', NULL, NULL),
	(26, 2, '20120823', 'tunasalad.jpg', 'jpg', 480, 360, 45599, NULL, '2012-08-23 16:23:47', NULL, NULL),
	(27, 2, '20120823', 'salmonsalad.jpg', 'jpg', 480, 360, 29891, NULL, '2012-08-23 16:25:09', NULL, NULL),
	(28, 2, '20120823', 'tunasandwich.jpg', 'jpg', 480, 360, 214429, NULL, '2012-08-23 16:47:16', NULL, NULL);
/*!40000 ALTER TABLE `attachment` ENABLE KEYS */;


-- Dumping structure for table tablet_menu.attachment_deleted
DROP TABLE IF EXISTS `attachment_deleted`;
CREATE TABLE IF NOT EXISTS `attachment_deleted` (
  `_id` int(10) unsigned NOT NULL auto_increment,
  `id_domain` int(10) unsigned NOT NULL,
  `path` varchar(255) NOT NULL default '',
  `filename` varchar(100) NOT NULL default '',
  `deleted` timestamp NULL default NULL,
  PRIMARY KEY  (`_id`),
  KEY `FK_attachment_deleted1` (`id_domain`),
  CONSTRAINT `FK_attachment_deleted1` FOREIGN KEY (`id_domain`) REFERENCES `domain` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Dumping data for table tablet_menu.attachment_deleted: ~1 rows (approximately)
/*!40000 ALTER TABLE `attachment_deleted` DISABLE KEYS */;
INSERT INTO `attachment_deleted` (`_id`, `id_domain`, `path`, `filename`, `deleted`) VALUES
	(2, 2, '20120809', 'piatto-pasta-300.jpg', '2012-08-29 09:47:54');
/*!40000 ALTER TABLE `attachment_deleted` ENABLE KEYS */;


-- Dumping structure for table tablet_menu.category
DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
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

-- Dumping data for table tablet_menu.category: ~14 rows (approximately)
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`_id`, `id_menu`, `img`, `active`, `creation_date`, `update_date`, `deleted`, `item_order`) VALUES
	(1, 3, 5, 1, '2012-07-18 20:25:21', '2012-09-12 08:06:39', NULL, 1),
	(2, 3, 24, 1, '2012-07-18 20:25:30', '2012-08-23 16:17:07', NULL, 2),
	(3, 3, NULL, 1, '2012-07-18 20:25:41', NULL, '2012-07-22 11:47:49', 4),
	(8, 3, NULL, 0, '2012-07-19 22:48:36', '2012-07-20 22:27:55', '2012-07-22 11:49:47', 3),
	(9, 3, NULL, 0, '2012-07-22 12:48:01', NULL, '2012-07-22 11:49:43', 5),
	(10, 3, NULL, 0, '2012-07-22 12:49:54', NULL, '2012-07-22 12:52:48', 6),
	(11, 3, NULL, 0, '2012-07-22 12:53:45', NULL, '2012-07-22 12:52:48', 7),
	(12, 3, NULL, 0, '2012-07-22 12:55:33', NULL, '2012-07-22 12:52:48', 8),
	(13, 3, NULL, 0, '2012-07-22 12:58:26', NULL, '2012-07-22 12:52:48', 9),
	(14, 3, NULL, 0, '2012-07-22 12:59:49', NULL, '2012-07-22 12:52:48', 10),
	(15, 3, NULL, 0, '2012-07-22 13:51:32', '2012-08-10 11:33:06', '2012-08-10 12:02:02', 11),
	(16, 3, 4, 1, '2012-08-09 17:09:57', '2012-08-29 09:40:58', NULL, 12),
	(17, 3, 7, 1, '2012-08-09 17:21:00', '2012-08-29 09:47:54', NULL, 13),
	(18, 3, 25, 1, '2012-08-10 11:41:07', '2012-08-23 16:19:25', NULL, 14);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;


-- Dumping structure for table tablet_menu.category_2_language
DROP TABLE IF EXISTS `category_2_language`;
CREATE TABLE IF NOT EXISTS `category_2_language` (
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- Dumping data for table tablet_menu.category_2_language: ~20 rows (approximately)
/*!40000 ALTER TABLE `category_2_language` DISABLE KEYS */;
INSERT INTO `category_2_language` (`_id`, `id_category`, `lang`, `title`, `subtitle`, `body`, `creation_date`, `update_date`, `deleted`) VALUES
	(1, 1, 'en_EN', 'Starters', 'Starting <b>tasty</b>', ' ', '2012-07-18 20:26:16', '2012-08-23 16:48:37', NULL),
	(2, 1, 'it_IT', 'Antipasti', 'Per iniziare con <b>gusto</b>', ' ', '2012-07-18 20:26:37', '2012-08-23 16:48:57', NULL),
	(3, 1, 'es_ES', 'Entrantes', 'Para empezar con <b>gusto</b>', ' ', '2012-07-18 20:27:01', '2012-08-23 16:48:48', NULL),
	(4, 2, 'en_EN', 'Main Course', ' ', ' ', '2012-07-19 10:04:48', NULL, NULL),
	(5, 1, 'de_DE', 'SprichMatz', 'bla ', 'bla', '2012-07-22 11:36:15', '2012-09-10 17:57:51', '2012-09-10 18:02:36'),
	(6, 8, 'en_EN', 'Special', '', '', '2012-07-22 11:37:41', NULL, NULL),
	(7, 14, 'en_EN', 'dddd', '', '', '2012-07-22 11:59:56', NULL, NULL),
	(8, 15, 'en_EN', 'awcac', '', '', '2012-07-22 12:51:35', NULL, NULL),
	(9, 16, 'en_EN', 'Desserts', '<i><span style="color: rgb(49, 45, 46); font-family: Arial; line-height: 18px; text-align: justify; ">The freshest and finest cakes and delights</span>&nbsp;</i><br>', '<br>', '2012-08-09 17:10:10', '2012-08-23 16:59:36', NULL),
	(10, 16, 'it_IT', 'Dolci', '<i>Le delizie più fresche e buone</i><br>', '<br>', '2012-08-09 17:11:13', '2012-08-23 17:02:48', NULL),
	(11, 17, 'en_EN', 'Pasta', 'A lot of different pastas with <u>the tastiest sauces</u>&nbsp;and blalnlallanhddnh mnanhhddbvhdb, nnfchhh &nbsp;kiughvbdbd. aggagagagshshhsh, mjhcgcbvcvgdhdbbsbb hfg mfhhdb kj!!&nbsp;', '<br>', '2012-08-09 17:21:06', '2012-08-23 17:09:22', NULL),
	(12, 17, 'it_IT', 'Pasta', 'Tanti tipi di pasta con le più <u>deliziose salse</u>&nbsp;\r\nand blalnlallanhddnh mnanhhddbvhdb, nnfchhh &nbsp;kiughvbdbd. aggagagagshshhsh, mjhcgcbvcvgdhdbbsbb hfg mfhhdb kj!!&nbsp;', '<br>', '2012-08-09 17:26:44', '2012-08-23 17:10:09', NULL),
	(13, 17, 'es_ES', 'Pasta', 'Muchos tipos de pastas con&nbsp;<u>las salsas màs sabrosas</u>&nbsp;\r\nand blalnlallanhddnh mnanhhddbvhdb, nnfchhh &nbsp;kiughvbdbd. aggagagagshshhsh, mjhcgcbvcvgdhdbbsbb hfg mfhhdb kj!!&nbsp;', '<br>', '2012-08-09 17:26:55', '2012-08-23 17:09:52', NULL),
	(14, 16, 'es_ES', 'Postres', '<i>Los dulces màs frescos y gustosos</i>', '<br>', '2012-08-09 17:27:09', '2012-08-23 17:06:35', NULL),
	(15, 18, 'en_EN', 'Sandwiches', '<font face="impact">Big tasty sandwiches</font>', '<br>', '2012-08-10 11:41:17', '2012-08-23 17:13:14', NULL),
	(16, 18, 'it_IT', 'Panini', '<span style="font-family: impact; ">Big tasty sandwiches</span>&nbsp;<br>', '<br>', '2012-08-10 11:41:32', '2012-08-23 17:13:39', NULL),
	(17, 18, 'es_ES', 'Bocadillos', '<span style="font-family: impact; ">Big tasty sandwiches</span>&nbsp;<br>', '<br>', '2012-08-10 11:41:44', '2012-08-23 17:13:31', NULL),
	(18, 2, 'it_IT', 'Piatto unico', '<br>', '<br>', '2012-08-23 16:14:26', NULL, NULL),
	(19, 2, 'es_ES', 'Platos principales', '<br>', '<br>', '2012-08-23 16:22:13', NULL, NULL),
	(20, 1, 'da_DA', 'xx', '<br>', '<br>', '2012-09-10 18:02:22', NULL, '2012-09-10 18:02:29');
/*!40000 ALTER TABLE `category_2_language` ENABLE KEYS */;


-- Dumping structure for table tablet_menu.category_2_tag
DROP TABLE IF EXISTS `category_2_tag`;
CREATE TABLE IF NOT EXISTS `category_2_tag` (
  `id_category` int(10) unsigned NOT NULL,
  `id_tag` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`id_category`,`id_tag`),
  KEY `id_tag` (`id_tag`),
  CONSTRAINT `category_2_tag_ibfk_1` FOREIGN KEY (`id_category`) REFERENCES `category` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `category_2_tag_ibfk_2` FOREIGN KEY (`id_tag`) REFERENCES `tag` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table tablet_menu.category_2_tag: ~1 rows (approximately)
/*!40000 ALTER TABLE `category_2_tag` DISABLE KEYS */;
INSERT INTO `category_2_tag` (`id_category`, `id_tag`) VALUES
	(1, 5);
/*!40000 ALTER TABLE `category_2_tag` ENABLE KEYS */;


-- Dumping structure for table tablet_menu.currency
DROP TABLE IF EXISTS `currency`;
CREATE TABLE IF NOT EXISTS `currency` (
  `_id` int(10) unsigned NOT NULL auto_increment,
  `label` varchar(3) NOT NULL default '',
  `symbol` varchar(3) NOT NULL default '',
  `symbol_html` varchar(6) NOT NULL default '',
  `struts_format` varchar(20) NOT NULL default '',
  `digit` tinyint(3) unsigned NOT NULL default '0',
  `separator_decimal` varchar(1) NOT NULL default '',
  `separator_thousand` varchar(1) NOT NULL default '',
  PRIMARY KEY  (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table tablet_menu.currency: ~4 rows (approximately)
/*!40000 ALTER TABLE `currency` DISABLE KEYS */;
INSERT INTO `currency` (`_id`, `label`, `symbol`, `symbol_html`, `struts_format`, `digit`, `separator_decimal`, `separator_thousand`) VALUES
	(1, 'EUR', '€', '&#128;', '{0,number,##0.00}', 2, ',', '\''),
	(2, 'USD', '$', '&#36;', '{0,number,##0.00}', 2, '.', ','),
	(3, 'GBP', '£', '&#163;', '{0,number,##0.00}', 2, '.', ','),
	(4, 'YEN', '¥', '&#165;', '{0,number,##0}', 0, '', ',');
/*!40000 ALTER TABLE `currency` ENABLE KEYS */;


-- Dumping structure for table tablet_menu.device
DROP TABLE IF EXISTS `device`;
CREATE TABLE IF NOT EXISTS `device` (
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

-- Dumping data for table tablet_menu.device: ~1 rows (approximately)
/*!40000 ALTER TABLE `device` DISABLE KEYS */;
INSERT INTO `device` (`id`, `domain`, `device_id`, `creation_date`, `version`, `last_update`, `md5_key`, `deleted`) VALUES
	(1, 2, 'APA91bE9203BFxZYcO6v5aHn7V__ld5UVDPKkCRJ5_z89tCnalw6ARWv7v1tt8_fvVexBnaVzvOXwdzyCeG8sjRu5AIoYYuUZ02G84-gOpoVQuGqOKz9CqO6k0KsQyNmDUBunpomyh8vK9hzUvfzKoATLkoFHWkEvQ', '2012-09-05 21:28:26', NULL, '2012-09-05 21:28:26', 'dpThy7Xu2h', NULL);
/*!40000 ALTER TABLE `device` ENABLE KEYS */;


-- Dumping structure for table tablet_menu.device_action
DROP TABLE IF EXISTS `device_action`;
CREATE TABLE IF NOT EXISTS `device_action` (
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

-- Dumping data for table tablet_menu.device_action: ~29 rows (approximately)
/*!40000 ALTER TABLE `device_action` DISABLE KEYS */;
INSERT INTO `device_action` (`id`, `id_device`, `action`, `action_date`, `custom_data`, `success`) VALUES
	(1, 1, 1, '2012-09-05 21:28:26', '', 1),
	(2, 1, 2, '2012-09-05 21:28:26', '0|1', 1),
	(3, 1, 5, '2012-09-05 21:28:27', '1_deploy.sql', 1),
	(4, 1, 5, '2012-09-05 21:28:27', '1_attach.zip', 1),
	(5, 1, 5, '2012-09-05 21:28:28', '1_cmd.txt', 1),
	(6, 1, 4, '2012-09-05 21:37:29', '', 1),
	(7, 1, 1, '2012-09-05 21:40:04', '', 1),
	(8, 1, 2, '2012-09-05 21:40:04', '0|1', 1),
	(9, 1, 5, '2012-09-05 21:40:05', '1_deploy.sql', 1),
	(10, 1, 5, '2012-09-05 21:40:05', '1_attach.zip', 1),
	(11, 1, 5, '2012-09-05 21:40:05', '1_cmd.txt', 1),
	(12, 1, 4, '2012-09-05 21:41:07', '', 1),
	(13, 1, 1, '2012-09-05 21:42:05', '', 1),
	(14, 1, 2, '2012-09-05 21:42:05', '0|1', 1),
	(15, 1, 5, '2012-09-05 21:42:06', '1_deploy.sql', 1),
	(16, 1, 5, '2012-09-05 21:42:06', '1_attach.zip', 1),
	(17, 1, 5, '2012-09-05 21:42:07', '1_cmd.txt', 1),
	(18, 1, 4, '2012-09-05 21:44:29', '', 1),
	(19, 1, 1, '2012-09-05 21:44:48', '', 1),
	(20, 1, 2, '2012-09-05 21:44:48', '0|1', 1),
	(21, 1, 5, '2012-09-05 21:44:49', '1_deploy.sql', 1),
	(22, 1, 5, '2012-09-05 21:44:50', '1_attach.zip', 1),
	(23, 1, 5, '2012-09-05 21:44:50', '1_cmd.txt', 1),
	(24, 1, 4, '2012-09-05 21:50:13', '', 1),
	(25, 1, 1, '2012-09-05 21:50:25', '', 1),
	(26, 1, 2, '2012-09-05 21:50:25', '0|1', 1),
	(27, 1, 5, '2012-09-05 21:50:28', '1_deploy.sql', 1),
	(28, 1, 5, '2012-09-05 21:50:28', '1_attach.zip', 1),
	(29, 1, 5, '2012-09-05 21:50:28', '1_cmd.txt', 1);
/*!40000 ALTER TABLE `device_action` ENABLE KEYS */;


-- Dumping structure for table tablet_menu.discount_policy
DROP TABLE IF EXISTS `discount_policy`;
CREATE TABLE IF NOT EXISTS `discount_policy` (
  `id` bigint(20) unsigned NOT NULL auto_increment,
  `recurring` bit(7) NOT NULL COMMENT 'Meno significativa = lunedi',
  `fromtime` time NOT NULL default '00:00:00',
  `totime` time NOT NULL default '23:59:59',
  `fixed_price` decimal(10,2) default NULL,
  `perc_discount` tinyint(3) NOT NULL default '0',
  `active` tinyint(3) unsigned NOT NULL default '1',
  `priority_order` int(10) unsigned NOT NULL default '1',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- Dumping data for table tablet_menu.discount_policy: ~5 rows (approximately)
/*!40000 ALTER TABLE `discount_policy` DISABLE KEYS */;
INSERT INTO `discount_policy` (`id`, `recurring`, `fromtime`, `totime`, `fixed_price`, `perc_discount`, `active`, `priority_order`) VALUES
	(13, b'00000110', '20:00:00', '00:00:00', -1.00, -7, 1, 1),
	(14, b'00011000', '00:00:00', '00:00:00', 10.00, 0, 0, 2),
	(15, b'00000110', '20:00:00', '05:00:00', -1.00, 10, 1, 1),
	(16, b'10010000', '14:00:00', '20:00:00', -1.00, -10, 0, 2),
	(17, b'00001000', '00:00:00', '23:59:00', 5.30, 0, 1, 3);
/*!40000 ALTER TABLE `discount_policy` ENABLE KEYS */;


-- Dumping structure for table tablet_menu.domain
DROP TABLE IF EXISTS `domain`;
CREATE TABLE IF NOT EXISTS `domain` (
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

-- Dumping data for table tablet_menu.domain: ~3 rows (approximately)
/*!40000 ALTER TABLE `domain` DISABLE KEYS */;
INSERT INTO `domain` (`_id`, `label`, `description`, `system`, `active`, `creation_date`, `device_key`, `unlock_key`) VALUES
	(1, 'SYS', 'System Domain Shared', 1, 1, '2012-07-12 16:56:31', '', ''),
	(2, 'OXY', 'OxyBay Consulting S.L.', 0, 1, '2012-07-12 16:56:31', 'fvhssi', '9687'),
	(3, 'DOT', 'Dotland Srl', 0, 0, '2012-07-12 18:44:11', '', '');
/*!40000 ALTER TABLE `domain` ENABLE KEYS */;


-- Dumping structure for table tablet_menu.language
DROP TABLE IF EXISTS `language`;
CREATE TABLE IF NOT EXISTS `language` (
  `label` varchar(5) NOT NULL,
  `description` varchar(50) NOT NULL default '',
  `active` tinyint(4) NOT NULL default '0',
  `application` tinyint(4) NOT NULL default '0',
  `_id` int(10) NOT NULL auto_increment,
  PRIMARY KEY  (`label`),
  UNIQUE KEY `_id` (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- Dumping data for table tablet_menu.language: ~14 rows (approximately)
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` (`label`, `description`, `active`, `application`, `_id`) VALUES
	('cs_CS', 'Česky', 1, 0, 1),
	('da_DA', 'Dansk', 1, 0, 2),
	('de_DE', 'Deutsch', 1, 1, 3),
	('en_EN', 'English', 1, 1, 4),
	('es_ES', 'Español', 1, 1, 5),
	('fi_FI', 'Suomi', 1, 0, 6),
	('fr_FR', 'Français', 1, 1, 7),
	('hu_HU', 'Hrvatski', 1, 0, 8),
	('it_IT', 'Italiano', 1, 1, 9),
	('nl_NL', 'Nederlands', 1, 0, 10),
	('no_NO', 'Norsk', 1, 0, 11),
	('pt_PT', 'Português', 1, 1, 12),
	('ru_ru', 'Русский', 1, 0, 14),
	('sv_SV', 'Svenska', 1, 0, 13);
/*!40000 ALTER TABLE `language` ENABLE KEYS */;


-- Dumping structure for table tablet_menu.menu
DROP TABLE IF EXISTS `menu`;
CREATE TABLE IF NOT EXISTS `menu` (
  `_id` int(10) unsigned NOT NULL auto_increment,
  `domain` int(10) unsigned NOT NULL,
  `title` varchar(100) NOT NULL,
  `subtitle` text NOT NULL,
  `body` text NOT NULL,
  `logo` int(10) unsigned default NULL,
  `id_currency` int(10) unsigned NOT NULL,
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
  KEY `FK_menu5` (`id_currency`),
  CONSTRAINT `FK2_menu2` FOREIGN KEY (`last_version`) REFERENCES `menu_version` (`_id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `FK_menu1` FOREIGN KEY (`domain`) REFERENCES `domain` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_menu3` FOREIGN KEY (`logo`) REFERENCES `attachment` (`_id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `FK_menu4` FOREIGN KEY (`main_lang`) REFERENCES `language` (`label`),
  CONSTRAINT `FK_menu5` FOREIGN KEY (`id_currency`) REFERENCES `currency` (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table tablet_menu.menu: ~1 rows (approximately)
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` (`_id`, `domain`, `title`, `subtitle`, `body`, `logo`, `id_currency`, `active`, `creation_date`, `update_date`, `deleted`, `last_version`, `main_lang`) VALUES
	(3, 2, 'Menu senza titolo', '<br>', '<br>', 3, 1, 1, '2012-08-01 19:34:37', '2012-08-12 10:44:25', NULL, 3, 'en_EN');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;


-- Dumping structure for table tablet_menu.menu_2_language
DROP TABLE IF EXISTS `menu_2_language`;
CREATE TABLE IF NOT EXISTS `menu_2_language` (
  `_id` int(10) unsigned NOT NULL auto_increment,
  `id_menu` int(10) unsigned NOT NULL,
  `lang` varchar(5) NOT NULL,
  PRIMARY KEY  (`_id`),
  KEY `FK_menu_2_language1` (`id_menu`),
  KEY `FK_menu_2_language2` (`lang`),
  CONSTRAINT `FK_menu_2_language1` FOREIGN KEY (`id_menu`) REFERENCES `menu` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_menu_2_language2` FOREIGN KEY (`lang`) REFERENCES `language` (`label`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- Dumping data for table tablet_menu.menu_2_language: ~3 rows (approximately)
/*!40000 ALTER TABLE `menu_2_language` DISABLE KEYS */;
INSERT INTO `menu_2_language` (`_id`, `id_menu`, `lang`) VALUES
	(16, 3, 'it_IT'),
	(17, 3, 'es_ES'),
	(18, 3, 'en_EN');
/*!40000 ALTER TABLE `menu_2_language` ENABLE KEYS */;


-- Dumping structure for table tablet_menu.menu_version
DROP TABLE IF EXISTS `menu_version`;
CREATE TABLE IF NOT EXISTS `menu_version` (
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

-- Dumping data for table tablet_menu.menu_version: ~1 rows (approximately)
/*!40000 ALTER TABLE `menu_version` DISABLE KEYS */;
INSERT INTO `menu_version` (`_id`, `id_menu`, `id_domain`, `version`, `file_sql`, `file_sql_size`, `file_zip`, `file_zip_size`, `file_op`, `file_op_size`, `creation_date`, `deploy_end`) VALUES
	(3, 3, 2, 1, '1_deploy.sql', 57828, '1_attach.zip', 605316, '1_cmd.txt', 32, '2012-09-05 21:39:41', '2012-09-05 21:39:41');
/*!40000 ALTER TABLE `menu_version` ENABLE KEYS */;


-- Dumping structure for table tablet_menu.product
DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
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

-- Dumping data for table tablet_menu.product: ~21 rows (approximately)
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`_id`, `id_menu`, `img`, `active`, `price`, `creation_date`, `update_date`, `deleted`, `item_order`) VALUES
	(1, 3, 11, 1, 6.50, '2012-07-22 22:02:43', '2012-09-25 15:35:42', NULL, 1),
	(2, 3, 16, 1, 11.00, '2012-07-22 22:03:01', '2012-09-25 14:41:55', NULL, 2),
	(3, 3, 6, 1, 6.00, '2012-07-23 16:19:55', '2012-09-25 14:41:55', NULL, 3),
	(4, 3, 17, 1, 9.00, '2012-08-09 17:18:29', '2012-09-25 14:41:55', NULL, 10),
	(5, 3, 19, 1, 8.50, '2012-08-09 17:21:30', '2012-09-25 14:41:55', NULL, 4),
	(6, 3, 21, 1, 10.00, '2012-08-09 17:25:17', '2012-09-25 14:41:55', NULL, 5),
	(7, 3, 26, 1, 8.00, '2012-08-09 17:32:07', '2012-09-26 09:18:17', NULL, 6),
	(8, 3, 14, 1, 6.00, '2012-08-09 17:33:55', '2012-09-25 14:41:55', NULL, 7),
	(9, 3, 15, 1, 6.00, '2012-08-10 10:23:05', '2012-09-25 14:41:55', NULL, 8),
	(10, 3, 8, 1, 6.00, '2012-08-10 10:33:05', '2012-09-25 14:41:55', NULL, 9),
	(11, 3, 9, 1, 6.00, '2012-08-10 10:42:05', '2012-08-10 10:44:50', NULL, 11),
	(12, 3, 10, 1, 6.00, '2012-08-10 10:45:32', '2012-08-10 10:49:09', NULL, 12),
	(13, 3, 18, 1, 8.50, '2012-08-10 10:50:59', '2012-08-10 11:55:38', NULL, 13),
	(14, 3, 20, 1, 9.00, '2012-08-10 11:02:48', '2012-08-10 11:58:51', NULL, 14),
	(15, 3, NULL, 0, 9.00, '2012-08-10 11:13:15', '2012-08-10 11:40:06', '2012-08-10 12:05:19', 15),
	(16, 3, 27, 1, 9.00, '2012-08-10 11:15:09', '2012-08-23 16:25:09', NULL, 16),
	(17, 3, 13, 1, 6.00, '2012-08-10 11:44:36', '2012-08-10 11:50:24', NULL, 17),
	(18, 3, 12, 0, 7.00, '2012-08-10 11:46:04', '2012-09-04 20:40:58', NULL, 18),
	(19, 3, 22, 1, 6.00, '2012-08-10 12:01:34', '2012-08-10 12:11:22', NULL, 19),
	(20, 3, 23, 1, 6.00, '2012-08-10 12:14:33', '2012-08-23 16:34:29', NULL, 20),
	(21, 3, 28, 1, 6.50, '2012-08-23 16:41:55', '2012-08-23 16:47:16', NULL, 21);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;


-- Dumping structure for table tablet_menu.product_2_category
DROP TABLE IF EXISTS `product_2_category`;
CREATE TABLE IF NOT EXISTS `product_2_category` (
  `id_product` int(10) unsigned NOT NULL,
  `id_category` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`id_product`,`id_category`),
  KEY `FK_product_2_category1` (`id_category`),
  CONSTRAINT `FK_product_2_category1` FOREIGN KEY (`id_category`) REFERENCES `category` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_product_2_category2` FOREIGN KEY (`id_product`) REFERENCES `product` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table tablet_menu.product_2_category: ~19 rows (approximately)
/*!40000 ALTER TABLE `product_2_category` DISABLE KEYS */;
INSERT INTO `product_2_category` (`id_product`, `id_category`) VALUES
	(4, 1),
	(7, 1),
	(15, 1),
	(16, 1),
	(18, 1),
	(19, 1),
	(6, 2),
	(13, 2),
	(14, 2),
	(1, 16),
	(8, 16),
	(9, 16),
	(10, 16),
	(11, 16),
	(12, 16),
	(17, 16),
	(5, 17),
	(20, 18),
	(21, 18);
/*!40000 ALTER TABLE `product_2_category` ENABLE KEYS */;


-- Dumping structure for table tablet_menu.product_2_discount
DROP TABLE IF EXISTS `product_2_discount`;
CREATE TABLE IF NOT EXISTS `product_2_discount` (
  `id_product` int(10) unsigned NOT NULL,
  `id_discount` bigint(20) unsigned NOT NULL,
  PRIMARY KEY  (`id_product`,`id_discount`),
  KEY `FK_product_2_discount2` (`id_discount`),
  CONSTRAINT `FK_product_2_discount1` FOREIGN KEY (`id_product`) REFERENCES `product` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_product_2_discount2` FOREIGN KEY (`id_discount`) REFERENCES `discount_policy` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table tablet_menu.product_2_discount: ~5 rows (approximately)
/*!40000 ALTER TABLE `product_2_discount` DISABLE KEYS */;
INSERT INTO `product_2_discount` (`id_product`, `id_discount`) VALUES
	(1, 13),
	(1, 14),
	(7, 15),
	(7, 16),
	(7, 17);
/*!40000 ALTER TABLE `product_2_discount` ENABLE KEYS */;


-- Dumping structure for table tablet_menu.product_2_language
DROP TABLE IF EXISTS `product_2_language`;
CREATE TABLE IF NOT EXISTS `product_2_language` (
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
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8;

-- Dumping data for table tablet_menu.product_2_language: ~64 rows (approximately)
/*!40000 ALTER TABLE `product_2_language` DISABLE KEYS */;
INSERT INTO `product_2_language` (`_id`, `id_product`, `lang`, `title`, `subtitle`, `body`, `creation_date`, `update_date`, `deleted`) VALUES
	(9, 1, 'en_EN', 'CheeseCake', ' ', ' ', '2012-07-22 22:04:01', NULL, NULL),
	(10, 1, 'it_IT', 'Cheese Cake', ' ', ' ', '2012-07-22 22:04:25', '2012-08-10 10:25:41', NULL),
	(11, 2, 'en_EN', 'Chili Sausage', ' ', ' ', '2012-07-22 22:05:10', NULL, NULL),
	(12, 3, 'en_EN', 'Banana Flambe', 'aa', 'dd', '2012-07-23 15:30:30', '2012-09-10 17:04:11', NULL),
	(13, 3, 'it_IT', 'Banane alla fiamma', 's<br>', '<br>', '2012-08-02 19:12:25', '2012-09-10 16:36:03', NULL),
	(14, 4, 'en_EN', 'Tuna Carpaccio', '<br>', '<br>', '2012-08-09 17:18:39', NULL, NULL),
	(15, 4, 'it_IT', 'Carpaccio di tonno', 'asdadsaaa<br>', '<br>', '2012-08-09 17:19:01', '2012-09-10 16:36:48', NULL),
	(16, 4, 'es_ES', 'Carpaccio de atun', '<br>', '<br>', '2012-08-09 17:19:40', NULL, NULL),
	(17, 5, 'en_EN', 'Mushroom Linguini', '<br>', '<br>', '2012-08-09 17:21:53', NULL, NULL),
	(18, 5, 'it_IT', 'Linguine ai funghi', '<br>', '<br>', '2012-08-09 17:22:12', '2012-08-09 17:22:17', NULL),
	(19, 6, 'en_EN', 'Avocado Chicken', '<br>', '<br>', '2012-08-09 17:25:27', NULL, NULL),
	(20, 6, 'it_IT', 'Pollo con avogado', '<br>', '<br>', '2012-08-09 17:25:50', NULL, NULL),
	(21, 7, 'en_EN', 'Tuna Salad', '<br>', '<br>', '2012-08-09 17:32:17', NULL, NULL),
	(22, 7, 'it_IT', 'Insalata di tonno', '<br>', '<br>', '2012-08-09 17:32:33', NULL, NULL),
	(23, 7, 'es_ES', 'Ensalada de atun', '<br>', '<br>', '2012-08-09 17:32:53', NULL, NULL),
	(24, 8, 'en_EN', 'Chocolate Mousse', '<br>', '<br>', '2012-08-09 17:34:03', NULL, NULL),
	(25, 8, 'it_IT', 'Mousse al cioccolato', '<br>', '<br>', '2012-08-09 17:34:19', NULL, NULL),
	(26, 9, 'en_EN', 'Homemade Tiramisu', '<br>', '<br>', '2012-08-10 10:23:22', NULL, NULL),
	(27, 9, 'it_IT', 'Tiramisu ', '<br>', '<br>', '2012-08-10 10:24:02', NULL, NULL),
	(28, 8, 'es_ES', 'Mousse de chocolate', '<br>', '<br>', '2012-08-10 10:24:38', NULL, NULL),
	(29, 2, 'it_IT', 'Salsiccia al chili', 'aa<br>', '<br>', '2012-08-10 10:28:46', '2012-09-10 16:30:33', NULL),
	(30, 10, 'en_EN', 'Sacher Torte', '<br>', '<br>', '2012-08-10 10:34:46', NULL, NULL),
	(31, 10, 'it_IT', 'Sacher Torte', '<br>', '<br>', '2012-08-10 10:35:03', NULL, NULL),
	(32, 10, 'es_ES', 'Sacher Torte', '<br>', '<br>', '2012-08-10 10:35:16', NULL, NULL),
	(33, 11, 'en_EN', 'Apple Pie', '<br>', '<br>', '2012-08-10 10:42:12', NULL, NULL),
	(34, 11, 'it_IT', 'Torta di mele', '<br>', '<br>', '2012-08-10 10:42:50', NULL, NULL),
	(35, 11, 'es_ES', 'Tarta de manzana', '<br>', '<br>', '2012-08-10 10:43:49', NULL, NULL),
	(36, 12, 'en_EN', 'Lemon meringue', '<br>', '<br>', '2012-08-10 10:45:38', NULL, NULL),
	(37, 12, 'it_IT', 'Meringata al limone', '<br>', '<br>', '2012-08-10 10:45:54', NULL, NULL),
	(38, 12, 'es_ES', 'Tarta merengue y limòn', '<br>', '<br>', '2012-08-10 10:47:45', NULL, NULL),
	(39, 13, 'en_EN', 'Lamb in a mint sauce', '<br>', '<br>', '2012-08-10 10:51:14', NULL, NULL),
	(40, 13, 'it_IT', 'Agnello in salsa di menta', '<br>', '<br>', '2012-08-10 10:52:09', NULL, NULL),
	(41, 13, 'es_ES', 'Cordero en salsa de menta', '<br>', '<br>', '2012-08-10 10:54:34', NULL, NULL),
	(42, 1, 'es_ES', 'Tarta de queso', '<br>', '<br>', '2012-08-10 10:55:22', NULL, NULL),
	(43, 5, 'es_ES', 'Linguini con setas', '<br>', '<br>', '2012-08-10 10:56:14', NULL, NULL),
	(44, 6, 'es_ES', 'Pollo con aguacate', '<br>', '<br>', '2012-08-10 10:57:55', NULL, NULL),
	(45, 9, 'es_ES', 'Tiramisu ', '<br>', '<br>', '2012-08-10 10:58:14', NULL, NULL),
	(46, 3, 'es_ES', 'Plàtano flambe', '<br>', '<br>', '2012-08-10 10:59:45', '2012-09-10 17:04:24', NULL),
	(47, 14, 'en_EN', 'Fresh Trout in a lemon sauce', '<br>', '<br>', '2012-08-10 11:02:54', '2012-08-10 11:04:37', NULL),
	(48, 14, 'es_ES', 'Trucha fresca con salsa de limòn', '<br>', '<br>', '2012-08-10 11:04:11', NULL, NULL),
	(49, 14, 'it_IT', 'Trota fresca in salsa di limone', '<br>', '<br>', '2012-08-10 11:05:00', NULL, NULL),
	(50, 16, 'en_EN', 'Smoked salmon salad', '<br>', '<br>', '2012-08-10 11:15:19', NULL, NULL),
	(51, 16, 'it_IT', 'Salmone affumicato in insalata', '<br>', '<br>', '2012-08-10 11:34:53', NULL, NULL),
	(52, 16, 'es_ES', 'Ensalada de salmòn ahumado', '<br>', '<br>', '2012-08-10 11:35:54', NULL, NULL),
	(53, 2, 'es_ES', 'Salchicha con pimientos', '<br>', '<br>', '2012-08-10 11:39:52', NULL, NULL),
	(54, 17, 'en_EN', 'Crème caramel', '<br>', '<br>', '2012-08-10 11:44:44', NULL, NULL),
	(55, 17, 'it_IT', 'Crème caramel', '<br>', '<br>', '2012-08-10 11:44:58', NULL, NULL),
	(56, 17, 'es_ES', 'Flan', '<br>', '<br>', '2012-08-10 11:45:10', NULL, NULL),
	(57, 18, 'en_EN', 'Seafood soup', '<br>', '<br>', '2012-08-10 11:46:12', NULL, NULL),
	(58, 18, 'es_ES', 'Sopa de marisco', '<br>', '<br>', '2012-08-10 11:46:35', NULL, NULL),
	(59, 18, 'it_IT', 'Zuppa di pesce', '<br>', '<br>', '2012-08-10 11:46:49', NULL, NULL),
	(60, 19, 'en_EN', 'Prawns omelet', '<br>', '<br>', '2012-08-10 12:01:43', NULL, NULL),
	(61, 19, 'es_ES', 'Tortilla de gambas', '<br>', '<br>', '2012-08-10 12:02:05', NULL, NULL),
	(62, 19, 'it_IT', 'Frittata di gamberi', '<br>', '<br>', '2012-08-10 12:10:27', NULL, NULL),
	(63, 20, 'en_EN', 'Amanda', 'Ham and cheese<br>', '<b>Lorem ipsum </b>dolor sit amet, consectetur adipisicing elit, sed do eiusmod\r\n tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim \r\nveniam, quis nostrud exercitation ullamco <i>laboris </i>nisi ut aliquip ex ea \r\ncommodo consequat. Duis aute irure dolor in reprehenderit in voluptate \r\nvelit esse cillum dolore eu fugiat nulla <u>pariatur</u>. Excepteur sint \r\noccaecat cupidatat non proident, sunt in culpa qui officia deserunt \r\nmollit anim id est laborum', '2012-08-10 12:14:45', '2012-08-23 17:15:06', NULL),
	(64, 20, 'it_IT', 'Amanda', 'Prosciutto cotto e formaggio<br>', '<b>Lorem ipsum </b>dolor sit amet, consectetur adipisicing elit, sed do eiusmod\r\n tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim \r\nveniam, quis nostrud exercitation ullamco <i>laboris </i>nisi ut aliquip ex ea \r\ncommodo consequat. Duis aute irure dolor in reprehenderit in voluptate \r\nvelit esse cillum dolore eu fugiat nulla <u>pariatur</u>. Excepteur sint \r\noccaecat cupidatat non proident, sunt in culpa qui officia deserunt \r\nmollit anim id est laborum', '2012-08-10 12:15:07', '2012-08-23 17:15:29', NULL),
	(65, 20, 'es_ES', 'Amanda', 'York y queso<br>', '<b>Lorem ipsum </b>dolor sit amet, consectetur adipisicing elit, sed do eiusmod\r\n tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim \r\nveniam, quis nostrud exercitation ullamco <i>laboris </i>nisi ut aliquip ex ea \r\ncommodo consequat. Duis aute irure dolor in reprehenderit in voluptate \r\nvelit esse cillum dolore eu fugiat nulla <u>pariatur</u>. Excepteur sint \r\noccaecat cupidatat non proident, sunt in culpa qui officia deserunt \r\nmollit anim id est laborum', '2012-08-10 12:15:31', '2012-08-23 17:15:22', NULL),
	(66, 21, 'en_EN', 'Amelie', 'Tuna, cheese and tomato', 'Sed ut perspiciatis unde omnis iste natus error sit voluptatem \r\naccusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab\r\n illo inventore veritatis et quasi architecto beatae vitae dicta sunt \r\nexplicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut \r\nodit aut fugit, sed quia consequuntur magni dolores eos qui ratione \r\nvoluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum \r\nquia dolor sit amet, consectetur, adipisci velit, sed quia non numquam \r\neius modi tempora incidunt ut labore et dolore magnam aliquam quaerat \r\nvoluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam \r\ncorporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur?\r\n Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse \r\nquam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo \r\nvoluptas nulla pariatur', '2012-08-23 16:43:50', '2012-08-23 17:15:51', NULL),
	(67, 21, 'es_ES', 'Amelie', 'Tuna, queso y tomate', 'Sed ut perspiciatis unde omnis iste natus error sit voluptatem \r\naccusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab\r\n illo inventore veritatis et quasi architecto beatae vitae dicta sunt \r\nexplicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut \r\nodit aut fugit, sed quia consequuntur magni dolores eos qui ratione \r\nvoluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum \r\nquia dolor sit amet, consectetur, adipisci velit, sed quia non numquam \r\neius modi tempora incidunt ut labore et dolore magnam aliquam quaerat \r\nvoluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam \r\ncorporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur?\r\n Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse \r\nquam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo \r\nvoluptas nulla pariatur', '2012-08-23 16:44:14', '2012-08-23 17:16:00', NULL),
	(68, 21, 'it_IT', 'Amelie', 'Tonno, formaggio e pomodoro', 'Sed ut perspiciatis unde omnis iste natus error sit voluptatem \r\naccusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab\r\n illo inventore veritatis et quasi architecto beatae vitae dicta sunt \r\nexplicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut \r\nodit aut fugit, sed quia consequuntur magni dolores eos qui ratione \r\nvoluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum \r\nquia dolor sit amet, consectetur, adipisci velit, sed quia non numquam \r\neius modi tempora incidunt ut labore et dolore magnam aliquam quaerat \r\nvoluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam \r\ncorporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur?\r\n Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse \r\nquam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo \r\nvoluptas nulla pariatur', '2012-08-23 16:44:43', '2012-08-23 17:16:08', NULL),
	(69, 1, 'fr_FR', 'aa', 's2ggaaggsaa<br>', '<br>', '2012-09-10 16:37:09', '2012-09-10 16:43:04', '2012-09-10 17:46:33'),
	(70, 1, 'fi_FI', 'aa', 'dd', '', '2012-09-10 16:45:33', NULL, '2012-09-10 17:46:28'),
	(71, 1, 'de_DE', 'aa', 'ddd<br>', '<br>', '2012-09-10 16:46:08', NULL, '2012-09-10 17:46:23'),
	(72, 1, 'da_DA', 'wwd', 'ddss<br>', '<br>', '2012-09-10 16:48:25', '2012-09-10 17:46:08', '2012-09-10 17:46:16');
/*!40000 ALTER TABLE `product_2_language` ENABLE KEYS */;


-- Dumping structure for table tablet_menu.product_2_tag
DROP TABLE IF EXISTS `product_2_tag`;
CREATE TABLE IF NOT EXISTS `product_2_tag` (
  `id_product` int(10) unsigned NOT NULL,
  `id_tag` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`id_product`,`id_tag`),
  KEY `id_tag` (`id_tag`),
  CONSTRAINT `product_2_tag_ibfk_1` FOREIGN KEY (`id_product`) REFERENCES `product` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `product_2_tag_ibfk_2` FOREIGN KEY (`id_tag`) REFERENCES `tag` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table tablet_menu.product_2_tag: ~3 rows (approximately)
/*!40000 ALTER TABLE `product_2_tag` DISABLE KEYS */;
INSERT INTO `product_2_tag` (`id_product`, `id_tag`) VALUES
	(7, 1),
	(7, 2),
	(1, 4);
/*!40000 ALTER TABLE `product_2_tag` ENABLE KEYS */;


-- Dumping structure for table tablet_menu.tag
DROP TABLE IF EXISTS `tag`;
CREATE TABLE IF NOT EXISTS `tag` (
  `id` int(10) unsigned NOT NULL auto_increment,
  `label` varchar(50) NOT NULL default '',
  `id_domain` int(10) unsigned NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `label` (`label`),
  KEY `FK_tag1` (`id_domain`),
  CONSTRAINT `FK_tag1` FOREIGN KEY (`id_domain`) REFERENCES `domain` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Dumping data for table tablet_menu.tag: ~5 rows (approximately)
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` (`id`, `label`, `id_domain`) VALUES
	(1, 'Scorte Limitate', 2),
	(2, 'Pranzo', 2),
	(3, 'Cocktails', 2),
	(4, 'Cena', 2),
	(5, 'Contorni', 2);
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;


-- Dumping structure for table tablet_menu.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
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

-- Dumping data for table tablet_menu.user: ~15 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`_id`, `email`, `pwd`, `timezone`, `nickname`, `creation_date`, `update_date`, `id_lang`, `deleted`, `firstname`, `lastname`, `type`, `active`, `id_domain`) VALUES
	(3, 'admin@oxybay.com', '21232f297a57a5a743894a0e4a801fc3', 'Europe/London', 'SysAdmin', '2012-04-21 00:48:42', '2012-07-26 15:06:31', 'it_IT', NULL, 'Pippo', 'De Pini', 10, 1, 1),
	(4, 'admin2@oxybay.com', '21232f297a57a5a743894a0e4a801fc3', 'Europe/Berlin', 'admin1', '2012-06-18 10:52:12', '2012-07-05 14:54:20', 'en_EN', NULL, 'Paolosdad asd ads ad ada dsads', 'Gioppona da da das ad asda dsa d', 0, 1, 2),
	(5, 'admin3@oxybay.com', '86106102400fdcdfc5eee1f24c8f0da7', NULL, 'admin2', '2012-06-18 18:27:59', '2012-07-11 19:49:04', 'it_IT', NULL, 'Flavio Paolo', 'Micheli', 0, 1, 2),
	(6, 'simone.bonacina@oxybay.com', '47eb752bac1c08c75e30d9624b3e58b7', NULL, 'simone', '2012-06-27 10:26:37', '2012-06-27 18:13:37', 'en_EN', NULL, 'Simone', 'Bonacina', 20, 1, 2),
	(8, 'user1@email.com', '24c9e15e52afc47c225b757e7bee1f9d', NULL, 'nick1', '2012-06-28 15:05:05', NULL, NULL, NULL, '', '', 0, 1, 2),
	(9, 'user2@email.com', '7e58d63b60197ceb55a1c487989a3720', NULL, 'nick2', '2012-06-28 15:05:05', NULL, NULL, NULL, '', '', 0, 1, 2),
	(10, 'user3@email.com', '92877af70a45fd6a2ed7fe81e1236b78', NULL, 'nick3', '2012-06-28 15:05:05', NULL, NULL, '2012-06-29 14:26:27', '', '', 0, 1, 2),
	(11, 'user4@email.com', '3f02ebe3d7929b091e3d8ccfde2f3bc6', NULL, 'nick4', '2012-06-28 15:05:05', NULL, NULL, '2012-06-29 14:26:37', '', '', 0, 1, 2),
	(12, 'user5@email.com', '0a791842f52a0acfbb3a783378c066b8', NULL, 'nick5', '2012-06-28 15:05:05', NULL, NULL, NULL, '', '', 0, 1, 2),
	(13, 'user6@email.com', 'affec3b64cf90492377a8114c86fc093', NULL, 'nick6', '2012-06-28 15:05:05', NULL, NULL, '2012-06-29 14:26:37', '', '', 0, 1, 2),
	(14, 'user7@email.com', '3e0469fb134991f8f75a2760e409c6ed', NULL, 'nick7', '2012-06-28 15:05:05', NULL, NULL, NULL, '', '', 0, 1, 2),
	(15, 'user8@email.com', '7668f673d5669995175ef91b5d171945', NULL, 'nick8', '2012-06-28 15:05:05', NULL, NULL, NULL, '', '', 0, 1, 2),
	(16, 'user9@email.com', '8808a13b854c2563da1a5f6cb2130868', NULL, 'nick9', '2012-06-28 15:05:05', NULL, NULL, '2012-06-29 16:34:59', '', '', 0, 1, 2),
	(17, 'user10@email.com', '990d67a9f94696b1abe2dccf06900322', NULL, 'nick10', '2012-06-28 15:05:05', NULL, NULL, '2012-06-29 16:32:19', '', '', 0, 1, 2),
	(18, 'gala1@mailinator.com', '4c61cb1773ec5d477f47835b345ad994', 'Europe/Amsterdam', 'Gala1', '2012-07-11 11:29:21', '2012-07-11 11:29:45', 'en_EN', NULL, 'Gala1', 'Pippone', 0, 0, 2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


-- Dumping structure for table tablet_menu.user_permission
DROP TABLE IF EXISTS `user_permission`;
CREATE TABLE IF NOT EXISTS `user_permission` (
  `user_id` int(10) unsigned NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY  (`user_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table tablet_menu.user_permission: ~0 rows (approximately)
/*!40000 ALTER TABLE `user_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_permission` ENABLE KEYS */;


-- Dumping structure for view tablet_menu.view_active_attachment
DROP VIEW IF EXISTS `view_active_attachment`;
-- Creating temporary table to overcome VIEW dependency errors
CREATE TABLE `view_active_attachment` (
	`img` INT(11) UNSIGNED NULL DEFAULT NULL,
	`id_menu` INT(11) UNSIGNED NOT NULL DEFAULT '0'
) ENGINE=MyISAM;


-- Dumping structure for table tablet_menu.zz_valid_connect
DROP TABLE IF EXISTS `zz_valid_connect`;
CREATE TABLE IF NOT EXISTS `zz_valid_connect` (
  `label` varchar(1) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table tablet_menu.zz_valid_connect: ~1 rows (approximately)
/*!40000 ALTER TABLE `zz_valid_connect` DISABLE KEYS */;
INSERT INTO `zz_valid_connect` (`label`) VALUES
	('1');
	
	
--
-- Database: `tablet_menu`
--

-- --------------------------------------------------------

--
-- Table structure for table `daily_menu`
--

CREATE TABLE IF NOT EXISTS `daily_menu` (
  `_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `domain` int(10) unsigned NOT NULL,
  `active` tinyint(3) unsigned NOT NULL DEFAULT '0',
  `price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00',
  `creation_date` timestamp NULL DEFAULT NULL,
  `update_date` timestamp NULL DEFAULT NULL,
  `deleted` timestamp NULL DEFAULT NULL,
  `week_day` smallint(6) DEFAULT NULL,
  `day_type` tinyint(3) unsigned NOT NULL COMMENT '0=Lunch,1=Dinner',
  PRIMARY KEY (`_id`),
  KEY `domain` (`domain`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=18 ;

--
-- Dumping data for table `daily_menu`
--

INSERT INTO `daily_menu` (`_id`, `domain`, `active`, `price`, `creation_date`, `update_date`, `deleted`, `week_day`, `day_type`) VALUES
(4, 2, 1, 3.00, '2012-09-24 06:56:35', '2012-09-27 11:32:02', NULL, 1, 0),
(5, 2, 0, 0.00, '2012-09-24 06:56:35', NULL, NULL, 1, 1),
(6, 2, 0, 0.00, '2012-09-24 06:56:35', NULL, NULL, 2, 0),
(7, 2, 0, 0.00, '2012-09-24 06:56:35', NULL, NULL, 2, 1),
(8, 2, 0, 0.00, '2012-09-24 06:56:35', NULL, NULL, 3, 0),
(9, 2, 0, 0.00, '2012-09-24 06:56:35', NULL, NULL, 3, 1),
(10, 2, 0, 0.00, '2012-09-24 06:56:35', NULL, NULL, 4, 0),
(11, 2, 0, 0.00, '2012-09-24 06:56:35', NULL, NULL, 4, 1),
(12, 2, 0, 0.00, '2012-09-24 06:56:35', NULL, NULL, 5, 0),
(13, 2, 0, 0.00, '2012-09-24 06:56:35', NULL, NULL, 5, 1),
(14, 2, 0, 0.00, '2012-09-24 06:56:35', NULL, NULL, 6, 0),
(15, 2, 0, 0.00, '2012-09-24 06:56:35', NULL, NULL, 6, 1),
(16, 2, 0, 0.00, '2012-09-24 06:56:35', NULL, NULL, 7, 0),
(17, 2, 0, 0.00, '2012-09-24 06:56:35', NULL, NULL, 7, 1);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `daily_menu`
--
ALTER TABLE `daily_menu`
  ADD CONSTRAINT `daily_menu_ibfk_1` FOREIGN KEY (`domain`) REFERENCES `domain` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE;
  
--
-- Table structure for table `product_2_daily_menu`
--

CREATE TABLE IF NOT EXISTS `product_2_daily_menu` (
  `_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `daily_menu` int(10) unsigned NOT NULL,
  `product_id` int(10) unsigned NOT NULL,
  `item_order` smallint(6) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `daily_menu` (`daily_menu`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=36 ;

--
-- Dumping data for table `product_2_daily_menu`
--

INSERT INTO `product_2_daily_menu` (`_id`, `daily_menu`, `product_id`, `item_order`) VALUES
(34, 4, 5, 2),
(35, 4, 20, 3);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `product_2_daily_menu`
--
ALTER TABLE `product_2_daily_menu`
  ADD CONSTRAINT `product_2_daily_menu_ibfk_1` FOREIGN KEY (`daily_menu`) REFERENCES `daily_menu` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `tablet_menu`.`product_2_daily_menu` ADD INDEX ( `product_id` );
ALTER TABLE `product_2_daily_menu` ADD FOREIGN KEY ( `product_id` ) REFERENCES `tablet_menu`.`product` (
`_id`
) ON DELETE CASCADE ON UPDATE CASCADE ;

--
-- Table structure for table `category_2_daily_menu`
--

CREATE TABLE IF NOT EXISTS `category_2_daily_menu` (
  `_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `daily_menu` int(10) unsigned NOT NULL,
  `category_id` int(10) unsigned NOT NULL,
  `item_order` smallint(6) NOT NULL,
  PRIMARY KEY (`_id`),
  KEY `daily_menu` (`daily_menu`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `category_2_daily_menu`
--

INSERT INTO `category_2_daily_menu` (`_id`, `daily_menu`, `category_id`, `item_order`) VALUES
(5, 4, 1, 1),
(6, 4, 16, 4);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `category_2_daily_menu`
--
ALTER TABLE `category_2_daily_menu`
  ADD CONSTRAINT `category_2_daily_menu_ibfk_1` FOREIGN KEY (`daily_menu`) REFERENCES `daily_menu` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `tablet_menu`.`category_2_daily_menu` ADD INDEX ( `category_id` );
ALTER TABLE `category_2_daily_menu` ADD FOREIGN KEY ( `category_id` ) REFERENCES `tablet_menu`.`category` (
`_id`
) ON DELETE CASCADE ON UPDATE CASCADE ;

/*!40000 ALTER TABLE `zz_valid_connect` ENABLE KEYS */;


-- Dumping structure for view tablet_menu.view_active_attachment
DROP VIEW IF EXISTS `view_active_attachment`;
-- Removing temporary table and create final VIEW structure
DROP TABLE IF EXISTS `view_active_attachment`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY INVOKER VIEW `view_active_attachment` AS select `menu`.`logo` AS `img`,`menu`.`_id` AS `id_menu` from `menu` where ((`menu`.`logo` is not null) and isnull(`menu`.`deleted`) and (`menu`.`active` = 1)) union select `category`.`img` AS `img`,`category`.`id_menu` AS `id_menu` from `category` where ((`category`.`img` is not null) and isnull(`category`.`deleted`) and (`category`.`active` = 1)) union select `b`.`img` AS `img`,`b`.`id_menu` AS `id_menu` from ((`product` `b` join `product_2_category` `d` on((`b`.`_id` = `d`.`id_product`))) join `category` `e` on(((`d`.`id_category` = `e`.`_id`) and (`e`.`active` = 1) and isnull(`e`.`deleted`)))) where ((`b`.`img` is not null) and (`b`.`active` = 1) and isnull(`b`.`deleted`));
/*!40014 SET FOREIGN_KEY_CHECKS=1 */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
