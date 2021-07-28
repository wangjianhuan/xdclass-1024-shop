# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.23)
# Database: xdclass_coupon
# Generation Time: 2021-07-28 13:54:30 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table coupon
# ------------------------------------------------------------

DROP TABLE IF EXISTS `coupon`;

CREATE TABLE `coupon` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `category` varchar(11) DEFAULT NULL COMMENT '优惠卷类型[NEW_USER注册赠券，TASK任务卷，PROMOTION促销劵]',
  `publish` varchar(11) DEFAULT NULL COMMENT '发布状态, PUBLISH发布，DRAFT草稿，OFFLINE下线',
  `coupon_img` varchar(524) DEFAULT NULL COMMENT '优惠券图片',
  `coupon_title` varchar(128) DEFAULT NULL COMMENT '优惠券标题',
  `price` decimal(16,2) DEFAULT NULL COMMENT '抵扣价格',
  `user_limit` int(11) DEFAULT NULL COMMENT '每人限制张数',
  `start_time` datetime DEFAULT NULL COMMENT '优惠券开始有效时间',
  `end_time` datetime DEFAULT NULL COMMENT '优惠券失效时间',
  `publish_count` int(11) DEFAULT NULL COMMENT '优惠券总量',
  `stock` int(11) DEFAULT '0' COMMENT '库存',
  `create_time` datetime DEFAULT NULL,
  `condition_price` decimal(16,2) DEFAULT NULL COMMENT '满多少才可以使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `coupon` WRITE;
/*!40000 ALTER TABLE `coupon` DISABLE KEYS */;

INSERT INTO `coupon` (`id`, `category`, `publish`, `coupon_img`, `coupon_title`, `price`, `user_limit`, `start_time`, `end_time`, `publish_count`, `stock`, `create_time`, `condition_price`)
VALUES
	(18,'NEW_USER','PUBLISH','https://file.xdclass.net/video/2020/alibabacloud/zt-alibabacloud.png','永久有效-新人注册-0元满减-5元抵扣劵-限领取1张-不可叠加使用',5.00,1,'2000-01-01 00:00:00','2099-01-29 00:00:00',100000000,99999983,'2020-12-26 16:33:02',0.00),
	(19,'PROMOTION','PUBLISH','https://file.xdclass.net/video/2020/alibabacloud/zt-alibabacloud.png','有效中-21年1月到25年1月-20元满减-5元抵扣劵-限领取2张-不可叠加使用',5.00,110,'2000-01-29 00:00:00','2025-01-29 00:00:00',1000,81,'2020-12-26 16:33:03',20.00),
	(20,'PROMOTION','PUBLISH','https://file.xdclass.net/video/2020/alibabacloud/zt-alibabacloud.png','有效中-20年8月到21年9月-商品id1-8.8元抵扣劵-限领取2张-不可叠加使用',8.80,2,'2020-08-01 00:00:00','2021-09-29 00:00:00',100,96,'2020-12-26 16:33:03',0.00),
	(21,'PROMOTION','PUBLISH','https://file.xdclass.net/video/2020/alibabacloud/zt-alibabacloud.png','有效中-20年8月到21年9月-商品id2-9.9元抵扣劵-限领取2张-可叠加使用',8.80,2,'2020-08-01 00:00:00','2021-09-29 00:00:00',100,96,'2020-12-26 16:33:03',0.00),
	(22,'PROMOTION','PUBLISH','https://file.xdclass.net/video/2020/alibabacloud/zt-alibabacloud.png','过期-20年8月到20年9月-商品id3-6元抵扣劵-限领取1张-可叠加使用',6.00,1,'2020-08-01 00:00:00','2020-09-29 00:00:00',100,100,'2020-12-26 16:33:03',0.00);

/*!40000 ALTER TABLE `coupon` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table coupon_record
# ------------------------------------------------------------

DROP TABLE IF EXISTS `coupon_record`;

CREATE TABLE `coupon_record` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `coupon_id` bigint(11) DEFAULT NULL COMMENT '优惠券id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间获得时间',
  `use_state` varchar(32) DEFAULT NULL COMMENT '使用状态  可用 NEW,已使用USED,过期 EXPIRED;',
  `user_id` bigint(11) DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(128) DEFAULT NULL COMMENT '用户昵称',
  `coupon_title` varchar(128) DEFAULT NULL COMMENT '优惠券标题',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `order_id` bigint(11) DEFAULT NULL COMMENT '订单id',
  `price` decimal(16,2) DEFAULT NULL COMMENT '抵扣价格',
  `condition_price` decimal(16,2) DEFAULT NULL COMMENT '满多少才可以使用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `coupon_record` WRITE;
/*!40000 ALTER TABLE `coupon_record` DISABLE KEYS */;

INSERT INTO `coupon_record` (`id`, `coupon_id`, `create_time`, `use_state`, `user_id`, `user_name`, `coupon_title`, `start_time`, `end_time`, `order_id`, `price`, `condition_price`)
VALUES
	(1,19,'2021-07-24 12:55:24','USED',5,'Anna小姐姐','有效中-21年1月到25年1月-20元满减-5元抵扣劵-限领取2张-不可叠加使用','2000-01-29 00:00:00','2025-01-29 00:00:00',NULL,5.00,20.00),
	(11,18,'2021-07-21 22:55:57','USED',8,'Anna小姐姐','永久有效-新人注册-0元满减-5元抵扣劵-限领取1张-不可叠加使用','2000-01-01 00:00:00','2099-01-29 00:00:00',NULL,5.00,0.00),
	(12,18,'2021-07-25 19:26:52','NEW',3,'wangjianhuan','永久有效-新人注册-0元满减-5元抵扣劵-限领取1张-不可叠加使用','2000-01-01 00:00:00','2099-01-29 00:00:00',NULL,5.00,0.00),
	(13,19,'2021-07-25 19:27:05','NEW',5,'Anna小姐姐','有效中-21年1月到25年1月-20元满减-5元抵扣劵-限领取2张-不可叠加使用','2000-01-29 00:00:00','2025-01-29 00:00:00',NULL,5.00,20.00),
	(14,19,'2021-07-25 19:27:06','NEW',5,'Anna小姐姐','有效中-21年1月到25年1月-20元满减-5元抵扣劵-限领取2张-不可叠加使用','2000-01-29 00:00:00','2025-01-29 00:00:00',NULL,5.00,20.00),
	(15,19,'2021-07-25 19:27:06','NEW',5,'Anna小姐姐','有效中-21年1月到25年1月-20元满减-5元抵扣劵-限领取2张-不可叠加使用','2000-01-29 00:00:00','2025-01-29 00:00:00',NULL,5.00,20.00),
	(16,19,'2021-07-25 19:27:07','NEW',5,'Anna小姐姐','有效中-21年1月到25年1月-20元满减-5元抵扣劵-限领取2张-不可叠加使用','2000-01-29 00:00:00','2025-01-29 00:00:00',NULL,5.00,20.00),
	(17,19,'2021-07-25 19:27:07','NEW',5,'Anna小姐姐','有效中-21年1月到25年1月-20元满减-5元抵扣劵-限领取2张-不可叠加使用','2000-01-29 00:00:00','2025-01-29 00:00:00',NULL,5.00,20.00),
	(18,19,'2021-07-25 19:27:08','NEW',5,'Anna小姐姐','有效中-21年1月到25年1月-20元满减-5元抵扣劵-限领取2张-不可叠加使用','2000-01-29 00:00:00','2025-01-29 00:00:00',NULL,5.00,20.00),
	(19,19,'2021-07-27 18:43:09','USED',8,'Anna小姐姐','有效中-21年1月到25年1月-20元满减-5元抵扣劵-限领取2张-不可叠加使用','2000-01-29 00:00:00','2025-01-29 00:00:00',NULL,5.00,20.00);

/*!40000 ALTER TABLE `coupon_record` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table coupon_task
# ------------------------------------------------------------

DROP TABLE IF EXISTS `coupon_task`;

CREATE TABLE `coupon_task` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `coupon_record_id` bigint(11) DEFAULT NULL COMMENT '优惠券记录id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `out_trade_no` varchar(64) DEFAULT NULL COMMENT '订单号',
  `lock_state` varchar(32) DEFAULT NULL COMMENT '锁定状态 锁定LOCK-完成FINISH 取消CANCEL',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `coupon_task` WRITE;
/*!40000 ALTER TABLE `coupon_task` DISABLE KEYS */;

INSERT INTO `coupon_task` (`id`, `coupon_record_id`, `create_time`, `out_trade_no`, `lock_state`)
VALUES
	(7,19,'2021-07-28 00:06:35','NAdgmGwJ24QIvB2OAUh7Amdje03ki4lX','FINISH'),
	(8,11,'2021-07-28 11:23:36','6jGuyM7eMQj3nABsLsXbqcp0cDLmp4Va','FINISH');

/*!40000 ALTER TABLE `coupon_task` ENABLE KEYS */;
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
