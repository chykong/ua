/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50534
Source Host           : localhost:3306
Source Database       : ua_db

Target Server Type    : MYSQL
Target Server Version : 50534
File Encoding         : 65001

Date: 2017-05-13 18:39:33
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `t_sys_config`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_config`;
CREATE TABLE `t_sys_config` (
  `syskey` varchar(20) NOT NULL,
  `sysvalue` varchar(40) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `display_order` int(11) DEFAULT NULL,
  PRIMARY KEY (`syskey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_config
-- ----------------------------
INSERT INTO t_sys_config VALUES ('MIN_WITHDRAW_AMOUNT', '5', '取现最低金额', '1');

-- ----------------------------
-- Table structure for `t_sys_function`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_function`;
CREATE TABLE `t_sys_function` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module_id` int(11) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `code` varchar(30) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `display_order` smallint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ind_t_sys_opera` (`module_id`)
) ENGINE=InnoDB AUTO_INCREMENT=542 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_function
-- ----------------------------
INSERT INTO t_sys_function VALUES ('133', '4', '新增', 'SysRoleAdd', 'sys/role/add.htm', '1', '', '1');
INSERT INTO t_sys_function VALUES ('134', '4', '修改', 'SysRoleUpdate', 'sys/role/update.htm', '1', '', '2');
INSERT INTO t_sys_function VALUES ('135', '4', '删除', 'SysRoleDelete', 'sys/role/delete.htm', '1', '', '3');
INSERT INTO t_sys_function VALUES ('138', '4', '设置权限', 'SysRoleUpdateRoleModule', 'sys/role/updateRoleModule.htm', '1', '', '6');
INSERT INTO t_sys_function VALUES ('141', '49', '修改', 'SysConfigUpdate', 'sys/config/update.htm', '1', '', '3');
INSERT INTO t_sys_function VALUES ('144', '51', '导出', 'SysLogDownload', 'sys/log/downloadLog.htm', '1', '', '3');
INSERT INTO t_sys_function VALUES ('145', '3', '新增模块', 'SysModuleAdd', 'sys/module/add.htm', '1', '', '3');
INSERT INTO t_sys_function VALUES ('148', '3', '修改模块', 'SysModuleUpdate', 'sys/module/update.htm', '1', '', '4');
INSERT INTO t_sys_function VALUES ('149', '3', '删除模块', 'SysModuleDelete', 'sys/module/delete.htm', '1', '', '5');
INSERT INTO t_sys_function VALUES ('151', '3', '新增功能', 'SysFunctionAdd', 'sys/function/add.htm', '1', '', '7');
INSERT INTO t_sys_function VALUES ('152', '3', '修改功能', 'SysFunctionUpdate', 'sys/function/update.htm', '1', '', '8');
INSERT INTO t_sys_function VALUES ('153', '3', '删除功能', 'SysFunctionDelete', 'sys/function/delete.htm', '1', '', '9');
INSERT INTO t_sys_function VALUES ('154', '3', '清空缓存', 'SysModuleClearCache', 'sys/module/clearCache.htm', '1', '', '10');
INSERT INTO t_sys_function VALUES ('162', '7', '新增', 'SysUserAdd', 'sys/user/add.htm', '1', '', '3');
INSERT INTO t_sys_function VALUES ('165', '7', '修改', 'SysUserUpdate', 'sys/user/update.htm', '1', '', '4');
INSERT INTO t_sys_function VALUES ('167', '7', '密码重置', 'SysUserResetPass', 'sys/user/saveResetPass.htm', '0', '', '6');
INSERT INTO t_sys_function VALUES ('168', '7', '锁定', 'SysUserLock', 'sys/user/saveLock.htm', '0', '', '7');
INSERT INTO t_sys_function VALUES ('169', '7', '解锁', 'SysUserUnlock', 'sys/user/saveUnlock.htm', '0', '', '8');
INSERT INTO t_sys_function VALUES ('177', '55', '新增', 'BaseProductAdd', 'base/product/add.htm', '1', '', '2');
INSERT INTO t_sys_function VALUES ('178', '55', '修改', 'BaseProductUpdate', 'base/product/update.htm', '1', '', '3');
INSERT INTO t_sys_function VALUES ('179', '55', '删除', 'BaseProductDelete', 'base/product/delete.htm', '1', '', '4');
INSERT INTO t_sys_function VALUES ('340', '7', '删除', 'SysUserDelete', 'sys/user/delete.htm', '1', null, '9');
INSERT INTO t_sys_function VALUES ('457', '49', '手动返佣', 'SysConfigSaveCommission', 'sys/config/saveCommission.htm', '1', '', '4');
INSERT INTO t_sys_function VALUES ('458', '7', '设置省份权限', 'SysUserSaveUserdata', '/sys/user/saveUserdata.htm', '1', '', '7');
INSERT INTO t_sys_function VALUES ('487', '58', '新增', 'BaseCategoryAdd', 'base/category/add.htm', '1', '', '1');
INSERT INTO t_sys_function VALUES ('488', '58', '修改', 'BaseCategoryUpdate', 'base/category/update.htm', '1', '', '2');
INSERT INTO t_sys_function VALUES ('489', '58', '删除', 'BaseCategoryDelete', 'base/category/delete.htm', '1', '', '3');
INSERT INTO t_sys_function VALUES ('532', '55', '新增规格', 'BaseProductSpecAdd', 'base/product/addSpec.htm', '1', '', '4');
INSERT INTO t_sys_function VALUES ('533', '55', '修改规格', 'BaseProductSpecUpdate', 'base/product/updateSpec.htm', '1', '', '5');
INSERT INTO t_sys_function VALUES ('534', '55', '设置价格', 'BaseProductPriceSet', 'base/productprice/setPrice.htm', '1', '', '6');
INSERT INTO t_sys_function VALUES ('535', '55', '删除价格', 'BaseProductPriceDelete', 'base/productprice/deletePrice.htm', '1', '', '7');

-- ----------------------------
-- Table structure for `t_sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_log`;
CREATE TABLE `t_sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `opera_date` datetime DEFAULT NULL,
  `opera_ip` varchar(20) DEFAULT NULL,
  `module_name` varchar(20) DEFAULT NULL,
  `opera_name` varchar(20) DEFAULT NULL,
  `opera_type` tinyint(4) DEFAULT NULL,
  `opera_url` varchar(100) DEFAULT NULL,
  `opera_params` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ind_t_sys_log` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2933 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_log
-- ----------------------------
INSERT INTO t_sys_log VALUES ('2861', '1', '2017-05-13 15:45:31', '0:0:0:0:0:0:0:1', '模块管理', '修改模块', '1', 'sys/module/update.htm', 'id=2&backUrl=http%3A%2F%2Flocalhost%3A8080%2Fua%2Fsys%2Fmodule%2Findex.htm%3F&name=系统管理&parent_id=1&code=sys&url=#&target=main&iconImg=fa-cogs&display_order=8&');
INSERT INTO t_sys_log VALUES ('2862', '1', '2017-05-13 15:45:50', '0:0:0:0:0:0:0:1', '模块管理', '删除模块', '1', 'sys/module/delete.htm', 'id=137&backUrl=http://localhost:8080/ua/sys/module/index.htm?&');
INSERT INTO t_sys_log VALUES ('2863', '1', '2017-05-13 15:46:37', '0:0:0:0:0:0:0:1', '模块管理', '修改模块', '1', 'sys/module/update.htm', 'id=51&backUrl=http%3A%2F%2Flocalhost%3A8080%2Fua%2Fsys%2Fmodule%2Findex.htm%3F&name=操作日志&parent_id=2&code=SysLog&url=sys/log/index.htm&target=main&iconImg=61555&display_order=5&');
INSERT INTO t_sys_log VALUES ('2864', '1', '2017-05-13 15:47:09', '0:0:0:0:0:0:0:1', '模块管理', '新增模块', '1', 'sys/module/add.htm', 'backUrl=http%3A%2F%2Flocalhost%3A8080%2Fua%2Fsys%2Fmodule%2Findex.htm%3F&name=系统监控&parent_id=2&code=SysMonitor&url=druid&target=main&iconImg=&display_order=6&');
INSERT INTO t_sys_log VALUES ('2865', '1', '2017-05-13 15:47:40', '0:0:0:0:0:0:0:1', '模块管理', '修改模块', '1', 'sys/module/update.htm', 'id=147&backUrl=http%3A%2F%2Flocalhost%3A8080%2Fua%2Fsys%2Fmodule%2Findex.htm%3F&name=系统监控&parent_id=2&code=SysMonitor&url=druid&target=blank&iconImg=&display_order=6&');
INSERT INTO t_sys_log VALUES ('2866', '1', '2017-05-13 15:47:58', '0:0:0:0:0:0:0:1', '模块管理', '删除模块', '1', 'sys/module/delete.htm', 'id=97&backUrl=http://localhost:8080/ua/sys/module/index.htm?&');
INSERT INTO t_sys_log VALUES ('2867', '1', '2017-05-13 15:48:02', '0:0:0:0:0:0:0:1', '模块管理', '删除模块', '1', 'sys/module/delete.htm', 'id=117&backUrl=http://localhost:8080/ua/sys/module/index.htm?&');
INSERT INTO t_sys_log VALUES ('2868', '1', '2017-05-13 15:48:05', '0:0:0:0:0:0:0:1', '模块管理', '删除模块', '1', 'sys/module/delete.htm', 'id=124&backUrl=http://localhost:8080/ua/sys/module/index.htm?&');
INSERT INTO t_sys_log VALUES ('2869', '1', '2017-05-13 15:48:10', '0:0:0:0:0:0:0:1', '模块管理', '删除模块', '1', 'sys/module/delete.htm', 'id=69&backUrl=http://localhost:8080/ua/sys/module/index.htm?&');
INSERT INTO t_sys_log VALUES ('2870', '1', '2017-05-13 15:48:13', '0:0:0:0:0:0:0:1', '模块管理', '删除模块', '1', 'sys/module/delete.htm', 'id=126&backUrl=http://localhost:8080/ua/sys/module/index.htm?&');
INSERT INTO t_sys_log VALUES ('2871', '1', '2017-05-13 15:48:18', '0:0:0:0:0:0:0:1', '模块管理', '删除模块', '1', 'sys/module/delete.htm', 'id=119&backUrl=http://localhost:8080/ua/sys/module/index.htm?&');
INSERT INTO t_sys_log VALUES ('2872', '1', '2017-05-13 15:48:21', '0:0:0:0:0:0:0:1', '模块管理', '删除模块', '1', 'sys/module/delete.htm', 'id=120&backUrl=http://localhost:8080/ua/sys/module/index.htm?&');
INSERT INTO t_sys_log VALUES ('2873', '1', '2017-05-13 15:48:26', '0:0:0:0:0:0:0:1', '模块管理', '删除模块', '1', 'sys/module/delete.htm', 'id=59&backUrl=http://localhost:8080/ua/sys/module/index.htm?&');
INSERT INTO t_sys_log VALUES ('2874', '1', '2017-05-13 15:48:30', '0:0:0:0:0:0:0:1', '模块管理', '删除模块', '1', 'sys/module/delete.htm', 'id=140&backUrl=http://localhost:8080/ua/sys/module/index.htm?&');
INSERT INTO t_sys_log VALUES ('2875', '1', '2017-05-13 15:48:33', '0:0:0:0:0:0:0:1', '模块管理', '删除模块', '1', 'sys/module/delete.htm', 'id=139&backUrl=http://localhost:8080/ua/sys/module/index.htm?&');
INSERT INTO t_sys_log VALUES ('2876', '1', '2017-05-13 15:48:37', '0:0:0:0:0:0:0:1', '模块管理', '删除模块', '1', 'sys/module/delete.htm', 'id=60&backUrl=http://localhost:8080/ua/sys/module/index.htm?&');
INSERT INTO t_sys_log VALUES ('2877', '1', '2017-05-13 15:48:41', '0:0:0:0:0:0:0:1', '模块管理', '删除模块', '1', 'sys/module/delete.htm', 'id=121&backUrl=http://localhost:8080/ua/sys/module/index.htm?&');
INSERT INTO t_sys_log VALUES ('2878', '1', '2017-05-13 15:48:46', '0:0:0:0:0:0:0:1', '模块管理', '删除模块', '1', 'sys/module/delete.htm', 'id=118&backUrl=http://localhost:8080/ua/sys/module/index.htm?&');
INSERT INTO t_sys_log VALUES ('2879', '1', '2017-05-13 15:48:50', '0:0:0:0:0:0:0:1', '模块管理', '删除模块', '1', 'sys/module/delete.htm', 'id=76&backUrl=http://localhost:8080/ua/sys/module/index.htm?&');
INSERT INTO t_sys_log VALUES ('2880', '1', '2017-05-13 15:48:54', '0:0:0:0:0:0:0:1', '模块管理', '删除模块', '1', 'sys/module/delete.htm', 'id=56&backUrl=http://localhost:8080/ua/sys/module/index.htm?&');
INSERT INTO t_sys_log VALUES ('2881', '1', '2017-05-13 15:48:57', '0:0:0:0:0:0:0:1', '模块管理', '删除模块', '1', 'sys/module/delete.htm', 'id=57&backUrl=http://localhost:8080/ua/sys/module/index.htm?&');
INSERT INTO t_sys_log VALUES ('2882', '1', '2017-05-13 15:49:01', '0:0:0:0:0:0:0:1', '模块管理', '删除模块', '1', 'sys/module/delete.htm', 'id=21&backUrl=http://localhost:8080/ua/sys/module/index.htm?&');
INSERT INTO t_sys_log VALUES ('2883', '1', '2017-05-13 15:49:05', '0:0:0:0:0:0:0:1', '模块管理', '删除模块', '1', 'sys/module/delete.htm', 'id=141&backUrl=http://localhost:8080/ua/sys/module/index.htm?&');
INSERT INTO t_sys_log VALUES ('2884', '1', '2017-05-13 15:49:15', '0:0:0:0:0:0:0:1', '模块管理', '删除模块', '1', 'sys/module/delete.htm', 'id=101&backUrl=http://localhost:8080/ua/sys/module/index.htm?&');
INSERT INTO t_sys_log VALUES ('2885', '1', '2017-05-13 15:49:18', '0:0:0:0:0:0:0:1', '模块管理', '删除模块', '1', 'sys/module/delete.htm', 'id=135&backUrl=http://localhost:8080/ua/sys/module/index.htm?&');
INSERT INTO t_sys_log VALUES ('2886', '1', '2017-05-13 15:49:21', '0:0:0:0:0:0:0:1', '模块管理', '删除模块', '1', 'sys/module/delete.htm', 'id=144&backUrl=http://localhost:8080/ua/sys/module/index.htm?&');
INSERT INTO t_sys_log VALUES ('2887', '1', '2017-05-13 15:49:24', '0:0:0:0:0:0:0:1', '模块管理', '删除模块', '1', 'sys/module/delete.htm', 'id=103&backUrl=http://localhost:8080/ua/sys/module/index.htm?&');
INSERT INTO t_sys_log VALUES ('2888', '1', '2017-05-13 15:49:27', '0:0:0:0:0:0:0:1', '模块管理', '删除模块', '1', 'sys/module/delete.htm', 'id=142&backUrl=http://localhost:8080/ua/sys/module/index.htm?&');
INSERT INTO t_sys_log VALUES ('2889', '1', '2017-05-13 15:49:30', '0:0:0:0:0:0:0:1', '模块管理', '删除模块', '1', 'sys/module/delete.htm', 'id=146&backUrl=http://localhost:8080/ua/sys/module/index.htm?&');
INSERT INTO t_sys_log VALUES ('2890', '1', '2017-05-13 15:49:34', '0:0:0:0:0:0:0:1', '模块管理', '删除模块', '1', 'sys/module/delete.htm', 'id=136&backUrl=http://localhost:8080/ua/sys/module/index.htm?&');
INSERT INTO t_sys_log VALUES ('2891', '1', '2017-05-13 15:49:38', '0:0:0:0:0:0:0:1', '模块管理', '删除模块', '1', 'sys/module/delete.htm', 'id=32&backUrl=http://localhost:8080/ua/sys/module/index.htm?&');
INSERT INTO t_sys_log VALUES ('2892', '1', '2017-05-13 15:49:49', '0:0:0:0:0:0:0:1', '模块管理', '修改模块', '1', 'sys/module/update.htm', 'id=55&backUrl=http%3A%2F%2Flocalhost%3A8080%2Fua%2Fsys%2Fmodule%2Findex.htm%3F&name=商品信息&parent_id=138&code=BaseProduct&url=base/product/index.htm&target=main&iconImg=61558&display_order=1&');
INSERT INTO t_sys_log VALUES ('2893', '1', '2017-05-13 15:55:03', '0:0:0:0:0:0:0:1', '模块管理', '新增模块', '1', 'sys/module/add.htm', 'backUrl=%2Fsys%2Fmodule%2Findex.htm%3F&name=123213&parent_id=138&parent_name=商品管理&code=3123&url=3213&target=main&iconImg=&display_order=312&');
INSERT INTO t_sys_log VALUES ('2894', '1', '2017-05-13 16:00:29', '0:0:0:0:0:0:0:1', '模块管理', '修改模块', '1', 'sys/module/update.htm', 'id=55&backUrl=http%3A%2F%2Flocalhost%3A8080%2Fua%2Fsys%2Fmodule%2Findex.htm%3F&name=商品信息&parent_id=138&parent_name=商品管理&code=BaseProduct&url=base/product/index.htm&target=main&iconImg=61558&display_order=1&');
INSERT INTO t_sys_log VALUES ('2895', '1', '2017-05-13 16:00:52', '0:0:0:0:0:0:0:1', '模块管理', '修改模块', '1', 'sys/module/update.htm', 'id=55&backUrl=%2Fsys%2Fmodule%2Findex.htm%3F&name=商品信息&parent_id=138&parent_name=商品管理&code=BaseProduct&url=base/product/index.htm&target=main&iconImg=61558&display_order=1&');
INSERT INTO t_sys_log VALUES ('2896', '1', '2017-05-13 16:02:36', '0:0:0:0:0:0:0:1', '模块管理', '新增功能', '1', 'sys/function/add.htm', 'backUrl=http%3A%2F%2Flocalhost%3A8080%2Fua%2Fsys%2Ffunction%2Findex.htm%3Fmodule_id%3D148&module_id=148&name=3123&code=3123&url=3123&type=0&description=3123&display_order=312&');
INSERT INTO t_sys_log VALUES ('2897', '1', '2017-05-13 16:02:59', '0:0:0:0:0:0:0:1', '模块管理', '新增功能', '1', 'sys/function/add.htm', 'backUrl=http%3A%2F%2Flocalhost%3A8080%2Fua%2Fsys%2Ffunction%2Findex.htm%3Fmodule_id%3D148&module_id=148&name=3123&code=3123&url=3123&type=0&description=3123&display_order=312&');
INSERT INTO t_sys_log VALUES ('2898', '1', '2017-05-13 16:03:34', '0:0:0:0:0:0:0:1', '模块管理', '修改功能', '1', 'sys/function/update.htm', 'id=541&backUrl=http%3A%2F%2Flocalhost%3A8080%2Fua%2Fsys%2Ffunction%2Findex.htm%3Fmodule_id%3D148&module_id=148&name=3123&code=3123&url=3123&type=0&description=3123&display_order=312&');
INSERT INTO t_sys_log VALUES ('2899', '1', '2017-05-13 16:04:32', '0:0:0:0:0:0:0:1', '模块管理', '修改功能', '1', 'sys/function/update.htm', 'id=541&backUrl=http%3A%2F%2Flocalhost%3A8080%2Fua%2Fsys%2Ffunction%2Findex.htm%3Fmodule_id%3D148&module_id=148&name=3123&code=3123&url=3123&type=0&description=3123&display_order=312&');
INSERT INTO t_sys_log VALUES ('2900', '1', '2017-05-13 16:04:39', '0:0:0:0:0:0:0:1', '模块管理', '修改功能', '1', 'sys/function/update.htm', 'id=541&backUrl=%2Fsys%2Ffunction%2Findex.htm%3Fmodule_id%3D148&module_id=148&name=3123&code=3123&url=3123&type=0&description=3123&display_order=312&');
INSERT INTO t_sys_log VALUES ('2901', '1', '2017-05-13 16:05:51', '0:0:0:0:0:0:0:1', '模块管理', '修改功能', '1', 'sys/function/update.htm', 'id=541&backUrl=%2Fsys%2Ffunction%2Findex.htm%3Fmodule_id%3D148&module_id=148&name=3123&code=3123&url=3123&type=0&description=3123&display_order=312&');
INSERT INTO t_sys_log VALUES ('2902', '1', '2017-05-13 16:05:56', '0:0:0:0:0:0:0:1', '模块管理', '修改功能', '1', 'sys/function/update.htm', 'id=541&backUrl=%2Fsys%2Ffunction%2Findex.htm%3Fmodule_id%3D148&module_id=148&name=3123&code=3123&url=3123&type=0&description=3123&display_order=312&');
INSERT INTO t_sys_log VALUES ('2903', '1', '2017-05-13 16:06:30', '0:0:0:0:0:0:0:1', '模块管理', '修改功能', '1', 'sys/function/update.htm', 'id=541&backUrl=%2Fsys%2Ffunction%2Findex.htm%3Fmodule_id%3D148&module_id=148&name=3123&code=3123&url=3123&type=0&description=3123&display_order=312&');
INSERT INTO t_sys_log VALUES ('2904', '1', '2017-05-13 16:06:42', '0:0:0:0:0:0:0:1', '模块管理', '删除模块', '1', 'sys/module/delete.htm', 'id=148&backUrl=/sys/module/index.htm?&');
INSERT INTO t_sys_log VALUES ('2905', '1', '2017-05-13 16:08:18', '0:0:0:0:0:0:0:1', '模块管理', '修改模块', '1', 'sys/module/update.htm', 'id=138&backUrl=%2Fsys%2Fmodule%2Findex.htm%3F&name=商品管理&parent_id=1&parent_name=系统模块列表&code=BaseProduct&url=#&target=main&iconImg=glyphicon glyphicon-th-large&display_order=1&');
INSERT INTO t_sys_log VALUES ('2906', '1', '2017-05-13 16:08:25', '0:0:0:0:0:0:0:1', '模块管理', '修改模块', '1', 'sys/module/update.htm', 'id=138&backUrl=%2Fsys%2Fmodule%2Findex.htm%3F&name=商品管理&parent_id=1&parent_name=系统模块列表&code=BaseProduct&url=#&target=main&iconImg=glyphicon-th-large&display_order=1&');
INSERT INTO t_sys_log VALUES ('2907', '1', '2017-05-13 16:08:37', '0:0:0:0:0:0:0:1', '模块管理', '修改模块', '1', 'sys/module/update.htm', 'id=138&backUrl=%2Fsys%2Fmodule%2Findex.htm%3F&name=商品管理&parent_id=1&parent_name=系统模块列表&code=BaseProduct&url=#&target=main&iconImg=fa-product-hunt&display_order=1&');
INSERT INTO t_sys_log VALUES ('2908', '1', '2017-05-13 16:09:28', '0:0:0:0:0:0:0:1', '模块管理', '删除模块', '1', 'sys/module/delete.htm', 'id=145&backUrl=/sys/module/index.htm?&');
INSERT INTO t_sys_log VALUES ('2909', '1', '2017-05-13 16:50:02', '0:0:0:0:0:0:0:1', '角色管理', '删除', '1', 'sys/role/delete.htm', 'id=37&backUrl=http://localhost:8080/ua/sys/role/index.htm?&');
INSERT INTO t_sys_log VALUES ('2910', '1', '2017-05-13 16:51:17', '0:0:0:0:0:0:0:1', '角色管理', '删除', '1', 'sys/role/delete.htm', 'id=27&backUrl=/sys/role/index.htm?&');
INSERT INTO t_sys_log VALUES ('2911', '1', '2017-05-13 16:51:22', '0:0:0:0:0:0:0:1', '角色管理', '删除', '1', 'sys/role/delete.htm', 'id=28&backUrl=/sys/role/index.htm?&');
INSERT INTO t_sys_log VALUES ('2912', '1', '2017-05-13 16:51:26', '0:0:0:0:0:0:0:1', '角色管理', '删除', '1', 'sys/role/delete.htm', 'id=29&backUrl=/sys/role/index.htm?&');
INSERT INTO t_sys_log VALUES ('2913', '1', '2017-05-13 16:51:29', '0:0:0:0:0:0:0:1', '角色管理', '删除', '1', 'sys/role/delete.htm', 'id=30&backUrl=/sys/role/index.htm?&');
INSERT INTO t_sys_log VALUES ('2914', '1', '2017-05-13 16:51:32', '0:0:0:0:0:0:0:1', '角色管理', '删除', '1', 'sys/role/delete.htm', 'id=31&backUrl=/sys/role/index.htm?&');
INSERT INTO t_sys_log VALUES ('2915', '1', '2017-05-13 16:51:35', '0:0:0:0:0:0:0:1', '角色管理', '删除', '1', 'sys/role/delete.htm', 'id=35&backUrl=/sys/role/index.htm?&');
INSERT INTO t_sys_log VALUES ('2916', '1', '2017-05-13 16:51:38', '0:0:0:0:0:0:0:1', '角色管理', '删除', '1', 'sys/role/delete.htm', 'id=36&backUrl=/sys/role/index.htm?&');
INSERT INTO t_sys_log VALUES ('2917', '1', '2017-05-13 16:51:42', '0:0:0:0:0:0:0:1', '角色管理', '删除', '1', 'sys/role/delete.htm', 'id=38&backUrl=/sys/role/index.htm?&');
INSERT INTO t_sys_log VALUES ('2918', '1', '2017-05-13 16:51:45', '0:0:0:0:0:0:0:1', '角色管理', '删除', '1', 'sys/role/delete.htm', 'id=2&backUrl=/sys/role/index.htm?&');
INSERT INTO t_sys_log VALUES ('2919', '1', '2017-05-13 16:51:57', '0:0:0:0:0:0:0:1', '角色管理', '新增', '1', 'sys/role/add.htm', 'backUrl=%2Fsys%2Frole%2Findex.htm%3F&moduleArr=138@@55@@58@@2@@3@@4@@7@@49@@51@@147@@&functionArr=177@@178@@179@@532@@533@@534@@535@@487@@488@@489@@145@@148@@149@@151@@152@@153@@154@@133@@134@@135@@138@@162@@165@@340@@458@@141@@457@@144@@&name=测试角色&description=测试&module=138&function=177&');
INSERT INTO t_sys_log VALUES ('2920', '1', '2017-05-13 16:53:24', '0:0:0:0:0:0:0:1', '角色管理', '新增', '1', 'sys/role/add.htm', 'backUrl=%2Fsys%2Frole%2Findex.htm%3F&moduleArr=138@@55@@58@@2@@3@@4@@7@@49@@51@@147@@&functionArr=177@@178@@179@@532@@533@@534@@535@@487@@488@@489@@145@@148@@149@@151@@152@@153@@154@@133@@134@@135@@138@@162@@165@@340@@458@@141@@457@@144@@&name=测试角色&description=测试&module=138&function=177&');
INSERT INTO t_sys_log VALUES ('2921', '1', '2017-05-13 17:06:03', '0:0:0:0:0:0:0:1', '角色管理', '新增', '1', 'sys/role/add.htm', 'backUrl=%2Fsys%2Frole%2Findex.htm%3F&moduleArr=138@@55@@58@@2@@3@@4@@7@@49@@51@@147@@&functionArr=177@@178@@179@@532@@533@@534@@535@@487@@488@@489@@145@@148@@149@@151@@152@@153@@154@@133@@134@@135@@138@@162@@165@@340@@458@@141@@457@@144@@&name=测试角色&description=测试&module=138&function=177&');
INSERT INTO t_sys_log VALUES ('2922', '1', '2017-05-13 17:06:03', '0:0:0:0:0:0:0:1', '角色管理', '新增', '1', 'sys/role/add.htm', 'backUrl=%2Fsys%2Frole%2Findex.htm%3F&moduleArr=138@@55@@58@@2@@3@@4@@7@@49@@51@@147@@&functionArr=177@@178@@179@@532@@533@@534@@535@@487@@488@@489@@145@@148@@149@@151@@152@@153@@154@@133@@134@@135@@138@@162@@165@@340@@458@@141@@457@@144@@&name=测试角色&description=测试&module=138&function=177&');
INSERT INTO t_sys_log VALUES ('2923', '1', '2017-05-13 17:06:03', '0:0:0:0:0:0:0:1', '角色管理', '新增', '1', 'sys/role/add.htm', 'backUrl=%2Fsys%2Frole%2Findex.htm%3F&moduleArr=138@@55@@58@@2@@3@@4@@7@@49@@51@@147@@&functionArr=177@@178@@179@@532@@533@@534@@535@@487@@488@@489@@145@@148@@149@@151@@152@@153@@154@@133@@134@@135@@138@@162@@165@@340@@458@@141@@457@@144@@&name=测试角色&description=测试&module=138&function=177&');
INSERT INTO t_sys_log VALUES ('2924', '1', '2017-05-13 17:06:39', '0:0:0:0:0:0:0:1', '角色管理', '新增', '1', 'sys/role/add.htm', 'backUrl=%2Fsys%2Frole%2Findex.htm%3F&moduleArr=138@@55@@58@@2@@3@@4@@7@@49@@51@@147@@&functionArr=177@@178@@179@@532@@533@@534@@535@@487@@488@@489@@145@@148@@149@@151@@152@@153@@154@@133@@134@@135@@138@@162@@165@@340@@458@@141@@457@@144@@&name=测试角色&description=测试&module=138&function=177&');
INSERT INTO t_sys_log VALUES ('2925', '1', '2017-05-13 17:07:41', '0:0:0:0:0:0:0:1', '角色管理', '修改', '1', 'sys/role/update.htm', 'backUrl=%2Fsys%2Frole%2Findex.htm%3F&id=39&moduleArr=138@@55@@58@@2@@3@@4@@7@@49@@51@@147@@&functionArr=177@@178@@179@@532@@533@@534@@535@@487@@488@@489@@145@@148@@149@@151@@152@@153@@154@@133@@134@@135@@138@@162@@165@@340@@458@@141@@457@@144@@&name=测试角色&description=测试&module=138&function=177&');
INSERT INTO t_sys_log VALUES ('2926', '1', '2017-05-13 17:07:49', '0:0:0:0:0:0:0:1', '角色管理', '新增', '1', 'sys/role/add.htm', 'backUrl=%2Fsys%2Frole%2Findex.htm%3F&moduleArr=138@@55@@58@@&functionArr=177@@178@@179@@532@@533@@534@@535@@487@@488@@489@@&name=312&description=321&module=138&function=177&');
INSERT INTO t_sys_log VALUES ('2927', '1', '2017-05-13 17:08:25', '0:0:0:0:0:0:0:1', '角色管理', '删除', '1', 'sys/role/delete.htm', 'id=43&backUrl=/sys/role/index.htm?&');
INSERT INTO t_sys_log VALUES ('2928', '1', '2017-05-13 17:32:18', '0:0:0:0:0:0:0:1', '角色管理', '修改', '1', 'sys/role/update.htm', 'backUrl=%2Fsys%2Frole%2Findex.htm%3F&id=1&moduleArr=138@@55@@58@@2@@3@@4@@7@@49@@51@@&functionArr=177@@178@@179@@532@@533@@534@@535@@487@@488@@489@@145@@148@@149@@151@@152@@153@@154@@133@@134@@135@@138@@162@@165@@340@@458@@141@@457@@144@@&name=系统管理员&description=系统管理员&module=138&function=177&');
INSERT INTO t_sys_log VALUES ('2929', '1', '2017-05-13 17:34:01', '0:0:0:0:0:0:0:1', '用户管理', '新增', '1', 'sys/user/add.htm', 'backUrl=%2Fsys%2Fuser%2Findex.htm%3F&username=test&realname=测试用户&role_id=39&');
INSERT INTO t_sys_log VALUES ('2930', '1', '2017-05-13 17:34:11', '0:0:0:0:0:0:0:1', '角色管理', '删除', '1', 'sys/role/delete.htm', 'id=39&backUrl=/sys/role/index.htm?&');
INSERT INTO t_sys_log VALUES ('2931', '1', '2017-05-13 17:34:18', '0:0:0:0:0:0:0:1', '用户管理', '修改', '1', 'sys/user/update.htm', 'backUrl=%2Fsys%2Fuser%2Findex.htm%3F&id=19&username=test&realname=测试用户&role_id=41&');
INSERT INTO t_sys_log VALUES ('2932', '1', '2017-05-13 17:35:31', '0:0:0:0:0:0:0:1', '角色管理', '修改', '1', 'sys/role/update.htm', 'backUrl=%2Fsys%2Frole%2Findex.htm%3F&id=1&moduleArr=138@@55@@58@@2@@3@@4@@7@@49@@51@@147@@&functionArr=177@@178@@179@@532@@533@@534@@535@@487@@488@@489@@145@@148@@149@@151@@152@@153@@154@@133@@134@@135@@138@@162@@165@@340@@458@@141@@457@@144@@&name=系统管理员&description=系统管理员&module=138&function=177&');

-- ----------------------------
-- Table structure for `t_sys_module`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_module`;
CREATE TABLE `t_sys_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `code` varchar(30) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `target` varchar(20) DEFAULT NULL,
  `iconImg` varchar(20) DEFAULT NULL,
  `display_order` smallint(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=149 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_module
-- ----------------------------
INSERT INTO t_sys_module VALUES ('1', '系统模块列表', 'root', '0', '#', 'main', '0xf022', '1');
INSERT INTO t_sys_module VALUES ('2', '系统管理', 'sys', '1', '#', 'main', 'fa-cogs', '8');
INSERT INTO t_sys_module VALUES ('3', '模块管理', 'SysModule', '2', 'sys/module/index.htm', 'main', '61548', '1');
INSERT INTO t_sys_module VALUES ('4', '角色管理', 'SysRole', '2', 'sys/role/index.htm', 'main', '61632', '2');
INSERT INTO t_sys_module VALUES ('7', '用户管理', 'SysUser', '2', 'sys/user/index.htm', 'main', 'fa-user', '3');
INSERT INTO t_sys_module VALUES ('40', '统计查询', 'SP', '1', '#', 'main', 'fa-bar-chart', '6');
INSERT INTO t_sys_module VALUES ('49', '基本设置', 'SysConfig', '2', 'sys/config/index.htm', 'main', '61459', '4');
INSERT INTO t_sys_module VALUES ('51', '操作日志', 'SysLog', '2', 'sys/log/index.htm', 'main', '61555', '5');
INSERT INTO t_sys_module VALUES ('55', '商品信息', 'BaseProduct', '138', 'base/product/index.htm', 'main', '61558', '1');
INSERT INTO t_sys_module VALUES ('58', '商品分类', 'BaseCategory', '138', 'base/category/index.htm', 'main', '61746', '2');
INSERT INTO t_sys_module VALUES ('138', '商品管理', 'BaseProduct', '1', '#', 'main', 'fa-product-hunt', '1');
INSERT INTO t_sys_module VALUES ('143', '商品统计', 'StatProduct', '40', 'stat/product/index.htm', 'main', '', '2');
INSERT INTO t_sys_module VALUES ('147', '系统监控', 'SysMonitor', '2', 'druid', 'blank', '', '6');

-- ----------------------------
-- Table structure for `t_sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `display_order` smallint(6) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `is_delete` tinyint(4) DEFAULT NULL,
  `create_person` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO t_sys_role VALUES ('1', '系统管理员', '系统管理员', '99', '2015-06-01 15:13:11', '0', null);
INSERT INTO t_sys_role VALUES ('41', '测试角色', '测试', null, '2017-05-13 17:06:37', '1', '管理员');

-- ----------------------------
-- Table structure for `t_sys_rolefunction`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_rolefunction`;
CREATE TABLE `t_sys_rolefunction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `function_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ind_t_sys_rolefunction` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31860 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_rolefunction
-- ----------------------------
INSERT INTO t_sys_rolefunction VALUES ('31832', '1', '177');
INSERT INTO t_sys_rolefunction VALUES ('31833', '1', '178');
INSERT INTO t_sys_rolefunction VALUES ('31834', '1', '179');
INSERT INTO t_sys_rolefunction VALUES ('31835', '1', '532');
INSERT INTO t_sys_rolefunction VALUES ('31836', '1', '533');
INSERT INTO t_sys_rolefunction VALUES ('31837', '1', '534');
INSERT INTO t_sys_rolefunction VALUES ('31838', '1', '535');
INSERT INTO t_sys_rolefunction VALUES ('31839', '1', '487');
INSERT INTO t_sys_rolefunction VALUES ('31840', '1', '488');
INSERT INTO t_sys_rolefunction VALUES ('31841', '1', '489');
INSERT INTO t_sys_rolefunction VALUES ('31842', '1', '145');
INSERT INTO t_sys_rolefunction VALUES ('31843', '1', '148');
INSERT INTO t_sys_rolefunction VALUES ('31844', '1', '149');
INSERT INTO t_sys_rolefunction VALUES ('31845', '1', '151');
INSERT INTO t_sys_rolefunction VALUES ('31846', '1', '152');
INSERT INTO t_sys_rolefunction VALUES ('31847', '1', '153');
INSERT INTO t_sys_rolefunction VALUES ('31848', '1', '154');
INSERT INTO t_sys_rolefunction VALUES ('31849', '1', '133');
INSERT INTO t_sys_rolefunction VALUES ('31850', '1', '134');
INSERT INTO t_sys_rolefunction VALUES ('31851', '1', '135');
INSERT INTO t_sys_rolefunction VALUES ('31852', '1', '138');
INSERT INTO t_sys_rolefunction VALUES ('31853', '1', '162');
INSERT INTO t_sys_rolefunction VALUES ('31854', '1', '165');
INSERT INTO t_sys_rolefunction VALUES ('31855', '1', '340');
INSERT INTO t_sys_rolefunction VALUES ('31856', '1', '458');
INSERT INTO t_sys_rolefunction VALUES ('31857', '1', '141');
INSERT INTO t_sys_rolefunction VALUES ('31858', '1', '457');
INSERT INTO t_sys_rolefunction VALUES ('31859', '1', '144');

-- ----------------------------
-- Table structure for `t_sys_rolemodule`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_rolemodule`;
CREATE TABLE `t_sys_rolemodule` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `module_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ind_t_sys_rolemodule` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16343 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_rolemodule
-- ----------------------------
INSERT INTO t_sys_rolemodule VALUES ('16333', '1', '138');
INSERT INTO t_sys_rolemodule VALUES ('16334', '1', '55');
INSERT INTO t_sys_rolemodule VALUES ('16335', '1', '58');
INSERT INTO t_sys_rolemodule VALUES ('16336', '1', '2');
INSERT INTO t_sys_rolemodule VALUES ('16337', '1', '3');
INSERT INTO t_sys_rolemodule VALUES ('16338', '1', '4');
INSERT INTO t_sys_rolemodule VALUES ('16339', '1', '7');
INSERT INTO t_sys_rolemodule VALUES ('16340', '1', '49');
INSERT INTO t_sys_rolemodule VALUES ('16341', '1', '51');
INSERT INTO t_sys_rolemodule VALUES ('16342', '1', '147');

-- ----------------------------
-- Table structure for `t_sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(40) DEFAULT NULL,
  `randomcode` varchar(6) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `realname` varchar(20) DEFAULT NULL,
  `department_id` int(10) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `create_person` varchar(20) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ind_t_sys_user_2` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO t_sys_user VALUES ('1', 'admin', '540168fc54ddeefc97b6af20bac6d457', '170960', '1', '管理员', '1', null, null, '1', '1');
INSERT INTO t_sys_user VALUES ('19', 'test', 'c78090252e665ca155e1aa8af4ed1d3d', '530082', '1', '测试用户', null, '2017-05-13 17:34:01', '管理员', '41', '1');

-- ----------------------------
-- Table structure for `t_sys_userlogin`
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_userlogin`;
CREATE TABLE `t_sys_userlogin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `login_date` datetime DEFAULT NULL,
  `login_ip` varchar(20) DEFAULT NULL,
  `terminal` varchar(20) DEFAULT NULL,
  `explorerType` varchar(40) DEFAULT NULL,
  `explorerVersion` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ind_t_sys_userlogin` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1150 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_userlogin
-- ----------------------------
