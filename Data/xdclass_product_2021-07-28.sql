# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.23)
# Database: xdclass_product
# Generation Time: 2021-07-28 13:54:49 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table banner
# ------------------------------------------------------------

DROP TABLE IF EXISTS `banner`;

CREATE TABLE `banner` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `img` varchar(524) DEFAULT NULL COMMENT '图片',
  `url` varchar(524) DEFAULT NULL COMMENT '跳转地址',
  `weight` int(11) DEFAULT NULL COMMENT '权重',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `banner` WRITE;
/*!40000 ALTER TABLE `banner` DISABLE KEYS */;

INSERT INTO `banner` (`id`, `img`, `url`, `weight`)
VALUES
	(1,'https://file.xdclass.net/video/2020/alibabacloud/zx-lbt.jpeg','https://m.xdclass.net/#/member',1),
	(2,'https://file.xdclass.net/video/%E5%AE%98%E7%BD%91%E8%BD%AE%E6%92%AD%E5%9B%BE/20%E5%B9%B4%E5%8F%8C11%E9%98%BF%E9%87%8C%E4%BA%91/fc-lbt.jpeg','https://www.aliyun.com/1111/pintuan-share?ptCode=MTcwMTY3MzEyMjc5MDU2MHx8MTE0fDE%3D&userCode=r5saexap',3),
	(3,'https://file.xdclass.net/video/%E5%AE%98%E7%BD%91%E8%BD%AE%E6%92%AD%E5%9B%BE/20%E5%B9%B4%E5%8F%8C11%E9%98%BF%E9%87%8C%E4%BA%91/FAN-lbu-vip.jpeg','https://file.xdclass.net/video/%E5%AE%98%E7%BD%91%E8%BD%AE%E6%92%AD%E5%9B%BE/Nginx.jpeg',2);

/*!40000 ALTER TABLE `banner` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table product
# ------------------------------------------------------------

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL COMMENT '标题',
  `cover_img` varchar(128) DEFAULT NULL COMMENT '封面图',
  `detail` varchar(256) DEFAULT '' COMMENT '详情',
  `old_amount` decimal(16,2) DEFAULT NULL COMMENT '老价格',
  `amount` decimal(16,2) DEFAULT NULL COMMENT '新价格',
  `stock` int(11) DEFAULT NULL COMMENT '库存',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `lock_stock` int(11) DEFAULT '0' COMMENT '锁定库存',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;

INSERT INTO `product` (`id`, `title`, `cover_img`, `detail`, `old_amount`, `amount`, `stock`, `create_time`, `lock_stock`)
VALUES
	(1,'小滴课堂抱枕','https://file.xdclass.net/video/2020/alibabacloud/zt-alibabacloud.png','https://file.xdclass.net/video/2021/60-MLS/summary.jpeg',32.00,223.00,100,'2021-09-12 00:00:00',64),
	(2,'技术人的杯子Linux','https://file.xdclass.net/video/2020/alibabacloud/zt-alibabacloud.png','https://file.xdclass.net/video/2021/59-Postman/summary.jpeg',432.00,42.00,20,'2021-03-12 00:00:00',14),
	(3,'技术人的杯子docker','https://file.xdclass.net/video/2020/alibabacloud/zt-alibabacloud.png','https://file.xdclass.net/video/2021/60-MLS/summary.jpeg',35.00,12.00,20,'2022-09-22 00:00:00',13),
	(4,'技术人的杯子git','https://file.xdclass.net/video/2020/alibabacloud/zt-alibabacloud.png','https://file.xdclass.net/video/2021/60-MLS/summary.jpeg',12.00,14.00,20,'2022-11-12 00:00:00',2);

/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table product_task
# ------------------------------------------------------------

DROP TABLE IF EXISTS `product_task`;

CREATE TABLE `product_task` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `product_id` bigint(11) DEFAULT NULL COMMENT '商品id',
  `buy_num` int(11) DEFAULT NULL COMMENT '购买数量',
  `product_name` varchar(128) DEFAULT NULL COMMENT '商品标题',
  `lock_state` varchar(32) DEFAULT NULL COMMENT '锁定状态锁定LOCK  完成FINISH-取消CANCEL',
  `out_trade_no` varchar(32) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `product_task` WRITE;
/*!40000 ALTER TABLE `product_task` DISABLE KEYS */;

INSERT INTO `product_task` (`id`, `product_id`, `buy_num`, `product_name`, `lock_state`, `out_trade_no`, `create_time`)
VALUES
	(10,2,2,NULL,'FINISH','NAdgmGwJ24QIvB2OAUh7Amdje03ki4lX','2021-07-28 00:06:35'),
	(11,1,2,NULL,'FINISH','NAdgmGwJ24QIvB2OAUh7Amdje03ki4lX','2021-07-28 00:06:35'),
	(12,2,2,NULL,'FINISH','6jGuyM7eMQj3nABsLsXbqcp0cDLmp4Va','2021-07-28 11:23:36'),
	(13,1,2,NULL,'FINISH','6jGuyM7eMQj3nABsLsXbqcp0cDLmp4Va','2021-07-28 11:23:36');

/*!40000 ALTER TABLE `product_task` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table undo_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `undo_log`;

CREATE TABLE `undo_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) NOT NULL,
  `context` varchar(128) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
