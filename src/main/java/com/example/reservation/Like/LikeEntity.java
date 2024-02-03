package com.example.reservation.Like;

import com.example.reservation.Board.entitiy.BoardEntity;
import com.example.reservation.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "like_table")
public class LikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity username;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity board;

    public LikeEntity(BoardEntity board, UserEntity username) {
        this.board= board;
        this.username=username;
    }


    public Long getId() {
        return id;
    }

    public UserEntity getUsername() {
        return username;
    }

    public BoardEntity getBoard() {
        return board;
    }

}
