package com.example.DDIP_web_server.controller;

import com.example.DDIP_web_server.entity.CrewRoomMember;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<List<Map<String, String>>> getCrewRoomMembers(@RequestParam("crewRoom") String crewRoom) {
        Integer path = Integer.parseInt(crewRoom);
        List<CrewRoomMember> result = crewRoomMemberService.getMembersBycrewRoom(path);
        List<Map<String, String>> data = new ArrayList<Map<String, String>>();
        for(int i = 0; i < result.size(); i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("crewRoom", String.valueOf(result.get(i).getcrewRoom()));
            map.put("crewRoomMemberId", String.valueOf(result.get(i).getcrewRoomMemberId()));
            map.put("member", result.get(i).getMember());
            map.put("color", result.get(i).getcolor());
            map.put("startDate", result.get(i).getstartDate().toString());
            map.put("memberType", result.get(i).getmemberType());
            map.put("contactNumber", result.get(i).getcontactNumber());
            data.add(map);

//            result.get(i).getall();
        }
        return ResponseEntity.ok(data);
    }
}
