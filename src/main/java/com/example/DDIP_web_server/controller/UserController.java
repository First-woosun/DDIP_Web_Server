package com.example.DDIP_web_server.controller;

import com.example.DDIP_web_server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.DDIP_web_server.entity.User;
import com.example.DDIP_web_server.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users/signup")
    public User signup(@RequestBody User user) {
        return userService.signup(user);
    }

    @PostMapping("/users/login")
    public User login(@RequestParam String userid, @RequestParam String userpwd) {
        User user = userService.login(userid, userpwd);
        if (user != null) {
            return user;  // 로그인 성공
        } else {
            throw new RuntimeException("Invalid userid or userpwd");  // 로그인 실패
        }
    }

    //ID 중복 확인 API
    @GetMapping("/check-username")
    public ResponseEntity isUsernameTaken(@RequestParam("userid") String userid) {
        boolean isTaken = userService.isUsernameTaken(userid);

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
