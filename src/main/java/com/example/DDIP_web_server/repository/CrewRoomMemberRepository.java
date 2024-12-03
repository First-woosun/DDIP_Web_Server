package com.example.DDIP_web_server.repository;

import com.example.DDIP_web_server.entity.CrewRoomMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrewRoomMemberRepository extends JpaRepository<CrewRoomMember, Integer> {
}
