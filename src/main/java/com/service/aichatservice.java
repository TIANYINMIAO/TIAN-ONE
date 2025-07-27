package com.service;

public interface aichatservice {

    int AddChatHistory(String name ,String content,String user,String sessionId);
    String createNewSession(String username, String firstMessage);
    boolean validateSession(String sessionId, String username);
}
