package com.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class user {
    private String username;
    private String password;
    private String email;
    private int status;
    private int role;
    private LocalDateTime createdAt;
}
