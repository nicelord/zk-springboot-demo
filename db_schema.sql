/*
 Navicat Premium Data Transfer

 Source Server         : local_mysl
 Source Server Type    : MySQL
 Source Server Version : 100130
 Source Host           : localhost:3306
 Source Schema         : zk-springboot-demo

 Target Server Type    : MySQL
 Target Server Version : 100130
 File Encoding         : 65001

 Date: 03/06/2021 10:04:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for db_migration
-- ----------------------------
DROP TABLE IF EXISTS `db_migration`;
CREATE TABLE `db_migration`  (
  `id` int(11) NOT NULL,
  `mtype` varchar(1) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `mstatus` varchar(10) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `mversion` varchar(150) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `mcomment` varchar(150) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `mchecksum` int(11) NOT NULL,
  `run_on` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `run_by` varchar(30) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `run_time` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of db_migration
-- ----------------------------
INSERT INTO `db_migration` VALUES (0, 'I', 'SUCCESS', '0', '<init>', 0, '2021-05-28 10:27:11', 'rejam', 0);
INSERT INTO `db_migration` VALUES (1, 'V', 'SUCCESS', '1.0', 'init', 1425247511, '2021-05-28 10:27:11', 'rejam', 19);
INSERT INTO `db_migration` VALUES (2, 'V', 'SUCCESS', '1.1', 'init', 1183233556, '2021-05-28 10:27:11', 'rejam', 41);
INSERT INTO `db_migration` VALUES (3, 'V', 'SUCCESS', '1.2', 'init', -491442995, '2021-05-28 10:30:15', 'rejam', 39);
INSERT INTO `db_migration` VALUES (4, 'V', 'SUCCESS', '1.3', 'init', 2044357375, '2021-05-28 10:54:59', 'rejam', 44);
INSERT INTO `db_migration` VALUES (5, 'V', 'SUCCESS', '1.4', 'add_user_role', 1410773534, '2021-05-28 17:28:07', 'rejam', 34);
INSERT INTO `db_migration` VALUES (6, 'V', 'SUCCESS', '1.5', 'dropsFor_1.4', -1835652442, '2021-05-28 17:29:03', 'rejam', 104);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `email` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `role` varchar(5) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT 'USER',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uq_users_username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'admin', 'admin', 'admin@admin.com', 'ADMIN');
INSERT INTO `users` VALUES (3, 'user4', '123456', 'user2@gmail.com', 'USER');

SET FOREIGN_KEY_CHECKS = 1;
