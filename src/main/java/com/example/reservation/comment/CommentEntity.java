package com.example.reservation.comment;

import com.example.reservation.Board.entitiy.BoardEntity;
import com.example.reservation.comment.DTO.CommentDTO;
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

    @ManyToOne // Comment 엔티티와 article 엔티티를 다대일 관계로 설정
    @JoinColumn(name="board_id") // 외래키 생성, Board 엔티티의 기본키(id)와 매핑
    private BoardEntity board;


    @Column
    private String username;

    public CommentEntity(Long id, BoardEntity board, String username, String content) {
        this.id = id;
        this.board = board;
        this.username = username;
        this.content = content;
    }


    public static CommentEntity createComment(CommentDTO dto, BoardEntity board) {
        //예외 발생
        if(dto.getId()!=null) throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        if(dto.getBoardId() != board.getId()) throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못됐습니다.");
        //2. 입력된 댓글 내용이 비어있지 않은지 확인
        if (dto.getContent() == null || dto.getContent().isEmpty()) {
            throw new IllegalArgumentException("댓글 생성 실패! 댓글 내용이 비어있습니다.");
        }
        System.out.println("✅✅"+dto.getId());
        System.out.println("✅✅"+board);
        System.out.println("✅✅"+dto.getUsername());
        System.out.println("✅✅"+dto.getContent());

        //엔티티 생성 및 반환
        return new CommentEntity(
                dto.getId(), // 댓글 아이디
                board, // 부모 게시글
                dto.getUsername(), // 댓글 닉네임
                dto.getContent() // 댓글 본문
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

    public String getUsername() {
        return username;
    }
}
