package com.example.sns_project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowDTO {

    private Long id;

    private Long followerId;

    private Long followingId;

    private String followerAlias;

    private String followingAlias;
}
