package spring.boot.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
@Component
public class JedisUtils {
    @Autowired
    JedisPool jedisPool;

    public Jedis getJedis(){
      return jedisPool.getResource();
    }

    public void jedisClose(Jedis jedis){
        if (jedis != null){
            jedis.close();
        }
    }

}
