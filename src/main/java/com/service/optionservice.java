package com.service;

import com.pojo.option;

public interface optionservice {
    /**
     * 根据题目id获取答案，成功则返回行数，失败返回0
     *
     * @param option
     */
    int getanswer(option option);

}
