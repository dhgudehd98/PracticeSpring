package com.sh.loginbyrestcontroller.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class Member {
    private int id ;
    private String email;
    private String password;
    private String name;
    private LocalDateTime createdAt;
}