package com.test1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class sessions {
    private String sessionsId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String title;


}
