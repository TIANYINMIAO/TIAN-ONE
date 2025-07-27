package com.controller;

import com.mapper.RegistrationMapper;
import com.config.LoginResponse;
import com.pojo.user;
import com.result.Result;
import com.util.BCryptUtil;
import com.util.JwtUtil;
import com.util.MD5Util;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.config.Codes;
import com.service.loginservice ;

import static com.config.Codes.SECRET_KEY;

@RestController
@Slf4j
@RequestMapping("/huanghe")
public class logincontroller {
    /**
     * 登录功能
     */
    @Autowired
    private loginservice loginservice;

    @PostMapping("/login")
    public Result login(@RequestBody user user) {
        System.out.println("用户" + user.getUsername()+"准备登录");
        // 查询数据库，验证身份,根据用户名
      //  !MD5Util.verify(user.getPassword(), user.getPassword());
        user user1 = loginservice.getUser(user.getUsername());
        System.out.println("user1"+user1);
       if(user1==null) {
           return Result.error(Codes.ERROR_2);//用户不存在
       }
       if(BCryptUtil.verify(user.getPassword(), user1.getPassword())){
            if(user1.getStatus()==0){
                System.out.println("===用户被禁用===");
                return Result.error(Codes.ERROR_3);//用户被禁用
            }

                String token = Jwts.builder()
                        .setSubject(user.getUsername()) // 设置用户名
                        .setIssuedAt(new Date()) // 签发时间
                        .setExpiration(new Date(System.currentTimeMillis() + 6000000)) // 过期时间 1min
                        .signWith(SignatureAlgorithm.HS512, SECRET_KEY) // 使用 HS512 签名算法和密钥
                        .compact();

            log.info("token是："+token);
                // 构造登录响应对象
                LoginResponse response = new LoginResponse(token, user.getUsername(), user.getEmail());
                System.out.println("登录成功");
                return Result.success(response);
            }
        return Result.error(Codes.ERROR_7);//密码错误

    }

}
