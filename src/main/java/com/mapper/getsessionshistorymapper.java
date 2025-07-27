package com.mapper;

import com.pojo.messageDTO;
import com.pojo.sessions;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface getsessionshistorymapper {
    /*
     * 查询历史会话记录
     */
    @Select("select * from sessions where username=#{username}")
    List<sessions> getsessionshistory(String username);

    /*
     * 查询历史会话记录
     */
    @Select("SELECT * FROM messages WHERE session_id = #{sessionid} ORDER BY created_at ASC")
    List<messageDTO> getsessionshistorybyid(String sessionid);
}
