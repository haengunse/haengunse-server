package fortune.haengunseserver.domain.fortune.today.scheduler;

import fortune.haengunseserver.domain.fortune.today.dto.response.agefortune.AgeResponseDto;
import fortune.haengunseserver.domain.fortune.today.service.agefortune.AgeFortuneService;
import fortune.haengunseserver.domain.fortune.today.service.agefortune.AgeFortuneStore;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AgeScheduler {

    private final AgeFortuneService ageFortuneService;
    private final AgeFortuneStore ageFortuneStore;

    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void updateAgeFortune() {

        int maxRetries = 3;
        int delayMillis = 10000; // 10초 후 재시도

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                List<AgeResponseDto> fortunes = ageFortuneService.getFortune(null);
                ageFortuneStore.update(fortunes);
                return;
            } catch (Exception e) {
                if (attempt < maxRetries) {
                    try {
                        Thread.sleep(delayMillis);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }
    }
}
