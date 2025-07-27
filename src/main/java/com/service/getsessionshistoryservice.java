package com.service;

import com.pojo.messageDTO;
import com.pojo.sessions;
import com.result.Result;
import org.springframework.http.ResponseEntity;

import java.util.List;


public interface getsessionshistoryservice {
    /**
     * 查询历史会话记录
     * @param username
     * @return
     */
    List<sessions> getsessionshistory(String username);

    /**  
     * 根据sessionId查询历史消息记录
     * @param sessionId
     * @return
     */
    Result<List<messageDTO>> getMessages(String sessionId);
}
