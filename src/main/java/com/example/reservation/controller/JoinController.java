package com.example.reservation.controller;


import com.example.reservation.Service.JoinService;
import com.example.reservation.dto.EmailRequestDTO;
import com.example.reservation.dto.JoinDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@ResponseBody
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService){
        this.joinService = joinService;
    }
    @PostMapping("/join")
    public String JoinProcess(JoinDTO joinDTO, EmailRequestDTO emailRequestDTO, MultipartFile profileImage){
        System.out.println(joinDTO.getUsername());
        System.out.println(joinDTO.getIntroduce());
        System.out.println(emailRequestDTO.getEmail());
        try {
            joinService.joinProcess(joinDTO,emailRequestDTO,profileImage);
            return "회원가입이 완료되었습니다.";
        } catch(Exception e){
            return "회원가입 중 오류가 발생했습니다.";
        }


    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest servletRequest){
        joinService.logout();
        return ResponseEntity.status(HttpStatus.OK).body("로그아웃된 회원입니다.");

    }


}
