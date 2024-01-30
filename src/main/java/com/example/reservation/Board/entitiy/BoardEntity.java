package com.example.reservation.Board.entitiy;


import com.example.reservation.Board.dto.BoardRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="board_table")
@Getter
@Setter
@NoArgsConstructor
public class BoardEntity extends BoardTimestamp {
    //id와 generatedvalue를 동시에 사용하면 jpa가 기본키를 자동으로 생성해줌
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //column은 객체 필드를 테이블의 컬럼으로 맵핑시킴
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String auther;

    @Column(nullable = false)
    private String password;


    //생성자를 만들어줘야함
    public BoardEntity(BoardRequestDTO requestDTO){
        this.title = requestDTO.getTitle();
        this.content = requestDTO.getContent();
        this.auther = requestDTO.getAuthor();
        this.password = requestDTO.getPassword();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
