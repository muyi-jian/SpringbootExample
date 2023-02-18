package spring.boot.redis.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import spring.boot.redis.config.JedisUtils;

import javax.annotation.Resource;

@RestController
public class JedisController {


    @Resource
    JedisUtils jedisUtils;
    @GetMapping("/test")
    public String testJedis(){
        Jedis jedis = jedisUtils.getJedis();
        jedis.set("name","tom");
        String name = jedis.get("name");
        System.out.println("name: "+name);
        jedisUtils.jedisClose(jedis);
        return name;
    }
}
