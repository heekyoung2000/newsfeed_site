package com.example.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //AuthenticationManager가 인자로 받을 AuthenticationConfiguraion 객체 생성자 주입
    private final AuthenticationConfiguration authenticationConfiguration;
    //JWTUtil 주입

    public SecurityConfig(AuthenticationConfiguration authenticationConfiguration) {
        this.authenticationConfiguration = authenticationConfiguration;

    }


    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // csrf 보안 사용 X
                .formLogin(AbstractHttpConfigurer::disable) // formLogin 사용 X
                .sessionManagement(AbstractHttpConfigurer::disable) // session 사용 X
                .headers(h -> h
                        .frameOptions(FrameOptionsConfig::disable)
                )
                .httpBasic(AbstractHttpConfigurer::disable);

        return http.build();
    }


//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//
//        //csrf disable
//        http
//                .csrf((auth) -> auth.disable());
//
//        //From 로그인 방식 disable
//        http
//                .formLogin((auth) -> auth.disable());
//
//        //From 로그인 방식 disable
//        http
//                .httpBasic((auth)->auth.disable());
//
//
//        //경로별 인가 작업
//        http
//                .authorizeHttpRequests((auth)->auth
//                        .requestMatchers("/login","/","/join","/mailSend","/authCheck","/success","/UserService/join","/UserService/login").permitAll()
////                        //이 경로로 접근하는 사람 모두 허용
//                        .requestMatchers("/admin","/board/{boardId}/comments","/board/new","NewsfeedService/board/post","/board/post/{id}","/logout","/board/like",
//                                "/UserService/admin","/UserService/user").permitAll()
//                        //admin 권한을 가진 사용자만 접근 허용
//                        .anyRequest().authenticated());
//
//
//        //JWTFilter 등록
////        http
////                .addFilterBefore(new JWTFilter(jwtUtil,redisUtil), LoginFilter.class);
//
////        http
////                .addFilterBefore(new LogoutFilter((LogoutSuccessHandler) jwtUtil), JWTFilter.class);
//
//        //필터 추가 LoginFilter()는 인자를 받음 (AuthenticationManager() 메소드에 authenticationConfiguration 객체를 넣어야 함) 따라서 등록 필요
//        http
//                .addFilterAt(new LoginFilter(authenticationManager(authenticationConfiguration),jwtUtil,redisUtil), UsernamePasswordAuthenticationFilter.class);
//        //세션 설정
//        http
//                .sessionManagement((session)-> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        //무조건 stateless 상태로 만들어 줘야함
//
//        return http.build();
//    }
}
