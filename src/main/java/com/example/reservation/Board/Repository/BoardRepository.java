package com.example.reservation.Board.Repository;

import com.example.reservation.Board.entitiy.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
    List<BoardEntity> findAllByOrderByCreatedAtDesc();


    }
