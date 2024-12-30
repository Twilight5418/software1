/*
 Navicat Premium Dump SQL

 Source Server         : 校内
 Source Server Type    : MySQL
 Source Server Version : 90001 (9.0.1)
 Source Host           : localhost:3306
 Source Schema         : software1

 Target Server Type    : MySQL
 Target Server Version : 90001 (9.0.1)
 File Encoding         : 65001

 Date: 30/12/2024 14:12:02
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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

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
  `is_approved` bit(1) NULL DEFAULT NULL,
  PRIMARY KEY (`review_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of review
-- ----------------------------
INSERT INTO `review` VALUES ('2c88d3d367533dfbd256402802368e6f', '基于深度学习的图像识别技术研究', '张三', 'REVIEWER_NAME', b'1');
INSERT INTO `review` VALUES ('55b7dc74f789313756d1341e57a1e315', '基于深度学习的图像识别技术研究', '张三', '111111', b'0');
INSERT INTO `review` VALUES ('735df26eb42117f4759ec7310ac911a7', '基于深度学习的图像识别技术研究', '张三', '111111', b'0');
INSERT INTO `review` VALUES ('9f331ab00cbd21938fcb9bb9cf00cd9c', '基于深度学习的图像识别技术研究', '1111', '111111', b'0');
INSERT INTO `review` VALUES ('edb35216b487efffc7df14205ff0a828', '农业种植', '李四', '111111', b'1');

-- ----------------------------
-- Table structure for student_thesis_selection
-- ----------------------------
DROP TABLE IF EXISTS `student_thesis_selection`;
CREATE TABLE `student_thesis_selection`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `student_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `thesis_proposal_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `student_id`(`student_id` ASC) USING BTREE,
  INDEX `thesis_proposal_id`(`thesis_proposal_id` ASC) USING BTREE,
  CONSTRAINT `student_thesis_selection_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `student_thesis_selection_ibfk_2` FOREIGN KEY (`thesis_proposal_id`) REFERENCES `thesis_proposal` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1873603926880497667 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of student_thesis_selection
-- ----------------------------
INSERT INTO `student_thesis_selection` VALUES (1873312671054614530, '4f5cb22e-1e28-4cf7-8f5b-3b9dca03f11c', '2c88d3d367533dfbd256402802368e6f');
INSERT INTO `student_thesis_selection` VALUES (1873314413880229889, '4f5cb22e-1e28-4cf7-8f5b-3b9dca03f11c', '2c88d3d367533dfbd256402802368e6f');
INSERT INTO `student_thesis_selection` VALUES (1873603127794282498, 'e9273a6f-095e-4caa-aaf6-5bf30e743fd5', '2c88d3d367533dfbd256402802368e6f');
INSERT INTO `student_thesis_selection` VALUES (1873603926880497666, 'e9273a6f-095e-4caa-aaf6-5bf30e743fd5', 'edb35216b487efffc7df14205ff0a828');

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of thesis_proposal
-- ----------------------------
INSERT INTO `thesis_proposal` VALUES ('2c88d3d367533dfbd256402802368e6f', '张三', '计算机科学研究所', '博士', 35, '人工智能', 'zhangsan@example.com', '基于深度学习的图像识别技术研究');
INSERT INTO `thesis_proposal` VALUES ('55b7dc74f789313756d1341e57a1e315', '张三', '计算机科学研究所', '博士', 35, '人工智能', 'zhangsan@example.com', '基于深度学习的图像识别技术研究');
INSERT INTO `thesis_proposal` VALUES ('735df26eb42117f4759ec7310ac911a7', '张三', '计算机科学研究所', '博士', 35, '人工智能', 'zhangsan@example.com', '基于深度学习的图像识别技术研究');
INSERT INTO `thesis_proposal` VALUES ('9f331ab00cbd21938fcb9bb9cf00cd9c', '1111', '计算机科学研究所', '博士', 35, '人工智能', 'zhangsan@example.com', '基于深度学习的图像识别技术研究');
INSERT INTO `thesis_proposal` VALUES ('edb35216b487efffc7df14205ff0a828', '李四', '计算机', '硕士', 12, '计算机科学与技术', '123123123123', '农业种植');

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('022b37fe-afcb-4b5b-80ae-a49fa33b33fd', 'managerUser1', '$2a$10$gB8YAcJWpZhyZitsT.RfieBKXRePHP3rsnPzkVWrsAVb7AJmX6aFm', 'MANAGER');
INSERT INTO `user` VALUES ('1', '1', '1', 'student');
INSERT INTO `user` VALUES ('18efdb67-1837-4292-b080-e1e4be444174', '123123', '$2a$10$ttbi8D6R2VnWboEbmhzwL.XXNYSfc/XQCc3OETx55ETibgnzOfHUm', 'teacher');
INSERT INTO `user` VALUES ('4f5cb22e-1e28-4cf7-8f5b-3b9dca03f11c', 'newUser', '$2a$10$hQqXw8IXPhreRf/FmPSHCuQTJ4TU58qEL3rK.WHZC3Zyqe2lw9JqG', 'student');
INSERT INTO `user` VALUES ('70321837-0dd2-4fb9-948e-9c582df6ddc6', 'managerUser', '$2a$10$/M6ME0GYXLVnUB4XvtorjOF7ew4fQw3tiMf7fe/xKB0rYSwa9EILy', 'MANAGER');
INSERT INTO `user` VALUES ('8bb9e611-f49c-4830-b5f9-74b57497b5bf', '111111', '$2a$10$WQEsZADrh0Qx6aaWao.W4eSNgG56VNkjqLQZp9uuoGjCyelz5TIIO', 'manager');
INSERT INTO `user` VALUES ('e9273a6f-095e-4caa-aaf6-5bf30e743fd5', '123456', '$2a$10$D8if1.TDD52WTPsredp3AeP04uVTy3aNbc.WMWPSOLQa.00hFA17S', 'student');

SET FOREIGN_KEY_CHECKS = 1;
