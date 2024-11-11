package com.example.DDIP_web_server.controller;

import com.example.DDIP_web_server.entity.Member;
import com.example.DDIP_web_server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.DDIP_web_server.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users/signup")
    public Member signup(@RequestBody Member user) {
        return userService.signup(user);
    }

    @PostMapping("/users/login")
    public Member login(@RequestParam String id, @RequestParam String password) {
        Member user = userService.login(id, password);
        if (user != null) {
            return user;  // 로그인 성공
        } else {
            throw new RuntimeException("Invalid id or password");  // 로그인 실패
        }
    }

    //ID 중복 확인 API
    @GetMapping("/check-username")
    public ResponseEntity isUsernameTaken(@RequestParam("id") String id) {
        boolean isTaken = userService.isUsernameTaken(id);

        if (isTaken) {
            // 이미 아이디가 사용 중인 경우 409 응답 반환
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username is already taken");
        } else {
            return ResponseEntity.ok(isTaken);
        }
    }

    @GetMapping("/check-admin")
    public boolean isAdminAccount(@RequestParam("id") String id) {
        boolean isadmin = userService.isAdminAccount(id);
        if (isadmin) {
            return true;
        }else{
            return false;
        }
    }
}
