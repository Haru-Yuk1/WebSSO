/*
 Navicat Premium Data Transfer

 Source Server         : LocalHost
 Source Server Type    : MySQL
 Source Server Version : 50744 (5.7.44-log)
 Source Host           : localhost:3306
 Source Schema         : ssoweb

 Target Server Type    : MySQL
 Target Server Version : 50744 (5.7.44-log)
 File Encoding         : 65001

 Date: 02/12/2024 11:30:49
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for login_history
-- ----------------------------
DROP TABLE IF EXISTS `login_history`;
CREATE TABLE `login_history`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `logindate` timestamp NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of login_history
-- ----------------------------
INSERT INTO `login_history` VALUES ('zhangsan', '2024-11-29 20:20:39');
INSERT INTO `login_history` VALUES ('zhangsan', '2024-11-29 20:21:45');
INSERT INTO `login_history` VALUES ('zhangsan', '2024-11-29 20:26:09');
INSERT INTO `login_history` VALUES ('zhangsan', '2024-11-30 13:46:26');
INSERT INTO `login_history` VALUES ('zhangsan', '2024-11-30 15:23:27');
INSERT INTO `login_history` VALUES ('zhangsan', '2024-11-30 15:28:24');
INSERT INTO `login_history` VALUES ('zhangsan', '2024-11-30 15:29:00');
INSERT INTO `login_history` VALUES ('zhangsan', '2024-11-30 15:30:15');
INSERT INTO `login_history` VALUES ('zhangsan', '2024-11-30 15:39:15');
INSERT INTO `login_history` VALUES ('zhangsan', '2024-11-30 15:46:47');
INSERT INTO `login_history` VALUES ('zhangsan', '2024-11-30 15:47:17');
INSERT INTO `login_history` VALUES ('tian', '2024-11-30 15:47:52');
INSERT INTO `login_history` VALUES ('zhangsan', '2024-11-30 15:48:20');
INSERT INTO `login_history` VALUES ('zhangsan', '2024-11-30 15:54:09');
INSERT INTO `login_history` VALUES ('lisi', '2024-11-30 15:54:36');
INSERT INTO `login_history` VALUES ('zhangsan', '2024-11-30 16:07:21');
INSERT INTO `login_history` VALUES ('lisi', '2024-11-30 16:07:49');
INSERT INTO `login_history` VALUES ('zhangsan', '2024-11-30 16:11:27');
INSERT INTO `login_history` VALUES ('xiaoming', '2024-11-30 16:12:21');
INSERT INTO `login_history` VALUES ('zhangsan', '2024-11-30 16:12:50');
INSERT INTO `login_history` VALUES ('zhangsan', '2024-11-30 16:14:03');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `isDisabled` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('zhangsan', '123', 0);
INSERT INTO `user` VALUES ('lisi', '123', 0);
INSERT INTO `user` VALUES ('wangwu', '123', 0);
INSERT INTO `user` VALUES ('lala', 'lala', 0);
INSERT INTO `user` VALUES ('tian', '123', NULL);
INSERT INTO `user` VALUES ('xiaoming', '123', 1);

SET FOREIGN_KEY_CHECKS = 1;
