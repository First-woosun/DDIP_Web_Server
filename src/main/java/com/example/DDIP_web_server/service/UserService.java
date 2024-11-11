package com.example.DDIP_web_server.service;

import com.example.DDIP_web_server.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.DDIP_web_server.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Member signup(Member user) {
        return userRepository.save(user);
    }

    public Member login(String id, String password) {
        Member user = userRepository.findByid(id);
        if (user != null && user.getPassword().equals(password)) {
            return user;  // 로그인 성공
        }
        return null;  // 로그인 실패
    }

    public boolean isUsernameTaken(String id) {
        return userRepository.existsByid(id);
    }

    public boolean isAdminAccount(String id) {
        Member user = userRepository.findByid(id);
        return user.getUserType();
    }

//    public boolean checkUseridExists(String userid) {
//        return userRepository.existsByUserid(userid);
//    }
}
