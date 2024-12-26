package com.example.sns_project.controller;

import com.example.sns_project.service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/likes")
public class LikesController {

    private final LikesService lService;

    @GetMapping("/check")
    public void insertLike(Long userId, Long postId) {
        if (!lService.checkLike(userId, postId)) {
            lService.insertLike(userId, postId);
        } else {
            throw new RuntimeException("Like already exists for userId: " + userId + " and postId: " + postId);
        }
    }

    // 좋아요 추가
//    @PostMapping("/add")
//    public void addLike(@RequestParam Long userId, @RequestParam Long postId) {
//        System.out.println("Add Like - userId: " + userId + ", postId: " + postId);
//        lService.insertLike(userId, postId);
//    }
//
//    // 좋아요 삭제
//    @DeleteMapping("/remove")
//    public void removeLike(@RequestParam Long userId, @RequestParam Long postId) {
//        lService.deleteLike(userId, postId);
//    }

    @PostMapping("/add")
    public ResponseEntity<String> addLike(@RequestParam Long userId, @RequestParam Long postId) {
        lService.insertLike(userId, postId);
        return ResponseEntity.ok("Like added successfully");
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> removeLike(@RequestParam Long userId, @RequestParam Long postId) {
        lService.deleteLike(userId, postId);
        return ResponseEntity.ok("Like removed successfully");
    }
}
