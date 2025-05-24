package com.fincodehub.shiro.util;


import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @author YangJian
 * @version 1.0.0
 * @title PwdUtil
 * @create 2025/5/24 12:43
 * @description <TODO description class purpose>
 */
public class PwdUtil {

    public static void main(String[] args) {
        //密码明文
        String password = "123";
        //使用SHA-256加密

        Sha256Hash sha256Hash = new Sha256Hash(password);
        System.out.println("SHA-256加密："+sha256Hash.toHex());
        Sha256Hash sha256Hash1 = new Sha256Hash(password, "salt");
        System.out.println("SHA-256带盐加密："+sha256Hash1.toHex());
        //多次迭代加密
        Sha256Hash sha256Hash2 = new Sha256Hash(password, "salt", 3);
        System.out.println("SHA-256带盐3次加密："+sha256Hash2.toHex());

        //父类多次迭代加密
        SimpleHash simpleHash = new SimpleHash("SHA-256", password, "salt", 3);
        System.out.println("父类HA-256带盐3次加密："+simpleHash.toHex());

    }
}
