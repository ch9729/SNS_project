package com.example.sns_project.controller;

import com.example.sns_project.entity.Post;
import com.example.sns_project.entity.User;
import com.example.sns_project.service.PostService;
import com.example.sns_project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService pService;
    private final UserService uService;

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
    public void deletePost(@RequestParam Long id, Principal principal) {
        String userId = principal.getName();
        pService.postById(id);
        return "redirect:/myPage";
    }


}
