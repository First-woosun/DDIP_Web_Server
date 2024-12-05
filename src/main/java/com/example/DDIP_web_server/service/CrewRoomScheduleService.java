package com.example.DDIP_web_server.service;
import com.example.DDIP_web_server.entity.CrewRoomMember;
import com.example.DDIP_web_server.entity.CrewRoomSchedule;
import com.example.DDIP_web_server.repository.CrewRoomMemberRepository;
import com.example.DDIP_web_server.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.DDIP_web_server.repository.CrewRoomScheduleRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
public class CrewRoomScheduleService {

    @Autowired
    private CrewRoomScheduleRepository crewRoomScheduleRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CrewRoomMemberRepository crewRoomMemberRepository;

    @Transactional
    public void saveSchedule(CrewRoomSchedule schedule) {
        crewRoomScheduleRepository.save(schedule);
    }

    //스케줄 가져오기
    public List<Map<String, Object>> getActiveSchedulesForCurrentMonth(Integer crewRoomId, String memberId) {
        List<CrewRoomSchedule> schedules = crewRoomScheduleRepository.findSchedulesForCurrentMonth(crewRoomId, memberId);

        // 데이터 포맷 변환
        List<Map<String, Object>> result = new ArrayList<>();
        for (CrewRoomSchedule schedule : schedules) {
            Map<String, Object> scheduleMap = new HashMap<>();
            scheduleMap.put("scheduleId", schedule.getScheduleId());
            scheduleMap.put("date", schedule.getDate());
            scheduleMap.put("startTime", schedule.getStartTime());
            scheduleMap.put("endTime", schedule.getEndTime());
            result.add(scheduleMap);
        }

        return result;
    }

    //스케줄 수정
    public boolean updateScheduleStatusToExchanged(Integer scheduleId) {
        Optional<CrewRoomSchedule> optionalSchedule = crewRoomScheduleRepository.findById(scheduleId);

        if (optionalSchedule.isPresent()) {
            CrewRoomSchedule schedule = optionalSchedule.get();

            if ("ACTIVE".equals(schedule.getStatus())) {
                schedule.setStatus("EXCHANGED");
                crewRoomScheduleRepository.save(schedule);
                return true;
            }
        }
        return false;
    }

    public List<Map<String, Object>> getExchangeableSchedules(Integer crewRoomId) {
        List<CrewRoomSchedule> schedules = crewRoomScheduleRepository.findExchangeableSchedulesByCrewRoomId(crewRoomId);

        // 데이터를 가공하여 member ID 대신 이름을 포함한 응답 생성
        List<Map<String, Object>> result = new ArrayList<>();
        for (CrewRoomSchedule schedule : schedules) {
            Map<String, Object> scheduleMap = new HashMap<>();
            scheduleMap.put("scheduleId", schedule.getScheduleId());
            scheduleMap.put("date", schedule.getDate());
            scheduleMap.put("startTime", schedule.getStartTime());
            scheduleMap.put("endTime", schedule.getEndTime());
            scheduleMap.put("totalHours", schedule.getTotalHours());
            scheduleMap.put("pay", schedule.getPay());

            // member ID를 이름으로 변환
            String memberName = memberRepository.findNameById(schedule.getMember());
            scheduleMap.put("member", memberName);

            result.add(scheduleMap);
        }
        return result;
    }

    public boolean exchangeSchedule(Integer scheduleId, String memberId) {
        Optional<CrewRoomSchedule> optionalSchedule = crewRoomScheduleRepository.findById(scheduleId);

        if (optionalSchedule.isPresent()) {
            CrewRoomSchedule schedule = optionalSchedule.get();
            schedule.setMember(memberId);  // 멤버 변경
            schedule.setStatus("ACTIVE"); // 상태 변경
            crewRoomScheduleRepository.save(schedule);
            return true;
        }
        return false;
    }

    public List<CrewRoomSchedule> getMySchedules(String memberId) {
        return crewRoomScheduleRepository.getMySchedules(memberId);
    }
}
