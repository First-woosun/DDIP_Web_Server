package com.example.DDIP_web_server.repository;

import com.example.DDIP_web_server.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
