package com.example.DDIP_web_server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.DDIP_web_server.entity.member;
import com.example.DDIP_web_server.repository.UserRepository;

@Service
public class loginAndSignup {

    @Autowired
    private UserRepository userRepository;

    public member signup(member member) {
        return userRepository.save(member);
    }

    public member login(String userid, String userpwd) {
        member member = userRepository.findByUserid(userid);
        if (member != null && member.getUserpwd().equals(userpwd)) {
            return member;  // 로그인 성공
        }
        return null;  // 로그인 실패
    }

    public boolean isUsernameTaken(String userid) {
        return userRepository.existsByUserid(userid);
    }

//    public boolean checkUseridExists(String userid) {
//        return userRepository.existsByUserid(userid);
//    }
}
