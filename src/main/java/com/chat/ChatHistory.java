package com.chat;

import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletRequest;

public class ChatHistory {
    public static void AddChatHistory(String content, String modelResponse) {
    }

    // 在Controller中获取用户名
    public String getUserName(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("claims");
        if (claims != null) {
            return claims.getSubject(); // 获取用户名
        } else {
            return "未授权";
        }
    }
}
