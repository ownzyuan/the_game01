/*
SQLyog Professional v12.09 (64 bit)
MySQL - 8.0.20 : Database - the_games
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`the_games` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `the_games`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '管理员序号',
  `username` varchar(10) DEFAULT NULL COMMENT '用户名',
  `password` varchar(10) DEFAULT NULL COMMENT '密码',
  `perms` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='管理员';

/*Data for the table `admin` */

insert  into `admin`(`id`,`username`,`password`,`perms`) values (1,'admin','zy123456','');

/*Table structure for table `games` */

DROP TABLE IF EXISTS `games`;

CREATE TABLE `games` (
  `id_game` int NOT NULL COMMENT '游戏编号',
  `game_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '游戏名',
  `deleted` int DEFAULT '0' COMMENT '逻辑删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id_game`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='游戏表\r\n用于存储所有游戏名';

/*Data for the table `games` */

insert  into `games`(`id_game`,`game_name`,`deleted`,`create_time`,`update_time`) values (1,'英雄联盟',0,'2020-06-02 14:28:05','2020-06-02 14:28:05'),(2,'绝地求生',0,'2020-06-02 16:51:19','2020-06-02 16:51:22'),(3,'守望先锋',0,'2020-06-02 16:51:47','2020-06-02 16:51:48'),(4,'剑网三',0,'2020-06-02 16:51:55','2020-06-02 16:51:56'),(5,'穿越火线',0,'2020-06-02 16:52:21','2020-06-02 16:52:22'),(6,'剑灵',0,'2020-06-02 16:52:42','2020-06-02 16:52:44'),(7,'炉石传说',0,'2020-06-02 16:53:07','2020-06-02 16:53:09'),(8,'开心消消乐',0,'2020-06-02 16:53:04','2020-06-02 16:53:38'),(9,'崩坏3',0,'2020-06-02 16:54:20','2020-06-02 16:54:22'),(10,'王者荣耀',0,'2020-06-02 16:54:30','2020-06-02 16:54:31'),(11,'和平精英',0,'2020-06-02 16:54:39','2020-06-02 16:54:41'),(12,'第五人格',0,'2020-06-02 16:54:56','2020-06-02 16:54:58');

/*Table structure for table `lol` */

DROP TABLE IF EXISTS `lol`;

CREATE TABLE `lol` (
  `h_id` int NOT NULL COMMENT '英雄编号',
  `designation` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '英雄称号',
  `hero_name` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '英雄名',
  `gender` int DEFAULT NULL COMMENT '性别,0男1女',
  `force_id` int DEFAULT NULL COMMENT '势力编号',
  `occupation_one` int DEFAULT NULL COMMENT '职业（主）',
  `occupation_two` int DEFAULT NULL COMMENT '职业（次）',
  `route_one` int DEFAULT NULL COMMENT '推荐分路一',
  `route_two` int DEFAULT NULL COMMENT '推荐分路二',
  `deleted` int DEFAULT '0' COMMENT '逻辑删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`h_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='英雄联盟';

/*Data for the table `lol` */

insert  into `lol`(`h_id`,`designation`,`hero_name`,`gender`,`force_id`,`occupation_one`,`occupation_two`,`route_one`,`route_two`,`deleted`,`create_time`,`update_time`) values (1,'德玛西亚之力','盖伦',0,1,5,6,1,4,0,'2020-06-02 14:56:42','2020-06-08 19:36:55'),(2,'诺克萨斯之手','德莱厄斯',0,2,6,5,1,2,0,'2020-06-02 15:20:02','2020-06-02 15:20:02'),(3,'德邦总管','赵信',0,1,6,3,2,1,0,'2020-06-04 18:07:35','2020-06-04 18:07:35'),(4,'寒冰射手','艾希',1,3,1,4,4,5,0,'2020-06-04 18:07:35','2020-06-04 18:07:35'),(5,'刀锋舞者','艾瑞利亚',1,7,6,3,1,3,0,'2020-06-04 18:07:35','2020-06-04 18:07:35'),(6,'荣耀行刑官','德莱文',0,2,1,6,4,4,0,'2020-06-04 18:07:35','2020-06-04 18:07:35'),(7,'铁铠冥魂','莫德凯撒',0,4,6,2,1,3,0,'2020-06-04 18:07:35','2020-06-04 18:07:35'),(8,'虚空行者','卡萨丁',0,8,3,2,3,3,0,'2020-06-04 18:07:35','2020-06-04 18:07:35'),(9,'皮城女警','凯特琳',0,9,1,1,4,4,0,'2020-06-04 18:07:35','2020-06-04 18:07:35'),(10,'法外狂徒','格雷福斯',0,5,1,3,2,3,0,'2020-06-04 18:07:35','2020-06-04 18:07:35'),(11,'沙漠皇帝','阿兹尔',0,11,2,2,3,3,0,'2020-06-04 18:07:35','2020-06-04 18:07:35'),(12,'不屈之枪','潘森',0,10,6,3,3,1,0,'2020-06-04 18:07:35','2020-06-04 18:07:35'),(13,'时间刺客','艾克',0,6,3,2,3,2,0,'2020-06-07 13:07:36','2020-06-07 13:07:36'),(14,'皮城执法官','蔚',1,9,6,5,2,1,0,'2020-06-07 13:10:45','2020-06-07 13:10:45'),(15,'战争之影','赫卡里姆',0,4,6,5,2,1,0,'2020-06-07 13:26:29','2020-06-07 13:26:29'),(16,'虚空掠夺者','卡兹克',0,8,3,3,2,3,0,'2020-06-07 13:43:05','2020-06-07 13:43:05'),(17,'星界游神','巴德',0,10,4,2,5,5,0,'2020-06-07 13:46:56','2020-06-07 13:46:56'),(18,'北极之怒','瑟庄妮',1,3,5,6,2,1,0,'2020-06-07 13:50:06','2020-06-07 13:50:06'),(19,'天启者','卡尔玛',1,7,4,2,5,3,0,'2020-06-07 13:55:05','2020-06-07 13:55:05'),(20,'刀锋之影','泰隆',0,2,3,3,3,2,0,'2020-06-07 14:04:38','2020-06-07 14:04:38'),(21,'沙漠死神','内瑟斯',0,11,6,5,1,2,0,'2020-06-07 14:33:11','2020-06-07 14:33:11'),(22,'巨魔之王','特朗德尔',0,3,6,5,2,1,0,'2020-06-07 14:34:32','2020-06-07 14:34:32'),(23,'魂锁典狱长','锤石',0,4,4,4,5,5,0,'2020-06-07 14:41:53','2020-06-07 14:41:53'),(24,'战争女神','希维尔',1,11,1,1,4,4,0,'2020-06-08 19:19:17','2020-06-08 19:20:16'),(25,'海洋之灾','普朗克',0,5,6,6,1,3,0,'2020-06-08 19:38:59','2020-06-08 20:25:59'),(26,'疾风剑豪','亚索',0,7,6,3,3,4,1,'2020-06-09 10:31:39','2020-06-09 10:31:39'),(27,'涤魂圣枪','塞纳',1,4,4,1,5,4,0,'2020-06-12 18:40:48','2020-06-12 18:40:48');

/*Table structure for table `lol_forces` */

DROP TABLE IF EXISTS `lol_forces`;

CREATE TABLE `lol_forces` (
  `f_id` int NOT NULL COMMENT '势力编号',
  `f_name` varchar(20) DEFAULT NULL COMMENT '势力名',
  `deleted` int DEFAULT '0' COMMENT '逻辑删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`f_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='势力';

/*Data for the table `lol_forces` */

insert  into `lol_forces`(`f_id`,`f_name`,`deleted`,`create_time`,`update_time`) values (1,'德玛西亚',0,'2020-06-12 14:31:19','2020-06-12 12:02:47'),(2,'诺克萨斯',0,'2020-06-12 14:31:20','2020-06-12 14:31:28'),(3,'弗雷尔卓德',0,'2020-06-12 14:31:24','2020-06-12 14:31:22'),(4,'暗影岛',0,'2020-06-12 14:31:33','2020-06-12 14:31:35'),(5,'比尔吉沃特',0,'2020-06-12 14:31:37','2020-06-12 14:31:38'),(6,'祖安',0,'2020-06-12 14:31:40','2020-06-12 14:31:42'),(7,'艾欧尼亚',0,'2020-06-12 14:31:46','2020-06-12 14:31:48'),(8,'虚空',0,'2020-06-12 14:31:50','2020-06-12 14:31:53'),(9,'皮尔特沃夫',0,'2020-06-12 14:31:55','2020-06-12 14:32:05'),(10,'巨神峰',0,'2020-06-12 14:31:57','2020-06-12 14:32:07'),(11,'恕瑞玛',0,'2020-06-12 14:31:58','2020-06-12 14:32:11'),(12,'班德尔城',0,'2020-06-12 14:32:02','2020-06-12 14:32:13'),(13,'以绪塔尔',0,'2020-06-12 14:32:04','2020-06-12 14:32:16');

/*Table structure for table `lol_occupation` */

DROP TABLE IF EXISTS `lol_occupation`;

CREATE TABLE `lol_occupation` (
  `hc_id` int NOT NULL COMMENT '职业序号',
  `name_us` varchar(20) DEFAULT NULL COMMENT '职业名(英文)',
  `name_cn` varchar(20) DEFAULT NULL COMMENT '职业名(中文)',
  PRIMARY KEY (`hc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='英雄职业';

/*Data for the table `lol_occupation` */

insert  into `lol_occupation`(`hc_id`,`name_us`,`name_cn`) values (1,'adc','射手'),(2,'ap','法师'),(3,'assassin','刺客'),(4,'support','辅助'),(5,'tank','坦克'),(6,'warrior','战士');

/*Table structure for table `lol_routes` */

DROP TABLE IF EXISTS `lol_routes`;

CREATE TABLE `lol_routes` (
  `r_id` int NOT NULL COMMENT '分路编号',
  `route` varchar(10) DEFAULT NULL COMMENT '分路',
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `lol_routes` */

insert  into `lol_routes`(`r_id`,`route`) values (1,'上路'),(2,'打野'),(3,'中路'),(4,'下路'),(5,'辅助');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
