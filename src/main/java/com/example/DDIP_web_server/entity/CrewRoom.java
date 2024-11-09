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

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Member owner;

    @Column(length = 50)
    private String crewRoomInvitation;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

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

    public Member getOwner() {
        return owner;
    }

    public void setOwner(Member owner) {
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
