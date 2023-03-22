### SpringBoot之mongoDB

MongoDB是一个**由C++语言编写的、基于分布式文件存储的、开源、高性能、无模式**的文档型数据库，在高负载的情况下，添加更多的节点，可以保证服务器性能，MongoDB 旨在为WEB应用提供可扩展的高性能数据存储解决方案

MongoDB是一个介于关系数据库和非关系数据库之间的产品，是非关系数据库当中功能最丰富，最像关系数据库的。它支持的数据结构非常松散，是类似json的bson格式，因此可以存储比较复杂的数据类型。

Mongo最大的特点是它支持的查询语言非常强大，其语法有点类似于面向对象的查询语言，几乎可以实现类似关系数据库单表查询的绝大部分功能，而且还支持对数据建立索引。

MongoDB 将数据存储为一个文档，数据结构由键值(key=>value)对组成。

windows版本下载

```
https://www.mongodb.com/try/download/community
```

1. 下载好MongoDB，然后双击.msi文件。
2. 点击next
3. 选择custom
4. 选择安装目录
5. 配置名称、数据存放文件夹、日志存放文件
6. 点击next ，取消选择Install MongoDB Compass（这里重点强调取消选择，否则你会后悔的！！！）
7. 点击install安装

安装完成后需要在data文件夹中创建db文件夹

![image-20230222195325293](F:\学习笔记\Notes\content\Java\Spring系列\SpringbootExample\image\image-20230222195325293.png)

配置环境变量：此电脑=>>右击点击属性=>>选择高级系统设置=>>点击环境变量=>>选择path=>>点击编辑=>>然后新建=>选择bin目录路径=>>确定

![image-20230222195517158](F:\学习笔记\Notes\content\Java\Spring系列\SpringbootExample\image\image-20230222195517158.png)

![image-20230222195458698](F:\学习笔记\Notes\content\Java\Spring系列\SpringbootExample\image\image-20230222195458698.png)

配置存储路径并打开mongo服务

注意：必须在自己电脑根目录运行，否则会报错！！！

```
mongod --dbpath D:\software\install\develop\MongoDB\Server\6.0\data\db

```

配置Mongo服务
让mongo服务自己启动，不用我们认为去控制，这里我们需要在log文件夹下面创建一个db.log文件

浏览器访问：http://localhost:27017/

```
出现以下内容说明安装成功
It looks like you are trying to access MongoDB over HTTP on the native driver port.
原因：
MongoDB的HTTP服务没有开启，需要开启。
解决方案： mongodb --httpinterface进行启动.
```

```
安装服务
mongod --dbpath "D:\software\install\develop\MongoDB\Server\6.0\data\db" --logpath "D:\software\install\develop\MongoDB\Server\6.0\log\mongod.log"  --install --serviceName "MongoDB"
```

在MongoDB6之前，我们配置完环境变量后，可以直接通过终端输入"mongo"进入Mongoshell，但MongoDB6没有mong.exe和mongdb.exe，终端输入"mongo"会报错：`'mongo' 不是内部或外部命令，也不是可运行的程序或批处理文件。`

要想通过命令行启动mongoDB需要自己下载一个Mongoshell，下载及使用相当简单

关闭服务和删除进程

```
　　> NET stop MongoDB (关闭服务)
 
　　> D:\soft\java\MongoDB\Server\4.0\bin>mongod --dbpath "D:\soft\java\MongoDB\data\db" --logpath "D:\soft\java\MongoDB\data\log\mongodb.log" --remove --serviceName "MongoDB"
 
　　 (删除，注意不是--install了）
```

其他命令

```
 
客户端mongovue
 
下表为mongodb启动的参数说明：
参数	描述
--bind_ip	绑定服务IP，若绑定127.0.0.1，则只能本机访问，不指定默认本地所有IP
--logpath	定MongoDB日志文件，注意是指定文件不是目录
--logappend	使用追加的方式写日志
--dbpath	指定数据库路径
--port	指定服务端口号，默认端口27017
--serviceName	指定服务名称
--serviceDisplayName	指定服务名称，有多个mongodb服务时执行。
--install	指定作为一个Windows服务安装。
--auth 是否需要认证，
MongoDB后台管理 Shell
如果你需要进入MongoDB后台管理，你需要先打开mongodb装目录的下的bin目录，然后执行mongo.exe文件，MongoDB Shell是MongoDB自带的交互式Javascript shell,用来对MongoDB进行操作和管理的交互式环境。
当你进入mongoDB后台后，它默认会链接到 test 文档（数据库）：
> mongo
MongoDB shell version: 3.0.6
connecting to: test
……
由于它是一个JavaScript shell，您可以运行一些简单的算术运算:
> 2 + 2
4
>db 命令用于查看当前操作的文档（数据库）：
> db
test
>
插入一些简单的记录并查找它：
> db.runoob.insert({x:10})
WriteResult({ "nInserted" : 1 })
> db.runoob.find()
{ "_id" : ObjectId("5604ff74a274a611b0c990aa"), "x" : 10 }
>
第一个命令将数字 10 插入到 runoob 集合的 x 字段中。
```

#### springboot 整合mongoDB

pom

```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>
<dependency>
	<groupId>org.apache.commons</groupId>
	<artifactId>commons-lang3</artifactId>
</dependency>
<dependency>
	<groupId>org.projectlombok</groupId>
	<artifactId>lombok</artifactId>
	<optional>true</optional>
</dependency>
```

application.yml

```
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/test
      # uri: mongodb://用户名:密码@服务器IP:端口/数据库名
      # 上方为明确指定某个数据的用户进行连接
      # 也可以使用admin 数据库中的用户进行连接  统一到admin 数据库进行认证
      # admin 用户认证 url 写法： mongodb://账户:密码%40@ip:端口/数据库名?authSource=admin&authMechanism=SCRAM-SHA-1

```

User

```
package spring.boot.mongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection="YJ")
public class User {
    private long id;
    private String name;
    private int age;


}

```

使用MongoTemplate

```

@RequiredArgsConstructor
@RestController
public class MongoController {

    private final MongoTemplate mongoTemplate;

    @RequestMapping("/mongo/insert")
    public User insert(@RequestParam("name") String name, @RequestParam("age") int age) {
        // 新增
        User user = new User(RandomUtils.nextInt(), name, age);
        mongoTemplate.insert(user, "YJ");

        // 查询
        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.findOne(query, User.class, "YJ");
    }


}
```

继承MongoRepository

```

@RequiredArgsConstructor
@RestController
public class MongoController {

    private final UserRepository userRepository;

    @RequestMapping("/mongo/save")
    public User repoInsert(@RequestParam("name") String name, @RequestParam("age") int age) {
        // 新增
        User user = new User(RandomUtils.nextInt(), name, age);
        userRepository.save(user);

        // 查询
        return userRepository.findByName(name).get(0);
    }
}
```



java类转换为mongodb的文档,它有以下几种注释：

 * @Id - 文档的唯一标识，在mongodb中为ObjectId，它是唯一的，通过时间戳+机器标识+进程ID+自增计数器（确保同一秒内产生的Id不会冲突）构成。

 * @Document - 把一个java类声明为mongodb的文档，可以通过collection参数指定这个类对应的文档。

 * @Indexed - 声明该字段需要索引，建索引可以大大的提高查询效率。

 * @Transient - 映射忽略的字段，该字段不会保存到MongoDB

 * @CompoundIndex - 复合索引的声明，建复合索引可以有效地提高多字段的查询效率。

 * @PersistenceConstructor - 声明构造函数，作用是把从数据库取出的数据实例化为对象。该构造函数传入的值为从DBObject中取出的数据。