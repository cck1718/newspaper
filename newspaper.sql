/*
 Navicat Premium Data Transfer

 Source Server         : ceshi
 Source Server Type    : MySQL
 Source Server Version : 80030 (8.0.30)
 Source Host           : localhost:3306
 Source Schema         : newspaper

 Target Server Type    : MySQL
 Target Server Version : 80030 (8.0.30)
 File Encoding         : 65001

 Date: 13/10/2022 12:30:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '内容',
  `summary` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `category_id` int NULL DEFAULT NULL COMMENT '类别id',
  `is_top` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '是否置顶',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '1' COMMENT '状态 1是草稿 0是发布',
  `create_by` int NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_by` int NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (12, 'spring', 'springspringspringspringspringspringspringspringspringspringspringspringspringspringspringspringspringspringspringspringspringspringspringspringspringspringspringspringspringspringspringspring', 'spring', 7, '0', '0', 1, '2022-10-12 04:42:25', 1, '2022-10-12 06:11:03');
INSERT INTO `article` VALUES (14, '12312321', '去问驱蚊器委屈为请问请问恶趣味恶趣味请问请问请问企鹅', '12312312', 2, '0', '0', 1, '2022-10-12 06:47:23', 1, '2022-10-12 06:59:29');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '历史');
INSERT INTO `category` VALUES (2, '军事');
INSERT INTO `category` VALUES (3, '科技');
INSERT INTO `category` VALUES (4, '养生');
INSERT INTO `category` VALUES (5, '娱乐');
INSERT INTO `category` VALUES (6, '文学');
INSERT INTO `category` VALUES (7, '编程');
INSERT INTO `category` VALUES (8, '汽车');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `permission` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (1, '文章发布', 'release');
INSERT INTO `menu` VALUES (2, '个人管理', 'personal');
INSERT INTO `menu` VALUES (3, '文章管理', 'content');
INSERT INTO `menu` VALUES (4, '管理用户', 'administration');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '用户', 'USER');
INSERT INTO `role` VALUES (2, '管理员', 'ADMIN');

-- ----------------------------
-- Table structure for rolebymenu
-- ----------------------------
DROP TABLE IF EXISTS `rolebymenu`;
CREATE TABLE `rolebymenu`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `menu_id` int NULL DEFAULT NULL,
  `role_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `menu_id`(`menu_id` ASC) USING BTREE,
  INDEX `role_id`(`role_id` ASC) USING BTREE,
  CONSTRAINT `rolebymenu_ibfk_1` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `rolebymenu_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rolebymenu
-- ----------------------------
INSERT INTO `rolebymenu` VALUES (1, 1, 1);
INSERT INTO `rolebymenu` VALUES (2, 2, 1);
INSERT INTO `rolebymenu` VALUES (3, 3, 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'zhangsan', '$2a$10$QqCyfrLeq1dVHS0EmiWHie7CFOjIIjrE8vq6vNgu/V2uye4q.wfv6', '18773768317', '2022-10-10 08:47:41', '2022-10-11 08:40:12');
INSERT INTO `user` VALUES (4, 'lisi', '$2a$10$U8vzX1a.xQxBfwe6QfnI4eNurkD73m.IXl2k8/JNaJ4mSNnq6Tx72', '18387872957', '2022-10-11 04:47:26', NULL);
INSERT INTO `user` VALUES (5, '0731', '$2a$10$ixMyPO7G.lRb8f6NPPWP9O6HfENtJZpeys/N1DuFssTZI/kKTM5f.', '12387674653', '2022-10-11 05:38:13', NULL);
INSERT INTO `user` VALUES (6, 'user', '$2a$10$VLnMgFOr0/MLWqto..LTHurLc185gA/iBFdN37AIBOkAYUg5YKRxS', '18876475598', '2022-10-12 12:25:53', NULL);

-- ----------------------------
-- Table structure for userbyrole
-- ----------------------------
DROP TABLE IF EXISTS `userbyrole`;
CREATE TABLE `userbyrole`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NULL DEFAULT NULL,
  `role_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id` ASC) USING BTREE,
  INDEX `role_id`(`role_id` ASC) USING BTREE,
  CONSTRAINT `userbyrole_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `userbyrole_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userbyrole
-- ----------------------------
INSERT INTO `userbyrole` VALUES (1, 1, 1);
INSERT INTO `userbyrole` VALUES (2, 4, 1);
INSERT INTO `userbyrole` VALUES (3, 5, 1);
INSERT INTO `userbyrole` VALUES (4, 6, 1);

SET FOREIGN_KEY_CHECKS = 1;
