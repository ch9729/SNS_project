package com.example.sns_project.service;

import com.example.sns_project.dto.UserDTO;
import com.example.sns_project.entity.User;
import com.example.sns_project.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

        uMapper.insertUser(user.getId(),user.getPassword(),user.getName(),user.getAlias(), user.getProfile());
    }

    // 로그인
    public boolean loginUser(UserDTO userDTO) {
        User user = uMapper.findById(userDTO.getId());
        System.out.println("user = " + user);
        if(user == null) {
            throw new RuntimeException("사용자를 찾을수 없습니다.");

        }

        return passwordEncoder.matches(userDTO.getPassword(), user.getPassword());

    }

    //회원 정보 가져오기
    public User getUserById(String id) {
        return uMapper.findById(id);
    }

    public void updateUser(UserDTO userDTO, MultipartFile file) {
        User existingUser = uMapper.findById(userDTO.getId());

        // 비밀번호 처리
        String updatedPassword = userDTO.getPassword();
        if (updatedPassword != null && !updatedPassword.isEmpty()) {
            updatedPassword = passwordEncoder.encode(updatedPassword);
        } else {
            updatedPassword = existingUser.getPassword();
        }

        // 이미지 처리
        String profilePath = existingUser.getProfile();
        String projectDir = System.getProperty("user.dir");
        String uploadDir = projectDir + "/uploads/profile/";

        if (file != null && !file.isEmpty()) {
            String fileName = "profile_" + userDTO.getId() + "_" + System.currentTimeMillis() + ".png";
            File saveDir = new File(uploadDir);

            try {
                // 폴더가 없으면 생성
                if (!saveDir.exists()) {
                    if (!saveDir.mkdirs()) {
                        throw new RuntimeException("폴더 생성 실패: " + uploadDir);
                    }
                }

                // 파일 저장
                File saveFile = new File(saveDir, fileName);
                file.transferTo(saveFile);

                // 로그 출력
                System.out.println("파일 저장 경로: " + saveFile.getAbsolutePath());

                profilePath = "uploads/profile/" + fileName; // DB에 저장될 경로
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("파일 저장 실패: " + e.getMessage(), e);
            }
        }

        // 사용자 정보 업데이트
        uMapper.updateUser(
                userDTO.getId(),
                updatedPassword,
                userDTO.getName(),
                userDTO.getAlias(),
                profilePath
        );
    }

    // 회원 탈퇴
    public void deleteUser(String id) {
        uMapper.deleteUser(id);
    }

    // 회원 검색
    public List<User> searchUser(String query) {
        return uMapper.selectUserLike(query);
    }

    public List<User> getAllUsers() {
        return uMapper.selectUsers();
    }

    public User getUserByNum(Long userNum) {
        return uMapper.getUserByNum(userNum);
    }

    public Long getUserNumByUsername(String username) {
        return uMapper.getUserNumByUsername(username);
    }

}
