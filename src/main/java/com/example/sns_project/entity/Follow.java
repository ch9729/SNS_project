package com.example.sns_project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Follow {

    private Long id;

    private Long followerId;

    private Long followingId;

    private User follower;

    private User following;
}
