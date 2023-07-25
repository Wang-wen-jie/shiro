package com.lavender.shiro_springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lavender.shiro_springboot.entiy.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT NAME FROM role WHERE id IN (" +
            "SELECT rid FROM role_user WHERE uid = (" +
            "SELECT id FROM user WHERE NAME= #{principal})" +
            ") ")
    List<String> getUserRoleInfoMapper(@Param("principal") String principal);


    @Select("<script>" +
            "select info from permissions where id in" +
            "(select pid from role_ps where rid in (" +
            "select id from role where name in" +
            "<foreach collection='roles' item='name' open='(' separator=',' close=')'>" +
            "#{name}" +
            "</foreach>" +
            "))" +
            "</script>")
    List<String> getUserPermissionInfoMapper(@Param("roles") List<String> roles);

}
