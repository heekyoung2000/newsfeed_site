package com.example.reservation.Like.controller;

import com.example.reservation.Like.DTO.LikeRequestDTO;
import com.example.reservation.Like.Service.LikeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LikeController {
    private final LikeService likeService;


    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/board/like")
    public String insert(@RequestBody @Valid LikeRequestDTO likeRequestDTO) throws Exception {
        likeService.insert(likeRequestDTO);
        return " 좋아용 성공";
    }

    @DeleteMapping("/board/like")
    public String delete(@RequestBody @Valid LikeRequestDTO likeRequestDTO) throws Exception {
        likeService.delete(likeRequestDTO);
        return " 좋아용 삭제";
    }


}
