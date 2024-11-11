package com.example.DDIP_web_server.controller;


import com.example.DDIP_web_server.service.CrewRoomPayService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/salary")
public class CrewRoomPayController {

    private final CrewRoomPayService crewRoomPayService;

    public CrewRoomPayController(CrewRoomPayService scheduleService) {
        this.crewRoomPayService = scheduleService;
    }

    @GetMapping("/hourly-rate")
    public double getHourlyRate() {
        return crewRoomPayService.getHourlyRate();
    }

    @GetMapping("/work-hours/{month}")
    public Map<Integer, Double> getWeeklyHoursByMonth(@PathVariable int month) {
        return crewRoomPayService.getWeeklyHoursByMonth(month);
    }
}