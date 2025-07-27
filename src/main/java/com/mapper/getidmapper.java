package com.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;
@Mapper
public interface getidmapper {
    /**
     * 获取主键id
     */
    @Select("SELECT id FROM photos")
    List<Long> findAllphotosIds();

    @Select("SELECT id FROM question")
    List<Long> findAllquestionIds();

}
