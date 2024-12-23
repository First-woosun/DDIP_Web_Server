

package com.example.DDIP_web_server.controller;

import com.example.DDIP_web_server.entity.CrewRoom;
import com.example.DDIP_web_server.service.CrewRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

@RestController  // RESTful 컨트롤러로 설정
@RequestMapping("/api/crewroom")  // 기본 URL 설정
public class CrewRoomController {

    @Autowired
    private CrewRoomService crewRoomService;


    // 크루룸 생성 요청 처리 메소드
    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createCrewRoom(@RequestBody CrewRoom crewRoom) {
        // 서비스 클래스를 통해 크루룸 생성
        CrewRoom newCrewRoom = crewRoomService.createCrewRoom(crewRoom);

        // 디버깅 로그 추가
        System.out.println("Returning CrewRoom(크루룸컨트롤러): " + newCrewRoom);

        // 응답을 위한 Map 객체 생성
        Map<String, String> response = new HashMap<>();
        //response.put("id", String.valueOf(newCrewRoom.getId()));  // 생성된 크루룸의 ID 추가
        response.put("id", String.valueOf(newCrewRoom.getCrewRoomId()));  // 생성된 크루룸의 ID 추가(entity 형식에 맞게 수정.)
        response.put("message", "크루룸이 성공적으로 생성되었습니다.");

        // 생성된 크루룸 ID와 메시지를 응답으로 반환
        return ResponseEntity.ok(response);
    }

    // 모든 크루룸을 조회하는 GET 요청
    @GetMapping("/list")
    public ResponseEntity<List<CrewRoom>> getAllCrewRooms() {
        List<CrewRoom> crewRooms = crewRoomService.getAllCrewRooms();  // 모든 크루룸 목록 조회
        return ResponseEntity.ok(crewRooms);  // 목록 반환
    }

    // 특정 ID의 크루룸을 조회하는 GET 요청
    @GetMapping("/{id}")
    public ResponseEntity<CrewRoom> getCrewRoomById(@PathVariable Long id) {
        CrewRoom crewRoom = crewRoomService.getCrewRoomById(id);  // ID로 크루룸 조회
        if (crewRoom != null) {
            return ResponseEntity.ok(crewRoom);  // 조회된 크루룸 반환
        } else {
            return ResponseEntity.notFound().build();  // 없을 경우 404 Not Found 응답
        }
    }

    // 멤버ID로 소속된 크루룸 조회
    @GetMapping("/list/{memberId}")
    public ResponseEntity<List<Map<String, String>>> getCrewRoomsByMemberId(@PathVariable String memberId) {
        List<Map<String, String>> crewRooms = crewRoomService.getCrewRoomsByMemberId(memberId);
        return ResponseEntity.ok(crewRooms);
    }

    // 초대코드 검사
    @PostMapping("/checkInviteCode")
    public ResponseEntity<Map<String, Object>> checkInviteCode(@RequestBody Map<String, String> request) {
        String inviteCode = request.get("inviteCode");
        CrewRoom crewRoom = crewRoomService.findByInvitationCode(inviteCode);

        Map<String, Object> response = new HashMap<>();
        if (crewRoom != null) {
            response.put("isValid", true);
            response.put("crewRoomId", crewRoom.getCrewRoomId());
        } else {
            response.put("isValid", false);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("/listWithInvitation/{memberId}")
    public ResponseEntity<List<Map<String, String>>> getCrewRoomsWithInvitation(@PathVariable String memberId) {
        List<Map<String, String>> crewRooms = crewRoomService.getCrewRoomsWithInvitationByMemberId(memberId);
        return ResponseEntity.ok(crewRooms);
    }

    @GetMapping("/getInviteCode/{crewRoomId}")
    public ResponseEntity<Map<String, String>> getInviteCode(@PathVariable String crewRoomId) {
        String inviteCode = crewRoomService.getInviteCodeByCrewRoomId(crewRoomId);
        if (inviteCode != null) {
            Map<String, String> response = new HashMap<>();
            response.put("inviteCode", inviteCode);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


}
