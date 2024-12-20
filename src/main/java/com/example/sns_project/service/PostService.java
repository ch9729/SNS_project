package com.example.sns_project.service;

import com.example.sns_project.dto.CommentDTO;
import com.example.sns_project.dto.PostDTO;
import com.example.sns_project.entity.Post;
import com.example.sns_project.mapper.CommentMapper;
import com.example.sns_project.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper pMapper;
    private final CommentMapper cMapper;

    public void addPost(Post post, MultipartFile file) throws IOException {
        if(!file.isEmpty()) {
            String uploadDir = "C:/work/SNS_project/uploads/image/";
            File directory = new File(uploadDir);
            if(!directory.exists()) {
                directory.mkdirs();
            }

            String originalFilename = file.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String savedFilename = UUID.randomUUID().toString() + fileExtension;

            File saveFile = new File(uploadDir + savedFilename);
            file.transferTo(saveFile);

            post.setImage("/uploads/image/" + savedFilename);
        } else {
            post.setImage(null);
        }
        pMapper.insertPost(post);
    }

    public List<PostDTO> getAllPost() {
        return pMapper.allPost();
    }

    public void deletePost(Long id) {
        pMapper.deletePost(id);
    }

    public PostDTO getById(Long id) {
        PostDTO post =  pMapper.postById(id);
        List<CommentDTO> comments = cMapper.commentByPostId(id);
        post.setComments(comments);
        return post;
    }
}
