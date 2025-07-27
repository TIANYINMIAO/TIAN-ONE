package com.service;

import com.pojo.userDTO;
import com.result.Result;
import org.springframework.stereotype.Service;

public interface RegistrationService {
    /**
     * 发送验证码
     * @param email
     */
    Result sendVerificationCode(String email);

    /**
     * 注册验证
     * @param
     * @param
     * @return
     */
    Result register(userDTO userDTO);
}
