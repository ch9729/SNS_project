package com.example.sns_project.service;

import com.example.sns_project.dto.UserDTO;
import com.example.sns_project.entity.User;
import com.example.sns_project.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper uMapper;
    private final PasswordEncoder passwordEncoder;

    // Entity -> DTO로 변환 (현재 mapper에서만 사용할 것이라 private설정)
    private UserDTO toDTO(User user) {

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setPassword(user.getPassword());
        userDTO.setName(user.getName());
        userDTO.setAlias(user.getAlias());

        return userDTO;
    }

    // DTO -> Entity 변환
    private User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setName(userDTO.getName());
        user.setAlias(userDTO.getAlias());
        return user;
    }

    //회원가입
    public void createUser(UserDTO userDTO, boolean isEdit) {
        User user = toEntity(userDTO);
        if(isEdit) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            uMapper.updateUser(user.getPassword(),user.getName(),user.getAlias());
        }
        uMapper.insertUser(user.getId(),user.getPassword(),user.getName(),user.getAlias());
    }

    // 로그인
    public boolean loginUser(UserDTO userDTO) {
        User user = uMapper.findById(userDTO.getId());
        if(user == null) {
            throw new RuntimeException("사용자를 찾을수 없습니다.");
        }
        return passwordEncoder.matches(userDTO.getPassword(), user.getPassword());
    }

    //회원 정보 가져오기
    public User getUserById(String id) {
        return uMapper.findById(id);
    }
}
