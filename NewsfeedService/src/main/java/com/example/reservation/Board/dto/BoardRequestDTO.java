package com.example.reservation.Board.dto;

import lombok.Getter;

@Getter
public class BoardRequestDTO {
    private String title;
    private String content;
    private String author;
    private String password;

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public String getPassword() {
        return password;
    }
}
