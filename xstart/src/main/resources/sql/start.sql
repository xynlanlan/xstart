/*Table structure for table `sys_resources` */

CREATE TABLE `sys_resources` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '菜单名',
  `menu_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '菜单路径',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标class',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父id',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `disabled` tinyint(1) DEFAULT NULL COMMENT '是否禁用(0:启用 1:禁用)',
  `is_del` tinyint(1) DEFAULT NULL COMMENT '是否删除(0: 未删除 1:已删除)',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='菜单资源表';

/*Data for the table `sys_resources` */

insert  into `sys_resources`(`id`,`menu_name`,`menu_path`,`icon`,`parent_id`,`sort`,`disabled`,`is_del`,`create_by`,`create_time`,`update_by`,`update_time`) values (1,'系统字典','#','fa-desktop',0,1,0,0,1,'2018-05-14 17:23:26',1,'2018-05-14 17:23:26'),(2,'字典类型','role.do','fa-caret-right',1,2,0,0,1,'2018-05-14 17:23:26',1,'2018-05-14 17:23:26'),(4,'字典名称','happuser/listUsers.do','fa-caret-right',1,4,0,0,1,'2018-05-14 17:23:26',1,'2018-05-14 17:23:26'),(5,'系统用户','user/listUsers.do','fa-caret-right',1,3,0,0,1,'2018-05-14 17:23:26',1,'2018-05-14 17:23:26'),(6,'菜单管理','#','fa-list',0,2,0,0,1,'2018-05-14 17:23:26',1,'2018-05-14 17:23:26'),(7,'菜单维护','ez/system/sysmenu/list.do','fa-caret-right',6,1,0,0,1,'2018-05-14 17:23:26',1,'2018-05-14 17:23:26'),(8,'性能监控','druid/index.html','fa-caret-right',9,1,0,0,1,'2018-05-14 17:23:26',1,'2018-05-14 17:23:26'),(9,'系统工具','#','fa-th',0,3,0,0,1,'2018-05-14 17:23:26',1,'2018-05-14 17:23:26'),(10,'接口测试','tool/interfaceTest.do','fa-caret-right',9,2,0,0,1,'2018-05-14 17:23:26',1,'2018-05-14 17:23:26'),(11,'发送邮件','tool/goSendEmail.do','fa-caret-right',9,3,0,0,1,'2018-05-14 17:23:26',1,'2018-05-14 17:23:26'),(12,'置二维码','tool/goTwoDimensionCode.do','fa-caret-right',9,4,0,0,1,'2018-05-14 17:23:26',1,'2018-05-14 17:23:26'),(13,'多级别树','tool/ztree.do','fa-caret-right',9,5,0,0,1,'2018-05-14 17:23:26',1,'2018-05-14 17:23:26'),(14,'地图工具','tool/map.do','fa-caret-right',9,6,0,0,1,'2018-05-14 17:23:26',1,'2018-05-14 17:23:26'),(15,'微信管理','#','fa-comments',0,2,0,0,1,'2018-05-14 17:23:26',1,'2018-05-14 17:23:26'),(16,'文本回复','textmsg/list.do','fa-caret-right',15,2,0,0,1,'2018-05-14 17:23:26',1,'2018-05-14 17:23:26'),(17,'应用命令','command/list.do','fa-caret-right',15,4,0,0,1,'2018-05-14 17:23:26',1,'2018-05-14 17:23:26'),(18,'图文回复','imgmsg/list.do','fa-caret-right',15,3,0,0,1,'2018-05-14 17:23:26',1,'2018-05-14 17:23:26'),(19,'关注回复','#','fa-caret-right',15,1,0,0,1,'2018-05-14 17:23:26',1,'2018-05-14 17:23:26'),(20,'在线管理','onlinemanager/list.do','fa-caret-right',1,5,0,0,1,'2018-05-14 17:23:26',1,'2018-05-14 17:23:26'),(21,'打印测试','tool/printTest.do','fa-caret-right',9,7,0,0,1,'2018-05-14 17:23:26',1,'2018-05-14 17:23:26'),(22,'客户管理','sdfsdf/index.do','fa-caret-right',1,1,0,0,1,'2018-05-14 17:23:26',1,'2018-05-14 17:23:26'),(23,'菜单管理','ez/system/sysmenu/list.html','fa-leaf green',19,1,0,0,1,'2018-05-14 17:23:26',1,'2018-05-14 17:23:26'),(24,'三级1','#','fa-pencil',19,2,0,0,1,'2018-05-14 17:23:26',1,'2018-05-14 17:23:26');

/*Table structure for table `sys_role` */

CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名',
  `alias` varchar(50) DEFAULT NULL COMMENT '别名',
  `description` varchar(300) DEFAULT NULL COMMENT '简介',
  `disabled` tinyint(1) DEFAULT NULL COMMENT '是否禁用(0:启用 1:禁用)',
  `is_del` tinyint(1) DEFAULT NULL COMMENT '是否删除(0: 未删除 1:已删除)',
  `create_by` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`role_name`,`alias`,`description`,`disabled`,`is_del`,`create_by`,`create_time`,`update_by`,`update_time`) values (1,'超级管理员','超级管理员',NULL,0,0,0,'2018-05-14 18:34:41',0,'2018-05-14 17:34:44');

/*Table structure for table `sys_role_resources` */

CREATE TABLE `sys_role_resources` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `resources_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

/*Data for the table `sys_role_resources` */

/*Table structure for table `sys_user` */

CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_account` varchar(50) DEFAULT NULL COMMENT '登录账号',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `sex` tinyint(1) DEFAULT NULL COMMENT '性别(0:男 1:女)',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `birth_date` datetime DEFAULT NULL COMMENT '出生时间',
  `disable` tinyint(1) DEFAULT NULL COMMENT '是否禁用(0:启用 1:禁用)',
  `is_del` tinyint(1) DEFAULT NULL COMMENT '是否删除(0: 未删除 1:已删除)',
  `create_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`login_account`,`user_name`,`password`,`sex`,`phone`,`email`,`birth_date`,`disable`,`is_del`,`create_by`,`create_time`,`update_by`,`update_time`) values (1,'admin','管理员','123456',0,'1378594156','54546354@13.com','1993-02-17 17:33:16',0,0,0,'2018-05-14 17:33:50',0,'2018-05-14 17:33:55');

/*Table structure for table `sys_user_role` */

CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`role_id`,`user_id`) values (1,1,1);

/* Function  structure for function  `getPriority_MENU` */

DELIMITER $$

CREATE DEFINER=`root`@`localhost` FUNCTION `getPriority_MENU`(gid INT) RETURNS varchar(255) CHARSET utf8
    DETERMINISTIC
BEGIN  
    DECLARE gParentID INT DEFAULT 0;  
    DECLARE gOrderID INT DEFAULT 0;  
    DECLARE gPriority VARCHAR(255) DEFAULT '';  
     
    SELECT parent_id,sort INTO gParentID,gOrderID FROM sys_resources WHERE id = gid;  
  
    IF gParentID > 0 THEN   
    SET gPriority = CONCAT(gOrderID,gid);  
    ELSE   
    SET gPriority = gid;  
    END IF;       
           
    WHILE gParentID > 0 DO  /*0为根*/  
      SET gPriority = CONCAT(gParentID, '.', gPriority);  
      SELECT parent_id INTO gParentID FROM sys_resources WHERE id = gParentID;  
    END WHILE;   
    RETURN gPriority;  
  END $$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
