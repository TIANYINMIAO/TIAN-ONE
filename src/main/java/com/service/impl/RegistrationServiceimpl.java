package com.service.impl;

import com.mapper.RegistrationMapper;
import com.service.RegistrationService;
import com.pojo.user;
import com.pojo.userDTO;
import com.result.Result;
import com.util.EmailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.config.Codes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;
@Slf4j
@Service
public class RegistrationServiceimpl implements RegistrationService {
    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private RegistrationMapper registrationMapper;

    public Result sendVerificationCode(String email) {
        // 生成随机验证码
        String verificationCode = String.valueOf(100000 + new Random().nextInt(900000));

        System.out.println("验证码是："+verificationCode);

        // 将验证码存入 Redis，设置过期时间为分钟
        redisTemplate.opsForValue().set("verificationCode:" + email, verificationCode, 10, TimeUnit.MINUTES);//有bug
        String code = redisTemplate.opsForValue().get("verificationCode:" + email);
        //  System.out.println("从缓存中获取的验证码是："+code);
        // 发送验证码
        emailUtil.sendVerificationEmail(email, verificationCode);
        System.out.printf("发送成功！！");
        return Result.success(Codes.SUCCESS1);
    }
    public Result register(userDTO userDTO) {
        String verificationCode = redisTemplate.opsForValue().get("verificationCode:" + userDTO.getEmail());
        System.out.printf("开始验证！");
        System.out.printf("用户邮箱是："+userDTO.getEmail());
        System.out.printf("用户名是："+userDTO.getUsername());
        System.out.printf("从redis中获取验证码是："+verificationCode);
        System.out.printf("从前端获取验证码是："+userDTO.getVerificationcode());
        if (verificationCode.equals(userDTO.getVerificationcode())) {  // 比较验证码
            System.out.printf("验证成功！");
            // 查询数据库。看是否注册过（根据用户email）
            Integer result = registrationMapper.getRegister(userDTO.getEmail());  // 用实例来调用
            System.out.printf("111");
            if (result == null) {
                System.out.printf("222");
                //补全用户信息（创建时间）
                user user = new user();//创建用户对象
                user.setUsername(userDTO.getUsername());
                user.setEmail(userDTO.getEmail());
                user.setPassword(userDTO.getPassword());

                LocalDateTime currentDateTime = LocalDateTime.now();
                 // 定义时间格式为 "yyyy-MM-dd-HH-mm-ss"
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");

                 // 格式化当前时间
                String formattedDateTime = currentDateTime.format(formatter);

                // 输出格式化后的字符串
                System.out.println("Formatted DateTime: " + formattedDateTime);

                // 定义解析的格式（与上面的格式相同）
                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");

                // 将格式化后的字符串解析回LocalDateTime
                LocalDateTime localDateTime = LocalDateTime.parse(formattedDateTime, formatter1);
                user.setCreatedAt(localDateTime);
                //添加默认头像

                // 向数据库中添加用户信息
                int result1 = registrationMapper.insertRegister(user);
                if (result1 == 1) {
                    System.out.printf("333");
                    return Result.success(Codes.SUCCESS);
                } else {
                    System.out.printf("444");
                    return Result.error(Codes.ERROR_8);
                }
                // 处理注册逻辑
            }
            System.out.printf("555");
            return Result.error(Codes.ERROR_9);


        } else {
            System.out.printf("验证失败！");
            return Result.error(Codes.ERROR_10);
        }
    }
    }
