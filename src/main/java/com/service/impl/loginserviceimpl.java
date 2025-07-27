package com.service.impl;

import com.mapper.RegistrationMapper;
import com.pojo.user;
import com.service.loginservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class loginserviceimpl implements loginservice {
    /**
     * 用户登录
     */

    @Autowired
    private RegistrationMapper registrationMapper;
    @Override
    public user getUser(String username) {

        return registrationMapper.getUser(username);
    }
}
