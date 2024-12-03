package com.example.DDIP_web_server.controller;

import com.example.DDIP_web_server.entity.CrewRoomMember;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.DDIP_web_server.service.CrewRoomMemberService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/CrewRoomMember")
public class CrewRoomMemberController {
    private final CrewRoomMemberService crewRoomMemberService;

    public CrewRoomMemberController(CrewRoomMemberService crewRoomMemberService) {
        this.crewRoomMemberService = crewRoomMemberService;
    }

    @GetMapping("/getCrewRoomMembers/{crewRoom}")
    public ResponseEntity<List<Map<String, String>>> getCrewRoomMembers(@PathVariable("crewRoom") Integer crewRoom) {
        List<CrewRoomMember> result = crewRoomMemberService.getMembersBycrewRoom(crewRoom);
        List<Map<String, String>> data = new ArrayList<>();

        for (CrewRoomMember member : result) {
            Map<String, String> map = new HashMap<>();
            map.put("crewRoom", String.valueOf(member.getCrewRoom()));
            map.put("crewRoomMemberId", String.valueOf(member.getCrewRoomMemberId()));
            map.put("member", member.getMember());
            map.put("color", member.getColor());
            map.put("startDate", member.getStartDate() != null ? member.getStartDate().toString() : "N/A");
            map.put("memberType", member.getMemberType());
            map.put("contactNumber", member.getContactNumber());
            data.add(map);
        }

        return ResponseEntity.ok(data);
    }

    @PostMapping("/addMemberToCrew")
    public ResponseEntity<String> addMemberToCrew(@RequestBody CrewRoomMember crewRoomMember) {
        try {
            // 요청 데이터 검증
            if (crewRoomMember.getCrewRoom() == null || crewRoomMember.getMember() == null) {
                String errorMessage = "Crew Room ID or Member is missing.";
                System.out.println(errorMessage);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
            }

            // 요청 데이터 로깅
            System.out.println("Received member data:");
            System.out.println("Crew Room ID: " + crewRoomMember.getCrewRoom());
            System.out.println("Member: " + crewRoomMember.getMember());
            System.out.println("Member Type: " + crewRoomMember.getMemberType());
            System.out.println("Color: " + crewRoomMember.getColor());
            System.out.println("Contact Number: " + crewRoomMember.getContactNumber());

            boolean success = crewRoomMemberService.addMember(crewRoomMember);

            if (success) {
                return ResponseEntity.ok("Member added successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add member: Unknown error.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add member: " + e.getMessage());
        }
    }

}
