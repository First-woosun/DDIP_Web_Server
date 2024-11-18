package com.example.DDIP_web_server.service;
import com.example.DDIP_web_server.entity.CrewRoomMember;
import com.example.DDIP_web_server.entity.CrewRoomSchedule;
import com.example.DDIP_web_server.repository.CrewRoomMemberRepository;
import com.example.DDIP_web_server.repository.MemberRepository;
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
    private MemberRepository memberRepository;
    @Autowired
    private CrewRoomMemberRepository crewRoomMemberRepository;

/*    public int getPayByMemberId(String memberId) {
        CrewRoomMember crewRoomMember = crewRoomMemberRepository.findByMemberId(memberId);
        if (crewRoomMember != null) {
            return crewRoomMember.getPay();
        } else {
            throw new RuntimeException("Pay information not found for memberId: " + memberId);
        }
    }*/

    @Transactional
    public void saveSchedule(CrewRoomSchedule schedule) {
        crewScheduleRepository.save(schedule);
    }
}
