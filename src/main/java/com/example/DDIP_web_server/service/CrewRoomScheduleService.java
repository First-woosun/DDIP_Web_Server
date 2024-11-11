package com.example.DDIP_web_server.service;
import com.example.DDIP_web_server.entity.CrewRoomMember;
import com.example.DDIP_web_server.entity.CrewRoomSchedule;
import com.example.DDIP_web_server.repository.CrewRoomMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.DDIP_web_server.repository.CrewRoomScheduleRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class CrewRoomScheduleService {


    @Autowired
    private CrewRoomScheduleRepository crewScheduleRepository;

    @Autowired
    private CrewRoomMemberRepository crewRoomMemberRepository;

    public CrewRoomMember getCrewRoomMemberByMemberId(int memberId) {
        CrewRoomMember crewRoomMember = crewRoomMemberRepository.findByMemberId(String.valueOf(memberId));
        if (crewRoomMember != null) {
            return crewRoomMember;
        } else {
            throw new RuntimeException("CrewRoomMember not found for memberId: " + memberId);
        }
    }

    @Transactional
    public void saveSchedule(CrewRoomSchedule schedule) {
        crewScheduleRepository.save(schedule);
    }
}
