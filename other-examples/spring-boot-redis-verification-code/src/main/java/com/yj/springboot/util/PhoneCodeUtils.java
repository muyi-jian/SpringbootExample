package com.yj.springboot.util;

import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.util.Random;

public class PhoneCodeUtils {

    // 1、生成6位验证码
    public static String getCode(){
        String code = "";
        Random random = new Random();
        for (int i=0;i<6;i++){
            code += random.nextInt(10);
        }
        return code;
    }
    // 2.每个手机每天只能发送三次，验证码放到redis中，设置过期时间120
    public static String verifyCode(String phone, RedisTemplate redisTemplate){
        System.out.println("jinru===");
        //验证码key
        String codeKey = "VerifyCode"+phone+":code";
        //发送次数key
        String countKey = "VerifyCode"+phone+":count";

        //获取发送次数key
        Object count = redisTemplate.opsForValue().get(countKey);

        System.out.println("count:::"+count);
        if (count == null){
            // 没有发送过，第一次发送，设置次数为1
            redisTemplate.opsForValue().setIfAbsent(countKey,1, Duration.ofHours(24));
        }else if ((Integer)count<=2){
            redisTemplate.opsForValue().increment(countKey,1);
        }else if ((Integer)count>2){
            System.out.println("已经发送第三次，不能再发送了");

        }
        //发送验证码放到redis里面
        String code = getCode();
        redisTemplate.opsForValue().setIfAbsent(codeKey,code,Duration.ofMinutes(3));
        return code;
    }
    // 3、验证码校验
    public static String getRedisCode(String phone,String code,RedisTemplate redisTemplate){
        //验证码key
        String codeKey = "VerifyCode"+phone+":code";
        String redisCode = (String) redisTemplate.opsForValue().get(codeKey);
        if ( redisCode == null){
            return "验证失败";
        }
        if (redisCode.equals(code)) {
            System.out.println("验证成功");
            //验证功能后删除，不能再使用
            redisTemplate.delete(codeKey);
            return "验证成功";
        }else{
            System.out.println("验证失败");
            return "验证失败";
        }

    }
}
