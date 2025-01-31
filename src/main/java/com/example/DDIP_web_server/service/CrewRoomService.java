package com.example.DDIP_web_server.service;



import com.example.DDIP_web_server.entity.CrewRoom;
import com.example.DDIP_web_server.repository.CrewRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service  // 서비스 계층을 나타내는 어노테이션
public class CrewRoomService {

    @Autowired
    private CrewRoomRepository crewRoomRepository;

    // 새로운 크루룸을 생성하여 저장
    public CrewRoom createCrewRoom(CrewRoom crewRoom) {
        // 저장된 CrewRoom 반환
        CrewRoom savedCrewRoom = crewRoomRepository.save(crewRoom);

        // 로그로 확인
        System.out.println("Saved CrewRoom(크루룸서비스): " + savedCrewRoom);

        return savedCrewRoom;
    }

    // 모든 크루룸을 조회
    public List<CrewRoom> getAllCrewRooms() {
        return crewRoomRepository.findAll();
    }

    // 특정 ID의 크루룸을 조회
    public CrewRoom getCrewRoomById(Long id) {
        return crewRoomRepository.findById(id).orElse(null);
    }

    // CrewRoomMember에서 memberId로 크루룸ID 목록 가져오기
    public List<Map<String, String>> getCrewRoomsByMemberId(String memberId) {
        //중복 제거
        List<Integer> crewRoomIds = crewRoomRepository.findDistinctCrewRoomIdsByMemberId(memberId);

        // 각 크루룸ID에 해당하는 크루룸명 가져오기
        List<Map<String, String>> crewRooms = new ArrayList<>();
        for (Integer crewRoomId : crewRoomIds) {
            String crewRoomName = crewRoomRepository.findCrewRoomNameById(crewRoomId);
            Map<String, String> crewRoomInfo = new HashMap<>();
            crewRoomInfo.put("crewRoomId", String.valueOf(crewRoomId));
            crewRoomInfo.put("crewRoomName", crewRoomName);
            crewRooms.add(crewRoomInfo);
        }
        return crewRooms;
    }

    public CrewRoom findByInvitationCode(String inviteCode) {
        return crewRoomRepository.findByInvitationCode(inviteCode);
    }

    public List<Map<String, String>> getCrewRoomsWithInvitationByMemberId(String memberId) {
        List<Integer> crewRoomIds = crewRoomRepository.findDistinctCrewRoomIdsByMemberId(memberId);

        List<Map<String, String>> crewRooms = new ArrayList<>();
        for (Integer crewRoomId : crewRoomIds) {
            CrewRoom crewRoom = crewRoomRepository.findById(Long.valueOf(crewRoomId)).orElse(null);
            if (crewRoom != null) {
                Map<String, String> crewRoomInfo = new HashMap<>();
                crewRoomInfo.put("crewRoomId", String.valueOf(crewRoom.getCrewRoomId()));
                crewRoomInfo.put("crewRoomName", crewRoom.getCrewRoomName());
                crewRoomInfo.put("crewRoomInvitation", crewRoom.getCrewRoomInvitation()); // 초대코드 포함
                crewRooms.add(crewRoomInfo);
            }
        }
        return crewRooms;
    }
    public String getInviteCodeByCrewRoomId(String crewRoomId) {
        // 크루룸 조회
        CrewRoom crewRoom = crewRoomRepository.findById(Long.valueOf(crewRoomId)).orElse(null);
        if (crewRoom != null) {
            // 초대 코드 반환
            return crewRoom.getCrewRoomInvitation();
        }
        return null; // 크루룸이 없는 경우
    }
}
