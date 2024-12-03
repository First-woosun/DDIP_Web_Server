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
    public Integer getcrewRoomMemberId() {
        return crewRoomMemberId;
    }

    public void setcrewRoomMemberId(Integer crewRoomMemberId) {
        this.crewRoomMemberId = crewRoomMemberId;
    }

    public Integer getcrewRoom() {
        return crewRoom;
    }

    public void setcrewRoom(Integer crewRoom) {
        this.crewRoom = crewRoom;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getcolor() {return color;}

    public void setcolor(String color) {this.color = color;}

    public Date getstartDate() {
        return startDate;
    }

    public void setstartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getcontactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }


    public String getmemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) { this.memberType = memberType; }

    public void getall(){
        System.out.println(getMember());
        System.out.println(getmemberType());
        System.out.println(getcontactNumber());
        System.out.println(getstartDate());
        System.out.println(getcolor());
    }

    @Override
    public String toString() {
        return "CrewRoomMember{" +
                "crewRoomMemberId=" + crewRoomMemberId +
                ", crewRoom=" + crewRoom +
                ", member='" + member + '\'' +
                ", color='" + color + '\'' +
                ", startDate=" + startDate +
                ", contactNumber='" + contactNumber + '\'' +
                ", memberType='" + memberType + '\'' +
                '}';
    }


}