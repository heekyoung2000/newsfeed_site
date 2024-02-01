package com.example.reservation.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
// 회원가입 객체 설정

@Entity
@Table(name="person_table")
@Getter
@Setter
public class UserEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String username; //이름
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String introduce;//인사말

    @Column(nullable = false)
    private String email;//이메일



    private String role; // 권한

    //getter,setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }





}
