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
-- Table structure for table `ingredient`
--

DROP TABLE IF EXISTS `ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredient` (
  `id` int NOT NULL AUTO_INCREMENT,
  `meal_id` int DEFAULT NULL,
  `name` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `meal_id_idx` (`meal_id`),
  CONSTRAINT `meal_id` FOREIGN KEY (`meal_id`) REFERENCES `meal` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredient`
--

LOCK TABLES `ingredient` WRITE;
/*!40000 ALTER TABLE `ingredient` DISABLE KEYS */;
INSERT INTO `ingredient` VALUES (1,57,'6 cups water'),(2,57,'3 tablespoons vegetable oil'),(3,57,'1 medium carrot , strips (1/2-inch thick)'),(4,57,'1 tablespoon ketchup'),(5,57,'2 garlic cloves , minced'),(6,57,'1 onion , chopped (medium sized)'),(7,57,'1 tablespoon brown sugar (optional)'),(8,57,'1 medium potato sticks (1-inch x1/2-inch)'),(9,57,'1 teaspoon powdered thyme'),(10,57,'1/4 pimiento strip (optional)'),(11,57,'1 teaspoon powdered parsley , to taste'),(12,57,'1 teaspoon coriander , to taste'),(13,57,'1/2-3/4 lb boneless skinless chicken breast , diced into 1-inch cubes'),(14,57,'1/4 cup dry white wine (optional)'),(15,57,'1/2 large red bell pepper , chopped'),(16,57,'2 stalks celery or cumin , chopped'),(17,57,'1 cup water'),(18,57,'AROMATIC HERBS'),(19,57,'salt and pepper, to taste'),(20,58,'&frac12; small butternut pumpkin, peeled &amp; diced'),(21,58,'1 leek, finely chopped'),(22,58,'5 kale leaves, ribs removed &amp; finely chopped'),(23,58,'6 organic eggs, whisked'),(24,58,'&frac34; cup natural yoghurt'),(25,58,'Pinch sea salt &amp; pepper'),(26,58,'Handful fresh herbs such as basil and parsley, roughly chopped'),(27,58,'60g crumbled feta'),(28,58,'Handful pumpkin seeds'),(29,59,'1 1&frasl;2 cups whole wheat flour'),(30,59,'1&frasl;2 cup oats'),(31,59,'1&frasl;4 cup cocoa powder'),(32,59,'1 teaspoon baking soda'),(33,59,'1&frasl;4 teaspoon baking powder'),(34,59,'1&frasl;2 teaspoon salt'),(35,59,'1 teaspoon cinnamon'),(36,59,'1 egg'),(37,59,'2 very ripe bananas'),(38,59,'3&frasl;4 cup honey'),(39,59,'1&frasl;4 cup maple syrup'),(40,59,'4 ounces firm tofu'),(41,59,'1&frasl;4 cup unrefined melted coconut oil'),(42,59,'1&frasl;2 cup walnuts, chopped'),(43,59,'1&frasl;2 cup raisins'),(44,60,'3 cups old-fashioned rolled oats'),(45,60,'1 teaspoon baking powder'),(46,60,'1 teaspoon ground cinnamon'),(47,60,'&frac14; teaspoon salt'),(48,60,'1 cup milk'),(49,60,'2 large eggs'),(50,60,'&frac14; cup honey'),(51,60,'2 tablespoons brown sugar'),(52,60,'1 teaspoon vanilla extract'),(53,60,'1 cup fresh blueberries'),(54,61,'2-&frac12; cups Shredded, Cooked Chicken'),(55,61,'1-&frac12; cup Ricotta Cheese'),(56,61,'&frac34; cups Grated Parmesan Cheese, Divided, Plus A Bit More For Garnish'),(57,61,'&frac12; cups Chopped Fresh Herbs (I Used Parsley And Thyme), Divided'),(58,61,'Salt And Pepper, to taste'),(59,61,'8 whole Crepes'),(60,61,'3 Tablespoons Butter'),(61,61,'2 bulbs Shallots, Sliced'),(62,61,'2 whole Medium Zucchini, Diced'),(63,61,'8 ounces, weight Button Mushrooms, Diced'),(64,61,'1 cup Chicken Stock'),(65,61,'&frac14; cups Dry White Wine'),(66,61,'2 teaspoons Lemon Zest, Plus A Bit For Garnish'),(67,62,'1/4 cup vegetable oil'),(68,62,'12 garlic cloves, finely chopped'),(69,62,'1/2 large red onion, thinly sliced'),(70,62,'2 tablespoons chopped fresh Thai chiles'),(71,62,'1 to 1 1/2 pounds boneless, skinless chicken thighs, sliced into thin pieces (about 1&quot; x 1/4&quot;)'),(72,62,'1/4 cup fish sauce (I used nuoc nam)'),(73,62,'1/4 cup black soy sauce'),(74,62,'1/4 cup Golden Mountain sauce'),(75,62,'1 tablespoon palm sugar'),(76,62,'1 red bell pepper, cut into thin strips'),(77,62,'1 yellow bell pepper, cut into thin strips'),(78,62,'1 cup fresh Thai basil leaves'),(79,62,'14 ounces fresh wide rice noodles (they come in a 2-pound bag; I just scaled back a bit), separated into individual noodles'),(80,63,'1 large cooked boneless chicken breast that has already been dipped and cooked in the Kraft Fresh Taste Cheddar Jack and Bacon coating mix.'),(81,63,'1 cup of Kraft Triple Cheddar Shredded Cheese'),(82,63,'6 large flour tortillas'),(83,63,'6 pieces of crispy fried bacon');
/*!40000 ALTER TABLE `ingredient` ENABLE KEYS */;
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
