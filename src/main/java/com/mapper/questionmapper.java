package com.mapper;

import com.pojo.question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface questionmapper {
    /**
     * 根据题目id获取题目
     * @param questionIds
     */

    List<question> getanswer(@Param("questionIds")List<Integer> questionIds) ;
        // TODO

}
