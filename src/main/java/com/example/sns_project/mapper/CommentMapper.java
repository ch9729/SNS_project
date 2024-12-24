package com.example.sns_project.mapper;

import com.example.sns_project.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    void insertComment(CommentDTO commentDTO);

    List<CommentDTO> commentByPostId(Long postId);

    void deleteComment(Long id);

    String getAliasByComment(Long id);
}
