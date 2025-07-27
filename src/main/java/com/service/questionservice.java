package com.service;

import com.pojo.question;
import org.springframework.stereotype.Service;

import java.util.List;
public interface questionservice {
    /**
     * 根据题目id获取题目
     * @param questionIds
     */
    List<question> getanswer(List<Integer> questionIds);
}
