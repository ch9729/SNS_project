package com.example.sns_project.controller;

import com.example.sns_project.dto.UserDTO;
import com.example.sns_project.entity.User;
import com.example.sns_project.mapper.UserMapper;
import com.example.sns_project.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserMapper uMapper;
    private final UserService uService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "index";
    }
    
    //계정생성 확인 눌렀을시 페이지 이동
    @GetMapping("/join")
    public String join(UserDTO userDTO) {
        return "join";
    }

    // 회원가입 (DTO로 입력을 받아 Entity로 변환)
    @PostMapping("/join")
    public String join(@Valid @ModelAttribute UserDTO userDTO,
                       BindingResult result,
                       Model model) {
        if (result.hasErrors()) {
            return "join";
        }
        try {
            uService.createUser(userDTO);
            return "index";
        } catch ( Exception e ) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage());
            return "join";
        }

    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }

//    @PostMapping("/main")
//    public String main(UserDTO userDTO,
//                       Model model) {
//        boolean loginUser = uService.loginUser(userDTO);
//
//        if(loginUser) {
//            return "redirect:/main";  //로그인 성공시
//        } else {
//            model.addAttribute("error","아이디 혹은 비밀번호가 일치하지 않습니다.");
//            return "index";
//        }
//    }
}
