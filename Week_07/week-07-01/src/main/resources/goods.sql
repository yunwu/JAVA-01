 create table goods(
  id bigint,
  main_goods_id bigint,
  name varchar(50),
  type varchar(20),
  main_img varchar(50),
  valid_start_time datetime comment '生效时间', 
  valid_end_time datetime comment '失效时间',  
  create_time datetime,
  update_time datetime,
  deleted tinyint,
  verson int,
  PRIMARY KEY (`id`)
 )engine=innodb comment '商品表';
 
  create table goods_detail(
 	id bigint comment '和商品表商品ID一致', 
 	pictures text comment '图片',
 	`desc` text,
    create_time datetime,
    update_time datetime,
    deleted tinyint,
    PRIMARY KEY (`id`)
 )engine=innodb comment '商品详情表';
 
 -- 当时的商品信息，暂时不考虑优惠券信息, 如果有优惠券，将优惠券作为一个json字符串导入到这个表
 create table goods_snap_shot(
   id bigint,
   `goods_id` bigint,
   `order_id` bigint,
   goods_name varchar(100),
   `type` varchar(20),
   create_time datetime,
   update_time datetime,
   deleted tinyint,
   PRIMARY KEY (`id`)
   -- 关联该商品
 )engine=innodb comment '商品快照表';
 
 
 create table goods_repertory(
  id bigint comment '和商品表的id保持一致', 
  size char(3),
  count int(8),
  create_time datetime,
  update_time datetime,
  deleted tinyint,
  PRIMARY KEY (`id`)
 )engine=innodb comment '商品库存表';
 
 
 create table `order`(
	id bigint,
	goods_snap_shot_id bigint,
	price double,
	goods_name varchar(50),
	status tinyint,
	create_time datetime,
    update_time datetime,
    deleted tinyint,
    PRIMARY KEY (`id`)
)engine=innodb comment '订单表';

 
 
 
 
 