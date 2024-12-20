package com.example.sns_project.controller;

import com.example.sns_project.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class LikeController {

    private final LikeService lService;
}
