# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.23)
# Database: xdclass_order
# Generation Time: 2021-07-28 13:54:39 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table product_order
# ------------------------------------------------------------

DROP TABLE IF EXISTS `product_order`;

CREATE TABLE `product_order` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `out_trade_no` varchar(64) DEFAULT NULL COMMENT '订单唯一标识',
  `state` varchar(11) DEFAULT NULL COMMENT 'NEW 未支付订单,PAY已经支付订单,CANCEL超时取消订单',
  `create_time` datetime DEFAULT NULL COMMENT '订单生成时间',
  `total_amount` decimal(16,2) DEFAULT NULL COMMENT '订单总金额',
  `pay_amount` decimal(16,2) DEFAULT NULL COMMENT '订单实际支付价格',
  `pay_type` varchar(64) DEFAULT NULL COMMENT '支付类型，微信-银行-支付宝',
  `nickname` varchar(64) DEFAULT NULL COMMENT '昵称',
  `head_img` varchar(524) DEFAULT NULL COMMENT '头像',
  `user_id` bigint(11) DEFAULT NULL COMMENT '用户id',
  `del` int(5) DEFAULT '0' COMMENT '0表示未删除，1表示已经删除',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `order_type` varchar(32) DEFAULT NULL COMMENT '订单类型 DAILY普通单，PROMOTION促销订单',
  `receiver_address` varchar(1024) DEFAULT NULL COMMENT '收货地址 json存储',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `product_order` WRITE;
/*!40000 ALTER TABLE `product_order` DISABLE KEYS */;

INSERT INTO `product_order` (`id`, `out_trade_no`, `state`, `create_time`, `total_amount`, `pay_amount`, `pay_type`, `nickname`, `head_img`, `user_id`, `del`, `update_time`, `order_type`, `receiver_address`)
VALUES
	(6,'NAdgmGwJ24QIvB2OAUh7Amdje03ki4lX','PAY','2021-07-28 00:06:35',530.00,525.00,'ALIPAY','Anna小姐姐','https://wangjianhuan-1024shop.oss-cn-shenzhen.aliyuncs.com/user/2021/07/12/ab2da37343664d3e8a7e5fcf11bc9a4c.png',8,0,NULL,'DAILY','{\"city\":\"广州市\",\"defaultStatus\":1,\"detailAddress\":\"运营中心-老王隔壁88号\",\"id\":2,\"phone\":\"15677290653\",\"province\":\"广东省\",\"receiveName\":\"冰冰\",\"region\":\"天河区\",\"userId\":8}'),
	(7,'6jGuyM7eMQj3nABsLsXbqcp0cDLmp4Va','PAY','2021-07-28 11:23:36',530.00,525.00,'ALIPAY','Anna小姐姐','https://wangjianhuan-1024shop.oss-cn-shenzhen.aliyuncs.com/user/2021/07/12/ab2da37343664d3e8a7e5fcf11bc9a4c.png',8,0,NULL,'DAILY','{\"city\":\"广州市\",\"defaultStatus\":1,\"detailAddress\":\"运营中心-老王隔壁88号\",\"id\":2,\"phone\":\"15677290653\",\"province\":\"广东省\",\"receiveName\":\"冰冰\",\"region\":\"天河区\",\"userId\":8}');

/*!40000 ALTER TABLE `product_order` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table product_order_item
# ------------------------------------------------------------

DROP TABLE IF EXISTS `product_order_item`;

CREATE TABLE `product_order_item` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `product_order_id` bigint(11) DEFAULT NULL COMMENT '订单号',
  `out_trade_no` varchar(32) DEFAULT NULL,
  `product_id` bigint(11) DEFAULT NULL COMMENT '产品id',
  `product_name` varchar(128) DEFAULT NULL COMMENT '商品名称',
  `product_img` varchar(524) DEFAULT NULL COMMENT '商品图片',
  `buy_num` int(11) DEFAULT NULL COMMENT '购买数量',
  `create_time` datetime DEFAULT NULL,
  `total_amount` decimal(16,2) DEFAULT NULL COMMENT '购物项商品总价格',
  `amount` decimal(16,0) DEFAULT NULL COMMENT '购物项商品单价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `product_order_item` WRITE;
/*!40000 ALTER TABLE `product_order_item` DISABLE KEYS */;

INSERT INTO `product_order_item` (`id`, `product_order_id`, `out_trade_no`, `product_id`, `product_name`, `product_img`, `buy_num`, `create_time`, `total_amount`, `amount`)
VALUES
	(5,6,'NAdgmGwJ24QIvB2OAUh7Amdje03ki4lX',2,'技术人的杯子Linux','https://file.xdclass.net/video/2020/alibabacloud/zt-alibabacloud.png',2,'2021-07-28 00:06:35',84.00,42),
	(6,6,'NAdgmGwJ24QIvB2OAUh7Amdje03ki4lX',1,'小滴课堂抱枕','https://file.xdclass.net/video/2020/alibabacloud/zt-alibabacloud.png',2,'2021-07-28 00:06:35',446.00,223),
	(7,7,'6jGuyM7eMQj3nABsLsXbqcp0cDLmp4Va',2,'技术人的杯子Linux','https://file.xdclass.net/video/2020/alibabacloud/zt-alibabacloud.png',2,'2021-07-28 11:23:36',84.00,42),
	(8,7,'6jGuyM7eMQj3nABsLsXbqcp0cDLmp4Va',1,'小滴课堂抱枕','https://file.xdclass.net/video/2020/alibabacloud/zt-alibabacloud.png',2,'2021-07-28 11:23:36',446.00,223);

/*!40000 ALTER TABLE `product_order_item` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
