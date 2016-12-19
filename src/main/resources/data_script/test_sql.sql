CREATE TABLE `test_user` (
	`sId` varchar(19) NOT NULL,
	`userName` varchar(19) NOT NULL,
    `password` varchar(19) NOT NULL,
	PRIMARY KEY (`sId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



insert into test_user(sId,userName,password) values (uuid_short(),'test',md5('123456'));

/* 记录日志表  */
CREATE TABLE `service_log` (  
  `sid` varchar(20) NOT NULL,  
  `userid` bigint(20) unsigned,  
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',  
  `content` varchar(8000) NOT NULL DEFAULT '' COMMENT '日志内容',  
  `operation` varchar(250) NOT NULL DEFAULT '' COMMENT '用户所做的操作',  
  PRIMARY KEY (`sid`)  
) ENGINE=InnoDB DEFAULT CHARSET=utf8; 