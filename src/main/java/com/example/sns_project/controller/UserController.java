package com.example.sns_project.controller;

import com.example.sns_project.entity.User;
import com.example.sns_project.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class UserController {

    private UserMapper uMapper;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/join")
    public String join(@Valid @ModelAttribute User user, BindingResult result) {
        if(result.hasErrors()) {
            return "join";
        }

    }
}
