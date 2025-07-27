package com.mapper;

import com.pojo.user;
import org.apache.ibatis.annotations.*;

@Mapper
public interface RegistrationMapper {
    /**
     * 查询邮箱是否注册
     * @param email
     * @return
     */
    @Select("select * from users where email=#{email}")
    Integer getRegister(String email) ;
    /**
     * 插入注册信息
     * @param user
     */
    @Insert("INSERT INTO users (username, password, email, created_at) " +
            "VALUES (#{username}, #{password}, #{email}, #{createdAt})")
    int insertRegister(user user);
    /**
     * 验证用户信息
     * @param username
     * @return
     */
    @Select("select * from users where username=#{username}")
    user getUser(@Param("username") String username);

}
