package com.example.reservation.member.controller;


import com.example.reservation.member.Service.JoinService;
import com.example.reservation.member.dto.EmailRequestDTO;
import com.example.reservation.member.dto.JoinDTO;
import com.example.reservation.member.entity.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@ResponseBody
@RequestMapping("/UserService")
public class MemberController {

    private final JoinService joinService;

    public MemberController(JoinService joinService){
        this.joinService = joinService;
    }
    //회원가입
    @PostMapping("/join")
    public String JoinProcess(JoinDTO joinDTO, EmailRequestDTO emailRequestDTO,@RequestParam("profileImage") MultipartFile profileImage){
        System.out.println(joinDTO.getUsername());
        System.out.println(joinDTO.getIntroduce());
        System.out.println(emailRequestDTO.getEmail());
        try {//joinDTO와 emailRequestDTO, profileImage를 통해 가입
            joinService.joinProcess(joinDTO,emailRequestDTO,profileImage);
            return "회원가입이 완료되었습니다.";
        } catch(Exception e){
            return "회원가입 중 오류가 발생했습니다.";
        }


    }

    //로그아웃
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest servletRequest){
        joinService.logout();
        return ResponseEntity.status(HttpStatus.OK).body("로그아웃된 회원입니다.");

    }

    //이름, 인사말 업데이트
    @PatchMapping("/{id}")
    public ResponseEntity<UserEntity> updateMember(@PathVariable Long id,@RequestBody JoinDTO joinDTO){
        UserEntity updateUser= joinService.updateMember(id,joinDTO.getUsername(),joinDTO.getIntroduce());
        return ResponseEntity.ok(updateUser);
    }

    //비밀번호 업데이트
    @PatchMapping("/{id}/password")
    public ResponseEntity<UserEntity> updatePassword(@PathVariable Long id,@RequestBody JoinDTO joinDTO){
        UserEntity updateUser= joinService.updatepassword(id,joinDTO.getPassword());
        return ResponseEntity.ok(updateUser);
    }

    //프로필 업데이트
    @PatchMapping("/{id}/profile")
    public ResponseEntity<UserEntity> updateProfile(@PathVariable Long id,@RequestParam("profileImage") MultipartFile profileImage) throws IOException {
        UserEntity updateUser= joinService.updateProfileImage(id,profileImage);

        return ResponseEntity.ok(updateUser);
    }
}
