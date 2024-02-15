package com.example.reservation.Follow;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="follow_table")
public class FollowEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "follower_id")
    @Column
    //팔로우를 한사람
    private Long follower;
//    @ManyToOne(fetch = FetchType.LAZY)
    //팔로우를 당한 사람
//    @JoinColumn(name = "following_id")
    @Column
    private Long following;

    public FollowEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFollower() {
        return follower;
    }

    public void setFollower(Long follower) {
        this.follower = follower;
    }

    public Long getFollowing() {
        return following;
    }

    public void setFollowing(Long following) {
        this.following = following;
    }
}
