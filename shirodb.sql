/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : shirodb

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 25/07/2023 13:46:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for permissions
-- ----------------------------
DROP TABLE IF EXISTS `permissions`;
CREATE TABLE `permissions` (
  `id` bigint NOT NULL COMMENT '编号',
  `name` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限名',
  `info` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限信息',
  `desc` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of permissions
-- ----------------------------
BEGIN;
INSERT INTO `permissions` VALUES (1, '删除用户', 'user:delete', '删除用户');
INSERT INTO `permissions` VALUES (2, '新增用户', 'user:add', '新增用户');
INSERT INTO `permissions` VALUES (3, '修改用户', 'user:edit', '修改用户');
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint NOT NULL COMMENT '编号',
  `name` varchar(30) CHARACTER SET utf8mb3 DEFAULT NULL COMMENT '角色名',
  `desc` varchar(50) CHARACTER SET utf8mb3 DEFAULT NULL COMMENT '描述',
  `realname` varchar(20) CHARACTER SET utf8mb3 DEFAULT NULL COMMENT '角色显示名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, 'admin', '所有权限', '管理员');
INSERT INTO `role` VALUES (2, 'userMag', '用户管理权限', '用户管理');
COMMIT;

-- ----------------------------
-- Table structure for role_ps
-- ----------------------------
DROP TABLE IF EXISTS `role_ps`;
CREATE TABLE `role_ps` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `rid` bigint DEFAULT NULL,
  `pid` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of role_ps
-- ----------------------------
BEGIN;
INSERT INTO `role_ps` VALUES (1, 1, 1);
INSERT INTO `role_ps` VALUES (2, 1, 2);
INSERT INTO `role_ps` VALUES (3, 1, 3);
COMMIT;

-- ----------------------------
-- Table structure for role_user
-- ----------------------------
DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user` (
  `id` bigint NOT NULL,
  `uid` bigint NOT NULL,
  `rid` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of role_user
-- ----------------------------
BEGIN;
INSERT INTO `role_user` VALUES (1, 1, 1);
INSERT INTO `role_user` VALUES (2, 1, 2);
INSERT INTO `role_user` VALUES (3, 2, 2);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8mb3 DEFAULT NULL,
  `pwd` varchar(50) CHARACTER SET utf8mb3 DEFAULT NULL,
  `rid` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, '张三', '7174f64b13022acd3c56e2781e098a5f', NULL);
INSERT INTO `user` VALUES (2, '李四', '7174f64b13022acd3c56e2781e098a5f', NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
