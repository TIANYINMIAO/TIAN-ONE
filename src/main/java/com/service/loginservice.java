package com.service;

import com.pojo.user;
public interface loginservice {
    /**
     * 用户登录
     */
    user getUser(String username);
}
