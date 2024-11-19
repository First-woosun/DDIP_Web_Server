package com.example.DDIP_web_server.repository;

import com.example.DDIP_web_server.entity.CrewRoomSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrewRoomScheduleRepository extends JpaRepository<CrewRoomSchedule, Integer> {

    // 현재 주 스케줄 검색
    @Query(value = "SELECT * FROM CrewRoomSchedule c " +
            "WHERE c.member = :memberId " +
            "AND YEARWEEK(c.date, 1) = YEARWEEK(CURRENT_DATE, 1)",
            nativeQuery = true)
    List<CrewRoomSchedule> findCurrentWeekSchedulesByMemberId(@Param("memberId") String memberId);

    // 특정 월의 주별 근무 시간 및 급여 계산
    @Query(value = "SELECT YEARWEEK(c.date, 1) AS week, " +
            "SUM(c.totalHours) AS weeklyHours, " +
            "SUM(c.totalHours * c.pay) AS weeklyPay " +
            "FROM CrewRoomSchedule c " +
            "WHERE MONTH(c.date) = :month " +
            "GROUP BY YEARWEEK(c.date, 1)",
            nativeQuery = true)
    List<Object[]> findWeeklyHoursAndPayByMonth(@Param("month") int month);


/*
    // 특정 월의 주간 근무 시간 계산
    @Query(value = "SELECT YEARWEEK(c.date, 1) AS week, " +
            "SUM(TIMESTAMPDIFF(MINUTE, c.start_time, c.end_time) / 60.0) AS weeklyHours " +
            "FROM CrewRoomSchedule c " +
            "WHERE MONTH(c.date) = :month " +
            "GROUP BY YEARWEEK(c.date, 1)",
            nativeQuery = true)
    List<Object[]> findWeeklyHoursByMonth(@Param("month") int month);

    // 특정 월의 주별 급여 합계 계산
    @Query(value = "SELECT YEARWEEK(c.date, 1) AS week, " +
            "SUM((TIMESTAMPDIFF(MINUTE, c.start_time, c.end_time) / 60.0) * c.pay) AS weeklyPay " +
            "FROM CrewRoomSchedule c " +
            "WHERE MONTH(c.date) = :month " +
            "GROUP BY YEARWEEK(c.date, 1)",
            nativeQuery = true)
    List<Object[]> findWeeklyPayByMonth(@Param("month") int month);

*/


}
