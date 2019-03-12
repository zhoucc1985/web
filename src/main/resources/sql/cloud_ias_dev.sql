/*
 包含初始化数据，省份、城市、学校、默认模板、默认系统管理员、菜单权限
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : cloud_ias_dev

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 19/12/2018 10:58:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for city_data
-- ----------------------------
DROP TABLE IF EXISTS `city_data`;
CREATE TABLE `city_data` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `p_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=555 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of city_data
-- ----------------------------
BEGIN;
INSERT INTO `city_data` VALUES (1, '北京市', 1);
INSERT INTO `city_data` VALUES (2, '上海市', 2);
INSERT INTO `city_data` VALUES (3, '天津市', 3);
INSERT INTO `city_data` VALUES (4, '重庆市', 4);
INSERT INTO `city_data` VALUES (5, '亳州市', 5);
INSERT INTO `city_data` VALUES (6, '六安市', 5);
INSERT INTO `city_data` VALUES (7, '合肥市', 5);
INSERT INTO `city_data` VALUES (8, '安庆市', 5);
INSERT INTO `city_data` VALUES (9, '宣城市', 5);
INSERT INTO `city_data` VALUES (10, '宿州市', 5);
INSERT INTO `city_data` VALUES (11, '巢湖市', 5);
INSERT INTO `city_data` VALUES (12, '池州市', 5);
INSERT INTO `city_data` VALUES (13, '淮北市', 5);
INSERT INTO `city_data` VALUES (14, '淮南市', 5);
INSERT INTO `city_data` VALUES (15, '滁州市', 5);
INSERT INTO `city_data` VALUES (16, '芜湖市', 5);
INSERT INTO `city_data` VALUES (17, '蚌埠市', 5);
INSERT INTO `city_data` VALUES (18, '铜陵市', 5);
INSERT INTO `city_data` VALUES (19, '阜阳市', 5);
INSERT INTO `city_data` VALUES (20, '马鞍山市', 5);
INSERT INTO `city_data` VALUES (21, '黄山市', 5);
INSERT INTO `city_data` VALUES (36, '三明市', 6);
INSERT INTO `city_data` VALUES (37, '南平市', 6);
INSERT INTO `city_data` VALUES (38, '厦门市', 6);
INSERT INTO `city_data` VALUES (39, '宁德市', 6);
INSERT INTO `city_data` VALUES (40, '泉州市', 6);
INSERT INTO `city_data` VALUES (41, '漳州市', 6);
INSERT INTO `city_data` VALUES (42, '福州市', 6);
INSERT INTO `city_data` VALUES (43, '莆田市', 6);
INSERT INTO `city_data` VALUES (44, '龙岩市', 6);
INSERT INTO `city_data` VALUES (51, '临夏州', 7);
INSERT INTO `city_data` VALUES (52, '兰州市', 7);
INSERT INTO `city_data` VALUES (53, '嘉峪关市', 7);
INSERT INTO `city_data` VALUES (54, '天水市', 7);
INSERT INTO `city_data` VALUES (55, '定西市', 7);
INSERT INTO `city_data` VALUES (56, '平凉市', 7);
INSERT INTO `city_data` VALUES (57, '庆阳市', 7);
INSERT INTO `city_data` VALUES (58, '张掖市', 7);
INSERT INTO `city_data` VALUES (59, '武威市', 7);
INSERT INTO `city_data` VALUES (60, '甘南州', 7);
INSERT INTO `city_data` VALUES (61, '白银市', 7);
INSERT INTO `city_data` VALUES (62, '酒泉市', 7);
INSERT INTO `city_data` VALUES (63, '金昌市', 7);
INSERT INTO `city_data` VALUES (64, '陇南市', 7);
INSERT INTO `city_data` VALUES (66, '东莞市', 8);
INSERT INTO `city_data` VALUES (67, '中山市', 8);
INSERT INTO `city_data` VALUES (68, '云浮市', 8);
INSERT INTO `city_data` VALUES (69, '佛山市', 8);
INSERT INTO `city_data` VALUES (70, '广州市', 8);
INSERT INTO `city_data` VALUES (71, '惠州市', 8);
INSERT INTO `city_data` VALUES (72, '揭阳市', 8);
INSERT INTO `city_data` VALUES (73, '梅州市', 8);
INSERT INTO `city_data` VALUES (74, '汕头市', 8);
INSERT INTO `city_data` VALUES (75, '汕尾市', 8);
INSERT INTO `city_data` VALUES (76, '江门市', 8);
INSERT INTO `city_data` VALUES (77, '河源市', 8);
INSERT INTO `city_data` VALUES (78, '深圳市', 8);
INSERT INTO `city_data` VALUES (79, '清远市', 8);
INSERT INTO `city_data` VALUES (80, '湛江市', 8);
INSERT INTO `city_data` VALUES (81, '潮州市', 8);
INSERT INTO `city_data` VALUES (82, '珠海市', 8);
INSERT INTO `city_data` VALUES (83, '肇庆市', 8);
INSERT INTO `city_data` VALUES (84, '茂名市', 8);
INSERT INTO `city_data` VALUES (85, '阳江市', 8);
INSERT INTO `city_data` VALUES (86, '韶关市', 8);
INSERT INTO `city_data` VALUES (97, '北海市', 9);
INSERT INTO `city_data` VALUES (98, '南宁市', 9);
INSERT INTO `city_data` VALUES (99, '崇左市', 9);
INSERT INTO `city_data` VALUES (100, '来宾市', 9);
INSERT INTO `city_data` VALUES (101, '柳州市', 9);
INSERT INTO `city_data` VALUES (102, '桂林市', 9);
INSERT INTO `city_data` VALUES (103, '梧州市', 9);
INSERT INTO `city_data` VALUES (104, '河池市', 9);
INSERT INTO `city_data` VALUES (105, '玉林市', 9);
INSERT INTO `city_data` VALUES (106, '百色市', 9);
INSERT INTO `city_data` VALUES (107, '贵港市', 9);
INSERT INTO `city_data` VALUES (108, '贺州市', 9);
INSERT INTO `city_data` VALUES (109, '钦州市', 9);
INSERT INTO `city_data` VALUES (110, '防城港市', 9);
INSERT INTO `city_data` VALUES (112, '六盘水市', 10);
INSERT INTO `city_data` VALUES (113, '安顺市', 10);
INSERT INTO `city_data` VALUES (114, '毕节市', 10);
INSERT INTO `city_data` VALUES (115, '贵阳市', 10);
INSERT INTO `city_data` VALUES (116, '遵义市', 10);
INSERT INTO `city_data` VALUES (117, '铜仁市', 10);
INSERT INTO `city_data` VALUES (118, '黔东南州', 10);
INSERT INTO `city_data` VALUES (119, '黔南州', 10);
INSERT INTO `city_data` VALUES (120, '黔西南州', 10);
INSERT INTO `city_data` VALUES (127, '保定市', 11);
INSERT INTO `city_data` VALUES (128, '唐山市', 11);
INSERT INTO `city_data` VALUES (129, '廊坊市', 11);
INSERT INTO `city_data` VALUES (130, '张家口市', 11);
INSERT INTO `city_data` VALUES (131, '承德市', 11);
INSERT INTO `city_data` VALUES (132, '沧州市', 11);
INSERT INTO `city_data` VALUES (133, '石家庄市', 11);
INSERT INTO `city_data` VALUES (134, '秦皇岛市', 11);
INSERT INTO `city_data` VALUES (135, '衡水市', 11);
INSERT INTO `city_data` VALUES (136, '邢台市', 11);
INSERT INTO `city_data` VALUES (137, '邯郸市', 11);
INSERT INTO `city_data` VALUES (142, '三门峡市', 12);
INSERT INTO `city_data` VALUES (143, '信阳市', 12);
INSERT INTO `city_data` VALUES (144, '南阳市', 12);
INSERT INTO `city_data` VALUES (145, '周口市', 12);
INSERT INTO `city_data` VALUES (146, '商丘市', 12);
INSERT INTO `city_data` VALUES (147, '安阳市', 12);
INSERT INTO `city_data` VALUES (148, '平顶山市', 12);
INSERT INTO `city_data` VALUES (149, '开封市', 12);
INSERT INTO `city_data` VALUES (150, '新乡市', 12);
INSERT INTO `city_data` VALUES (151, '洛阳市', 12);
INSERT INTO `city_data` VALUES (152, '济源市', 12);
INSERT INTO `city_data` VALUES (153, '漯河市', 12);
INSERT INTO `city_data` VALUES (154, '濮阳市', 12);
INSERT INTO `city_data` VALUES (155, '焦作市', 12);
INSERT INTO `city_data` VALUES (156, '许昌市', 12);
INSERT INTO `city_data` VALUES (157, '郑州市', 12);
INSERT INTO `city_data` VALUES (158, '驻马店市', 12);
INSERT INTO `city_data` VALUES (159, '鹤壁市', 12);
INSERT INTO `city_data` VALUES (173, '七台河市', 13);
INSERT INTO `city_data` VALUES (174, '伊春市', 13);
INSERT INTO `city_data` VALUES (175, '佳木斯市', 13);
INSERT INTO `city_data` VALUES (176, '双鸭山市', 13);
INSERT INTO `city_data` VALUES (177, '哈尔滨市', 13);
INSERT INTO `city_data` VALUES (178, '大兴安岭地区', 13);
INSERT INTO `city_data` VALUES (179, '大庆市', 13);
INSERT INTO `city_data` VALUES (180, '牡丹江市', 13);
INSERT INTO `city_data` VALUES (181, '绥化市', 13);
INSERT INTO `city_data` VALUES (182, '鸡西市', 13);
INSERT INTO `city_data` VALUES (183, '鹤岗市', 13);
INSERT INTO `city_data` VALUES (184, '黑河市', 13);
INSERT INTO `city_data` VALUES (185, '齐齐哈尔市', 13);
INSERT INTO `city_data` VALUES (188, '仙桃市', 14);
INSERT INTO `city_data` VALUES (189, '十堰市', 14);
INSERT INTO `city_data` VALUES (190, '咸宁市', 14);
INSERT INTO `city_data` VALUES (191, '天门市', 14);
INSERT INTO `city_data` VALUES (192, '孝感市', 14);
INSERT INTO `city_data` VALUES (193, '宜昌市', 14);
INSERT INTO `city_data` VALUES (194, '恩施州', 14);
INSERT INTO `city_data` VALUES (195, '武汉市', 14);
INSERT INTO `city_data` VALUES (196, '江汉区', 14);
INSERT INTO `city_data` VALUES (197, '潜江市', 14);
INSERT INTO `city_data` VALUES (198, '神农架林区', 14);
INSERT INTO `city_data` VALUES (199, '荆州市', 14);
INSERT INTO `city_data` VALUES (200, '荆门市', 14);
INSERT INTO `city_data` VALUES (201, '襄阳市', 14);
INSERT INTO `city_data` VALUES (202, '鄂州市', 14);
INSERT INTO `city_data` VALUES (203, '随州市', 14);
INSERT INTO `city_data` VALUES (204, '黄冈市', 14);
INSERT INTO `city_data` VALUES (205, '黄石市', 14);
INSERT INTO `city_data` VALUES (219, '娄底市', 15);
INSERT INTO `city_data` VALUES (220, '岳阳市', 15);
INSERT INTO `city_data` VALUES (221, '常德市', 15);
INSERT INTO `city_data` VALUES (222, '张家界市', 15);
INSERT INTO `city_data` VALUES (223, '怀化市', 15);
INSERT INTO `city_data` VALUES (224, '株洲市', 15);
INSERT INTO `city_data` VALUES (225, '永州市', 15);
INSERT INTO `city_data` VALUES (226, '湘潭市', 15);
INSERT INTO `city_data` VALUES (227, '湘西州', 15);
INSERT INTO `city_data` VALUES (228, '益阳市', 15);
INSERT INTO `city_data` VALUES (229, '衡阳市', 15);
INSERT INTO `city_data` VALUES (230, '邵阳市', 15);
INSERT INTO `city_data` VALUES (231, '郴州市', 15);
INSERT INTO `city_data` VALUES (232, '长沙市', 15);
INSERT INTO `city_data` VALUES (234, '吉林市', 16);
INSERT INTO `city_data` VALUES (235, '四平市', 16);
INSERT INTO `city_data` VALUES (236, '延边州', 16);
INSERT INTO `city_data` VALUES (237, '松原市', 16);
INSERT INTO `city_data` VALUES (238, '白城市', 16);
INSERT INTO `city_data` VALUES (239, '白山市', 16);
INSERT INTO `city_data` VALUES (240, '辽源市', 16);
INSERT INTO `city_data` VALUES (241, '通化市', 16);
INSERT INTO `city_data` VALUES (242, '长春市', 16);
INSERT INTO `city_data` VALUES (249, '南京市', 17);
INSERT INTO `city_data` VALUES (250, '南通市', 17);
INSERT INTO `city_data` VALUES (251, '宿迁市', 17);
INSERT INTO `city_data` VALUES (252, '常州市', 17);
INSERT INTO `city_data` VALUES (253, '徐州市', 17);
INSERT INTO `city_data` VALUES (254, '扬州市', 17);
INSERT INTO `city_data` VALUES (255, '无锡市', 17);
INSERT INTO `city_data` VALUES (256, '泰州市', 17);
INSERT INTO `city_data` VALUES (257, '淮安市', 17);
INSERT INTO `city_data` VALUES (258, '盐城市', 17);
INSERT INTO `city_data` VALUES (259, '苏州市', 17);
INSERT INTO `city_data` VALUES (260, '连云港市', 17);
INSERT INTO `city_data` VALUES (261, '镇江市', 17);
INSERT INTO `city_data` VALUES (264, '上饶市', 18);
INSERT INTO `city_data` VALUES (265, '九江市', 18);
INSERT INTO `city_data` VALUES (266, '南昌市', 18);
INSERT INTO `city_data` VALUES (267, '吉安市', 18);
INSERT INTO `city_data` VALUES (268, '宜春市', 18);
INSERT INTO `city_data` VALUES (269, '抚州市', 18);
INSERT INTO `city_data` VALUES (270, '新余市', 18);
INSERT INTO `city_data` VALUES (271, '景德镇市', 18);
INSERT INTO `city_data` VALUES (272, '萍乡市', 18);
INSERT INTO `city_data` VALUES (273, '赣州市', 18);
INSERT INTO `city_data` VALUES (274, '鹰潭市', 18);
INSERT INTO `city_data` VALUES (279, '丹东市', 19);
INSERT INTO `city_data` VALUES (280, '大连市', 19);
INSERT INTO `city_data` VALUES (281, '抚顺市', 19);
INSERT INTO `city_data` VALUES (282, '朝阳市', 19);
INSERT INTO `city_data` VALUES (283, '本溪市', 19);
INSERT INTO `city_data` VALUES (284, '沈阳市', 19);
INSERT INTO `city_data` VALUES (285, '盘锦市', 19);
INSERT INTO `city_data` VALUES (286, '营口市', 19);
INSERT INTO `city_data` VALUES (287, '葫芦岛市', 19);
INSERT INTO `city_data` VALUES (288, '辽阳市', 19);
INSERT INTO `city_data` VALUES (289, '铁岭市', 19);
INSERT INTO `city_data` VALUES (290, '锦州市', 19);
INSERT INTO `city_data` VALUES (291, '阜新市', 19);
INSERT INTO `city_data` VALUES (292, '鞍山市', 19);
INSERT INTO `city_data` VALUES (294, '乌兰察布市', 20);
INSERT INTO `city_data` VALUES (295, '乌海市', 20);
INSERT INTO `city_data` VALUES (296, '兴安盟', 20);
INSERT INTO `city_data` VALUES (297, '包头市', 20);
INSERT INTO `city_data` VALUES (298, '呼伦贝尔市', 20);
INSERT INTO `city_data` VALUES (299, '呼和浩特市', 20);
INSERT INTO `city_data` VALUES (300, '巴彦淖尔市', 20);
INSERT INTO `city_data` VALUES (301, '赤峰市', 20);
INSERT INTO `city_data` VALUES (302, '通辽市', 20);
INSERT INTO `city_data` VALUES (303, '鄂尔多斯市', 20);
INSERT INTO `city_data` VALUES (304, '锡林郭勒盟', 20);
INSERT INTO `city_data` VALUES (305, '阿拉善盟', 20);
INSERT INTO `city_data` VALUES (309, '中卫市', 21);
INSERT INTO `city_data` VALUES (310, '吴忠市', 21);
INSERT INTO `city_data` VALUES (311, '固原市', 21);
INSERT INTO `city_data` VALUES (312, '石嘴山市', 21);
INSERT INTO `city_data` VALUES (313, '银川市', 21);
INSERT INTO `city_data` VALUES (316, '果洛州', 22);
INSERT INTO `city_data` VALUES (317, '海东市', 22);
INSERT INTO `city_data` VALUES (318, '海北州', 22);
INSERT INTO `city_data` VALUES (319, '海南州', 22);
INSERT INTO `city_data` VALUES (320, '海西州', 22);
INSERT INTO `city_data` VALUES (321, '玉树州', 22);
INSERT INTO `city_data` VALUES (322, '西宁市', 22);
INSERT INTO `city_data` VALUES (323, '黄南州', 22);
INSERT INTO `city_data` VALUES (331, '东营市', 23);
INSERT INTO `city_data` VALUES (332, '临沂市', 23);
INSERT INTO `city_data` VALUES (333, '威海市', 23);
INSERT INTO `city_data` VALUES (334, '德州市', 23);
INSERT INTO `city_data` VALUES (335, '日照市', 23);
INSERT INTO `city_data` VALUES (336, '枣庄市', 23);
INSERT INTO `city_data` VALUES (337, '泰安市', 23);
INSERT INTO `city_data` VALUES (338, '济南市', 23);
INSERT INTO `city_data` VALUES (339, '济宁市', 23);
INSERT INTO `city_data` VALUES (340, '淄博市', 23);
INSERT INTO `city_data` VALUES (341, '滨州市', 23);
INSERT INTO `city_data` VALUES (342, '潍坊市', 23);
INSERT INTO `city_data` VALUES (343, '烟台市', 23);
INSERT INTO `city_data` VALUES (344, '聊城市', 23);
INSERT INTO `city_data` VALUES (345, '莱芜市', 23);
INSERT INTO `city_data` VALUES (346, '菏泽市', 23);
INSERT INTO `city_data` VALUES (347, '青岛市', 23);
INSERT INTO `city_data` VALUES (362, '临汾市', 24);
INSERT INTO `city_data` VALUES (363, '吕梁市', 24);
INSERT INTO `city_data` VALUES (364, '大同市', 24);
INSERT INTO `city_data` VALUES (365, '太原市', 24);
INSERT INTO `city_data` VALUES (366, '忻州市', 24);
INSERT INTO `city_data` VALUES (367, '晋中市', 24);
INSERT INTO `city_data` VALUES (368, '晋城市', 24);
INSERT INTO `city_data` VALUES (369, '朔州市', 24);
INSERT INTO `city_data` VALUES (370, '运城市', 24);
INSERT INTO `city_data` VALUES (371, '长治市', 24);
INSERT INTO `city_data` VALUES (372, '阳泉市', 24);
INSERT INTO `city_data` VALUES (377, '咸阳市', 25);
INSERT INTO `city_data` VALUES (378, '商洛市', 25);
INSERT INTO `city_data` VALUES (379, '安康市', 25);
INSERT INTO `city_data` VALUES (380, '宝鸡市', 25);
INSERT INTO `city_data` VALUES (381, '延安市', 25);
INSERT INTO `city_data` VALUES (382, '榆林市', 25);
INSERT INTO `city_data` VALUES (383, '汉中市', 25);
INSERT INTO `city_data` VALUES (384, '渭南市', 25);
INSERT INTO `city_data` VALUES (385, '西安市', 25);
INSERT INTO `city_data` VALUES (386, '铜川市', 25);
INSERT INTO `city_data` VALUES (392, '乐山市', 26);
INSERT INTO `city_data` VALUES (393, '内江市', 26);
INSERT INTO `city_data` VALUES (394, '凉山州', 26);
INSERT INTO `city_data` VALUES (395, '南充市', 26);
INSERT INTO `city_data` VALUES (396, '宜宾市', 26);
INSERT INTO `city_data` VALUES (397, '巴中市', 26);
INSERT INTO `city_data` VALUES (398, '广元市', 26);
INSERT INTO `city_data` VALUES (399, '广安市', 26);
INSERT INTO `city_data` VALUES (400, '德阳市', 26);
INSERT INTO `city_data` VALUES (401, '成都市', 26);
INSERT INTO `city_data` VALUES (402, '攀枝花市', 26);
INSERT INTO `city_data` VALUES (403, '泸州市', 26);
INSERT INTO `city_data` VALUES (404, '甘孜州', 26);
INSERT INTO `city_data` VALUES (405, '眉山市', 26);
INSERT INTO `city_data` VALUES (406, '绵阳市', 26);
INSERT INTO `city_data` VALUES (407, '自贡市', 26);
INSERT INTO `city_data` VALUES (408, '资阳市', 26);
INSERT INTO `city_data` VALUES (409, '达州市', 26);
INSERT INTO `city_data` VALUES (410, '遂宁市', 26);
INSERT INTO `city_data` VALUES (411, '阿坝州', 26);
INSERT INTO `city_data` VALUES (412, '雅安市', 26);
INSERT INTO `city_data` VALUES (423, '山南市', 27);
INSERT INTO `city_data` VALUES (424, '拉萨市', 27);
INSERT INTO `city_data` VALUES (425, '日喀则市', 27);
INSERT INTO `city_data` VALUES (426, '昌都市', 27);
INSERT INTO `city_data` VALUES (427, '林芝市', 27);
INSERT INTO `city_data` VALUES (428, '那曲地区', 27);
INSERT INTO `city_data` VALUES (429, '阿里地区', 27);
INSERT INTO `city_data` VALUES (430, '乌鲁木齐市', 28);
INSERT INTO `city_data` VALUES (431, '五家渠市', 28);
INSERT INTO `city_data` VALUES (432, '伊犁哈萨克州', 28);
INSERT INTO `city_data` VALUES (433, '克孜勒苏柯尔克孜州', 28);
INSERT INTO `city_data` VALUES (434, '克拉玛依市', 28);
INSERT INTO `city_data` VALUES (435, '北屯市', 28);
INSERT INTO `city_data` VALUES (436, '博尔塔拉蒙古州', 28);
INSERT INTO `city_data` VALUES (437, '双河市', 28);
INSERT INTO `city_data` VALUES (438, '可克达拉市', 28);
INSERT INTO `city_data` VALUES (439, '吐鲁番市', 28);
INSERT INTO `city_data` VALUES (440, '和田地区', 28);
INSERT INTO `city_data` VALUES (441, '哈密市', 28);
INSERT INTO `city_data` VALUES (442, '喀什地区', 28);
INSERT INTO `city_data` VALUES (443, '图木舒克市', 28);
INSERT INTO `city_data` VALUES (444, '塔城地区', 28);
INSERT INTO `city_data` VALUES (445, '巴音郭楞蒙古州', 28);
INSERT INTO `city_data` VALUES (446, '昌吉回族州', 28);
INSERT INTO `city_data` VALUES (447, '石河子市', 28);
INSERT INTO `city_data` VALUES (448, '铁门关市', 28);
INSERT INTO `city_data` VALUES (449, '阿克苏地区', 28);
INSERT INTO `city_data` VALUES (450, '阿勒泰地区', 28);
INSERT INTO `city_data` VALUES (451, '阿拉尔市', 28);
INSERT INTO `city_data` VALUES (461, '临沧市', 29);
INSERT INTO `city_data` VALUES (462, '丽江市', 29);
INSERT INTO `city_data` VALUES (463, '保山市', 29);
INSERT INTO `city_data` VALUES (464, '大理州', 29);
INSERT INTO `city_data` VALUES (465, '德宏州', 29);
INSERT INTO `city_data` VALUES (466, '怒江州', 29);
INSERT INTO `city_data` VALUES (467, '文山州', 29);
INSERT INTO `city_data` VALUES (468, '昆明市', 29);
INSERT INTO `city_data` VALUES (469, '昭通市', 29);
INSERT INTO `city_data` VALUES (470, '普洱市', 29);
INSERT INTO `city_data` VALUES (471, '曲靖市', 29);
INSERT INTO `city_data` VALUES (472, '楚雄州', 29);
INSERT INTO `city_data` VALUES (473, '玉溪市', 29);
INSERT INTO `city_data` VALUES (474, '红河州', 29);
INSERT INTO `city_data` VALUES (475, '西双版纳州', 29);
INSERT INTO `city_data` VALUES (476, '迪庆州', 29);
INSERT INTO `city_data` VALUES (492, '丽水市', 30);
INSERT INTO `city_data` VALUES (493, '台州市', 30);
INSERT INTO `city_data` VALUES (494, '嘉兴市', 30);
INSERT INTO `city_data` VALUES (495, '宁波市', 30);
INSERT INTO `city_data` VALUES (496, '杭州市', 30);
INSERT INTO `city_data` VALUES (497, '温州市', 30);
INSERT INTO `city_data` VALUES (498, '湖州市', 30);
INSERT INTO `city_data` VALUES (499, '绍兴市', 30);
INSERT INTO `city_data` VALUES (500, '舟山市', 30);
INSERT INTO `city_data` VALUES (501, '衢州市', 30);
INSERT INTO `city_data` VALUES (502, '金华市', 30);
INSERT INTO `city_data` VALUES (507, '万宁市', 31);
INSERT INTO `city_data` VALUES (508, '三亚市', 31);
INSERT INTO `city_data` VALUES (509, '三沙市', 31);
INSERT INTO `city_data` VALUES (510, '东方市', 31);
INSERT INTO `city_data` VALUES (511, '五指山市', 31);
INSERT INTO `city_data` VALUES (512, '儋州市', 31);
INSERT INTO `city_data` VALUES (513, '文昌市', 31);
INSERT INTO `city_data` VALUES (514, '海口市', 31);
INSERT INTO `city_data` VALUES (515, '琼海市', 31);
INSERT INTO `city_data` VALUES (522, '香港', 32);
INSERT INTO `city_data` VALUES (523, '云林县', 33);
INSERT INTO `city_data` VALUES (524, '南投县', 33);
INSERT INTO `city_data` VALUES (525, '台东县', 33);
INSERT INTO `city_data` VALUES (526, '台中县', 33);
INSERT INTO `city_data` VALUES (527, '台中市', 33);
INSERT INTO `city_data` VALUES (528, '台北市', 33);
INSERT INTO `city_data` VALUES (529, '台南县', 33);
INSERT INTO `city_data` VALUES (530, '台南市', 33);
INSERT INTO `city_data` VALUES (531, '嘉义县', 33);
INSERT INTO `city_data` VALUES (532, '基隆市', 33);
INSERT INTO `city_data` VALUES (533, '宜兰县', 33);
INSERT INTO `city_data` VALUES (534, '屏东县', 33);
INSERT INTO `city_data` VALUES (535, '彰化县', 33);
INSERT INTO `city_data` VALUES (536, '新北市', 33);
INSERT INTO `city_data` VALUES (537, '新竹县', 33);
INSERT INTO `city_data` VALUES (538, '桃园县', 33);
INSERT INTO `city_data` VALUES (539, '澎湖县', 33);
INSERT INTO `city_data` VALUES (540, '花莲县', 33);
INSERT INTO `city_data` VALUES (541, '苗栗县', 33);
INSERT INTO `city_data` VALUES (542, '高雄县', 33);
INSERT INTO `city_data` VALUES (554, '澳门', 34);
COMMIT;

-- ----------------------------
-- Table structure for cloud_login_log
-- ----------------------------
DROP TABLE IF EXISTS `cloud_login_log`;
CREATE TABLE `cloud_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `college_id` bigint(20) DEFAULT NULL,
  `ipv4` varchar(255) DEFAULT NULL,
  `login_date` datetime DEFAULT NULL,
  `login_out_date` datetime DEFAULT NULL,
  `mac` varchar(255) DEFAULT NULL,
  `session_id` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_os_and_browser` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='登录日志';

-- ----------------------------
-- Table structure for cloud_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `cloud_operate_log`;
CREATE TABLE `cloud_operate_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `college_id` bigint(20) DEFAULT NULL,
  `college_name` varchar(255) DEFAULT NULL,
  `operate_duration` bigint(20) DEFAULT NULL,
  `end_log_date` datetime DEFAULT NULL,
  `operate_ipv4` varchar(255) DEFAULT NULL,
  `operate_method_code` varchar(255) DEFAULT NULL,
  `operate_method_type` varchar(255) DEFAULT NULL,
  `operate_request_param` varchar(255) DEFAULT NULL,
  `operate_user_id` bigint(20) DEFAULT NULL,
  `operate_user_name` varchar(255) DEFAULT NULL,
  `operate_module` varchar(255) DEFAULT NULL,
  `operate_name` varchar(255) DEFAULT NULL,
  `operate_type` varchar(255) DEFAULT NULL,
  `is_sucess` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作日志';

-- ----------------------------
-- Table structure for cloud_user
-- ----------------------------
DROP TABLE IF EXISTS `cloud_user`;
CREATE TABLE `cloud_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(16) DEFAULT NULL COMMENT '账号',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `name` varchar(16) DEFAULT NULL COMMENT '名称',
  `college_id` bigint(20) DEFAULT NULL COMMENT '所属学院',
  `profession_id` bigint(20) DEFAULT NULL COMMENT '所属专业',
  `class_id` bigint(20) DEFAULT NULL COMMENT '所属班级',
  `mail` varchar(255) DEFAULT NULL COMMENT '邮箱地址',
  `mobile_phone` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `job_number` varchar(64) DEFAULT NULL COMMENT '工号',
  `job_title` varchar(32) DEFAULT NULL COMMENT '职称',
  `student_number` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for collection_batch
-- ----------------------------
DROP TABLE IF EXISTS `collection_batch`;
CREATE TABLE `collection_batch` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `report_name` varchar(255) DEFAULT NULL COMMENT '报告名称，自动生成',
  `batch_no` varchar(255) DEFAULT NULL COMMENT '批次号',
  `org_id` bigint(20) DEFAULT NULL COMMENT '组织机构id',
  `import_time` datetime DEFAULT NULL COMMENT '导入时间',
  `rt_id` bigint(20) DEFAULT NULL COMMENT '报告模板id',
  `total_number` int(11) DEFAULT NULL COMMENT '导入的文档总数据',
  `valid_number` int(11) DEFAULT NULL COMMENT '有效数据数',
  `invalid_number` int(11) DEFAULT NULL COMMENT '无效数据数',
  `status` varchar(32) DEFAULT NULL COMMENT 'CHECKED校验完成CHECKING校验中',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='报告数据批次';

-- ----------------------------
-- Table structure for collection_data_source
-- ----------------------------
DROP TABLE IF EXISTS `collection_data_source`;
CREATE TABLE `collection_data_source` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `student_no` varchar(64) DEFAULT NULL COMMENT '学生学号',
  `name` varchar(64) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `age` varchar(3) DEFAULT NULL COMMENT '年龄',
  `idcard` varchar(32) DEFAULT NULL COMMENT '身份证',
  `birth_date` varchar(32) DEFAULT NULL COMMENT '出生日期',
  `birth_province` varchar(32) DEFAULT NULL COMMENT '出生身份',
  `birth_city` varchar(32) DEFAULT NULL COMMENT '出生城市',
  `college_name` varchar(255) DEFAULT NULL COMMENT '学院名称',
  `profession_name` varchar(255) DEFAULT NULL COMMENT '专业名称',
  `org_id` bigint(20) DEFAULT NULL COMMENT '学校组织机构id',
  `constellation` varchar(16) DEFAULT NULL COMMENT '星座',
  `score` varchar(32) DEFAULT NULL COMMENT '高考成绩',
  `batch_id` bigint(20) DEFAULT NULL COMMENT '报告批次id',
  `error_msg` longtext COMMENT '当前列错误字段信息对象',
  `is_error` tinyint(4) DEFAULT NULL COMMENT '是否存在错误数据',
  `org_code` varchar(255) DEFAULT NULL COMMENT '学校编码Code',
  PRIMARY KEY (`id`),
  UNIQUE KEY `student_no` (`student_no`,`org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='采集数据原数据';

-- ----------------------------
-- Table structure for collection_data_standard
-- ----------------------------
DROP TABLE IF EXISTS `collection_data_standard`;
CREATE TABLE `collection_data_standard` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `source_id` bigint(20) DEFAULT NULL COMMENT '原数据id',
  `student_no` varchar(64) DEFAULT NULL COMMENT '学生学号',
  `name` varchar(64) DEFAULT NULL COMMENT '学生姓名',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `idcard` varchar(32) DEFAULT NULL COMMENT '身份证',
  `birth_date` date DEFAULT NULL COMMENT '生日',
  `birth_province` varchar(32) DEFAULT NULL COMMENT '出生省份',
  `birth_city` varchar(32) DEFAULT NULL COMMENT '出生城市',
  `college_name` varchar(255) DEFAULT NULL COMMENT '学院名称',
  `profession_name` varchar(255) DEFAULT NULL COMMENT '专业名称',
  `org_id` bigint(20) DEFAULT NULL COMMENT '学校机构id',
  `constellation` varchar(16) DEFAULT NULL COMMENT '星座',
  `score` double DEFAULT NULL COMMENT '高考成绩',
  `batch_id` bigint(20) DEFAULT NULL COMMENT '批次id',
  `generate` tinyint(4) DEFAULT NULL COMMENT '是否已经被生成了报告（1:true）',
  `org_code` varchar(255) DEFAULT NULL COMMENT '学校编码code',
  PRIMARY KEY (`id`),
  UNIQUE KEY `student_no` (`student_no`,`org_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='采集数据标准数据';

-- ----------------------------
-- Table structure for org_info
-- ----------------------------
DROP TABLE IF EXISTS `org_info`;
CREATE TABLE `org_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(100) DEFAULT NULL COMMENT '机构编码 (默认教育局为D000，每加一级后面拼接 D +数字(从1开始)，以此类推)',
  `parent_code` varchar(100) DEFAULT NULL COMMENT '父级组织机构编码',
  `number` varchar(32) NOT NULL COMMENT '组织机构号',
  `name` varchar(100) NOT NULL COMMENT '机构名称',
  `school_nike_name` varchar(100) DEFAULT NULL COMMENT '学校简称',
  `school_founding_time` date DEFAULT NULL COMMENT '学校成立时间',
  `school_address` varchar(255) DEFAULT NULL COMMENT '学校地址',
  `school_motto` varchar(255) DEFAULT NULL COMMENT '学校校训',
  `school_logo_path` varchar(255) DEFAULT NULL COMMENT '学校logo地址',
  `school_id` varchar(32) DEFAULT NULL COMMENT '学校唯一id,目前只有学校才有',
  `parent_number` varchar(32) DEFAULT NULL COMMENT '父组织机构号',
  `type` varchar(32) DEFAULT NULL COMMENT '教育部：education，省厅：province，学校：school',
  `province_id` bigint(20) DEFAULT NULL COMMENT '省份id',
  `city_id` bigint(20) DEFAULT NULL COMMENT '城市id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `number` (`number`) USING BTREE,
  KEY `code` (`code`),
  KEY `parent_code` (`parent_code`)
) ENGINE=InnoDB AUTO_INCREMENT=890 DEFAULT CHARSET=utf8 COMMENT='组织机构';

-- ----------------------------
-- Records of org_info
-- ----------------------------
BEGIN;
INSERT INTO `org_info` VALUES (1, 'D000', '0', '0000000', '教育部', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'EDUCATION', NULL, NULL);
INSERT INTO `org_info` VALUES (2, 'D000D1', 'D000', '6016631', '品质体验厅2', NULL, NULL, NULL, NULL, NULL, NULL, '0000000', 'PROVINCE', NULL, NULL);
INSERT INTO `org_info` VALUES (3, 'D000D2', 'D000', '6125719', '广西高校易班发展研究中心', NULL, NULL, NULL, NULL, NULL, NULL, '0000000', 'PROVINCE', NULL, NULL);
INSERT INTO `org_info` VALUES (4, 'D000D3', 'D000', '6791279', '四川教育', NULL, NULL, NULL, NULL, NULL, NULL, '0000000', 'PROVINCE', NULL, NULL);
INSERT INTO `org_info` VALUES (5, 'D000D4', 'D000', '7933855', '广东高校易班发展中心', NULL, NULL, NULL, NULL, NULL, NULL, '0000000', 'PROVINCE', NULL, NULL);
INSERT INTO `org_info` VALUES (6, 'D000D5', 'D000', '8164073', '福建易班', NULL, NULL, NULL, NULL, NULL, NULL, '0000000', 'PROVINCE', NULL, NULL);
INSERT INTO `org_info` VALUES (7, 'D000D6', 'D000', '12884894', '贵州省高校易班建设与发展中心', NULL, NULL, NULL, NULL, NULL, NULL, '0000000', 'PROVINCE', NULL, NULL);
INSERT INTO `org_info` VALUES (8, 'D000D7', 'D000', '14363624', '辽宁易班', NULL, NULL, NULL, NULL, NULL, NULL, '0000000', 'PROVINCE', NULL, NULL);
INSERT INTO `org_info` VALUES (9, 'D000D8', 'D000', '14653414', '浙江省易班发展中心', NULL, NULL, NULL, NULL, NULL, NULL, '0000000', 'PROVINCE', NULL, NULL);
INSERT INTO `org_info` VALUES (10, 'D000D9', 'D000', '14653422', '云南省高校“易班”发展中心', NULL, NULL, NULL, NULL, NULL, NULL, '0000000', 'PROVINCE', NULL, NULL);
INSERT INTO `org_info` VALUES (11, 'D000D10', 'D000', '14653428', '西藏自治区易班发展中心', NULL, NULL, NULL, NULL, NULL, NULL, '0000000', 'PROVINCE', NULL, NULL);
INSERT INTO `org_info` VALUES (12, 'D000D11', 'D000', '14653438', '陕西省高校易班发展中心', NULL, NULL, NULL, NULL, NULL, NULL, '0000000', 'PROVINCE', NULL, NULL);
INSERT INTO `org_info` VALUES (13, 'D000D12', 'D000', '14653442', '山东省易班发展中心', NULL, NULL, NULL, NULL, NULL, NULL, '0000000', 'PROVINCE', NULL, NULL);
INSERT INTO `org_info` VALUES (14, 'D000D13', 'D000', '14653448', '江苏省易班发展中心', NULL, NULL, NULL, NULL, NULL, NULL, '0000000', 'PROVINCE', NULL, NULL);
INSERT INTO `org_info` VALUES (15, 'D000D14', 'D000', '14653452', '湖北易班', NULL, NULL, NULL, NULL, NULL, NULL, '0000000', 'PROVINCE', NULL, NULL);
INSERT INTO `org_info` VALUES (16, 'D000D15', 'D000', '14653458', '安徽省易班发展中心', NULL, NULL, NULL, NULL, NULL, NULL, '0000000', 'PROVINCE', NULL, NULL);
INSERT INTO `org_info` VALUES (17, 'D000D16', 'D000', '14684028', '龙江易班', NULL, NULL, NULL, NULL, NULL, NULL, '0000000', 'PROVINCE', NULL, NULL);
INSERT INTO `org_info` VALUES (18, 'D000D17', 'D000', '14684030', '吉林省易班发展中心', NULL, NULL, NULL, NULL, NULL, NULL, '0000000', 'PROVINCE', NULL, NULL);
INSERT INTO `org_info` VALUES (19, 'D000D18', 'D000', '14684040', '内蒙古自治区易班发展中心', NULL, NULL, NULL, NULL, NULL, NULL, '0000000', 'PROVINCE', NULL, NULL);
INSERT INTO `org_info` VALUES (20, 'D000D19', 'D000', '14684046', '甘肃省易班发展中心', NULL, NULL, NULL, NULL, NULL, NULL, '0000000', 'PROVINCE', NULL, NULL);
INSERT INTO `org_info` VALUES (21, 'D000D20', 'D000', '14684048', '新疆生产建设兵团易班发展中心', NULL, NULL, NULL, NULL, NULL, NULL, '0000000', 'PROVINCE', NULL, NULL);
INSERT INTO `org_info` VALUES (22, 'D000D21', 'D000', '14684052', '海南省易班发展中心', NULL, NULL, NULL, NULL, NULL, NULL, '0000000', 'PROVINCE', NULL, NULL);
INSERT INTO `org_info` VALUES (23, 'D000D22', 'D000', '15083998', '湖南省易班发展中心', NULL, NULL, NULL, NULL, NULL, NULL, '0000000', 'PROVINCE', NULL, NULL);
INSERT INTO `org_info` VALUES (24, 'D000D23', 'D000', '15966103', '河北省易班发展中心', NULL, NULL, NULL, NULL, NULL, NULL, '0000000', 'PROVINCE', NULL, NULL);
INSERT INTO `org_info` VALUES (25, 'D000D24', 'D000', '22150004', '青海省易班发展中心', NULL, NULL, NULL, NULL, NULL, NULL, '0000000', 'PROVINCE', NULL, NULL);
INSERT INTO `org_info` VALUES (26, 'D000D1D1', 'D000D1', '5000081', '易班大学', NULL, NULL, NULL, NULL, NULL, '305', '6016631', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (27, 'D000D2D1', 'D000D2', '5000106', '广西师范大学', '广西师范大学', '2018-12-10', NULL, NULL, '/school/logo/27/108.png', '418', '6125719', 'SCHOOL', 9, 98);
INSERT INTO `org_info` VALUES (28, 'D000D2D2', 'D000D2', '5000115', '广西科技大学', 'fgggffff', '2018-11-28', NULL, 'gggg', '/school/logo/28/108.png', '427', '6125719', 'SCHOOL', 8, 66);
INSERT INTO `org_info` VALUES (29, 'D000D2D3', 'D000D2', '5000114', '桂林医学院', NULL, NULL, NULL, NULL, NULL, '426', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (30, 'D000D2D4', 'D000D2', '5000109', '桂林电子科技大学', NULL, NULL, NULL, NULL, NULL, '421', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (31, 'D000D2D5', 'D000D2', '5000140', '柳州职业技术学院', NULL, NULL, NULL, NULL, NULL, '452', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (32, 'D000D2D6', 'D000D2', '5000105', '广西大学', NULL, NULL, NULL, NULL, NULL, '417', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (33, 'D000D2D7', 'D000D2', '5000112', '广西师范学院', NULL, NULL, NULL, NULL, NULL, '424', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (34, 'D000D2D8', 'D000D2', '5000122', '钦州学院', NULL, NULL, NULL, NULL, NULL, '434', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (35, 'D000D2D9', 'D000D2', '5000150', '广西电力职业技术学院', NULL, NULL, NULL, NULL, NULL, '462', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (36, 'D000D2D10', 'D000D2', '5000117', '玉林师范学院', NULL, NULL, NULL, NULL, NULL, '429', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (37, 'D000D2D11', 'D000D2', '5000149', '广西经贸职业技术学院', NULL, NULL, NULL, NULL, NULL, '461', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (38, 'D000D2D12', 'D000D2', '5000116', '右江民族医学院', NULL, NULL, NULL, NULL, NULL, '428', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (39, 'D000D2D13', 'D000D2', '5000107', '广西医科大学', NULL, NULL, NULL, NULL, NULL, '419', '6125719', 'SCHOOL', 9, 99);
INSERT INTO `org_info` VALUES (40, 'D000D2D14', 'D000D2', '5000108', '广西民族大学', NULL, NULL, NULL, NULL, NULL, '420', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (41, 'D000D2D15', 'D000D2', '5000110', '桂林理工大学', NULL, NULL, NULL, NULL, NULL, '422', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (42, 'D000D2D16', 'D000D2', '5000111', '广西中医药大学', NULL, NULL, NULL, NULL, NULL, '423', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (43, 'D000D2D17', 'D000D2', '5000113', '广西艺术学院', NULL, NULL, NULL, NULL, NULL, '425', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (44, 'D000D2D18', 'D000D2', '5000118', '河池学院', NULL, NULL, NULL, NULL, NULL, '430', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (45, 'D000D2D19', 'D000D2', '5000119', '广西财经学院', NULL, NULL, NULL, NULL, NULL, '431', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (46, 'D000D2D20', 'D000D2', '5000120', '百色学院', NULL, NULL, NULL, NULL, NULL, '432', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (47, 'D000D2D21', 'D000D2', '5000123', '梧州学院', NULL, NULL, NULL, NULL, NULL, '435', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (48, 'D000D2D22', 'D000D2', '5000121', '贺州学院', NULL, NULL, NULL, NULL, NULL, '433', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (49, 'D000D2D23', 'D000D2', '5000124', '广西民族师范学院', NULL, NULL, NULL, NULL, NULL, '436', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (50, 'D000D2D24', 'D000D2', '5000178', '桂林航天工业学院', NULL, NULL, NULL, NULL, NULL, '490', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (51, 'D000D2D25', 'D000D2', '5000164', '广西大学行健文理学院', NULL, NULL, NULL, NULL, NULL, '476', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (52, 'D000D2D26', 'D000D2', '5000165', '广西师范大学漓江学院', NULL, NULL, NULL, NULL, NULL, '477', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (53, 'D000D2D27', 'D000D2', '5000166', '广西民族大学相思湖学院', NULL, NULL, NULL, NULL, NULL, '478', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (54, 'D000D2D28', 'D000D2', '5000167', '桂林电子科技大学信息科技学院', NULL, NULL, NULL, NULL, NULL, '479', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (55, 'D000D2D29', 'D000D2', '5000168', '桂林理工大学博文管理学院', NULL, NULL, NULL, NULL, NULL, '480', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (56, 'D000D2D30', 'D000D2', '5000169', '广西中医药大学赛恩斯新医药学院', NULL, NULL, NULL, NULL, NULL, '481', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (57, 'D000D2D31', 'D000D2', '5000187', '广西科技大学鹿山学院', NULL, NULL, NULL, NULL, NULL, '545', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (58, 'D000D2D32', 'D000D2', '5000170', '广西师范学院师园学院', NULL, NULL, NULL, NULL, NULL, '482', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (59, 'D000D2D33', 'D000D2', '5000130', '桂林师范高等专科学校', NULL, NULL, NULL, NULL, NULL, '442', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (60, 'D000D2D34', 'D000D2', '5000132', '广西幼儿师范高等专科学校', NULL, NULL, NULL, NULL, NULL, '444', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (61, 'D000D2D35', 'D000D2', '5000138', '广西职业技术学院', NULL, NULL, NULL, NULL, NULL, '450', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (62, 'D000D2D36', 'D000D2', '5000141', '广西机电职业技术学院', NULL, NULL, NULL, NULL, NULL, '453', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (63, 'D000D2D37', 'D000D2', '5000151', '广西工商职业技术学院', NULL, NULL, NULL, NULL, NULL, '463', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (64, 'D000D2D38', 'D000D2', '5000125', '广西外国语学院', NULL, NULL, NULL, NULL, NULL, '437', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (65, 'D000D2D39', 'D000D2', '5000126', '南宁学院', NULL, NULL, NULL, NULL, NULL, '438', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (66, 'D000D3D1', 'D000D3', '5349545', '西南石油大学', NULL, NULL, NULL, NULL, NULL, '22017', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (67, 'D000D3D2', 'D000D3', '5349552', '成都学院', NULL, NULL, NULL, NULL, NULL, '22097', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (68, 'D000D3D3', 'D000D3', '5349589', '四川理工学院', NULL, NULL, NULL, NULL, NULL, '22019', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (69, 'D000D3D4', 'D000D3', '5349598', '中国民用航空飞行学院', NULL, NULL, NULL, NULL, NULL, '22096', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (70, 'D000D3D5', 'D000D3', '5349617', '乐山师范学院', NULL, NULL, NULL, NULL, NULL, '22025', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (71, 'D000D3D6', 'D000D3', '5349624', '四川文理学院', NULL, NULL, NULL, NULL, NULL, '22014', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (72, 'D000D3D7', 'D000D3', '5349633', '四川民族学院', NULL, NULL, NULL, NULL, NULL, '22100', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (73, 'D000D3D8', 'D000D3', '5349562', '四川交通职业技术学院', NULL, NULL, NULL, NULL, NULL, '22064', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (74, 'D000D3D9', 'D000D3', '5349606', '绵阳职业技术学院', NULL, NULL, NULL, NULL, NULL, '22048', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (75, 'D000D3D10', 'D000D3', '5349611', '四川信息职业技术学院', NULL, NULL, NULL, NULL, NULL, '22084', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (76, 'D000D3D11', 'D000D3', '5532645', '成都信息工程大学银杏酒店管理学院', NULL, NULL, NULL, NULL, NULL, '34083', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (77, 'D000D3D12', 'D000D3', '5349546', '成都理工大学', NULL, NULL, NULL, NULL, NULL, '22005', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (78, 'D000D3D13', 'D000D3', '5349554', '成都师范学院', NULL, NULL, NULL, NULL, NULL, '22105', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (79, 'D000D3D14', 'D000D3', '5349591', '攀枝花学院', NULL, NULL, NULL, NULL, NULL, '22030', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (80, 'D000D3D15', 'D000D3', '5349603', '西南科技大学', NULL, NULL, NULL, NULL, NULL, '22027', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (81, 'D000D3D16', 'D000D3', '5349619', '西华师范大学', NULL, NULL, NULL, NULL, NULL, '22023', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (82, 'D000D3D17', 'D000D3', '5349627', '四川农业大学', NULL, NULL, NULL, NULL, NULL, '22002', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (83, 'D000D3D18', 'D000D3', '5349634', '西昌学院', NULL, NULL, NULL, NULL, NULL, '22028', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (84, 'D000D3D19', 'D000D3', '5349596', '四川化工职业技术学院', NULL, NULL, NULL, NULL, NULL, '22061', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (85, 'D000D3D20', 'D000D3', '5349600', '四川建筑职业技术学院', NULL, NULL, NULL, NULL, NULL, '22063', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (86, 'D000D3D21', 'D000D3', '5349605', '四川文化艺术学院', NULL, NULL, NULL, NULL, NULL, '22104', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (87, 'D000D3D22', 'D000D3', '5349548', '四川师范大学', NULL, NULL, NULL, NULL, NULL, '22006', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (88, 'D000D3D23', 'D000D3', '5349555', '成都工业学院', NULL, NULL, NULL, NULL, NULL, '22098', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (89, 'D000D3D24', 'D000D3', '5925583', '西南医科大学', NULL, NULL, NULL, NULL, NULL, '34111', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (90, 'D000D3D25', 'D000D3', '5349614', '内江师范学院', NULL, NULL, NULL, NULL, NULL, '22024', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (91, 'D000D3D26', 'D000D3', '5349620', '川北医学院', NULL, NULL, NULL, NULL, NULL, '22022', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (92, 'D000D3D27', 'D000D3', '5532643', '阿坝师范学院', NULL, NULL, NULL, NULL, NULL, '34081', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (93, 'D000D3D28', 'D000D3', '5349561', '成都航空职业技术学院', NULL, NULL, NULL, NULL, NULL, '22036', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (94, 'D000D3D29', 'D000D3', '5349599', '四川工程职业技术学院', NULL, NULL, NULL, NULL, NULL, '22054', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (95, 'D000D3D30', 'D000D3', '5349586', '四川文轩职业学院', NULL, NULL, NULL, NULL, NULL, '22117', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (96, 'D000D3D31', 'D000D3', '5349641', '西南财经大学天府学院', NULL, NULL, NULL, NULL, NULL, '22094', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (97, 'D000D3D32', 'D000D3', '5000090', '西华大学', NULL, NULL, NULL, NULL, NULL, '310', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (98, 'D000D4D1', 'D000D4', '5388957', '佛山科学技术学院', NULL, NULL, NULL, NULL, NULL, '18036', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (99, 'D000D4D2', 'D000D4', '7444666', '华南农业大学', NULl, NULL, NULL, NULL, NULL, '18006', '7933855', 'SCHOOL', 8, 70);
INSERT INTO `org_info` VALUES (100, 'D000D4D3', 'D000D4', '5458581', '广东海洋大学', NULL, NULL, NULL, NULL, NULL, '18031', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (101, 'D000D4D4', 'D000D4', '7444670', '广东财经大学', NULL, NULL, NULL, NULL, NULL, '18145', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (102, 'D000D4D5', 'D000D4', '7444672', '仲恺农业工程学院', NULL, NULL, NULL, NULL, NULL, '18011', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (103, 'D000D4D6', 'D000D4', '7444676', '星海音乐学院', NULL, NULL, NULL, NULL, NULL, '18019', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (104, 'D000D4D7', 'D000D4', '7444680', '广州大学', NULL, NULL, NULL, NULL, NULL, '18007', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (105, 'D000D3D33', 'D000D3', '5349604', '绵阳师范学院', NULL, NULL, NULL, NULL, NULL, '22026', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (106, 'D000D5D1', 'D000D5', '5000089', '厦门大学', NULL, NULL, NULL, NULL, NULL, '314', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (107, 'D000D5D2', 'D000D5', '5000188', '福建农林大学', NULL, NULL, NULL, NULL, NULL, '546', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (108, 'D000D5D3', 'D000D5', '5000189', '福州大学', NULL, NULL, NULL, NULL, NULL, '547', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (109, 'D000D5D4', 'D000D5', '5000191', '福建工程学院', NULL, NULL, NULL, NULL, NULL, '549', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (110, 'D000D5D5', 'D000D5', '5000195', '闽南师范大学', NULL, NULL, NULL, NULL, NULL, '553', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (111, 'D000D5D6', 'D000D5', '5000197', '泉州师范学院', NULL, NULL, NULL, NULL, NULL, '555', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (112, 'D000D5D7', 'D000D5', '5000200', '福建中医药大学', NULL, NULL, NULL, NULL, NULL, '558', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (113, 'D000D5D8', 'D000D5', '5000201', '福建医科大学', NULL, NULL, NULL, NULL, NULL, '559', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (114, 'D000D5D9', 'D000D5', '5000202', '福建江夏学院', NULL, NULL, NULL, NULL, NULL, '560', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (115, 'D000D5D10', 'D000D5', '5000206', '福建生物工程职业技术学院', NULL, NULL, NULL, NULL, NULL, '564', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (116, 'D000D5D11', 'D000D5', '5000190', '福建师范大学', NULL, NULL, NULL, NULL, NULL, '548', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (117, 'D000D5D12', 'D000D5', '5000192', '闽江学院', NULL, NULL, NULL, NULL, NULL, '550', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (118, 'D000D5D13', 'D000D5', '5000193', '集美大学', NULL, NULL, NULL, NULL, NULL, '551', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (119, 'D000D5D14', 'D000D5', '5000194', '厦门理工学院', NULL, NULL, NULL, NULL, NULL, '552', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (120, 'D000D5D15', 'D000D5', '5000199', '莆田学院', NULL, NULL, NULL, NULL, NULL, '557', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (121, 'D000D5D16', 'D000D5', '5000196', '龙岩学院', NULL, NULL, NULL, NULL, NULL, '554', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (122, 'D000D5D17', 'D000D5', '5000205', '三明学院', NULL, NULL, NULL, NULL, NULL, '563', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (123, 'D000D5D18', 'D000D5', '5000204', '武夷学院', NULL, NULL, NULL, NULL, NULL, '562', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (124, 'D000D5D19', 'D000D5', '5000203', '宁德师范学院', NULL, NULL, NULL, NULL, NULL, '561', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (125, 'D000D5D20', 'D000D5', '5376721', '福建船政交通职业学院', NULL, NULL, NULL, NULL, NULL, '31081', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (126, 'D000D5D21', 'D000D5', '5376722', '福建信息职业技术学院', NULL, NULL, NULL, NULL, NULL, '31042', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (127, 'D000D5D22', 'D000D5', '5376723', '福建商学院', NULL, NULL, NULL, NULL, NULL, '31038', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (128, 'D000D5D23', 'D000D5', '5376724', '福建商学院（马尾校区）', NULL, NULL, NULL, NULL, NULL, '31028', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (129, 'D000D5D24', 'D000D5', '5376725', '福建卫生职业技术学院', NULL, NULL, NULL, NULL, NULL, '31041', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (130, 'D000D5D25', 'D000D5', '5376726', '福建农业职业技术学院', NULL, NULL, NULL, NULL, NULL, '31037', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (131, 'D000D5D26', 'D000D5', '5376727', '福建幼儿师范高等专科学校', NULL, NULL, NULL, NULL, NULL, '31084', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (132, 'D000D5D27', 'D000D5', '5376728', '闽江师范高等专科学校', NULL, NULL, NULL, NULL, NULL, '31097', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (133, 'D000D5D28', 'D000D5', '5376729', '福建艺术职业学院', NULL, NULL, NULL, NULL, NULL, '31083', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (134, 'D000D5D29', 'D000D5', '5376730', '福建体育职业技术学院', NULL, NULL, NULL, NULL, NULL, '31088', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (135, 'D000D5D30', 'D000D5', '5376731', '福州职业技术学院', NULL, NULL, NULL, NULL, NULL, '31051', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (136, 'D000D5D31', 'D000D5', '5376732', '厦门海洋职业技术学院', NULL, NULL, NULL, NULL, NULL, '31066', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (137, 'D000D5D32', 'D000D5', '5376734', '厦门城市职业学院', NULL, NULL, NULL, NULL, NULL, '31075', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (138, 'D000D5D33', 'D000D5', '5376735', '泉州医学高等专科学校', NULL, NULL, NULL, NULL, NULL, '31063', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (139, 'D000D5D34', 'D000D5', '5376736', '福建电力职业技术学院', NULL, NULL, NULL, NULL, NULL, '31027', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (140, 'D000D5D35', 'D000D5', '5376737', '黎明职业大学', NULL, NULL, NULL, NULL, NULL, '31052', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (141, 'D000D5D36', 'D000D5', '5376738', '泉州经贸职业技术学院', NULL, NULL, NULL, NULL, NULL, '31061', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (142, 'D000D5D37', 'D000D5', '5376739', '泉州幼儿师范高等专科学校', NULL, NULL, NULL, NULL, NULL, '31096', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (143, 'D000D5D38', 'D000D5', '5376740', '泉州工艺美术职业学院', NULL, NULL, NULL, NULL, NULL, '31085', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (144, 'D000D5D39', 'D000D5', '5376741', '漳州职业技术学院', NULL, NULL, NULL, NULL, NULL, '31072', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (145, 'D000D5D40', 'D000D5', '5376742', '漳州卫生职业学院', NULL, NULL, NULL, NULL, NULL, '31076', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (146, 'D000D5D41', 'D000D5', '5376743', '漳州城市职业学院', NULL, NULL, NULL, NULL, NULL, '31089', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (147, 'D000D5D42', 'D000D5', '5376744', '湄洲湾职业技术学院', NULL, NULL, NULL, NULL, NULL, '31053', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (148, 'D000D5D43', 'D000D5', '5376745', '闽西职业技术学院', NULL, NULL, NULL, NULL, NULL, '31055', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (149, 'D000D5D44', 'D000D5', '5376746', '福建水利电力职业技术学院', NULL, NULL, NULL, NULL, NULL, '31040', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (150, 'D000D5D45', 'D000D5', '5376747', '三明医学科技职业学院', NULL, NULL, NULL, NULL, NULL, '31065', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (151, 'D000D5D46', 'D000D5', '5376748', '福建林业职业技术学院', NULL, NULL, NULL, NULL, NULL, '31036', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (152, 'D000D5D47', 'D000D5', '5376749', '闽北职业技术学院', NULL, NULL, NULL, NULL, NULL, '31054', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (153, 'D000D5D48', 'D000D5', '5376750', '宁德职业技术学院', NULL, NULL, NULL, NULL, NULL, '31086', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (154, 'D000D5D49', 'D000D5', '5458575', '仰恩大学', NULL, NULL, NULL, NULL, NULL, '31012', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (155, 'D000D5D50', 'D000D5', '5421123', '闽南理工学院', NULL, NULL, NULL, NULL, NULL, '31078', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (156, 'D000D5D51', 'D000D5', '8254799', '福州外语外贸学院', NULL, NULL, NULL, NULL, NULL, '31049', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (157, 'D000D5D52', 'D000D5', '8254811', '泉州信息工程学院', NULL, NULL, NULL, NULL, NULL, '31080', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (158, 'D000D5D53', 'D000D5', '8255289', '福州理工学院', NULL, NULL, NULL, NULL, NULL, '34177', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (159, 'D000D5D54', 'D000D5', '8255291', '阳光学院', NULL, NULL, NULL, NULL, NULL, '34179', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (160, 'D000D5D55', 'D000D5', '8255293', '厦门工学院', NULL, NULL, NULL, NULL, NULL, '34181', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (161, 'D000D5D56', 'D000D5', '8255295', '厦门华厦学院', NULL, NULL, NULL, NULL, NULL, '34183', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (162, 'D000D5D57', 'D000D5', '8255297', '福建师范大学福清分校', NULL, NULL, NULL, NULL, NULL, '34185', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (163, 'D000D5D58', 'D000D5', '8254827', '厦门大学嘉庚学院', NULL, NULL, NULL, NULL, NULL, '31018', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (164, 'D000D5D59', 'D000D5', '5458571', '福州大学至诚学院', NULL, NULL, NULL, NULL, NULL, '31021', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (165, 'D000D5D60', 'D000D5', '8254839', '福建师范大学闽南科技学院', NULL, NULL, NULL, NULL, NULL, '31023', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (166, 'D000D5D61', 'D000D5', '5458569', '福建师范大学协和学院', NULL, NULL, NULL, NULL, NULL, '31022', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (167, 'D000D5D62', 'D000D5', '5458567', '福建农林大学金山学院', NULL, NULL, NULL, NULL, NULL, '31025', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (168, 'D000D5D63', 'D000D5', '8254859', '福建农林大学东方学院', NULL, NULL, NULL, NULL, NULL, '31024', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (169, 'D000D5D64', 'D000D5', '5458573', '集美大学诚毅学院', NULL, NULL, NULL, NULL, NULL, '31019', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (170, 'D000D5D65', 'D000D5', '8254867', '福建华南女子职业学院', NULL, NULL, NULL, NULL, NULL, '31031', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (171, 'D000D5D66', 'D000D5', '8254875', '福州英华职业学院', NULL, NULL, NULL, NULL, NULL, '31050', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (172, 'D000D5D67', 'D000D5', '8254883', '福州科技职业技术学院', NULL, NULL, NULL, NULL, NULL, '31046', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (173, 'D000D5D68', 'D000D5', '8254893', '厦门华天涉外职业技术学院', NULL, NULL, NULL, NULL, NULL, '31067', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (174, 'D000D5D69', 'D000D5', '8254905', '厦门演艺职业学院', NULL, NULL, NULL, NULL, NULL, '31071', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (175, 'D000D5D70', 'D000D5', '8254915', '厦门兴才职业技术学院', NULL, NULL, NULL, NULL, NULL, '31070', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (176, 'D000D5D71', 'D000D5', '8254919', '厦门软件职业技术学院', NULL, NULL, NULL, NULL, NULL, '31087', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (177, 'D000D5D72', 'D000D5', '8254923', '厦门东海职业技术学院', NULL, NULL, NULL, NULL, NULL, '31090', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (178, 'D000D5D73', 'D000D5', '8254947', '厦门南洋职业学院', NULL, NULL, NULL, NULL, NULL, '31069', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (179, 'D000D5D74', 'D000D5', '8254963', '厦门安防科技职业学院', NULL, NULL, NULL, NULL, NULL, '31095', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (180, 'D000D5D75', 'D000D5', '8254971', '泉州理工职业学院', NULL, NULL, NULL, NULL, NULL, '31064', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (181, 'D000D5D76', 'D000D5', '8254981', '泉州纺织服装职业学院', NULL, NULL, NULL, NULL, NULL, '31058', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (182, 'D000D5D77', 'D000D5', '8255047', '泉州华光职业学院', NULL, NULL, NULL, NULL, NULL, '31060', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (183, 'D000D5D78', 'D000D5', '8255061', '泉州海洋职业学院', NULL, NULL, NULL, NULL, NULL, '31093', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (184, 'D000D5D79', 'D000D5', '8255087', '泉州轻工职业学院', NULL, NULL, NULL, NULL, NULL, '31094', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (185, 'D000D5D80', 'D000D5', '8255105', '泉州工程职业技术学院', NULL, NULL, NULL, NULL, NULL, '31098', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (186, 'D000D5D81', 'D000D5', '8255117', '漳州科技职业学院', NULL, NULL, NULL, NULL, NULL, '31091', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (187, 'D000D5D82', 'D000D5', '8255141', '漳州理工职业学院', NULL, NULL, NULL, NULL, NULL, '31092', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (188, 'D000D5D83', 'D000D5', '8255149', '武夷山职业学院', NULL, NULL, NULL, NULL, NULL, '31074', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (189, 'D000D5D84', 'D000D5', '5376733', '厦门医学院', NULL, NULL, NULL, NULL, NULL, '31082', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (190, 'D000D5D85', 'D000D5', '9218468', '福建易班', NULL, NULL, NULL, NULL, NULL, '34190', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (191, 'D000D4D8', 'D000D4', '5458587', '广州中医药大学', NULL, NULL, NULL, NULL, NULL, '18009', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (192, 'D000D4D9', 'D000D4', '5370583', '广东工业大学', NULL, NULL, NULL, NULL, NULL, '18005', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (193, 'D000D4D10', 'D000D4', '10356324', '广东医科大学', NULL, NULL, NULL, NULL, NULL, '34202', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (194, 'D000D4D11', 'D000D4', '8204669', '广东药科大学', NULL, NULL, NULL, NULL, NULL, '34173', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (195, 'D000D4D12', 'D000D4', '10356172', '广东技术师范学院', NULL, NULL, NULL, NULL, NULL, '18020', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (196, 'D000D4D13', 'D000D4', '5972625', '岭南师范学院', NULL, NULL, NULL, NULL, NULL, '18144', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (197, 'D000D4D14', 'D000D4', '5370585', '广东石油化工学院', NULL, NULL, NULL, NULL, NULL, '18133', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (198, 'D000D4D15', 'D000D4', '10356188', '广州医科大学', NULL, NULL, NULL, NULL, NULL, '18143', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (199, 'D000D4D16', 'D000D4', '10356192', '嘉应学院', NULL, NULL, NULL, NULL, NULL, '18034', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (200, 'D000D4D17', 'D000D4', '5370584', '五邑大学', NULL, NULL, NULL, NULL, NULL, '18025', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (201, 'D000D4D18', 'D000D4', '5458589', '肇庆学院', NULL, NULL, NULL, NULL, NULL, '18026', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (202, 'D000D4D19', 'D000D4', '10356296', '广东轻工职业技术学院', '广轻', '2018-11-28', '', '', '/school/logo/202/学校logo.png', '18067', '7933855', 'SCHOOL', 8, 70);
INSERT INTO `org_info` VALUES (203, 'D000D4D20', 'D000D4', '10356206', '广东建设职业技术学院', NULL, NULL, NULL, NULL, NULL, '18059', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (204, 'D000D4D21', 'D000D4', '8688521', '广东科贸职业学院', NULL, NULL, NULL, NULL, NULL, '18158', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (205, 'D000D4D22', 'D000D4', '10356212', '广东食品药品职业学院', NULL, NULL, NULL, NULL, NULL, '18155', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (206, 'D000D4D23', 'D000D4', '10356216', '广东女子职业技术学院', NULL, NULL, NULL, NULL, NULL, '18066', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (207, 'D000D4D24', 'D000D4', '10356218', '广东农工商职业技术学院', NULL, NULL, NULL, NULL, NULL, '18065', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (208, 'D000D4D25', 'D000D4', '5375815', '河源职业技术学院', NULL, NULL, NULL, NULL, NULL, '18099', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (209, 'D000D4D26', 'D000D4', '10356226', '惠州城市职业学院', NULL, NULL, NULL, NULL, NULL, '18175', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (210, 'D000D4D27', 'D000D4', '10356230', '东莞职业技术学院', NULL, NULL, NULL, NULL, NULL, '18044', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (211, 'D000D4D28', 'D000D4', '10356236', '广州工商学院', NULL, NULL, NULL, NULL, NULL, '18149', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (212, 'D000D4D29', 'D000D4', '10356248', '广州华立科技职业学院', NULL, NULL, NULL, NULL, NULL, '18088', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (213, 'D000D4D30', 'D000D4', '5370588', '中山大学南方学院', NULL, NULL, NULL, NULL, NULL, '18037', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (214, 'D000D4D31', 'D000D4', '10356184', '广东第二师范学院', NULL, NULL, NULL, NULL, NULL, '18152', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (215, 'D000D2D40', 'D000D2', '5540419', '桂林旅游学院', NULL, NULL, NULL, NULL, NULL, '34087', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (216, 'D000D2D41', 'D000D2', '5000129', '广西科技师范学院', NULL, NULL, NULL, NULL, NULL, '441', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (217, 'D000D2D42', 'D000D2', '5540421', '广西警察学院', NULL, NULL, NULL, NULL, NULL, '34089', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (218, 'D000D2D43', 'D000D2', '5000127', '广西体育高等专科学校', NULL, NULL, NULL, NULL, NULL, '439', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (219, 'D000D2D44', 'D000D2', '5000139', '南宁职业技术学院', NULL, NULL, NULL, NULL, NULL, '451', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (220, 'D000D2D45', 'D000D2', '5000142', '广西水利电力职业技术学院', NULL, NULL, NULL, NULL, NULL, '454', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (221, 'D000D2D46', 'D000D2', '5000143', '广西交通职业技术学院', NULL, NULL, NULL, NULL, NULL, '455', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (222, 'D000D2D47', 'D000D2', '5000144', '广西建设职业技术学院', NULL, NULL, NULL, NULL, NULL, '456', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (223, 'D000D2D48', 'D000D2', '5000145', '广西农业职业技术学院', NULL, NULL, NULL, NULL, NULL, '457', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (224, 'D000D2D49', 'D000D2', '5000146', '广西生态工程职业技术学院', NULL, NULL, NULL, NULL, NULL, '458', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (225, 'D000D2D50', 'D000D2', '5000147', '广西国际商务职业技术学院', NULL, NULL, NULL, NULL, NULL, '459', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (226, 'D000D2D51', 'D000D2', '5000148', '广西工业职业技术学院', NULL, NULL, NULL, NULL, NULL, '460', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (227, 'D000D2D52', 'D000D2', '5000135', '广西卫生职业技术学院', NULL, NULL, NULL, NULL, NULL, '447', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (228, 'D000D2D53', 'D000D2', '5000152', '柳州铁道职业技术学院', NULL, NULL, NULL, NULL, NULL, '464', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (229, 'D000D2D54', 'D000D2', '5000154', '广西现代职业技术学院', NULL, NULL, NULL, NULL, NULL, '466', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (230, 'D000D2D55', 'D000D2', '5000155', '北海职业学院', NULL, NULL, NULL, NULL, NULL, '467', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (231, 'D000D2D56', 'D000D2', '5000156', '柳州城市职业学院', NULL, NULL, NULL, NULL, NULL, '468', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (232, 'D000D2D57', 'D000D2', '5000157', '百色职业学院', NULL, NULL, NULL, NULL, NULL, '469', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (233, 'D000D2D58', 'D000D2', '5000158', '梧州职业学院', NULL, NULL, NULL, NULL, NULL, '470', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (234, 'D000D2D59', 'D000D2', '11070003', '广西金融职业技术学院', NULL, NULL, NULL, NULL, NULL, '19105', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (235, 'D000D2D60', 'D000D2', '5000159', '广西演艺职业学院', NULL, NULL, NULL, NULL, NULL, '471', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (236, 'D000D2D61', 'D000D2', '5000161', '北海艺术设计学院', NULL, NULL, NULL, NULL, NULL, '473', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (237, 'D000D2D62', 'D000D2', '5000160', '桂林山水职业学院', NULL, NULL, NULL, NULL, NULL, '472', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (238, 'D000D2D63', 'D000D2', '5000162', '广西城市职业学院', NULL, NULL, NULL, NULL, NULL, '474', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (239, 'D000D2D64', 'D000D2', '5000163', '广西英华国际职业学院', NULL, NULL, NULL, NULL, NULL, '475', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (240, 'D000D2D65', 'D000D2', '5000175', '广西工程职业学院', NULL, NULL, NULL, NULL, NULL, '487', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (241, 'D000D2D66', 'D000D2', '5000174', '广西理工职业技术学院', NULL, NULL, NULL, NULL, NULL, '486', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (242, 'D000D2D67', 'D000D2', '5000173', '广西经济职业学院', NULL, NULL, NULL, NULL, NULL, '485', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (243, 'D000D2D68', 'D000D2', '5000176', '广西科技职业学院', NULL, NULL, NULL, NULL, NULL, '488', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (244, 'D000D2D69', 'D000D2', '5000186', '广西培贤国际职业学院', NULL, NULL, NULL, NULL, NULL, '544', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (245, 'D000D2D70', 'D000D2', '11070221', '广西广播电视大学', NULL, NULL, NULL, NULL, NULL, '34213', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (246, 'D000D2D71', 'D000D2', '5000133', '广西教育学院', NULL, NULL, NULL, NULL, NULL, '445', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (247, 'D000D2D72', 'D000D2', '5000134', '广西经济管理干部学院', NULL, NULL, NULL, NULL, NULL, '446', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (248, 'D000D2D73', 'D000D2', '5000136', '广西政法管理干部学院', NULL, NULL, NULL, NULL, NULL, '448', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (249, 'D000D2D74', 'D000D2', '5000137', '南宁地区教育学院', NULL, NULL, NULL, NULL, NULL, '449', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (250, 'D000D2D75', 'D000D2', '11070223', '桂林市职工大学', NULL, NULL, NULL, NULL, NULL, '34215', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (251, 'D000D2D76', 'D000D2', '11070225', '玉柴职业技术学院', NULL, NULL, NULL, NULL, NULL, '34217', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (252, 'D000D2D77', 'D000D2', '11070227', '广西蓝天航空职业学院', NULL, NULL, NULL, NULL, NULL, '34219', '6125719', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (253, 'D000D3D34', 'D000D3', '5349631', '巴中职业技术学院', NULL, NULL, NULL, NULL, NULL, '22114', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (254, 'D000D3D35', 'D000D3', '5000079', '电子科技大学', NULL, NULL, NULL, NULL, NULL, '303', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (255, 'D000D3D36', 'D000D3', '5349544', '四川大学', NULL, NULL, NULL, NULL, NULL, '22001', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (256, 'D000D3D37', 'D000D3', '5343463', '西南交通大学', NULL, NULL, NULL, NULL, NULL, '22004', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (257, 'D000D3D38', 'D000D3', '5343483', '西南财经大学', NULL, NULL, NULL, NULL, NULL, '22009', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (258, 'D000D3D39', 'D000D3', '5343972', '西南民族大学', NULL, NULL, NULL, NULL, NULL, '22007', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (259, 'D000D3D40', 'D000D3', '5349547', '成都中医药大学', NULL, NULL, NULL, NULL, NULL, '22011', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (260, 'D000D3D41', 'D000D3', '5349550', '成都信息工程大学', NULL, NULL, NULL, NULL, NULL, '22012', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (261, 'D000D3D42', 'D000D3', '5349551', '四川音乐学院', NULL, NULL, NULL, NULL, NULL, '22016', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (262, 'D000D3D43', 'D000D3', '5349549', '成都体育学院', NULL, NULL, NULL, NULL, NULL, '22015', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (263, 'D000D3D44', 'D000D3', '5349553', '成都医学院', NULL, NULL, NULL, NULL, NULL, '22013', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (264, 'D000D3D45', 'D000D3', '5349557', '四川旅游学院', NULL, NULL, NULL, NULL, NULL, '22099', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (265, 'D000D3D46', 'D000D3', '5349571', '四川国际标榜职业学院', NULL, NULL, NULL, NULL, NULL, '22058', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (266, 'D000D3D47', 'D000D3', '5349565', '成都农业科技职业学院', NULL, NULL, NULL, NULL, NULL, '22038', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (267, 'D000D3D48', 'D000D3', '5349572', '成都艺术职业学院', NULL, NULL, NULL, NULL, NULL, '22039', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (268, 'D000D3D49', 'D000D3', '5349574', '四川商务职业学院', NULL, NULL, NULL, NULL, NULL, '22068', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (269, 'D000D3D50', 'D000D3', '5349577', '四川文化传媒职业学院', NULL, NULL, NULL, NULL, NULL, '22074', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (270, 'D000D3D51', 'D000D3', '5349575', '四川华新现代职业学院', NULL, NULL, NULL, NULL, NULL, '22060', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (271, 'D000D3D52', 'D000D3', '5349576', '四川管理职业学院', NULL, NULL, NULL, NULL, NULL, '22056', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (272, 'D000D3D53', 'D000D3', '5349578', '四川艺术职业学院', NULL, NULL, NULL, NULL, NULL, '22085', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (273, 'D000D3D54', 'D000D3', '5349580', '四川科技职业学院', NULL, NULL, NULL, NULL, NULL, '22091', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (274, 'D000D3D55', 'D000D3', '5349581', '四川文化产业职业学院', NULL, NULL, NULL, NULL, NULL, '22090', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (275, 'D000D3D56', 'D000D3', '5349579', '四川财经职业学院', NULL, NULL, NULL, NULL, NULL, '22106', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (276, 'D000D3D57', 'D000D3', '5349583', '四川城市职业学院', NULL, NULL, NULL, NULL, NULL, '22107', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (277, 'D000D3D58', 'D000D3', '5349559', '成都东软学院', NULL, NULL, NULL, NULL, NULL, '22033', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (278, 'D000D3D59', 'D000D3', '5349556', '四川传媒学院', NULL, NULL, NULL, NULL, NULL, '22101', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (279, 'D000D3D60', 'D000D3', '5349558', '成都文理学院', NULL, NULL, NULL, NULL, NULL, '22102', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (280, 'D000D3D61', 'D000D3', '5349584', '四川电影电视学院', NULL, NULL, NULL, NULL, NULL, '22122', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (281, 'D000D3D62', 'D000D3', '5349635', '四川大学锦城学院', NULL, NULL, NULL, NULL, NULL, '22081', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (282, 'D000D3D63', 'D000D3', '5349637', '电子科技大学成都学院', NULL, NULL, NULL, NULL, NULL, '22042', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (283, 'D000D3D64', 'D000D3', '5925579', '四川工商学院', NULL, NULL, NULL, NULL, NULL, '34107', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (284, 'D000D3D65', 'D000D3', '5349642', '四川大学锦江学院', NULL, NULL, NULL, NULL, NULL, '22082', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (285, 'D000D3D66', 'D000D3', '5349560', '成都纺织高等专科学校', NULL, NULL, NULL, NULL, NULL, '22034', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (286, 'D000D3D67', 'D000D3', '10970082', '民办四川天一学院', NULL, NULL, NULL, NULL, NULL, '22049', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (287, 'D000D3D68', 'D000D3', '5349563', '四川电力职业技术学院', NULL, NULL, NULL, NULL, NULL, '22053', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (288, 'D000D3D69', 'D000D3', '5349566', '成都职业技术学院', NULL, NULL, NULL, NULL, NULL, '22040', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (289, 'D000D3D70', 'D000D3', '5349569', '四川水利职业技术学院', NULL, NULL, NULL, NULL, NULL, '22070', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (290, 'D000D3D71', 'D000D3', '5349568', '四川航天职业技术学院', NULL, NULL, NULL, NULL, NULL, '22059', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (291, 'D000D3D72', 'D000D3', '5349567', '四川邮电职业技术学院', NULL, NULL, NULL, NULL, NULL, '22076', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (292, 'D000D3D73', 'D000D3', '5349573', '四川托普信息技术职业学院', NULL, NULL, NULL, NULL, NULL, '22072', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (293, 'D000D3D74', 'D000D3', '5349582', '四川现代职业学院', NULL, NULL, NULL, NULL, NULL, '22108', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (294, 'D000D3D75', 'D000D3', '5349711', '四川长江职业学院', NULL, NULL, NULL, NULL, NULL, '13035', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (295, 'D000D3D76', 'D000D3', '5349585', '四川护理职业学院', NULL, NULL, NULL, NULL, NULL, '22119', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (296, 'D000D3D77', 'D000D3', '5349587', '成都工业职业技术学院', NULL, NULL, NULL, NULL, NULL, '22120', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (297, 'D000D3D78', 'D000D3', '5349588', '四川西南航空职业学院', NULL, NULL, NULL, NULL, NULL, '22121', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (298, 'D000D3D79', 'D000D3', '10970302', '成都工贸职业技术学院', NULL, NULL, NULL, NULL, NULL, '34206', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (299, 'D000D3D80', 'D000D3', '5349625', '达州职业技术学院', NULL, NULL, NULL, NULL, NULL, '22041', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (300, 'D000D3D81', 'D000D3', '5349602', '四川司法警官职业学院', NULL, NULL, NULL, NULL, NULL, '22071', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (301, 'D000D3D82', 'D000D3', '5349601', '四川工业科技学院', NULL, NULL, NULL, NULL, NULL, '22103', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (302, 'D000D3D83', 'D000D3', '5349639', '四川外国语大学成都学院', NULL, NULL, NULL, NULL, NULL, '34035', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (303, 'D000D3D84', 'D000D3', '5349564', '四川工商职业技术学院', NULL, NULL, NULL, NULL, NULL, '22055', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (304, 'D000D3D85', 'D000D3', '5349626', '广安职业技术学院', NULL, NULL, NULL, NULL, NULL, '22043', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (305, 'D000D3D86', 'D000D3', '5349612', '川北幼儿师范高等专科学校', NULL, NULL, NULL, NULL, NULL, '22111', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (306, 'D000D3D87', 'D000D3', '5349618', '乐山职业技术学院', NULL, NULL, NULL, NULL, NULL, '22045', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (307, 'D000D3D88', 'D000D3', '5349640', '成都理工大学工程技术学院', NULL, NULL, NULL, NULL, NULL, '22089', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (308, 'D000D3D89', 'D000D3', '5349616', '川南幼儿师范高等专科学校', NULL, NULL, NULL, NULL, NULL, '22118', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (309, 'D000D3D90', 'D000D3', '5349594', '四川警察学院', NULL, NULL, NULL, NULL, NULL, '22021', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (310, 'D000D3D91', 'D000D3', '5349595', '泸州职业技术学院', NULL, NULL, NULL, NULL, NULL, '22046', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (311, 'D000D3D92', 'D000D3', '5349597', '四川三河职业学院', NULL, NULL, NULL, NULL, NULL, '22110', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (312, 'D000D3D93', 'D000D3', '5349629', '眉山职业技术学院', NULL, NULL, NULL, NULL, NULL, '22047', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (313, 'D000D3D94', 'D000D3', '5349643', '西南科技大学城市学院', NULL, NULL, NULL, NULL, NULL, '22092', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (314, 'D000D3D95', 'D000D3', '5349607', '四川中医药高等专科学校', NULL, NULL, NULL, NULL, NULL, '22078', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (315, 'D000D3D96', 'D000D3', '5349608', '四川幼儿师范高等专科学校', NULL, NULL, NULL, NULL, NULL, '22109', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (316, 'D000D3D97', 'D000D3', '5349609', '四川汽车职业技术学院', NULL, NULL, NULL, NULL, NULL, '22113', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (317, 'D000D3D98', 'D000D3', '5349610', '四川电子机械职业技术学院', NULL, NULL, NULL, NULL, NULL, '22116', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (318, 'D000D3D99', 'D000D3', '5349644', '西南交通大学希望学院', NULL, NULL, NULL, NULL, NULL, '34036', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (319, 'D000D3D100', 'D000D3', '5349621', '南充职业技术学院', NULL, NULL, NULL, NULL, NULL, '22051', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (320, 'D000D3D101', 'D000D3', '5349615', '内江职业技术学院', NULL, NULL, NULL, NULL, NULL, '22050', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (321, 'D000D3D102', 'D000D3', '5349592', '四川机电职业技术学院', NULL, NULL, NULL, NULL, NULL, '22062', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (322, 'D000D3D103', 'D000D3', '5349613', '四川职业技术学院', NULL, NULL, NULL, NULL, NULL, '22077', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (323, 'D000D3D104', 'D000D3', '10970304', '四川应用技术职业学院', NULL, NULL, NULL, NULL, NULL, '34208', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (324, 'D000D3D105', 'D000D3', '5349628', '雅安职业技术学院', NULL, NULL, NULL, NULL, NULL, '22079', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (325, 'D000D3D106', 'D000D3', '5349622', '宜宾学院', NULL, NULL, NULL, NULL, NULL, '22029', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (326, 'D000D3D107', 'D000D3', '5349623', '宜宾职业技术学院', NULL, NULL, NULL, NULL, NULL, '22080', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (327, 'D000D3D108', 'D000D3', '5349630', '四川希望汽车职业学院', NULL, NULL, NULL, NULL, NULL, '22115', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (328, 'D000D3D109', 'D000D3', '5349590', '四川卫生康复职业学院', NULL, NULL, NULL, NULL, NULL, '22112', '6791279', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (329, 'D000D5D86', 'D000D5', '5000198', '华侨大学', NULL, NULL, NULL, NULL, NULL, '556', '8164073', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (330, 'D000D6D1', 'D000D6', '8076821', '贵州理工学院', NULL, NULL, NULL, NULL, NULL, '21051', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (331, 'D000D6D2', 'D000D6', '5375828', '贵州大学', NULL, NULL, NULL, NULL, NULL, '21001', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (332, 'D000D6D3', 'D000D6', '10980656', '贵州师范大学', NULL, NULL, NULL, NULL, NULL, '21007', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (333, 'D000D6D4', 'D000D6', '10980682', '贵州财经大学', NULL, NULL, NULL, NULL, NULL, '21004', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (334, 'D000D6D5', 'D000D6', '5458595', '贵州民族大学', NULL, NULL, NULL, NULL, NULL, '21005', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (335, 'D000D6D6', 'D000D6', '10980746', '贵州医科大学', NULL, NULL, NULL, NULL, NULL, '34210', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (336, 'D000D6D7', 'D000D6', '5458593', '贵阳中医学院', NULL, NULL, NULL, NULL, NULL, '21003', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (337, 'D000D6D8', 'D000D6', '5458599', '遵义医学院', NULL, NULL, NULL, NULL, NULL, '21009', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (338, 'D000D6D9', 'D000D6', '5458597', '贵州师范学院', NULL, NULL, NULL, NULL, NULL, '21050', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (339, 'D000D6D10', 'D000D6', '11160247', '贵州职业技术学院', NULL, NULL, NULL, NULL, NULL, '21056', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (340, 'D000D6D11', 'D000D6', '6793033', '贵州商学院', NULL, NULL, NULL, NULL, NULL, '34153', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (341, 'D000D6D12', 'D000D6', '5375800', '贵阳学院', NULL, NULL, NULL, NULL, NULL, '21006', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (342, 'D000D6D13', 'D000D6', '5375798', '遵义师范学院', NULL, NULL, NULL, NULL, NULL, '21010', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (343, 'D000D6D14', 'D000D6', '10980698', '安顺学院', NULL, NULL, NULL, NULL, NULL, '21013', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (344, 'D000D6D15', 'D000D6', '5375803', '六盘水师范学院', NULL, NULL, NULL, NULL, NULL, '21026', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (345, 'D000D6D16', 'D000D6', '5375799', '贵州工程应用技术学院', NULL, NULL, NULL, NULL, NULL, '21048', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (346, 'D000D6D17', 'D000D6', '5375801', '铜仁学院', NULL, NULL, NULL, NULL, NULL, '21008', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (347, 'D000D6D18', 'D000D6', '5375804', '凯里学院', NULL, NULL, NULL, NULL, NULL, '21014', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (348, 'D000D6D19', 'D000D6', '5375806', '黔南民族师范学院', NULL, NULL, NULL, NULL, NULL, '21049', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (349, 'D000D6D20', 'D000D6', '5375797', '兴义民族师范学院', NULL, NULL, NULL, NULL, NULL, '21047', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (350, 'D000D7D1', 'D000D7', '5375786', '东北大学', NULL, NULL, NULL, NULL, NULL, '7002', '14363624', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (351, 'D000D7D2', 'D000D7', '5370525', '大连理工大学', NULL, NULL, NULL, NULL, NULL, '7001', '14363624', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (352, 'D000D7D3', 'D000D7', '5421099', '大连海事大学', NULL, NULL, NULL, NULL, NULL, '7004', '14363624', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (353, 'D000D7D4', 'D000D7', '13556568', '沈阳建筑大学', NULL, NULL, NULL, NULL, NULL, '7018', '14363624', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (354, 'D000D7D5', 'D000D7', '13875412', '沈阳农业大学', NULL, NULL, NULL, NULL, NULL, '7019', '14363624', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (355, 'D000D7D6', 'D000D7', '5387476', '中国医科大学', NULL, NULL, NULL, NULL, NULL, '7031', '14363624', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (356, 'D000D7D7', 'D000D7', '13875430', '沈阳航空航天大学', NULL, NULL, NULL, NULL, NULL, '7108', '14363624', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (357, 'D000D7D8', 'D000D7', '13875458', '沈阳体育学院', NULL, NULL, NULL, NULL, NULL, '7028', '14363624', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (358, 'D000D7D9', 'D000D7', '5387120', '东北财经大学', NULL, NULL, NULL, NULL, NULL, '7005', '14363624', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (359, 'D000D7D10', 'D000D7', '5387113', '辽宁科技学院', NULL, NULL, NULL, NULL, NULL, '7039', '14363624', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (360, 'D000D7D11', 'D000D7', '13771076', '锦州医科大学', NULL, NULL, NULL, NULL, NULL, '34256', '14363624', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (361, 'D000D7D12', 'D000D7', '14362600', '辽宁铁道职业技术学院', NULL, NULL, NULL, NULL, NULL, '7135', '14363624', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (362, 'D000D7D13', 'D000D7', '14362550', '辽宁轨道交通职业学院', NULL, NULL, NULL, NULL, NULL, '7133', '14363624', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (363, 'D000D8D1', 'D000D8', '5421155', '浙江理工大学', NULL, NULL, NULL, NULL, NULL, '30002', '14653414', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (364, 'D000D8D2', 'D000D8', '5458631', '杭州电子科技大学', NULL, NULL, NULL, NULL, NULL, '30004', '14653414', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (365, 'D000D8D3', 'D000D8', '5458635', '浙江工商大学', NULL, NULL, NULL, NULL, NULL, '30006', '14653414', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (366, 'D000D8D4', 'D000D8', '11310515', '中国计量大学', NULL, NULL, NULL, NULL, NULL, '30007', '14653414', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (367, 'D000D8D5', 'D000D8', '5534735', '浙江传媒学院', NULL, NULL, NULL, NULL, NULL, '30011', '14653414', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (368, 'D000D8D6', 'D000D8', '14634562', '浙江水利水电学院', NULL, NULL, NULL, NULL, NULL, '30067', '14653414', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (369, 'D000D8D7', 'D000D8', '5421161', '浙江金融职业学院', NULL, NULL, NULL, NULL, NULL, '30061', '14653414', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (370, 'D000D8D8', 'D000D8', '5421159', '浙江经济职业技术学院', NULL, NULL, NULL, NULL, NULL, '30062', '14653414', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (371, 'D000D9D1', 'D000D9', '5458627', '云南大学', NULL, NULL, NULL, NULL, NULL, '20012', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (372, 'D000D9D2', 'D000D9', '13649068', '昆明理工大学', NULL, NULL, NULL, NULL, NULL, '20001', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (373, 'D000D9D3', 'D000D9', '5375819', '云南师范大学', NULL, NULL, NULL, NULL, NULL, '20003', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (374, 'D000D9D4', 'D000D9', '13677218', '昆明医科大学', NULL, NULL, NULL, NULL, NULL, '20063', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (375, 'D000D9D5', 'D000D9', '13677240', '云南农业大学', NULL, NULL, NULL, NULL, NULL, '20002', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (376, 'D000D9D6', 'D000D9', '5375821', '云南中医学院', NULL, NULL, NULL, NULL, NULL, '20008', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (377, 'D000D9D7', 'D000D9', '13677320', '云南民族大学', NULL, NULL, NULL, NULL, NULL, '20005', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (378, 'D000D9D8', 'D000D9', '5458625', '云南财经大学', NULL, NULL, NULL, NULL, NULL, '20004', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (379, 'D000D9D9', 'D000D9', '13677410', '西南林业大学', NULL, NULL, NULL, NULL, NULL, '20006', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (380, 'D000D9D10', 'D000D9', '5375820', '云南艺术学院', NULL, NULL, NULL, NULL, NULL, '20011', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (381, 'D000D9D11', 'D000D9', '13677234', '云南警官学院', NULL, NULL, NULL, NULL, NULL, '20010', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (382, 'D000D9D12', 'D000D9', '11174967', '云南开放大学', NULL, NULL, NULL, NULL, NULL, '20036', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (383, 'D000D9D13', 'D000D9', '13677542', '昆明学院', NULL, NULL, NULL, NULL, NULL, '20056', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (384, 'D000D9D14', 'D000D9', '6190611', '大理大学', NULL, NULL, NULL, NULL, NULL, '34125', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (385, 'D000D9D15', 'D000D9', '13677602', '玉溪师范学院', NULL, NULL, NULL, NULL, NULL, '20014', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (386, 'D000D9D16', 'D000D9', '13677660', '曲靖师范学院', NULL, NULL, NULL, NULL, NULL, '20013', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (387, 'D000D9D17', 'D000D9', '6793037', '楚雄师范学院', NULL, NULL, NULL, NULL, NULL, '20015', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (388, 'D000D9D18', 'D000D9', '13677774', '红河学院', NULL, NULL, NULL, NULL, NULL, '20009', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (389, 'D000D9D19', 'D000D9', '5446205', '保山学院', NULL, NULL, NULL, NULL, NULL, '20065', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (390, 'D000D9D20', 'D000D9', '5446211', '文山学院', NULL, NULL, NULL, NULL, NULL, '20066', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (391, 'D000D9D21', 'D000D9', '5446215', '昭通学院', NULL, NULL, NULL, NULL, NULL, '20054', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (392, 'D000D9D22', 'D000D9', '13678078', '普洱学院', NULL, NULL, NULL, NULL, NULL, '20064', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (393, 'D000D9D23', 'D000D9', '6064681', '滇西科技师范学院', NULL, NULL, NULL, NULL, NULL, '34113', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (394, 'D000D9D24', 'D000D9', '11170533', '滇西应用技术大学', NULL, NULL, NULL, NULL, NULL, '34225', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (395, 'D000D9D25', 'D000D9', '13678316', '云南大学滇池学院', NULL, NULL, NULL, NULL, NULL, '20021', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (396, 'D000D9D26', 'D000D9', '13678576', '昆明理工大学津桥学院', NULL, NULL, NULL, NULL, NULL, '20057', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (397, 'D000D9D27', 'D000D9', '13678672', '云南师范大学商学院', NULL, NULL, NULL, NULL, NULL, '20048', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (398, 'D000D9D28', 'D000D9', '13678726', '云南师范大学文理学院', NULL, NULL, NULL, NULL, NULL, '20058', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (399, 'D000D9D29', 'D000D9', '13676926', '云南艺术学院文华学院', NULL, NULL, NULL, NULL, NULL, '20060', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (400, 'D000D9D30', 'D000D9', '5458629', '云南大学旅游文化学院', NULL, NULL, NULL, NULL, NULL, '20061', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (401, 'D000D9D31', 'D000D9', '13678886', '云南工商学院', NULL, NULL, NULL, NULL, NULL, '20067', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (402, 'D000D9D32', 'D000D9', '13678932', '云南经济管理学院', NULL, NULL, NULL, NULL, NULL, '20041', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (403, 'D000D9D33', 'D000D9', '5458623', '昆明冶金高等专科学校', NULL, NULL, NULL, NULL, NULL, '20026', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (404, 'D000D9D34', 'D000D9', '13678992', '云南交通职业技术学院', NULL, NULL, NULL, NULL, NULL, '20040', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (405, 'D000D9D35', 'D000D9', '13679034', '云南机电职业技术学院', NULL, NULL, NULL, NULL, NULL, '20039', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (406, 'D000D9D36', 'D000D9', '13679080', '云南国土资源职业学院', NULL, NULL, NULL, NULL, NULL, '20038', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (407, 'D000D9D37', 'D000D9', '13679150', '云南林业职业技术学院', NULL, NULL, NULL, NULL, NULL, '20043', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (408, 'D000D9D38', 'D000D9', '13679206', '云南农业职业技术学院', NULL, NULL, NULL, NULL, NULL, '20045', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (409, 'D000D9D39', 'D000D9', '13679240', '昆明工业职业技术学院', NULL, NULL, NULL, NULL, NULL, '20023', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (410, 'D000D9D40', 'D000D9', '5446209', '云南司法警官职业学院', NULL, NULL, NULL, NULL, NULL, '20049', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (411, 'D000D9D41', 'D000D9', '13679334', '云南文化艺术职业学院', NULL, NULL, NULL, NULL, NULL, '20051', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (412, 'D000D9D42', 'D000D9', '13679364', '云南体育运动职业技术学院', NULL, NULL, NULL, NULL, NULL, '20050', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (413, 'D000D9D43', 'D000D9', '13769844', '德宏师范高等专科学校', NULL, NULL, NULL, NULL, NULL, '20020', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (414, 'D000D9D44', 'D000D9', '13679486', '丽江师范高等专科学校', NULL, NULL, NULL, NULL, NULL, '20028', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (415, 'D000D9D45', 'D000D9', '13679548', '云南旅游职业学院', NULL, NULL, NULL, NULL, NULL, '20078', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (416, 'D000D9D46', 'D000D9', '13679654', '曲靖医学高等专科学校', NULL, NULL, NULL, NULL, NULL, '20029', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (417, 'D000D9D47', 'D000D9', '13679734', '楚雄医药高等专科学校', NULL, NULL, NULL, NULL, NULL, '20019', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (418, 'D000D9D48', 'D000D9', '13679840', '西双版纳职业技术学院', NULL, NULL, NULL, NULL, NULL, '20032', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (419, 'D000D9D49', 'D000D9', '13679876', '云南能源职业技术学院', NULL, NULL, NULL, NULL, NULL, '20044', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (420, 'D000D9D50', 'D000D9', '5446213', '玉溪农业职业技术学院', NULL, NULL, NULL, NULL, NULL, '20033', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (421, 'D000D9D51', 'D000D9', '13651018', '云南锡业职业技术学院', NULL, NULL, NULL, NULL, NULL, '20071', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (422, 'D000D9D52', 'D000D9', '5446207', '德宏职业学院', NULL, NULL, NULL, NULL, NULL, '20074', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (423, 'D000D9D53', 'D000D9', '13647296', '红河卫生职业学院', NULL, NULL, NULL, NULL, NULL, '20079', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (424, 'D000D9D54', 'D000D9', '13680010', '大理农林职业技术学院', NULL, NULL, NULL, NULL, NULL, '20081', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (425, 'D000D9D55', 'D000D9', '11170537', '云南财经职业学院', NULL, NULL, NULL, NULL, NULL, '34229', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (426, 'D000D9D56', 'D000D9', '11170543', '昆明铁道职业技术学院', NULL, NULL, NULL, NULL, NULL, '34233', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (427, 'D000D9D57', 'D000D9', '11170541', '云南水利水电职业学院', NULL, NULL, NULL, NULL, NULL, '34231', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (428, 'D000D9D58', 'D000D9', '11170545', '昭通卫生职业学院', NULL, NULL, NULL, NULL, NULL, '34235', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (429, 'D000D9D59', 'D000D9', '11170535', '大理护理职业学院', NULL, NULL, NULL, NULL, NULL, '34227', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (430, 'D000D9D60', 'D000D9', '11170551', '云南交通运输职业学院', NULL, NULL, NULL, NULL, NULL, '34241', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (431, 'D000D9D61', 'D000D9', '11170549', '云南工贸职业技术学院', NULL, NULL, NULL, NULL, NULL, '34239', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (432, 'D000D9D62', 'D000D9', '11170547', '云南轻纺职业学院', NULL, NULL, NULL, NULL, NULL, '34237', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (433, 'D000D9D63', 'D000D9', '11170553', '云南特殊教育职业学院', NULL, NULL, NULL, NULL, NULL, '34243', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (434, 'D000D9D64', 'D000D9', '13680186', '云南科技信息职业学院', NULL, NULL, NULL, NULL, NULL, '20042', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (435, 'D000D9D65', 'D000D9', '13680254', '昆明艺术职业学院', NULL, NULL, NULL, NULL, NULL, '20027', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (436, 'D000D9D66', 'D000D9', '13680416', '云南城市建设职业学院', NULL, NULL, NULL, NULL, NULL, '20068', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (437, 'D000D9D67', 'D000D9', '13680470', '云南新兴职业学院', NULL, NULL, NULL, NULL, NULL, '20052', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (438, 'D000D9D68', 'D000D9', '13680518', '云南工程职业学院', NULL, NULL, NULL, NULL, NULL, '20069', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (439, 'D000D9D69', 'D000D9', '13678730', '云南经贸外事职业学院', NULL, NULL, NULL, NULL, NULL, '20072', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (440, 'D000D9D70', 'D000D9', '13684356', '云南三鑫职业技术学院', NULL, NULL, NULL, NULL, NULL, '20073', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (441, 'D000D9D71', 'D000D9', '13684458', '云南商务职业学院', NULL, NULL, NULL, NULL, NULL, '20075', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (442, 'D000D9D72', 'D000D9', '13678030', '昆明卫生职业学院', NULL, NULL, NULL, NULL, NULL, '20076', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (443, 'D000D9D73', 'D000D9', '13684634', '云南外事外语职业学院', NULL, NULL, NULL, NULL, NULL, '20080', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (444, 'D000D10D1', 'D000D10', '5530757', '西藏民族大学', NULL, NULL, NULL, NULL, NULL, '34079', '14653428', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (445, 'D000D11D1', 'D000D11', '5000184', '西北农林科技大学', NULL, NULL, NULL, NULL, NULL, '528', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (446, 'D000D11D2', 'D000D11', '5370550', '陕西师范大学', NULL, NULL, NULL, NULL, NULL, '10005', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (447, 'D000D11D3', 'D000D11', '5370551', '西安电子科技大学', NULL, NULL, NULL, NULL, NULL, '10006', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (448, 'D000D11D4', 'D000D11', '5370552', '长安大学', NULL, NULL, NULL, NULL, NULL, '10002', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (449, 'D000D11D5', 'D000D11', '5370549', '西北工业大学', NULL, NULL, NULL, NULL, NULL, '10003', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (450, 'D000D11D6', 'D000D11', '5446197', '西安理工大学', NULL, NULL, NULL, NULL, NULL, '10007', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (451, 'D000D11D7', 'D000D11', '8025227', '西北大学', NULL, NULL, NULL, NULL, NULL, '10004', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (452, 'D000D11D8', 'D000D11', '8025241', '西安建筑科技大学', NULL, NULL, NULL, NULL, NULL, '10025', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (453, 'D000D11D9', 'D000D11', '5370591', '陕西科技大学', NULL, NULL, NULL, NULL, NULL, '10034', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (454, 'D000D11D10', 'D000D11', '8025249', '西安科技大学', NULL, NULL, NULL, NULL, NULL, '10008', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (455, 'D000D11D11', 'D000D11', '5000084', '西安石油大学', NULL, NULL, NULL, NULL, NULL, '308', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (456, 'D000D11D12', 'D000D11', '8025261', '延安大学', NULL, NULL, NULL, NULL, NULL, '10031', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (457, 'D000D11D13', 'D000D11', '8025269', '西安工业大学', NULL, NULL, NULL, NULL, NULL, '10009', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (458, 'D000D11D14', 'D000D11', '5375808', '西安工程大学', NULL, NULL, NULL, NULL, NULL, '10069', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (459, 'D000D11D15', 'D000D11', '5446201', '西安外国语大学', NULL, NULL, NULL, NULL, NULL, '10010', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (460, 'D000D11D16', 'D000D11', '8025275', '西北政法大学', NULL, NULL, NULL, NULL, NULL, '10014', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (461, 'D000D11D17', 'D000D11', '8025283', '西安邮电大学', NULL, NULL, NULL, NULL, NULL, '10011', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (462, 'D000D11D18', 'D000D11', '8025291', '西安美术学院', NULL, NULL, NULL, NULL, NULL, '10016', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (463, 'D000D11D19', 'D000D11', '8025297', '西安体育学院', NULL, NULL, NULL, NULL, NULL, '10015', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (464, 'D000D11D20', 'D000D11', '8025335', '陕西中医药大学', NULL, NULL, NULL, NULL, NULL, '10032', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (465, 'D000D11D21', 'D000D11', '8025359', '西安医学院', NULL, NULL, NULL, NULL, NULL, '10012', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (466, 'D000D11D22', 'D000D11', '8025375', '咸阳师范学院', NULL, NULL, NULL, NULL, NULL, '10033', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (467, 'D000D11D23', 'D000D11', '8025385', '渭南师范学院', NULL, NULL, NULL, NULL, NULL, '10036', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (468, 'D000D11D24', 'D000D11', '8025395', '榆林学院', NULL, NULL, NULL, NULL, NULL, '10038', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (469, 'D000D11D25', 'D000D11', '8025405', '安康学院', NULL, NULL, NULL, NULL, NULL, '10040', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (470, 'D000D11D26', 'D000D11', '8025417', '商洛学院', NULL, NULL, NULL, NULL, NULL, '10039', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (471, 'D000D11D27', 'D000D11', '5375807', '西安航空学院', NULL, NULL, NULL, NULL, NULL, '10100', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (472, 'D000D11D28', 'D000D11', '5387136', '陕西学前师范学院', NULL, NULL, NULL, NULL, NULL, '10084', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (473, 'D000D11D29', 'D000D11', '5387137', '西京学院', NULL, NULL, NULL, NULL, NULL, '10019', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (474, 'D000D11D30', 'D000D11', '8025469', '陕西工业职业技术学院', NULL, NULL, NULL, NULL, NULL, '10050', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (475, 'D000D11D31', 'D000D11', '8025485', '西安航空职业技术学院', NULL, NULL, NULL, NULL, NULL, '10072', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (476, 'D000D11D32', 'D000D11', '8025501', '陕西铁路工程职业技术学院', NULL, NULL, NULL, NULL, NULL, '10060', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (477, 'D000D11D33', 'D000D11', '8025507', '宝鸡职业技术学院', NULL, NULL, NULL, NULL, NULL, '10043', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (478, 'D000D11D34', 'D000D11', '8025521', '西安医学高等专科学校', NULL, NULL, NULL, NULL, NULL, '10109', '14653438', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (479, 'D000D12D1', 'D000D12', '5370538', '中国石油大学（华东）', NULL, NULL, NULL, NULL, NULL, '34039', '14653442', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (480, 'D000D12D2', 'D000D12', '5370537', '中国海洋大学', NULL, NULL, NULL, NULL, NULL, '8002', '14653442', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (481, 'D000D12D3', 'D000D12', '5370536', '山东大学', NULL, NULL, NULL, NULL, NULL, '8001', '14653442', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (482, 'D000D12D4', 'D000D12', '11371327', '山东农业大学', NULL, NULL, NULL, NULL, NULL, '8033', '14653442', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (483, 'D000D12D5', 'D000D12', '5370582', '山东师范大学', NULL, NULL, NULL, NULL, NULL, '8005', '14653442', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (484, 'D000D12D6', 'D000D12', '11371305', '青岛大学', NULL, NULL, NULL, NULL, NULL, '8019', '14653442', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (485, 'D000D12D7', 'D000D12', '11371315', '曲阜师范大学', NULL, NULL, NULL, NULL, NULL, '8014', '14653442', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (486, 'D000D12D8', 'D000D12', '5534719', '山东科技大学', NULL, NULL, NULL, NULL, NULL, '8020', '14653442', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (487, 'D000D12D9', 'D000D12', '5370580', '青岛科技大学', NULL, NULL, NULL, NULL, NULL, '8021', '14653442', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (488, 'D000D12D10', 'D000D12', '5446181', '山东理工大学', NULL, NULL, NULL, NULL, NULL, '8027', '14653442', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (489, 'D000D12D11', 'D000D12', '5370579', '聊城大学', NULL, NULL, NULL, NULL, NULL, '8026', '14653442', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (490, 'D000D12D12', 'D000D12', '11263647', '济南大学', NULL, NULL, NULL, NULL, NULL, '8003', '14653442', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (491, 'D000D12D13', 'D000D12', '5458953', '齐鲁工业大学', NULL, NULL, NULL, NULL, NULL, '8161', '14653442', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (492, 'D000D12D14', 'D000D12', '13303764', '鲁东大学', NULL, NULL, NULL, NULL, NULL, '8016', '14653442', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (493, 'D000D13D1', 'D000D13', '5375787', '南京农业大学', NULL, NULL, NULL, NULL, NULL, '16010', '14653448', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (494, 'D000D13D2', 'D000D13', '5370528', '中国矿业大学', NULL, NULL, NULL, NULL, NULL, '34038', '14653448', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (495, 'D000D13D3', 'D000D13', '5350631', '钟山职业技术学院', NULL, NULL, NULL, NULL, NULL, '16122', '14653448', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (496, 'D000D14D1', 'D000D14', '5370541', '武汉理工大学', NULL, NULL, NULL, NULL, NULL, '13004', '14653452', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (497, 'D000D14D2', 'D000D14', '5365968', '武汉生物工程学院', NULL, NULL, NULL, NULL, NULL, '13017', '14653452', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (498, 'D000D14D3', 'D000D14', '5370543', '中南财经政法大学', NULL, NULL, NULL, NULL, NULL, '13006', '14653452', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (499, 'D000D15D1', 'D000D15', '5531749', '淮北师范大学', NULL, NULL, NULL, NULL, NULL, '29092', '14653458', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (500, 'D000D7D14', 'D000D7', '14654764', '辽宁大学', NULL, NULL, NULL, NULL, NULL, '7003', '14363624', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (501, 'D000D16D1', 'D000D16', '5446157', '哈尔滨工程大学', NULL, NULL, NULL, NULL, NULL, '5002', '14684028', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (502, 'D000D16D2', 'D000D16', '5387135', '东北农业大学', NULL, NULL, NULL, NULL, NULL, '5004', '14684028', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (503, 'D000D16D3', 'D000D16', '5458931', '齐齐哈尔大学', NULL, NULL, NULL, NULL, NULL, '5017', '14684028', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (504, 'D000D16D4', 'D000D16', '14424558', '牡丹江师范学院', NULL, NULL, NULL, NULL, NULL, '5023', '14684028', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (505, 'D000D16D5', 'D000D16', '14428472', '佳木斯大学', NULL, NULL, NULL, NULL, NULL, '5016', '14684028', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (506, 'D000D16D6', 'D000D16', '14428490', '东北石油大学', NULL, NULL, NULL, NULL, NULL, '5020', '14684028', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (507, 'D000D16D7', 'D000D16', '14428514', '黑龙江八一农垦大学', NULL, NULL, NULL, NULL, NULL, '5019', '14684028', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (508, 'D000D16D8', 'D000D16', '14428520', '黑龙江工程学院', NULL, NULL, NULL, NULL, NULL, '5007', '14684028', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (509, 'D000D16D9', 'D000D16', '5458927', '哈尔滨学院', NULL, NULL, NULL, NULL, NULL, '5009', '14684028', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (510, 'D000D16D10', 'D000D16', '14428530', '绥化学院', NULL, NULL, NULL, NULL, NULL, '5024', '14684028', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (511, 'D000D16D11', 'D000D16', '14428562', '黑龙江农业工程职业学院', NULL, NULL, NULL, NULL, NULL, '5047', '14684028', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (512, 'D000D16D12', 'D000D16', '14428576', '黑龙江民族职业学院', NULL, NULL, NULL, NULL, NULL, '5043', '14684028', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (513, 'D000D16D13', 'D000D16', '14428588', '黑龙江旅游职业技术学院', NULL, NULL, NULL, NULL, NULL, '5041', '14684028', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (514, 'D000D16D14', 'D000D16', '14428602', '哈尔滨铁道职业技术学院', NULL, NULL, NULL, NULL, NULL, '5032', '14684028', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (515, 'D000D16D15', 'D000D16', '14428622', '哈尔滨职业技术学院', NULL, NULL, NULL, NULL, NULL, '5034', '14684028', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (516, 'D000D16D16', 'D000D16', '14428634', '哈尔滨科学技术职业学院', NULL, NULL, NULL, NULL, NULL, '5100', '14684028', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (517, 'D000D16D17', 'D000D16', '14428656', '大庆医学高等专科学校', NULL, NULL, NULL, NULL, NULL, '5026', '14684028', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (518, 'D000D17D1', 'D000D17', '5000182', '东北师范大学', NULL, NULL, NULL, NULL, NULL, '526', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (519, 'D000D17D2', 'D000D17', '5387123', '长春工业大学', NULL, NULL, NULL, NULL, NULL, '6016', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (520, 'D000D17D3', 'D000D17', '5387127', '长春中医药大学', NULL, NULL, NULL, NULL, NULL, '6005', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (521, 'D000D17D4', 'D000D17', '5387130', '吉林农业大学', NULL, NULL, NULL, NULL, NULL, '6004', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (522, 'D000D17D5', 'D000D17', '11400709', '长春工程学院', NULL, NULL, NULL, NULL, NULL, '6009', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (523, 'D000D17D6', 'D000D17', '14362354', '长春理工大学', NULL, NULL, NULL, NULL, NULL, '6017', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (524, 'D000D17D7', 'D000D17', '14432264', '吉林电子信息职业技术学院', NULL, NULL, NULL, NULL, NULL, '6035', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (525, 'D000D17D8', 'D000D17', '14683052', '吉林工程技术师范学院', NULL, NULL, NULL, NULL, NULL, '6011', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (526, 'D000D17D9', 'D000D17', '14683094', '吉林工程职业学院', NULL, NULL, NULL, NULL, NULL, '6069', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (527, 'D000D18D1', 'D000D18', '5446165', '内蒙古大学', NULL, NULL, NULL, NULL, NULL, '23001', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (528, 'D000D18D2', 'D000D18', '7852596', '内蒙古农业大学', NULL, NULL, NULL, NULL, NULL, '23003', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (529, 'D000D18D3', 'D000D18', '8150887', '内蒙古师范大学', NULL, NULL, NULL, NULL, NULL, '23004', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (530, 'D000D18D4', 'D000D18', '9734119', '内蒙古工业大学', NULL, NULL, NULL, NULL, NULL, '23002', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (531, 'D000D18D5', 'D000D18', '11992450', '呼和浩特职业学院', NULL, NULL, NULL, NULL, NULL, '23015', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (532, 'D000D18D6', 'D000D18', '12664980', '包头轻工职业技术学院', NULL, NULL, NULL, NULL, NULL, '23012', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (533, 'D000D18D7', 'D000D18', '13340490', '内蒙古商贸职业学院', NULL, NULL, NULL, NULL, NULL, '23026', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (534, 'D000D18D8', 'D000D18', '13898620', '内蒙古科技大学包头医学院', NULL, NULL, NULL, NULL, NULL, '23054', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (535, 'D000D18D9', 'D000D18', '14246562', '内蒙古化工职业学院', NULL, NULL, NULL, NULL, NULL, '23021', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (536, 'D000D18D10', 'D000D18', '14497836', '内蒙古建筑职业技术学院', NULL, NULL, NULL, NULL, NULL, '23023', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (537, 'D000D18D11', 'D000D18', '14504378', '内蒙古电子信息职业技术学院', NULL, NULL, NULL, NULL, NULL, '23019', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (538, 'D000D19D1', 'D000D19', '5189448', '兰州理工大学', NULL, NULL, NULL, NULL, NULL, '25009', '14684046', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (539, 'D000D20D1', 'D000D20', '5374636', '石河子大学', NULL, NULL, NULL, NULL, NULL, '28007', '14684048', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (540, 'D000D20D2', 'D000D20', '5374635', '新疆石河子职业技术学院', NULL, NULL, NULL, NULL, NULL, '28027', '14684048', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (541, 'D000D20D3', 'D000D20', '5374637', '塔里木大学', NULL, NULL, NULL, NULL, NULL, '28008', '14684048', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (542, 'D000D20D4', 'D000D20', '5374638', '新疆生产建设兵团兴新职业技术学院', NULL, NULL, NULL, NULL, NULL, '28047', '14684048', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (543, 'D000D20D5', 'D000D20', '5374639', '新疆兵团警官高等专科学校', NULL, NULL, NULL, NULL, NULL, '28018', '14684048', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (544, 'D000D21D1', 'D000D21', '5000185', '海南大学', NULL, NULL, NULL, NULL, NULL, '529', '14684052', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (545, 'D000D21D2', 'D000D21', '11257981', '海南医学院', NULL, NULL, NULL, NULL, NULL, '15002', '14684052', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (546, 'D000D21D3', 'D000D21', '11175227', '三亚学院', NULL, NULL, NULL, NULL, NULL, '15016', '14684052', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (547, 'D000D21D4', 'D000D21', '5458601', '海南经贸职业技术学院', NULL, NULL, NULL, NULL, NULL, '15007', '14684052', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (548, 'D000D21D5', 'D000D21', '5375818', '海南外国语职业学院', NULL, NULL, NULL, NULL, NULL, '15009', '14684052', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (549, 'D000D14D4', 'D000D14', '5370540', '中国地质大学（武汉）', NULL, NULL, NULL, NULL, NULL, '13005', '14653452', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (550, 'D000D14D5', 'D000D14', '5370567', '武汉科技大学', NULL, NULL, NULL, NULL, NULL, '13022', '14653452', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (551, 'D000D14D6', 'D000D14', '14767034', '武汉工程大学', NULL, NULL, NULL, NULL, NULL, '13021', '14653452', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (552, 'D000D14D7', 'D000D14', '14767094', '长江工程职业技术学院', NULL, NULL, NULL, NULL, NULL, '13034', '14653452', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (553, 'D000D14D8', 'D000D14', '14767116', '武汉城市职业学院', NULL, NULL, NULL, NULL, NULL, '13136', '14653452', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (554, 'D000D14D9', 'D000D14', '14767148', '湖北水利水电职业技术学院', NULL, NULL, NULL, NULL, NULL, '13054', '14653452', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (555, 'D000D14D10', 'D000D14', '14767192', '湖北生物科技职业学院', NULL, NULL, NULL, NULL, NULL, '13053', '14653452', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (556, 'D000D14D11', 'D000D14', '14767202', '武昌职业学院', NULL, NULL, NULL, NULL, NULL, '13137', '14653452', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (557, 'D000D7D15', 'D000D7', '13875582', '渤海大学', NULL, NULL, NULL, NULL, NULL, '7036', '14363624', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (558, 'D000D7D16', 'D000D7', '14735866', '大连交通大学', NULL, NULL, NULL, NULL, NULL, '7007', '14363624', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (559, 'D000D22D1', 'D000D22', '5000207', '中南大学', NULL, NULL, NULL, NULL, NULL, '565', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (560, 'D000D22D2', 'D000D22', '5341035', '湖南大学', NULL, NULL, NULL, NULL, NULL, '14003', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (561, 'D000D22D3', 'D000D22', '13382694', '湖南师范大学', NULL, NULL, NULL, NULL, NULL, '14001', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (562, 'D000D22D4', 'D000D22', '5421133', '湘潭大学', NULL, NULL, NULL, NULL, NULL, '14013', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (563, 'D000D22D5', 'D000D22', '13178046', '长沙理工大学', NULL, NULL, NULL, NULL, NULL, '14004', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (564, 'D000D22D6', 'D000D22', '15079646', '湖南农业大学', NULL, NULL, NULL, NULL, NULL, '14005', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (565, 'D000D22D7', 'D000D22', '12482218', '中南林业科技大学', NULL, NULL, NULL, NULL, NULL, '14007', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (566, 'D000D22D8', 'D000D22', '5370558', '湖南中医药大学', NULL, NULL, NULL, NULL, NULL, '14006', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (567, 'D000D22D9', 'D000D22', '13229242', '南华大学', NULL, NULL, NULL, NULL, NULL, '14016', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (568, 'D000D22D10', 'D000D22', '5370621', '湖南科技大学', NULL, NULL, NULL, NULL, NULL, '14014', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (569, 'D000D22D11', 'D000D22', '13385516', '衡阳师范学院', NULL, NULL, NULL, NULL, NULL, '14022', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (570, 'D000D22D12', 'D000D22', '14070774', '湖南文理学院', NULL, NULL, NULL, NULL, NULL, '14023', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (571, 'D000D22D13', 'D000D22', '5370560', '湖南城市学院', NULL, NULL, NULL, NULL, NULL, '14019', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (572, 'D000D22D14', 'D000D22', '13179162', '怀化学院', NULL, NULL, NULL, NULL, NULL, '14024', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (573, 'D000D22D15', 'D000D22', '14569326', '湘南学院', NULL, NULL, NULL, NULL, NULL, '14021', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (574, 'D000D22D16', 'D000D22', '15080012', '长沙学院', NULL, NULL, NULL, NULL, NULL, '14008', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (575, 'D000D22D17', 'D000D22', '10991694', '湖南财政经济学院', NULL, NULL, NULL, NULL, NULL, '14122', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (576, 'D000D22D18', 'D000D22', '5370557', '长沙民政职业技术学院', NULL, NULL, NULL, NULL, NULL, '14031', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (577, 'D000D22D19', 'D000D22', '12994158', '长沙航空职业技术学院', NULL, NULL, NULL, NULL, NULL, '14029', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (578, 'D000D22D20', 'D000D22', '15080060', '湖南科技职业学院', NULL, NULL, NULL, NULL, NULL, '14127', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (579, 'D000D22D21', 'D000D22', '5421139', '湖南交通职业技术学院', NULL, NULL, NULL, NULL, NULL, '14058', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (580, 'D000D22D22', 'D000D22', '13088902', '湖南工程职业技术学院', NULL, NULL, NULL, NULL, NULL, '14047', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (581, 'D000D22D23', 'D000D22', '15065562', '湖南铁道职业技术学院', NULL, NULL, NULL, NULL, NULL, '14077', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (582, 'D000D22D24', 'D000D22', '6286813', '湘潭医卫职业技术学院', NULL, NULL, NULL, NULL, NULL, '34129', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (583, 'D000D22D25', 'D000D22', '11104975', '湖南工艺美术职业学院', NULL, NULL, NULL, NULL, NULL, '14050', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (584, 'D000D22D26', 'D000D22', '14088270', '湖南科技学院', NULL, NULL, NULL, NULL, NULL, '14025', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (585, 'D000D4D32', 'D000D4', '5458579', '东莞理工学院', NULL, NULL, NULL, NULL, NULL, '18028', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (586, 'D000D4D33', 'D000D4', '11249833', '东莞理工学院城市学院', NULL, NULL, NULL, NULL, NULL, '18043', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (587, 'D000D4D34', 'D000D4', '11249837', '广东白云学院', NULL, NULL, NULL, NULL, NULL, '18022', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (588, 'D000D4D35', 'D000D4', '11249847', '广东工贸职业技术学院', NULL, NULL, NULL, NULL, NULL, '18051', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (589, 'D000D4D36', 'D000D4', '11249851', '广东环境保护工程职业学院', NULL, NULL, NULL, NULL, NULL, '18167', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (590, 'D000D4D37', 'D000D4', '11249855', '广东机电职业技术学院', NULL, NULL, NULL, NULL, NULL, '18056', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (591, 'D000D4D38', 'D000D4', '5375813', '广东理工职业学院', NULL, NULL, NULL, NULL, NULL, '18063', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (592, 'D000D4D39', 'D000D4', '11249863', '广东松山职业技术学院', NULL, NULL, NULL, NULL, NULL, '18071', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (593, 'D000D4D40', 'D000D4', '11249865', '广东邮电职业技术学院', NULL, NULL, NULL, NULL, NULL, '18079', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (594, 'D000D4D41', 'D000D4', '11249869', '广州体育学院', NULL, NULL, NULL, NULL, NULL, '18017', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (595, 'D000D4D42', 'D000D4', '11249881', '广州现代信息工程职业技术学院', NULL, NULL, NULL, NULL, NULL, '18098', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (596, 'D000D4D43', 'D000D4', '11249907', '惠州学院', NULL, NULL, NULL, NULL, NULL, '18035', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (597, 'D000D4D44', 'D000D4', '11249919', '吉林大学珠海学院', NULL, NULL, NULL, NULL, NULL, '18104', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (598, 'D000D4D45', 'D000D4', '11249929', '江门职业技术学院', NULL, NULL, NULL, NULL, NULL, '18105', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (599, 'D000D4D46', 'D000D4', '11249937', '汕头大学', NULL, NULL, NULL, NULL, NULL, '18024', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (600, 'D000D4D47', 'D000D4', '11249987', '韶关学院', NULL, NULL, NULL, NULL, NULL, '18032', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (601, 'D000D4D48', 'D000D4', '5375816', '顺德职业技术学院', NULL, NULL, NULL, NULL, NULL, '18117', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (602, 'D000D4D49', 'D000D4', '5370545', '中山大学', NULL, NULL, NULL, NULL, NULL, '18002', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (603, 'D000D22D27', 'D000D22', '13599086', '湖南工业大学', NULL, NULL, NULL, NULL, NULL, '14018', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (604, 'D000D22D28', 'D000D22', '15079716', '湖南商学院', NULL, NULL, NULL, NULL, NULL, '14011', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (605, 'D000D22D29', 'D000D22', '5421137', '邵阳学院', NULL, NULL, NULL, NULL, NULL, '34047', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (606, 'D000D16D18', 'D000D16', '5370527', '东北林业大学', NULL, NULL, NULL, NULL, NULL, '5003', '14684028', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (607, 'D000D16D19', 'D000D16', '14424430', '黑龙江大学', NULL, NULL, NULL, NULL, NULL, '5012', '14684028', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (608, 'D000D16D20', 'D000D16', '5387134', '哈尔滨师范大学', NULL, NULL, NULL, NULL, NULL, '5014', '14684028', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (609, 'D000D16D21', 'D000D16', '14424446', '哈尔滨理工大学', NULL, NULL, NULL, NULL, NULL, '5015', '14684028', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (610, 'D000D16D22', 'D000D16', '14424538', '哈尔滨商业大学', NULL, NULL, NULL, NULL, NULL, '5013', '14684028', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (611, 'D000D16D23', 'D000D16', '5387132', '黑龙江科技大学', NULL, NULL, NULL, NULL, NULL, '5008', '14684028', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (612, 'D000D16D24', 'D000D16', '15143082', '齐齐哈尔医学院', NULL, NULL, NULL, NULL, NULL, '5018', '14684028', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (613, 'D000D6D21', 'D000D6', '13569826', '贵州大学明德学院', NULL, NULL, NULL, NULL, NULL, '21042', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (614, 'D000D6D22', 'D000D6', '15281709', '贵阳幼儿师范高等专科学校', NULL, NULL, NULL, NULL, NULL, '21059', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (615, 'D000D6D23', 'D000D6', '15385423', '贵州建设职业技术学院', NULL, NULL, NULL, NULL, NULL, '21063', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (616, 'D000D6D24', 'D000D6', '5375805', '遵义医药高等专科学校', NULL, NULL, NULL, NULL, NULL, '21034', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (617, 'D000D4D50', 'D000D4', '15270868', '南方医科大学', NULL, NULL, NULL, NULL, NULL, '18010', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (618, 'D000D4D51', 'D000D4', '15270870', '韩山师范学院', NULL, NULL, NULL, NULL, NULL, '18033', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (619, 'D000D4D52', 'D000D4', '5458583', '广东金融学院', NULL, NULL, NULL, NULL, NULL, '18014', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (620, 'D000D4D53', 'D000D4', '15270872', '广东省外语艺术职业学院', NULL, NULL, NULL, NULL, NULL, '18157', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (621, 'D000D4D54', 'D000D4', '15270876', '广东科学技术职业学院', NULL, NULL, NULL, NULL, NULL, '18062', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (622, 'D000D4D55', 'D000D4', '15270878', '广东工程职业技术学院', NULL, NULL, NULL, NULL, NULL, '18050', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (623, 'D000D4D56', 'D000D4', '15270882', '广东体育职业技术学院', NULL, NULL, NULL, NULL, NULL, '18072', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (624, 'D000D4D57', 'D000D4', '15270884', '广州体育职业技术学院', NULL, NULL, NULL, NULL, NULL, '18096', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (625, 'D000D4D58', 'D000D4', '15270886', '广州工程技术职业学院', NULL, NULL, NULL, NULL, NULL, '18085', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (626, 'D000D4D59', 'D000D4', '15270888', '广州铁路职业技术学院', NULL, NULL, NULL, NULL, NULL, '18097', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (627, 'D000D4D60', 'D000D4', '15270890', '广州科技贸易职业学院', NULL, NULL, NULL, NULL, NULL, '18091', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (628, 'D000D4D61', 'D000D4', '15270892', '深圳信息职业技术学院', NULL, NULL, NULL, NULL, NULL, '18114', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (629, 'D000D4D62', 'D000D4', '15270894', '汕尾职业技术学院', NULL, NULL, NULL, NULL, NULL, '18113', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (630, 'D000D4D63', 'D000D4', '5370586', '中山火炬职业技术学院', NULL, NULL, NULL, NULL, NULL, '18126', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (631, 'D000D4D64', 'D000D4', '15270898', '佛山职业技术学院', NULL, NULL, NULL, NULL, NULL, '18046', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (632, 'D000D4D65', 'D000D4', '15270900', '阳江职业技术学院', NULL, NULL, NULL, NULL, NULL, '18119', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (633, 'D000D4D66', 'D000D4', '15271000', '广东茂名幼儿师范专科学校', NULL, NULL, NULL, NULL, NULL, '34278', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (634, 'D000D4D67', 'D000D4', '15270904', '清远职业技术学院', NULL, NULL, NULL, NULL, NULL, '18111', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (635, 'D000D4D68', 'D000D4', '15270906', '罗定职业技术学院', NULL, NULL, NULL, NULL, NULL, '18107', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (636, 'D000D4D69', 'D000D4', '15270908', '广州科技职业技术学院', NULL, NULL, NULL, NULL, NULL, '18092', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (637, 'D000D4D70', 'D000D4', '15270910', '广东文理职业学院', NULL, NULL, NULL, NULL, NULL, '18162', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (638, 'D000D4D71', 'D000D4', '15270914', '广州华商职业学院', NULL, NULL, NULL, NULL, NULL, '18165', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (639, 'D000D4D72', 'D000D4', '15270920', '广东碧桂园职业学院', NULL, NULL, NULL, NULL, NULL, '18176', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (640, 'D000D4D73', 'D000D4', '15270922', '北京师范大学珠海分校', NULL, NULL, NULL, NULL, NULL, '18041', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (641, 'D000D4D74', 'D000D4', '15270924', '北京理工大学珠海学院', NULL, NULL, NULL, NULL, NULL, '18040', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (642, 'D000D4D75', 'D000D4', '15270926', '广东外语外贸大学南国商学院', NULL, NULL, NULL, NULL, NULL, '18038', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (643, 'D000D23D1', 'D000D23', '5387265', '河北大学', NULL, NULL, NULL, NULL, NULL, '11001', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (644, 'D000D23D2', 'D000D23', '5387105', '燕山大学', NULL, NULL, NULL, NULL, NULL, '11012', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (645, 'D000D23D3', 'D000D23', '15187734', '河北农业大学', NULL, NULL, NULL, NULL, NULL, '11053', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (646, 'D000D23D4', 'D000D23', '15802155', '河北师范大学', NULL, NULL, NULL, NULL, NULL, '11006', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (647, 'D000D23D5', 'D000D23', '15802227', '河北医科大学', NULL, NULL, NULL, NULL, NULL, '11008', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (648, 'D000D23D6', 'D000D23', '15802309', '河北经贸大学', NULL, NULL, NULL, NULL, NULL, '11009', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (649, 'D000D23D7', 'D000D23', '12191018', '河北科技大学', NULL, NULL, NULL, NULL, NULL, '11007', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (650, 'D000D23D8', 'D000D23', '5458925', '石家庄铁道大学', NULL, NULL, NULL, NULL, NULL, '11004', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (651, 'D000D23D9', 'D000D23', '5458917', '河北工程大学', NULL, NULL, NULL, NULL, NULL, '11026', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (652, 'D000D23D10', 'D000D23', '15821219', '河北地质大学', NULL, NULL, NULL, NULL, NULL, '34285', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (653, 'D000D23D11', 'D000D23', '15817639', '河北中医学院', NULL, NULL, NULL, NULL, NULL, '11136', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (654, 'D000D23D12', 'D000D23', '14185658', '承德医学院', NULL, NULL, NULL, NULL, NULL, '11024', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (655, 'D000D23D13', 'D000D23', '15817669', '河北金融学院', NULL, NULL, NULL, NULL, NULL, '11031', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (656, 'D000D23D14', 'D000D23', '15817685', '华北科技学院', NULL, NULL, NULL, NULL, NULL, '11021', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (657, 'D000D23D15', 'D000D23', '15817717', '承德石油高等专科学校', NULL, NULL, NULL, NULL, NULL, '11040', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (658, 'D000D23D16', 'D000D23', '15817755', '邢台职业技术学院', NULL, NULL, NULL, NULL, NULL, '11101', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (659, 'D000D23D17', 'D000D23', '15817785', '河北建材职业技术学院', NULL, NULL, NULL, NULL, NULL, '11050', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (660, 'D000D23D18', 'D000D23', '15082430', '河北交通职业技术学院', NULL, NULL, NULL, NULL, NULL, '11051', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (661, 'D000D23D19', 'D000D23', '15817891', '石家庄铁路职业技术学院', NULL, NULL, NULL, NULL, NULL, '11087', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (662, 'D000D23D20', 'D000D23', '15817957', '河北工艺美术职业学院', NULL, NULL, NULL, NULL, NULL, '11158', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (663, 'D000D23D21', 'D000D23', '15817983', '石家庄学院', NULL, NULL, NULL, NULL, NULL, '11011', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (664, 'D000D23D22', 'D000D23', '15082486', '唐山学院', NULL, NULL, NULL, NULL, NULL, '11016', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (665, 'D000D23D23', 'D000D23', '15818027', '邯郸学院', NULL, NULL, NULL, NULL, NULL, '11027', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (666, 'D000D23D24', 'D000D23', '15818099', '河北民族师范学院', NULL, NULL, NULL, NULL, NULL, '11125', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (667, 'D000D23D25', 'D000D23', '15818135', '河北外国语学院', NULL, NULL, NULL, NULL, NULL, '11134', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (668, 'D000D23D26', 'D000D23', '15818179', '河北工程技术学院', NULL, NULL, NULL, NULL, NULL, '11131', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (669, 'D000D23D27', 'D000D23', '14975186', '石家庄科技工程职业学院', NULL, NULL, NULL, NULL, NULL, '11146', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (670, 'D000D23D28', 'D000D23', '15818217', '渤海理工职业学院', NULL, NULL, NULL, NULL, NULL, '11159', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (671, 'D000D23D29', 'D000D23', '15818333', '河北师范大学汇华学院', NULL, NULL, NULL, NULL, NULL, '11110', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (672, 'D000D23D30', 'D000D23', '15821221', '河北地质大学华信学院', NULL, NULL, NULL, NULL, NULL, '34287', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (673, 'D000D23D31', 'D000D23', '15819057', '河北工业职业技术学院', NULL, NULL, NULL, NULL, NULL, '11046', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (674, 'D000D23D32', 'D000D23', '15340483', '沧州师范学院', NULL, NULL, NULL, NULL, NULL, '11126', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (675, 'D000D23D33', 'D000D23', '15149256', '衡水学院', NULL, NULL, NULL, NULL, NULL, '11028', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (676, 'D000D23D34', 'D000D23', '15895415', '北华航天工业学院', NULL, NULL, NULL, NULL, NULL, '11018', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (677, 'D000D23D35', 'D000D23', '16173657', '秦皇岛职业技术学院', NULL, NULL, NULL, NULL, NULL, '11080', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (678, 'D000D9D74', 'D000D9', '13679770', '保山中医药高等专科学校', NULL, NULL, NULL, NULL, NULL, '20018', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (679, 'D000D9D75', 'D000D9', '13684602', '云南现代职业技术学院', NULL, NULL, NULL, NULL, NULL, '20077', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (680, 'D000D23D36', 'D000D23', '5387106', '河北工业大学', NULL, NULL, NULL, NULL, NULL, '11121', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (681, 'D000D23D37', 'D000D23', '17329298', '沧州医学高等专科学校', NULL, NULL, NULL, NULL, NULL, '11036', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (682, 'D000D6D25', 'D000D6', '15461731', '贵阳护理职业学院', NULL, NULL, NULL, NULL, NULL, '21044', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (683, 'D000D6D26', 'D000D6', '15885453', '铜仁幼儿师范高等专科学校', NULL, NULL, NULL, NULL, NULL, '21060', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (684, 'D000D6D27', 'D000D6', '15885493', '毕节医学高等专科学校', NULL, NULL, NULL, NULL, NULL, '21062', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (685, 'D000D6D28', 'D000D6', '16123257', '遵义职业技术学院', NULL, NULL, NULL, NULL, NULL, '21035', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (686, 'D000D6D29', 'D000D6', '16156617', '贵州城市职业学院', NULL, NULL, NULL, NULL, NULL, '21052', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (687, 'D000D6D30', 'D000D6', '15978087', '安顺职业技术学院', NULL, NULL, NULL, NULL, NULL, '21015', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (688, 'D000D6D31', 'D000D6', '16386245', '贵州警察学院', NULL, NULL, NULL, NULL, NULL, '21021', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (689, 'D000D6D32', 'D000D6', '12435100', '贵州轻工职业技术学院', NULL, NULL, NULL, NULL, NULL, '21024', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (690, 'D000D6D33', 'D000D6', '16428251', '贵阳职业技术学院', NULL, NULL, NULL, NULL, NULL, '21055', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (691, 'D000D6D34', 'D000D6', '16479829', '毕节职业技术学院', NULL, NULL, NULL, NULL, NULL, '21011', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (692, 'D000D6D35', 'D000D6', '16479853', '贵州交通职业技术学院', NULL, NULL, NULL, NULL, NULL, '21020', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (693, 'D000D6D36', 'D000D6', '16624566', '黔南民族幼儿师范高等专科学校', NULL, NULL, NULL, NULL, NULL, '21061', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (694, 'D000D6D37', 'D000D6', '16945528', '贵州水利水电职业技术学院', NULL, NULL, NULL, NULL, NULL, '34296', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (695, 'D000D6D38', 'D000D6', '16911072', '黔南民族职业技术学院', NULL, NULL, NULL, NULL, NULL, '21030', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (696, 'D000D6D39', 'D000D6', '17308790', '贵州医科大学神奇民族医药学院', NULL, NULL, NULL, NULL, NULL, '34302', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (697, 'D000D6D40', 'D000D6', '17493986', '贵州航天职业技术学院', NULL, NULL, NULL, NULL, NULL, '21018', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (698, 'D000D18D12', 'D000D18', '14891784', '内蒙古科技大学', NULL, NULL, NULL, NULL, NULL, '23007', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (699, 'D000D18D13', 'D000D18', '15007546', '包头师范学院', NULL, NULL, NULL, NULL, NULL, '34260', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (700, 'D000D18D14', 'D000D18', '14979922', '内蒙古医科大学', NULL, NULL, NULL, NULL, NULL, '23005', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (701, 'D000D18D15', 'D000D18', '5387110', '内蒙古民族大学', NULL, NULL, NULL, NULL, NULL, '23008', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (702, 'D000D18D16', 'D000D18', '14979958', '赤峰学院', NULL, NULL, NULL, NULL, NULL, '23009', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (703, 'D000D18D17', 'D000D18', '14979990', '内蒙古财经大学', NULL, NULL, NULL, NULL, NULL, '23006', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (704, 'D000D18D18', 'D000D18', '14980036', '呼伦贝尔学院', NULL, NULL, NULL, NULL, NULL, '23010', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (705, 'D000D18D19', 'D000D18', '14862180', '集宁师范学院', NULL, NULL, NULL, NULL, NULL, '23016', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (706, 'D000D18D20', 'D000D18', '14980074', '河套学院', NULL, NULL, NULL, NULL, NULL, '23014', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (707, 'D000D18D21', 'D000D18', '14751916', '呼和浩特民族学院', NULL, NULL, NULL, NULL, NULL, '23040', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (708, 'D000D18D22', 'D000D18', '15007548', '内蒙古大学交通职业技术学院', NULL, NULL, NULL, NULL, NULL, '34262', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (709, 'D000D18D23', 'D000D18', '14980842', '内蒙古大学创业学院', NULL, NULL, NULL, NULL, NULL, '34157', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (710, 'D000D18D24', 'D000D18', '5446171', '内蒙古师范大学鸿德学院', NULL, NULL, NULL, NULL, NULL, '34055', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (711, 'D000D18D25', 'D000D18', '15007550', '内蒙古艺术学院', NULL, NULL, NULL, NULL, NULL, '34264', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (712, 'D000D18D26', 'D000D18', '5446167', '鄂尔多斯应用技术学院', NULL, NULL, NULL, NULL, NULL, '34053', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (713, 'D000D18D27', 'D000D18', '14980370', '内蒙古丰州职业学院', NULL, NULL, NULL, NULL, NULL, '23035', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (714, 'D000D18D28', 'D000D18', '14980454', '包头职业技术学院', NULL, NULL, NULL, NULL, NULL, '23013', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (715, 'D000D18D29', 'D000D18', '14980592', '兴安职业技术学院', NULL, NULL, NULL, NULL, NULL, '23032', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (716, 'D000D18D30', 'D000D18', '14750138', '内蒙古机电职业技术学院', NULL, NULL, NULL, NULL, NULL, '23022', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (717, 'D000D18D31', 'D000D18', '14993382', '锡林郭勒职业学院', NULL, NULL, NULL, NULL, NULL, '23031', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (718, 'D000D18D32', 'D000D18', '14993400', '内蒙古警察职业学院', NULL, NULL, NULL, NULL, NULL, '23033', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (719, 'D000D18D33', 'D000D18', '14993426', '内蒙古体育职业学院', NULL, NULL, NULL, NULL, NULL, '23027', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (720, 'D000D18D34', 'D000D18', '14993440', '乌兰察布职业学院', NULL, NULL, NULL, NULL, NULL, '23030', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (721, 'D000D18D35', 'D000D18', '5387111', '通辽职业学院', NULL, NULL, NULL, NULL, NULL, '23028', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (722, 'D000D18D36', 'D000D18', '14993498', '科尔沁艺术职业学院', NULL, NULL, NULL, NULL, NULL, '23017', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (723, 'D000D18D37', 'D000D18', '5387112', '内蒙古交通职业技术学院', NULL, NULL, NULL, NULL, NULL, '23024', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (724, 'D000D18D38', 'D000D18', '14993522', '包头钢铁职业技术学院', NULL, NULL, NULL, NULL, NULL, '23011', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (725, 'D000D18D39', 'D000D18', '14993528', '乌海职业技术学院', NULL, NULL, NULL, NULL, NULL, '23029', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (726, 'D000D18D40', 'D000D18', '14993550', '内蒙古科技职业学院', NULL, NULL, NULL, NULL, NULL, '23037', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (727, 'D000D18D41', 'D000D18', '14993574', '内蒙古北方职业技术学院', NULL, NULL, NULL, NULL, NULL, '23034', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (728, 'D000D18D42', 'D000D18', '14993612', '赤峰职业技术学院', NULL, NULL, NULL, NULL, NULL, '23038', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (729, 'D000D18D43', 'D000D18', '14993628', '内蒙古经贸外语职业学院', NULL, NULL, NULL, NULL, NULL, '23036', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (730, 'D000D18D44', 'D000D18', '14993656', '包头铁道职业技术学院', NULL, NULL, NULL, NULL, NULL, '23041', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (731, 'D000D18D45', 'D000D18', '15057008', '乌兰察布医学高等专科学校', NULL, NULL, NULL, NULL, NULL, '23042', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (732, 'D000D18D46', 'D000D18', '14764360', '鄂尔多斯职业学院', NULL, NULL, NULL, NULL, NULL, '23043', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (733, 'D000D18D47', 'D000D18', '14994910', '内蒙古工业职业学院', NULL, NULL, NULL, NULL, NULL, '23044', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (734, 'D000D18D48', 'D000D18', '14994932', '呼伦贝尔职业技术学院', NULL, NULL, NULL, NULL, NULL, '23045', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (735, 'D000D18D49', 'D000D18', '14994980', '内蒙古能源职业学院', NULL, NULL, NULL, NULL, NULL, '23047', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (736, 'D000D18D50', 'D000D18', '14995010', '赤峰工业职业技术学院', NULL, NULL, NULL, NULL, NULL, '23048', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (737, 'D000D18D51', 'D000D18', '14995030', '阿拉善职业技术学院', NULL, NULL, NULL, NULL, NULL, '23049', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (738, 'D000D18D52', 'D000D18', '14995048', '内蒙古美术职业学院', NULL, NULL, NULL, NULL, NULL, '23050', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (739, 'D000D18D53', 'D000D18', '5446169', '内蒙古民族幼儿师范高等专科学校', NULL, NULL, NULL, NULL, NULL, '23051', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (740, 'D000D18D54', 'D000D18', '14995078', '鄂尔多斯生态环境职业学院', NULL, NULL, NULL, NULL, NULL, '23052', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (741, 'D000D18D55', 'D000D18', '15007552', '扎兰屯职业学院', NULL, NULL, NULL, NULL, NULL, '34266', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (742, 'D000D18D56', 'D000D18', '15029070', '内蒙古师范大学青年政治学院', NULL, NULL, NULL, NULL, NULL, '34272', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (743, 'D000D18D57', 'D000D18', '14994960', '满洲里学院', NULL, NULL, NULL, NULL, NULL, '23046', '14684040', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (744, 'D000D23D38', 'D000D23', '5458921', '华北理工大学', NULL, NULL, NULL, NULL, NULL, '34075', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (745, 'D000D23D39', 'D000D23', '17633316', '石家庄铁道大学四方学院', NULL, NULL, NULL, NULL, NULL, '11114', '15966103', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (746, 'D000D8D9', 'D000D8', '15028266', '浙江工商大学杭州商学院', NULL, NULL, NULL, NULL, NULL, '34268', '14653414', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (747, 'D000D8D10', 'D000D8', '15028268', '杭州电子科技大学信息工程学院', NULL, NULL, NULL, NULL, NULL, '34270', '14653414', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (748, 'D000D4D76', 'D000D4', '5370546', '华南理工大学', NULL, NULL, NULL, NULL, NULL, '18001', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (749, 'D000D4D77', 'D000D4', '5370554', '暨南大学', NULL, NULL, NULL, NULL, NULL, '18003', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (750, 'D000D4D78', 'D000D4', '15486791', '华南师范大学', NULL, NULL, NULL, NULL, NULL, '18004', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (751, 'D000D4D79', 'D000D4', '5458585', '广东外语外贸大学', NULL, NULL, NULL, NULL, NULL, '18008', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (752, 'D000D4D80', 'D000D4', '15486881', '广州美术学院', NULL, NULL, NULL, NULL, NULL, '18018', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (753, 'D000D4D81', 'D000D4', '17012920', '广东警官学院', NULL, NULL, NULL, NULL, NULL, '18016', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (754, 'D000D4D82', 'D000D4', '15493979', '广州航海学院', NULL, NULL, NULL, NULL, NULL, '18087', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (755, 'D000D4D83', 'D000D4', '15486983', '深圳大学', NULL, NULL, NULL, NULL, NULL, '18023', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (756, 'D000D4D84', 'D000D4', '15487001', '广州民航职业技术学院', NULL, NULL, NULL, NULL, NULL, '18093', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (757, 'D000D4D85', 'D000D4', '15487041', '广东职业技术学院', NULL, NULL, NULL, NULL, NULL, '18156', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (758, 'D000D4D86', 'D000D4', '15487065', '广东交通职业技术学院', NULL, NULL, NULL, NULL, NULL, '18060', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (759, 'D000D4D87', 'D000D4', '15487103', '广东水利电力职业技术学院', NULL, NULL, NULL, NULL, NULL, '18069', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (760, 'D000D4D88', 'D000D4', '15487121', '广东行政职业学院', NULL, NULL, NULL, NULL, NULL, '18077', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (761, 'D000D4D89', 'D000D4', '15487141', '广东文艺职业学院', NULL, NULL, NULL, NULL, NULL, '18075', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (762, 'D000D4D90', 'D000D4', '17012960', '广东舞蹈戏剧职业学院', NULL, NULL, NULL, NULL, NULL, '18171', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (763, 'D000D4D91', 'D000D4', '17013000', '广东生态工程职业学院', NULL, NULL, NULL, NULL, NULL, '18174', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (764, 'D000D4D92', 'D000D4', '15487165', '广州番禺职业技术学院', NULL, NULL, NULL, NULL, NULL, '18045', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (765, 'D000D4D93', 'D000D4', '15493513', '广州卫生职业技术学院', NULL, NULL, NULL, NULL, NULL, '34279', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (766, 'D000D4D94', 'D000D4', '17013036', '深圳职业技术学院', NULL, NULL, NULL, NULL, NULL, '18116', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (767, 'D000D4D95', 'D000D4', '15487211', '珠海城市职业技术学院', NULL, NULL, NULL, NULL, NULL, '18127', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (768, 'D000D4D96', 'D000D4', '15487241', '汕头职业技术学院', NULL, NULL, NULL, NULL, NULL, '18112', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (769, 'D000D4D97', 'D000D4', '17013074', '惠州卫生职业技术学院', NULL, NULL, NULL, NULL, NULL, '18172', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (770, 'D000D4D98', 'D000D4', '16179949', '中山职业技术学院', NULL, NULL, NULL, NULL, NULL, '18159', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (771, 'D000D4D99', 'D000D4', '15487251', '茂名职业技术学院', NULL, NULL, NULL, NULL, NULL, '18108', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (772, 'D000D4D100', 'D000D4', '15493515', '广东茂名健康职业学院', NULL, NULL, NULL, NULL, NULL, '34281', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (773, 'D000D4D101', 'D000D4', '15487279', '揭阳职业技术学院', NULL, NULL, NULL, NULL, NULL, '18106', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (774, 'D000D4D102', 'D000D4', '15493517', '湛江幼儿师范专科学校', NULL, NULL, NULL, NULL, NULL, '34283', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (775, 'D000D4D103', 'D000D4', '13712362', '广东培正学院', NULL, NULL, NULL, NULL, NULL, '18021', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (776, 'D000D4D104', 'D000D4', '15487389', '广州商学院', NULL, NULL, NULL, NULL, NULL, '18148', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (777, 'D000D4D105', 'D000D4', '17013124', '广东东软学院', NULL, NULL, NULL, NULL, NULL, '18147', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (778, 'D000D4D106', 'D000D4', '15487425', '广东理工学院', NULL, NULL, NULL, NULL, NULL, '18151', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (779, 'D000D4D107', 'D000D4', '15487457', '广东南华工商职业学院', NULL, NULL, NULL, NULL, NULL, '18109', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (780, 'D000D4D108', 'D000D4', '15487495', '私立华联学院', NULL, NULL, NULL, NULL, NULL, '18118', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (781, 'D000D4D109', 'D000D4', '17013264', '潮汕职业技术学院', NULL, NULL, NULL, NULL, NULL, '18048', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (782, 'D000D4D110', 'D000D4', '15487531', '广东岭南职业技术学院', NULL, NULL, NULL, NULL, NULL, '18064', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (783, 'D000D4D111', 'D000D4', '15487569', '广东亚视演艺职业学院', NULL, NULL, NULL, NULL, NULL, '18078', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (784, 'D000D4D112', 'D000D4', '15487609', '珠海艺术职业学院', NULL, NULL, NULL, NULL, NULL, '18128', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (785, 'D000D4D113', 'D000D4', '17013458', '广州涉外经济职业技术学院', NULL, NULL, NULL, NULL, NULL, '18095', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (786, 'D000D4D114', 'D000D4', '15487633', '广州南洋理工职业学院', NULL, NULL, NULL, NULL, NULL, '18094', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (787, 'D000D4D115', 'D000D4', '15487651', '惠州经济职业技术学院', NULL, NULL, NULL, NULL, NULL, '18103', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (788, 'D000D4D116', 'D000D4', '15487673', '广东工商职业学院', NULL, NULL, NULL, NULL, NULL, '18123', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (789, 'D000D4D117', 'D000D4', '15487703', '广州华南商贸职业学院', NULL, NULL, NULL, NULL, NULL, '18089', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (790, 'D000D4D118', 'D000D4', '15487713', '广州城建职业学院', NULL, NULL, NULL, NULL, NULL, '18163', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (791, 'D000D4D119', 'D000D4', '15487731', '广东南方职业学院', NULL, NULL, NULL, NULL, NULL, '18164', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (792, 'D000D4D120', 'D000D4', '17013492', '广州华夏职业学院', NULL, NULL, NULL, NULL, NULL, '18166', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (793, 'D000D4D121', 'D000D4', '17013522', '广东创新科技职业学院', NULL, NULL, NULL, NULL, NULL, '18170', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (794, 'D000D4D122', 'D000D4', '17013538', '广州东华职业学院', NULL, NULL, NULL, NULL, NULL, '18169', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (795, 'D000D4D123', 'D000D4', '17023136', '广东酒店管理职业技术学院', NULL, NULL, NULL, NULL, NULL, '34298', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (796, 'D000D4D124', 'D000D4', '15487739', '广东工业大学华立学院', NULL, NULL, NULL, NULL, NULL, '18052', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (797, 'D000D4D125', 'D000D4', '17013568', '广州大学松田学院', NULL, NULL, NULL, NULL, NULL, '18084', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (798, 'D000D4D126', 'D000D4', '15487759', '中山大学新华学院', NULL, NULL, NULL, NULL, NULL, '18134', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (799, 'D000D4D127', 'D000D4', '15487781', '华南农业大学珠江学院', NULL, NULL, NULL, NULL, NULL, '18101', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (800, 'D000D4D128', 'D000D4', '15494197', '广东财经大学华商学院', NULL, NULL, NULL, NULL, NULL, '18135', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (801, 'D000D4D129', 'D000D4', '17013608', '广东技术师范学院天河学院', NULL, NULL, NULL, NULL, NULL, '18130', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (802, 'D000D4D130', 'D000D4', '15487803', '广州大学华软软件学院', NULL, NULL, NULL, NULL, NULL, '18082', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (803, 'D000D4D131', 'D000D4', '17023140', '惠州工程职业学院', NULL, NULL, NULL, NULL, NULL, '34300', '7933855', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (804, 'D000D17D10', 'D000D17', '5370526', '吉林大学', NULL, NULL, NULL, NULL, NULL, '6001', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (805, 'D000D17D11', 'D000D17', '5387124', '延边大学', NULL, NULL, NULL, NULL, NULL, '6018', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (806, 'D000D17D12', 'D000D17', '5458933', '东北电力大学', NULL, NULL, NULL, NULL, NULL, '6006', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (807, 'D000D17D13', 'D000D17', '5387128', '北华大学', NULL, NULL, NULL, NULL, NULL, '6019', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (808, 'D000D17D14', 'D000D17', '5387121', '吉林师范大学', NULL, NULL, NULL, NULL, NULL, '6022', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (809, 'D000D17D15', 'D000D17', '5387122', '吉林财经大学', NULL, NULL, NULL, NULL, NULL, '6061', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (810, 'D000D17D16', 'D000D17', '5458939', '长春大学', NULL, NULL, NULL, NULL, NULL, '6003', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (811, 'D000D17D17', 'D000D17', '6395491', '吉林建筑大学', NULL, NULL, NULL, NULL, NULL, '34149', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (812, 'D000D17D18', 'D000D17', '17439786', '长春师范大学', NULL, NULL, NULL, NULL, NULL, '6010', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (813, 'D000D17D19', 'D000D17', '17439838', '吉林化工学院', NULL, NULL, NULL, NULL, NULL, '6007', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (814, 'D000D17D20', 'D000D17', '5458935', '吉林农业科技学院', NULL, NULL, NULL, NULL, NULL, '6020', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (815, 'D000D17D21', 'D000D17', '5387126', '吉林医药学院', NULL, NULL, NULL, NULL, NULL, '6021', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (816, 'D000D17D22', 'D000D17', '5458937', '通化师范学院', NULL, NULL, NULL, NULL, NULL, '6024', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (817, 'D000D17D23', 'D000D17', '5387129', '白城师范学院', NULL, NULL, NULL, NULL, NULL, '6023', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (818, 'D000D17D24', 'D000D17', '6395489', '吉林工商学院', NULL, NULL, NULL, NULL, NULL, '34147', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (819, 'D000D17D25', 'D000D17', '17439940', '吉林体育学院', NULL, NULL, NULL, NULL, NULL, '6014', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (820, 'D000D17D26', 'D000D17', '17439988', '吉林艺术学院', NULL, NULL, NULL, NULL, NULL, '6015', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (821, 'D000D17D27', 'D000D17', '17440016', '吉林警察学院', NULL, NULL, NULL, NULL, NULL, '6063', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (822, 'D000D17D28', 'D000D17', '17440064', '长春金融高等专科学校', NULL, NULL, NULL, NULL, NULL, '6027', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (823, 'D000D17D29', 'D000D17', '17440106', '白城医学高等专科学校', NULL, NULL, NULL, NULL, NULL, '6025', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (824, 'D000D17D30', 'D000D17', '17440134', '吉林交通职业技术学院', NULL, NULL, NULL, NULL, NULL, '6039', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (825, 'D000D17D31', 'D000D17', '17440168', '吉林工业职业技术学院', NULL, NULL, NULL, NULL, NULL, '6037', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (826, 'D000D17D32', 'D000D17', '17440214', '吉林铁道职业技术学院', NULL, NULL, NULL, NULL, NULL, '6057', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (827, 'D000D17D33', 'D000D17', '17440244', '吉林司法警官职业学院', NULL, NULL, NULL, NULL, NULL, '6043', '14684030', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (828, 'D000D9D76', 'D000D9', '6286817', '昆明医科大学海源学院', NULL, NULL, NULL, NULL, NULL, '34131', '14653422', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (829, 'D000D22D30', 'D000D22', '18879920', '吉首大学', NULL, NULL, NULL, NULL, NULL, '14017', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (830, 'D000D22D31', 'D000D22', '18882360', '湖南理工学院', NULL, NULL, NULL, NULL, NULL, '14020', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (831, 'D000D22D32', 'D000D22', '18882728', '湖南涉外经济学院', NULL, NULL, NULL, NULL, NULL, '14010', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (832, 'D000D22D33', 'D000D22', '5534757', '湖南工学院', NULL, NULL, NULL, NULL, NULL, '14048', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (833, 'D000D22D34', 'D000D22', '18883012', '湖南女子学院', NULL, NULL, NULL, NULL, NULL, '14067', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (834, 'D000D22D35', 'D000D22', '18883272', '湖南医药学院', NULL, NULL, NULL, NULL, NULL, '14124', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (835, 'D000D22D36', 'D000D22', '18883406', '湖南信息学院', NULL, NULL, NULL, NULL, NULL, '14082', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (836, 'D000D22D37', 'D000D22', '18883548', '湖南工业职业技术学院', NULL, NULL, NULL, NULL, NULL, '14049', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (837, 'D000D22D38', 'D000D22', '18883696', '湖南信息职业技术学院', NULL, NULL, NULL, NULL, NULL, '14083', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (838, 'D000D22D39', 'D000D22', '5421135', '湖南邮电职业技术学院', NULL, NULL, NULL, NULL, NULL, '14129', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (839, 'D000D22D40', 'D000D22', '18883876', '湖南商务职业技术学院', NULL, NULL, NULL, NULL, NULL, '14069', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (840, 'D000D22D41', 'D000D22', '18884096', '湖南生物机电职业技术学院', NULL, NULL, NULL, NULL, NULL, '14071', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (841, 'D000D22D42', 'D000D22', '18884230', '湖南机电职业技术学院', NULL, NULL, NULL, NULL, NULL, '14055', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (842, 'D000D22D43', 'D000D22', '18884420', '湖南艺术职业学院', NULL, NULL, NULL, NULL, NULL, '14086', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (843, 'D000D22D44', 'D000D22', '18884552', '湖南司法警官职业学院', NULL, NULL, NULL, NULL, NULL, '14075', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (844, 'D000D22D45', 'D000D22', '5370601', '长沙职业技术学院', NULL, NULL, NULL, NULL, NULL, '14037', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (845, 'D000D22D46', 'D000D22', '18885606', '湖南网络工程职业学院', NULL, NULL, NULL, NULL, NULL, '14080', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (846, 'D000D22D47', 'D000D22', '18885788', '湖南水利水电职业技术学院', NULL, NULL, NULL, NULL, NULL, '14072', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (847, 'D000D22D48', 'D000D22', '18885946', '湖南化工职业技术学院', NULL, NULL, NULL, NULL, NULL, '14131', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (848, 'D000D22D49', 'D000D22', '18886056', '湖南汽车工程职业学院', NULL, NULL, NULL, NULL, NULL, '14132', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (849, 'D000D22D50', 'D000D22', '18886208', '湖南理工职业技术学院', NULL, NULL, NULL, NULL, NULL, '14063', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (850, 'D000D22D51', 'D000D22', '18886318', '湖南电气职业技术学院', NULL, NULL, NULL, NULL, NULL, '14134', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (851, 'D000D22D52', 'D000D22', '14036078', '湖南环境生物职业技术学院', NULL, NULL, NULL, NULL, NULL, '14054', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (852, 'D000D22D53', 'D000D22', '18886546', '常德职业技术学院', NULL, NULL, NULL, NULL, NULL, '14038', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (853, 'D000D22D54', 'D000D22', '18886758', '娄底职业技术学院', NULL, NULL, NULL, NULL, NULL, '14092', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (854, 'D000D22D55', 'D000D22', '18886910', '永州职业技术学院', NULL, NULL, NULL, NULL, NULL, '14099', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (855, 'D000D22D56', 'D000D22', '18887020', '益阳职业技术学院', NULL, NULL, NULL, NULL, NULL, '14098', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (856, 'D000D22D57', 'D000D22', '5370559', '郴州职业技术学院', NULL, NULL, NULL, NULL, NULL, '14039', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (857, 'D000D22D58', 'D000D22', '18887198', '邵阳职业技术学院', NULL, NULL, NULL, NULL, NULL, '14094', '15083998', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (858, 'D000D6D41', 'D000D6', '17595904', '贵州食品工程职业学院', NULL, NULL, NULL, NULL, NULL, '34310', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (859, 'D000D6D42', 'D000D6', '17844462', '铜仁职业技术学院', NULL, NULL, NULL, NULL, NULL, '21033', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (860, 'D000D6D43', 'D000D6', '17846016', '贵州民族大学人文科技学院', NULL, NULL, NULL, NULL, NULL, '34312', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (861, 'D000D6D44', 'D000D6', '17872484', '贵州农业职业学院', NULL, NULL, NULL, NULL, NULL, '34314', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (862, 'D000D6D45', 'D000D6', '17717632', '贵州大学科技学院', NULL, NULL, NULL, NULL, NULL, '21043', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (863, 'D000D6D46', 'D000D6', '19913026', '贵州应用技术职业学院', NULL, NULL, NULL, NULL, NULL, '34320', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (864, 'D000D6D47', 'D000D6', '19909790', '黔南民族医学高等专科学校', NULL, NULL, NULL, NULL, NULL, '21029', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (865, 'D000D6D48', 'D000D6', '18129240', '毕节幼儿师范高等专科学校', NULL, NULL, NULL, NULL, NULL, '34318', '12884894', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (866, 'D000D15D2', 'D000D15', '5370533', '中国科学技术大学', NULL, NULL, NULL, NULL, NULL, '29001', '14653458', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (867, 'D000D15D3', 'D000D15', '5370534', '合肥工业大学', NULL, NULL, NULL, NULL, NULL, '29003', '14653458', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (868, 'D000D15D4', 'D000D15', '5375788', '安徽大学', NULL, NULL, NULL, NULL, NULL, '29002', '14653458', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (869, 'D000D15D5', 'D000D15', '5375790', '安徽师范大学', NULL, NULL, NULL, NULL, NULL, '29012', '14653458', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (870, 'D000D15D6', 'D000D15', '15181480', '安徽医科大学', NULL, NULL, NULL, NULL, NULL, '29004', '14653458', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (871, 'D000D15D7', 'D000D15', '5375789', '安徽工业大学', NULL, NULL, NULL, NULL, NULL, '29009', '14653458', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (872, 'D000D15D8', 'D000D15', '5375791', '安徽农业大学', NULL, NULL, NULL, NULL, NULL, '29008', '14653458', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (873, 'D000D15D9', 'D000D15', '5534749', '安徽财经大学', NULL, NULL, NULL, NULL, NULL, '29015', '14653458', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (874, 'D000D15D10', 'D000D15', '5421121', '安徽工商职业学院', NULL, NULL, NULL, NULL, NULL, '29034', '14653458', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (875, 'D000D15D11', 'D000D15', '5534775', '安庆师范大学', NULL, NULL, NULL, NULL, NULL, '29020', '14653458', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (876, 'D000D15D12', 'D000D15', '15181510', '皖南医学院', NULL, NULL, NULL, NULL, NULL, '29011', '14653458', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (877, 'D000D15D13', 'D000D15', '5370597', '安徽科技学院', NULL, NULL, NULL, NULL, NULL, '29010', '14653458', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (878, 'D000D15D14', 'D000D15', '5370592', '皖西学院', NULL, NULL, NULL, NULL, NULL, '29022', '14653458', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (879, 'D000D15D15', 'D000D15', '5370594', '淮南师范学院', NULL, NULL, NULL, NULL, NULL, '29017', '14653458', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (880, 'D000D15D16', 'D000D15', '5534747', '合肥学院', NULL, NULL, NULL, NULL, NULL, '29007', '14653458', 'SCHOOL', 5, 7);
INSERT INTO `org_info` VALUES (881, 'D000D15D17', 'D000D15', '15181924', '巢湖学院', NULL, NULL, NULL, NULL, NULL, '29023', '14653458', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (882, 'D000D15D18', 'D000D15', '5534743', '铜陵学院', NULL, NULL, NULL, NULL, NULL, '29021', '14653458', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (883, 'D000D15D19', 'D000D15', '15181942', '滁州学院', NULL, NULL, NULL, NULL, NULL, '29024', '14653458', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (884, 'D000D15D20', 'D000D15', '5534751', '亳州学院', NULL, NULL, NULL, NULL, NULL, '29064', '14653458', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (885, 'D000D15D21', 'D000D15', '15181952', '安徽新华学院', NULL, NULL, NULL, NULL, NULL, '29027', '14653458', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (886, 'D000D24D1', 'D000D24', '22110774', '青海大学', NULL, NULL, NULL, NULL, NULL, '26001', '22150004', 'SCHOOL', NULL, NULL);
INSERT INTO `org_info` VALUES (887, 'D000D4D1D1', 'D000D4D1', 'Z0001', '教务处', NULL, NULL, NULL, NULL, NULL, NULL, '5388957', 'DEPARTMENT', NULL, NULL);
INSERT INTO `org_info` VALUES (888, 'D000D4D1D2', 'D000D4D1', 'Z0002', '党支部', NULL, NULL, NULL, NULL, NULL, NULL, '5388957', 'DEPARTMENT', NULL, NULL);
INSERT INTO `org_info` VALUES (889, 'D000D4D19D1', 'D000D4D19', 'Z0003', '测试', NULL, NULL, NULL, NULL, NULL, NULL, '10356296', 'DEPARTMENT', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for province_city
-- ----------------------------
DROP TABLE IF EXISTS `province_city`;
CREATE TABLE `province_city` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `province` varchar(255) DEFAULT NULL,
  `province_city` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of province_city
-- ----------------------------
BEGIN;
INSERT INTO `province_city` VALUES (1, '上海', '上海');
INSERT INTO `province_city` VALUES (2, '云南', '昆明');
INSERT INTO `province_city` VALUES (3, '内蒙古', '呼和浩特');
INSERT INTO `province_city` VALUES (4, '北京', '北京');
INSERT INTO `province_city` VALUES (5, '吉林', '长春');
INSERT INTO `province_city` VALUES (6, '四川', '成都');
INSERT INTO `province_city` VALUES (7, '天津', '天津');
INSERT INTO `province_city` VALUES (8, '宁夏', '银川');
INSERT INTO `province_city` VALUES (9, '安徽', '合肥');
INSERT INTO `province_city` VALUES (10, '山东', '济南');
INSERT INTO `province_city` VALUES (11, '山西', '太原');
INSERT INTO `province_city` VALUES (12, '广东', '广州');
INSERT INTO `province_city` VALUES (13, '广西', '南宁');
INSERT INTO `province_city` VALUES (14, '新疆', '乌鲁木齐');
INSERT INTO `province_city` VALUES (15, '江苏', '南京');
INSERT INTO `province_city` VALUES (16, '江西', '南昌');
INSERT INTO `province_city` VALUES (17, '河北', '石家庄');
INSERT INTO `province_city` VALUES (18, '河南', '郑州');
INSERT INTO `province_city` VALUES (19, '浙江', '杭州');
INSERT INTO `province_city` VALUES (20, '海南', '海口');
INSERT INTO `province_city` VALUES (21, '湖北', '武汉');
INSERT INTO `province_city` VALUES (22, '湖南', '长沙');
INSERT INTO `province_city` VALUES (23, '甘肃', '兰州');
INSERT INTO `province_city` VALUES (24, '福建', '福州');
INSERT INTO `province_city` VALUES (25, '西藏', '拉萨');
INSERT INTO `province_city` VALUES (26, '贵州', '贵阳');
INSERT INTO `province_city` VALUES (27, '辽宁', '沈阳');
INSERT INTO `province_city` VALUES (28, '重庆', '重庆');
INSERT INTO `province_city` VALUES (29, '陕西', '西安');
INSERT INTO `province_city` VALUES (30, '青海', '西宁');
INSERT INTO `province_city` VALUES (31, '黑龙江', '哈尔滨');
INSERT INTO `province_city` VALUES (32, '台湾', '台北');
INSERT INTO `province_city` VALUES (33, '香港', '香港');
INSERT INTO `province_city` VALUES (34, '澳门', '澳门');
INSERT INTO `province_city` VALUES (35, '龙江', '哈尔滨');
COMMIT;

-- ----------------------------
-- Table structure for province_city_distance
-- ----------------------------
DROP TABLE IF EXISTS `province_city_distance`;
CREATE TABLE `province_city_distance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `to_province_city` varchar(100) DEFAULT NULL,
  `from_province_city` varchar(100) DEFAULT NULL,
  `distance` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1157 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of province_city_distance
-- ----------------------------
BEGIN;
INSERT INTO `province_city_distance` VALUES (1, '北京', '北京', 0);
INSERT INTO `province_city_distance` VALUES (2, '北京', '天津', 125);
INSERT INTO `province_city_distance` VALUES (3, '北京', '上海', 1239);
INSERT INTO `province_city_distance` VALUES (4, '北京', '重庆', 3026);
INSERT INTO `province_city_distance` VALUES (5, '北京', '呼和浩特', 480);
INSERT INTO `province_city_distance` VALUES (6, '北京', '乌鲁木齐', 3300);
INSERT INTO `province_city_distance` VALUES (7, '北京', '拉萨', 3736);
INSERT INTO `province_city_distance` VALUES (8, '北京', '银川', 1192);
INSERT INTO `province_city_distance` VALUES (9, '北京', '南宁', 2373);
INSERT INTO `province_city_distance` VALUES (10, '北京', '哈尔滨', 1230);
INSERT INTO `province_city_distance` VALUES (11, '北京', '长春', 979);
INSERT INTO `province_city_distance` VALUES (12, '北京', '沈阳', 684);
INSERT INTO `province_city_distance` VALUES (13, '北京', '石家庄', 292);
INSERT INTO `province_city_distance` VALUES (14, '北京', '郑州', 695);
INSERT INTO `province_city_distance` VALUES (15, '北京', '济南', 421);
INSERT INTO `province_city_distance` VALUES (16, '北京', '太原', 506);
INSERT INTO `province_city_distance` VALUES (17, '北京', '兰州', 1622);
INSERT INTO `province_city_distance` VALUES (18, '北京', '西安', 1092);
INSERT INTO `province_city_distance` VALUES (19, '北京', '西宁', 1819);
INSERT INTO `province_city_distance` VALUES (20, '北京', '成都', 1881);
INSERT INTO `province_city_distance` VALUES (21, '北京', '武汉', 1171);
INSERT INTO `province_city_distance` VALUES (22, '北京', '合肥', 1037);
INSERT INTO `province_city_distance` VALUES (23, '北京', '南京', 2373);
INSERT INTO `province_city_distance` VALUES (24, '北京', '杭州', 1322);
INSERT INTO `province_city_distance` VALUES (25, '北京', '福州', 1981);
INSERT INTO `province_city_distance` VALUES (26, '北京', '南昌', 1458);
INSERT INTO `province_city_distance` VALUES (27, '北京', '长沙', 1516);
INSERT INTO `province_city_distance` VALUES (28, '北京', '贵阳', 2318);
INSERT INTO `province_city_distance` VALUES (29, '北京', '昆明', 2907);
INSERT INTO `province_city_distance` VALUES (30, '北京', '广州', 2179);
INSERT INTO `province_city_distance` VALUES (31, '北京', '海口', 2775);
INSERT INTO `province_city_distance` VALUES (32, '北京', '香港', 2100);
INSERT INTO `province_city_distance` VALUES (33, '北京', '澳门', 2265);
INSERT INTO `province_city_distance` VALUES (34, '北京', '台北', 1715);
INSERT INTO `province_city_distance` VALUES (35, '天津', '北京', 125);
INSERT INTO `province_city_distance` VALUES (36, '天津', '天津', 0);
INSERT INTO `province_city_distance` VALUES (37, '天津', '上海', 1150);
INSERT INTO `province_city_distance` VALUES (38, '天津', '重庆', 1954);
INSERT INTO `province_city_distance` VALUES (39, '天津', '呼和浩特', 604);
INSERT INTO `province_city_distance` VALUES (40, '天津', '乌鲁木齐', 3330);
INSERT INTO `province_city_distance` VALUES (41, '天津', '拉萨', 3740);
INSERT INTO `province_city_distance` VALUES (42, '天津', '银川', 1316);
INSERT INTO `province_city_distance` VALUES (43, '天津', '南宁', 2389);
INSERT INTO `province_city_distance` VALUES (44, '天津', '哈尔滨', 1207);
INSERT INTO `province_city_distance` VALUES (45, '天津', '长春', 955);
INSERT INTO `province_city_distance` VALUES (46, '天津', '沈阳', 661);
INSERT INTO `province_city_distance` VALUES (47, '天津', '石家庄', 308);
INSERT INTO `province_city_distance` VALUES (48, '天津', '郑州', 711);
INSERT INTO `province_city_distance` VALUES (49, '天津', '济南', 332);
INSERT INTO `province_city_distance` VALUES (50, '天津', '太原', 523);
INSERT INTO `province_city_distance` VALUES (51, '天津', '兰州', 1626);
INSERT INTO `province_city_distance` VALUES (52, '天津', '西安', 1107);
INSERT INTO `province_city_distance` VALUES (53, '天津', '西宁', 1897);
INSERT INTO `province_city_distance` VALUES (54, '天津', '成都', 1897);
INSERT INTO `province_city_distance` VALUES (55, '天津', '武汉', 1187);
INSERT INTO `province_city_distance` VALUES (56, '天津', '合肥', 1217);
INSERT INTO `province_city_distance` VALUES (57, '天津', '南京', 975);
INSERT INTO `province_city_distance` VALUES (58, '天津', '杭州', 1240);
INSERT INTO `province_city_distance` VALUES (59, '天津', '福州', 1891);
INSERT INTO `province_city_distance` VALUES (60, '天津', '南昌', 1406);
INSERT INTO `province_city_distance` VALUES (61, '天津', '长沙', 1576);
INSERT INTO `province_city_distance` VALUES (62, '天津', '贵阳', 2364);
INSERT INTO `province_city_distance` VALUES (63, '天津', '昆明', 2923);
INSERT INTO `province_city_distance` VALUES (64, '天津', '广州', 2197);
INSERT INTO `province_city_distance` VALUES (65, '天津', '海口', 2792);
INSERT INTO `province_city_distance` VALUES (66, '天津', '香港', 2224);
INSERT INTO `province_city_distance` VALUES (67, '天津', '澳门', 2234);
INSERT INTO `province_city_distance` VALUES (68, '天津', '台北', 2130);
INSERT INTO `province_city_distance` VALUES (69, '上海', '北京', 1239);
INSERT INTO `province_city_distance` VALUES (70, '上海', '天津', 1150);
INSERT INTO `province_city_distance` VALUES (71, '上海', '上海', 0);
INSERT INTO `province_city_distance` VALUES (72, '上海', '重庆', 1945);
INSERT INTO `province_city_distance` VALUES (73, '上海', '呼和浩特', 1717);
INSERT INTO `province_city_distance` VALUES (74, '上海', '乌鲁木齐', 3929);
INSERT INTO `province_city_distance` VALUES (75, '上海', '拉萨', 4157);
INSERT INTO `province_city_distance` VALUES (76, '上海', '银川', 2092);
INSERT INTO `province_city_distance` VALUES (77, '上海', '南宁', 1892);
INSERT INTO `province_city_distance` VALUES (78, '上海', '哈尔滨', 2342);
INSERT INTO `province_city_distance` VALUES (79, '上海', '长春', 2090);
INSERT INTO `province_city_distance` VALUES (80, '上海', '沈阳', 1796);
INSERT INTO `province_city_distance` VALUES (81, '上海', '石家庄', 1118);
INSERT INTO `province_city_distance` VALUES (82, '上海', '郑州', 943);
INSERT INTO `province_city_distance` VALUES (83, '上海', '济南', 839);
INSERT INTO `province_city_distance` VALUES (84, '上海', '太原', 1327);
INSERT INTO `province_city_distance` VALUES (85, '上海', '兰州', 2006);
INSERT INTO `province_city_distance` VALUES (86, '上海', '西安', 1381);
INSERT INTO `province_city_distance` VALUES (87, '上海', '西宁', 2240);
INSERT INTO `province_city_distance` VALUES (88, '上海', '成都', 2181);
INSERT INTO `province_city_distance` VALUES (89, '上海', '武汉', 908);
INSERT INTO `province_city_distance` VALUES (90, '上海', '合肥', 467);
INSERT INTO `province_city_distance` VALUES (91, '上海', '南京', 295);
INSERT INTO `province_city_distance` VALUES (92, '上海', '杭州', 178);
INSERT INTO `province_city_distance` VALUES (93, '上海', '福州', 836);
INSERT INTO `province_city_distance` VALUES (94, '上海', '南昌', 775);
INSERT INTO `province_city_distance` VALUES (95, '上海', '长沙', 1049);
INSERT INTO `province_city_distance` VALUES (96, '上海', '贵阳', 1910);
INSERT INTO `province_city_distance` VALUES (97, '上海', '昆明', 2529);
INSERT INTO `province_city_distance` VALUES (98, '上海', '广州', 1539);
INSERT INTO `province_city_distance` VALUES (99, '上海', '海口', 2134);
INSERT INTO `province_city_distance` VALUES (100, '上海', '香港', 1504);
INSERT INTO `province_city_distance` VALUES (101, '上海', '澳门', 1615);
INSERT INTO `province_city_distance` VALUES (102, '上海', '台北', 685);
INSERT INTO `province_city_distance` VALUES (103, '重庆', '北京', 3006);
INSERT INTO `province_city_distance` VALUES (104, '重庆', '天津', 1954);
INSERT INTO `province_city_distance` VALUES (105, '重庆', '上海', 1945);
INSERT INTO `province_city_distance` VALUES (106, '重庆', '重庆', 0);
INSERT INTO `province_city_distance` VALUES (107, '重庆', '呼和浩特', 1847);
INSERT INTO `province_city_distance` VALUES (108, '重庆', '乌鲁木齐', 3202);
INSERT INTO `province_city_distance` VALUES (109, '重庆', '拉萨', 2457);
INSERT INTO `province_city_distance` VALUES (110, '重庆', '银川', 1570);
INSERT INTO `province_city_distance` VALUES (111, '重庆', '南宁', 993);
INSERT INTO `province_city_distance` VALUES (112, '重庆', '哈尔滨', 3156);
INSERT INTO `province_city_distance` VALUES (113, '重庆', '长春', 2905);
INSERT INTO `province_city_distance` VALUES (114, '重庆', '沈阳', 2610);
INSERT INTO `province_city_distance` VALUES (115, '重庆', '石家庄', 1650);
INSERT INTO `province_city_distance` VALUES (116, '重庆', '郑州', 1326);
INSERT INTO `province_city_distance` VALUES (117, '重庆', '济南', 1799);
INSERT INTO `province_city_distance` VALUES (118, '重庆', '太原', 1450);
INSERT INTO `province_city_distance` VALUES (119, '重庆', '兰州', 1275);
INSERT INTO `province_city_distance` VALUES (120, '重庆', '西安', 847);
INSERT INTO `province_city_distance` VALUES (121, '重庆', '西宁', 430);
INSERT INTO `province_city_distance` VALUES (122, '重庆', '成都', 356);
INSERT INTO `province_city_distance` VALUES (123, '重庆', '武汉', 1078);
INSERT INTO `province_city_distance` VALUES (124, '重庆', '合肥', 1449);
INSERT INTO `province_city_distance` VALUES (125, '重庆', '南京', 1651);
INSERT INTO `province_city_distance` VALUES (126, '重庆', '杭州', 1897);
INSERT INTO `province_city_distance` VALUES (127, '重庆', '福州', 1962);
INSERT INTO `province_city_distance` VALUES (128, '重庆', '南昌', 1400);
INSERT INTO `province_city_distance` VALUES (129, '重庆', '长沙', 1500);
INSERT INTO `province_city_distance` VALUES (130, '重庆', '贵阳', 384);
INSERT INTO `province_city_distance` VALUES (131, '重庆', '昆明', 973);
INSERT INTO `province_city_distance` VALUES (132, '重庆', '广州', 1710);
INSERT INTO `province_city_distance` VALUES (133, '重庆', '海口', 1467);
INSERT INTO `province_city_distance` VALUES (134, '重庆', '香港', 1283);
INSERT INTO `province_city_distance` VALUES (135, '重庆', '澳门', 1093);
INSERT INTO `province_city_distance` VALUES (136, '重庆', '台北', 1835);
INSERT INTO `province_city_distance` VALUES (137, '呼和浩特', '北京', 480);
INSERT INTO `province_city_distance` VALUES (138, '呼和浩特', '天津', 604);
INSERT INTO `province_city_distance` VALUES (139, '呼和浩特', '上海', 1717);
INSERT INTO `province_city_distance` VALUES (140, '呼和浩特', '重庆', 1847);
INSERT INTO `province_city_distance` VALUES (141, '呼和浩特', '呼和浩特', 0);
INSERT INTO `province_city_distance` VALUES (142, '呼和浩特', '乌鲁木齐', 2825);
INSERT INTO `province_city_distance` VALUES (143, '呼和浩特', '拉萨', 3260);
INSERT INTO `province_city_distance` VALUES (144, '呼和浩特', '银川', 716);
INSERT INTO `province_city_distance` VALUES (145, '呼和浩特', '南宁', 2657);
INSERT INTO `province_city_distance` VALUES (146, '呼和浩特', '哈尔滨', 1710);
INSERT INTO `province_city_distance` VALUES (147, '呼和浩特', '长春', 1458);
INSERT INTO `province_city_distance` VALUES (148, '呼和浩特', '沈阳', 1164);
INSERT INTO `province_city_distance` VALUES (149, '呼和浩特', '石家庄', 736);
INSERT INTO `province_city_distance` VALUES (150, '呼和浩特', '郑州', 955);
INSERT INTO `province_city_distance` VALUES (151, '呼和浩特', '济南', 902);
INSERT INTO `province_city_distance` VALUES (152, '呼和浩特', '太原', 527);
INSERT INTO `province_city_distance` VALUES (153, '呼和浩特', '兰州', 1146);
INSERT INTO `province_city_distance` VALUES (154, '呼和浩特', '西安', 1000);
INSERT INTO `province_city_distance` VALUES (155, '呼和浩特', '西宁', 1344);
INSERT INTO `province_city_distance` VALUES (156, '呼和浩特', '成都', 1789);
INSERT INTO `province_city_distance` VALUES (157, '呼和浩特', '武汉', 1514);
INSERT INTO `province_city_distance` VALUES (158, '呼和浩特', '合肥', 1518);
INSERT INTO `province_city_distance` VALUES (159, '呼和浩特', '南京', 1545);
INSERT INTO `province_city_distance` VALUES (160, '呼和浩特', '杭州', 1802);
INSERT INTO `province_city_distance` VALUES (161, '呼和浩特', '福州', 2401);
INSERT INTO `province_city_distance` VALUES (162, '呼和浩特', '南昌', 1906);
INSERT INTO `province_city_distance` VALUES (163, '呼和浩特', '长沙', 1798);
INSERT INTO `province_city_distance` VALUES (164, '呼和浩特', '贵阳', 2227);
INSERT INTO `province_city_distance` VALUES (165, '呼和浩特', '昆明', 2816);
INSERT INTO `province_city_distance` VALUES (166, '呼和浩特', '广州', 2461);
INSERT INTO `province_city_distance` VALUES (167, '呼和浩特', '海口', 3057);
INSERT INTO `province_city_distance` VALUES (168, '呼和浩特', '香港', 2283);
INSERT INTO `province_city_distance` VALUES (169, '呼和浩特', '澳门', 2443);
INSERT INTO `province_city_distance` VALUES (170, '呼和浩特', '台北', 1142);
INSERT INTO `province_city_distance` VALUES (171, '乌鲁木齐', '北京', 3300);
INSERT INTO `province_city_distance` VALUES (172, '乌鲁木齐', '天津', 3330);
INSERT INTO `province_city_distance` VALUES (173, '乌鲁木齐', '上海', 3929);
INSERT INTO `province_city_distance` VALUES (174, '乌鲁木齐', '重庆', 3202);
INSERT INTO `province_city_distance` VALUES (175, '乌鲁木齐', '呼和浩特', 2825);
INSERT INTO `province_city_distance` VALUES (176, '乌鲁木齐', '乌鲁木齐', 0);
INSERT INTO `province_city_distance` VALUES (177, '乌鲁木齐', '拉萨', 2668);
INSERT INTO `province_city_distance` VALUES (178, '乌鲁木齐', '银川', 2111);
INSERT INTO `province_city_distance` VALUES (179, '乌鲁木齐', '南宁', 4279);
INSERT INTO `province_city_distance` VALUES (180, '乌鲁木齐', '哈尔滨', 4531);
INSERT INTO `province_city_distance` VALUES (181, '乌鲁木齐', '长春', 4279);
INSERT INTO `province_city_distance` VALUES (182, '乌鲁木齐', '沈阳', 3985);
INSERT INTO `province_city_distance` VALUES (183, '乌鲁木齐', '石家庄', 3026);
INSERT INTO `province_city_distance` VALUES (184, '乌鲁木齐', '郑州', 3027);
INSERT INTO `province_city_distance` VALUES (185, '乌鲁木齐', '济南', 3316);
INSERT INTO `province_city_distance` VALUES (186, '乌鲁木齐', '太原', 2825);
INSERT INTO `province_city_distance` VALUES (187, '乌鲁木齐', '兰州', 1928);
INSERT INTO `province_city_distance` VALUES (188, '乌鲁木齐', '西安', 2541);
INSERT INTO `province_city_distance` VALUES (189, '乌鲁木齐', '西宁', 1762);
INSERT INTO `province_city_distance` VALUES (190, '乌鲁木齐', '成都', 2914);
INSERT INTO `province_city_distance` VALUES (191, '乌鲁木齐', '武汉', 3323);
INSERT INTO `province_city_distance` VALUES (192, '乌鲁木齐', '合肥', 3609);
INSERT INTO `province_city_distance` VALUES (193, '乌鲁木齐', '南京', 3652);
INSERT INTO `province_city_distance` VALUES (194, '乌鲁木齐', '杭州', 3928);
INSERT INTO `province_city_distance` VALUES (195, '乌鲁木齐', '福州', 4255);
INSERT INTO `province_city_distance` VALUES (196, '乌鲁木齐', '南昌', 3647);
INSERT INTO `province_city_distance` VALUES (197, '乌鲁木齐', '长沙', 3640);
INSERT INTO `province_city_distance` VALUES (198, '乌鲁木齐', '贵阳', 3671);
INSERT INTO `province_city_distance` VALUES (199, '乌鲁木齐', '昆明', 3865);
INSERT INTO `province_city_distance` VALUES (200, '乌鲁木齐', '广州', 4316);
INSERT INTO `province_city_distance` VALUES (201, '乌鲁木齐', '海口', 4747);
INSERT INTO `province_city_distance` VALUES (202, '乌鲁木齐', '香港', 4143);
INSERT INTO `province_city_distance` VALUES (203, '乌鲁木齐', '澳门', 4307);
INSERT INTO `province_city_distance` VALUES (204, '乌鲁木齐', '台北', 3432);
INSERT INTO `province_city_distance` VALUES (205, '拉萨', '北京', 3736);
INSERT INTO `province_city_distance` VALUES (206, '拉萨', '天津', 3740);
INSERT INTO `province_city_distance` VALUES (207, '拉萨', '上海', 4157);
INSERT INTO `province_city_distance` VALUES (208, '拉萨', '重庆', 2457);
INSERT INTO `province_city_distance` VALUES (209, '拉萨', '呼和浩特', 3260);
INSERT INTO `province_city_distance` VALUES (210, '拉萨', '乌鲁木齐', 2668);
INSERT INTO `province_city_distance` VALUES (211, '拉萨', '拉萨', 0);
INSERT INTO `province_city_distance` VALUES (212, '拉萨', '银川', 2547);
INSERT INTO `province_city_distance` VALUES (213, '拉萨', '南宁', 3431);
INSERT INTO `province_city_distance` VALUES (214, '拉萨', '哈尔滨', 4967);
INSERT INTO `province_city_distance` VALUES (215, '拉萨', '长春', 4715);
INSERT INTO `province_city_distance` VALUES (216, '拉萨', '沈阳', 4421);
INSERT INTO `province_city_distance` VALUES (217, '拉萨', '石家庄', 3436);
INSERT INTO `province_city_distance` VALUES (218, '拉萨', '郑州', 3254);
INSERT INTO `province_city_distance` VALUES (219, '拉萨', '济南', 3726);
INSERT INTO `province_city_distance` VALUES (220, '拉萨', '太原', 3236);
INSERT INTO `province_city_distance` VALUES (221, '拉萨', '兰州', 2132);
INSERT INTO `province_city_distance` VALUES (222, '拉萨', '西安', 2775);
INSERT INTO `province_city_distance` VALUES (223, '拉萨', '西宁', 1918);
INSERT INTO `province_city_distance` VALUES (224, '拉萨', '成都', 2106);
INSERT INTO `province_city_distance` VALUES (225, '拉萨', '武汉', 3500);
INSERT INTO `province_city_distance` VALUES (226, '拉萨', '合肥', 3836);
INSERT INTO `province_city_distance` VALUES (227, '拉萨', '南京', 3880);
INSERT INTO `province_city_distance` VALUES (228, '拉萨', '杭州', 4155);
INSERT INTO `province_city_distance` VALUES (229, '拉萨', '福州', 4447);
INSERT INTO `province_city_distance` VALUES (230, '拉萨', '南昌', 3885);
INSERT INTO `province_city_distance` VALUES (231, '拉萨', '长沙', 3960);
INSERT INTO `province_city_distance` VALUES (232, '拉萨', '贵阳', 2822);
INSERT INTO `province_city_distance` VALUES (233, '拉萨', '昆明', 2246);
INSERT INTO `province_city_distance` VALUES (234, '拉萨', '广州', 4148);
INSERT INTO `province_city_distance` VALUES (235, '拉萨', '海口', 3899);
INSERT INTO `province_city_distance` VALUES (236, '拉萨', '香港', 3691);
INSERT INTO `province_city_distance` VALUES (237, '拉萨', '澳门', 3666);
INSERT INTO `province_city_distance` VALUES (238, '拉萨', '台北', 4438);
INSERT INTO `province_city_distance` VALUES (239, '银川', '北京', 1192);
INSERT INTO `province_city_distance` VALUES (240, '银川', '天津', 1316);
INSERT INTO `province_city_distance` VALUES (241, '银川', '上海', 2092);
INSERT INTO `province_city_distance` VALUES (242, '银川', '重庆', 1570);
INSERT INTO `province_city_distance` VALUES (243, '银川', '呼和浩特', 716);
INSERT INTO `province_city_distance` VALUES (244, '银川', '乌鲁木齐', 2111);
INSERT INTO `province_city_distance` VALUES (245, '银川', '拉萨', 2547);
INSERT INTO `province_city_distance` VALUES (246, '银川', '银川', 0);
INSERT INTO `province_city_distance` VALUES (247, '银川', '南宁', 2673);
INSERT INTO `province_city_distance` VALUES (248, '银川', '哈尔滨', 2422);
INSERT INTO `province_city_distance` VALUES (249, '银川', '长春', 2170);
INSERT INTO `province_city_distance` VALUES (250, '银川', '沈阳', 1876);
INSERT INTO `province_city_distance` VALUES (251, '银川', '石家庄', 948);
INSERT INTO `province_city_distance` VALUES (252, '银川', '郑州', 1189);
INSERT INTO `province_city_distance` VALUES (253, '银川', '济南', 1238);
INSERT INTO `province_city_distance` VALUES (254, '银川', '太原', 748);
INSERT INTO `province_city_distance` VALUES (255, '银川', '兰州', 433);
INSERT INTO `province_city_distance` VALUES (256, '银川', '西安', 723);
INSERT INTO `province_city_distance` VALUES (257, '银川', '西宁', 631);
INSERT INTO `province_city_distance` VALUES (258, '银川', '成都', 1291);
INSERT INTO `province_city_distance` VALUES (259, '银川', '武汉', 1471);
INSERT INTO `province_city_distance` VALUES (260, '银川', '合肥', 1771);
INSERT INTO `province_city_distance` VALUES (261, '银川', '南京', 1815);
INSERT INTO `province_city_distance` VALUES (262, '银川', '杭州', 2091);
INSERT INTO `province_city_distance` VALUES (263, '银川', '福州', 2418);
INSERT INTO `province_city_distance` VALUES (264, '银川', '南昌', 1921);
INSERT INTO `province_city_distance` VALUES (265, '银川', '长沙', 1814);
INSERT INTO `province_city_distance` VALUES (266, '银川', '贵阳', 1951);
INSERT INTO `province_city_distance` VALUES (267, '银川', '昆明', 2540);
INSERT INTO `province_city_distance` VALUES (268, '银川', '广州', 2479);
INSERT INTO `province_city_distance` VALUES (269, '银川', '海口', 3074);
INSERT INTO `province_city_distance` VALUES (270, '银川', '香港', 2462);
INSERT INTO `province_city_distance` VALUES (271, '银川', '澳门', 2501);
INSERT INTO `province_city_distance` VALUES (272, '银川', '台北', 1453);
INSERT INTO `province_city_distance` VALUES (273, '南宁', '北京', 2373);
INSERT INTO `province_city_distance` VALUES (274, '南宁', '天津', 2389);
INSERT INTO `province_city_distance` VALUES (275, '南宁', '上海', 1892);
INSERT INTO `province_city_distance` VALUES (276, '南宁', '重庆', 993);
INSERT INTO `province_city_distance` VALUES (277, '南宁', '呼和浩特', 2657);
INSERT INTO `province_city_distance` VALUES (278, '南宁', '乌鲁木齐', 4279);
INSERT INTO `province_city_distance` VALUES (279, '南宁', '拉萨', 3431);
INSERT INTO `province_city_distance` VALUES (280, '南宁', '银川', 2673);
INSERT INTO `province_city_distance` VALUES (281, '南宁', '南宁', 0);
INSERT INTO `province_city_distance` VALUES (282, '南宁', '哈尔滨', 3592);
INSERT INTO `province_city_distance` VALUES (283, '南宁', '长春', 3340);
INSERT INTO `province_city_distance` VALUES (284, '南宁', '沈阳', 3046);
INSERT INTO `province_city_distance` VALUES (285, '南宁', '石家庄', 2096);
INSERT INTO `province_city_distance` VALUES (286, '南宁', '郑州', 1708);
INSERT INTO `province_city_distance` VALUES (287, '南宁', '济南', 2122);
INSERT INTO `province_city_distance` VALUES (288, '南宁', '太原', 2138);
INSERT INTO `province_city_distance` VALUES (289, '南宁', '兰州', 2352);
INSERT INTO `province_city_distance` VALUES (290, '南宁', '西安', 3324);
INSERT INTO `province_city_distance` VALUES (291, '南宁', '西宁', 2407);
INSERT INTO `province_city_distance` VALUES (292, '南宁', '成都', 1330);
INSERT INTO `province_city_distance` VALUES (293, '南宁', '武汉', 1215);
INSERT INTO `province_city_distance` VALUES (294, '南宁', '合肥', 1626);
INSERT INTO `province_city_distance` VALUES (295, '南宁', '南京', 1783);
INSERT INTO `province_city_distance` VALUES (296, '南宁', '杭州', 1706);
INSERT INTO `province_city_distance` VALUES (297, '南宁', '福州', 1615);
INSERT INTO `province_city_distance` VALUES (298, '南宁', '南昌', 1185);
INSERT INTO `province_city_distance` VALUES (299, '南宁', '长沙', 851);
INSERT INTO `province_city_distance` VALUES (300, '南宁', '贵阳', 623);
INSERT INTO `province_city_distance` VALUES (301, '南宁', '昆明', 879);
INSERT INTO `province_city_distance` VALUES (302, '南宁', '广州', 734);
INSERT INTO `province_city_distance` VALUES (303, '南宁', '海口', 473);
INSERT INTO `province_city_distance` VALUES (304, '南宁', '香港', 540);
INSERT INTO `province_city_distance` VALUES (305, '南宁', '澳门', 601);
INSERT INTO `province_city_distance` VALUES (306, '南宁', '台北', 1362);
INSERT INTO `province_city_distance` VALUES (307, '哈尔滨', '北京', 1230);
INSERT INTO `province_city_distance` VALUES (308, '哈尔滨', '天津', 1207);
INSERT INTO `province_city_distance` VALUES (309, '哈尔滨', '上海', 2342);
INSERT INTO `province_city_distance` VALUES (310, '哈尔滨', '重庆', 3156);
INSERT INTO `province_city_distance` VALUES (311, '哈尔滨', '呼和浩特', 1710);
INSERT INTO `province_city_distance` VALUES (312, '哈尔滨', '乌鲁木齐', 4531);
INSERT INTO `province_city_distance` VALUES (313, '哈尔滨', '拉萨', 4967);
INSERT INTO `province_city_distance` VALUES (314, '哈尔滨', '银川', 2422);
INSERT INTO `province_city_distance` VALUES (315, '哈尔滨', '南宁', 3592);
INSERT INTO `province_city_distance` VALUES (316, '哈尔滨', '哈尔滨', 0);
INSERT INTO `province_city_distance` VALUES (317, '哈尔滨', '长春', 256);
INSERT INTO `province_city_distance` VALUES (318, '哈尔滨', '沈阳', 546);
INSERT INTO `province_city_distance` VALUES (319, '哈尔滨', '石家庄', 1510);
INSERT INTO `province_city_distance` VALUES (320, '哈尔滨', '郑州', 1914);
INSERT INTO `province_city_distance` VALUES (321, '哈尔滨', '济南', 1524);
INSERT INTO `province_city_distance` VALUES (322, '哈尔滨', '太原', 1725);
INSERT INTO `province_city_distance` VALUES (323, '哈尔滨', '兰州', 2849);
INSERT INTO `province_city_distance` VALUES (324, '哈尔滨', '西安', 2309);
INSERT INTO `province_city_distance` VALUES (325, '哈尔滨', '西宁', 3047);
INSERT INTO `province_city_distance` VALUES (326, '哈尔滨', '成都', 3099);
INSERT INTO `province_city_distance` VALUES (327, '哈尔滨', '武汉', 2389);
INSERT INTO `province_city_distance` VALUES (328, '哈尔滨', '合肥', 2140);
INSERT INTO `province_city_distance` VALUES (329, '哈尔滨', '南京', 2166);
INSERT INTO `province_city_distance` VALUES (330, '哈尔滨', '杭州', 2425);
INSERT INTO `province_city_distance` VALUES (331, '哈尔滨', '福州', 3083);
INSERT INTO `province_city_distance` VALUES (332, '哈尔滨', '南昌', 2591);
INSERT INTO `province_city_distance` VALUES (333, '哈尔滨', '长沙', 2700);
INSERT INTO `province_city_distance` VALUES (334, '哈尔滨', '贵阳', 3537);
INSERT INTO `province_city_distance` VALUES (335, '哈尔滨', '昆明', 4126);
INSERT INTO `province_city_distance` VALUES (336, '哈尔滨', '广州', 3383);
INSERT INTO `province_city_distance` VALUES (337, '哈尔滨', '海口', 3984);
INSERT INTO `province_city_distance` VALUES (338, '哈尔滨', '香港', 3664);
INSERT INTO `province_city_distance` VALUES (339, '哈尔滨', '澳门', 3448);
INSERT INTO `province_city_distance` VALUES (340, '哈尔滨', '台北', 2350);
INSERT INTO `province_city_distance` VALUES (341, '长春', '北京', 979);
INSERT INTO `province_city_distance` VALUES (342, '长春', '天津', 955);
INSERT INTO `province_city_distance` VALUES (343, '长春', '上海', 2090);
INSERT INTO `province_city_distance` VALUES (344, '长春', '重庆', 2905);
INSERT INTO `province_city_distance` VALUES (345, '长春', '呼和浩特', 1458);
INSERT INTO `province_city_distance` VALUES (346, '长春', '乌鲁木齐', 4279);
INSERT INTO `province_city_distance` VALUES (347, '长春', '拉萨', 4715);
INSERT INTO `province_city_distance` VALUES (348, '长春', '银川', 2170);
INSERT INTO `province_city_distance` VALUES (349, '长春', '南宁', 3340);
INSERT INTO `province_city_distance` VALUES (350, '长春', '哈尔滨', 256);
INSERT INTO `province_city_distance` VALUES (351, '长春', '长春', 0);
INSERT INTO `province_city_distance` VALUES (352, '长春', '沈阳', 294);
INSERT INTO `province_city_distance` VALUES (353, '长春', '石家庄', 1259);
INSERT INTO `province_city_distance` VALUES (354, '长春', '郑州', 1662);
INSERT INTO `province_city_distance` VALUES (355, '长春', '济南', 1272);
INSERT INTO `province_city_distance` VALUES (356, '长春', '太原', 1473);
INSERT INTO `province_city_distance` VALUES (357, '长春', '兰州', 2598);
INSERT INTO `province_city_distance` VALUES (358, '长春', '西安', 2057);
INSERT INTO `province_city_distance` VALUES (359, '长春', '西宁', 2795);
INSERT INTO `province_city_distance` VALUES (360, '长春', '成都', 2847);
INSERT INTO `province_city_distance` VALUES (361, '长春', '武汉', 2137);
INSERT INTO `province_city_distance` VALUES (362, '长春', '合肥', 1888);
INSERT INTO `province_city_distance` VALUES (363, '长春', '南京', 1915);
INSERT INTO `province_city_distance` VALUES (364, '长春', '杭州', 2173);
INSERT INTO `province_city_distance` VALUES (365, '长春', '福州', 2831);
INSERT INTO `province_city_distance` VALUES (366, '长春', '南昌', 2341);
INSERT INTO `province_city_distance` VALUES (367, '长春', '长沙', 2433);
INSERT INTO `province_city_distance` VALUES (368, '长春', '贵阳', 3285);
INSERT INTO `province_city_distance` VALUES (369, '长春', '昆明', 3874);
INSERT INTO `province_city_distance` VALUES (370, '长春', '广州', 3133);
INSERT INTO `province_city_distance` VALUES (371, '长春', '海口', 3732);
INSERT INTO `province_city_distance` VALUES (372, '长春', '香港', 3112);
INSERT INTO `province_city_distance` VALUES (373, '长春', '澳门', 2088);
INSERT INTO `province_city_distance` VALUES (374, '长春', '台北', 2404);
INSERT INTO `province_city_distance` VALUES (375, '沈阳', '北京', 684);
INSERT INTO `province_city_distance` VALUES (376, '沈阳', '天津', 661);
INSERT INTO `province_city_distance` VALUES (377, '沈阳', '上海', 1796);
INSERT INTO `province_city_distance` VALUES (378, '沈阳', '重庆', 2610);
INSERT INTO `province_city_distance` VALUES (379, '沈阳', '呼和浩特', 1164);
INSERT INTO `province_city_distance` VALUES (380, '沈阳', '乌鲁木齐', 3985);
INSERT INTO `province_city_distance` VALUES (381, '沈阳', '拉萨', 4421);
INSERT INTO `province_city_distance` VALUES (382, '沈阳', '银川', 1876);
INSERT INTO `province_city_distance` VALUES (383, '沈阳', '南宁', 3046);
INSERT INTO `province_city_distance` VALUES (384, '沈阳', '哈尔滨', 546);
INSERT INTO `province_city_distance` VALUES (385, '沈阳', '长春', 294);
INSERT INTO `province_city_distance` VALUES (386, '沈阳', '沈阳', 0);
INSERT INTO `province_city_distance` VALUES (387, '沈阳', '石家庄', 964);
INSERT INTO `province_city_distance` VALUES (388, '沈阳', '郑州', 1368);
INSERT INTO `province_city_distance` VALUES (389, '沈阳', '济南', 978);
INSERT INTO `province_city_distance` VALUES (390, '沈阳', '太原', 1179);
INSERT INTO `province_city_distance` VALUES (391, '沈阳', '兰州', 2303);
INSERT INTO `province_city_distance` VALUES (392, '沈阳', '西安', 1763);
INSERT INTO `province_city_distance` VALUES (393, '沈阳', '西宁', 2489);
INSERT INTO `province_city_distance` VALUES (394, '沈阳', '成都', 2547);
INSERT INTO `province_city_distance` VALUES (395, '沈阳', '武汉', 1827);
INSERT INTO `province_city_distance` VALUES (396, '沈阳', '合肥', 1587);
INSERT INTO `province_city_distance` VALUES (397, '沈阳', '南京', 1610);
INSERT INTO `province_city_distance` VALUES (398, '沈阳', '杭州', 1800);
INSERT INTO `province_city_distance` VALUES (399, '沈阳', '福州', 2533);
INSERT INTO `province_city_distance` VALUES (400, '沈阳', '南昌', 2045);
INSERT INTO `province_city_distance` VALUES (401, '沈阳', '长沙', 2132);
INSERT INTO `province_city_distance` VALUES (402, '沈阳', '贵阳', 1987);
INSERT INTO `province_city_distance` VALUES (403, '沈阳', '昆明', 3572);
INSERT INTO `province_city_distance` VALUES (404, '沈阳', '广州', 2832);
INSERT INTO `province_city_distance` VALUES (405, '沈阳', '海口', 3435);
INSERT INTO `province_city_distance` VALUES (406, '沈阳', '香港', 2850);
INSERT INTO `province_city_distance` VALUES (407, '沈阳', '澳门', 2906);
INSERT INTO `province_city_distance` VALUES (408, '沈阳', '台北', 1182);
INSERT INTO `province_city_distance` VALUES (409, '石家庄', '北京', 292);
INSERT INTO `province_city_distance` VALUES (410, '石家庄', '天津', 308);
INSERT INTO `province_city_distance` VALUES (411, '石家庄', '上海', 1118);
INSERT INTO `province_city_distance` VALUES (412, '石家庄', '重庆', 1650);
INSERT INTO `province_city_distance` VALUES (413, '石家庄', '呼和浩特', 736);
INSERT INTO `province_city_distance` VALUES (414, '石家庄', '乌鲁木齐', 3206);
INSERT INTO `province_city_distance` VALUES (415, '石家庄', '拉萨', 3436);
INSERT INTO `province_city_distance` VALUES (416, '石家庄', '银川', 948);
INSERT INTO `province_city_distance` VALUES (417, '石家庄', '南宁', 2096);
INSERT INTO `province_city_distance` VALUES (418, '石家庄', '哈尔滨', 1510);
INSERT INTO `province_city_distance` VALUES (419, '石家庄', '长春', 1259);
INSERT INTO `province_city_distance` VALUES (420, '石家庄', '沈阳', 964);
INSERT INTO `province_city_distance` VALUES (421, '石家庄', '石家庄', 0);
INSERT INTO `province_city_distance` VALUES (422, '石家庄', '郑州', 418);
INSERT INTO `province_city_distance` VALUES (423, '石家庄', '济南', 300);
INSERT INTO `province_city_distance` VALUES (424, '石家庄', '太原', 218);
INSERT INTO `province_city_distance` VALUES (425, '石家庄', '兰州', 1321);
INSERT INTO `province_city_distance` VALUES (426, '石家庄', '西安', 802);
INSERT INTO `province_city_distance` VALUES (427, '石家庄', '西宁', 1519);
INSERT INTO `province_city_distance` VALUES (428, '石家庄', '成都', 1593);
INSERT INTO `province_city_distance` VALUES (429, '石家庄', '武汉', 955);
INSERT INTO `province_city_distance` VALUES (430, '石家庄', '合肥', 915);
INSERT INTO `province_city_distance` VALUES (431, '石家庄', '南京', 942);
INSERT INTO `province_city_distance` VALUES (432, '石家庄', '杭州', 1200);
INSERT INTO `province_city_distance` VALUES (433, '石家庄', '福州', 1859);
INSERT INTO `province_city_distance` VALUES (434, '石家庄', '南昌', 1278);
INSERT INTO `province_city_distance` VALUES (435, '石家庄', '长沙', 1240);
INSERT INTO `province_city_distance` VALUES (436, '石家庄', '贵阳', 2030);
INSERT INTO `province_city_distance` VALUES (437, '石家庄', '昆明', 26119);
INSERT INTO `province_city_distance` VALUES (438, '石家庄', '广州', 1902);
INSERT INTO `province_city_distance` VALUES (439, '石家庄', '海口', 2496);
INSERT INTO `province_city_distance` VALUES (440, '石家庄', '香港', 2009);
INSERT INTO `province_city_distance` VALUES (441, '石家庄', '澳门', 1984);
INSERT INTO `province_city_distance` VALUES (442, '石家庄', '台北', 669);
INSERT INTO `province_city_distance` VALUES (443, '郑州', '北京', 695);
INSERT INTO `province_city_distance` VALUES (444, '郑州', '天津', 711);
INSERT INTO `province_city_distance` VALUES (445, '郑州', '上海', 943);
INSERT INTO `province_city_distance` VALUES (446, '郑州', '重庆', 1326);
INSERT INTO `province_city_distance` VALUES (447, '郑州', '呼和浩特', 955);
INSERT INTO `province_city_distance` VALUES (448, '郑州', '乌鲁木齐', 3027);
INSERT INTO `province_city_distance` VALUES (449, '郑州', '拉萨', 3254);
INSERT INTO `province_city_distance` VALUES (450, '郑州', '银川', 1189);
INSERT INTO `province_city_distance` VALUES (451, '郑州', '南宁', 1708);
INSERT INTO `province_city_distance` VALUES (452, '郑州', '哈尔滨', 1914);
INSERT INTO `province_city_distance` VALUES (453, '郑州', '长春', 1662);
INSERT INTO `province_city_distance` VALUES (454, '郑州', '沈阳', 1368);
INSERT INTO `province_city_distance` VALUES (455, '郑州', '石家庄', 418);
INSERT INTO `province_city_distance` VALUES (456, '郑州', '郑州', 0);
INSERT INTO `province_city_distance` VALUES (457, '郑州', '济南', 495);
INSERT INTO `province_city_distance` VALUES (458, '郑州', '太原', 437);
INSERT INTO `province_city_distance` VALUES (459, '郑州', '兰州', 1103);
INSERT INTO `province_city_distance` VALUES (460, '郑州', '西安', 478);
INSERT INTO `province_city_distance` VALUES (461, '郑州', '西宁', 1338);
INSERT INTO `province_city_distance` VALUES (462, '郑州', '成都', 1278);
INSERT INTO `province_city_distance` VALUES (463, '郑州', '武汉', 569);
INSERT INTO `province_city_distance` VALUES (464, '郑州', '合肥', 622);
INSERT INTO `province_city_distance` VALUES (465, '郑州', '南京', 666);
INSERT INTO `province_city_distance` VALUES (466, '郑州', '杭州', 941);
INSERT INTO `province_city_distance` VALUES (467, '郑州', '福州', 1452);
INSERT INTO `province_city_distance` VALUES (468, '郑州', '南昌', 961);
INSERT INTO `province_city_distance` VALUES (469, '郑州', '长沙', 853);
INSERT INTO `province_city_distance` VALUES (470, '郑州', '贵阳', 1544);
INSERT INTO `province_city_distance` VALUES (471, '郑州', '昆明', 2100);
INSERT INTO `province_city_distance` VALUES (472, '郑州', '广州', 1516);
INSERT INTO `province_city_distance` VALUES (473, '郑州', '海口', 2108);
INSERT INTO `province_city_distance` VALUES (474, '郑州', '香港', 1598);
INSERT INTO `province_city_distance` VALUES (475, '郑州', '澳门', 1583);
INSERT INTO `province_city_distance` VALUES (476, '郑州', '台北', 727);
INSERT INTO `province_city_distance` VALUES (477, '济南', '北京', 421);
INSERT INTO `province_city_distance` VALUES (478, '济南', '天津', 332);
INSERT INTO `province_city_distance` VALUES (479, '济南', '上海', 839);
INSERT INTO `province_city_distance` VALUES (480, '济南', '重庆', 1799);
INSERT INTO `province_city_distance` VALUES (481, '济南', '呼和浩特', 902);
INSERT INTO `province_city_distance` VALUES (482, '济南', '乌鲁木齐', 3316);
INSERT INTO `province_city_distance` VALUES (483, '济南', '拉萨', 3726);
INSERT INTO `province_city_distance` VALUES (484, '济南', '银川', 1238);
INSERT INTO `province_city_distance` VALUES (485, '济南', '南宁', 2122);
INSERT INTO `province_city_distance` VALUES (486, '济南', '哈尔滨', 1524);
INSERT INTO `province_city_distance` VALUES (487, '济南', '长春', 1272);
INSERT INTO `province_city_distance` VALUES (488, '济南', '沈阳', 978);
INSERT INTO `province_city_distance` VALUES (489, '济南', '石家庄', 300);
INSERT INTO `province_city_distance` VALUES (490, '济南', '郑州', 495);
INSERT INTO `province_city_distance` VALUES (491, '济南', '济南', 0);
INSERT INTO `province_city_distance` VALUES (492, '济南', '太原', 509);
INSERT INTO `province_city_distance` VALUES (493, '济南', '兰州', 1612);
INSERT INTO `province_city_distance` VALUES (494, '济南', '西安', 951);
INSERT INTO `province_city_distance` VALUES (495, '济南', '西宁', 1810);
INSERT INTO `province_city_distance` VALUES (496, '济南', '成都', 1751);
INSERT INTO `province_city_distance` VALUES (497, '济南', '武汉', 919);
INSERT INTO `province_city_distance` VALUES (498, '济南', '合肥', 660);
INSERT INTO `province_city_distance` VALUES (499, '济南', '南京', 663);
INSERT INTO `province_city_distance` VALUES (500, '济南', '杭州', 929);
INSERT INTO `province_city_distance` VALUES (501, '济南', '福州', 1580);
INSERT INTO `province_city_distance` VALUES (502, '济南', '南昌', 1095);
INSERT INTO `province_city_distance` VALUES (503, '济南', '长沙', 1209);
INSERT INTO `province_city_distance` VALUES (504, '济南', '贵阳', 1934);
INSERT INTO `province_city_distance` VALUES (505, '济南', '昆明', 2514);
INSERT INTO `province_city_distance` VALUES (506, '济南', '广州', 1887);
INSERT INTO `province_city_distance` VALUES (507, '济南', '海口', 2480);
INSERT INTO `province_city_distance` VALUES (508, '济南', '香港', 1917);
INSERT INTO `province_city_distance` VALUES (509, '济南', '澳门', 1986);
INSERT INTO `province_city_distance` VALUES (510, '济南', '台北', 1360);
INSERT INTO `province_city_distance` VALUES (511, '太原', '北京', 506);
INSERT INTO `province_city_distance` VALUES (512, '太原', '天津', 523);
INSERT INTO `province_city_distance` VALUES (513, '太原', '上海', 1327);
INSERT INTO `province_city_distance` VALUES (514, '太原', '重庆', 1450);
INSERT INTO `province_city_distance` VALUES (515, '太原', '呼和浩特', 527);
INSERT INTO `province_city_distance` VALUES (516, '太原', '乌鲁木齐', 2825);
INSERT INTO `province_city_distance` VALUES (517, '太原', '拉萨', 3236);
INSERT INTO `province_city_distance` VALUES (518, '太原', '银川', 748);
INSERT INTO `province_city_distance` VALUES (519, '太原', '南宁', 2138);
INSERT INTO `province_city_distance` VALUES (520, '太原', '哈尔滨', 1725);
INSERT INTO `province_city_distance` VALUES (521, '太原', '长春', 1473);
INSERT INTO `province_city_distance` VALUES (522, '太原', '沈阳', 1179);
INSERT INTO `province_city_distance` VALUES (523, '太原', '石家庄', 218);
INSERT INTO `province_city_distance` VALUES (524, '太原', '郑州', 437);
INSERT INTO `province_city_distance` VALUES (525, '太原', '济南', 509);
INSERT INTO `province_city_distance` VALUES (526, '太原', '太原', 0);
INSERT INTO `province_city_distance` VALUES (527, '太原', '兰州', 1121);
INSERT INTO `province_city_distance` VALUES (528, '太原', '西安', 602);
INSERT INTO `province_city_distance` VALUES (529, '太原', '西宁', 1319);
INSERT INTO `province_city_distance` VALUES (530, '太原', '成都', 1392);
INSERT INTO `province_city_distance` VALUES (531, '太原', '武汉', 997);
INSERT INTO `province_city_distance` VALUES (532, '太原', '合肥', 1051);
INSERT INTO `province_city_distance` VALUES (533, '太原', '南京', 1095);
INSERT INTO `province_city_distance` VALUES (534, '太原', '杭州', 1370);
INSERT INTO `province_city_distance` VALUES (535, '太原', '福州', 1882);
INSERT INTO `province_city_distance` VALUES (536, '太原', '南昌', 1389);
INSERT INTO `province_city_distance` VALUES (537, '太原', '长沙', 1281);
INSERT INTO `province_city_distance` VALUES (538, '太原', '贵阳', 1862);
INSERT INTO `province_city_distance` VALUES (539, '太原', '昆明', 2419);
INSERT INTO `province_city_distance` VALUES (540, '太原', '广州', 1944);
INSERT INTO `province_city_distance` VALUES (541, '太原', '海口', 2538);
INSERT INTO `province_city_distance` VALUES (542, '太原', '香港', 2045);
INSERT INTO `province_city_distance` VALUES (543, '太原', '澳门', 2019);
INSERT INTO `province_city_distance` VALUES (544, '太原', '台北', 887);
INSERT INTO `province_city_distance` VALUES (545, '兰州', '北京', 1622);
INSERT INTO `province_city_distance` VALUES (546, '兰州', '天津', 1626);
INSERT INTO `province_city_distance` VALUES (547, '兰州', '上海', 2006);
INSERT INTO `province_city_distance` VALUES (548, '兰州', '重庆', 1275);
INSERT INTO `province_city_distance` VALUES (549, '兰州', '呼和浩特', 1146);
INSERT INTO `province_city_distance` VALUES (550, '兰州', '乌鲁木齐', 1928);
INSERT INTO `province_city_distance` VALUES (551, '兰州', '拉萨', 2132);
INSERT INTO `province_city_distance` VALUES (552, '兰州', '银川', 433);
INSERT INTO `province_city_distance` VALUES (553, '兰州', '南宁', 2352);
INSERT INTO `province_city_distance` VALUES (554, '兰州', '哈尔滨', 2849);
INSERT INTO `province_city_distance` VALUES (555, '兰州', '长春', 2598);
INSERT INTO `province_city_distance` VALUES (556, '兰州', '沈阳', 2303);
INSERT INTO `province_city_distance` VALUES (557, '兰州', '石家庄', 1321);
INSERT INTO `province_city_distance` VALUES (558, '兰州', '郑州', 1103);
INSERT INTO `province_city_distance` VALUES (559, '兰州', '济南', 1612);
INSERT INTO `province_city_distance` VALUES (560, '兰州', '太原', 1121);
INSERT INTO `province_city_distance` VALUES (561, '兰州', '兰州', 0);
INSERT INTO `province_city_distance` VALUES (562, '兰州', '西安', 624);
INSERT INTO `province_city_distance` VALUES (563, '兰州', '西宁', 216);
INSERT INTO `province_city_distance` VALUES (564, '兰州', '成都', 1058);
INSERT INTO `province_city_distance` VALUES (565, '兰州', '武汉', 1446);
INSERT INTO `province_city_distance` VALUES (566, '兰州', '合肥', 1685);
INSERT INTO `province_city_distance` VALUES (567, '兰州', '南京', 1729);
INSERT INTO `province_city_distance` VALUES (568, '兰州', '杭州', 2005);
INSERT INTO `province_city_distance` VALUES (569, '兰州', '福州', 2332);
INSERT INTO `province_city_distance` VALUES (570, '兰州', '南昌', 1835);
INSERT INTO `province_city_distance` VALUES (571, '兰州', '长沙', 1727);
INSERT INTO `province_city_distance` VALUES (572, '兰州', '贵阳', 1754);
INSERT INTO `province_city_distance` VALUES (573, '兰州', '昆明', 1938);
INSERT INTO `province_city_distance` VALUES (574, '兰州', '广州', 2393);
INSERT INTO `province_city_distance` VALUES (575, '兰州', '海口', 2820);
INSERT INTO `province_city_distance` VALUES (576, '兰州', '香港', 2425);
INSERT INTO `province_city_distance` VALUES (577, '兰州', '澳门', 2445);
INSERT INTO `province_city_distance` VALUES (578, '兰州', '台北', 2096);
INSERT INTO `province_city_distance` VALUES (579, '西安', '北京', 1092);
INSERT INTO `province_city_distance` VALUES (580, '西安', '天津', 1107);
INSERT INTO `province_city_distance` VALUES (581, '西安', '上海', 1381);
INSERT INTO `province_city_distance` VALUES (582, '西安', '重庆', 847);
INSERT INTO `province_city_distance` VALUES (583, '西安', '呼和浩特', 1000);
INSERT INTO `province_city_distance` VALUES (584, '西安', '乌鲁木齐', 2541);
INSERT INTO `province_city_distance` VALUES (585, '西安', '拉萨', 2775);
INSERT INTO `province_city_distance` VALUES (586, '西安', '银川', 723);
INSERT INTO `province_city_distance` VALUES (587, '西安', '南宁', 3324);
INSERT INTO `province_city_distance` VALUES (588, '西安', '哈尔滨', 2309);
INSERT INTO `province_city_distance` VALUES (589, '西安', '长春', 2057);
INSERT INTO `province_city_distance` VALUES (590, '西安', '沈阳', 1763);
INSERT INTO `province_city_distance` VALUES (591, '西安', '石家庄', 802);
INSERT INTO `province_city_distance` VALUES (592, '西安', '郑州', 478);
INSERT INTO `province_city_distance` VALUES (593, '西安', '济南', 951);
INSERT INTO `province_city_distance` VALUES (594, '西安', '太原', 602);
INSERT INTO `province_city_distance` VALUES (595, '西安', '兰州', 624);
INSERT INTO `province_city_distance` VALUES (596, '西安', '西安', 0);
INSERT INTO `province_city_distance` VALUES (597, '西安', '西宁', 858);
INSERT INTO `province_city_distance` VALUES (598, '西安', '成都', 798);
INSERT INTO `province_city_distance` VALUES (599, '西安', '武汉', 813);
INSERT INTO `province_city_distance` VALUES (600, '西安', '合肥', 1104);
INSERT INTO `province_city_distance` VALUES (601, '西安', '南京', 1174);
INSERT INTO `province_city_distance` VALUES (602, '西安', '杭州', 1379);
INSERT INTO `province_city_distance` VALUES (603, '西安', '福州', 1699);
INSERT INTO `province_city_distance` VALUES (604, '西安', '南昌', 1205);
INSERT INTO `province_city_distance` VALUES (605, '西安', '长沙', 1097);
INSERT INTO `province_city_distance` VALUES (606, '西安', '贵阳', 2885);
INSERT INTO `province_city_distance` VALUES (607, '西安', '昆明', 3475);
INSERT INTO `province_city_distance` VALUES (608, '西安', '广州', 1760);
INSERT INTO `province_city_distance` VALUES (609, '西安', '海口', 2355);
INSERT INTO `province_city_distance` VALUES (610, '西安', '香港', 1420);
INSERT INTO `province_city_distance` VALUES (611, '西安', '澳门', 1780);
INSERT INTO `province_city_distance` VALUES (612, '西安', '台北', 1581);
INSERT INTO `province_city_distance` VALUES (613, '西宁', '北京', 1819);
INSERT INTO `province_city_distance` VALUES (614, '西宁', '天津', 1897);
INSERT INTO `province_city_distance` VALUES (615, '西宁', '上海', 2240);
INSERT INTO `province_city_distance` VALUES (616, '西宁', '重庆', 430);
INSERT INTO `province_city_distance` VALUES (617, '西宁', '呼和浩特', 1344);
INSERT INTO `province_city_distance` VALUES (618, '西宁', '乌鲁木齐', 1762);
INSERT INTO `province_city_distance` VALUES (619, '西宁', '拉萨', 1918);
INSERT INTO `province_city_distance` VALUES (620, '西宁', '银川', 631);
INSERT INTO `province_city_distance` VALUES (621, '西宁', '南宁', 2407);
INSERT INTO `province_city_distance` VALUES (622, '西宁', '哈尔滨', 3047);
INSERT INTO `province_city_distance` VALUES (623, '西宁', '长春', 2795);
INSERT INTO `province_city_distance` VALUES (624, '西宁', '沈阳', 2489);
INSERT INTO `province_city_distance` VALUES (625, '西宁', '石家庄', 1519);
INSERT INTO `province_city_distance` VALUES (626, '西宁', '郑州', 1338);
INSERT INTO `province_city_distance` VALUES (627, '西宁', '济南', 1810);
INSERT INTO `province_city_distance` VALUES (628, '西宁', '太原', 1319);
INSERT INTO `province_city_distance` VALUES (629, '西宁', '兰州', 216);
INSERT INTO `province_city_distance` VALUES (630, '西宁', '西安', 858);
INSERT INTO `province_city_distance` VALUES (631, '西宁', '西宁', 0);
INSERT INTO `province_city_distance` VALUES (632, '西宁', '成都', 1076);
INSERT INTO `province_city_distance` VALUES (633, '西宁', '武汉', 1679);
INSERT INTO `province_city_distance` VALUES (634, '西宁', '合肥', 1920);
INSERT INTO `province_city_distance` VALUES (635, '西宁', '南京', 2029);
INSERT INTO `province_city_distance` VALUES (636, '西宁', '杭州', 2239);
INSERT INTO `province_city_distance` VALUES (637, '西宁', '福州', 2566);
INSERT INTO `province_city_distance` VALUES (638, '西宁', '南昌', 2068);
INSERT INTO `province_city_distance` VALUES (639, '西宁', '长沙', 1960);
INSERT INTO `province_city_distance` VALUES (640, '西宁', '贵阳', 1809);
INSERT INTO `province_city_distance` VALUES (641, '西宁', '昆明', 1977);
INSERT INTO `province_city_distance` VALUES (642, '西宁', '广州', 2627);
INSERT INTO `province_city_distance` VALUES (643, '西宁', '海口', 2875);
INSERT INTO `province_city_distance` VALUES (644, '西宁', '香港', 2651);
INSERT INTO `province_city_distance` VALUES (645, '西宁', '澳门', 2644);
INSERT INTO `province_city_distance` VALUES (646, '西宁', '台北', 2014);
INSERT INTO `province_city_distance` VALUES (647, '成都', '北京', 1881);
INSERT INTO `province_city_distance` VALUES (648, '成都', '天津', 1897);
INSERT INTO `province_city_distance` VALUES (649, '成都', '上海', 2181);
INSERT INTO `province_city_distance` VALUES (650, '成都', '重庆', 356);
INSERT INTO `province_city_distance` VALUES (651, '成都', '呼和浩特', 1789);
INSERT INTO `province_city_distance` VALUES (652, '成都', '乌鲁木齐', 2914);
INSERT INTO `province_city_distance` VALUES (653, '成都', '拉萨', 2106);
INSERT INTO `province_city_distance` VALUES (654, '成都', '银川', 1291);
INSERT INTO `province_city_distance` VALUES (655, '成都', '南宁', 1330);
INSERT INTO `province_city_distance` VALUES (656, '成都', '哈尔滨', 3099);
INSERT INTO `province_city_distance` VALUES (657, '成都', '长春', 2847);
INSERT INTO `province_city_distance` VALUES (658, '成都', '沈阳', 2547);
INSERT INTO `province_city_distance` VALUES (659, '成都', '石家庄', 1593);
INSERT INTO `province_city_distance` VALUES (660, '成都', '郑州', 1278);
INSERT INTO `province_city_distance` VALUES (661, '成都', '济南', 1751);
INSERT INTO `province_city_distance` VALUES (662, '成都', '太原', 1392);
INSERT INTO `province_city_distance` VALUES (663, '成都', '兰州', 1058);
INSERT INTO `province_city_distance` VALUES (664, '成都', '西安', 798);
INSERT INTO `province_city_distance` VALUES (665, '成都', '西宁', 1076);
INSERT INTO `province_city_distance` VALUES (666, '成都', '成都', 0);
INSERT INTO `province_city_distance` VALUES (667, '成都', '武汉', 1455);
INSERT INTO `province_city_distance` VALUES (668, '成都', '合肥', 1860);
INSERT INTO `province_city_distance` VALUES (669, '成都', '南京', 1970);
INSERT INTO `province_city_distance` VALUES (670, '成都', '杭州', 2179);
INSERT INTO `province_city_distance` VALUES (671, '成都', '福州', 2341);
INSERT INTO `province_city_distance` VALUES (672, '成都', '南昌', 1844);
INSERT INTO `province_city_distance` VALUES (673, '成都', '长沙', 1801);
INSERT INTO `province_city_distance` VALUES (674, '成都', '贵阳', 736);
INSERT INTO `province_city_distance` VALUES (675, '成都', '昆明', 917);
INSERT INTO `province_city_distance` VALUES (676, '成都', '广州', 2047);
INSERT INTO `province_city_distance` VALUES (677, '成都', '海口', 1798);
INSERT INTO `province_city_distance` VALUES (678, '成都', '香港', 1771);
INSERT INTO `province_city_distance` VALUES (679, '成都', '澳门', 1714);
INSERT INTO `province_city_distance` VALUES (680, '成都', '台北', 1892);
INSERT INTO `province_city_distance` VALUES (681, '武汉', '北京', 1171);
INSERT INTO `province_city_distance` VALUES (682, '武汉', '天津', 1187);
INSERT INTO `province_city_distance` VALUES (683, '武汉', '上海', 908);
INSERT INTO `province_city_distance` VALUES (684, '武汉', '重庆', 1078);
INSERT INTO `province_city_distance` VALUES (685, '武汉', '呼和浩特', 1514);
INSERT INTO `province_city_distance` VALUES (686, '武汉', '乌鲁木齐', 3323);
INSERT INTO `province_city_distance` VALUES (687, '武汉', '拉萨', 3500);
INSERT INTO `province_city_distance` VALUES (688, '武汉', '银川', 1471);
INSERT INTO `province_city_distance` VALUES (689, '武汉', '南宁', 1215);
INSERT INTO `province_city_distance` VALUES (690, '武汉', '哈尔滨', 2389);
INSERT INTO `province_city_distance` VALUES (691, '武汉', '长春', 2137);
INSERT INTO `province_city_distance` VALUES (692, '武汉', '沈阳', 1827);
INSERT INTO `province_city_distance` VALUES (693, '武汉', '石家庄', 955);
INSERT INTO `province_city_distance` VALUES (694, '武汉', '郑州', 569);
INSERT INTO `province_city_distance` VALUES (695, '武汉', '济南', 919);
INSERT INTO `province_city_distance` VALUES (696, '武汉', '太原', 997);
INSERT INTO `province_city_distance` VALUES (697, '武汉', '兰州', 1446);
INSERT INTO `province_city_distance` VALUES (698, '武汉', '西安', 813);
INSERT INTO `province_city_distance` VALUES (699, '武汉', '西宁', 1679);
INSERT INTO `province_city_distance` VALUES (700, '武汉', '成都', 1455);
INSERT INTO `province_city_distance` VALUES (701, '武汉', '武汉', 0);
INSERT INTO `province_city_distance` VALUES (702, '武汉', '合肥', 457);
INSERT INTO `province_city_distance` VALUES (703, '武汉', '南京', 614);
INSERT INTO `province_city_distance` VALUES (704, '武汉', '杭州', 860);
INSERT INTO `province_city_distance` VALUES (705, '武汉', '福州', 924);
INSERT INTO `province_city_distance` VALUES (706, '武汉', '南昌', 434);
INSERT INTO `province_city_distance` VALUES (707, '武汉', '长沙', 361);
INSERT INTO `province_city_distance` VALUES (708, '武汉', '贵阳', 1278);
INSERT INTO `province_city_distance` VALUES (709, '武汉', '昆明', 1834);
INSERT INTO `province_city_distance` VALUES (710, '武汉', '广州', 1021);
INSERT INTO `province_city_distance` VALUES (711, '武汉', '海口', 1615);
INSERT INTO `province_city_distance` VALUES (712, '武汉', '香港', 1115);
INSERT INTO `province_city_distance` VALUES (713, '武汉', '澳门', 1124);
INSERT INTO `province_city_distance` VALUES (714, '武汉', '台北', 1075);
INSERT INTO `province_city_distance` VALUES (715, '合肥', '北京', 1037);
INSERT INTO `province_city_distance` VALUES (716, '合肥', '天津', 1217);
INSERT INTO `province_city_distance` VALUES (717, '合肥', '上海', 467);
INSERT INTO `province_city_distance` VALUES (718, '合肥', '重庆', 1449);
INSERT INTO `province_city_distance` VALUES (719, '合肥', '呼和浩特', 1518);
INSERT INTO `province_city_distance` VALUES (720, '合肥', '乌鲁木齐', 3609);
INSERT INTO `province_city_distance` VALUES (721, '合肥', '拉萨', 3836);
INSERT INTO `province_city_distance` VALUES (722, '合肥', '银川', 1771);
INSERT INTO `province_city_distance` VALUES (723, '合肥', '南宁', 1626);
INSERT INTO `province_city_distance` VALUES (724, '合肥', '哈尔滨', 2140);
INSERT INTO `province_city_distance` VALUES (725, '合肥', '长春', 1888);
INSERT INTO `province_city_distance` VALUES (726, '合肥', '沈阳', 1587);
INSERT INTO `province_city_distance` VALUES (727, '合肥', '石家庄', 915);
INSERT INTO `province_city_distance` VALUES (728, '合肥', '郑州', 622);
INSERT INTO `province_city_distance` VALUES (729, '合肥', '济南', 660);
INSERT INTO `province_city_distance` VALUES (730, '合肥', '太原', 1051);
INSERT INTO `province_city_distance` VALUES (731, '合肥', '兰州', 1685);
INSERT INTO `province_city_distance` VALUES (732, '合肥', '西安', 1104);
INSERT INTO `province_city_distance` VALUES (733, '合肥', '西宁', 1920);
INSERT INTO `province_city_distance` VALUES (734, '合肥', '成都', 1860);
INSERT INTO `province_city_distance` VALUES (735, '合肥', '武汉', 457);
INSERT INTO `province_city_distance` VALUES (736, '合肥', '合肥', 0);
INSERT INTO `province_city_distance` VALUES (737, '合肥', '南京', 173);
INSERT INTO `province_city_distance` VALUES (738, '合肥', '杭州', 419);
INSERT INTO `province_city_distance` VALUES (739, '合肥', '福州', 999);
INSERT INTO `province_city_distance` VALUES (740, '合肥', '南昌', 509);
INSERT INTO `province_city_distance` VALUES (741, '合肥', '长沙', 771);
INSERT INTO `province_city_distance` VALUES (742, '合肥', '贵阳', 1688);
INSERT INTO `province_city_distance` VALUES (743, '合肥', '昆明', 2245);
INSERT INTO `province_city_distance` VALUES (744, '合肥', '广州', 1268);
INSERT INTO `province_city_distance` VALUES (745, '合肥', '海口', 1868);
INSERT INTO `province_city_distance` VALUES (746, '合肥', '香港', 1278);
INSERT INTO `province_city_distance` VALUES (747, '合肥', '澳门', 1340);
INSERT INTO `province_city_distance` VALUES (748, '合肥', '台北', 714);
INSERT INTO `province_city_distance` VALUES (749, '南京', '北京', 2373);
INSERT INTO `province_city_distance` VALUES (750, '南京', '天津', 975);
INSERT INTO `province_city_distance` VALUES (751, '南京', '上海', 295);
INSERT INTO `province_city_distance` VALUES (752, '南京', '重庆', 1651);
INSERT INTO `province_city_distance` VALUES (753, '南京', '呼和浩特', 1545);
INSERT INTO `province_city_distance` VALUES (754, '南京', '乌鲁木齐', 3652);
INSERT INTO `province_city_distance` VALUES (755, '南京', '拉萨', 3880);
INSERT INTO `province_city_distance` VALUES (756, '南京', '银川', 1815);
INSERT INTO `province_city_distance` VALUES (757, '南京', '南宁', 1783);
INSERT INTO `province_city_distance` VALUES (758, '南京', '哈尔滨', 2166);
INSERT INTO `province_city_distance` VALUES (759, '南京', '长春', 1915);
INSERT INTO `province_city_distance` VALUES (760, '南京', '沈阳', 1610);
INSERT INTO `province_city_distance` VALUES (761, '南京', '石家庄', 942);
INSERT INTO `province_city_distance` VALUES (762, '南京', '郑州', 666);
INSERT INTO `province_city_distance` VALUES (763, '南京', '济南', 663);
INSERT INTO `province_city_distance` VALUES (764, '南京', '太原', 1095);
INSERT INTO `province_city_distance` VALUES (765, '南京', '兰州', 1729);
INSERT INTO `province_city_distance` VALUES (766, '南京', '西安', 1174);
INSERT INTO `province_city_distance` VALUES (767, '南京', '西宁', 2029);
INSERT INTO `province_city_distance` VALUES (768, '南京', '成都', 1970);
INSERT INTO `province_city_distance` VALUES (769, '南京', '武汉', 614);
INSERT INTO `province_city_distance` VALUES (770, '南京', '合肥', 173);
INSERT INTO `province_city_distance` VALUES (771, '南京', '南京', 0);
INSERT INTO `province_city_distance` VALUES (772, '南京', '杭州', 278);
INSERT INTO `province_city_distance` VALUES (773, '南京', '福州', 966);
INSERT INTO `province_city_distance` VALUES (774, '南京', '南昌', 632);
INSERT INTO `province_city_distance` VALUES (775, '南京', '长沙', 927);
INSERT INTO `province_city_distance` VALUES (776, '南京', '贵阳', 1844);
INSERT INTO `province_city_distance` VALUES (777, '南京', '昆明', 2402);
INSERT INTO `province_city_distance` VALUES (778, '南京', '广州', 1462);
INSERT INTO `province_city_distance` VALUES (779, '南京', '海口', 2017);
INSERT INTO `province_city_distance` VALUES (780, '南京', '香港', 1395);
INSERT INTO `province_city_distance` VALUES (781, '南京', '澳门', 1480);
INSERT INTO `province_city_distance` VALUES (782, '南京', '台北', 573);
INSERT INTO `province_city_distance` VALUES (783, '杭州', '北京', 1322);
INSERT INTO `province_city_distance` VALUES (784, '杭州', '天津', 1240);
INSERT INTO `province_city_distance` VALUES (785, '杭州', '上海', 178);
INSERT INTO `province_city_distance` VALUES (786, '杭州', '重庆', 1897);
INSERT INTO `province_city_distance` VALUES (787, '杭州', '呼和浩特', 1802);
INSERT INTO `province_city_distance` VALUES (788, '杭州', '乌鲁木齐', 3928);
INSERT INTO `province_city_distance` VALUES (789, '杭州', '拉萨', 4155);
INSERT INTO `province_city_distance` VALUES (790, '杭州', '银川', 2091);
INSERT INTO `province_city_distance` VALUES (791, '杭州', '南宁', 1706);
INSERT INTO `province_city_distance` VALUES (792, '杭州', '哈尔滨', 2425);
INSERT INTO `province_city_distance` VALUES (793, '杭州', '长春', 2173);
INSERT INTO `province_city_distance` VALUES (794, '杭州', '沈阳', 1800);
INSERT INTO `province_city_distance` VALUES (795, '杭州', '石家庄', 1200);
INSERT INTO `province_city_distance` VALUES (796, '杭州', '郑州', 941);
INSERT INTO `province_city_distance` VALUES (797, '杭州', '济南', 929);
INSERT INTO `province_city_distance` VALUES (798, '杭州', '太原', 1370);
INSERT INTO `province_city_distance` VALUES (799, '杭州', '兰州', 2005);
INSERT INTO `province_city_distance` VALUES (800, '杭州', '西安', 1379);
INSERT INTO `province_city_distance` VALUES (801, '杭州', '西宁', 2239);
INSERT INTO `province_city_distance` VALUES (802, '杭州', '成都', 2179);
INSERT INTO `province_city_distance` VALUES (803, '杭州', '武汉', 860);
INSERT INTO `province_city_distance` VALUES (804, '杭州', '合肥', 419);
INSERT INTO `province_city_distance` VALUES (805, '杭州', '南京', 278);
INSERT INTO `province_city_distance` VALUES (806, '杭州', '杭州', 0);
INSERT INTO `province_city_distance` VALUES (807, '杭州', '福州', 688);
INSERT INTO `province_city_distance` VALUES (808, '杭州', '南昌', 562);
INSERT INTO `province_city_distance` VALUES (809, '杭州', '长沙', 971);
INSERT INTO `province_city_distance` VALUES (810, '杭州', '贵阳', 1763);
INSERT INTO `province_city_distance` VALUES (811, '杭州', '昆明', 2343);
INSERT INTO `province_city_distance` VALUES (812, '杭州', '广州', 1352);
INSERT INTO `province_city_distance` VALUES (813, '杭州', '海口', 1947);
INSERT INTO `province_city_distance` VALUES (814, '杭州', '香港', 1287);
INSERT INTO `province_city_distance` VALUES (815, '杭州', '澳门', 1372);
INSERT INTO `province_city_distance` VALUES (816, '杭州', '台北', 834);
INSERT INTO `province_city_distance` VALUES (817, '福州', '北京', 1981);
INSERT INTO `province_city_distance` VALUES (818, '福州', '天津', 1891);
INSERT INTO `province_city_distance` VALUES (819, '福州', '上海', 836);
INSERT INTO `province_city_distance` VALUES (820, '福州', '重庆', 1962);
INSERT INTO `province_city_distance` VALUES (821, '福州', '呼和浩特', 2401);
INSERT INTO `province_city_distance` VALUES (822, '福州', '乌鲁木齐', 4255);
INSERT INTO `province_city_distance` VALUES (823, '福州', '拉萨', 4447);
INSERT INTO `province_city_distance` VALUES (824, '福州', '银川', 2418);
INSERT INTO `province_city_distance` VALUES (825, '福州', '南宁', 1615);
INSERT INTO `province_city_distance` VALUES (826, '福州', '哈尔滨', 3083);
INSERT INTO `province_city_distance` VALUES (827, '福州', '长春', 2831);
INSERT INTO `province_city_distance` VALUES (828, '福州', '沈阳', 2533);
INSERT INTO `province_city_distance` VALUES (829, '福州', '石家庄', 1859);
INSERT INTO `province_city_distance` VALUES (830, '福州', '郑州', 1452);
INSERT INTO `province_city_distance` VALUES (831, '福州', '济南', 1580);
INSERT INTO `province_city_distance` VALUES (832, '福州', '太原', 1882);
INSERT INTO `province_city_distance` VALUES (833, '福州', '兰州', 2332);
INSERT INTO `province_city_distance` VALUES (834, '福州', '西安', 1699);
INSERT INTO `province_city_distance` VALUES (835, '福州', '西宁', 2566);
INSERT INTO `province_city_distance` VALUES (836, '福州', '成都', 2341);
INSERT INTO `province_city_distance` VALUES (837, '福州', '武汉', 924);
INSERT INTO `province_city_distance` VALUES (838, '福州', '合肥', 999);
INSERT INTO `province_city_distance` VALUES (839, '福州', '南京', 966);
INSERT INTO `province_city_distance` VALUES (840, '福州', '杭州', 688);
INSERT INTO `province_city_distance` VALUES (841, '福州', '福州', 0);
INSERT INTO `province_city_distance` VALUES (842, '福州', '南昌', 582);
INSERT INTO `province_city_distance` VALUES (843, '福州', '长沙', 967);
INSERT INTO `province_city_distance` VALUES (844, '福州', '贵阳', 1756);
INSERT INTO `province_city_distance` VALUES (845, '福州', '昆明', 2478);
INSERT INTO `province_city_distance` VALUES (846, '福州', '广州', 901);
INSERT INTO `province_city_distance` VALUES (847, '福州', '海口', 1497);
INSERT INTO `province_city_distance` VALUES (848, '福州', '香港', 868);
INSERT INTO `province_city_distance` VALUES (849, '福州', '澳门', 957);
INSERT INTO `province_city_distance` VALUES (850, '福州', '台北', 1432);
INSERT INTO `province_city_distance` VALUES (851, '南昌', '北京', 1458);
INSERT INTO `province_city_distance` VALUES (852, '南昌', '天津', 1406);
INSERT INTO `province_city_distance` VALUES (853, '南昌', '上海', 775);
INSERT INTO `province_city_distance` VALUES (854, '南昌', '重庆', 1400);
INSERT INTO `province_city_distance` VALUES (855, '南昌', '呼和浩特', 1906);
INSERT INTO `province_city_distance` VALUES (856, '南昌', '乌鲁木齐', 3647);
INSERT INTO `province_city_distance` VALUES (857, '南昌', '拉萨', 3885);
INSERT INTO `province_city_distance` VALUES (858, '南昌', '银川', 1921);
INSERT INTO `province_city_distance` VALUES (859, '南昌', '南宁', 1185);
INSERT INTO `province_city_distance` VALUES (860, '南昌', '哈尔滨', 2591);
INSERT INTO `province_city_distance` VALUES (861, '南昌', '长春', 2341);
INSERT INTO `province_city_distance` VALUES (862, '南昌', '沈阳', 2045);
INSERT INTO `province_city_distance` VALUES (863, '南昌', '石家庄', 1278);
INSERT INTO `province_city_distance` VALUES (864, '南昌', '郑州', 961);
INSERT INTO `province_city_distance` VALUES (865, '南昌', '济南', 1095);
INSERT INTO `province_city_distance` VALUES (866, '南昌', '太原', 1389);
INSERT INTO `province_city_distance` VALUES (867, '南昌', '兰州', 1835);
INSERT INTO `province_city_distance` VALUES (868, '南昌', '西安', 1205);
INSERT INTO `province_city_distance` VALUES (869, '南昌', '西宁', 2068);
INSERT INTO `province_city_distance` VALUES (870, '南昌', '成都', 1844);
INSERT INTO `province_city_distance` VALUES (871, '南昌', '武汉', 434);
INSERT INTO `province_city_distance` VALUES (872, '南昌', '合肥', 509);
INSERT INTO `province_city_distance` VALUES (873, '南昌', '南京', 632);
INSERT INTO `province_city_distance` VALUES (874, '南昌', '杭州', 562);
INSERT INTO `province_city_distance` VALUES (875, '南昌', '福州', 582);
INSERT INTO `province_city_distance` VALUES (876, '南昌', '南昌', 0);
INSERT INTO `province_city_distance` VALUES (877, '南昌', '长沙', 453);
INSERT INTO `province_city_distance` VALUES (878, '南昌', '贵阳', 1242);
INSERT INTO `province_city_distance` VALUES (879, '南昌', '昆明', 1822);
INSERT INTO `province_city_distance` VALUES (880, '南昌', '广州', 835);
INSERT INTO `province_city_distance` VALUES (881, '南昌', '海口', 1426);
INSERT INTO `province_city_distance` VALUES (882, '南昌', '香港', 849);
INSERT INTO `province_city_distance` VALUES (883, '南昌', '澳门', 910);
INSERT INTO `province_city_distance` VALUES (884, '南昌', '台北', 1150);
INSERT INTO `province_city_distance` VALUES (885, '长沙', '北京', 1516);
INSERT INTO `province_city_distance` VALUES (886, '长沙', '天津', 1576);
INSERT INTO `province_city_distance` VALUES (887, '长沙', '上海', 1049);
INSERT INTO `province_city_distance` VALUES (888, '长沙', '重庆', 1500);
INSERT INTO `province_city_distance` VALUES (889, '长沙', '呼和浩特', 1798);
INSERT INTO `province_city_distance` VALUES (890, '长沙', '乌鲁木齐', 3640);
INSERT INTO `province_city_distance` VALUES (891, '长沙', '拉萨', 3960);
INSERT INTO `province_city_distance` VALUES (892, '长沙', '银川', 1814);
INSERT INTO `province_city_distance` VALUES (893, '长沙', '南宁', 851);
INSERT INTO `province_city_distance` VALUES (894, '长沙', '哈尔滨', 2700);
INSERT INTO `province_city_distance` VALUES (895, '长沙', '长春', 2433);
INSERT INTO `province_city_distance` VALUES (896, '长沙', '沈阳', 2132);
INSERT INTO `province_city_distance` VALUES (897, '长沙', '石家庄', 1240);
INSERT INTO `province_city_distance` VALUES (898, '长沙', '郑州', 853);
INSERT INTO `province_city_distance` VALUES (899, '长沙', '济南', 1209);
INSERT INTO `province_city_distance` VALUES (900, '长沙', '太原', 1281);
INSERT INTO `province_city_distance` VALUES (901, '长沙', '兰州', 1727);
INSERT INTO `province_city_distance` VALUES (902, '长沙', '西安', 1097);
INSERT INTO `province_city_distance` VALUES (903, '长沙', '西宁', 1960);
INSERT INTO `province_city_distance` VALUES (904, '长沙', '成都', 1801);
INSERT INTO `province_city_distance` VALUES (905, '长沙', '武汉', 361);
INSERT INTO `province_city_distance` VALUES (906, '长沙', '合肥', 771);
INSERT INTO `province_city_distance` VALUES (907, '长沙', '南京', 927);
INSERT INTO `province_city_distance` VALUES (908, '长沙', '杭州', 971);
INSERT INTO `province_city_distance` VALUES (909, '长沙', '福州', 967);
INSERT INTO `province_city_distance` VALUES (910, '长沙', '南昌', 453);
INSERT INTO `province_city_distance` VALUES (911, '长沙', '长沙', 0);
INSERT INTO `province_city_distance` VALUES (912, '长沙', '贵阳', 1105);
INSERT INTO `province_city_distance` VALUES (913, '长沙', '昆明', 2626);
INSERT INTO `province_city_distance` VALUES (914, '长沙', '广州', 734);
INSERT INTO `province_city_distance` VALUES (915, '长沙', '海口', 1326);
INSERT INTO `province_city_distance` VALUES (916, '长沙', '香港', 835);
INSERT INTO `province_city_distance` VALUES (917, '长沙', '澳门', 815);
INSERT INTO `province_city_distance` VALUES (918, '长沙', '台北', 926);
INSERT INTO `province_city_distance` VALUES (919, '贵阳', '北京', 2318);
INSERT INTO `province_city_distance` VALUES (920, '贵阳', '天津', 2364);
INSERT INTO `province_city_distance` VALUES (921, '贵阳', '上海', 1910);
INSERT INTO `province_city_distance` VALUES (922, '贵阳', '重庆', 384);
INSERT INTO `province_city_distance` VALUES (923, '贵阳', '呼和浩特', 2227);
INSERT INTO `province_city_distance` VALUES (924, '贵阳', '乌鲁木齐', 3671);
INSERT INTO `province_city_distance` VALUES (925, '贵阳', '拉萨', 2822);
INSERT INTO `province_city_distance` VALUES (926, '贵阳', '银川', 1951);
INSERT INTO `province_city_distance` VALUES (927, '贵阳', '南宁', 623);
INSERT INTO `province_city_distance` VALUES (928, '贵阳', '哈尔滨', 3537);
INSERT INTO `province_city_distance` VALUES (929, '贵阳', '长春', 3285);
INSERT INTO `province_city_distance` VALUES (930, '贵阳', '沈阳', 1987);
INSERT INTO `province_city_distance` VALUES (931, '贵阳', '石家庄', 2030);
INSERT INTO `province_city_distance` VALUES (932, '贵阳', '郑州', 1544);
INSERT INTO `province_city_distance` VALUES (933, '贵阳', '济南', 1934);
INSERT INTO `province_city_distance` VALUES (934, '贵阳', '太原', 1862);
INSERT INTO `province_city_distance` VALUES (935, '贵阳', '兰州', 1754);
INSERT INTO `province_city_distance` VALUES (936, '贵阳', '西安', 2885);
INSERT INTO `province_city_distance` VALUES (937, '贵阳', '西宁', 1809);
INSERT INTO `province_city_distance` VALUES (938, '贵阳', '成都', 736);
INSERT INTO `province_city_distance` VALUES (939, '贵阳', '武汉', 1278);
INSERT INTO `province_city_distance` VALUES (940, '贵阳', '合肥', 1688);
INSERT INTO `province_city_distance` VALUES (941, '贵阳', '南京', 1844);
INSERT INTO `province_city_distance` VALUES (942, '贵阳', '杭州', 1763);
INSERT INTO `province_city_distance` VALUES (943, '贵阳', '福州', 1756);
INSERT INTO `province_city_distance` VALUES (944, '贵阳', '南昌', 1242);
INSERT INTO `province_city_distance` VALUES (945, '贵阳', '长沙', 1105);
INSERT INTO `province_city_distance` VALUES (946, '贵阳', '贵阳', 0);
INSERT INTO `province_city_distance` VALUES (947, '贵阳', '昆明', 588);
INSERT INTO `province_city_distance` VALUES (948, '贵阳', '广州', 470);
INSERT INTO `province_city_distance` VALUES (949, '贵阳', '海口', 1320);
INSERT INTO `province_city_distance` VALUES (950, '贵阳', '香港', 893);
INSERT INTO `province_city_distance` VALUES (951, '贵阳', '澳门', 847);
INSERT INTO `province_city_distance` VALUES (952, '贵阳', '台北', 1492);
INSERT INTO `province_city_distance` VALUES (953, '昆明', '北京', 2907);
INSERT INTO `province_city_distance` VALUES (954, '昆明', '天津', 2923);
INSERT INTO `province_city_distance` VALUES (955, '昆明', '上海', 2529);
INSERT INTO `province_city_distance` VALUES (956, '昆明', '重庆', 973);
INSERT INTO `province_city_distance` VALUES (957, '昆明', '呼和浩特', 2816);
INSERT INTO `province_city_distance` VALUES (958, '昆明', '乌鲁木齐', 3865);
INSERT INTO `province_city_distance` VALUES (959, '昆明', '拉萨', 2246);
INSERT INTO `province_city_distance` VALUES (960, '昆明', '银川', 2540);
INSERT INTO `province_city_distance` VALUES (961, '昆明', '南宁', 879);
INSERT INTO `province_city_distance` VALUES (962, '昆明', '哈尔滨', 4126);
INSERT INTO `province_city_distance` VALUES (963, '昆明', '长春', 3874);
INSERT INTO `province_city_distance` VALUES (964, '昆明', '沈阳', 3572);
INSERT INTO `province_city_distance` VALUES (965, '昆明', '石家庄', 26119);
INSERT INTO `province_city_distance` VALUES (966, '昆明', '郑州', 2100);
INSERT INTO `province_city_distance` VALUES (967, '昆明', '济南', 2514);
INSERT INTO `province_city_distance` VALUES (968, '昆明', '太原', 2419);
INSERT INTO `province_city_distance` VALUES (969, '昆明', '兰州', 1938);
INSERT INTO `province_city_distance` VALUES (970, '昆明', '西安', 3475);
INSERT INTO `province_city_distance` VALUES (971, '昆明', '西宁', 1977);
INSERT INTO `province_city_distance` VALUES (972, '昆明', '成都', 917);
INSERT INTO `province_city_distance` VALUES (973, '昆明', '武汉', 1834);
INSERT INTO `province_city_distance` VALUES (974, '昆明', '合肥', 2245);
INSERT INTO `province_city_distance` VALUES (975, '昆明', '南京', 2402);
INSERT INTO `province_city_distance` VALUES (976, '昆明', '杭州', 2343);
INSERT INTO `province_city_distance` VALUES (977, '昆明', '福州', 2478);
INSERT INTO `province_city_distance` VALUES (978, '昆明', '南昌', 1822);
INSERT INTO `province_city_distance` VALUES (979, '昆明', '长沙', 2626);
INSERT INTO `province_city_distance` VALUES (980, '昆明', '贵阳', 588);
INSERT INTO `province_city_distance` VALUES (981, '昆明', '昆明', 0);
INSERT INTO `province_city_distance` VALUES (982, '昆明', '广州', 1585);
INSERT INTO `province_city_distance` VALUES (983, '昆明', '海口', 1337);
INSERT INTO `province_city_distance` VALUES (984, '昆明', '香港', 1735);
INSERT INTO `province_city_distance` VALUES (985, '昆明', '澳门', 1472);
INSERT INTO `province_city_distance` VALUES (986, '昆明', '台北', 2200);
INSERT INTO `province_city_distance` VALUES (987, '广州', '北京', 2179);
INSERT INTO `province_city_distance` VALUES (988, '广州', '天津', 2197);
INSERT INTO `province_city_distance` VALUES (989, '广州', '上海', 1539);
INSERT INTO `province_city_distance` VALUES (990, '广州', '重庆', 1710);
INSERT INTO `province_city_distance` VALUES (991, '广州', '呼和浩特', 2461);
INSERT INTO `province_city_distance` VALUES (992, '广州', '乌鲁木齐', 4316);
INSERT INTO `province_city_distance` VALUES (993, '广州', '拉萨', 4148);
INSERT INTO `province_city_distance` VALUES (994, '广州', '银川', 2479);
INSERT INTO `province_city_distance` VALUES (995, '广州', '南宁', 734);
INSERT INTO `province_city_distance` VALUES (996, '广州', '哈尔滨', 3383);
INSERT INTO `province_city_distance` VALUES (997, '广州', '长春', 3133);
INSERT INTO `province_city_distance` VALUES (998, '广州', '沈阳', 2832);
INSERT INTO `province_city_distance` VALUES (999, '广州', '石家庄', 1902);
INSERT INTO `province_city_distance` VALUES (1000, '广州', '郑州', 1516);
INSERT INTO `province_city_distance` VALUES (1001, '广州', '济南', 1887);
INSERT INTO `province_city_distance` VALUES (1002, '广州', '太原', 1944);
INSERT INTO `province_city_distance` VALUES (1003, '广州', '兰州', 2393);
INSERT INTO `province_city_distance` VALUES (1004, '广州', '西安', 1760);
INSERT INTO `province_city_distance` VALUES (1005, '广州', '西宁', 2627);
INSERT INTO `province_city_distance` VALUES (1006, '广州', '成都', 2047);
INSERT INTO `province_city_distance` VALUES (1007, '广州', '武汉', 1021);
INSERT INTO `province_city_distance` VALUES (1008, '广州', '合肥', 1268);
INSERT INTO `province_city_distance` VALUES (1009, '广州', '南京', 1462);
INSERT INTO `province_city_distance` VALUES (1010, '广州', '杭州', 1352);
INSERT INTO `province_city_distance` VALUES (1011, '广州', '福州', 901);
INSERT INTO `province_city_distance` VALUES (1012, '广州', '南昌', 835);
INSERT INTO `province_city_distance` VALUES (1013, '广州', '长沙', 734);
INSERT INTO `province_city_distance` VALUES (1014, '广州', '贵阳', 470);
INSERT INTO `province_city_distance` VALUES (1015, '广州', '昆明', 1585);
INSERT INTO `province_city_distance` VALUES (1016, '广州', '广州', 0);
INSERT INTO `province_city_distance` VALUES (1017, '广州', '海口', 601);
INSERT INTO `province_city_distance` VALUES (1018, '广州', '香港', 135);
INSERT INTO `province_city_distance` VALUES (1019, '广州', '澳门', 110);
INSERT INTO `province_city_distance` VALUES (1020, '广州', '台北', 882);
INSERT INTO `province_city_distance` VALUES (1021, '海口', '北京', 2775);
INSERT INTO `province_city_distance` VALUES (1022, '海口', '天津', 2792);
INSERT INTO `province_city_distance` VALUES (1023, '海口', '上海', 2134);
INSERT INTO `province_city_distance` VALUES (1024, '海口', '重庆', 1467);
INSERT INTO `province_city_distance` VALUES (1025, '海口', '呼和浩特', 3057);
INSERT INTO `province_city_distance` VALUES (1026, '海口', '乌鲁木齐', 4747);
INSERT INTO `province_city_distance` VALUES (1027, '海口', '拉萨', 3899);
INSERT INTO `province_city_distance` VALUES (1028, '海口', '银川', 3074);
INSERT INTO `province_city_distance` VALUES (1029, '海口', '南宁', 473);
INSERT INTO `province_city_distance` VALUES (1030, '海口', '哈尔滨', 3984);
INSERT INTO `province_city_distance` VALUES (1031, '海口', '长春', 3732);
INSERT INTO `province_city_distance` VALUES (1032, '海口', '沈阳', 3435);
INSERT INTO `province_city_distance` VALUES (1033, '海口', '石家庄', 2496);
INSERT INTO `province_city_distance` VALUES (1034, '海口', '郑州', 2108);
INSERT INTO `province_city_distance` VALUES (1035, '海口', '济南', 2480);
INSERT INTO `province_city_distance` VALUES (1036, '海口', '太原', 2538);
INSERT INTO `province_city_distance` VALUES (1037, '海口', '兰州', 2820);
INSERT INTO `province_city_distance` VALUES (1038, '海口', '西安', 2355);
INSERT INTO `province_city_distance` VALUES (1039, '海口', '西宁', 2875);
INSERT INTO `province_city_distance` VALUES (1040, '海口', '成都', 1798);
INSERT INTO `province_city_distance` VALUES (1041, '海口', '武汉', 1615);
INSERT INTO `province_city_distance` VALUES (1042, '海口', '合肥', 1868);
INSERT INTO `province_city_distance` VALUES (1043, '海口', '南京', 2017);
INSERT INTO `province_city_distance` VALUES (1044, '海口', '杭州', 1947);
INSERT INTO `province_city_distance` VALUES (1045, '海口', '福州', 1497);
INSERT INTO `province_city_distance` VALUES (1046, '海口', '南昌', 1426);
INSERT INTO `province_city_distance` VALUES (1047, '海口', '长沙', 1326);
INSERT INTO `province_city_distance` VALUES (1048, '海口', '贵阳', 1320);
INSERT INTO `province_city_distance` VALUES (1049, '海口', '昆明', 1337);
INSERT INTO `province_city_distance` VALUES (1050, '海口', '广州', 601);
INSERT INTO `province_city_distance` VALUES (1051, '海口', '海口', 0);
INSERT INTO `province_city_distance` VALUES (1052, '海口', '香港', 702);
INSERT INTO `province_city_distance` VALUES (1053, '海口', '澳门', 581);
INSERT INTO `province_city_distance` VALUES (1054, '海口', '台北', 2517);
INSERT INTO `province_city_distance` VALUES (1055, '香港', '北京', 2100);
INSERT INTO `province_city_distance` VALUES (1056, '香港', '天津', 2224);
INSERT INTO `province_city_distance` VALUES (1057, '香港', '上海', 1504);
INSERT INTO `province_city_distance` VALUES (1058, '香港', '重庆', 1283);
INSERT INTO `province_city_distance` VALUES (1059, '香港', '呼和浩特', 2283);
INSERT INTO `province_city_distance` VALUES (1060, '香港', '乌鲁木齐', 4143);
INSERT INTO `province_city_distance` VALUES (1061, '香港', '拉萨', 3691);
INSERT INTO `province_city_distance` VALUES (1062, '香港', '银川', 2462);
INSERT INTO `province_city_distance` VALUES (1063, '香港', '南宁', 540);
INSERT INTO `province_city_distance` VALUES (1064, '香港', '哈尔滨', 3664);
INSERT INTO `province_city_distance` VALUES (1065, '香港', '长春', 3112);
INSERT INTO `province_city_distance` VALUES (1066, '香港', '沈阳', 2850);
INSERT INTO `province_city_distance` VALUES (1067, '香港', '石家庄', 2009);
INSERT INTO `province_city_distance` VALUES (1068, '香港', '郑州', 1598);
INSERT INTO `province_city_distance` VALUES (1069, '香港', '济南', 1917);
INSERT INTO `province_city_distance` VALUES (1070, '香港', '太原', 2045);
INSERT INTO `province_city_distance` VALUES (1071, '香港', '兰州', 2425);
INSERT INTO `province_city_distance` VALUES (1072, '香港', '西安', 1420);
INSERT INTO `province_city_distance` VALUES (1073, '香港', '西宁', 2651);
INSERT INTO `province_city_distance` VALUES (1074, '香港', '成都', 1771);
INSERT INTO `province_city_distance` VALUES (1075, '香港', '武汉', 1115);
INSERT INTO `province_city_distance` VALUES (1076, '香港', '合肥', 1278);
INSERT INTO `province_city_distance` VALUES (1077, '香港', '南京', 1395);
INSERT INTO `province_city_distance` VALUES (1078, '香港', '杭州', 1287);
INSERT INTO `province_city_distance` VALUES (1079, '香港', '福州', 868);
INSERT INTO `province_city_distance` VALUES (1080, '香港', '南昌', 849);
INSERT INTO `province_city_distance` VALUES (1081, '香港', '长沙', 835);
INSERT INTO `province_city_distance` VALUES (1082, '香港', '贵阳', 893);
INSERT INTO `province_city_distance` VALUES (1083, '香港', '昆明', 1735);
INSERT INTO `province_city_distance` VALUES (1084, '香港', '广州', 135);
INSERT INTO `province_city_distance` VALUES (1085, '香港', '海口', 702);
INSERT INTO `province_city_distance` VALUES (1086, '香港', '香港', 0);
INSERT INTO `province_city_distance` VALUES (1087, '香港', '澳门', 224);
INSERT INTO `province_city_distance` VALUES (1088, '香港', '台北', 780);
INSERT INTO `province_city_distance` VALUES (1089, '澳门', '北京', 2265);
INSERT INTO `province_city_distance` VALUES (1090, '澳门', '天津', 2234);
INSERT INTO `province_city_distance` VALUES (1091, '澳门', '上海', 1615);
INSERT INTO `province_city_distance` VALUES (1092, '澳门', '重庆', 1093);
INSERT INTO `province_city_distance` VALUES (1093, '澳门', '呼和浩特', 2443);
INSERT INTO `province_city_distance` VALUES (1094, '澳门', '乌鲁木齐', 4307);
INSERT INTO `province_city_distance` VALUES (1095, '澳门', '拉萨', 3666);
INSERT INTO `province_city_distance` VALUES (1096, '澳门', '银川', 2501);
INSERT INTO `province_city_distance` VALUES (1097, '澳门', '南宁', 601);
INSERT INTO `province_city_distance` VALUES (1098, '澳门', '哈尔滨', 3448);
INSERT INTO `province_city_distance` VALUES (1099, '澳门', '长春', 2088);
INSERT INTO `province_city_distance` VALUES (1100, '澳门', '沈阳', 2906);
INSERT INTO `province_city_distance` VALUES (1101, '澳门', '石家庄', 1984);
INSERT INTO `province_city_distance` VALUES (1102, '澳门', '郑州', 1583);
INSERT INTO `province_city_distance` VALUES (1103, '澳门', '济南', 1986);
INSERT INTO `province_city_distance` VALUES (1104, '澳门', '太原', 2019);
INSERT INTO `province_city_distance` VALUES (1105, '澳门', '兰州', 2445);
INSERT INTO `province_city_distance` VALUES (1106, '澳门', '西安', 1780);
INSERT INTO `province_city_distance` VALUES (1107, '澳门', '西宁', 2644);
INSERT INTO `province_city_distance` VALUES (1108, '澳门', '成都', 1714);
INSERT INTO `province_city_distance` VALUES (1109, '澳门', '武汉', 1124);
INSERT INTO `province_city_distance` VALUES (1110, '澳门', '合肥', 1340);
INSERT INTO `province_city_distance` VALUES (1111, '澳门', '南京', 1480);
INSERT INTO `province_city_distance` VALUES (1112, '澳门', '杭州', 1372);
INSERT INTO `province_city_distance` VALUES (1113, '澳门', '福州', 957);
INSERT INTO `province_city_distance` VALUES (1114, '澳门', '南昌', 910);
INSERT INTO `province_city_distance` VALUES (1115, '澳门', '长沙', 815);
INSERT INTO `province_city_distance` VALUES (1116, '澳门', '贵阳', 847);
INSERT INTO `province_city_distance` VALUES (1117, '澳门', '昆明', 1472);
INSERT INTO `province_city_distance` VALUES (1118, '澳门', '广州', 110);
INSERT INTO `province_city_distance` VALUES (1119, '澳门', '海口', 581);
INSERT INTO `province_city_distance` VALUES (1120, '澳门', '香港', 224);
INSERT INTO `province_city_distance` VALUES (1121, '澳门', '澳门', 0);
INSERT INTO `province_city_distance` VALUES (1122, '澳门', '台北', 823);
INSERT INTO `province_city_distance` VALUES (1123, '台北', '北京', 1715);
INSERT INTO `province_city_distance` VALUES (1124, '台北', '天津', 2130);
INSERT INTO `province_city_distance` VALUES (1125, '台北', '上海', 685);
INSERT INTO `province_city_distance` VALUES (1126, '台北', '重庆', 1835);
INSERT INTO `province_city_distance` VALUES (1127, '台北', '呼和浩特', 1142);
INSERT INTO `province_city_distance` VALUES (1128, '台北', '乌鲁木齐', 3432);
INSERT INTO `province_city_distance` VALUES (1129, '台北', '拉萨', 4438);
INSERT INTO `province_city_distance` VALUES (1130, '台北', '银川', 1453);
INSERT INTO `province_city_distance` VALUES (1131, '台北', '南宁', 1362);
INSERT INTO `province_city_distance` VALUES (1132, '台北', '哈尔滨', 2350);
INSERT INTO `province_city_distance` VALUES (1133, '台北', '长春', 2404);
INSERT INTO `province_city_distance` VALUES (1134, '台北', '沈阳', 1182);
INSERT INTO `province_city_distance` VALUES (1135, '台北', '石家庄', 669);
INSERT INTO `province_city_distance` VALUES (1136, '台北', '郑州', 727);
INSERT INTO `province_city_distance` VALUES (1137, '台北', '济南', 1360);
INSERT INTO `province_city_distance` VALUES (1138, '台北', '太原', 887);
INSERT INTO `province_city_distance` VALUES (1139, '台北', '兰州', 2096);
INSERT INTO `province_city_distance` VALUES (1140, '台北', '西安', 1581);
INSERT INTO `province_city_distance` VALUES (1141, '台北', '西宁', 2014);
INSERT INTO `province_city_distance` VALUES (1142, '台北', '成都', 1892);
INSERT INTO `province_city_distance` VALUES (1143, '台北', '武汉', 1075);
INSERT INTO `province_city_distance` VALUES (1144, '台北', '合肥', 714);
INSERT INTO `province_city_distance` VALUES (1145, '台北', '南京', 573);
INSERT INTO `province_city_distance` VALUES (1146, '台北', '杭州', 834);
INSERT INTO `province_city_distance` VALUES (1147, '台北', '福州', 1432);
INSERT INTO `province_city_distance` VALUES (1148, '台北', '南昌', 1150);
INSERT INTO `province_city_distance` VALUES (1149, '台北', '长沙', 926);
INSERT INTO `province_city_distance` VALUES (1150, '台北', '贵阳', 1492);
INSERT INTO `province_city_distance` VALUES (1151, '台北', '昆明', 2200);
INSERT INTO `province_city_distance` VALUES (1152, '台北', '广州', 882);
INSERT INTO `province_city_distance` VALUES (1153, '台北', '海口', 2517);
INSERT INTO `province_city_distance` VALUES (1154, '台北', '香港', 780);
INSERT INTO `province_city_distance` VALUES (1155, '台北', '澳门', 823);
INSERT INTO `province_city_distance` VALUES (1156, '台北', '台北', 0);
COMMIT;

-- ----------------------------
-- Table structure for province_data
-- ----------------------------
DROP TABLE IF EXISTS `province_data`;
CREATE TABLE `province_data` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '省编号',
  `name` varchar(100) DEFAULT NULL COMMENT '省名称',
  `code` varchar(50) DEFAULT NULL COMMENT '拼音码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of province_data
-- ----------------------------
BEGIN;
INSERT INTO `province_data` VALUES (1, '北京市', '110000');
INSERT INTO `province_data` VALUES (2, '上海市', '310000');
INSERT INTO `province_data` VALUES (3, '天津市', '120000');
INSERT INTO `province_data` VALUES (4, '重庆市', '500000');
INSERT INTO `province_data` VALUES (5, '安徽省', '340000');
INSERT INTO `province_data` VALUES (6, '福建省', '350000');
INSERT INTO `province_data` VALUES (7, '甘肃省', '320000');
INSERT INTO `province_data` VALUES (8, '广东省', '440000');
INSERT INTO `province_data` VALUES (9, '广西', '450000');
INSERT INTO `province_data` VALUES (10, '贵州省', '520000');
INSERT INTO `province_data` VALUES (11, '河北省', '130000');
INSERT INTO `province_data` VALUES (12, '河南省', '410000');
INSERT INTO `province_data` VALUES (13, '黑龙江省', '230000');
INSERT INTO `province_data` VALUES (14, '湖北省', '420000');
INSERT INTO `province_data` VALUES (15, '湖南省', '430000');
INSERT INTO `province_data` VALUES (16, '吉林省', '220000');
INSERT INTO `province_data` VALUES (17, '江苏省', '320000');
INSERT INTO `province_data` VALUES (18, '江西省', '360000');
INSERT INTO `province_data` VALUES (19, '辽宁省', '210000');
INSERT INTO `province_data` VALUES (20, '内蒙古', '150000');
INSERT INTO `province_data` VALUES (21, '宁夏', '640000');
INSERT INTO `province_data` VALUES (22, '青海省', '630000');
INSERT INTO `province_data` VALUES (23, '山东省', '370000');
INSERT INTO `province_data` VALUES (24, '山西省', '140000');
INSERT INTO `province_data` VALUES (25, '陕西省', '610000');
INSERT INTO `province_data` VALUES (26, '四川省', '510000');
INSERT INTO `province_data` VALUES (27, '西藏', '540000');
INSERT INTO `province_data` VALUES (28, '新疆', '650000');
INSERT INTO `province_data` VALUES (29, '云南省', '530000');
INSERT INTO `province_data` VALUES (30, '浙江省', '330000');
INSERT INTO `province_data` VALUES (31, '海南省', '460000');
INSERT INTO `province_data` VALUES (32, '香港', '810000');
INSERT INTO `province_data` VALUES (33, '台湾省', '710000');
INSERT INTO `province_data` VALUES (34, '澳门', '820000');
COMMIT;

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `batch_id` bigint(20) NOT NULL COMMENT '批次id',
  `generate_number` int(11) NOT NULL COMMENT '总生成数据数目',
  `generate_status` varchar(32) DEFAULT NULL COMMENT '未生成：NOMINATED，生成成功：SUCCESS，正在生成：GENERATING，生成失败：FAIL',
  `generate_again` tinyint(4) DEFAULT NULL COMMENT '可以再生成：1，不需要再生成：0',
  `generate_time` datetime DEFAULT NULL COMMENT '最后一次生成时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `rt_id` bigint(20) DEFAULT NULL COMMENT '模板id',
  `org_id` bigint(20) NOT NULL COMMENT '组织机构id',
  `report_link` varchar(255) DEFAULT NULL COMMENT '报告链接',
  `visit_count` int(11) DEFAULT NULL COMMENT '浏览人数',
  `school_logo_path` varchar(255) DEFAULT NULL COMMENT '学校logo',
  PRIMARY KEY (`id`),
  KEY `batch_id` (`batch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8 COMMENT='报告';

-- ----------------------------
-- Table structure for report_detail_attribute
-- ----------------------------
DROP TABLE IF EXISTS `report_detail_attribute`;
CREATE TABLE `report_detail_attribute` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `rt_id` bigint(20) NOT NULL COMMENT '报告模板id',
  `rtp_id` bigint(20) DEFAULT NULL COMMENT '报告模板页id',
  `name` varchar(50) DEFAULT NULL COMMENT '代码标识',
  `description` varchar(100) DEFAULT NULL COMMENT '描述信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of report_detail_attribute
-- ----------------------------
BEGIN;
INSERT INTO `report_detail_attribute` VALUES (1, 1, NULL, 'schoolName', '学校名称');
INSERT INTO `report_detail_attribute` VALUES (2, 1, NULL, 'schoolLogo', '学校logo');
INSERT INTO `report_detail_attribute` VALUES (3, 1, NULL, 'reportName', '报告名称');
INSERT INTO `report_detail_attribute` VALUES (4, 1, NULL, 'templateBgmPath', '模板背景音乐');
INSERT INTO `report_detail_attribute` VALUES (5, 1, NULL, 'schoolNikeName', '学校简称');
INSERT INTO `report_detail_attribute` VALUES (6, 1, NULL, 'students', '学校总人数');
INSERT INTO `report_detail_attribute` VALUES (7, 1, NULL, 'collegeDistribution', '各学院学生人数统计');
INSERT INTO `report_detail_attribute` VALUES (8, 1, NULL, 'sourceDistribution', '各生源省份学生总人数');
INSERT INTO `report_detail_attribute` VALUES (9, 1, NULL, 'fromProvinceSum', '新生生源份总个数');
INSERT INTO `report_detail_attribute` VALUES (10, 1, NULL, 'distanceTotalSum', '新生生源城市与学校城市距离总和');
INSERT INTO `report_detail_attribute` VALUES (11, 1, NULL, 'earthCircleNum', '距离总和绕地球总圈数');
INSERT INTO `report_detail_attribute` VALUES (12, 1, NULL, 'longMarchNum', '距离总和跨长征距离总次数');
INSERT INTO `report_detail_attribute` VALUES (13, 1, NULL, 'sexRatio', '全校男女比例');
INSERT INTO `report_detail_attribute` VALUES (14, 1, NULL, 'majorBoyDistribution', '男生最多专业Top3及男女人数');
INSERT INTO `report_detail_attribute` VALUES (15, 1, NULL, 'majorGirlDistribution', '女生最多专业Top3及男女人数');
INSERT INTO `report_detail_attribute` VALUES (16, 1, NULL, 'majorScoreDistribution', '新生录取平均成绩专业排名');
INSERT INTO `report_detail_attribute` VALUES (17, 1, NULL, 'yearDistribution', '出生年份人数占比饼图');
INSERT INTO `report_detail_attribute` VALUES (18, 1, NULL, 'starDistribution', '全校本批新生12星座人数');
INSERT INTO `report_detail_attribute` VALUES (19, 1, NULL, 'starMate', '配对比例最高的TOP3星座配对');
INSERT INTO `report_detail_attribute` VALUES (20, 1, NULL, 'sameBirthTop3', '同月同日的人数最多TOP3生日');
INSERT INTO `report_detail_attribute` VALUES (21, 1, NULL, 'sameNameTop3', '同名同姓人数统计最多TOP3姓名');
COMMIT;

-- ----------------------------
-- Table structure for report_detail_value
-- ----------------------------
DROP TABLE IF EXISTS `report_detail_value`;
CREATE TABLE `report_detail_value` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `report_id` bigint(20) NOT NULL COMMENT '报告id',
  `report_detail_attribute_id` bigint(20) NOT NULL COMMENT '报告属性id',
  `value` longtext COMMENT '属性值',
  PRIMARY KEY (`id`),
  KEY `report_id` (`report_id`),
  KEY `report_detail_attribute_id` (`report_detail_attribute_id`),
  KEY `report_id_2` (`report_id`,`report_detail_attribute_id`)
) ENGINE=InnoDB AUTO_INCREMENT=148 DEFAULT CHARSET=utf8 COMMENT='报告属性值value';

-- ----------------------------
-- Table structure for report_template
-- ----------------------------
DROP TABLE IF EXISTS `report_template`;
CREATE TABLE `report_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '报告模板名称',
  `logo_patch` varchar(255) DEFAULT NULL COMMENT 'logo图标',
  `bgm_path` varchar(255) DEFAULT NULL COMMENT '背景音乐地址',
  `logo_remark` varchar(255) DEFAULT NULL COMMENT '报告图标备注',
  `default_template` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='报告模板';

-- ----------------------------
-- Records of report_template
-- ----------------------------
BEGIN;
INSERT INTO `report_template` VALUES (1, '迎新报告模板', '/reportTemplate/img/108.png', '/reportTemplate/img/music.mp3', '新鲜出炉！高校萌新大数据，pick你的脱单指数~', 1);
COMMIT;

-- ----------------------------
-- Table structure for report_template_page
-- ----------------------------
DROP TABLE IF EXISTS `report_template_page`;
CREATE TABLE `report_template_page` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) DEFAULT NULL COMMENT '页面编码，排序',
  `rt_id` bigint(20) DEFAULT NULL COMMENT '报告模板id',
  `page_snap_path` varchar(255) DEFAULT NULL COMMENT '页面快照，图片地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='报告模板页面';

-- ----------------------------
-- Records of report_template_page
-- ----------------------------
BEGIN;
INSERT INTO `report_template_page` VALUES (1, '01', 1, '/reportTemplate/img/1.png');
INSERT INTO `report_template_page` VALUES (2, '02', 1, '/reportTemplate/img/2.png');
INSERT INTO `report_template_page` VALUES (3, '03', 1, '/reportTemplate/img/3.png');
INSERT INTO `report_template_page` VALUES (4, '04', 1, '/reportTemplate/img/4.png');
INSERT INTO `report_template_page` VALUES (5, '05', 1, '/reportTemplate/img/5.png');
INSERT INTO `report_template_page` VALUES (6, '06', 1, '/reportTemplate/img/6.png');
INSERT INTO `report_template_page` VALUES (7, '07', 1, '/reportTemplate/img/7.png');
INSERT INTO `report_template_page` VALUES (8, '08', 1, '/reportTemplate/img/8.png');
INSERT INTO `report_template_page` VALUES (9, '09', 1, '/reportTemplate/img/9.png');
INSERT INTO `report_template_page` VALUES (10, '10', 1, '/reportTemplate/img/10.png');
INSERT INTO `report_template_page` VALUES (11, '11', 1, '/reportTemplate/img/11.png');
INSERT INTO `report_template_page` VALUES (12, '12', 1, '/reportTemplate/img/12.png');
INSERT INTO `report_template_page` VALUES (13, '13', 1, '/reportTemplate/img/13.png');
COMMIT;

-- ----------------------------
-- Table structure for report_template_page_field
-- ----------------------------
DROP TABLE IF EXISTS `report_template_page_field`;
CREATE TABLE `report_template_page_field` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rtp_id` bigint(20) DEFAULT NULL COMMENT '所属页面id',
  `code` varchar(255) DEFAULT NULL COMMENT '字段编码',
  `name` varchar(32) DEFAULT NULL COMMENT '字段名称',
  `rt_id` bigint(20) DEFAULT NULL COMMENT '这个是为了方便查询',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='报告模板页面字段';

-- ----------------------------
-- Records of report_template_page_field
-- ----------------------------
BEGIN;
INSERT INTO `report_template_page_field` VALUES (1, 1, 'studentNo', '学号', 1);
INSERT INTO `report_template_page_field` VALUES (2, 1, 'name', '姓名', 1);
INSERT INTO `report_template_page_field` VALUES (3, 1, 'sex', '性别', 1);
INSERT INTO `report_template_page_field` VALUES (4, 1, 'idcard', '身份证号', 1);
INSERT INTO `report_template_page_field` VALUES (5, 1, 'birthDate', '出生日期', 1);
INSERT INTO `report_template_page_field` VALUES (6, 1, 'birthProvince', '生源省份', 1);
INSERT INTO `report_template_page_field` VALUES (7, 1, 'birthCity', '生源城市', 1);
INSERT INTO `report_template_page_field` VALUES (8, 1, 'score', '高考成绩', 1);
INSERT INTO `report_template_page_field` VALUES (9, 1, 'collegeName', '学院名称', 1);
INSERT INTO `report_template_page_field` VALUES (10, 1, 'professionName', '专业名称', 1);
COMMIT;

-- ----------------------------
-- Table structure for report_visit_record
-- ----------------------------
DROP TABLE IF EXISTS `report_visit_record`;
CREATE TABLE `report_visit_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `report_id` bigint(20) DEFAULT NULL COMMENT '报告id',
  `ip_address` varchar(100) DEFAULT NULL COMMENT 'ip地址',
  PRIMARY KEY (`id`),
  KEY `report_id` (`report_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='报告访问IP记录详情';

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '权限名',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父节点id',
  `descreption` varchar(255) DEFAULT NULL COMMENT '描述',
  `permission_type` bigint(20) DEFAULT NULL COMMENT '权限类型 1:系统管理 2：业务管理',
  `status` varchar(255) DEFAULT 'NORMAL' COMMENT '权限状态  NORMAL：正常',
  `code` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='权限';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` VALUES (1, '菜单', 0, '顶级节点', 2, 'NORMAL', 'menu');
INSERT INTO `sys_permission` VALUES (2, '系统管理', 1, '系统管理', 1, 'NORMAL', 'system_manage');
INSERT INTO `sys_permission` VALUES (3, '业务管理', 1, '业务管理', 2, 'NORMAL', 'business_manage');
INSERT INTO `sys_permission` VALUES (4, '组织机构', 2, '组织机构', 1, 'NORMAL', 'organization');
INSERT INTO `sys_permission` VALUES (5, '角色管理', 2, '角色管理', 1, 'NORMAL', 'role_manage');
INSERT INTO `sys_permission` VALUES (6, '用户管理', 2, '用户管理', 1, 'NORMAL', 'user_manage');
INSERT INTO `sys_permission` VALUES (7, '迎新业务', 3, '迎新业务', 2, 'NORMAL', 'welcome_business');
INSERT INTO `sys_permission` VALUES (8, '数据采集', 7, '数据采集', 2, 'NORMAL', 'data_collect');
INSERT INTO `sys_permission` VALUES (9, '报告生成', 7, '报告生成', 2, 'NORMAL', 'report_produce');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(64) DEFAULT NULL,
  `role_type` varchar(32) DEFAULT NULL COMMENT '角色类型 ADMIN:管理员 ordinary:普通用户',
  `org_id` bigint(20) DEFAULT NULL COMMENT '组织机构id',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `default_role` tinyint(1) DEFAULT '0' COMMENT '是否为默认角色',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=890 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, '超级管理员角色', 'ADMIN', 1, '超级管理员角色', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_permission_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission_relation`;
CREATE TABLE `sys_role_permission_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8024 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission_relation
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_permission_relation` VALUES (1, 1, 1);
INSERT INTO `sys_role_permission_relation` VALUES (2, 1, 2);
INSERT INTO `sys_role_permission_relation` VALUES (3, 1, 3);
INSERT INTO `sys_role_permission_relation` VALUES (4, 1, 4);
INSERT INTO `sys_role_permission_relation` VALUES (5, 1, 5);
INSERT INTO `sys_role_permission_relation` VALUES (6, 1, 6);
INSERT INTO `sys_role_permission_relation` VALUES (7, 1, 7);
INSERT INTO `sys_role_permission_relation` VALUES (8, 1, 8);
INSERT INTO `sys_role_permission_relation` VALUES (9, 1, 9);
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(64) DEFAULT NULL,
  `login_pwd` varchar(255) DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(64) DEFAULT NULL,
  `job_number` varchar(32) DEFAULT NULL COMMENT '工号',
  `mobile_phone` varchar(11) DEFAULT NULL COMMENT '手机号码',
  `sex` int(2) DEFAULT NULL COMMENT '性别（1:男  2:女）',
  `status` varchar(32) DEFAULT 'INITIAL' COMMENT '用户状态 （INITIAL:初始，NORMAL:正常，LOCKING:锁定）',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `org_id` bigint(20) DEFAULT NULL COMMENT '组织机构id',
  `salt` varchar(255) DEFAULT NULL COMMENT '密码盐',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否已删除 0:未删除  1:已删除',
  `login_time` datetime DEFAULT NULL COMMENT '用户登录时间,最后一次登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=894 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'admin', 'd60d2f05b43fd32076319a915e6b2920', 'admin', '000001', '1111', 2, 'NORMAL', 1, 1, 'd58816aea1ddf9d4', 0, '1990-01-01');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
