/*
SQLyog Professional v12.09 (64 bit)
MySQL - 5.6.5-m8 : Database - javaweb_kkk
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`javaweb_kkk` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `javaweb_kkk`;

/*Table structure for table `actor_t` */

DROP TABLE IF EXISTS `actor_t`;

CREATE TABLE `actor_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `actor_name` varchar(50) DEFAULT NULL COMMENT '演员名称',
  `actor_img` varchar(200) DEFAULT NULL COMMENT '演员图片位置',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='演员表';

/*Table structure for table `area_dict_t` */

DROP TABLE IF EXISTS `area_dict_t`;

CREATE TABLE `area_dict_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `show_name` varchar(100) DEFAULT NULL COMMENT '显示名称',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='地域信息表';

/*Table structure for table `banner_t` */

DROP TABLE IF EXISTS `banner_t`;

CREATE TABLE `banner_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `banner_address` varchar(50) DEFAULT NULL COMMENT 'banner图存放路径',
  `banner_url` varchar(200) DEFAULT NULL COMMENT 'banner点击跳转url',
  `is_valid` int(11) DEFAULT '0' COMMENT '是否弃用 0-失效,1-失效',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='banner信息表';

/*Table structure for table `brand_dict_t` */

DROP TABLE IF EXISTS `brand_dict_t`;

CREATE TABLE `brand_dict_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `show_name` varchar(100) DEFAULT NULL COMMENT '显示名称',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='品牌信息表';

/*Table structure for table `cat_dict_t` */

DROP TABLE IF EXISTS `cat_dict_t`;

CREATE TABLE `cat_dict_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `show_name` varchar(100) DEFAULT NULL COMMENT '显示名称',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='类型信息表';

/*Table structure for table `cinema_t` */

DROP TABLE IF EXISTS `cinema_t`;

CREATE TABLE `cinema_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `cinema_name` varchar(50) DEFAULT NULL COMMENT '影院名称',
  `cinema_phone` varchar(50) DEFAULT NULL COMMENT '影院电话',
  `brand_id` int(11) DEFAULT NULL COMMENT '品牌编号',
  `area_id` int(11) DEFAULT NULL COMMENT '地域编号',
  `hall_ids` varchar(200) DEFAULT NULL COMMENT '包含的影厅类型,以#作为分割',
  `img_address` varchar(500) DEFAULT NULL COMMENT '影院图片地址',
  `cinema_address` varchar(200) DEFAULT NULL COMMENT '影院地址',
  `minimum_price` int(11) DEFAULT '0' COMMENT '最低票价',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='影院信息表';

/*Table structure for table `field_t` */

DROP TABLE IF EXISTS `field_t`;

CREATE TABLE `field_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `cinema_id` int(11) DEFAULT NULL COMMENT '影院编号',
  `film_id` int(11) DEFAULT NULL COMMENT '电影编号',
  `begin_time` varchar(50) DEFAULT NULL COMMENT '开始时间',
  `end_time` varchar(50) DEFAULT NULL COMMENT '结束时间',
  `hall_id` int(11) DEFAULT NULL COMMENT '放映厅类型编号',
  `hall_name` varchar(200) DEFAULT NULL COMMENT '放映厅名称',
  `price` int(11) DEFAULT NULL COMMENT '票价',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='放映场次表';

/*Table structure for table `film_actor_t` */

DROP TABLE IF EXISTS `film_actor_t`;

CREATE TABLE `film_actor_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `film_id` int(11) DEFAULT NULL COMMENT '影片编号,对应mooc_film_t',
  `actor_id` int(11) DEFAULT NULL COMMENT '演员编号,对应mooc_actor_t',
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='影片与演员映射表';

/*Table structure for table `film_info_t` */

DROP TABLE IF EXISTS `film_info_t`;

CREATE TABLE `film_info_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `film_id` varchar(100) DEFAULT NULL COMMENT '影片编号',
  `film_en_name` varchar(50) DEFAULT NULL COMMENT '影片英文名称',
  `film_score` varchar(20) DEFAULT NULL COMMENT '影片评分',
  `film_score_num` int(11) DEFAULT NULL COMMENT '评分人数,以万为单位',
  `film_length` int(11) DEFAULT NULL COMMENT '播放时长，以分钟为单位，不足取整',
  `biography` text COMMENT '影片介绍',
  `director_id` int(11) DEFAULT NULL COMMENT '导演编号',
  `film_imgs` text COMMENT '影片图片集地址,多个图片以逗号分隔',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='影片主表';

/*Table structure for table `film_t` */

DROP TABLE IF EXISTS `film_t`;

CREATE TABLE `film_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `film_name` varchar(100) DEFAULT NULL COMMENT '影片名称',
  `film_type` int(11) DEFAULT NULL COMMENT '片源类型: 0-2D,1-3D,2-3DIMAX,4-无',
  `img_address` varchar(200) DEFAULT NULL COMMENT '影片主图地址',
  `film_score` varchar(20) DEFAULT NULL COMMENT '影片评分',
  `film_preSaleNum` int(11) DEFAULT NULL COMMENT '影片预售数量',
  `film_box_office` int(11) DEFAULT NULL COMMENT '影片票房：每日更新，以万为单位',
  `film_source` int(11) DEFAULT NULL COMMENT '影片片源，参照片源字典表',
  `film_cats` varchar(50) DEFAULT NULL COMMENT '影片分类，参照分类表,多个分类以#分割',
  `film_area` int(11) DEFAULT NULL COMMENT '影片区域，参照区域表',
  `film_date` int(11) DEFAULT NULL COMMENT '影片上映年代，参照年代表',
  `film_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '影片上映时间',
  `film_status` int(11) DEFAULT NULL COMMENT '影片状态,1-正在热映，2-即将上映，3-经典影片',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='影片主表';

/*Table structure for table `hall_dict_t` */

DROP TABLE IF EXISTS `hall_dict_t`;

CREATE TABLE `hall_dict_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `show_name` varchar(100) DEFAULT NULL COMMENT '显示名称',
  `seat_address` varchar(200) DEFAULT NULL COMMENT '座位文件存放地址',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='地域信息表';

/*Table structure for table `hall_film_info_t` */

DROP TABLE IF EXISTS `hall_film_info_t`;

CREATE TABLE `hall_film_info_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `film_id` int(11) DEFAULT NULL COMMENT '电影编号',
  `film_name` varchar(50) DEFAULT NULL COMMENT '电影名称',
  `film_length` varchar(50) DEFAULT NULL COMMENT '电影时长',
  `film_cats` varchar(200) DEFAULT NULL COMMENT '电影类型',
  `film_language` varchar(50) DEFAULT NULL COMMENT '电影语言',
  `actors` varchar(200) DEFAULT NULL COMMENT '演员列表',
  `img_address` varchar(500) DEFAULT NULL COMMENT '图片地址',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='影厅电影信息表';

/*Table structure for table `order_t` */

DROP TABLE IF EXISTS `order_t`;

CREATE TABLE `order_t` (
  `UUID` varchar(100) DEFAULT NULL COMMENT '主键编号',
  `cinema_id` int(11) DEFAULT NULL COMMENT '影院编号',
  `field_id` int(11) DEFAULT NULL COMMENT '放映场次编号',
  `film_id` int(11) DEFAULT NULL COMMENT '电影编号',
  `seats_ids` varchar(50) DEFAULT NULL COMMENT '已售座位编号',
  `seats_name` varchar(200) DEFAULT NULL COMMENT '已售座位名称',
  `film_price` double DEFAULT NULL COMMENT '影片售价',
  `order_price` double DEFAULT NULL COMMENT '订单总金额',
  `order_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
  `order_user` varchar(11) DEFAULT NULL COMMENT '下单人',
  `order_status` int(11) DEFAULT '0' COMMENT '0-待支付,1-已支付,2-已关闭'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单信息表';

/*Table structure for table `source_dict_t` */

DROP TABLE IF EXISTS `source_dict_t`;

CREATE TABLE `source_dict_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `show_name` varchar(100) DEFAULT NULL COMMENT '显示名称',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='区域信息表';

/*Table structure for table `tb_comment` */

DROP TABLE IF EXISTS `tb_comment`;

CREATE TABLE `tb_comment` (
  `id` varchar(100) DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `publishtime` varchar(100) DEFAULT NULL,
  `userid` varchar(100) DEFAULT NULL,
  `nickname` varchar(100) DEFAULT NULL,
  `visits` varchar(100) DEFAULT NULL,
  `thumbup` varchar(100) DEFAULT NULL,
  `share` varchar(100) DEFAULT NULL,
  `comment` varchar(100) DEFAULT NULL,
  `state` varchar(100) DEFAULT NULL,
  `parentid` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `tb_label` */

DROP TABLE IF EXISTS `tb_label`;

CREATE TABLE `tb_label` (
  `id` varchar(100) NOT NULL,
  `state` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `mobile` varchar(100) DEFAULT NULL,
  `nickname` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `birthday` varchar(100) DEFAULT NULL,
  `head_address` text,
  `life_state` varchar(100) DEFAULT NULL,
  `biography` varchar(100) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `sex` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


/*Table structure for table `year_dict_t` */

DROP TABLE IF EXISTS `year_dict_t`;

CREATE TABLE `year_dict_t` (
  `UUID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `show_name` varchar(100) DEFAULT NULL COMMENT '显示名称',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='年代信息表';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
