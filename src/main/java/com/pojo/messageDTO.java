package com.pojo;

import com.enu.ContentType;
import com.enu.SenderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class messageDTO {
    private Long messageId;
    private String sessionId;
    private SenderType senderType;
    private ContentType contentType;
    private String content;
    private LocalDateTime createdAt;
    private Map<String, Object> metadata;
}
