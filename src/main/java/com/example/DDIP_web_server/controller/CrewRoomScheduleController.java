package com.example.DDIP_web_server.controller;


import com.example.DDIP_web_server.entity.CrewRoomMember;
import com.example.DDIP_web_server.entity.CrewRoomSchedule;
import com.example.DDIP_web_server.service.CrewRoomScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/schedules")
public class CrewRoomScheduleController {
    @Autowired
    private CrewRoomScheduleService crewRoomScheduleService;

    //스케줄 저장
    @PostMapping("/add")
    public ResponseEntity<Void> saveSchedule(@RequestBody CrewRoomSchedule schedule) {
        crewRoomScheduleService.saveSchedule(schedule);
        return ResponseEntity.ok().build();
    }
    //스케줄 조회
    @GetMapping("/{crewRoomId}/{memberId}")
    public ResponseEntity<List<Map<String, Object>>> getSchedules(
            @PathVariable String crewRoomId, // String으로 받기
            @PathVariable String memberId) {
        try {
            // roomId를 String에서 int로 변환
            int crewRoomIdInt = Integer.parseInt(crewRoomId);

            // 서비스 호출
            List<Map<String, Object>> schedules = crewRoomScheduleService.getActiveSchedulesForCurrentMonth(crewRoomIdInt, memberId);
            return ResponseEntity.ok(schedules);
        } catch (NumberFormatException e) {
            // roomId가 잘못된 형식일 경우 400 응답 반환
            return ResponseEntity.badRequest().body(null);
        }
    }
    // 스케줄 상태 업데이트 API
    @PatchMapping("/exchange/{scheduleId}")
    public ResponseEntity<String> updateScheduleStatus(@PathVariable Integer scheduleId) {
        boolean isUpdated = crewRoomScheduleService.updateScheduleStatusToExchanged(scheduleId);

        if (isUpdated) {
            return ResponseEntity.ok("Schedule status updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update schedule status.");
        }
    }
    @GetMapping("/exchangeable/{crewRoomId}")
    public ResponseEntity<List<Map<String, Object>>> getExchangeableSchedules(@PathVariable Integer crewRoomId) {
        List<Map<String, Object>> exchangeableSchedules = crewRoomScheduleService.getExchangeableSchedules(crewRoomId);
        return ResponseEntity.ok(exchangeableSchedules);
    }

    @PatchMapping("/DDIP")
    public ResponseEntity<String> exchangeSchedule(@RequestBody Map<String, String> requestData) {
        String memberId = requestData.get("memberId");
        Integer scheduleId = Integer.parseInt(requestData.get("scheduleId"));

        boolean isUpdated = crewRoomScheduleService.exchangeSchedule(scheduleId, memberId);

        if (isUpdated) {
            return ResponseEntity.ok("Schedule updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update schedule.");
        }
    }

    @GetMapping("getMySchedule/{member}")
    public ResponseEntity<List<Map<String, String>>> getMySchedule(@PathVariable String member) {
        List<CrewRoomSchedule> result = crewRoomScheduleService.getMySchedules(member);
        List<Map<String, String>> schedules = new ArrayList<>();
        for(int i = 0; i < result.size(); i++) {
            Map<String, String> schedule = new HashMap<>();
            schedule.put("crewRoom", String.valueOf(result.get(i).getCrewRoom()));
            schedule.put("member", result.get(i).getMember());
            schedule.put("date", result.get(i).getDate().toString());
            schedule.put("startTime", result.get(i).getStartTime().toString());
            schedule.put("endTime", result.get(i).getEndTime().toString());
            schedule.put("pay", result.get(i).getPay().toString());
            schedule.put("totalHours", result.get(i).getTotalHours().toString());
            schedules.add(schedule);
        }

        return ResponseEntity.ok(schedules);
    }

    @GetMapping("getAllSchedules/{crewRoom}")
    public ResponseEntity<List<Map<String, String>>> getAllSchedules(@PathVariable String crewRoom) {
        Integer path = Integer.parseInt(crewRoom);
        List<CrewRoomSchedule> result = crewRoomScheduleService.getAllSchedules(path);
        List<Map<String, String>> schedules = new ArrayList<>();
        for(int i = 0; i < result.size(); i++) {
            Map<String, String> schedule = new HashMap<>();
            schedule.put("crewRoom", String.valueOf(result.get(i).getCrewRoom()));
            schedule.put("member", result.get(i).getMember());
            schedule.put("date", result.get(i).getDate().toString());
            schedule.put("startTime", result.get(i).getStartTime().toString());
            schedule.put("endTime", result.get(i).getEndTime().toString());
            schedule.put("pay", result.get(i).getPay().toString());
            schedule.put("totalHours", result.get(i).getTotalHours().toString());
            schedules.add(schedule);
        }

        return ResponseEntity.ok(schedules);
    }
}

