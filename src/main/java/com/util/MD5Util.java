package com.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Slf4j
public class MD5Util {
    /**
     * 对密码使用MD5进行加密
     * @param password
     * @return
     */
    public static String puzzle(String password) {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String md5 = DigestUtils.md5DigestAsHex((password+uuid).getBytes(StandardCharsets.UTF_8));
        return (md5 + uuid);
    }

    /**
     * 使用MD5校验密码和密文是否相同
     * @param password
     * @param puzzleMd5
     * @return
     */
    public static boolean verify(String password, String puzzleMd5) {
        if (puzzleMd5.length() != 64) {
            log.error("数据库密文存储有误：{}", puzzleMd5);
            return false;
        }
        String uuid = puzzleMd5.substring(32, 64);
        String md5 = puzzleMd5.substring(0, 32);
        String tmp = DigestUtils.md5DigestAsHex((password+uuid).getBytes(StandardCharsets.UTF_8));
        if (md5.equals(tmp)) {
            log.info("密码正确");
            return true;
        }
        else {
            log.info("密码错误");
            return false;
        }
    }
}
