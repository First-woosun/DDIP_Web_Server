package com.example.DDIP_web_server.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CrewRoomLog")
public class CrewRoomLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logId;

    @Column(nullable = false, length = 100)
    private String crewRoom;

    //INSERT, UPDATE, DELETE, EXCHANGE
    @Column(length =20, nullable = false)
    private String action;

    @Column(nullable = false, length = 100)
    private String schedule;

    @Column(nullable = false, length = 100)
    private String exchange;

    @Column(nullable = false, length = 100)
    private String previousMember;

    @Column(nullable = false, length = 100)
    private String newMember;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date changeTime;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date changeDate;

    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    private Date changeStartTime;

    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    private Date changeEndTime;

    @Column(length = 255)
    private String description;

    // Getters and Setters
    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getCrewRoom() {
        return crewRoom;
    }

    public void setCrewRoom(String crewRoom) {
        this.crewRoom = crewRoom;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getPreviousMember() {
        return previousMember;
    }

    public void setPreviousMember(String previousMember) {
        this.previousMember = previousMember;
    }

    public String getNewMember() {
        return newMember;
    }

    public void setNewMember(String newMember) {
        this.newMember = newMember;
    }

    public Date getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public Date getChangeStartTime() {
        return changeStartTime;
    }

    public void setChangeStartTime(Date changeStartTime) {
        this.changeStartTime = changeStartTime;
    }

    public Date getChangeEndTime() {
        return changeEndTime;
    }

    public void setChangeEndTime(Date changeEndTime) {
        this.changeEndTime = changeEndTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
