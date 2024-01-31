package com.example.reservation.comment.Service;

import com.example.reservation.comment.CommentEntity;
import com.example.reservation.comment.DTO.CommentDTO;
import com.example.reservation.comment.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommentService {

    public List<CommentDTO> comments(Long boardId) {
        //1. 댓글 조회
        List<CommentEntity> comments = CommentRepository.findByBoardId(boardId);
        //2. 엔티티 -> DTO 변환
        List<CommentDTO> dtos = new ArrayList<CommentDTO>();
        for(int i =0;i<comments.size();i++){
            CommentEntity c= comments.get(i);
            CommentDTO commentdto = CommentDTO.createCommentDTO(c);
            dtos.add(commentdto);
        }
        //3. 결과 반환
        return CommentRepository.findByBoardId(boardId)
                .stream()
                .map(comment-> CommentDTO.createCommentDTO(comment))
                .collect(Collectors.toList());
    }
}
