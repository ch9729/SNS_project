package com.example.sns_project.controller;

import com.example.sns_project.dto.CommentDTO;
import com.example.sns_project.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService cService;

    @PostMapping("/comment/add")
    public String addComment(CommentDTO commentDTO,Principal principal) {
        commentDTO.setAuthor(principal.getName());

        System.out.println("Content: " + commentDTO.getContent());
        System.out.println("Post ID: " + commentDTO.getPostId());
        System.out.println("Author: " + commentDTO.getAuthor());

        cService.insertComment(commentDTO);
        return "redirect:/post/" + commentDTO.getPostId();
    }

    @GetMapping("/comment/post/{postId}")
    public String getCommentsByPostId(@PathVariable Long postId, Model model) {
        List<CommentDTO> comments = cService.getAllComment(postId);
        model.addAttribute("comments", comments);
        return "postDetail";
    }
}
