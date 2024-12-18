package com.example.sns_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardDTO {

    private Long id;
    
    private String title;

    private String content;
    
    private Long userNum;
    
    private String authorName;  // 유저 이름
    
    private String authorAlias; //유저 닉네임
    
    private String authorProfile;   //유저 이미지
}
