package com.pojo;

import com.enu.ContentType;
import com.enu.SenderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class chat {
    private SenderType senderType;
    private ContentType contentType;
    private String content;
    private LocalDateTime createdAt;
    private String sessionId;

}
