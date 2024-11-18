package com.example.DDIP_web_server.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CrewRoomExchange")
public class CrewRoomExchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer exchangeId;

    @ManyToOne
    @JoinColumn(name = "crew_room_id", nullable = false)
    private CrewRoom crewRoom;

    @ManyToOne
    @JoinColumn(name = "request_member_id", nullable = false)
    private Member requestMember;

    @ManyToOne
    @JoinColumn(name = "requested_schedule_id", nullable = false)
    private CrewRoomSchedule requestedSchedule;

    @ManyToOne
    @JoinColumn(name = "target_member_id")
    private Member targetMember;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date exchangeDate;

    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    private Date exchangeStartTime;

    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    private Date exchangeEndTime;

    //REQUESTED, COMPLETED, CANCELLED
    @Column(length =20, nullable = false)
    private String exchangeStatus;


    // Getters and Setters
    public Integer getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(Integer exchangeId) {
        this.exchangeId = exchangeId;
    }

    public CrewRoom getCrewRoom() {
        return crewRoom;
    }

    public void setCrewRoom(CrewRoom crewRoom) {
        this.crewRoom = crewRoom;
    }

    public Member getRequestMember() {
        return requestMember;
    }

    public void setRequestMember(Member requestMember) {
        this.requestMember = requestMember;
    }

    public CrewRoomSchedule getRequestedSchedule() {
        return requestedSchedule;
    }

    public void setRequestedSchedule(CrewRoomSchedule requestedSchedule) {
        this.requestedSchedule = requestedSchedule;
    }

    public Member getTargetMember() {
        return targetMember;
    }

    public void setTargetMember(Member targetMember) {
        this.targetMember = targetMember;
    }

    public Date getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(Date exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    public Date getExchangeStartTime() {
        return exchangeStartTime;
    }

    public void setExchangeStartTime(Date exchangeStartTime) {
        this.exchangeStartTime = exchangeStartTime;
    }

    public Date getExchangeEndTime() {
        return exchangeEndTime;
    }

    public void setExchangeEndTime(Date exchangeEndTime) {
        this.exchangeEndTime = exchangeEndTime;
    }

    public String getExchangeStatus() {
        return exchangeStatus;
    }

    public void setExchangeStatus(String exchangeStatus) {
        this.exchangeStatus = exchangeStatus;
    }
}
