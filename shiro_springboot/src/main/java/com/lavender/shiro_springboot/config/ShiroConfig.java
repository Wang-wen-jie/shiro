package com.lavender.shiro_springboot.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.lavender.shiro_springboot.realm.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {
    @Autowired
    private MyRealm myRealm;

    // 配置securitymanager
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager() {
        // 1 创建defaultWebSecurityManager 对象
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        // 2 创建加密对象，设置相关属性
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //     2.1采用md5加密
        matcher.setHashAlgorithmName("md5");
        //     2.2采用3次迭代加密
        matcher.setHashIterations(3);
        // 3 将加密对象存储到myRealm中
        myRealm.setCredentialsMatcher(matcher);
        // 4 将myRealm存储到defaultWebSecurityManager 对象
        defaultWebSecurityManager.setRealm(myRealm);

        // 4.5 设置remember me
        defaultWebSecurityManager.setRememberMeManager(rememberMeManger());

        // 5 返回
        return defaultWebSecurityManager;
    }

    // cookie属性设置
    public SimpleCookie rememberCookie() {
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        //     设置跨域
        //     cookie.setDomain(domain)
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(30 * 24 * 60 * 60);
        return cookie;
    }

    // 创建shiro的cookie管理对象
    public CookieRememberMeManager rememberMeManger() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberCookie());
        cookieRememberMeManager.setCipherKey("1234567890987654".getBytes());
        return cookieRememberMeManager;
    }

    // 配置shiro内置过滤器拦截范围
    @Bean
    public DefaultShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        // 设置不认证可访问资源
        definition.addPathDefinition("/myController/userLogin", "anon");
        definition.addPathDefinition("/myController/login", "anon");
        //  设置登出过滤器
        definition.addPathDefinition("/logout", "logout");

        //  设置需要进行登录认证的拦截范围
        definition.addPathDefinition("/**", "authc");

        // 添加存在用户的过滤器（rememberMe）
        definition.addPathDefinition("/**", "user");
        return definition;
    }

    @Bean
    public ShiroDialect shiroDialect(){
        return new ShiroDialect();
    }
}
