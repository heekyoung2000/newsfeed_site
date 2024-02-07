package com.example.reservation.Follow;

import com.example.reservation.member.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="follow_table")
public class FollowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower_id")
    //팔로우를 한사람
    private UserEntity follower;
    @ManyToOne(fetch = FetchType.LAZY)
    //팔로우를 당한 사람
    @JoinColumn(name = "following_id")
    private UserEntity following;

    public FollowEntity() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFollower(UserEntity follower) {
        this.follower = follower;
    }

    public void setFollowing(UserEntity following) {
        this.following = following;
    }

    public Long getd() {
        return id;
    }

    public UserEntity getFollower() {
        return follower;
    }

    public UserEntity getFollowing() {
        return following;
    }

}
