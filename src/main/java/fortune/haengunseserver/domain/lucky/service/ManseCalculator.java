package fortune.haengunseserver.domain.lucky.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import fortune.haengunseserver.domain.lucky.enums.Jiji;
import fortune.haengunseserver.domain.lucky.enums.SolarTerm;
import fortune.haengunseserver.domain.lucky.enums.Tenkan;
import org.springframework.stereotype.Service;

@Service
public class ManseCalculator {

    // 기준일: 1900년 1월 1일 (己亥년, 丙子월, 甲戌일)
    private static final LocalDate BASE_DATE = LocalDate.of(1900, 1, 1);

    // 사주 계산
    public String calculateManse(String birthDate, boolean isSolar, String birthTime) {
        LocalDate date = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalTime time = LocalTime.parse(birthTime, DateTimeFormatter.ofPattern("HH:mm"));

        return String.format(
                "연주: %s\n월주: %s\n일주: %s\n시주: %s",
                getYearGanZhi(date),
                getMonthGanZhi(date),
                getDayGanZhi(date, time),
                getHourGanZhi(date, time)
        );
    }

    // 연주 계산
    private String getYearGanZhi(LocalDate date) {
        Tenkan yearGan = Tenkan.fromIndex((date.getYear() - 4) % 10);
        Jiji yearZhi = Jiji.fromIndex((date.getYear() - 4) % 12);
        return formatGanZhi(yearGan, yearZhi);
    }

    // 월주 계산
    private String getMonthGanZhi(LocalDate date) {
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        // 절기 기준으로 월주의 지지 결정
        String zhiHanja = SolarTerm.getZhiForDate(month, day);  // 예: "亥"
        Jiji monthZhi = Jiji.fromHanja(zhiHanja);  // 오류 방지: 한자로 안전한 조회 방식 적용

        // 월주의 천간 결정 (연주의 천간에 따라 다름)
        int yearGanIndex = (year - 4) % 10; // 연주의 천간 인덱스
        int monthGanIndex = (yearGanIndex * 2 + monthZhi.ordinal()) % 10; // 월주 천간 계산

        return formatGanZhi(Tenkan.fromIndex(monthGanIndex), monthZhi);
    }

    // 일주 계산
    public String getDayGanZhi(LocalDate date, LocalTime time) {
        // 대한민국 표준시 적용 (23:30 이후에 태어나면 다음 날로 변경)
        if (time.getHour() > 23 || (time.getHour() == 23 && time.getMinute() >= 30)) {
            date = date.plusDays(1);
        }

        // 기준일(1900-01-01)부터 경과된 총 일수 계산
        long daysElapsed = ChronoUnit.DAYS.between(BASE_DATE, date);

        // 천간 계산 (10주기)
        int ganIndex = (int) (daysElapsed % 10) % 10;
        int zhiIndex = (10 + (int) (daysElapsed % 12)) % 12;

        return getGanZhi(ganIndex, zhiIndex);
    }

    // 시주(時柱) 계산
    private String getHourGanZhi(LocalDate date, LocalTime time) {
        int totalDays = (int) ChronoUnit.DAYS.between(BASE_DATE, date) % 60;
        int hourIndex = (time.getHour() + 1) / 2 % 12;
        Tenkan hourGan = Tenkan.fromIndex((totalDays % 10 * 2 + hourIndex) % 10);
        Jiji hourZhi = Jiji.fromIndex(hourIndex);

        return formatGanZhi(hourGan, hourZhi);
    }

    // 천간과 지지를 포맷하여 문자열 반환
    private String getGanZhi(int ganIndex, int zhiIndex) {
        Tenkan gan = Tenkan.fromIndex(ganIndex);
        Jiji zhi = Jiji.fromIndex(zhiIndex);

        return formatGanZhi(gan, zhi);
    }

    // 데이터 포맷하여 결과 반환
    private String formatGanZhi(Tenkan gan, Jiji zhi) {
        return gan.getName() + zhi.getName() + "[" + gan.getHanja() + zhi.getHanja() + "]"
                + "(" + gan.getElement() + "/" + gan.getYinYang() + ")";
    }
}

