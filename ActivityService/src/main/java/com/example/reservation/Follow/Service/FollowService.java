package com.example.reservation.Follow.Service;

import com.example.reservation.Follow.FollowEntity;
import com.example.reservation.Follow.repository.FollowRepository;
import com.example.reservation.client.UserClient;
import org.springframework.stereotype.Service;

@Service
public class FollowService {
    private final FollowRepository followRepository;
    private final UserClient userClient;

    public FollowService(FollowRepository followRepository,UserClient userClient) {
        this.followRepository = followRepository;
        this.userClient = userClient;
    }

    public String follow(Long followerId, Long followingId) {
        System.out.println(followerId);
        System.out.println(followingId);
        //유저 엔티티에서 가져옴
//        Long follower = userClient.getUserById(followerId);
//        Long following = userClient.getUserById(followingId);
//        UserEntity follower =  userRepository.findById(followerId);
//                .orElseThrow(() -> new IllegalArgumentException("Follower not found with id: " + followerId));
//        UserEntity following =  userRepository.findById(followingId);
////                .orElseThrow(() -> new IllegalArgumentException("Following not found with id: " + followingId));

        //유저가 있는지 확인
        if (userClient.checkUserExists(followerId)==false){
            return "등록된 유저가 아닙니다";
        }
        // 이미 팔로우 관계가 존재하는지 확인
        if (followRepository.existsByFollowerAndFollowing(followerId, followingId)) {
            return "이미 팔로우 되어 있습니다.";
        }

        // 팔로우하려는 사용자가 자기 자신인지 확인
        if (followerId.equals(followingId)) {
            return "자기 자신입니다. 팔로우할 수 없습니다.";
        }

        FollowEntity follow = new FollowEntity();
        follow.setFollower(followerId);
        follow.setFollowing(followingId);
        followRepository.save(follow);

        return "Success";
    }
//    private final FollowRepository followRepository;
//    private final UserRepository userRepository;
//
//    public FollowService(FollowRepository followRepository, UserRepository userRepository) {
//        this.followRepository = followRepository;
//        this.userRepository = userRepository;
//    }
//
//    public String follow(UserEntity following, UserEntity follower,FollowEntity follow) {
//        // 이미 팔로우 관계가 존재하는지 확인
//        if (followRepository.existsByFollowerAndFollowing(follower, following)) {
//            return "AlreadyFollowing";
//        }
//
//        // 팔로우하려는 사용자가 자기 자신인지 확인
//        if (following.getId()==(follower.getId())) {
//            return "CannotFollowYourself";
//        }
//
//        follow.setFollower(follower);
//        follow.setFollowing(following);
//        follower.getFollowing().add(follow);
//        following.getFollowers().add(follow);
//        followRepository.save(follow);
//        return "Success";
    }




//    public String cancelFollow(UserEntity user) {
//        followRepository.deleteFollowByFromUser(user);
//        return "Success";
//    }
