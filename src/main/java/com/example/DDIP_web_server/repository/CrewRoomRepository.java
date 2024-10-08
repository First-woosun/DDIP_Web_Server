package com.example.DDIP_web_server.repository;

import com.example.DDIP_web_server.entity.CrewRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  // Spring Data JPA 리포지토리로 설정
public interface CrewRoomRepository extends JpaRepository<CrewRoom, Long> {
    // 기본 CRUD 기능을 제공
}
