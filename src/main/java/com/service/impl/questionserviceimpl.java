package com.service.impl;

import com.pojo.question;
import com.service.questionservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mapper.questionmapper ;
import java.util.List;

@Service
public class questionserviceimpl implements questionservice {

    @Autowired
    private questionmapper questionmapper;
    @Override
    public List<question> getanswer(List<Integer> questionIds) {

        return questionmapper.getanswer(questionIds);
    }
}
