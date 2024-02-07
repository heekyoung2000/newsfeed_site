package com.example.reservation.Like.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikeRequestDTO {

    private Integer userId;
    private Long boardId;

    public LikeRequestDTO(Integer userId, Long boardId){
        this.userId=userId;
        this.boardId=boardId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Long getBoardId() {
        return boardId;
    }
}
