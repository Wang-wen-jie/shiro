package com.lavencer.shirotest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

public class MyRealm extends AuthenticatingRealm {
    //    自定义登录认证方法，shiro的login方法底层会调用该类的方法进行认证
//    需要配置自定义的realm生效，在ini文件可以配置，或者在springboot中配置
//    该方法只是获取对比的信息，认证逻辑还是按照shiro底层认证逻辑完成
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 1获取身份信息  t
        String principal = token.getPrincipal().toString();
        // 2获取凭证信息
        String password = new String((char[]) token.getCredentials());

        System.out.println("认证用户信息：" + principal + "--" + password);
        // 3获取数据库中存储的用户信息
        if (principal.equals("zhangsan")) {
            //     3.1数据库中加盐迭代三次的加密密码
            String pwdInfo = "7174f64b13022acd3c56e2781e098a5f";
            // 4创建封装校验逻辑对象，封装数据返回
            AuthenticationInfo info = new SimpleAuthenticationInfo(
                    token.getPrincipal(),
                    pwdInfo,
                    ByteSource.Util.bytes("salt"),
                    principal);
            return info;
        }

        return null;
    }
}
