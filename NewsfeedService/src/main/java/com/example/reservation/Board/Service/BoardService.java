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
        System.out.println("게시글 찾으러 service 옴");
        //내 id에서 내가 팔로우 한 사람들의 id를 찾고 그 사람들의 게시글 리스트로 출력
//        final follower = followRepository.get
        //현재 사용잦의 id를 이용해서 팔로우한 사람들의 id를 가져옴

        return boardRepository.findAllByOrderByCreatedAtDesc().stream().map(BoardResponseDTO::new).toList();
    }

    @Transactional
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
