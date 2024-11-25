package com.example.DDIP_web_server.service;

import com.example.DDIP_web_server.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.DDIP_web_server.repository.MemberRepository;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member findById(String id) {
        return memberRepository.findById(id).orElse(null);
    }

    public Member signup(Member user) {
        return memberRepository.save(user);
    }

    public Member login(String id, String password) {
        Member user = memberRepository.findByid(id);
        if (user != null && user.getPassword().equals(password)) {
            return user;  // 로그인 성공
        }
        return null;  // 로그인 실패
    }

    public boolean isUsernameTaken(String id) {
        return memberRepository.existsByid(id);
    }

    public String isAdminAccount(String id) {
        Member user = memberRepository.findByid(id);
        return user.getUser_type();
    }

    public boolean updateMember(String id, Member updatedMember) {
        if (memberRepository.existsById(id)) {
            Member existingMember = memberRepository.findById(id).get();
            existingMember.setName(updatedMember.getName());
            existingMember.setEmail(updatedMember.getEmail());
            existingMember.setContact_number(updatedMember.getContact_number());
            existingMember.setPassword(updatedMember.getPassword());
            memberRepository.save(existingMember);
            return true;
        }
        return false;
    }

    // 회원 탈퇴 함수
    public boolean deleteMember(String id) {
        if (memberRepository.existsById(id)) {
            memberRepository.deleteById(id);
            return true;
        }
        return false;
    }
}