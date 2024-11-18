package com.example.DDIP_web_server.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CrewRoom")
public class CrewRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer crewRoomId;

    @Column(nullable = false, length = 100)
    private String crewRoomName;

    @Column(nullable = false, length = 100)
    private String shopName;

    @Column(nullable = false, length = 100)
    private String owner;

    @Column(length = 50)
    private String crewRoomInvitation;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    // crewRoomId를 인수로 받는 생성자 추가
    public CrewRoom(Integer crewRoomId) {
        this.crewRoomId = crewRoomId;
    }

    // 기본 생성자 필요
    public CrewRoom() {
    }

    // Getters and Setters
    public Integer getCrewRoomId() {
        return crewRoomId;
    }

    public void setCrewRoomId(Integer crewRoomId) {
        this.crewRoomId = crewRoomId;
    }

    public String getCrewRoomName() {
        return crewRoomName;
    }

    public void setCrewRoomName(String crewRoomName) {
        this.crewRoomName = crewRoomName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCrewRoomInvitation() {
        return crewRoomInvitation;
    }

    public void setCrewRoomInvitation(String crewRoomInvitation) {
        this.crewRoomInvitation = crewRoomInvitation;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
