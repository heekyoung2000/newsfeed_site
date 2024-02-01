package com.example.reservation.Board.dto;

import com.example.reservation.Board.entitiy.BoardEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardResponseDTO {
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public BoardResponseDTO(BoardEntity entity){
        this.id = entity.getId();//null로 바꿔야 하나?
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuther();
        this.createdAt = entity.getCreatedAt();
    }

}
