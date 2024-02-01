package com.example.reservation.controller;


import com.example.reservation.Service.JoinService;
import com.example.reservation.dto.EmailRequestDTO;
import com.example.reservation.dto.JoinDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService){
        this.joinService = joinService;
    }
    @PostMapping("/join")
    public String JoinProcess(JoinDTO joinDTO, EmailRequestDTO emailRequestDTO){
        System.out.println(joinDTO.getUsername());
        System.out.println(joinDTO.getIntroduce());
        System.out.println(emailRequestDTO.getEmail());
        joinService.joinProcess(joinDTO,emailRequestDTO);

        return "ok";
    }
}
