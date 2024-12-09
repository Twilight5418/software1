/*
 Navicat Premium Data Transfer

 Source Server         : 数据库  大学
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : software1

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 09/12/2024 13:56:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for notification
-- ----------------------------
DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '教科办发布通知的id',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '通知内容',
  `publish_time` datetime NOT NULL,
  `publisher_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notification
-- ----------------------------
INSERT INTO `notification` VALUES ('1864624560862584833', '课题申报通知', '各位同学，请注意本学期的课题申报时间。', '2024-12-05 18:55:13', 'P001');
INSERT INTO `notification` VALUES ('1864625324230172673', '课题申报通知', '各位同学，请注意本学期的课题申报时间。', '2024-12-05 18:58:15', 'P001');
INSERT INTO `notification` VALUES ('1864638523037958145', '课题申报通知', '各位同学，请注意本学期的课题申报时间。', '2024-12-05 19:50:42', 'P001');

-- ----------------------------
-- Table structure for review
-- ----------------------------
DROP TABLE IF EXISTS `review`;
CREATE TABLE `review`  (
  `review_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `thesis_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `teacher_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `reviewer_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `is_approved` tinyint(1) NOT NULL,
  PRIMARY KEY (`review_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of review
-- ----------------------------
INSERT INTO `review` VALUES ('2c88d3d367533dfbd256402802368e6f', '基于深度学习的图像识别技术研究', '张三', 'REVIEWER_NAME', 1);

-- ----------------------------
-- Table structure for thesis_proposal
-- ----------------------------
DROP TABLE IF EXISTS `thesis_proposal`;
CREATE TABLE `thesis_proposal`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `teacher_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `research_institute` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `education` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `age` int NOT NULL,
  `target_major` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `contact_info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `thesis_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of thesis_proposal
-- ----------------------------
INSERT INTO `thesis_proposal` VALUES ('2c88d3d367533dfbd256402802368e6f', '张三', '计算机科学研究所', '博士', 35, '人工智能', 'zhangsan@example.com', '基于深度学习的图像识别技术研究');
INSERT INTO `thesis_proposal` VALUES ('55b7dc74f789313756d1341e57a1e315', '张三', '计算机科学研究所', '博士', 35, '人工智能', 'zhangsan@example.com', '基于深度学习的图像识别技术研究');
INSERT INTO `thesis_proposal` VALUES ('735df26eb42117f4759ec7310ac911a7', '张三', '计算机科学研究所', '博士', 35, '人工智能', 'zhangsan@example.com', '基于深度学习的图像识别技术研究');
INSERT INTO `thesis_proposal` VALUES ('9f331ab00cbd21938fcb9bb9cf00cd9c', '1111', '计算机科学研究所', '博士', 35, '人工智能', 'zhangsan@example.com', '基于深度学习的图像识别技术研究');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT ' id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '姓名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '职位',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('022b37fe-afcb-4b5b-80ae-a49fa33b33fd', 'managerUser1', '$2a$10$gB8YAcJWpZhyZitsT.RfieBKXRePHP3rsnPzkVWrsAVb7AJmX6aFm', 'MANAGER');
INSERT INTO `user` VALUES ('1', '1', '1', 'student');
INSERT INTO `user` VALUES ('4f5cb22e-1e28-4cf7-8f5b-3b9dca03f11c', 'newUser', '$2a$10$hQqXw8IXPhreRf/FmPSHCuQTJ4TU58qEL3rK.WHZC3Zyqe2lw9JqG', 'student');
INSERT INTO `user` VALUES ('70321837-0dd2-4fb9-948e-9c582df6ddc6', 'managerUser', '$2a$10$/M6ME0GYXLVnUB4XvtorjOF7ew4fQw3tiMf7fe/xKB0rYSwa9EILy', 'MANAGER');

SET FOREIGN_KEY_CHECKS = 1;
