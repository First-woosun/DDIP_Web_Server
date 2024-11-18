package com.example.DDIP_web_server.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CrewRoomExchange")
public class CrewRoomExchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer exchangeId;

    @Column(nullable = false, length = 100)
    private String crewRoom;

    @Column(nullable = false, length = 100)
    private String requestMember;

    @Column(nullable = false, length = 100)
    private String requestedSchedule;

    @Column(nullable = false, length = 100)
    private String targetMember;

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

    public String getCrewRoom() {
        return crewRoom;
    }

    public void setCrewRoom(String crewRoom) {
        this.crewRoom = crewRoom;
    }

    public String getRequestMember() {
        return requestMember;
    }

    public void setRequestMember(String requestMember) {
        this.requestMember = requestMember;
    }

    public String getRequestedSchedule() {
        return requestedSchedule;
    }

    public void setRequestedSchedule(String requestedSchedule) {
        this.requestedSchedule = requestedSchedule;
    }

    public String getTargetMember() {
        return targetMember;
    }

    public void setTargetMember(String targetMember) {
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
