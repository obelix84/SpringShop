-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: localhost    Database: springshopnew
-- ------------------------------------------------------
-- Server version	8.0.25
--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users`
VALUES (1,'admin@mail.ru','Admin','Adminov','$2a$12$rLM.xm1o4TG5qzSNuFLyXebgtXenHJm.ZiqQdve1Msy60FrCkj4dy','admin_user'),
       (2,'user@mail.ru','User','Userov','$2a$12$zKG0IKvnx822yjK5qaCGj.k.CJQ4.m4XxtCxX/haC.ClQLM6zZCGC','user_user'),
       (3,'manager@mail.ru','Manager','Managerov','$2a$12$OXFwAbml4efrzaioSqcQN.p8EiSVWlaXjwoEi8ZHnrgtmErMdraOu','manager_user');

