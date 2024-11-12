package com.example.DDIP_web_server.controller;

import com.example.DDIP_web_server.entity.Member;
import com.example.DDIP_web_server.repository.MyPageRepository;
import com.example.DDIP_web_server.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")

public class MypageController {
    @Autowired
    private MyPageService myPageService;

    @GetMapping("/collectData")
    public ResponseEntity<Map<String, String>> collectData(@RequestParam String id) {
        Member member = myPageService.getMemberById(id);
        Map<String, String> response = new HashMap<String, String>();
        response.put("name", member.getName());
        response.put("id", member.getId());
        response.put("email", member.getEmail());
        response.put("password", member.getPassword());
        response.put("contactNumber", member.getContactNumber());
        response.put("userType", member.getUserType());

        return ResponseEntity.ok(response);
    }
}
