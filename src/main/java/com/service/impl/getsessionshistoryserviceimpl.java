package com.service.impl;

import com.pojo.messageDTO;
import com.pojo.sessions;
import com.result.Result;
import com.service.getsessionshistoryservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.mapper.getsessionshistorymapper;
import java.util.List;
@Service
public class getsessionshistoryserviceimpl implements getsessionshistoryservice {
    @Autowired
    private getsessionshistorymapper getsessionshistorymapper;
    @Override
    public List<sessions> getsessionshistory(String username) {
        //查询历史会话记录

        return getsessionshistorymapper.getsessionshistory(username);
    }

    @Override
    public Result<List<messageDTO>> getMessages(String sessionId) {
        //根据用户sessionId查询历史消息记录
        return Result.success(getsessionshistorymapper.getsessionshistorybyid(sessionId));

    }
}