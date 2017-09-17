-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.17 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  8.0.0.4458
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 mpet 的数据库结构
CREATE DATABASE IF NOT EXISTS `mpet` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mpet`;


-- 导出  表 mpet.account 结构
CREATE TABLE IF NOT EXISTS `account` (
  `username` varchar(80) NOT NULL,
  `password` varchar(60) DEFAULT NULL,
  `email` varchar(80) NOT NULL,
  `xm` varchar(80) NOT NULL,
  `address` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='InnoDB free: 7168 kB; InnoDB free: 6144 kB';

-- 正在导出表  mpet.account 的数据：~70 rows (大约)
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` (`username`, `password`, `email`, `xm`, `address`) VALUES
	('121212', '111', '123@123.com', '111', '222'),
	('12211', '111', 'wk@123.com', '121212', '121212'),
	('1221233434', '111', 'wk@123.com', '121212', '1123123'),
	('123', '123', '123@123', '123', '123'),
	('12312', '111', '123@123.com', '123', '1232'),
	('1231231344', '111', 'wk@123.com', '123', '222'),
	('12323', '123', '123@123.com', '123123', '123123'),
	('12333343', '123', '123@123.com', '12312', '123'),
	('1234', '234', '123@123.com', '12312', '12312'),
	('12342', '234', '123@123.com', '123123', '345'),
	('12344421', '123', '123@123.com', '12312', '13'),
	('1454334', '111', '123@123.com', '12312', '1231232'),
	('223232224', '111', 'wk@123.com', '12123', '11212'),
	('23', '23', '123@123.com', '123', '123'),
	('23222334', '234', '123@123.com', '123123', '12312'),
	('23223', '111', '345345@123', '123', '121'),
	('232323', '1111', 'wk2003119@163.com', 'sdfsdsd', '11212'),
	('23332314', '111', '123@123.com', '1231', '123'),
	('234', '342', '234@123.com', '123', '123'),
	('2342', '234', '123@123.com', '12312', '124'),
	('23423', '111', '123@123.com', '123', '123123'),
	('234234', '111', '123@qq.com', '2343434', '你好'),
	('2444', '111', 'wk@123.com', '1212', '122112'),
	('333', '111', 'we@123.com', '12312', '1233123'),
	('3434341', '111', 'wk@123.com', '12323', '121212'),
	('35345666', '1212', '123@123.com', '12312', '12312312'),
	('354', '456', '123@123.com', '12312', '12312'),
	('4343434', '123', '123@123.com', '12312', '12312'),
	('4444', '111', '123@123.com', '123', '1232'),
	('44443', '111', 'wk2003119@163.com', 'sdsdsd', '1212'),
	('453567gff', '111', 'wk2003119@163.com', 'fggf', '33434534'),
	('655623', '111', '1212', '2323', '3434'),
	('666', '111', '234@123.com', 'ty', '123123'),
	('666777', '111', '123@123.com', '123123', '123123123'),
	('67', '67', '123@123.com', '12312', '123'),
	('6767', '6767', 'wk2003119@163.com', '4554', '3434'),
	('67679', '6767', 'wk2003119@163.com', '4554', '3434'),
	('77771', '111', 'wk2003119@163.com', '234', '123'),
	('777888', '123', '123@123.com', '12312', '123123'),
	('8881', '111', 'wk2003119@163.com', 'ddd', '222'),
	('8888', '111', 'w@123.com', '111', '23232'),
	('8989uuu', '111', 'wk2003119@163.com', '222', '12222'),
	('898y', '111', 'wk2003119@163.com', 'ddd', '121212'),
	('9999', '111', 'w@123.com', 'xsssss', 'dddd'),
	('999988', '111', '123@qq.com', '2343434', '你好'),
	('99999', '123', '123@123.com', 'dffd', '123'),
	('99999111', '234', '123@123.com', '12312', '13'),
	('EYEYEY', '111', '123@123.com', '1231', '123'),
	('fsd', '111', 'wk2003119@163.com', 'xxx', 'sdfsd'),
	('halou', '111', '123@123.com', 'niaho', 'dasfdasf'),
	('hello', '111', 'wk2003119@163.com', '11231', '123123123'),
	('HHH', '111', '123@123.com', 'sdf', '123'),
	('hhhh', '111', 'wk@163.com', '111', '4444'),
	('iiopop', '111', '123@123.com', '123', '343434'),
	('kjkksdf', '111', '123@123.com', '13', '1312'),
	('kkk111', '111', 'wk@123.com', '韩国', '韩国大厦'),
	('KKK12', '999', 'wk@163.com', 'dffdfd', '221'),
	('kkkkk', '111', 'wk2003119@163.com', 'xxxx', 'jjdjdj'),
	('lai', '111', '123@123.com', '23123', '123123'),
	('ligon', '111', 'wk2003119@163.com', '理工', '112'),
	('liubing', '111', '2691240021@qq.com', '111', '222'),
	('LLLL', '111', 'wk2003119@163.com', 'LLLL', 'dslflfsl'),
	('pppp', '111', 'wk2003119@163.com', 'sdf', '12'),
	('PPPP1', '111', '123@123.com', '12312', '123123'),
	('PPPP12', '111', '123@123.com', '123', '12312'),
	('rtrtr111', '111', 'wk2003119@163.com', 'sdf', '12'),
	('sdfsdf', '111', '123@123.com', '123123', '123123'),
	('ty', '111', 'wk2003119@163.com', '你好', '12312'),
	('UUU', '111', 'wk@163.com', 'UUU', 'UUU'),
	('UUUU', '1212', '123@123.com', '围困', '想出国发生的'),
	('uuuyyyy', '111', '123@123.com', '123', '12312312'),
	('weewew', '111', '', '', ''),
	('weikun', '119', 'wk2003119@163.com', '卫昆1', '哈尔滨'),
	('weikun12123', '111', 'wk@123.com', 'sdfsdf', '123123'),
	('weikun222', '1212', '12312@123.com', '12312', '12312'),
	('weikun2333', '111', 'wk2003119@163.com', 'sss', '111'),
	('wewe', '111', 'wk@123.com', '你好', '1212'),
	('wumeng', '111', '123@123.com', '121212', '1中国'),
	('www', '111', 'Q@123.com', 'rrr', '2121'),
	('yao', '111', 'wk@123.com', '1212', '32323'),
	('yao111', '111', '123@123.com', '123', '123123'),
	('ytytytu', '111', 'wk2003119@163.com', '222', '121212'),
	('yuchenglong', '111', '123@123.com', '121212', '11212'),
	('YYQ', '110', '123@qq.com', '杨永强', 'hajkfhkasj'),
	('yyyy', '111', 'w@123.com', 'yyy', 'sdfdsfd'),
	('yyyy111212', '111', '123@123.com', '123', '12312'),
	('zhai357', 'qwe', '123@sina.com', 'qwe', '全球'),
	('zhaodongpo', '111', '123@qq.com', '2343434', '你好'),
	('zhaofuyu', '123456', '123@qq.com', 'zhaofuyu', '123456'),
	('公寓', '111', 'wk2003119@163.com', '111', '123'),
	('发71', '111', '123@123.com', '商品', '2312'),
	('哦哦o', '111', '123@123.com', '如同仁堂', '123'),
	('商品', '111', '123@123.com', '商品', '2312'),
	('尚品', '111', 'wk2003119@163.com', '尚品', '尚品'),
	('杨林', '123456', '1434@qq.com', '杨林', 'sfefeff');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;


-- 导出  过程 mpet.addCart6 结构
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `addCart6`(IN `in_itemid` varchar(20), IN `in_quantity` int, IN `in_username` varchar(20), OUT `out_oid` int

)
begin
  set @oid:=0;
  set @itemid:="";
  set @qu:=0;# 老数量
  
  -- 以下取出没结账的最大订单编号
  select orderid into @oid
  from orders o
  where o.orderdate is null and username=in_username
  order by o.orderid desc
  limit 1;
 
  
  if @oid='' then #没有新开订单
  	
  	select orderid into @oid
  	from orders
  	order by orderid desc
  	limit 1;
  	
  	set @oid:=@oid+1;
  	insert into orders (orderid,username)
  	values(@oid ,in_username) ;
  
  end if;
  
  select itemid,quantity into @itemid,@qu
  from cart c
  where c.orderid=@oid and c.itemid=in_itemid and username=in_username;
  
  if @itemid='' then -- 证明是纯新商品，我们应该insert
  	
  	insert into cart(orderid,itemid,quantity,username) 
	  values(@oid,in_itemid,in_quantity,in_username);
  	
  else # 证明该购物车中有该商品，需要更新数量
    update cart c
    set quantity=@qu+in_quantity
    where c.orderid=@oid and c.itemid=in_itemid and username=in_username;
     
     
  end if;
  set out_oid=@oid;

end//
DELIMITER ;


-- 导出  表 mpet.cart 结构
CREATE TABLE IF NOT EXISTS `cart` (
  `username` varchar(50) NOT NULL DEFAULT '0',
  `orderid` int(11) NOT NULL DEFAULT '0',
  `itemid` varchar(10) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`orderid`,`itemid`,`username`),
  KEY `FK_FK_Reference_51` (`itemid`),
  KEY `FK888` (`username`),
  CONSTRAINT `FK888` FOREIGN KEY (`username`) REFERENCES `orders` (`username`),
  CONSTRAINT `FK_FK_Reference_51` FOREIGN KEY (`itemid`) REFERENCES `item` (`itemid`),
  CONSTRAINT `FK_Relationship_71` FOREIGN KEY (`orderid`) REFERENCES `orders` (`orderid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='InnoDB free: 7168 kB; InnoDB free: 6144 kB; (`itemid`) REFER';

-- 正在导出表  mpet.cart 的数据：~8 rows (大约)
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` (`username`, `orderid`, `itemid`, `quantity`) VALUES
	('weikun', 1, 'EST_15', 1),
	('weikun', 1, 'EST_16', 1),
	('weikun', 1, 'EST_2', 1),
	('weikun', 1, 'EST_21', 1),
	('weikun', 1, 'EST_27', 3),
	('weikun', 1, 'EST_5', 4),
	('weikun', 1, 'EST_7', 1),
	('weikun', 1, 'EST_9', 1),
	('weikun', 2, 'EST_7', 2),
	('weikun', 3, 'EST_12', 1),
	('weikun', 4, 'EST_9', 1),
	('YYQ', 5, 'EST_15', 3),
	('YYQ', 5, 'EST_3', 2),
	('YYQ', 6, 'EST_15', 7),
	('YYQ', 7, 'EST_2', 34),
	('YYQ', 8, 'EST_12', 2),
	('weikun', 9, 'EST_12', 2),
	('weikun', 10, 'EST_12', 1);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;


-- 导出  表 mpet.category 结构
CREATE TABLE IF NOT EXISTS `category` (
  `catid` varchar(10) NOT NULL,
  `name` varchar(80) DEFAULT NULL,
  `descn` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`catid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='InnoDB free: 7168 kB; InnoDB free: 6144 kB';

-- 正在导出表  mpet.category 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`catid`, `name`, `descn`) VALUES
	('BIRDS', '鸟类', '<image src="${ppath}/static/images/birds_icon.gif"><font size="5" color="blue"> Birds</font>'),
	('CATS', '猫', '<image src="${ppath}/static/images/cats_icon.gif"><font size="5" color="blue"> Cats</font>'),
	('DOGS', '狗', '<image src="${ppath}/static/images/dogs_icon.gif"><font size="5" color="blue"> Dogs</font>'),
	('FISH', '鱼', '<image src="${ppath}/static/images/fish_icon.gif"><font size="5" color="blue"> Fish</font>'),
	('REPTILES', '爬虫类', '<image src="${ppath}/static/images/reptiles_icon.gif"><font size="5" color="blue"> Reptiles</font>');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;


-- 导出  表 mpet.item 结构
CREATE TABLE IF NOT EXISTS `item` (
  `itemid` varchar(10) NOT NULL,
  `productid` varchar(10) NOT NULL,
  `listprice` decimal(10,2) DEFAULT NULL,
  `unitcost` decimal(10,2) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL,
  `attr1` varchar(80) DEFAULT NULL,
  `attr2` varchar(80) DEFAULT NULL,
  `attr3` varchar(80) DEFAULT NULL,
  `attr4` varchar(80) DEFAULT NULL,
  `attr5` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`itemid`),
  KEY `FK_fk_item_1` (`productid`),
  CONSTRAINT `FK_fk_item_1` FOREIGN KEY (`productid`) REFERENCES `product` (`productid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='InnoDB free: 7168 kB; (`productid`) REFER `jpetstore/product';

-- 正在导出表  mpet.item 的数据：~28 rows (大约)
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` (`itemid`, `productid`, `listprice`, `unitcost`, `status`, `attr1`, `attr2`, `attr3`, `attr4`, `attr5`) VALUES
	('EST_1', 'FI-SW-01', 16.50, 10.00, 'P', 'Large', NULL, NULL, NULL, NULL),
	('EST_10', 'K9-DL-01', 18.50, 12.00, 'P', 'Spotted Adult Female', NULL, NULL, NULL, NULL),
	('EST_11', 'RP-SN-01', 18.50, 12.00, 'P', 'Venomless', NULL, NULL, NULL, NULL),
	('EST_12', 'RP-SN-01', 18.50, 12.00, 'P', 'Rattleless', NULL, NULL, NULL, NULL),
	('EST_13', 'RP-LI-02', 18.50, 12.00, 'P', 'Green Adult', NULL, NULL, NULL, NULL),
	('EST_14', 'FL-DSH-01', 58.50, 12.00, 'P', 'Tailless', NULL, NULL, NULL, NULL),
	('EST_15', 'FL-DSH-01', 23.50, 12.00, 'P', 'With tail', NULL, NULL, NULL, NULL),
	('EST_16', 'FL-DLH-02', 93.50, 12.00, 'P', 'Adult Female', NULL, NULL, NULL, NULL),
	('EST_17', 'FL-DLH-02', 93.50, 12.00, 'P', 'Adult Male', NULL, NULL, NULL, NULL),
	('EST_18', 'AV-CB-01', 193.50, 92.00, 'P', 'Adult Male', NULL, NULL, NULL, NULL),
	('EST_19', 'AV-SB-02', 15.50, 2.00, 'P', 'Adult Male', NULL, NULL, NULL, NULL),
	('EST_2', 'FI-SW-01', 16.50, 10.00, 'P', 'Small', NULL, NULL, NULL, NULL),
	('EST_20', 'FI-FW-02', 5.50, 2.00, 'P', 'Adult Male', NULL, NULL, NULL, NULL),
	('EST_21', 'FI-FW-02', 5.29, 1.00, 'P', 'Adult Female', NULL, NULL, NULL, NULL),
	('EST_22', 'K9-RT-02', 135.50, 100.00, 'P', 'Adult Male', NULL, NULL, NULL, NULL),
	('EST_23', 'K9-RT-02', 145.49, 100.00, 'P', 'Adult Female', NULL, NULL, NULL, NULL),
	('EST_24', 'K9-RT-02', 255.50, 92.00, 'P', 'Adult Male', NULL, NULL, NULL, NULL),
	('EST_25', 'K9-RT-02', 325.29, 90.00, 'P', 'Adult Female', NULL, NULL, NULL, NULL),
	('EST_26', 'K9-CW-01', 125.50, 92.00, 'P', 'Adult Male', NULL, NULL, NULL, NULL),
	('EST_27', 'K9-CW-01', 155.29, 90.00, 'P', 'Adult Female', NULL, NULL, NULL, NULL),
	('EST_28', 'K9-RT-01', 155.29, 90.00, 'P', 'Adult Female', NULL, NULL, NULL, NULL),
	('EST_3', 'FI-SW-02', 18.50, 12.00, 'P', 'Toothless', NULL, NULL, NULL, NULL),
	('EST_4', 'FI-FW-01', 18.50, 12.00, 'P', 'Spotted', NULL, NULL, NULL, NULL),
	('EST_5', 'FI-FW-01', 18.50, 12.00, 'P', 'Spotless', NULL, NULL, NULL, NULL),
	('EST_6', 'K9-BD-01', 18.50, 12.00, 'P', 'Male Adult', NULL, NULL, NULL, NULL),
	('EST_7', 'K9-BD-01', 18.50, 12.00, 'P', 'Female Puppy', NULL, NULL, NULL, NULL),
	('EST_8', 'K9-PO-02', 18.50, 12.00, 'P', 'Male Puppy', NULL, NULL, NULL, NULL),
	('EST_9', 'K9-DL-01', 18.50, 12.00, 'P', 'Spotless Male Puppy', NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;


-- 导出  表 mpet.orders 结构
CREATE TABLE IF NOT EXISTS `orders` (
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `orderid` int(11) NOT NULL DEFAULT '0' COMMENT '订单序号',
  `orderdate` date DEFAULT NULL COMMENT '订单日期',
  `totalprice` decimal(10,2) DEFAULT '0.00' COMMENT '总价格',
  PRIMARY KEY (`orderid`,`username`),
  KEY `FK_orders_account` (`username`),
  CONSTRAINT `FK_orders_account` FOREIGN KEY (`username`) REFERENCES `account` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='InnoDB free: 7168 kB; InnoDB free: 6144 kB; (`linenum` `orde';

-- 正在导出表  mpet.orders 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`username`, `orderid`, `orderdate`, `totalprice`) VALUES
	('weikun', 1, '2017-02-18', NULL),
	('weikun', 2, '2017-02-18', NULL),
	('weikun', 3, '2017-02-18', NULL),
	('weikun', 4, '2017-02-18', NULL),
	('YYQ', 5, '2017-02-18', 44.00),
	('YYQ', 6, '2017-02-18', 164.50),
	('YYQ', 7, '2017-02-18', 561.00),
	('YYQ', 8, '2017-02-18', 37.00),
	('weikun', 9, '2017-02-18', 37.00),
	('weikun', 10, NULL, 0.00);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;


-- 导出  表 mpet.product 结构
CREATE TABLE IF NOT EXISTS `product` (
  `productid` varchar(10) NOT NULL,
  `catid` varchar(10) NOT NULL,
  `name` varchar(80) DEFAULT NULL,
  `descn` varchar(255) DEFAULT NULL,
  `pic` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`productid`),
  KEY `FK_fk_product_1` (`catid`),
  CONSTRAINT `FK_fk_product_1` FOREIGN KEY (`catid`) REFERENCES `category` (`catid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='InnoDB free: 7168 kB; (`category`) REFER `jpetstore/category';

-- 正在导出表  mpet.product 的数据：~16 rows (大约)
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`productid`, `catid`, `name`, `descn`, `pic`) VALUES
	('AV-CB-01', 'BIRDS', 'Amazon Parrot', 'Great companion for up to 75 years', 'bird4.gif'),
	('AV-SB-02', 'BIRDS', 'Finch', 'Great stress reliever', 'bird1.gif'),
	('FI-FW-01', 'FISH', 'Koi', 'Fresh Water fish from Japan', 'fish3.gif'),
	('FI-FW-02', 'FISH', 'Goldfish', 'Fresh Water fish from China', 'fish2.gif'),
	('FI-SW-01', 'FISH', 'Angelfish', 'Salt Water fish from Australia', 'fish1.jpg'),
	('FI-SW-02', 'FISH', 'Tiger Shark', 'Salt Water fish from Australia', 'fish4.gif'),
	('FL-DLH-02', 'CATS', 'Persian', 'Friendly house cat, doubles as a princess', 'cat1.gif'),
	('FL-DSH-01', 'CATS', 'Manx', 'Great for reducing mouse populations', 'cat3.gif'),
	('K9-BD-01', 'DOGS', 'Bulldog', 'Friendly dog from England', 'dog2.gif'),
	('K9-CW-01', 'DOGS', 'Chihuahua', 'Great companion dog', 'dog4.gif'),
	('K9-DL-01', 'DOGS', 'Dalmation', 'Great dog for a Fire Station', 'dog5.gif'),
	('K9-PO-02', 'DOGS', 'Poodle', 'Cute dog from France', 'dog6.gif'),
	('K9-RT-01', 'DOGS', 'Golden Retriever', 'Great family dog', 'dog1.gif'),
	('K9-RT-02', 'DOGS', 'Labrador Retriever', 'Great hunting dog', 'dog5.gif'),
	('RP-LI-02', 'REPTILES', 'Iguana', 'Friendly green friend', 'lizard2.gif'),
	('RP-SN-01', 'REPTILES', 'Rattlesnake', 'Doubles as a watch dog', 'lizard3.gif');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;


-- 导出  表 mpet.profile 结构
CREATE TABLE IF NOT EXISTS `profile` (
  `username` varchar(80) NOT NULL,
  `lang` varchar(80) NOT NULL,
  `catid` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`username`),
  KEY `FK_Reference_7` (`catid`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`catid`) REFERENCES `category` (`catid`),
  CONSTRAINT `FK_Relationship_4` FOREIGN KEY (`username`) REFERENCES `account` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='InnoDB free: 7168 kB; InnoDB free: 6144 kB; (`username`) REF';

-- 正在导出表  mpet.profile 的数据：~56 rows (大约)
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
INSERT INTO `profile` (`username`, `lang`, `catid`) VALUES
	('123', 'english', 'BIRDS'),
	('12312', 'english', 'BIRDS'),
	('1231231344', 'english', 'CATS'),
	('12333343', 'eng', 'BIRDS'),
	('1234', 'eng', 'BIRDS'),
	('12344421', 'eng', 'DOGS'),
	('1454334', 'eng', 'BIRDS'),
	('223232224', 'english', 'BIRDS'),
	('23', 'eng', 'BIRDS'),
	('23222334', 'eng', 'DOGS'),
	('232323', 'english', 'BIRDS'),
	('23332314', 'eng', 'CATS'),
	('2342', 'eng', NULL),
	('234234', 'english', NULL),
	('35345666', 'eng', 'BIRDS'),
	('4343434', 'eng', 'CATS'),
	('44443', 'english', 'BIRDS'),
	('453567gff', 'english', NULL),
	('655623', 'eng', 'BIRDS'),
	('666', 'english', 'CATS'),
	('67', 'eng', 'BIRDS'),
	('6767', 'english', 'BIRDS'),
	('77771', 'english', NULL),
	('777888', 'eng', NULL),
	('8881', 'english', NULL),
	('8888', 'chinese', 'REPTILES'),
	('8989uuu', 'english', 'BIRDS'),
	('9999', 'chinese', 'BIRDS'),
	('999988', 'english', NULL),
	('99999', 'chinese', 'CATS'),
	('fsd', 'chinese', 'CATS'),
	('halou', 'english', 'BIRDS'),
	('hello', 'english', NULL),
	('HHH', 'english', 'FISH'),
	('hhhh', 'chinese', 'DOGS'),
	('iiopop', 'eng', 'BIRDS'),
	('kkk111', 'english', 'BIRDS'),
	('KKK12', 'chi', 'BIRDS'),
	('liubing', 'english', 'BIRDS'),
	('LLLL', '1', 'BIRDS'),
	('PPPP1', 'eng', 'BIRDS'),
	('PPPP12', 'eng', 'CATS'),
	('sdfsdf', 'eng', 'DOGS'),
	('UUU', 'chi', 'BIRDS'),
	('uuuyyyy', 'eng', 'BIRDS'),
	('weewew', 'english', 'BIRDS'),
	('weikun', 'english', 'FISH'),
	('weikun222', '999', 'BIRDS'),
	('weikun2333', 'english', 'BIRDS'),
	('wewe', 'english', 'CATS'),
	('wumeng', 'english', 'BIRDS'),
	('www', 'english', 'FISH'),
	('yuchenglong', 'eng', 'BIRDS'),
	('YYQ', 'english', 'BIRDS'),
	('yyyy', 'english', 'DOGS'),
	('zhai357', 'chinese', 'BIRDS'),
	('zhaodongpo', 'english', NULL),
	('zhaofuyu', 'english', 'BIRDS'),
	('公寓', 'english', NULL),
	('哦哦o', 'english', NULL),
	('尚品', 'english', NULL),
	('杨林', 'english', 'BIRDS');
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;


-- 导出  过程 mpet.queryCart6 结构
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `queryCart6`(IN `in_username` varchar(20)


)
begin
  
  set @oid:='';
  
  select orderid into @oid
  from orders 
  where username=in_username and orderdate is null;
  
  
  select *
  from cart c
  where c.orderid=@oid 
 
  and username=in_username;

end//
DELIMITER ;


-- 导出  过程 mpet.updateCart6 结构
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateCart6`(IN `in_username` varchar(20), IN `in_orderid` varchar(20), IN `in_itemid` varchar(20), IN `in_qty` int


)
begin
    update cart c
    set quantity=in_qty
    where c.orderid=in_orderid and c.itemid=in_itemid
    and username=in_username;
     

end//
DELIMITER ;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
