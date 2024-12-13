package com.example.sns_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    
    private int userNum;
    
    private String id;
    
    private String password;
    
    private String name;
    
    // 사용자명
    private String alias;
}
