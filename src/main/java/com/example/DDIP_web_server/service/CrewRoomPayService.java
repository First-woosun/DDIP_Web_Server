package com.example.DDIP_web_server.service;

import org.springframework.stereotype.Service;
import com.example.DDIP_web_server.repository.CrewRoomScheduleRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CrewRoomPayService {

    // CrewRoomScheduleRepository를 주입하여, 데이터베이스와 상호작용하는 역할을 담당
    private final CrewRoomScheduleRepository scheduleRepository;
    // 최저 시급을 나타내는 상수, 이 값은 향후 계산에 사용될 수 있음
    private static final double HOURLY_RATE = 9860.0;

    // 생성자 주입을 통해 CrewRoomScheduleRepository의 인스턴스를 받아와 설정
    public CrewRoomPayService(CrewRoomScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    // 현재 설정된 시급을 반환하는 메서드
    public double getHourlyRate() {
        return HOURLY_RATE;
    }

    /**
     * 특정 월에 대한 주차별 근무 시간을 계산하여 반환하는 메서드
     *
     * @param month 계산할 대상 월 (1 ~ 12)
     * @return 주차별 근무 시간의 Map (키: 1~5 주차, 값: 각 주차별 총 근무 시간)
     */
    public Map<Integer, Double> getWeeklyHoursByMonth(int month) {
        // 데이터베이스에서 주어진 월의 주차별 근무 시간을 조회
        List<Object[]> results = scheduleRepository.findWeeklyHoursByMonth(month);
        Map<Integer, Double> weeklyHours = new HashMap<>();

        // 1. 데이터 로드 및 주차별 시간 계산
        // 데이터베이스에서 받아온 각 결과를 처리하여 주차별 근무 시간을 계산
        for (Object[] result : results) {
            // yearWeek는 연도와 주차 정보가 포함된 문자열 ("yyyyWW" 형식)
            String yearWeek = result[0].toString();
            // hours는 주차별 근무 시간, BigDecimal 형식이므로 double로 변환
            Double hours = ((BigDecimal) result[1]).doubleValue();

            // yearWeek 문자열을 파싱하여 연도(year)와 연도 내 주차(weekOfYear)를 추출
            int year = Integer.parseInt(yearWeek.substring(0, 4)); // 앞 4자리는 연도
            int weekOfYear = Integer.parseInt(yearWeek.substring(4)); // 나머지는 주차

            // 연도와 주차 정보를 통해 해당 주차의 날짜를 가져옴
            LocalDate date = LocalDate.ofYearDay(year, 1)
                    .with(WeekFields.of(Locale.getDefault()).weekOfYear(), weekOfYear);
            // 그 날짜를 이용해 해당 월의 몇 번째 주인지 계산
            int weekOfMonth = date.get(WeekFields.of(Locale.getDefault()).weekOfMonth());

            // 주차별 시간 맵에 주차(weekOfMonth)를 키로, 시간을 값으로 저장
            weeklyHours.put(weekOfMonth, hours);
        }

        // 2. 주차를 1부터 연속되도록 재정렬
        // weeklyHours에 있는 주차 정보가 순서대로 되어있지 않을 수 있으므로, 1부터 순서대로 정리
        Map<Integer, Double> adjustedWeeklyHours = new HashMap<>();
        int adjustedWeek = 1; // 시작 주차를 1로 설정
        // weeklyHours의 각 주차별 근무 시간을 순서대로 새로운 맵에 추가
        for (int originalWeek : weeklyHours.keySet()) {
            adjustedWeeklyHours.put(adjustedWeek, weeklyHours.get(originalWeek));
            adjustedWeek++;
        }

        // 3. 최대 5주차까지만 반환
        // 최대 5주차까지만 결과에 포함하도록 필터링, 5주를 초과하는 항목은 포함되지 않음
        return adjustedWeeklyHours.entrySet()
                .stream()
                .filter(entry -> entry.getKey() <= 5) // 5주차 이하의 데이터만 필터링
                .collect(Collectors.toMap(
                        Map.Entry::getKey, // 키는 주차
                        Map.Entry::getValue // 값은 근무 시간
                ));
    }
}
