package com.example.DDIP_web_server.repository;

import com.example.DDIP_web_server.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyPageRepository extends JpaRepository<Member, String>{
    Member findByid(String id);
}
