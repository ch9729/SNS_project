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

import java.security.Principal;

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
    public String main() {
        return "main";
    }

    @GetMapping("/myPage")
    public String myPage(Principal principal, Model model) {
        String user = principal.getName();
        User users = uService.getUserById(user);

        model.addAttribute("name", users.getName());
        model.addAttribute("alias", users.getAlias());
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
                       BindingResult bindingResult,
                       Principal principal) {
        if(bindingResult.hasErrors()) {
            System.out.println("에러");
            return "edit";
        }
        userDTO.setId(principal.getName());
        uService.updateUser(userDTO);

        return "redirect:/myPage";
    }

    @PostMapping("/delete")
    public String delete(Principal principal) {
        String user = principal.getName();
        uService.deleteUser(user);
        return "redirect:/logout";
    }
}
