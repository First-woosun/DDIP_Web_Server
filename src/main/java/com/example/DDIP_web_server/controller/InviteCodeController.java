// InviteCodeController.java
package com.example.DDIP_web_server.controller;

import com.example.DDIP_web_server.service.InviteCodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invite")
public class InviteCodeController {

    private final InviteCodeService inviteCodeService;

    public InviteCodeController(InviteCodeService inviteCodeService) {
        this.inviteCodeService = inviteCodeService;
    }

    // 초대 코드 생성 엔드포인트
    @PostMapping("/create")
    public ResponseEntity<String> createInviteCode(@RequestBody String code) {
        inviteCodeService.saveInviteCode(code);
        return ResponseEntity.ok("Invite code saved successfully.");
    }

    // 초대 코드 확인 엔드포인트
    @GetMapping("/check")
    public ResponseEntity<Boolean> checkInviteCode(@RequestParam String code) {
        return ResponseEntity.ok(inviteCodeService.findInviteCode(code).isPresent());
    }
}

