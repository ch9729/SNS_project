package com.example.sns_project.service;

import com.example.sns_project.entity.User;
import com.example.sns_project.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserMapper uMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = uMapper.findById(username);
        if (user == null) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getId())
                .password(user.getPassword())
                .roles("USER") // 권한 설정
                .build();

    }
}
