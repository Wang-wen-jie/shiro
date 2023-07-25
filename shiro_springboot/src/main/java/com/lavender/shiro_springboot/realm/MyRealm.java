package com.lavender.shiro_springboot.realm;

import com.lavender.shiro_springboot.entiy.User;
import com.lavender.shiro_springboot.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Component
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    // 自定义授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("自定义授权方法");
        // 1创建存储当前用户角色和权限的对象
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        // 2.1获取用户身份信息
        String principal = principals.getPrimaryPrincipal().toString();
        // 2.2调用业务层获取用户角色信息
        List<String> roles = userService.getUserRoleInfo(principal);
        System.out.println("当前用户角色信息="+roles);
        // 2.3创建对象，封装当前登录用户角色、权限信息
        authorizationInfo.addRoles(roles);

        // 3.1获取用户角色信息
        // 3.2调用业务层获取用户权限信息
        List<String> permission = userService.getUserPermissionInfo(roles);
        System.out.println("当前用户权限信息="+permission);
        // 3.3创建对象，封装当前登录用户角色、权限信息
        authorizationInfo.addStringPermissions(permission);


        return authorizationInfo;
    }

    // 自定义登录认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 1 获取用户身份信息
        String principal = token.getPrincipal().toString();
        // 2 调用业务层获取业务信息（数据库表中）
        User user = userService.getUserInfoByName(principal);
        // 3 非空判断，将数据完成封装返回
        if(user != null){
            return new SimpleAuthenticationInfo(
                    token.getPrincipal(),
                    user.getPwd(),
                    ByteSource.Util.bytes("salt"),
                    principal
            );
        }
        return null;
    }
}
