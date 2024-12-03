package com.example.DDIP_web_server.service;

import com.example.DDIP_web_server.entity.CrewRoomMember;
import com.example.DDIP_web_server.repository.CrewRoomMemberRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CrewRoomMemberService {
    private final CrewRoomMemberRepository repository;

    public CrewRoomMemberService(CrewRoomMemberRepository repository) {
        this.repository = repository;
    }

    public List<CrewRoomMember> getMembersBycrewRoom(Integer crewRoom) {
        return repository.findByCrewRoom(crewRoom);
    }

    public boolean addMember(CrewRoomMember crewRoomMember) {
        try {
            this.repository.save(crewRoomMember);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
