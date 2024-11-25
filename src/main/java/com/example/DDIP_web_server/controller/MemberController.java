package com.example.DDIP_web_server.controller;

import com.example.DDIP_web_server.entity.Member;
import com.example.DDIP_web_server.repository.MemberRepository;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.DDIP_web_server.service.MemberService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/Member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @PostMapping("/signup")
    public Member signup(@RequestBody Member user) {
        return memberService.signup(user);
    }

    @PostMapping("/login")
    public Member login(@RequestParam String id, @RequestParam String password) {
        Member user = memberService.login(id, password);
        if (user != null) {
            return user;  // 로그인 성공
        } else {
            throw new RuntimeException("Invalid id or password");  // 로그인 실패
        }
    }

    //ID 중복 확인 API
    @GetMapping("/check-username")
    public ResponseEntity isUsernameTaken(@RequestParam("id") String id) {
        boolean isTaken = memberService.isUsernameTaken(id);

        if (isTaken) {
            // 이미 아이디가 사용 중인 경우 409 응답 반환
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username is already taken");
        } else {
            return ResponseEntity.ok(isTaken);
        }
    }

    @GetMapping("/check-admin")
    public ResponseEntity<Map<String, String>> isAdminAccount(@RequestParam("id") String id) {
        String isadmin = memberService.isAdminAccount(id);
        Map<String, String> response = new HashMap<String, String>();
        if (isadmin.equals("Owner")) {
            response.put("result", "Owner");
        }else {
            response.put("result", "Staff");
        }
        return ResponseEntity.ok(response);
    }

    // 회원 정보 수정
    @PutMapping("/changeData/{id}")
    public ResponseEntity<Map<String, String>> updateMember(
            @PathVariable String id,
            @RequestBody Member updatedMember) {
        boolean success = memberService.updateMember(id, updatedMember);
        Map<String, String> response = new HashMap<>();
        if (success) {
            response.put("message", "회원 정보 수정 성공");
            return ResponseEntity.ok(response);
        }
        response.put("message", "회원 정보 수정 실패");
        return ResponseEntity.ok(response);
    }
}