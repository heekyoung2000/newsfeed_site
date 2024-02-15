package com.example.reservation.comment.Controller;

import com.example.reservation.comment.DTO.CommentDTO;
import com.example.reservation.comment.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ActivityService")
public class CommentController {
    @Autowired
    private CommentService commentService;//댓글 리파지터리 객체 주입


    //1.댓글 조회
    @GetMapping("/board/{boardId}/comments")
    public ResponseEntity<List<CommentDTO>> comments(@PathVariable Long boardId){
        //서비스에 위임
        List<CommentDTO> commentdto = commentService.comments(boardId);
        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(commentdto);
    }

    //2. 댓글 생성

    //3. 댓글 수정

    //4. 댓글 삭제

    @PostMapping("/board/{boardId}/comments")
    public ResponseEntity<CommentDTO> create(@PathVariable Long boardId, @RequestBody CommentDTO dto){
        //서비스에 위임
        CommentDTO createdDTO = commentService.create(boardId,dto);
        //결과 응답
        if (createdDTO==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(createdDTO);
        }
    }
}
