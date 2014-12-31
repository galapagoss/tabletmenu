CREATE TABLE IF NOT EXISTS `device` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `domain` int(10) unsigned NOT NULL,
  `device_id` varchar(200) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_device1` (`domain`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

--
-- Constraints for table `device`
--
ALTER TABLE `device`
  ADD CONSTRAINT `FK_device1` FOREIGN KEY (`domain`) REFERENCES `domain` (`_id`) ON DELETE CASCADE ON UPDATE CASCADE;