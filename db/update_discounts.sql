CREATE TABLE `category_2_discount` (
	`id_category` INT(10) UNSIGNED NOT NULL,
	`id_discount` BIGINT(20) UNSIGNED NOT NULL,
	PRIMARY KEY (`id_category`, `id_discount`),
	INDEX `FK_category_2_discount2` (`id_discount`),
	CONSTRAINT `FK_category_2_discount1` FOREIGN KEY (`id_category`) REFERENCES `category` (`_id`) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT `FK_category_2_discount2` FOREIGN KEY (`id_discount`) REFERENCES `discount_policy` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB; 

CREATE TABLE `tag_2_discount` (
	`id_tag` INT(10) UNSIGNED NOT NULL,
	`id_discount` BIGINT(20) UNSIGNED NOT NULL,
	PRIMARY KEY (`id_tag`, `id_discount`),
	INDEX `FK_tag_2_discount2` (`id_discount`),
	CONSTRAINT `FK_tag_2_discount1` FOREIGN KEY (`id_tag`) REFERENCES `tag` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT `FK_tag_2_discount2` FOREIGN KEY (`id_discount`) REFERENCES `discount_policy` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB;