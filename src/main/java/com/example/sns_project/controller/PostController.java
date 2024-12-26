package com.example.sns_project.controller;

import com.example.sns_project.dto.CommentDTO;
import com.example.sns_project.dto.PostDTO;
import com.example.sns_project.entity.Post;
import com.example.sns_project.entity.User;
import com.example.sns_project.service.CommentService;
import com.example.sns_project.service.LikesService;
import com.example.sns_project.service.PostService;
import com.example.sns_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService pService;
    private final UserService uService;
    private final CommentService cService;
    private final LikesService lService;

    @GetMapping("/add")
    public String addPost(Model model) {
        return  "addPost";
    }

    @PostMapping("/add")
    public String addPost(@ModelAttribute Post post, MultipartFile file, Principal principal) throws IOException {
        String userId = principal.getName();
        User userById = uService.getUserById(userId);
        post.setUserNum(userById.getUserNum());

        pService.addPost(post,file);
        return "redirect:/myPage";
    }

    @PostMapping("/delete")
    public String deletePost(@RequestParam("id") Long id) {
        pService.deletePost(id);
        return "redirect:/myPage";
    }

    @GetMapping("/{id}")
    public String getPostDetail(@PathVariable Long id, Model model) {
        PostDTO post = pService.getById(id);
        for (CommentDTO comment : post.getComments()) {
            String alias = cService.getAliasByComment(comment.getId());
            comment.setAuthor(alias);
        }
        model.addAttribute("post", post);
        return "postDetail";
    }

}
