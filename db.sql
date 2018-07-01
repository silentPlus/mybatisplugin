-- ----------------------------
-- Table structure for demo
-- ----------------------------
DROP TABLE IF EXISTS `demo`;
CREATE TABLE `demo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
   updatetime timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of demo
-- ----------------------------
INSERT INTO `demo`(name, updatetime) VALUES ('张三', NOW());
INSERT INTO `demo`(name, updatetime) VALUES ('李四', NOW());
INSERT INTO `demo`(name, updatetime) VALUES ('小米', NOW());
INSERT INTO `demo`(name, updatetime) VALUES ('大米', NOW());
