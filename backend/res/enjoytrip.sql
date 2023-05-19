-- enjoytrip definition
drop database if exists enjoytrip;
create database enjoytrip;

use enjoytrip;

drop table if exists user;

CREATE TABLE `user` (
  `id` varchar(20) NOT NULL,
  `pass` varchar(100) NOT NULL,
  `name` varchar(20) NOT NULL,
  `email` varchar(100) NOT NULL,
  `position` varchar(30) NOT NULL default 'user',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `board` (
  `article_no` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `subject` varchar(200) NOT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `hit` int unsigned NOT NULL DEFAULT '0',
  `register_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_no`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`hotplace` (
  `article_no` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(100) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `subject` VARCHAR(200) NOT NULL,
  `content` VARCHAR(2000) NULL DEFAULT NULL,
  `hit` INT UNSIGNED NOT NULL DEFAULT '0',
  `register_time` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `content_id` INT NULL,
  PRIMARY KEY (`article_no`),
  CONSTRAINT `hotplace_to_attraction_info_content_id_fk`
    FOREIGN KEY (`content_id`)
    REFERENCES `enjoytrip`.`attraction_info` (`content_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 75
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;
