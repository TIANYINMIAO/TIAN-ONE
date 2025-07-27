package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class sessions {
    private String sessionId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String title;
    private String userName;

}
