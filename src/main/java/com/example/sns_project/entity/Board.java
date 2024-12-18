package com.example.sns_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    private Long id;

    private String title;

    private String content;

    private Long userNum;   //작성자 번호

    private User user;      //조인
    
}
