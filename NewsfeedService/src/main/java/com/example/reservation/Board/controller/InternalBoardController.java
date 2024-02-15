package com.example.reservation.Board.controller;

import com.example.reservation.Board.Repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/internal/newsfeeds")
public class InternalBoardController {

    private final BoardRepository boardRepository;

    public InternalBoardController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    //보드가 있는지 확인
    @GetMapping
    public ResponseEntity<Boolean> checkBoardExists(@RequestParam(name="boardId") Long boardId){
        return ResponseEntity.ok().body(boardRepository.existsById(boardId));
    }

}
