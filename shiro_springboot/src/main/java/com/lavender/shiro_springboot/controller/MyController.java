package com.lavender.shiro_springboot.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("myController")
public class MyController {

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("userLogin")
    // @ResponseBody
    public String userLogin(String name, String pwd,
                            @RequestParam(defaultValue = "false") boolean rememberMe,
                            HttpSession session) {
        //     1 获取登录认证对象Subject
        Subject subject = SecurityUtils.getSubject();
        //     2 封装请求数据到token
        AuthenticationToken token = new UsernamePasswordToken(name, pwd, rememberMe);
        //     3 调用login方法 完成登录校验
        try {
            subject.login(token);
            // return "登录成功";
            session.setAttribute("user", token.getPrincipal().toString());
            return "main";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("登录失败");
            return "登录失败";
        }
    }

    // 登录认证验证rememberMe
    @GetMapping("userLoginRm")
    public String userlogin(HttpSession session) {
        session.setAttribute("user", "rememberMe");
        return "main";
    }

    //     登录认证验证角色
    @RequiresRoles("admin")
    @GetMapping("userLoginRoles")
    @ResponseBody
    public String userLoginRoles() {
        System.out.println("登录认证验证角色");
        return "角色验证成功";
    }

    //     登录认证验证权限
    @RequiresPermissions("user:delete")
    @GetMapping("userLoginPermission")
    @ResponseBody
    public String userLoginPermission() {
        System.out.println("登录认证验证删除权限");
        return "权限验证成功";
    }

}
