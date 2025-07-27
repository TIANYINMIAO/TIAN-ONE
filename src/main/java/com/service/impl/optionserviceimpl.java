package com.service.impl;

import com.pojo.option;
import com.service.optionservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mapper.optionmapper;
@Service
public class optionserviceimpl implements optionservice {
    /**
     * 根据题目id获取答案，成功则返回行数，失败返回0
     * @param option
     */
    @Autowired
    private optionmapper optionmapper;
    @Override
    public int getanswer(option option) {

        return optionmapper.getanswer(option);
    }
}
