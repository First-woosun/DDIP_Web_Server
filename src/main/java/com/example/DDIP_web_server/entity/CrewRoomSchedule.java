package com.example.DDIP_web_server.entity;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "CrewRoomSchedule")
public class CrewRoomSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scheduleId;

    @Column(nullable = false)
    private Integer crewRoom;

    @Column(nullable = false, length = 100)
    private String member;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;

    @Column
    private Double  totalHours;

    @Column
    private Integer pay;

    //ACTIVE, EXCHANGED, COMPLETE
    @Column(length =20, nullable = false)
    private String status;

    // Getters and Setters
    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double  getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Double totalHours) {
        this.totalHours = totalHours;
    }

    // startTime과 endTime 차이 계산 메서드
    @PrePersist
    @PreUpdate
    public void calculateTotalHours() {
        if (startTime != null && endTime != null) {
            // startTime과 endTime의 차이를 분 단위로 계산
            long differenceInMinutes = Duration.between(startTime, endTime).toMinutes();

            // 분을 시간 단위로 변환하여 totalHours에 저장
            double hours = differenceInMinutes / 60.0;
            this.totalHours = hours;
        } else {
            this.totalHours = 0.0;  // startTime 또는 endTime이 null인 경우 0으로 설정
        }
    }

    public Integer getPay() {return pay;}
    public void setPay(Integer pay) {this.pay = pay;}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
