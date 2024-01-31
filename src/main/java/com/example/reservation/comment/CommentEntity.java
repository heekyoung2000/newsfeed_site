package com.example.reservation.comment;

import com.example.reservation.Board.entitiy.BoardEntity;
import com.example.reservation.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comment_table")

public class CommentEntity{

    @Id//대표키 선언
    @GeneratedValue(strategy = GenerationType.IDENTITY) //대표키 id를 1씩 증가
    @Column(name = "Comment_id")
    private Long id;

    //댓글 내용
    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch =  FetchType.LAZY) // Comment 엔티티와 article 엔티티를 다대일 관계로 설정
    @JoinColumn(name="board_id") // 외래키 생성, Board 엔티티의 기본키(id)와 매핑
    private BoardEntity board;

    @Column(nullable = false)
    private UserEntity username;

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public BoardEntity getBoard() {
        return board;
    }

    public UserEntity getUsername() {
        return username;
    }
}
