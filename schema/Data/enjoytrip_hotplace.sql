-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: enjoytrip
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `hotplace`
--

DROP TABLE IF EXISTS `hotplace`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotplace` (
  `article_no` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `subject` varchar(200) NOT NULL,
  `content` varchar(2000) DEFAULT NULL,
  `hit` int unsigned NOT NULL DEFAULT '0',
  `likes` int unsigned NOT NULL DEFAULT '0',
  `register_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `content_id` int DEFAULT NULL,
  PRIMARY KEY (`article_no`),
  KEY `hotplace_to_attraction_info_content_id_fk` (`content_id`),
  CONSTRAINT `hotplace_to_attraction_info_content_id_fk` FOREIGN KEY (`content_id`) REFERENCES `attraction_info` (`content_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotplace`
--

LOCK TABLES `hotplace` WRITE;
/*!40000 ALTER TABLE `hotplace` DISABLE KEYS */;
INSERT INTO `hotplace` VALUES (1,'kyuh2001','도산공원최고','안창호 만세 안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세안창호 만세',155,18,'2023-05-23 13:05:55',126486),(3,'kyuh2001','봉은사최고','123최고최고',21,4,'2023-05-23 13:14:51',126504),(5,'kyuh2001','압로최고!','123',12,3,'2023-05-23 13:30:33',126823),(6,'kyuh2001','한원미술관 길다','한원 미술관 진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜진짜진짜 진짜진 짜 길다',25,7,'2023-05-23 13:30:45',129763),(7,'kyuh2001','공원','123',4,1,'2023-05-23 14:26:09',126513),(8,'kyuh2001','마리오아울렛','123',2,2,'2023-05-23 14:29:16',132593),(9,'kyuh2001','ㄱㄷ박물관','롯데호텔에서 건대박물관으ㄹ로',17,5,'2023-05-23 14:43:22',129818),(10,'kyuh2001','명동롯데호텔','123',5,10,'2023-05-23 14:43:36',2504463),(11,'kyuh2001','스파랜드','찜질방',4,6,'2023-05-23 14:43:53',128961),(12,'kyuh2001','나루아트센터','123',0,1,'2023-05-23 14:44:09',130699),(13,'kyuh2001','해운대 달맞이길','이야홍',0,0,'2023-05-23 14:44:30',126674),(14,'kyuh2001','ㅁㄴㅇㄹ','ㅁㄴㅇㄹ',0,0,'2023-05-23 14:46:45',126108),(16,'kyuh2001','ㅎㅅㄱㅇ','ㅎㅅㄱㅇ',0,0,'2023-05-23 14:47:10',127197),(17,'kyuh2001','구암','123',0,0,'2023-05-23 15:03:28',126824),(18,'kyuh2001','ㅇㅊㅎㄱ','ㅇㅊㅎㄱ',0,1,'2023-05-23 15:04:33',127215),(19,'kyuh2001','123','123',0,0,'2023-05-25 17:19:08',142001);
/*!40000 ALTER TABLE `hotplace` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-25 17:47:52
