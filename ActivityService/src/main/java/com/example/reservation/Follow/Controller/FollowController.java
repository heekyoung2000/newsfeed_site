package com.example.reservation.Follow.Controller;

import com.example.reservation.Follow.FollowRequest;
import com.example.reservation.Follow.Service.FollowService;
import com.example.reservation.member.Service.CustomUserDetailsService;
import com.example.reservation.member.Service.JoinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ActivityService")
public class FollowController {
    private final CustomUserDetailsService customUserDetailsService;
    private final FollowService followService;

    public FollowController(CustomUserDetailsService customUserDetailsService, JoinService joinService, CustomUserDetailsService customUserDetailsService1, FollowService followService) {
        this.customUserDetailsService = customUserDetailsService1;
        this.followService = followService;
    }

    @PostMapping("/users/follow")
    public ResponseEntity follow(@RequestBody FollowRequest followRequest) {
        Long followerId = followRequest.getFollowerId();
        Long followingId = followRequest.getFollowingId();
        String result = followService.follow(followerId, followingId);
        if (result != null && result.equals("Success")) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }

    }
}
