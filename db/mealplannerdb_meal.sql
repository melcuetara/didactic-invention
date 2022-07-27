-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: mealplannerdb
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `meal`
--

DROP TABLE IF EXISTS `meal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `meal` (
  `id` int NOT NULL AUTO_INCREMENT,
  `plan_id` int NOT NULL,
  `name` varchar(100) NOT NULL,
  `calories` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `plan_id_idx` (`plan_id`),
  CONSTRAINT `plan_id` FOREIGN KEY (`plan_id`) REFERENCES `plan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meal`
--

LOCK TABLES `meal` WRITE;
/*!40000 ALTER TABLE `meal` DISABLE KEYS */;
INSERT INTO `meal` VALUES (37,48,'Candied Garlic recipes',24.98),(38,48,'Easy-bake bread',2132.04),(39,49,'Stuffed Sausage and Cheese French Bread',20.06),(40,48,'Tagalong Chocolate-Peanut Butter Ice Cream recipes',157.04),(42,57,'Stir Fried/Braised Bok Choy',17.79),(43,48,'Milk Poha Recipe - How To Make Milk Poha - How To Prepare Milk Poha',58.94),(45,45,'Bibimbap Breakfast Bowl',254.72),(46,45,'Peanut Butter Cup Chocolate Coffee Cake',71.8),(47,48,'Poppy Seed Fruit Salad',10.17),(48,48,'Poppy Seed Fruit Salad',10.17),(49,48,'Sweet Cucumber Salad Recipe 5',15.87),(50,48,'Chilled Coconut Soup with Blackberry Ice Cream',268.14),(51,48,'Raspberry Banana Tofu Shake Recipe',37.87),(52,48,'Green Goddess Smoothie recipes',322.65),(53,48,'Kung Pao Tofu with Chinese Broccoli &amp; Brown Rice recipes',481.63),(54,48,'Upside-Down Banana Cake',22.99),(55,48,'Lemon Pepper Chicken Noodle Soup',18.65),(56,57,'One Pan Skinny Tex Mex Chicken and Zucchini',30.03),(57,48,'Johnd\'s White Bean Chili',61.62),(58,48,'Gluten-free Delicious Kale and Pumpkin Pie Recipe',64.19),(59,58,'Banana Nut Tofu Muffin',15.33),(60,58,'Baked Blueberry Oatmeal Cups recipes',11.21),(61,59,'Chicken and Cheese Crepes',189.4),(62,59,'Pad Kee Mao Gai (Drunken Noodles w/Chicken)',14.81),(63,60,'Chicken Cheddar Jack and Bacon Quesadilla #KraftFreshTake recipes',294.44);
/*!40000 ALTER TABLE `meal` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-07-27 18:38:59
