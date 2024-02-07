package com.example.reservation.comment.Service;

import com.example.reservation.Board.Repository.BoardRepository;
import com.example.reservation.Board.entitiy.BoardEntity;
import com.example.reservation.comment.CommentEntity;
import com.example.reservation.comment.DTO.CommentDTO;
import com.example.reservation.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BoardRepository boardRepository;

    public List<CommentDTO> comments(Long boardId) {
        //1. 댓글 조회
        List<CommentEntity> comments = commentRepository.findByBoardId(boardId);
        //2. 엔티티 -> DTO 변환
        List<CommentDTO> dtos = new ArrayList<CommentDTO>();
        for(int i =0;i<comments.size();i++){
            CommentEntity c= comments.get(i);
            CommentDTO commentdto = CommentDTO.createCommentDTO(c);
            dtos.add(commentdto);
        }
        //3. 결과 반환
        return commentRepository.findByBoardId(boardId)
                .stream()
                .map(comment-> CommentDTO.createCommentDTO(comment))
                .collect(Collectors.toList());
    }

    @Transactional // 문제가 발생했을 때 롤백처리
    public CommentDTO create(Long boardId, CommentDTO dto) {
        //1.게시글 조회 및 예외 발생
        BoardEntity board = boardRepository.findById(boardId)
                .orElseThrow(()-> new IllegalArgumentException("댓글 생성 실패!"+ "대상 게시글이 없습니다."));


        //2. 댓글 엔티티 생성
        CommentEntity comment = CommentEntity.createComment(dto,board);
        //3. 댓글 엔티티를 DB에 저장

        CommentEntity created = commentRepository.save(comment);
        System.out.println("Created Entity: " + created);
        //4. DTO로 변환해 반환
        return CommentDTO.createCommentDTO(created);
    }
}
