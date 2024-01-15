package com.yj.redis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.Set;

@SpringBootTest
class SpringBoot3RedisApplicationTests {

	@Test
	void contextLoads() {
	}

	// 注入RedisTemplate
	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Autowired
	RedisTemplate redisTemplate;

	@Test
    public void test(){
		// 将键为"name"，值为"zhangsan"的键值对存储到Redis数据库中。
		stringRedisTemplate.opsForValue().set("a","李四");
		// 断言方法，用于比较两个值是否相等。如果相等，则测试通过；如果不相等，则抛出异常。
		Assertions.assertEquals("李四",stringRedisTemplate.opsForValue().get("a"));
    }

	@Test
	public void getVal(){
		// 从redis中获取键值a的数据。
		String a = stringRedisTemplate.opsForValue().get("a");
		System.out.println("a::"+a);

	}

	@Test
	public void testInt(){
		redisTemplate.opsForValue().set("num",10);
		Assertions.assertEquals(10,redisTemplate.opsForValue().get("num"));
        Object num = redisTemplate.opsForValue().get("num");
		System.out.println("---num==="+num);
		// INCR	INCR key-name，将键的存储值加1
		redisTemplate.opsForValue().increment("num");
		Object num1 = redisTemplate.opsForValue().get("num");
		System.out.println("---num1==="+num1);
		// DECR	DECR key-name，将键的存储值减一
		redisTemplate.opsForValue().decrement("num");
        Object num2 = redisTemplate.opsForValue().get("num");
        System.out.println("---num2==="+num2);
		// DECRBY	DECRBY key-name amount，将键存储值减少amount
		redisTemplate.opsForValue().decrement("num",2);
        Object num3 = redisTemplate.opsForValue().get("num");
        System.out.println("---num3==="+num3);
		// INCRBY	INCRBY key-name amount，将键存储值增加amount
		redisTemplate.opsForValue().increment("num",4);
        Object num4 = redisTemplate.opsForValue().get("num");
        System.out.println("---num4==="+num4);

	}

	@Test
	public void testStr(){
		// APPEND	APPEND key-name value，将给定value追加到给定键存储值末尾
		redisTemplate.opsForValue().append("a","222");

	}
	@Test
	public void testStr1(){
		stringRedisTemplate.opsForValue().set("e","hello words");
		// 断言方法，用于比较两个值是否相等。如果相等，则测试通过；如果不相等，则抛出异常。
		Assertions.assertEquals("hello words",stringRedisTemplate.opsForValue().get("e"));
		// GETRANGE	GETRANGE key-name start end，获取偏移量start-end的子串
		String a = stringRedisTemplate.opsForValue().get("e", 2, 4);
		System.out.println("GETRANGE==="+a);

		// SETRANGE	SETRANGE key-name offset value，从offset开始设置value覆盖后续字符串内容 相当于替换中间截取相同长度的字符串替换掉，
		// 而不是把后面的全部替换掉
		stringRedisTemplate.opsForValue().set("e","eeeee",2);

	}

	@Test
	public void testList(){
		// https://blog.csdn.net/qq_36566262/article/details/124894808
		// RPUSH	RPUSH key-name value……，将一个多个value从列表右侧推入
		redisTemplate.opsForList().rightPushAll("list1", "aa", "bb", "cc", "dd");
        // start、end：首尾下标；0 - -1获取集合的所有元素
		System.out.println(redisTemplate.opsForList().range("list1", 0, -1));
		Assertions.assertEquals(redisTemplate.opsForList().index("list1", 1), "bb");
		redisTemplate.opsForList().trim("list1", 0, 2);
		System.out.println(redisTemplate.opsForList().range("list1", 0, -1));
		// LPUSH	LPUSH key-name value……，将一个多个value从列表左侧推入
		redisTemplate.opsForList().leftPushAll("list2", "aa","bb","cc","dd");
		// start、end：首尾下标；0 - -1获取集合的所有元素
		System.out.println(redisTemplate.opsForList().range("list2", 0, -1));
		redisTemplate.opsForList().trim("list2", 0, 2);
		System.out.println(redisTemplate.opsForList().range("list2", 0, -1));

	}
	@Test
	public void testList3(){
		// https://blog.csdn.net/qq_36566262/article/details/124894808
		redisTemplate.opsForList().rightPushAll("list3", "aa", "bb", "cc", "dd");
		System.out.println(redisTemplate.opsForList().range("list3", 0, -1));
		// RPOP	RPOP key-name ，移除并返回最右侧元素
		redisTemplate.opsForList().rightPop("list3");
		System.out.println(redisTemplate.opsForList().range("list3", 0, -1));
		redisTemplate.opsForList().rightPushAll("list4", "aa", "bb", "cc", "dd");
		System.out.println(redisTemplate.opsForList().range("list4", 0, -1));
		// LPOP    LPOP key-name ，移除并返回最左侧元素
		redisTemplate.opsForList().leftPop("list3");
		System.out.println(redisTemplate.opsForList().range("list3", 0, -1));
		redisTemplate.opsForList().rightPushAll("list5", "aa", "bb", "cc", "dd");
		System.out.println(redisTemplate.opsForList().range("list5", 0, -1));
        // LINDEX    LINDEX key-name offset，返回列表中偏移量为offset的元素
		System.out.println(redisTemplate.opsForList().index("list5",1));
        // LRANGE    LRANGE key-name start end，返回start-end范围的所以元素
		System.out.println(redisTemplate.opsForList().range("list5",0,2));
        // LTRIM    LTRIM key-name start end，修建列表为start-end范围内元素
		redisTemplate.opsForList().trim("list5",0,2);
		System.out.println(redisTemplate.opsForList().range("list5", 0, -1));

	}


	@Test
	public void testCollection(){

		// SADD	SADD key-name item……，将一个或多个item添加到集合
		redisTemplate.opsForSet().add("set1", "item1", "item2", "item3");
		// SREM	SREM key-name item……，将一个或多个元素从集合移除
		redisTemplate.opsForSet().remove("set1", "item2");
		// SISMEMBER	SISMEMBER key-name item，检查item是否存在于集合中
		boolean isMember = redisTemplate.opsForSet().isMember("set1", "item1");
		System.out.println(isMember);
		// SCARD	SCARD key-name，返回集合包含元素个数
		long cardinality = redisTemplate.opsForSet().size("set1");
		System.out.println(cardinality);
		// SMEMBERS	SMEMBERS key-name，返回集合包含的所以元素
		Set<String> members = redisTemplate.opsForSet().members("set1");
		System.out.println(members);
		// SRANDMEMBER	SRANDMEMBER key-name count，从集合随机返回count个元素
		List<String> randomMembers = redisTemplate.opsForSet().randomMembers("set1", 2);
		System.out.println(randomMembers);  // 随机输出[item1, item3]中的一个或两个元素

		// SPOP	SPOP key-name，随机移除集合中一个元素，并返回
		String poppedItem = (String) redisTemplate.opsForSet().pop("set1");
		System.out.println(poppedItem);  // 随机输出"item1"或"item3"

		// SMOVE	SMOVE source-key dest-key item，如果元素存在于源key，则移动到目的key
		// redisTemplate.opsForSet().move("source_set", "destination_set", "item1");

	}

	@Test
	public void testHash(){
		// HMSET	HMSET key-name key value [key value……]，设置散列的一个或多个键值对
		redisTemplate.opsForHash().put("hash1", "key1", "value1");
		// HMGET	HMGET key-name key [key……]，从散列中取出一个或n个键值对
		System.out.println(redisTemplate.opsForHash().get("hash1", "key1"));
		// HDEL	HDEL key-name key [key……]，从散列删除一个多个键值对
		redisTemplate.opsForHash().delete("hash1", "key1");
		// HLEN	HLEN key-name，返回散列的键值对数量
		Long hash1 = redisTemplate.opsForHash().size("hash1");
		System.out.println(hash1);

	}
	@Test
	public void testHash2(){
		// HEXISTS	HEXISTS key-name key，检查key是否存在于散列中
		// HKEYS	HKEYS key-name，返回散列所有键
		// HVALS	HVALS key-name，返回散列所有值
		// HGETALL	HGETALL key-name 返回散列所有键值对
		// HINCRBY	HINCRBY key-name key increment，将key的值增加increment

	}

	@Test
	public void testSet(){
		// ZADD	ZADD key-name score member [score member……]，添加一个多个成员
		// ZREM	ZREM key-name member [member……]，移除一个或多个成员
		// ZCARD	ZCARD key-name，返回成员个数
		// ZINCRBY	ZINCRBY key-name increment member，增加成员对应分数
		// ZCOUNT	ZCOUNT key-name min max，返回分值介于min，max直接成员个数
		// ZRANK	ZRANK key-name member，返回成员的排名
		// ZSCORE	ZSCORE key-name member，返回成员分数
		// ZRANGE	ZRANGE key-name start end [withscores]，返回介于指定范围的成员，可选项带分数返回
	}

}
