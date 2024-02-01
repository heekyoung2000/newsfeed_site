package com.example.reservation.comment;

import com.example.reservation.Board.entitiy.BoardEntity;
import com.example.reservation.comment.DTO.CommentDTO;
import com.example.reservation.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "comment_table")
@NoArgsConstructor
public class CommentEntity{

    @Id//대표키 선언
    @GeneratedValue(strategy = GenerationType.IDENTITY) //대표키 id를 1씩 증가
    @Column(name = "comment_id")
    private Long id;

    //댓글 내용
    @Column(nullable = false)
    private String content;

    @ManyToOne // Comment 엔티티와 article 엔티티를 다대일 관계로 설정
    @JoinColumn(name="board_id") // 외래키 생성, Board 엔티티의 기본키(id)와 매핑
    private BoardEntity board;

    @ManyToOne
    @JoinColumn(name="username")
    private UserEntity username;

    public CommentEntity(Long id, BoardEntity board, String username, String content) {
    }


    public static CommentEntity createComment(CommentDTO dto, BoardEntity board) {
        //예외 발생
        System.out.println(dto.getBoardId());
        System.out.println(board.getId());
        if(dto.getId()!=null) throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        if(dto.getBoardId()!=board.getId()) {

            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못됐습니다.");
        }
        //엔티티 생성 및 반환
        return new CommentEntity(
                dto.getId(),
                board,
                dto.getUsername(),
                dto.getContent()
        );
    }

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
