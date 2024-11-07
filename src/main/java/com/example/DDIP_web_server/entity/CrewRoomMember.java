package com.example.DDIP_web_server.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CrewRoomMember")
public class CrewRoomMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer crewRoomMemberId;

    @ManyToOne
    @JoinColumn(name = "crew_room_id", nullable = false)
    private CrewRoom crewRoom;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(length = 20)
    private String color;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(length = 20)
    private String contactNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberType memberType;

    public enum MemberType {
        OWNER, CREW
    }

    // Getters and Setters
    public Integer getCrewRoomMemberId() {
        return crewRoomMemberId;
    }

    public void setCrewRoomMemberId(Integer crewRoomMemberId) {
        this.crewRoomMemberId = crewRoomMemberId;
    }

    public CrewRoom getCrewRoom() {
        return crewRoom;
    }

    public void setCrewRoom(CrewRoom crewRoom) {
        this.crewRoom = crewRoom;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

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

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }
}
