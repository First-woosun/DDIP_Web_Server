package com.example.DDIP_web_server.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CrewRoomLog")
public class CrewRoomLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer logId;

    @ManyToOne
    @JoinColumn(name = "crew_room_id")
    private CrewRoom crewRoom;

    //INSERT, UPDATE, DELETE, EXCHANGE
    @Column(length =20, nullable = false)
    private String action;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private CrewRoomSchedule schedule;

    @ManyToOne
    @JoinColumn(name = "exchange_id")
    private CrewRoomExchange exchange;

    @ManyToOne
    @JoinColumn(name = "previous_member_id")
    private Member previousMember;

    @ManyToOne
    @JoinColumn(name = "new_member_id")
    private Member newMember;

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

    public CrewRoom getCrewRoom() {
        return crewRoom;
    }

    public void setCrewRoom(CrewRoom crewRoom) {
        this.crewRoom = crewRoom;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public CrewRoomSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(CrewRoomSchedule schedule) {
        this.schedule = schedule;
    }

    public CrewRoomExchange getExchange() {
        return exchange;
    }

    public void setExchange(CrewRoomExchange exchange) {
        this.exchange = exchange;
    }

    public Member getPreviousMember() {
        return previousMember;
    }

    public void setPreviousMember(Member previousMember) {
        this.previousMember = previousMember;
    }

    public Member getNewMember() {
        return newMember;
    }

    public void setNewMember(Member newMember) {
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
