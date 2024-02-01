package com.example.reservation.jwt;

import com.example.reservation.dto.CustomUserDetails;
import com.example.reservation.entity.UserEntity;
import com.example.reservation.util.RedisUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    private final RedisUtil redisUtil;


    public JWTFilter(JWTUtil jwtUtil,RedisUtil redisUtil) {

        this.jwtUtil = jwtUtil;
        this.redisUtil = redisUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //request에서 Authoriztion 헤더를 찾음
        String authorization = request.getHeader("Authorization");

        //Authoriztion 헤더 검증
        if(authorization==null || !authorization.startsWith("Bearer ")){
            System.out.println("token null");
            filterChain.doFilter(request,response);

            //조건이 해당되면 메소드 종료
            return;
        }

        System.out.println("authorization now");
        //Bearer 부분 제거 후 순수 토큰만 획득
        String origin_token = authorization;
        String token = authorization.split(" ")[1];



        //토큰 소멸 시간 검증
        if(jwtUtil.isExpired(token)){
            System.out.println("token expired");
            filterChain.doFilter(request,response);

            //조건이 해당되면 메소드 종료
            return;
        }

        //토큰에서 username과 role 획득
        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        //Redis에서 username을 키로 가지고 있는지 확인
        if(!redisUtil.hasKey(username)){
            // Redis에 해당 username을 키로 하는 데이터가 존재하지 않음
            System.out.println("redis에서 username을 찾을 수 없음 " + username);
            ResponseEntity.status(HttpStatus.OK).body("로그아웃된 회원입니다.");
//            response.getWriter().write("로그아웃된 회원입니다."); // 메시지 작성하여 반환
            return;
        }
        String tokenFromRedis = redisUtil.getData(username);
        if (!origin_token.equals(tokenFromRedis)) {
            // Redis에 해당 username을 키로 하는 데이터와 토큰이 일치하지 않음
            System.out.println("username은 있지만 토큰이 없음: " + username);
            ResponseEntity.status(HttpStatus.OK).body("로그아웃된 회원입니다.");
//            response.getWriter().write("로그아웃된 회원입니다."); // 메시지 작성하여 반환
            return;
        }


        //userEntitiy를 생성하여 값 set
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword("heeekoug");
        userEntity.setRole(role);

        //UserDetails에 회원 정보 객체 담기
        CustomUserDetails customUserDetails = new CustomUserDetails(userEntity);

        //스프링 시큐리티 인증 토큰 생성
        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetails,null,customUserDetails.getAuthorities());

        //세션에 사용자 등록
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
