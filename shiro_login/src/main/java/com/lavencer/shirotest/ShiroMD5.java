package com.lavencer.shirotest;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

public class ShiroMD5 {
    public static void main(String[] args) {
//        密码明文
        String password = "z3";
//        使用md5加密
        Md5Hash md5Hash = new Md5Hash(password);
        System.out.println("md5加密            = " + md5Hash);

//        带盐的md5加密，盐就是在密码明文后拼接新字符串，然后再进行加密
        Md5Hash md5Hash1 = new Md5Hash(password, "salt");
        System.out.println("md5带盐加密        = " + md5Hash1);

//        避免被破解，可以多次迭代加密
        Md5Hash md5Hash2 = new Md5Hash(password, "salt", 3);
        System.out.println("md5带盐3次迭代加密  = " + md5Hash2);

//        使用父类进行加密
        SimpleHash simpleHash = new SimpleHash("MD5", password, "salt", 3);
        System.out.println("父类 md5带盐3次迭代加密  = " + simpleHash.toHex());
    }
}
