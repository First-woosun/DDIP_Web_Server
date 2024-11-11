package com.example.DDIP_web_server.controller;


import com.example.DDIP_web_server.entity.CrewRoomMember;
import com.example.DDIP_web_server.entity.CrewRoomSchedule;
import com.example.DDIP_web_server.service.CrewRoomScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/schedules")
public class CrewRoomScheduleController {
    @Autowired
    private CrewRoomScheduleService crewRoomscheduleService;

    @GetMapping("/wages/{memberId}")
    public ResponseEntity<CrewRoomMember> getCrewRoomMemberByMemberId(@PathVariable int memberId) {
        CrewRoomMember crewRoomMember = crewRoomscheduleService.getCrewRoomMemberByMemberId(memberId);
        return ResponseEntity.ok(crewRoomMember);
    }

    @PostMapping("/addschedules")
    public ResponseEntity<Void> saveSchedule(@RequestBody CrewRoomSchedule schedule) {
        crewRoomscheduleService.saveSchedule(schedule);
        return ResponseEntity.ok().build();
    }
}
