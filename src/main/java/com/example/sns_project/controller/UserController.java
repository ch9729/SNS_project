package com.example.sns_project.controller;

import com.example.sns_project.dto.PostDTO;
import com.example.sns_project.dto.UserDTO;
import com.example.sns_project.entity.User;
import com.example.sns_project.mapper.UserMapper;
import com.example.sns_project.service.PostService;
import com.example.sns_project.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Collections;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserMapper uMapper;
    private final UserService uService;
    private final PostService pService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "index";
    }

    //계정생성 확인 눌렀을시 페이지 이동
    @GetMapping("/join")
    public String join(Principal principal, Model model) {
        UserDTO userDTO = new UserDTO();
        if(principal != null) {
            String userId = principal.getName();
            User userById = uService.getUserById(userId);

        }
        model.addAttribute("userDTO", userDTO);
        model.addAttribute("isEdit", principal != null);
        return "join";
    }

    // 회원가입 (DTO로 입력을 받아 Entity로 변환)
    @PostMapping("/join")
    public String join(@ModelAttribute("userDTO")UserDTO userDTO, Principal principal , Model model) {
        boolean isEdit = principal != null;
        uService.createUser(userDTO, isEdit);
        return "redirect:/myPage";
    }

    @GetMapping("/main")
    public String main(Model model) {
        List<PostDTO> posts = pService.getAllPost();
        model.addAttribute("posts", posts);
        return "main";
    }

    @GetMapping("/myPage")
    public String myPage(Principal principal, Model model) {
        String username = principal.getName();

        Long userNum = uService.getUserNumByUsername(username);

        List<PostDTO> posts = pService.getPostsByUser(userNum);

        model.addAttribute("posts", posts);

        User user = uService.getUserByNum(userNum);

        model.addAttribute("user", user);

        return "myPage";
    }

    @GetMapping("/edit")
    public String edit(Principal principal, Model model) {
        String user = principal.getName();
        User users = uService.getUserById(user);

        model.addAttribute("userDTO", users);

        return "edit";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute("userDTO") UserDTO userDTO,
                       MultipartFile file,
                       BindingResult bindingResult,
                       Principal principal) {
        if(bindingResult.hasErrors()) {
            System.out.println("에러");
            return "edit";
        }

        userDTO.setId(principal.getName());

        uService.updateUser(userDTO, file);

        return "redirect:/myPage";
    }

    @PostMapping("/delete")
    public String delete(Principal principal) {
        String user = principal.getName();
        uService.deleteUser(user);
        return "redirect:/logout";
    }

    // 사용자 검색
    @GetMapping("/search")
    public String searchUsers(@RequestParam(value = "query", required = false) String query, Model model) {

        List<User> users;
        if(query == null || query.isEmpty()) {
            users = uService.getAllUsers();
        } else {
            users = uService.searchUser(query);
        }
        model.addAttribute("users", users);
        model.addAttribute("query", query);

        return "search";
    }

    @GetMapping("/user/{userNum}")
    public String getUserPage(@PathVariable("userNum") Long userNum, Model model) {
        User user = uService.getUserByNum(userNum);

        System.out.println("userNum = " + userNum);
        model.addAttribute("user", user);

        List<PostDTO> posts = pService.getPostsByUser(userNum);
        model.addAttribute("posts", posts);
        return "userPage";
    }

}
