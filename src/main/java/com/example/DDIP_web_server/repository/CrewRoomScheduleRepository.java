package com.example.DDIP_web_server.repository;

import com.example.DDIP_web_server.entity.CrewRoomSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository  // Spring Data JPA 리포지토리로 설정
public interface CrewRoomScheduleRepository extends JpaRepository<CrewRoomSchedule, Long> {

    List<CrewRoomSchedule> findCurrentWeekSchedulesByMemberId(String memberId);
    @Query(value = "SELECT YEARWEEK(s.date, 1) AS week, " +
            "SUM(TIMESTAMPDIFF(MINUTE, s.start_time, s.end_time) / 60.0) AS weeklyHours " +
            "FROM CrewRoomSchedule s " +
            "WHERE MONTH(s.date) = :month " +
            "GROUP BY YEARWEEK(s.date, 1)",
            nativeQuery = true)
    List<Object[]> findWeeklyHoursByMonth(int month);

}
