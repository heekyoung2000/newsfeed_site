package com.example.reservation.Follow.repository;

import com.example.reservation.Follow.FollowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<FollowEntity,Long> {
//    boolean existsByFollowerAndFollowing(Long follower, Long following);

    boolean existsByFollowerAndFollowing(Long follower, Long following);




//    @Query("SELECT f FROM follow_table f WHERE f.following = :userId")
//    List<Follow> findFollowing(Long userId);
//
//    @Query("SELECT f FROM follow_table f WHERE f.following = :userId")
//    List<Follow> findFollowing(Long userId);
//    void deleteFollowByFromUser(UserEntity user);
//    @Query("select f from follow_table f where f.following = :from and f.follower = :to")
//    Optional<Follow> findFollow(@Param("from") UserEntity from_user, @Param("to") UserEntity to_user);
}
