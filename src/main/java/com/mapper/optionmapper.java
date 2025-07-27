package com.mapper;

import com.pojo.option;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface optionmapper {
    /**
     * 根据题目id获取答案，成功则返回行数，失败返回0
     * @param option
     */
    @Select("select * from question where id=#{questionId} and correct_answer=#{optionId}")
    int getanswer(option option);
}
