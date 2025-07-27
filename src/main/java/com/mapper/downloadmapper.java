package com.mapper;

import com.pojo.question;
import org.apache.ibatis.annotations.Mapper;
import com.pojo.photo;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface downloadmapper {
    /**
     * 自动下载照片
     * @return
     */
    @Select("select * from photos where id = #{randomNumber}")
    photo download(int randomNumber);

    question getQuestion(int randomNumber);
}
