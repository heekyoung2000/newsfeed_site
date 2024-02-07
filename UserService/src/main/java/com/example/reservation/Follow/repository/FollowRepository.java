package com.example.reservation.Follow.repository;

import com.example.reservation.Follow.FollowEntity;
import com.example.reservation.member.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<FollowEntity,Long> {
    boolean existsByFollowerAndFollowing(UserEntity follower, UserEntity following);

//    void deleteFollowByFromUser(UserEntity user);
//    @Query("select f from follow_table f where f.following = :from and f.follower = :to")
//    Optional<Follow> findFollow(@Param("from") UserEntity from_user, @Param("to") UserEntity to_user);
}
