package com.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
public class BCryptUtil {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * 使用BCrypt对密码进行加密
     * @param password 明文密码
     * @return 加密后的密文
     */
    public static String puzzle(String password) {
        return encoder.encode(password);
    }

    /**
     * 使用BCrypt验证密码和密文是否匹配
     * @param password 明文密码
     * @param encodedPassword 加密后的密文
     * @return 是否匹配
     */
    public static boolean verify(String password, String encodedPassword) {
        boolean matches = encoder.matches(password, encodedPassword);
        if (matches) {
            log.info("密码正确");
        } else {
            log.info("密码错误");
        }
        return matches;
    }
}
