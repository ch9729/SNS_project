package com.example.sns_project.service;

import com.example.sns_project.mapper.LikesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesService {

    private final LikesMapper lMapper;

    public boolean checkLike(Long userId, Long postId) {
        return lMapper.checkLike(userId, postId);
    }

    public void insertLike(Long userId, Long postId) {
        lMapper.insertLike(userId, postId);
    }

    public void deleteLike(Long userId, Long postId) {
        lMapper.deleteLike(userId, postId);
    }
}
