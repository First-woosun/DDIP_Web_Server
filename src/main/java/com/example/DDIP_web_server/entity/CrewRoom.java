package com.example.DDIP_web_server.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;  // 올바른 패키지로 변경

@Entity  // JPA 엔티티 클래스임을 나타내는 애너테이션
public class CrewRoom {

    @Id  // 올바른 JPA 식별자 애너테이션 사용
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 기본 키 자동 증가 설정
    private Long id;  // 기본 키 필드 정의

    private String crewRoomName;  // 크루룸명
    private String businessName;  // 업장명
    private String ownerName;     // 사장 이름

    // 기본 생성자
    public CrewRoom() {
    }

    // 전체 필드를 초기화하는 생성자
    public CrewRoom(String crewRoomName, String businessName, String ownerName) {
        this.crewRoomName = crewRoomName;
        this.businessName = businessName;
        this.ownerName = ownerName;
    }

    // Getter 및 Setter 메소드
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCrewRoomName() {
        return crewRoomName;
    }

    public void setCrewRoomName(String crewRoomName) {
        this.crewRoomName = crewRoomName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
