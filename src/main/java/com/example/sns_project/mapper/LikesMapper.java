package com.example.sns_project.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LikesMapper {

    boolean checkLike(Long userId, Long postId);

    void insertLike(Long userId, Long postId);

    void deleteLike(Long userId, Long postId);
}
