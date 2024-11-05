package com.example.DDIP_web_server.controller;

import com.example.DDIP_web_server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.DDIP_web_server.entity.member;
import com.example.DDIP_web_server.service.loginAndSignup;

@RestController
@RequestMapping("/api")
public class memberController {

    @Autowired
    private loginAndSignup loginAndSignup;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users/signup")
    public member signup(@RequestBody member member) {
        return loginAndSignup.signup(member);
    }

    @PostMapping("/users/login")
    public member login(@RequestParam String userid, @RequestParam String userpwd) {
        member member = loginAndSignup.login(userid, userpwd);
        if (member != null) {
            return member;  // 로그인 성공
        } else {
            throw new RuntimeException("Invalid userid or userpwd");  // 로그인 실패
        }
    }

    //ID 중복 확인 API
    @GetMapping("/check-username")
    public ResponseEntity isUsernameTaken(@RequestParam("userid") String userid) {
        boolean isTaken = loginAndSignup.isUsernameTaken(userid);

        if (isTaken) {
            // 이미 아이디가 사용 중인 경우 409 응답 반환
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username is already taken");
        } else {
            return ResponseEntity.ok(isTaken);
        }
    }

//    @GetMapping("/check_username/{username}")
//    public ResponseEntity<Boolean> checkUsername(@PathVariable String userid) {
//        boolean isTaken = userService.isUsernameTaken(userid);
//        return ResponseEntity.ok(isTaken);
//    }
//
//    @GetMapping("/check-duplicate")
//    public ResponseEntity<Boolean> checkDuplicate(@RequestBody String userid) {
//        boolean exists = userService.checkUseridExists(userid);
//        return ResponseEntity.ok(exists);
//    }
}
