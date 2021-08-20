/*
 Navicat Premium Data Transfer

 Source Server         : mysql-local
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 20/08/2021 18:18:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `login_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登陆名',
  `login_pwd` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登陆密码',
  `admin_role_ids` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '后台用户角色ids,json格式',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `avatar` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `sex` tinyint(4) NULL DEFAULT 0 COMMENT '性别 0女1男',
  `address` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `birthday` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生日  格式为YYYY-MM-DD',
  `login_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录的token',
  `status` tinyint(5) NULL DEFAULT 0 COMMENT '状态：0:正常,1:冻结',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `mobile`(`mobile`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE,
  UNIQUE INDEX `login_name`(`login_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '后台用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, '15210684514', NULL, 'zang', '867cfaa78a91d31c464db8cf4dadba16', '[1]', 'zang', NULL, 0, NULL, NULL, 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ7XCJ1c2VySWRcIjoxLFwidXNlclNvdXJjZVwiOlwiYmFja2VuZFwifSIsImlhdCI6MTYyOTQ1MzIxOSwiaXNzIjoiemFuZ1NwcmluZ2Jvb3QiLCJleHAiOjE2MzIwNDUyMTl9.EnwlyJPqlarVDE1ZJT7BVzmsd-ahMOyZDO6oIQCRypw', 0, '2021-08-20 09:27:17', '2021-08-20 17:53:40');

-- ----------------------------
-- Table structure for admin_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_menu`;
CREATE TABLE `admin_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `pid` bigint(20) NOT NULL DEFAULT 0 COMMENT '上级菜单父id',
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名',
  `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由地址',
  `is_frame` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否外链  true是false否',
  `menu_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单类型(M目录C菜单F按钮)',
  `visible` bit(1) NOT NULL DEFAULT b'0' COMMENT '显示状态 true显示false隐藏',
  `component` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径',
  `perms` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '显示排序',
  `is_cache` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否缓存 true  false',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '菜单状态 0正常1删除',
  `remark` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '后台菜单权限' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of admin_menu
-- ----------------------------
INSERT INTO `admin_menu` VALUES (1, 0, '教务管理', 'eduM', 'education', b'1', 'M', b'0', NULL, NULL, 2, b'1', 0, NULL, '2021-03-17 16:42:27', '2021-03-17 16:42:27');
INSERT INTO `admin_menu` VALUES (6, 1, '班级管理', 'classM', 'class', b'1', 'C', b'0', 'education/class/index', 'education:class:list', 2, b'0', 0, NULL, '2021-03-17 16:42:27', '2021-03-17 16:42:27');
INSERT INTO `admin_menu` VALUES (21, 1, '教师管理', 'techM', 'teacher', b'1', 'C', b'0', 'education/teacher/index', 'education:teacher:list', 3, b'0', 0, NULL, '2021-03-19 14:16:38', '2021-03-19 14:16:38');
INSERT INTO `admin_menu` VALUES (25, 1, '新建班级', 'dict', 'addEditClass', b'1', 'C', b'1', 'education/class/addEditClass', 'education:class:add', 5, b'0', 0, NULL, '2021-03-23 15:48:26', '2021-03-23 15:48:26');
INSERT INTO `admin_menu` VALUES (27, 0, '题库管理', 'ques', 'repo', b'1', 'M', b'0', NULL, NULL, 3, b'1', 0, NULL, '2021-04-09 21:02:56', '2021-04-09 21:02:56');
INSERT INTO `admin_menu` VALUES (28, 27, '题目管理', 'queM', 'question', b'1', 'C', b'0', 'repo/question/index', 'repo:question:list', 1, b'0', 0, NULL, '2021-04-09 21:03:47', '2021-04-09 21:03:47');
INSERT INTO `admin_menu` VALUES (29, 27, '新增题目', 'documentation', 'addEditQuestion', b'1', 'C', b'1', 'repo/question/addEditQuestion', 'repo:question:add', 2, b'0', 0, NULL, '2021-04-09 21:05:05', '2021-04-09 21:05:05');
INSERT INTO `admin_menu` VALUES (30, 27, '试卷管理', 'paperM', 'testPaper', b'1', 'C', b'0', 'repo/testPaper/index', 'repo:testPaper:list', 3, b'0', 0, NULL, '2021-04-09 21:06:32', '2021-04-09 21:06:32');

-- ----------------------------
-- Table structure for admin_resource
-- ----------------------------
DROP TABLE IF EXISTS `admin_resource`;
CREATE TABLE `admin_resource`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源名称',
  `url` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源URL',
  `description` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `admin_resource_category_id` bigint(20) NULL DEFAULT NULL COMMENT '资源分类ID',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '0正常1删除',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户接口资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for admin_resource_category
-- ----------------------------
DROP TABLE IF EXISTS `admin_resource_category`;
CREATE TABLE `admin_resource_category`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源名称',
  `sort` int(4) NULL DEFAULT NULL COMMENT '排序',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '0正常1删除',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '后台用户接口资源分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for admin_role
-- ----------------------------
DROP TABLE IF EXISTS `admin_role`;
CREATE TABLE `admin_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '0正常1删除',
  `admin_menu_ids` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色菜单权限列表，json格式',
  `admin_resource_ids` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色接口资源权限列表，json格式',
  `remark` varchar(0) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '后台用户角色' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
