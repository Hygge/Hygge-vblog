/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80000
 Source Host           : localhost:3306
 Source Schema         : myblog

 Target Server Type    : MySQL
 Target Server Version : 80000
 File Encoding         : 65001

 Date: 13/03/2022 15:13:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for v_article
-- ----------------------------
DROP TABLE IF EXISTS `v_article`;
CREATE TABLE `v_article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标题',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `context` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '内容',
  `number_view` int(11) NULL DEFAULT 0 COMMENT '浏览量',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `categorys_id` int(11) NULL DEFAULT 1 COMMENT '分类id 默认分类1',
  `created_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `status` int(2) NOT NULL COMMENT '文章状态 0回收站 1已发布',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章表';

-- ----------------------------
-- Records of v_article
-- ----------------------------
BEGIN;
INSERT INTO `v_article` VALUES (9, '测试篇一', '通过之前我们再定义页面路由时候的的meta信息，指定requireAuth: true，需要登录才能访问，因此这里我们在每次路由之前（router.beforeEach）判断token的状态，觉得是否需要跳转到登录页面。', '测试试试水\n![COS海琴烟 同人 cosplay美女3440x1440带鱼屏壁纸_彼岸图网.jpg](http://corehome0.oss-cn-hongkong.aliyuncs.com/images/COS%E6%B5%B7%E7%90%B4%E7%83%9F%20%E5%90%8C%E4%BA%BA%20cosplay%E7%BE%8E%E5%A5%B33440x1440%E5%B8%A6%E9%B1%BC%E5%B1%8F%E5%A3%81%E7%BA%B8_%E5%BD%BC%E5%B2%B8%E5%9B%BE%E7%BD%91_2022-02-07T11%3A42%3A27.585-168e835c-7dce-4ce4.jpg?Expires=1646032736&OSSAccessKeyId=LTAI4G3jsiM4zfyA3geoN3eX&Signature=7lNDq5YSuBveZGFKQ9DIfSJXyes%3D)', 35, 1, 1, '2022-02-07 11:43:59', 1), (13, '测试篇四、', 'test', '# 你好啊\nhello world', 1, 1, 1, '2022-02-07 12:51:48', 1), (14, '111111111111111', '111111111', '帆帆帆帆', 0, 1, 2, '2022-02-07 12:53:59', 0), (15, '测试篇五、', '帆帆帆帆', '啊啊啊啊啊啊啊啊啊啊啊啊啊啊1111111111\n![原神 雷电将军 大腿 星空4k动漫壁纸_彼岸图网.jpg](http://corehome0.oss-cn-hongkong.aliyuncs.com/images/%E5%8E%9F%E7%A5%9E%20%E9%9B%B7%E7%94%B5%E5%B0%86%E5%86%9B%20%E5%A4%A7%E8%85%BF%20%E6%98%9F%E7%A9%BA4k%E5%8A%A8%E6%BC%AB%E5%A3%81%E7%BA%B8_%E5%BD%BC%E5%B2%B8%E5%9B%BE%E7%BD%91_2022-02-07T14%3A00%3A39.392-26f7e493-be7e-4e29.jpg?Expires=1646041028&OSSAccessKeyId=LTAI4G3jsiM4zfyA3geoN3eX&Signature=lYiUKqKIlvkI%2FUnXJc9ze3TifkA%3D)', 3, 1, 2, '2022-02-07 12:55:47', 1), (16, '反反复复烦烦烦', '1', '2', 3, 1, 1, '2022-02-07 14:41:40', 1), (19, 'element-ui 表单错误：Cannot read property \'validate\' of undefined\"', 'element -ui表单校验的坑', '通常 Cannot read property \'validate\' of undefined ，一般情况是提交的 click 事件中, 传参没有带引号导致的，只需 confirmAdd(\'addform\') 即可。\n\n但是添加了 confirmAdd(\'addform\')之后，依然还是显示那个问题。\n\n终于发现 form 表单中 ref 居然绑定的是 变量，而非表单名称（字符串）。\n\n```html\n   <el-form :model=\"loginForm\" :rules=\"rules\" status-icon :ref=\"loginForm\" label-width=\"100px\" class=\"demo-ruleForm\">\n  \n  <el-form-item>\n        <el-button type=\"primary\" @click=\"submitForm(loginForm)\">登录</el-button>\n      </el-form-item>\n    </el-form>\n\n```\n', 37, 1, 1, '2022-02-09 11:33:08', 1), (20, 'SpringBoot整合redis缓存使用', 'SpringBoot整合redis缓存使用', '     一开始写增删改查基本都是直接对库操作，接口调用量少的时候，性能问题几乎不存在，但是数据量级上升之后，这些增删改查接口的压力也会大大上升，甚至会出现慢查询的情况，出现较大的延迟。这时候机智的小伙伴会使用索引，没错，索引可以解决一部分查询造成的性能问题。那么如何才能进一步提升查询的性能呢？对于读多写少的表可以使用缓存，那么将大大减少读取数据库的次数。\n\n \n\n \n\n一.Redis(缓存)常见的问题\n1.缓存穿透\n        缓存穿透指查询一个一定不存在的数据，由于缓存不命中就会从数据库查询，查不到数据则不写入缓存，这将导致这个不存在的数据都要到数据库查询，造成缓存穿透。这种情况其实就是因为数据库不存在的数据无法写入缓存，解决这个问题的方法，就是把这种数据库查不到值的情况也考虑进去。\n\n解决方案：\n缓存空值，将数据库查询不到的则作为空值存入，那么下次可以从缓存中获取key值是空值可以判断出数据库不存在这个值。\n使用布隆过滤器，布隆过滤器能判断一个 key 一定不存在（不保证一定存在，因为布隆过滤器结构原因，不能删除，但是旧值可能被新值替换，而将旧值删除后它可能依旧判断其可能存在），在缓存的基础上，构建布隆过滤器数据结构，在布隆过滤器中存储对应的 key，如果存在，则说明 key 对应的值为空。\n2.缓存击穿\n       缓存击穿针对某些高频访问的key，当这个key失效的瞬间，大量的请求击穿了缓存，直接请求数据库。\n\n解决方案:\n设置二级缓存\n设置高频key缓存永不过期\n使用互斥锁，在执行过程中，如果缓存过期，那么先获取分布式锁，在执行从数据库中加载数据，如果找到数据就加入缓存，没有就继续该有的动作，在这个过程中能保证只有一个线程操作数据库，避免了对数据库的大量请求。\n3.缓存雪崩\n        缓存雪崩指缓存服务器重启、或者大量缓存集中在某一个时间段失效，这样失效的时候，会给后端系统带来很大压力，造成数据库的故障。\n\n解决方法：\n缓存高可用设计，Redis sentinel和Redis Cluster等\n请求限流与服务熔断降级机制，限制服务请求次数，当服务不可用时快速熔断降级。\n设置缓存过期时间一定的随机分布，避免集中在同一时间缓存失效，可以在设计时将时间定为一个固定值+随机值。\n定时更新缓存策略，对于实时性要求不高的数据，定时进行更新。\n4.分布式锁\n    我们知道目前常见的分布式锁有基于zookeeper和基于redis的，基于zookeeper是一个持久节点下的临时顺序节点的抢占，是一个队列机制。而基于redis则是对某个redis缓存key的抢占。两者优缺点如下：\n\n 	优点	缺点\nzookeeper	\n1.有封装好的框架，容易实现\n\n2.有等待锁的队列，大大提升抢锁效率。\n\n添加和删除节点性能较低\nredis	Set和Del指令性能较高	\n1.实现复杂，需要考虑超时，原子性，误删等情形。\n\n2.没有等待锁的队列，只能在客户端自旋来等待，效率低下。\n\n    可以看出，redis实现分布式锁需要设置超时时间，如果不设置超时时间会出现什么问题呢？如果获取锁之后在解锁过程中出现宕机，则会导致死锁现象。因此需要设置超时时间来避免死锁现象。在redis2.8版本之前获取锁及设置超时时间分为setnx和expire两个步骤完成，如果这两个步骤之间出现宕机现象，依然会存在死锁现象。因此，redis2.8版本做了修改，将setnx和expire两个作为一个方法实现，避免了宕机引起的死锁现象。\n\n \n\n二.缓存的使用(springboot整合redis)\n1.引入依赖:\n<dependency>\n            <groupId>org.springframework.boot</groupId>\n            <artifactId>spring-boot-starter-data-redis</artifactId>\n        </dependency>\n \n        <dependency>\n            <groupId>org.springframework.boot</groupId>\n            <artifactId>spring-boot-starter-cache</artifactId>\n        </dependency>\n2.配置\napplication.yml文件\n\nspring:\n   redis:\n    ## Redis数据库索引（默认为0）\n    database: 0\n    ## Redis服务器地址\n    host: 127.0.0.1\n    ## Redis服务器连接端口\n    port: 6379\n    ## Redis服务器连接密码（默认为空）\n    password: root\n    jedis:\n      pool:\n        ## 连接池最大连接数（使用负值表示没有限制）\n        #spring.redis.pool.max-active=8\n        max-active: 8\n        ## 连接池最大阻塞等待时间（使用负值表示没有限制）\n        #spring.redis.pool.max-wait=-1\n        max-wait: -1\n        ## 连接池中的最大空闲连接\n        #spring.redis.pool.max-idle=8\n        max-idle: 8\n        ## 连接池中的最小空闲连接\n        #spring.redis.pool.min-idle=0\n        min-idle: 0\n    ## 连接超时时间（毫秒）\n    timeout: 1200\nApplication类----@EnableCaching启动缓存注解\n\n@SpringBootApplication\n@EnableTransactionManagement\n@MapperScan(\"com.carson.cachedemo.mapper\")\n@EnableScheduling\n@EnableCaching\npublic class CachedemoApplication {\n \n    public static void main(String[] args) {\n        SpringApplication.run(CachedemoApplication.class, args);\n    }\n \n}\n提供使用缓存的接口类，该类对user对象做了缓存，即user表中的每一条记录做了缓存，缓存key值是userid，缓存value值为user对象。其中常用的缓存注解\n\n其中获取记录需要添加注解:@Cache(value=\"xxx\",key=\"xxxx\",unless=\"#result==null\")\n\n删除则用@CacheEvict(value=\"xxx\",key=\"xxxxx\")\n\n更新则用 @CachePut(value=\"xxx\",key=\"xxxx\")\n\n@Cacheable	主要针对方法配置，能够根据方法的请求参数对其结果进行缓存\n@CacheEvict	清空缓存\n@CachePut	保证方法被调用，又希望结果被缓存。\n@Service\npublic class UserManageServiceImpl implements UserManageService{\n \n    @Autowired\n    UserManageMapper userManageMapper;\n \n \n    /**\n     * 根据用户id获取用户信息\n     * @param userid\n     * @return\n     */\n    @Override\n    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)\n    @Cacheable(value = \"user\",key = \"#userid\",unless = \"#result==null\")\n    public User findUser(int userid) {\n        return userManageMapper.findByUserid(userid);\n    }\n \n    /**\n     * 删除的时候需要把好友关系表中的关系也删除。\n     * @param userid\n     * @return\n     */\n    @Override\n    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)\n    @CacheEvict(value = \"user\",key = \"#userid\")\n    public int deleteUser(int userid) {\n \n        int res2 = userManageMapper.deleteFriendByUserid(userid);\n        int res3= userManageMapper.deleteFriendByFriendid(userid);\n        int res1= userManageMapper.deleteByUserid(userid);\n        return res1;\n    }\n \n    /**\n     *  查询所有好友\n     * @return\n     */\n    @Override\n    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)\n    public List<User> findAllUser() {\n        return userManageMapper.findAllUser();\n    }\n \n    /**\n     * 保存用户\n     * @param user\n     * @return\n     */\n    @Override\n    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)\n    public int saveUser(User user) {\n        return userManageMapper.insertUser(user);\n    }\n \n    /**\n     * 更新用户\n     * @param user\n     * @return\n     */\n    @Override\n    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)\n    @CachePut(value = \"user\",key = \"#userid\")\n    public int updateUser(User user) {\n        return userManageMapper.updateUser(user);\n    }\n \n    /**\n     * 获取所有好友信息\n     * @return\n     */\n    @Override\n    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)\n    public List<Friends> getFriends() {\n        return userManageMapper.getFriends();\n    }\n \n    /**\n     * 根据userid获取好友信息\n     * @param userid\n     * @return\n     */\n    @Override\n    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)\n    public Friends getFriendsByUserid(int userid) {\n        return userManageMapper.getFriendsByUserid(userid);\n    }\n \n    /**\n     * 先对friendid进行查询，如果用户表中没有friendid，以防安全，禁止添加，成对添加\n     *\n     * @return\n     */\n    @Override\n    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)\n    public int addFriend(Relation relation) {\n \n       Relation search=userManageMapper.getRelation(relation);\n       if(search!=null){\n           throw new OperationException(Result.FRIENDS_EXIST);\n       }\n       Relation newRelation = new Relation();\n       newRelation.setUserid(relation.getFriendid());\n       newRelation.setFriendid(relation.getUserid());\n       int res1 = userManageMapper.insertFriend(relation);\n       int res2 = userManageMapper.insertFriend(newRelation);\n       return res1*res2;\n    }\n \n    @Override\n    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)\n    public User findByFriendid(int friendid) {\n        return userManageMapper.findByFriendid(friendid);\n    }\n \n    /**\n     * 删除好友关系，逻辑是成对删除\n     * @param relation\n     * @return\n     */\n    @Override\n    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)\n    public int deleteFriend(Relation relation) {\n        Relation search=userManageMapper.getRelation(relation);\n        if(search==null){\n            throw new OperationException(Result.FRIENDS_NOT_EXIST);\n        }\n        Relation newRelation = new Relation();\n        newRelation.setUserid(relation.getFriendid());\n        newRelation.setFriendid(relation.getUserid());\n        int res1 = userManageMapper.deleteFriend(relation);\n        int res2 = userManageMapper.deleteFriend(newRelation);\n        return res1*res2;\n \n    }\n \n    /**\n     * 用于查询是否存在该用户\n     * @param login\n     * @return\n     */\n    @Override\n    public User search(Login login) {\n        return userManageMapper.searchForLogin(login);\n    }\n \n\n————————————————\n版权声明：本文为CSDN博主「carson0408」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。\n原文链接：https://blog.csdn.net/carson0408/article/details/104664552/', 2, 1, 2, '2022-02-10 15:31:13', 1);
COMMIT;

-- ----------------------------
-- Table structure for v_categorys
-- ----------------------------
DROP TABLE IF EXISTS `v_categorys`;
CREATE TABLE `v_categorys`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '分类表';

-- ----------------------------
-- Records of v_categorys
-- ----------------------------
BEGIN;
INSERT INTO `v_categorys` VALUES (1, '默认分类'), (2, 'java'), (3, 'python'), (4, 'js');
COMMIT;

-- ----------------------------
-- Table structure for v_comment
-- ----------------------------
DROP TABLE IF EXISTS `v_comment`;
CREATE TABLE `v_comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '用户id',
  `article_id` int(11) NOT NULL COMMENT '文章id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论昵称',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论者邮箱',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '链接',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评论表';

-- ----------------------------
-- Records of v_comment
-- ----------------------------
BEGIN;
INSERT INTO `v_comment` VALUES (1, '测试', NULL, 19, 'hygge', '1462266243@qq.com', ''), (2, '你好啊', NULL, 19, 'admin', 'zeng164@outlook.com', '12234'), (3, '你好', NULL, 9, 'test', 'hygge20@outlook.com', 'http://localhost:8080/#/'), (4, '第三条评论', NULL, 20, 'test', 'jzeng411@gmail.com', '');
COMMIT;

-- ----------------------------
-- Table structure for v_friend_link
-- ----------------------------
DROP TABLE IF EXISTS `v_friend_link`;
CREATE TABLE `v_friend_link`  (
  `id` int(11) NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `avator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像链接',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网站链接',
  `miaosu` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '友情链接';

-- ----------------------------
-- Records of v_friend_link
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for v_tag
-- ----------------------------
DROP TABLE IF EXISTS `v_tag`;
CREATE TABLE `v_tag`  (
  `id` int(11) NOT NULL,
  `tag_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '标签表';

-- ----------------------------
-- Records of v_tag
-- ----------------------------
BEGIN;
INSERT INTO `v_tag` VALUES (1, '前端'), (2, 'java');
COMMIT;

-- ----------------------------
-- Table structure for v_tag_relationship
-- ----------------------------
DROP TABLE IF EXISTS `v_tag_relationship`;
CREATE TABLE `v_tag_relationship`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id` int(11) NULL DEFAULT NULL COMMENT '文章id',
  `tag_id` int(11) NULL DEFAULT NULL COMMENT '标签id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章与标签联系表';

-- ----------------------------
-- Records of v_tag_relationship
-- ----------------------------
BEGIN;
INSERT INTO `v_tag_relationship` VALUES (10, 1, 2), (12, 15, 2), (14, 18, 1), (15, 18, 2), (16, 17, 2), (17, 20, 2);
COMMIT;

-- ----------------------------
-- Table structure for v_user
-- ----------------------------
DROP TABLE IF EXISTS `v_user`;
CREATE TABLE `v_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `avator` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `qq` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'QQ',
  `created_date` datetime(0) NOT NULL COMMENT '创建时间',
  `icp` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备案号',
  `signature` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签名',
  `count` int(11) NULL DEFAULT 0 COMMENT '网站浏览量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表';

-- ----------------------------
-- Records of v_user
-- ----------------------------
BEGIN;
INSERT INTO `v_user` VALUES (1, 'hygge', 'e10adc3949ba59abbe56e057f20f883e', '3163367790@qq.com', 'http://corehome0.oss-cn-hongkong.aliyuncs.com/images/as2022-02-27T17%3A04%3A49.211-b76290c4-57e4-4781.jpg?Expires=1647780079&OSSAccessKeyId=LTAI4G3jsiM4zfyA3geoN3eX&Signature=ufknr4DdyIu9G1KY0z2fsr2udoI%3D', '3163367790', '2022-02-05 20:39:07', '赣ICP备2021011250号-1', '你好啊！！！', 3);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
