package com.example.DDIP_web_server.entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

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


    @PrePersist
    private void generateInviteCode() {
        if (this.crewRoomInvitation == null || this.crewRoomInvitation.isEmpty()) {
                String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
                StringBuilder inviteCode = new StringBuilder();
                Random random = new Random();
                for (int i = 0; i < 6; i++) {
                    int index = random.nextInt(chars.length());
                    inviteCode.append(chars.charAt(index));
                }
            this.crewRoomInvitation = inviteCode.toString();
        }
    }
    // toString() 메서드 추가
    @Override
    public String toString() {
        return "CrewRoom{" +
                "crewRoomId=" + crewRoomId +
                ", crewRoomName='" + crewRoomName + '\'' +
                ", shopName='" + shopName + '\'' +
                ", owner='" + owner + '\'' +
                ", crewRoomInvitation='" + crewRoomInvitation + '\'' +
                '}';
    }
}
