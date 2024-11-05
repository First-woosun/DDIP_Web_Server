package com.example.DDIP_web_server.service;

import org.springframework.stereotype.Service;
import com.example.DDIP_web_server.entity.CrewRoomSchedule;
import com.example.DDIP_web_server.repository.CrewRoomScheduleRepository;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class CrewRoomScheduleService {

    private final CrewRoomScheduleRepository scheduleRepository;
    private static final double HOURLY_RATE = 9860.0; // 최저 시급

    public CrewRoomScheduleService(CrewRoomScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public double getHourlyRate() {
        return HOURLY_RATE;
    }

    public Map<Integer, Double> getWeeklyHoursByMonth(int month) {
        List<Object[]> results = scheduleRepository.findWeeklyHoursByMonth(month);
        Map<Integer, Double> weeklyHours = new HashMap<>();

        // 1. 데이터 로드 및 주차별 시간 계산
        for (Object[] result : results) {
            String yearWeek = result[0].toString();
            Double hours = ((BigDecimal) result[1]).doubleValue();

            int year = Integer.parseInt(yearWeek.substring(0, 4));
            int weekOfYear = Integer.parseInt(yearWeek.substring(4));

            LocalDate date = LocalDate.ofYearDay(year, 1)
                    .with(WeekFields.of(Locale.getDefault()).weekOfYear(), weekOfYear);
            int weekOfMonth = date.get(WeekFields.of(Locale.getDefault()).weekOfMonth());

            weeklyHours.put(weekOfMonth, hours);
        }

        // 2. 주차를 1부터 연속되도록 재정렬
        Map<Integer, Double> adjustedWeeklyHours = new HashMap<>();
        int adjustedWeek = 1;
        for (int originalWeek : weeklyHours.keySet()) {
            adjustedWeeklyHours.put(adjustedWeek, weeklyHours.get(originalWeek));
            adjustedWeek++;
        }

        // 3. 최대 5주차까지만 반환
        return adjustedWeeklyHours.entrySet()
                .stream()
                .filter(entry -> entry.getKey() <= 5)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue
                ));
    }




}
