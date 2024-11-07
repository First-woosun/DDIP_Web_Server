//수정 후 삭제 요망
package com.example.DDIP_web_server.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class InviteCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private LocalDateTime createdAt;


    // 생성자: 초대 코드 생성 시 현재 시간을 createdAt으로 설정
    public InviteCode(String code) {
        this.code = code;
        this.createdAt = LocalDateTime.now();
    }

    // 기본 생성자 (JPA 사용을 위해 필요)
    protected InviteCode() {
    }

    // Getter 및 Setter
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
