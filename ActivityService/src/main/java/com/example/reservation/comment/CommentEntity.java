package com.example.reservation.comment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@Table(name = "comment_table")
@NoArgsConstructor
public class CommentEntity{

    @Id//대표키 선언
    @GeneratedValue(strategy = GenerationType.IDENTITY) //대표키 id를 1씩 증가
    @Column(name = "comment_id")
    private Long id;

    //댓글 내용
    @Column
    private String content;

//    @ManyToOne // Comment 엔티티와 article 엔티티를 다대일 관계로 설정
//    @JoinColumn(name="board_id") // 외래키 생성, Board 엔티티의 기본키(id)와 매핑
    @Column
    private Long board;


    @Column
    private String username;

    public CommentEntity(Long id, Long board, String username, String content) {
        this.id = id;
        this.board = board;
        this.username = username;
        this.content = content;
    }




    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Long getBoard() {
        return board;
    }

    public String getUsername() {
        return username;
    }
}
