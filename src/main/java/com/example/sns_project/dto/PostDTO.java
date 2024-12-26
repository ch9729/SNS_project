package com.example.sns_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Long id;

    private String title;

    private String image;

    private Long userNum;

    private String userAlias;

    private String userProfile;

    private List<CommentDTO> comments;

    private int likes;

    private boolean isLiked;
}
