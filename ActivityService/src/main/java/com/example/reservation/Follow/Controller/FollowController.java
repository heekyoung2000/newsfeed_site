package com.example.reservation.Follow.Controller;

import com.example.reservation.Follow.FollowRequest;
import com.example.reservation.Follow.Service.FollowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ActivityService")
public class FollowController {

    private final FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

    @PostMapping("/users/follow")
    public ResponseEntity follow(@RequestBody FollowRequest followRequest) {
        Long followerId = followRequest.getFollowerId();
        Long followingId = followRequest.getFollowingId();
        String result = String.valueOf(followService.follow(followerId, followingId));
        if (result != null && result.equals("Success")) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }

    }
}
