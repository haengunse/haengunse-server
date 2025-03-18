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

        Tenkan yearGan = Tenkan.fromIndex((date.getYear() - 4) % 10);
        Jiji yearZhi = Jiji.fromIndex((date.getYear() - 4) % 12);
        String yearGanZhi = yearGan.getHanja() + yearZhi.getHanja(); // 한자 출력

        Tenkan monthGan = Tenkan.fromIndex((date.getYear() * 12 + date.getMonthValue()) % 10);
        Jiji monthZhi = Jiji.fromIndex(date.getMonthValue() % 12);
        String monthGanZhi = monthGan.getHanja() + monthZhi.getHanja(); // 한자 출력

        int totalDays = (int) (date.toEpochDay() % 60);
        Tenkan dayGan = Tenkan.fromIndex(totalDays % 10);
        Jiji dayZhi = Jiji.fromIndex(totalDays % 12);
        String dayGanZhi = dayGan.getHanja() + dayZhi.getHanja(); // 한자 출력

        int hourIndex = (time.getHour() + 1) / 2 % 12;
        Tenkan hourGan = Tenkan.fromIndex((dayGan.ordinal() * 2 + hourIndex) % 10);
        Jiji hourZhi = Jiji.fromIndex(hourIndex);
        String hourGanZhi = hourGan.getHanja() + hourZhi.getHanja(); // 한자 출력

        return String.format("연주: %s(%s/%s), 월주: %s(%s/%s), 일주: %s(%s/%s), 시주: %s(%s/%s)",
                yearGanZhi, yearGan.getElement(), yearGan.getYinYang(),
                monthGanZhi, monthGan.getElement(), monthGan.getYinYang(),
                dayGanZhi, dayGan.getElement(), dayGan.getYinYang(),
                hourGanZhi, hourGan.getElement(), hourGan.getYinYang());
    }
}

