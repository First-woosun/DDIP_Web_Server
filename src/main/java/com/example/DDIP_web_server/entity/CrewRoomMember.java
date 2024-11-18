package com.example.DDIP_web_server.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CrewRoomMember")
public class CrewRoomMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer crewRoomMemberId;

    @Column(nullable = false)
    private Integer crewRoom;

    @Column(nullable = false, length = 100)
    private String member;

    @Column(length = 20)
    private String color;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(length = 20)
    private String contactNumber;

    //owner crew
    @Column(length =100, nullable = false)
    private String memberType;

    // Getters and Setters
    public Integer getCrewRoomMemberId() {
        return crewRoomMemberId;
    }

    public void setCrewRoomMemberId(Integer crewRoomMemberId) {
        this.crewRoomMemberId = crewRoomMemberId;
    }

    public Integer getCrewRoom() {
        return crewRoom;
    }

    public void setCrewRoom(Integer crewRoom) {
        this.crewRoom = crewRoom;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getColor() {return color;}

    public void setColor(String color) {this.color = color;}

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }


    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) { this.memberType = memberType; }

}
