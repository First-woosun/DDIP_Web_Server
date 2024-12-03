package com.example.DDIP_web_server.service;

import com.example.DDIP_web_server.entity.CrewRoomMember;
import com.example.DDIP_web_server.repository.CrewRoomMemberRepository;
import org.springframework.stereotype.Service;

@Service
public class CrewRoomMemberService {
    private final CrewRoomMemberRepository crewRoomMemberRepository;

    public CrewRoomMemberService(CrewRoomMemberRepository crewRoomMemberRepository) {
        this.crewRoomMemberRepository = crewRoomMemberRepository;
    }

    public boolean addMember(CrewRoomMember crewRoomMember) {
        try {
            crewRoomMemberRepository.save(crewRoomMember);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
