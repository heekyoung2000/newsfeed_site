package com.example.reservation.comment.repository;

import com.example.reservation.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//댓글 리파지터리(db에게 crud 요청을 보내는 jpa 인터페이스)
//jparepository<대상_엔티티, 대표키_값의_타입>
public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
    //특정 게시글의 모든 댓글 조회
    @Query(value = "SELECT * FROM comment_table WHERE board_id = :boardId", nativeQuery = true)
    List<CommentEntity> findByBoardId(Long boardId);

    //특정 닉네임의 모든 댓글 조회


}
