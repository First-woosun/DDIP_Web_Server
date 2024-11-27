package com.example.DDIP_web_server.controller;

import com.example.DDIP_web_server.entity.CrewRoomMember;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.DDIP_web_server.service.CrewRoomMemberService;
import java.util.List;

@RestController
@RequestMapping("/api/CrewRoomMember")
public class CrewRoomMemberController {
    private final CrewRoomMemberService crewRoomMemberService;

    public CrewRoomMemberController(CrewRoomMemberService crewRoomMemberService) {
        this.crewRoomMemberService = crewRoomMemberService;
    }

    @GetMapping("/getCrewRoomMembers")
    public List<CrewRoomMember> getCrewRoomMembers(@RequestParam Integer crewRoom) {
        return crewRoomMemberService.getMembersBycrewRoom(crewRoom);
    }
}
