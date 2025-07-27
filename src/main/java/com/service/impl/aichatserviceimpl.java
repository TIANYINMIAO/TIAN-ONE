package com.service.impl;
import com.enu.ContentType;
import com.enu.SenderType;
import com.pojo.sessions;
import com.service.aichatservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pojo.chat;
import com.mapper.aichatMapper;

import javax.mail.Session;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class aichatserviceimpl implements aichatservice {
    /**
     * 添加聊天记录
     * @param name
     * @param ai
     * @param user
     * @return
     */
    @Autowired
    private aichatMapper aichatMapper;

    @Override
    public int AddChatHistory(String name, String ai,String user,String sessionId) {


        //构造ai对话对象
        chat aichat = new chat();
        aichat.setContent(ai);
        aichat.setSenderType(SenderType.ai);
        aichat.setContentType(ContentType.text);
        aichat.setCreatedAt(LocalDateTime.now());
        aichat.setSessionId(sessionId);
        //构造用户对话对象
        chat userchat = new chat();
        userchat.setContent(user);
        userchat.setSenderType(SenderType.user);
        userchat.setContentType(ContentType.text);
        userchat.setCreatedAt(LocalDateTime.now());
        userchat.setContent(user);
        userchat.setSessionId(sessionId);
        //调用mapper实现插入成功返回2
        return aichatMapper.AddChatHistory(userchat)+aichatMapper.AddChatHistory(aichat);
    }

    @Override
    public String createNewSession(String username, String firstMessage) {
        sessions session = new sessions();
        String sessionId = UUID.randomUUID().toString();

        session.setSessionId(sessionId);
        session.setUserName(username);
        session.setTitle(firstMessage.length() > 50 ? firstMessage.substring(0,50) : firstMessage);
        session.setCreatedAt(LocalDateTime.now());
        session.setUpdatedAt(LocalDateTime.now());

        aichatMapper.insertSession(session);
        return sessionId;
    }

    @Override
    public boolean validateSession(String sessionId, String username) {
        sessions session = aichatMapper.getSession(sessionId, username);
        return session != null;
    }
}
