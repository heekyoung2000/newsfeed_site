package com.example.reservation.comment.Controller;

import com.example.reservation.Board.Repository.BoardRepository;
import com.example.reservation.comment.DTO.CommentDTO;
import com.example.reservation.comment.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private CommentService commentService;//댓글 리파지터리 객체 주입
    
    @Autowired
    private BoardRepository boardRepository; // 게시글 리파지터리 객체 주입
    
    //1.댓글 조회
    @GetMapping("/board/boardId/comments")
    public ResponseEntity<List<CommentDTO>> comments(@PathVariable Long boardId){
        //서비스에 위임
        List<CommentDTO> commentdto = commentService.comments(boardId);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(commentdto);
    }
    
    //2. 댓글 생성
    
    //3. 댓글 수정
    
    //4. 댓글 삭제

//    @PostMapping("/board/{id]/comment")
//    public void writeComment(@PathVariable int id, CommentReqeustDTO commentReqeustDTO, Neo4jProperties.Authentication authentication){
//        UserDetails userDetails =  UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//
//        return;
//    }
}
