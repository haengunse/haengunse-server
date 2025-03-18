package fortune.haengunseserver.domain.lucky.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import fortune.haengunseserver.domain.lucky.enums.Jiji;
import fortune.haengunseserver.domain.lucky.enums.Tenkan;
import org.springframework.stereotype.Service;

@Service
public class ManseCalculator {

    public String calculateManse(String birthDate, boolean isSolar, String birthTime) {

        LocalDate date = LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalTime time = LocalTime.parse(birthTime, DateTimeFormatter.ofPattern("HH:mm"));

        // 연주 계산
        String yearGanZhi = formatGanZhi(
                Tenkan.fromIndex((date.getYear() - 4) % 10),
                Jiji.fromIndex((date.getYear() - 4) % 12)
        );

        // 월주 계산: 절기 보정 필요
        String monthGanZhi = formatGanZhi(
                Tenkan.fromIndex((date.getYear() * 12 + date.getMonthValue()) % 10),
                Jiji.fromIndex(date.getMonthValue() % 12)
        );

        // 일주 계산: 60갑자 계산 필요
        int totalDays = (int) date.toEpochDay() % 60;
        String dayGanZhi = formatGanZhi(
                Tenkan.fromIndex(totalDays % 10),
                Jiji.fromIndex(totalDays % 12)
        );

        // 시주 계산
        int hourIndex = (time.getHour() + 1) / 2 % 12;
        String hourGanZhi = formatGanZhi(
                Tenkan.fromIndex((totalDays % 10 * 2 + hourIndex) % 10),
                Jiji.fromIndex(hourIndex)
        );

        return String.format(
                "연주: %s\n월주: %s\n일주: %s\n시주: %s",
                yearGanZhi, monthGanZhi, dayGanZhi, hourGanZhi
        );
    }

    // 데이터 포맷하여 결과 반환
    private String formatGanZhi(Tenkan gan, Jiji zhi) {
        return gan.getName() + zhi.getName() + "[" + gan.getHanja() + zhi.getHanja() + "]"
                + "(" + gan.getElement() + "/" + gan.getYinYang() + ")";
    }
}

