package spring.boot.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@SpringBootTest
class SpringBootRedisJedisApplicationTests {

    @Autowired
    JedisPool jedisPool;

    @Test
    void contextLoads() {
        Jedis jedis = new Jedis("192.168.56.20",6379);
        jedis.auth("123456");
        String pong = jedis.ping();
        System.out.println("连接成功："+pong);
        jedis.close();
    }
    @Test
    void testJedis(){
        System.out.println(jedisPool);

        Jedis jedis = jedisPool.getResource();
        String pong = jedis.ping();
        System.out.println("连接成功："+pong);
        jedis.close();

    }




}
