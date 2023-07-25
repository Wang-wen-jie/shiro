package com.lavencer.shirotest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

public class ShiroRun {
    public static void main(String[] args) {
//        1 初始化获取SecurityManager
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
//        2 获取Subject对象
        Subject subject = SecurityUtils.getSubject();
//        3 创建token对象，web应用用户名密码从页面传递
        AuthenticationToken token = new UsernamePasswordToken("zhangsan", "z3");
//        4 完成登录
        try {
            subject.login(token);
            System.out.println("登录成功");
//            5 判断角色
            boolean  hasRole = subject.hasRole("role1");
            System.out.println("是否拥有此角色 = " + hasRole);
//            6 判断权限
            boolean permitted  = subject.isPermitted("user:insert");
            System.out.println("是否拥有此权限 = " + permitted);
//            权限判断 checkPermission没有返回值，没有权限直接抛出异常
//            subject.checkPermission("user:update");
        } catch (UnknownAccountException e) {
            System.out.println("用户名错误");
            throw new RuntimeException(e);
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误");
            throw new RuntimeException(e);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
    }
}
