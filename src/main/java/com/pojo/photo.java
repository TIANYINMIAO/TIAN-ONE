package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class photo {
    private Long id;
    private String filename;
    private String ossUrl;
    private LocalDateTime createdAt;

}
