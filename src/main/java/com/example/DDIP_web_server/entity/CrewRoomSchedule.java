package com.example.DDIP_web_server.entity;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.Temporal;

@Entity
//@Table(name = "CrewRoomSchedule")
public class CrewRoomSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scheduleId;

    @Column(name = "crew_room_id", nullable = false)
    private Integer crewRoomId;

    @Column(name = "member_id", nullable = false)
    private Integer memberId;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "total_hours")
    private LocalTime totalHours;


    public enum Status {
        ACTIVE,
        EXCHANGED
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "status", columnDefinition = "ENUM('ACTIVE', 'EXCHANGED') DEFAULT 'ACTIVE'")
    private Status status = Status.ACTIVE;


    // getter와 setter 메서드 추가

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getCrewRoomId() {
        return crewRoomId;
    }

    public void setCrewRoomId(Integer crewRoomId) {
        this.crewRoomId = crewRoomId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(LocalTime totalHours) {
        this.totalHours = totalHours;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
