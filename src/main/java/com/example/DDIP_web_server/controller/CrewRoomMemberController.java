package com.example.DDIP_web_server.controller;

import com.example.DDIP_web_server.entity.CrewRoomMember;
import com.example.DDIP_web_server.service.CrewRoomMemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;


@RestController
@RequestMapping("/api/CrewRoomMember")
public class CrewRoomMemberController {

    private final CrewRoomMemberService crewRoomMemberService;

    public CrewRoomMemberController(CrewRoomMemberService crewRoomMemberService) {
        this.crewRoomMemberService = crewRoomMemberService;
    }

    @PostMapping("/addMemberToCrew")
    public ResponseEntity<String> addMemberToCrew(@RequestBody CrewRoomMember crewRoomMember) {
        try {
            // 요청 데이터 검증 및 로깅
            System.out.println("Received member data:");
            System.out.println("Crew Room ID: " + crewRoomMember.getCrewRoom());
            System.out.println("Member: " + crewRoomMember.getMember());
            System.out.println("Member Type: " + crewRoomMember.getMemberType());
            System.out.println("Color: " + crewRoomMember.getColor());
            System.out.println("Contact Number: " + crewRoomMember.getContactNumber());

            if (crewRoomMember.getCrewRoom() == null || crewRoomMember.getMember() == null) {
                return ResponseEntity.badRequest().body("Crew Room ID or Member is missing.");
            }

            boolean success = crewRoomMemberService.addMember(crewRoomMember);

            if (success) {
                return ResponseEntity.ok("Member added successfully.");
            } else {
                return ResponseEntity.status(500).body("Failed to add member: Unknown error.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to add member: " + e.getMessage());
        }
    }
}
