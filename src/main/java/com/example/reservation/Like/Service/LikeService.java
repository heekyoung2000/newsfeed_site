package com.example.reservation.Like.Service;

import com.example.reservation.Board.Repository.BoardRepository;
import com.example.reservation.Board.entitiy.BoardEntity;
import com.example.reservation.Like.DTO.LikeRequestDTO;
import com.example.reservation.Like.LikeEntity;
import com.example.reservation.Like.Repository.LikeRepository;
import com.example.reservation.Repository.UserRepository;
import com.example.reservation.entity.UserEntity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LikeService {
    @Autowired
    private final LikeRepository likeRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final BoardRepository boardRepository;


    public LikeService(LikeRepository likeRepository, UserRepository userRepository, BoardRepository boardRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.boardRepository = boardRepository;
    }

    @Transactional
    public void insert(LikeRequestDTO likeRequestDTO) throws Exception{
        UserEntity user = userRepository.findById(likeRequestDTO.getUserId())
                .orElseThrow(() -> new EmptyResultDataAccessException("Could not find member with id : " +likeRequestDTO.getUserId() , 1));


        BoardEntity board = boardRepository.findById(likeRequestDTO.getBoardId())
                .orElseThrow(() -> new EmptyResultDataAccessException("Could not found board id : " + likeRequestDTO.getBoardId(),1));

        // 이미 좋아요되어있으면 에러 반환
        if (likeRepository.findByUsernameAndBoard(user, board).isPresent()){
            //TODO 409에러로 변경
            throw new Exception();
        }
        long likeCount = board.getLikecount() != null ? board.getLikecount() : 0;

        LikeEntity like = new LikeEntity(board, user);
        likeRepository.save(like);

        // 좋아요 카운트 증가
        board.setLikecount(likeCount + 1);
        boardRepository.save(board);

    }

    @Transactional
    public void delete(LikeRequestDTO likeRequestDTO){
        UserEntity user = userRepository.findById(likeRequestDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Could not found user id : " + likeRequestDTO.getUserId()));

        BoardEntity board = boardRepository.findById(likeRequestDTO.getBoardId())
                .orElseThrow(() -> new EntityNotFoundException("Could not found board id : " + likeRequestDTO.getBoardId()));

        LikeEntity like = likeRepository.findByUsernameAndBoard(user,board)
                .orElseThrow(() -> new EntityNotFoundException("Could not found heart id"));

        likeRepository.delete(like);
        // 좋아요 카운트 감소
        board.setLikecount(board.getLikecount() - 1);
        boardRepository.save(board);
    }


}
