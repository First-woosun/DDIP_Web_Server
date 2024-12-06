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

    List<CrewRoomSchedule> findByCrewRoomAndMemberAndStatus(Integer crewRoom, String member, String status);
    List<CrewRoomSchedule> findByCrewRoomAndStatus(Integer crewRoom, String status);

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

    // 특정 월, 멤버, 크루룸에 해당하는 스케줄 데이터를 조회하는 메서드
    @Query("SELECT s FROM CrewRoomSchedule s WHERE MONTH(s.date) = :month AND s.member = :member AND s.crewRoom = :crewRoom")
    List<CrewRoomSchedule> findSchedulesByMonthAndMemberAndCrewRoom(@Param("month") int month, @Param("member") String member, @Param("crewRoom") Integer crewRoom);

    @Query("SELECT s FROM CrewRoomSchedule s WHERE s.crewRoom = :crewroom AND s.member = :member AND s.status = 'ACTIVE' AND s.date BETWEEN :startDate AND :endDate")
    List<CrewRoomSchedule> findActiveSchedulesByCrewRoomAndMember(
            @Param("crewroom") int crewRoom,
            @Param("member") int member,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    @Query("SELECT DISTINCT s.member FROM CrewRoomSchedule s WHERE s.crewRoom = :crewroom AND s.status = 'ACTIVE'")
    List<Integer> findDistinctMembersByCrewRoom(@Param("crewroom") int crewRoom);

    // 사용자 ID와 일치하는 스케줄 모두 불러오기
    @Query("SELECT crs FROM CrewRoomSchedule crs WHERE crs.member = :member")
    List<CrewRoomSchedule> getMySchedules(@Param("member") String MemberID);

    //crewroom 번호와 일치하는 모든 스케줄 불러오기
    @Query("SELECT crs FROM CrewRoomSchedule crs WHERE crs.crewRoom = :crewRoom")
    List<CrewRoomSchedule> getAllSchedules(@Param("crewRoom") Integer crewRoom);
}

