package com.example.DDIP_web_server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.DDIP_web_server.entity.User;
import com.example.DDIP_web_server.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User signup(User user) {
        return userRepository.save(user);
    }

    public User login(String userid, String userpwd) {
        User user = userRepository.findByUserid(userid);
        if (user != null && user.getUserpwd().equals(userpwd)) {
            return user;  // 로그인 성공
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
