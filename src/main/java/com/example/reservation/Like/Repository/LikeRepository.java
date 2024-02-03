package com.example.reservation.Like.Repository;

import com.example.reservation.Board.entitiy.BoardEntity;
import com.example.reservation.Like.LikeEntity;
import com.example.reservation.member.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikeEntity,Long> {
    Optional<LikeEntity> findByUsernameAndBoard(UserEntity username, BoardEntity board);
}
