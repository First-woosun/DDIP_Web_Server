package com.example.DDIP_web_server.repository;

import com.example.DDIP_web_server.entity.member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<member, String> {
    member findByUserid(String userid);  // 사용자 조회 메서드

//    boolean existsByUserid(String userid);
    boolean existsByUserid(String userid);
}
