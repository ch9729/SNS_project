package com.example.sns_project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String id;

    @NotBlank(message = "패스워드를 입력해주세요.")
    private String password;

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "영어및 숫자만 입력 가능합니다.")
    @NotBlank(message = "사용자명을 입력해주세요.")
    private String alias;   //사용자명
}