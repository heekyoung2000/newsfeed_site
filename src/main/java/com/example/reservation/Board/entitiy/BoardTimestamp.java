package com.example.reservation.Board.entitiy;


import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

//게시판의 작성일시를 알려줌
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
//jpa가 시간에 대해 자동으로 값을 넣어주는 auditing 기능 사용
public class BoardTimestamp {
    @CreatedDate
    private LocalDateTime createdAt;
    // 생성 일자 객체 생성

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
