package com.example.sns_project.service;

import com.example.sns_project.dto.CommentDTO;
import com.example.sns_project.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentMapper cMapper;

    public void insertComment(CommentDTO commentDTO) {
        cMapper.insertComment(commentDTO);
    }

    public List<CommentDTO> getAllComment(Long postId) {
        return cMapper.commentByPostId(postId);
    }

    public void deleteComment(Long id) {
        cMapper.deleteComment(id);
    }
}
