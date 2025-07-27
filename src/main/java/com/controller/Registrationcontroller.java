package com.controller;

import com.config.Codes;
import com.service.RegistrationService;

import com.config.LoginResponse;
import com.pojo.user ;
import com.pojo.userDTO;
import com.result.Result;
import com.util.BCryptUtil;
import com.util.JwtUtil;
import com.util.MD5Util;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.config.Codes.SECRET_KEY;

@RestController
@Slf4j
@RequestMapping("/huanghe")
public class Registrationcontroller {
    /**
     * 注册功能
     *
     */

    @Autowired
    private RegistrationService registrationService;

    /**
     * 发送验证码
     * @param user
     */
    @PostMapping("/sendVerificationCode")
    public Result sendVerificationCode(@RequestBody user user){
        System.out.println("用户名是"+user.getUsername());
       // registrationService.sendVerificationCode(user.getEmail());
        if(registrationService.sendVerificationCode(user.getEmail()).getCode()==1){
            return Result.success(Codes.SUCCESS1);
        }else {
            return Result.error(Codes.ERROR_11);
        }
    }
    /**
     * 注册
     * @param
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody userDTO userDTO){
    System.out.println("收到请求用户"+userDTO.getUsername()+"注册");
    System.out.println("用户验证码"+userDTO.getVerificationcode());
    userDTO.setPassword(BCryptUtil.puzzle(userDTO.getPassword()));
       if (registrationService.register(userDTO).getCode()==1){
           log.info("验证码正确");
           String token = Jwts.builder()
                   .setSubject(userDTO.getUsername()) // 设置用户名
                   .setIssuedAt(new Date()) // 签发时间
                   .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 过期时间 1小时
                   .signWith(SignatureAlgorithm.HS512, SECRET_KEY) // 使用 HS512 签名算法和密钥
                   .compact();
           // 构造登录响应对象
           LoginResponse response = new LoginResponse(token, userDTO.getUsername(), userDTO.getEmail());
           log.info(response.toString());
           return Result.success(response);
       }else {
            return registrationService.register(userDTO);
       }



       // return Result.success();
    }

}
