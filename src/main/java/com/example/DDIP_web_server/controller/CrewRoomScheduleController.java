package com.example.DDIP_web_server.controller;


import com.example.DDIP_web_server.service.CrewRoomScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/salary")
public class CrewRoomScheduleController {

    private final CrewRoomScheduleService scheduleService;

    public CrewRoomScheduleController(CrewRoomScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/hourly-rate")
    public double getHourlyRate() {
        return scheduleService.getHourlyRate();
    }

    @GetMapping("/work-hours/{month}")
    public Map<Integer, Double> getWeeklyHoursByMonth(@PathVariable int month) {
        return scheduleService.getWeeklyHoursByMonth(month);
    }
}