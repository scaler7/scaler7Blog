/*
SQLyog Ultimate v9.62 
MySQL - 5.7.27 : Database - blog
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`blog` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `blog`;

/*Table structure for table `b_article` */

DROP TABLE IF EXISTS `b_article`;

CREATE TABLE `b_article` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `title` varchar(200) NOT NULL COMMENT '标题',
  `titlePic` varchar(55) DEFAULT NULL COMMENT '标题图片',
  `slug` varchar(200) DEFAULT NULL,
  `created` varchar(19) DEFAULT '0' COMMENT '创建时间',
  `modified` varchar(19) DEFAULT '0' COMMENT '最后一次修改时间',
  `content` text NOT NULL COMMENT '内容文字',
  `authorId` int(10) DEFAULT '0' COMMENT '作者id',
  `type` varchar(16) DEFAULT 'post' COMMENT '类别',
  `status` varchar(16) DEFAULT 'publish' COMMENT '状态',
  `tags` varchar(200) NOT NULL COMMENT '标签',
  `categories` varchar(255) DEFAULT NULL COMMENT '属性',
  `hits` int(10) DEFAULT '0' COMMENT '点击数',
  `commentsNum` int(10) DEFAULT '0' COMMENT '评论数',
  `allowComment` int(1) DEFAULT '1' COMMENT '是否允许被评论',
  PRIMARY KEY (`id`),
  UNIQUE KEY `slug` (`slug`),
  KEY `created` (`created`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `b_article` */

insert  into `b_article`(`id`,`title`,`titlePic`,`slug`,`created`,`modified`,`content`,`authorId`,`type`,`status`,`tags`,`categories`,`hits`,`commentsNum`,`allowComment`) values (1,'Linux上安装JDK、mysql、tomcat，以及将springboot项目部署到Linux上',NULL,NULL,'2019-12-04 15:37:39','2019-12-06 19:45:20','1## 安装jdk\r\n##### 1下载jdk并放入linux系统\r\n##### 2解压\r\nmkdir /usr/local/java  #创建文件夹，等下就将jdk解压到这里\r\n\r\ntar -zxvf jdk-8u181-linux-x64.tar.gz  -C /usr/local/java\r\n##### 3配置环境变量\r\nvim   /etc/profile\r\n![在这里插入图片描述](https://img-blog.csdnimg.cn/2019111922081738.png)\r\nJAVA_HOME=/usr/local/java/jdk1.8.0_181    #这里配置的是jdk安装路径\r\nPATH=\\$JAVA_HOME/bin:\\$PATH\r\nCLASSPATH=.:\\$JAVA_HOME/lib/dt.jar:\\$JAVA_HOME/lib/tools.jar\r\nexport JAVA_HOME     #需要对外暴露JAVA_HOME、PATH、CLASSPATH\r\nexport PATH\r\nexport CLASSPATH\r\n\r\n##### 4环境变量配置完成后，刷新环境变量\r\nsource /etc/profile\r\n\r\n##### 5测试\r\njava -version\r\n![在这里插入图片描述](https://img-blog.csdnimg.cn/2019111922115463.png)\r\n成功！\r\n\r\n## 安装tomcat\r\n##### 1下载tomcat并放入linux系统\r\n##### 2解压\r\nmkdir /usr/local/tomcat   #同样，创建一个文件夹\r\ntar -zxvf apache-tomcat-8.5.43.tar.gz  -C /usr/local/tomcat/  #将tomcat解压到上面创建的文件夹里\r\ncd /usr/local/tomcat/apache-tomcat-8.5.43/bin\r\n./startup.sh   #启动tomcat 访问localhost:8080\r\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20191119222047189.png)\r\n成功！\r\n\r\n./shutdown.sh   #关闭tomcat\r\n\r\n## 安装mysql(这里的mysql安装教程只针对5.7.27版本，其他版本安装方式可能不同！)\r\n##### 1下载mysql\r\n##### 2解压到/usr/local/mysql【必须是这个目录】\r\n#解压\r\ntar -zxvf mysql-5.7.27-linux-glibc2.12-x86_64.tar.gz   这一步是解压到当前目录\r\n#重命名\r\nmv mysql-5.7.27-linux-glibc2.12-x86_64 mysql 将解压后的文件夹重命名为mysql\r\n#复制解压后的mysql目录\r\ncp -rv mysql /usr/local 将mysql文件夹复制到/usr/local\r\n\r\n##### 3在根目录下创建/data/mysql 用于存放数据库数据\r\ncd /\r\nmkdir /data/mysql\r\n##### 4创建用户组\r\ncd /usr/local/mysql/\r\n\r\n#新建一个msyql组\r\ngroupadd mysql\r\n#新建msyql用户禁止登录shell\r\nuseradd -r -s /sbin/nologin -g mysql mysql -d /usr/local/mysql \r\n##### 5改变目录所有者\r\ncd /usr/local/mysql\r\npwd\r\nchown -R mysql .\r\nchgrp -R mysql .\r\n数据库目录\r\nchown -R mysql /data/mysql\r\n##### 6配置参数\r\n此处一定需要注意记录生成的临时密码，如上文结尾处的：YLi>7ecpe;YP\r\n\r\nbin/mysqld --initialize --user=mysql --basedir=/usr/local/mysql --datadir=/data/mysql\r\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20191119222803799.png)\r\n##### 7安装\r\nbin/mysql_ssl_rsa_setup --datadir=/data/mysql \r\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20191119222842429.png)\r\n##### 8修改系统配置文件\r\ncd /usr/local/mysql/support-files   #进入到support-files这个目录\r\ncp mysql.server /etc/init.d/mysql  #将mysql.server这个文件复制到/etc/init.d/mysql 下\r\nvim /etc/init.d/mysql #开启编辑模式，编辑/etc/init.d/mysql 找到以下两行并填入对应路径\r\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20191119223055426.png)\r\n##### 9启动mysql\r\n先执行\r\n/etc/init.d/mysql start\r\n如果无法启动执行下面命令\r\nchmod 777 /etc/my.cnf\r\n再执行/etc/init.d/mysql start\r\n![在这里插入图片描述](https://img-blog.csdnimg.cn/2019111922324149.png)\r\n\r\n##### 10登陆mysql并修改密码\r\n#登陆  的bin目录下执行\r\nmysql  -u root -p\r\n　　--如果出现：-bash: mysql: command not found\r\n　　--就执行： # ln -s /usr/local/mysql/bin/mysql /usr/bin --没有出现就不用执行　![在这里插入图片描述](https://img-blog.csdnimg.cn/20191119223322733.png)\r\n修改密码\r\nset password=password(\'123456\')\r\n![在这里插入图片描述](https://img-blog.csdnimg.cn/20191119223349624.png)\r\n##### 11设置root账户的host地址（修改了才可以远程连接）\r\n\r\nmysql>grant all privileges on *.* to \'root\'@\'%\' identified by \'123456\';\r\nmysql>flush privileges;\r\n\r\n##### 12添加系统路径【为设置开机启动提供】\r\nvim /etc/profile\r\n添加：\r\nexport PATH=/usr/local/mysql/bin:$PATH\r\nsource /etc/profile #刷新配置文件\r\n##### 13配置mysql文件自动启动\r\nchmod 755 /etc/init.d/mysql\r\nchkconfig --add mysql\r\nchkconfig --level 345 mysql on\r\n\r\n配置完成，自己测试一下远程连接\r\n\r\n## 将springboot项目部署在linux下 需要jdk，mysql(我使用的mysql)\r\n我使用的是springboot内置的tomcat\r\n1.将项目打成jar包\r\n2.将jar包放入linux\r\n3.执行命令java -jar 项目名\r\n\r\n终端关闭时，项目进程也会关闭，如果想让项目一直在后台运行，需要加上\r\n**nohup java -jar 项目名 >文件名 &**\r\n例如\r\n**nohup java -jar ERP-CMS-1.0.0.jar >erp.txt &**\r\n\r\nerp.txt这个文件是自己创建，项目有日志输出时，输出到这个文件\r\n&代表在后台运行。特定：当前ssh窗口不被锁定，但是当窗口关闭时，程序中止运行。\r\nnohup 意思是不挂断运行命令,当账户退出或终端关闭、窗口关闭时时,程序仍然运行\r\n\r\n至此，项目部署完毕',1,'post','publish','linux,jdk,mysql,tomcat,springboot','Linux',24,0,1),(2,'关于static变量可以\"先赋值、后声明\"的问题',NULL,NULL,'2019-12-05 15:52:03','2019-12-06 19:44:59','今天看到了一段代码，很有意思。\r\n```java\r\npublic class Test {\r\n    static{\r\n           i=0;\r\n           System.out.println(i); // 编译错误\"Cannot reference a field before it is defined\"\r\n    }\r\n    public static int i=1;\r\n}\r\n```\r\n上面一段代码，输出语句报编译错误，报错信息翻译一下 **\"无法在定义字段之前引用该字段\"**，乍一看，这错报的确实没啥问题啊，i 还没定义，咋能输出它呢?\r\n\r\n但是我把这段输出语句注释掉，发现程序又正常运行了，也就是这样\r\n```java\r\npublic class Test {\r\n    static{\r\n           i=0;\r\n    }\r\n    public static int i=1;\r\n}\r\n```\r\n静态变量 i 明明还没有声明，为什么可以在静态代码块中做 i = 0 这个赋值操作呢？\r\n\r\n我通过面向CSDN编程，解开了这一疑惑。static修饰的变量的声明和赋值其实是分开的。比如 public static int i=1; 在编译期JVM就已经把 public static int i 这一声明操作抽了出来并且执行了。也就是说在加载类之前，静态变量 i 就已经被声明了，内存中已经开辟出了属于它的空间。上面一段代码，其实可以抽象为这个样子：\r\n```java\r\n编译期：public static int; //编译期就已经声明了静态变量i\r\npublic class Test {\r\n    static{  //运行期就只剩赋值操作\r\n           i=0;\r\n           i=1;\r\n    }\r\n}\r\n```\r\n这也就是为什么 i=0; 可以先于 public static int i=1; 执行而不报错。\r\n\r\n但是问题又来了。众所周知，想要使用一个变量，要声明它并且对它赋值。根据上面的结论，我们这段代码中静态变量 i 在运行期就已经被声明了，i=0; 这行代码又执行了赋值操作，那么 i 不就可以用了吗？为什么输出 i 还是会报编译错误？\r\n\r\n我的个人理解是，按照理论来说，上面这种情况中，i 确实可用。但是先使用变量，后声明变量，这跟java的语法规则是背道而驰的！虽然理论上可行，但是它终究还是违背了java的语法规则，所以编译报错。\r\n\r\n虽然静态变量的声明赋值很特殊，可以 **\"先赋值，后声明\"**，但是我们书写静态变量和静态代码块时最好仍然遵守java语法规则，声明在前，赋值在后，避免不必要的错误。按规矩办事，你好我好大家好！\r\n\r\n最后贴出一位大牛的帖子，从根源上解释了这个问题。希望我有一天能达到这位大牛的境界哈哈：\r\nhttps://blog.csdn.net/darxin/article/details/5293427',1,'post','publish','java,static,静态变量','Java',12,0,1),(3,'layui-动态渲染下拉框(从后台获取下拉框的值) & 设置下拉框默认选中',NULL,NULL,'2019-12-06 16:39:58','2019-12-06 19:44:39','动态渲染下拉框\r\n```js\r\n	        $.post(\'请求地址\',function(rs){\r\n	        	//item是返回的数据集合中的每个元素\r\n        		$.each(rs.data, function (index, item) {\r\n        			// 追加option节点 \r\n        			//item.name是option里的文本值\r\n        			//item.id是option的value值\r\n    				$(\"#selectId\").append(new Option(item.name, item.id));\r\n    			});\r\n    			//重新渲染下拉框\r\n    			form.render(\'select\');\r\n        })\r\n```\r\n\r\n设置下拉框默认选中\r\n```js\r\n//id是需要设置默认选中的option节点的value值\r\n$(\"#selectId\").find(\"option[value=\"+id+\"]\").prop(\"selected\",true);\r\n```',1,'post','publish','layui,js,下拉框渲染','js',6,0,1),(4,'博客后台管理系统',NULL,NULL,'2019-12-06 20:10:29','2019-12-06 20:11:57','博客后台管理系统地址：http://114.55.27.97/login.html\r\n用户名:user 密码:123\r\n\r\n也可直接点击右侧友情链接下方的地址访问',1,'post','publish','scaler7','默认分类',3,0,1);

/*Table structure for table `b_attach` */

DROP TABLE IF EXISTS `b_attach`;

CREATE TABLE `b_attach` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fname` varchar(100) NOT NULL DEFAULT '',
  `ftype` varchar(50) DEFAULT '',
  `fkey` text NOT NULL,
  `authorId` int(10) DEFAULT NULL,
  `created` varchar(19) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `b_attach` */

insert  into `b_attach`(`id`,`fname`,`ftype`,`fkey`,`authorId`,`created`) values (21,'u=2045628443,1544264790&fm=27&gp=0.jpg','image','u=2045628443,1544264790&fm=27&gp=0.jpg',1,'2019-12-06 16:35:24'),(22,'AmazeUI-1.0.1.zip','file','AmazeUI-1.0.1.zip',1,'2019-12-07 09:16:09');

/*Table structure for table `b_categories` */

DROP TABLE IF EXISTS `b_categories`;

CREATE TABLE `b_categories` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(200) DEFAULT NULL COMMENT '项目名字',
  `sort` int(10) unsigned DEFAULT '0' COMMENT '排序',
  `count` int(11) DEFAULT '0' COMMENT '标签或分类辖下的文章数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `b_categories` */

insert  into `b_categories`(`id`,`name`,`sort`,`count`) values (1,'Java',1,1),(2,'Linux',2,1),(3,'js',3,1);

/*Table structure for table `b_comments` */

DROP TABLE IF EXISTS `b_comments`;

CREATE TABLE `b_comments` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `article_id` int(11) DEFAULT NULL,
  `created` varchar(16) DEFAULT NULL,
  `author` varchar(200) DEFAULT NULL,
  `author_id` int(11) unsigned DEFAULT '0',
  `owner_id` int(10) unsigned DEFAULT '0',
  `ip` varchar(64) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `agent` varchar(200) DEFAULT NULL,
  `content` text,
  `type` varchar(16) DEFAULT 'comment',
  `status` int(11) NOT NULL DEFAULT '1' COMMENT '1审核通过 0审核不通过',
  `parent_id` int(11) unsigned DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `cid` (`article_id`) USING BTREE,
  KEY `created` (`created`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

/*Data for the table `b_comments` */

insert  into `b_comments`(`id`,`article_id`,`created`,`author`,`author_id`,`owner_id`,`ip`,`url`,`agent`,`content`,`type`,`status`,`parent_id`) values (1,1,'2019-12-1 00:00:','弟中弟',1,1,'127.0.0.1','###','6','66666666666666666','comment',1,0);

/*Table structure for table `b_link` */

DROP TABLE IF EXISTS `b_link`;

CREATE TABLE `b_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(200) NOT NULL,
  `link_url` varchar(200) NOT NULL,
  `link_desc` varchar(200) DEFAULT NULL,
  `created` varchar(19) NOT NULL,
  `sort` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

/*Data for the table `b_link` */

insert  into `b_link`(`id`,`name`,`link_url`,`link_desc`,`created`,`sort`) values (6,'百度','http://www.baidu.com','#','2019-12-06 16:28:15',0),(7,'淘宝','http://www.taobao.com','#','2019-12-06 16:28:27',0),(8,'百度','http://www.baidu.com','#','2019-12-06 16:29:34',0),(10,'淘宝','http://www.taobao.com','#','2019-12-06 16:35:42',0),(11,'博客后台管理系统','http://114.55.27.97/login.html','#','2019-12-06 17:33:33',0);

/*Table structure for table `b_relationship` */

DROP TABLE IF EXISTS `b_relationship`;

CREATE TABLE `b_relationship` (
  `a_id` int(11) NOT NULL,
  `m_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `b_relationship` */

/*Table structure for table `u_loginlog` */

DROP TABLE IF EXISTS `u_loginlog`;

CREATE TABLE `u_loginlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_name` varchar(50) NOT NULL COMMENT '登录名',
  `ip` varchar(50) NOT NULL COMMENT 'ip地址',
  `login_time` varchar(19) NOT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4;

/*Data for the table `u_loginlog` */

insert  into `u_loginlog`(`id`,`user_name`,`ip`,`login_time`) values (1,'user','0:0:0:0:0:0:0:1','2019-12-06 14:50:28'),(2,'user','0:0:0:0:0:0:0:1','2019-12-06 14:50:30'),(3,'user','0:0:0:0:0:0:0:1','2019-12-06 14:51:10'),(4,'user','0:0:0:0:0:0:0:1','2019-12-06 14:51:14'),(5,'user','0:0:0:0:0:0:0:1','2019-12-06 14:51:16'),(6,'user','0:0:0:0:0:0:0:1','2019-12-06 14:51:18'),(7,'user','127.0.0.1','2019-12-06 15:02:22'),(8,'user','127.0.0.1','2019-12-06 15:04:14'),(9,'scaler7','127.0.0.1','2019-12-06 15:20:44'),(10,'user','127.0.0.1','2019-12-06 15:20:53'),(11,'scaler7','0:0:0:0:0:0:0:1','2019-12-06 16:02:05'),(12,'scaler7','0:0:0:0:0:0:0:1','2019-12-06 16:08:33'),(13,'scaler7','0:0:0:0:0:0:0:1','2019-12-06 16:14:51'),(14,'scaler7','0:0:0:0:0:0:0:1','2019-12-06 16:16:00'),(15,'scaler7','0:0:0:0:0:0:0:1','2019-12-06 16:16:40'),(16,'scaler7','0:0:0:0:0:0:0:1','2019-12-06 16:19:03'),(17,'scaler7','0:0:0:0:0:0:0:1','2019-12-06 16:19:45'),(18,'user','0:0:0:0:0:0:0:1','2019-12-06 16:22:28'),(19,'user','127.0.0.1','2019-12-06 16:24:45'),(20,'user','127.0.0.1','2019-12-06 16:24:49'),(21,'user','127.0.0.1','2019-12-06 16:25:20'),(22,'scaler7','127.0.0.1','2019-12-06 16:26:04'),(23,'scaler7','127.0.0.1','2019-12-06 16:28:06'),(24,'scaler7','0:0:0:0:0:0:0:1','2019-12-06 19:17:50'),(25,'user','127.0.0.1','2019-12-06 19:31:36'),(26,'user','127.0.0.1','2019-12-06 19:33:02'),(27,'user','127.0.0.1','2019-12-06 19:36:50'),(28,'scaler7','127.0.0.1','2019-12-06 19:37:01'),(29,'scaler7','127.0.0.1','2019-12-06 19:43:53'),(30,'user','0:0:0:0:0:0:0:1','2019-12-06 19:58:32'),(31,'user','0:0:0:0:0:0:0:1','2019-12-06 20:08:03'),(32,'scaler7','0:0:0:0:0:0:0:1','2019-12-06 20:09:34'),(33,'user','111.175.140.28','2019-12-06 20:23:27'),(34,'user','223.104.63.21','2019-12-06 20:28:00'),(35,'user','111.175.140.28','2019-12-06 20:28:15'),(36,'user','117.158.200.38','2019-12-06 20:28:28'),(37,'user','117.136.124.43','2019-12-06 20:32:16'),(38,'user','223.150.32.239','2019-12-06 20:34:54'),(39,'user','223.150.32.239','2019-12-06 20:36:58'),(40,'user','223.150.32.239','2019-12-06 20:38:55'),(41,'user','103.228.209.23','2019-12-06 21:23:25'),(42,'user','183.238.59.139','2019-12-06 21:31:34'),(43,'user','111.175.140.28','2019-12-06 21:48:37'),(44,'user','111.175.140.28','2019-12-07 09:12:51'),(45,'scaler7','111.175.140.28','2019-12-07 09:14:13'),(46,'user','111.175.140.28','2019-12-07 09:44:50'),(47,'user','111.175.140.28','2019-12-07 09:56:29');

/*Table structure for table `u_permission` */

DROP TABLE IF EXISTS `u_permission`;

CREATE TABLE `u_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(30) NOT NULL COMMENT '权限名',
  `state` int(11) NOT NULL DEFAULT '1' COMMENT '状态 1有效 0无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

/*Data for the table `u_permission` */

insert  into `u_permission`(`id`,`name`,`state`) values (1,'article:insert',1),(2,'article:update',1),(3,'article:delete',1),(4,'comment:insert',1),(5,'comment:update',1),(6,'comment:delete',1),(7,'category:insert',1),(8,'category:update',1),(9,'category:delete',1),(10,'attach:upload',1),(11,'attach:delete',1),(12,'link:insert',1),(13,'link:update',1),(14,'link:delete',1),(15,'category:insert',1),(16,'category:delete',1),(17,'comment:delete',1),(18,'comment:pass',1);

/*Table structure for table `u_rank` */

DROP TABLE IF EXISTS `u_rank`;

CREATE TABLE `u_rank` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(30) NOT NULL COMMENT '等级名称',
  `rank_detail` varchar(255) NOT NULL COMMENT '等级说明',
  `rank_icon` varchar(255) NOT NULL COMMENT '等级图标',
  `state` int(11) NOT NULL COMMENT '状态 1有效 0无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `u_rank` */

insert  into `u_rank`(`id`,`name`,`rank_detail`,`rank_icon`,`state`) values (1,'root','666','#',1);

/*Table structure for table `u_role` */

DROP TABLE IF EXISTS `u_role`;

CREATE TABLE `u_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) NOT NULL COMMENT '角色名',
  `state` int(11) NOT NULL DEFAULT '1' COMMENT '1有效 0无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `u_role` */

insert  into `u_role`(`id`,`name`,`state`) values (1,'root',1),(2,'user',1);

/*Table structure for table `u_role_permission` */

DROP TABLE IF EXISTS `u_role_permission`;

CREATE TABLE `u_role_permission` (
  `r_id` int(11) NOT NULL COMMENT '角色id',
  `p_id` int(11) NOT NULL COMMENT '权限id',
  KEY `fk_urp_rid_role_id` (`r_id`),
  KEY `fk_urp_pid_permission_id` (`p_id`),
  CONSTRAINT `fk_urp_pid_permission_id` FOREIGN KEY (`p_id`) REFERENCES `u_permission` (`id`),
  CONSTRAINT `fk_urp_rid_role_id` FOREIGN KEY (`r_id`) REFERENCES `u_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `u_role_permission` */

insert  into `u_role_permission`(`r_id`,`p_id`) values (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18);

/*Table structure for table `u_user` */

DROP TABLE IF EXISTS `u_user`;

CREATE TABLE `u_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户自增长id',
  `name` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `salt` varchar(50) DEFAULT NULL COMMENT '盐',
  `qq` varchar(20) NOT NULL COMMENT '密码',
  `create_time` varchar(16) NOT NULL COMMENT '创建时间',
  `score` int(11) NOT NULL COMMENT '积分',
  `face_img` varchar(255) NOT NULL COMMENT '图片地址',
  `last_login_time` varchar(16) NOT NULL COMMENT '上次登录时间',
  `last_login_ip` varchar(20) NOT NULL COMMENT '上次登陆ip地址',
  `state` int(11) NOT NULL COMMENT '状态 1可用 0禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `u_user` */

insert  into `u_user`(`id`,`name`,`password`,`salt`,`qq`,`create_time`,`score`,`face_img`,`last_login_time`,`last_login_ip`,`state`) values (1,'scaler7','d022646351048ac0ba397d12dfafa304',NULL,'1019423301','2019-12-4 00:00:',0,'#','2019-12-4 00:00:','127.0.0.1',1),(2,'user','d022646351048ac0ba397d12dfafa304',NULL,'123','2019-12-5 00:00:',0,'#','2019-12-5 00:00:','114.55.27.97',1);

/*Table structure for table `u_user_role` */

DROP TABLE IF EXISTS `u_user_role`;

CREATE TABLE `u_user_role` (
  `u_id` int(11) NOT NULL,
  `r_id` int(11) NOT NULL,
  PRIMARY KEY (`u_id`,`r_id`),
  KEY `fk_rId_role_id` (`r_id`),
  CONSTRAINT `fk_rId_role_id` FOREIGN KEY (`r_id`) REFERENCES `u_role` (`id`),
  CONSTRAINT `fk_uId_user_id` FOREIGN KEY (`u_id`) REFERENCES `u_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `u_user_role` */

insert  into `u_user_role`(`u_id`,`r_id`) values (1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
