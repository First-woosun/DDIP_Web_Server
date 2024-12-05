package com.example.DDIP_web_server.repository;

import com.example.DDIP_web_server.entity.CrewRoomSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

    @Query("SELECT crs FROM CrewRoomSchedule crs " +
            "WHERE crs.crewRoom = :crewRoomId " +
            "AND crs.status = 'EXCHANGED'")
    List<CrewRoomSchedule> findExchangeableSchedulesByCrewRoomId(@Param("crewRoomId") Integer crewRoomId);

    @Query("SELECT crs FROM CrewRoomSchedule crs " +
            "WHERE crs.crewRoom = :crewRoomId " +
            "AND crs.member = :memberId " +
            "AND crs.status = 'ACTIVE' " +
            "AND FUNCTION('MONTH', crs.date) = FUNCTION('MONTH', CURRENT_DATE) " +
            "AND FUNCTION('YEAR', crs.date) = FUNCTION('YEAR', CURRENT_DATE)")
    List<CrewRoomSchedule> findSchedulesForCurrentMonth(@Param("crewRoomId") Integer crewRoomId, @Param("memberId") String memberId);

    @Query("SELECT crs FROM CrewRoomSchedule crs WHERE crs.member = :member")
    List<CrewRoomSchedule> getMySchedules(@Param("member") String MemberID);
}