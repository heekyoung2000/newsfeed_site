package com.example.reservation.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/UserService")
public class AdminController {

    @GetMapping("/admin")
    public String adminP(){
        return "로그인 성공!";
    }



}


