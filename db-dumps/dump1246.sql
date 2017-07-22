-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        5.6.36 - MySQL Community Server (GPL)
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- porest 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `porest` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `porest`;

-- 테이블 porest.account 구조 내보내기
CREATE TABLE IF NOT EXISTS `account` (
  `id` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nickname` varchar(255) NOT NULL,
  `registration_id` varchar(255) NOT NULL,
  `received_like` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 porest.forest 구조 내보내기
CREATE TABLE IF NOT EXISTS `forest` (
  `tree_idx` int(11) NOT NULL,
  `hash_idx` int(11) NOT NULL AUTO_INCREMENT,
  `creation_time` datetime NOT NULL,
  PRIMARY KEY (`tree_idx`),
  KEY `hash_idx` (`hash_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 porest.tags 구조 내보내기
CREATE TABLE IF NOT EXISTS `tags` (
  `hash_idx` int(11) NOT NULL,
  `contant` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- 내보낼 데이터가 선택되어 있지 않습니다.
-- 테이블 porest.tree 구조 내보내기
CREATE TABLE IF NOT EXISTS `tree` (
  `tree_name` varchar(255) NOT NULL,
  `tree_idx` int(11) NOT NULL AUTO_INCREMENT,
  `owner` varchar(255) NOT NULL,
  `creation_time` datetime NOT NULL,
  `current_leaves` int(11) NOT NULL DEFAULT '0',
  `maximum_leaves` int(11) NOT NULL DEFAULT '365',
  `is_shared` tinyint(1) NOT NULL DEFAULT '0',
  `like_count` int(11) NOT NULL DEFAULT '0',
  `dislike_count` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`tree_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 내보낼 데이터가 선택되어 있지 않습니다.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
