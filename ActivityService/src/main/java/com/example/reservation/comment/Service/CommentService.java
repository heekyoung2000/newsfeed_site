package com.example.reservation.comment.Service;

import com.example.reservation.client.NewsfeedClient;
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


    private CommentService commentService;

    @Autowired
    private NewsfeedClient newsfeedClient;

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
//        BoardEntity board = boardRepository.findById(boardId)
//                .orElseThrow(()-> new IllegalArgumentException("댓글 생성 실패!"+ "대상 게시글이 없습니다."));

        //게시글 조회 후 없으면 null 처리
        if (!newsfeedClient.checkBoardExists(boardId)){
            System.out.println("게시글 id가 일치하지 않음");
            return null;
        }
        //2. 댓글 엔티티 생성
        CommentEntity comment = commentService.createComment(dto,boardId);
        //3. 댓글 엔티티를 DB에 저장

        CommentEntity created = commentRepository.save(comment);
        System.out.println("Created Entity: " + created);
        //4. DTO로 변환해 반환
        return CommentDTO.createCommentDTO(created);
    }

    public static CommentEntity createComment(CommentDTO dto, Long boardId) {
        //예외 발생
        System.out.println(boardId);
        System.out.println(dto.getBoardId());
        if(dto.getId()!=null) throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
//        if(dto.getBoardId() != boardId) throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못됐습니다.");
        //2. 입력된 댓글 내용이 비어있지 않은지 확인
        if (dto.getContent() == null || dto.getContent().isEmpty()) {
            throw new IllegalArgumentException("댓글 생성 실패! 댓글 내용이 비어있습니다.");
        }
        System.out.println("✅✅"+dto.getId());
        System.out.println("✅✅"+boardId);
        System.out.println("✅✅"+dto.getUsername());
        System.out.println("✅✅"+dto.getContent());

        //엔티티 생성 및 반환
        return new CommentEntity(
                dto.getId(), // 댓글 아이디
                dto.getBoardId(), // 부모 게시글
                dto.getUsername(), // 댓글 닉네임
                dto.getContent() // 댓글 본문
        );
    }
}
