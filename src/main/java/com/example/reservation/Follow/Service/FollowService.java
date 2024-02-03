package com.example.reservation.Follow.Service;

import com.example.reservation.Follow.FollowEntity;
import com.example.reservation.Follow.repository.FollowRepository;
import com.example.reservation.member.Repository.UserRepository;
import com.example.reservation.member.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class FollowService {
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public FollowService(FollowRepository followRepository, UserRepository userRepository) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
    }

    public String follow(Long followerId, Long followingId) {
        System.out.println(followerId);
        System.out.println(followingId);
        UserEntity follower = (UserEntity) userRepository.findById(followerId)
                .orElseThrow(() -> new IllegalArgumentException("Follower not found with id: " + followerId));
        UserEntity following = (UserEntity) userRepository.findById(followingId)
                .orElseThrow(() -> new IllegalArgumentException("Following not found with id: " + followingId));

        // 이미 팔로우 관계가 존재하는지 확인
        if (followRepository.existsByFollowerAndFollowing(follower, following)) {
            throw new IllegalArgumentException("Already following");
        }

        // 팔로우하려는 사용자가 자기 자신인지 확인
        if (followerId.equals(followingId)) {
            throw new IllegalArgumentException("Cannot follow yourself");
        }

        FollowEntity follow = new FollowEntity();
        follow.setFollower(follower);
        follow.setFollowing(following);
        follower.getFollowing().add(follow);
        following.getFollowers().add(follow);
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
