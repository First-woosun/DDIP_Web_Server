package com.example.DDIP_web_server.service;

import com.example.DDIP_web_server.entity.CrewRoomSchedule;
import com.example.DDIP_web_server.repository.CrewRoomScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CrewRoomPayService {

    private static final double MINIMUM_WAGE = 9860; // 최저시급

    private final CrewRoomScheduleRepository crewRoomScheduleRepository;

    public CrewRoomPayService(CrewRoomScheduleRepository crewRoomScheduleRepository) {
        this.crewRoomScheduleRepository = crewRoomScheduleRepository;
    }

    public Map<Integer, Map<String, Object>> calculateMemberMonthlyPay(Integer crewroomid, String member) {
        List<CrewRoomSchedule> schedules = crewRoomScheduleRepository.findByCrewRoomAndMemberAndStatus(
                crewroomid, member, "ACTIVE");

        LocalDate now = LocalDate.now();
        schedules = schedules.stream()
                .filter(schedule -> isCurrentMonth(schedule.getDate(), now))
                .collect(Collectors.toList());

        return calculateWeeklyPay(schedules); // 직접 중첩된 Map 반환
    }

    public Map<String, Object> calculateAllMembersMonthlyPay(Integer crewroomid) {
        List<CrewRoomSchedule> schedules = crewRoomScheduleRepository.findByCrewRoomAndStatus(
                crewroomid, "ACTIVE");

        LocalDate now = LocalDate.now();
        schedules = schedules.stream()
                .filter(schedule -> isCurrentMonth(schedule.getDate(), now))
                .collect(Collectors.toList());

        Map<String, Double> monthlyPay = schedules.stream()
                .collect(Collectors.groupingBy(CrewRoomSchedule::getMember,
                        Collectors.summingDouble(schedule -> calculateDailyPay(schedule))));

        Map<String, Object> result = new HashMap<>();
        monthlyPay.forEach(result::put); // Map<String, Double>을 Map<String, Object>로 변환
        return result;
    }

    private boolean isCurrentMonth(Date date, LocalDate now) {
        LocalDate scheduleDate = LocalDate.of(
                date.getYear() + 1900, date.getMonth() + 1, date.getDate());
        return scheduleDate.getMonthValue() == now.getMonthValue() &&
                scheduleDate.getYear() == now.getYear();
    }

    private Map<Integer, Map<String, Object>> calculateWeeklyPay(List<CrewRoomSchedule> schedules) {
        Map<Integer, Map<String, Object>> weeklyPay = new HashMap<>();

        schedules.stream().collect(Collectors.groupingBy(schedule ->
                        LocalDate.of(schedule.getDate().getYear() + 1900, schedule.getDate().getMonth() + 1, schedule.getDate().getDate())
                                .get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear())))
                .forEach((week, weekSchedules) -> {
                    double totalHours = weekSchedules.stream().mapToDouble(CrewRoomSchedule::getTotalHours).sum();
                    double totalPay = weekSchedules.stream().mapToDouble(this::calculateDailyPay).sum();

                    if (totalHours > 40) {
                        totalPay += ((totalHours - 40) * 8 * MINIMUM_WAGE / 40);
                    }

                    weeklyPay.put(week, Map.of("pay", totalPay, "hours", totalHours));
                });

        return weeklyPay;
    }

    private double calculateDailyPay(CrewRoomSchedule schedule) {
        return schedule.getTotalHours() * schedule.getPay();
    }
}
