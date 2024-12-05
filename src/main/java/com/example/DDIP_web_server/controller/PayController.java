package com.example.DDIP_web_server.controller;

import com.example.DDIP_web_server.service.CrewRoomPayService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/pay")
public class PayController {

    private final CrewRoomPayService CrewRoomPayService;

    public PayController(CrewRoomPayService CrewRoomPayService) {
        this.CrewRoomPayService = CrewRoomPayService;
    }

    @GetMapping("/crewroom/{crewroomid}/{member}")
    public Map<Integer, Map<String, Object>> getMemberMonthlyPay(
            @PathVariable Integer crewroomid,
            @PathVariable String member) {
        return CrewRoomPayService.calculateMemberMonthlyPay(crewroomid, member);
    }

    @GetMapping("/all/member/{crewroomid}")
    public Map<String, Object> getAllMembersMonthlyPay(
            @PathVariable Integer crewroomid) {
        return CrewRoomPayService.calculateAllMembersMonthlyPay(crewroomid);
    }
}
