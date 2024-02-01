package com.example.reservation.comment.DTO;

import com.example.reservation.Board.entitiy.BoardEntity;
import com.example.reservation.comment.CommentEntity;
import com.example.reservation.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

//commentdto는 댓글 엔티티를 담을 그릇

@AllArgsConstructor
@NoArgsConstructor //매개변수가 아예 없는 기본 생성자 자동 생성
@Getter // 각 필드 값을 조회할 수 있는 getter 메서드 자동 생성
@ToString // 모든 필드를 출력할 수 있는 toString 메서드 자동 생성
public class CommentDTO {

    private Long id;
    //댓글 내용
    private String content;
    //게시글 내용
    private Long boardId;

    private String username;

    public CommentDTO(BoardEntity board, Long id, UserEntity username, String content) {
    }


    //????
 
    public static CommentDTO createCommentDTO(CommentEntity comment) {
        return new CommentDTO(
                comment.getBoard(),//댓글 id
                comment.getBoard().getId(), //댓글 엔티티가 속한 부모 게시글의 id
                comment.getUsername(),
                comment.getContent() // 댓글 엔티티의 내용
        );
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Long getBoardId() {
        return boardId;
    }

    public String getUsername() {
        return username;
    }
}
