/*
Navicat MySQL Data Transfer

Source Server         : Mysql60
Source Server Version : 60011
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 60011
File Encoding         : 65001

Date: 2015-07-19 10:48:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for students
-- ----------------------------
DROP TABLE IF EXISTS `students`;
CREATE TABLE `students` (
  `sid` varchar(8) NOT NULL,
  `sname` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of students
-- ----------------------------
INSERT INTO `students` VALUES ('S0000001', '张三丰', '男', '2015-06-24', '武当山');
INSERT INTO `students` VALUES ('S0000002', '张无忌', '男', '2015-06-24', '武当山');
INSERT INTO `students` VALUES ('S0000004', '李白', '女', '2015-06-06', '西安长安区');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'zhangsan', '123456');

-- ----------------------------
-- Procedure structure for myproc
-- ----------------------------
DROP PROCEDURE IF EXISTS `myproc`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `myproc`(OUT s int)
BEGIN
select COUNT(*) into s from users;
END
;;
DELIMITER ;

-- ----------------------------
-- Procedure structure for myproc2
-- ----------------------------
DROP PROCEDURE IF EXISTS `myproc2`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `myproc2`(IN num int)
BEGIN
SELECT num;
SET num=num+1;
SELECT num;
END
;;
DELIMITER ;
