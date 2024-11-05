package com.example.DDIP_web_server.service;



import com.example.DDIP_web_server.entity.CrewRoom;
import com.example.DDIP_web_server.repository.CrewRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  // 서비스 계층을 나타내는 어노테이션
public class CrewRoomService {

    @Autowired
    private CrewRoomRepository crewRoomRepository;

    // 새로운 크루룸을 생성하여 저장
    public CrewRoom createCrewRoom(CrewRoom crewRoom) {
        return crewRoomRepository.save(crewRoom);
    }

    // 모든 크루룸을 조회
    public List<CrewRoom> getAllCrewRooms() {
        return crewRoomRepository.findAll();
    }

    // 특정 ID의 크루룸을 조회
    public CrewRoom getCrewRoomById(Long id) {
        return crewRoomRepository.findById(id).orElse(null);
    }
}
