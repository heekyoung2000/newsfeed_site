package com.example.reservation.Board.controller;

import com.example.reservation.Board.Repository.BoardRepository;
import com.example.reservation.Board.Service.BoardService;
import com.example.reservation.Board.dto.BoardRequestDTO;
import com.example.reservation.Board.dto.BoardResponseDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/NewsfeedService")
public class BoardController {
    private final BoardService boardService;
    private final BoardRepository boardRepository;

    public BoardController(BoardService boardService, BoardRepository boardRepository) {
        this.boardService = boardService;
        this.boardRepository = boardRepository;
    }

    //list 형태로 client로 보냄
    //게시글 전체 목록 조회
    @GetMapping("/board/post")
    public List<BoardResponseDTO> getPosts(){
        System.out.println("컨트롤러 넘어옴");
        return boardService.getPosts();
    }

    //게시글 생성
    @PostMapping("/board/new")
    public BoardResponseDTO createPost(@RequestBody BoardRequestDTO requestDTO){
        //현재 로그인한 사용자 정보 가져오기

        //로그인한 사용자가 없거나, 사용자 정보가 비정상적인 경우에 예외처리
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        // 로그인한 사용자가 없거나, 사용자 정보가 비정상적인 경우에 예외처리
        if (currentUsername == null || currentUsername.isEmpty()) {
            throw new RuntimeException();
        }

        // 로그인한 사용자와 작성자(author)가 일치하는지 확인
        if (!currentUsername.equals(requestDTO.getAuthor())) {
            return boardService.createPost(requestDTO);
        }
        return boardService.createPost(requestDTO);

    }

    //게시글 id당 조회
    @GetMapping("/board/post/{id}")
    public BoardResponseDTO getPost(@PathVariable Long id){
        return boardService.getPost(id);
    }
    
    
}
