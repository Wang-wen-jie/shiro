package com.lavender.shiro_springboot.service;

import com.lavender.shiro_springboot.entiy.User;

import java.util.List;
import java.util.Set;

public interface UserService {
//     用户登录
    User getUserInfoByName(String name);

// 根据用户查询角色信息
List<String> getUserRoleInfo(String principal);

    // 获取用户角色的权限信息
    List<String> getUserPermissionInfo(List<String> roles);

}
