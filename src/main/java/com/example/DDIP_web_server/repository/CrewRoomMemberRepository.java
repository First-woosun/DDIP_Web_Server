package com.example.DDIP_web_server.repository;

import com.example.DDIP_web_server.entity.CrewRoomMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrewRoomMemberRepository extends JpaRepository<CrewRoomMember, Integer> {

    // memberId를 기준으로 CrewRoomMember 엔터티를 찾는 메서드
    CrewRoomMember findByMemberId(String member_id);
}
