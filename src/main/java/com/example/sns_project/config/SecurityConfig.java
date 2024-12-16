package com.example.sns_project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers("/","/join", "/css/**", "/img/**", "/js/**").permitAll()
                        .requestMatchers("/myPage").authenticated() //인증된 사용자만 접근가능
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/") // 로그인 페이지 경러
                        .loginProcessingUrl("/login")   //로그인 처리 URL
                        .defaultSuccessUrl("/main",true)    // 로그인 성공 시 이동URL
                        .failureUrl("/?error=true") // 로그인 실패 시 이동할 URL
                        .usernameParameter("id")   // 시큐리티는 기본적으로 username을 받는데 해당 찾는것을 아아디로 받을시 입력이 필수*****
                        .passwordParameter("password")  // 패스워드도 같이 입력하는것이 좋다.
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout") // 로그아웃 처리 URL
                        .logoutSuccessUrl("/") // 로그아웃 성공 시 이동할 URL
                        .invalidateHttpSession(true) // 세션 무효화
                        .deleteCookies("JSESSIONID")) // 쿠키 삭제
        ;
        return http.build();
    }

    // 패스워드 암호화
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 패스워드랑 암호화 비교
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
