package com.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 构造登录响应对象
 */
public class LoginResponse {
    private String token;
    private String userName;
    private String email;
}
