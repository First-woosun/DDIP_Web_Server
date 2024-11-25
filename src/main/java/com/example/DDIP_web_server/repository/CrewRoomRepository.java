package com.example.DDIP_web_server.repository;

import com.example.DDIP_web_server.entity.CrewRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository  // Spring Data JPA 리포지토리로 설정
public interface CrewRoomRepository extends JpaRepository<CrewRoom, Long> {
    // CrewRoomMember 테이블에서 중복 없는 크루룸ID 가져오기
    @Query("SELECT DISTINCT crm.crewRoom FROM CrewRoomMember crm WHERE crm.member = :memberId")
    List<Integer> findDistinctCrewRoomIdsByMemberId(@Param("memberId") String memberId);

    // 특정 크루룸ID의 크루룸명 가져오기
    @Query("SELECT cr.crewRoomName FROM CrewRoom cr WHERE cr.crewRoomId = :crewRoomId")
    String findCrewRoomNameById(@Param("crewRoomId") Integer crewRoomId);
}
