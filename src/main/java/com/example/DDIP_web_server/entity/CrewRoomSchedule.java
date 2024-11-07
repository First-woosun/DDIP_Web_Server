package com.example.DDIP_web_server.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CrewRoomSchedule")
public class CrewRoomSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scheduleId;

    @ManyToOne
    @JoinColumn(name = "crew_room_id", nullable = false)
    private CrewRoom crewRoom;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    private Date startTime;

    @Temporal(TemporalType.TIME)
    @Column(nullable = false)
    private Date endTime;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;

    @Temporal(TemporalType.TIME)
    private Date totalHours;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public enum Status {
        ACTIVE, EXCHANGED
    }

    // Getters and Setters
    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Date totalHours) {
        this.totalHours = totalHours;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
