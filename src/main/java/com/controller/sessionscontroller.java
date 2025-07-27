package com.controller;

import com.pojo.messageDTO;
import com.pojo.sessions;

import com.result.Result;
import com.service.getsessionshistoryservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/huanghe")
public class sessionscontroller {

    @Autowired
    private getsessionshistoryservice getsessionshistory;
    @GetMapping("/sessionshistory")
    public Result<List<sessions>> getsessionshistory(){
        //依据用户名查询历史会话记录
        //获取用户名
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        log.info("用户名：{}",username);
        //查询历史会话记录
       // System.out.println(getsessionshistory.getsessionshistory(username));
        List<sessions> list = getsessionshistory.getsessionshistory(username);
        for (sessions s : list) {
            System.out.println("SessionId: " + s.getSessionId());
            System.out.println("CreatedAt: " + s.getCreatedAt());
            System.out.println("UpdatedAt: " + s.getUpdatedAt());
            System.out.println("Title: " + s.getTitle());
        }
        return Result.success(list);
    }

    @GetMapping("/sessions/{sessionId}/messages")
    Result<List<messageDTO>> getMessages(
            @PathVariable String sessionId
    )
    {
        //依据sessionId查询历史消息记录
        List<messageDTO> list = getsessionshistory.getMessages(sessionId).getData();
        return Result.success(list);

    }
}
