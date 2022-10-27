-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.23 - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for minesweeperdb
CREATE DATABASE IF NOT EXISTS `minesweeperdb` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `minesweeperdb`;

-- Dumping structure for table minesweeperdb.game
CREATE TABLE IF NOT EXISTS `game` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `score` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `levelId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `levelId` (`levelId`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table minesweeperdb.game: 2 rows
/*!40000 ALTER TABLE `game` DISABLE KEYS */;
INSERT INTO `game` (`id`, `date`, `score`, `userId`, `levelId`) VALUES
	(1, '2022-07-02', 15, 2, 1),
	(2, '2022-07-03', 34, 2, 1),
	(3, '2022-09-04', 5, 2, 1);
/*!40000 ALTER TABLE `game` ENABLE KEYS */;

-- Dumping structure for table minesweeperdb.level
CREATE TABLE IF NOT EXISTS `level` (
  `id` int(11) NOT NULL,
  `difficulty` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `percentageOfBomb` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table minesweeperdb.level: 3 rows
/*!40000 ALTER TABLE `level` DISABLE KEYS */;
INSERT INTO `level` (`id`, `difficulty`, `percentageOfBomb`) VALUES
	(1, 'Easy', 0.1),
	(2, 'Normal', 0.2),
	(3, 'Hard', 0.35);
/*!40000 ALTER TABLE `level` ENABLE KEYS */;

-- Dumping structure for table minesweeperdb.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `highscore` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table minesweeperdb.user: 4 rows
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `username`, `password`, `email`, `highscore`) VALUES
	(1, 'mica', 'mica123', 'mica@gmail.com', 10),
	(2, 'marija', 'marija123', 'marija.jeremic31@gmail.com', 35),
	(3, 'zika', 'zika123', 'zika@gmail.com', 0),
	(4, 'juca', 'juca123', 'juca@gmail.com', 10000);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
