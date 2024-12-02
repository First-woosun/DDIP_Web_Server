package com.example.DDIP_web_server.repository;

import com.example.DDIP_web_server.entity.CrewRoomMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CrewRoomMemberRepository extends JpaRepository<CrewRoomMember, Integer> {
    // 특정 crewRoomId에 따라 데이터를 가져오는 메서드 정의
    List<CrewRoomMember> findByCrewRoom(Integer crewRoom);

    CrewRoomMember findNameByMember(String member);
}
