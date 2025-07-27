package com.mapper;

import com.pojo.chat;
import com.pojo.sessions;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import javax.mail.Session;

@Mapper
public interface aichatMapper {
    /**
     * 添加聊天记录
     * @param
     * @return
     */
    @Insert("INSERT INTO messages (content, sender_type, content_type, created_at, session_id) "
            + "VALUES (#{content}, #{senderType}, #{contentType}, #{createdAt}, #{sessionId})")
    int AddChatHistory(chat message);

    /**
     * 获取会话
     * @param sessionId
     * @param
     * @return
     */
    @Select("SELECT * FROM sessions WHERE session_id = #{sessionId} AND username = #{username}")
    sessions getSession(@Param("sessionId") String sessionId, @Param("username") String useranme);
    /**
     * 添加会话
     * @param
     * @return
     */
    @Insert("INSERT INTO sessions (session_id, username, title, created_at, updated_at) " +
            "VALUES (#{sessionId}, #{userName}, #{title}, #{createdAt}, #{updatedAt})")
    int insertSession(sessions session);
}
