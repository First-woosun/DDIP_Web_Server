package com.example.DDIP_web_server.service;

import com.example.DDIP_web_server.entity.Member;
import com.example.DDIP_web_server.repository.MyPageRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MyPageService {
    @Autowired
    private MyPageRepository myPageRepository;

    public Member getMemberById(String id) {
        return myPageRepository.findByid(id);
    }
}
