package com.example.sns_project.mapper;

import com.example.sns_project.dto.PostDTO;
import com.example.sns_project.entity.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {

    void insertPost(Post post);

    List<PostDTO> allPost();

    void deletePost(Long id);

    PostDTO postById(Long id);

    List<PostDTO> postsByUserNum(Long userNum);
}
