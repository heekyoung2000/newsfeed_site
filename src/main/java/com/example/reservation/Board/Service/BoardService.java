package com.example.reservation.Board.Service;

import com.example.reservation.Board.Repository.BoardRepository;
import com.example.reservation.Board.dto.BoardRequestDTO;
import com.example.reservation.Board.dto.BoardResponseDTO;
import com.example.reservation.Board.entitiy.BoardEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }


    @Transactional(readOnly = true)
    public List<BoardResponseDTO> getPosts() {
        //게시글을 생성일자를 기준으로 내림차순 정렬
        return boardRepository.findAllByOrderByCreatedAtDesc().stream().map(BoardResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public BoardResponseDTO createPost(BoardRequestDTO requestDTO){
        BoardEntity board = new BoardEntity(requestDTO);
        boardRepository.save(board);
        return new BoardResponseDTO(board);
    }

    //게시글의 id를 가진 데이터를 반환
    @Transactional
    public BoardResponseDTO getPost(Long id){
        return boardRepository.findById(id).map(BoardResponseDTO::new).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
    }
}
