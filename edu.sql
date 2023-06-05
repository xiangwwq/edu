-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: edu
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chapter`
--

DROP TABLE IF EXISTS `chapter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chapter` (
  `id` int NOT NULL AUTO_INCREMENT,
  `courser_id` int DEFAULT NULL,
  `title` varchar(300) DEFAULT NULL,
  `sort` int DEFAULT NULL,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `chapter_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chapter`
--

/*!40000 ALTER TABLE `chapter` DISABLE KEYS */;
INSERT INTO `chapter` VALUES (1,1,'第一章 java入门可以',1,'2023-05-12 20:33:48'),(2,1,'第二章 常见的数据类型',2,'2023-05-12 20:35:19'),(9,2,'学习Vue',1,'2023-05-13 14:50:26'),(10,2,'学习node',2,'2023-05-13 14:50:42'),(11,7,'第一章 ',1,'2023-05-13 15:04:20'),(12,8,'第一章',1,'2023-05-14 12:26:06');
/*!40000 ALTER TABLE `chapter` ENABLE KEYS */;

--
-- Table structure for table `courser`
--

DROP TABLE IF EXISTS `courser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courser` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(30) DEFAULT NULL,
  `price` int DEFAULT NULL,
  `lesson_num` int DEFAULT '50' COMMENT '课时数',
  `cover` varchar(200) DEFAULT NULL COMMENT '课程封面',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `courser_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courser`
--

/*!40000 ALTER TABLE `courser` DISABLE KEYS */;
INSERT INTO `courser` VALUES (1,'java',200,13,'http://localhost:3000/images//2023-05-133/4d191388-99e1-48d8-901b-15cc6abfdfd8.jpg','2023-05-14 11:49:48'),(2,'vue',20,50,'http://localhost:3000/images//2023-05-133/4d191388-99e1-48d8-901b-15cc6abfdfd8.jpg','2023-05-14 13:17:09'),(3,'C语言',15,50,'http://localhost:3000/images//2023-05-133/4d191388-99e1-48d8-901b-15cc6abfdfd8.jpg','2023-05-12 19:44:10'),(4,'go语言',12,10,'http://localhost:3000/images//2023-05-133/4d191388-99e1-48d8-901b-15cc6abfdfd8.jpg','2023-05-12 19:44:36'),(5,'MySql',16,50,'http://localhost:3000/images//2023-05-133/4d191388-99e1-48d8-901b-15cc6abfdfd8.jpg','2023-05-12 19:46:16'),(6,'高等数学',50,10,'http://localhost:3000/images//2023-05-133/4d191388-99e1-48d8-901b-15cc6abfdfd8.jpg','2023-05-12 19:46:16'),(7,'线性代数',10,100,'http://localhost:3000/images//2023-05-133/0dcc9d6b-30bf-46e5-a266-511c99996925.png','2023-05-13 15:04:08'),(8,'skt',10,0,'http://localhost:3000/images//2023-05-134/b4ce7334-4ba6-4c4f-af3f-c3b5f674a58b.jpg','2023-05-14 12:42:30');
/*!40000 ALTER TABLE `courser` ENABLE KEYS */;

--
-- Table structure for table `msg`
--

DROP TABLE IF EXISTS `msg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `msg` (
  `id` int DEFAULT NULL COMMENT '课程id',
  `message` text COMMENT '课程描述'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `msg`
--

/*!40000 ALTER TABLE `msg` DISABLE KEYS */;
INSERT INTO `msg` VALUES (1,'java课程  这是课程详情,本课程由专业老师授课,内容优质,利于消'),(2,'这是课程详情,本课程由专业老师授课,内容优质,利于消化'),(3,'这是课程详情,本课程由专业老师授课,内容优质,利于消化'),(4,'这是课程详情,本课程由专业老师授课,内容优质,利于消化'),(5,'这是课程详情,本课程由专业老师授课,内容优质,利于消化'),(6,'这是课程详情,本课程由专业老师授课,内容优质,利于消化'),(7,'这是课程详情,本课程由专业老师授课,内容优质,利于消化'),(8,'sktskt');
/*!40000 ALTER TABLE `msg` ENABLE KEYS */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `uid` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `auth` int DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `user_uid_uindex` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'小陈','123','123',1),(2,'小李','123','321',0),(3,'小小','123456','090909',NULL),(4,'星期三','136','136',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

--
-- Table structure for table `video`
--

DROP TABLE IF EXISTS `video`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `video` (
  `id` int NOT NULL AUTO_INCREMENT,
  `course_id` int DEFAULT NULL,
  `chapter_id` int DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `video_source_path` varchar(300) DEFAULT NULL COMMENT '视频地址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `video_id_uindex` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `video`
--

/*!40000 ALTER TABLE `video` DISABLE KEYS */;
INSERT INTO `video` VALUES (1,1,1,'1.1节','8038bc99048d409897b77aec4077d256'),(2,1,1,'1.2节','8038bc99048d409897b77aec4077d256'),(3,1,2,'2.1节','8038bc99048d409897b77aec4077d256'),(4,1,2,'2.2节','8038bc99048d409897b77aec4077d256'),(7,7,11,'1.1节','8038bc99048d409897b77aec4077d256'),(8,1,1,'1.3','8038bc99048d409897b77aec4077d256'),(9,8,12,'1.1节','8038bc99048d409897b77aec4077d256'),(10,8,12,'1.22222','423d56d0f21371edb8e66733a78e0102');
/*!40000 ALTER TABLE `video` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-14 13:43:11
