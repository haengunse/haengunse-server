package fortune.haengunseserver.domain.fortune.today.scheduler;

import fortune.haengunseserver.domain.fortune.today.dto.response.starfortune.StarResponseDto;
import fortune.haengunseserver.domain.fortune.today.service.starfortune.StarFortuneService;
import fortune.haengunseserver.domain.fortune.today.service.starfortune.StarFortuneStore;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StarScheduler {

    private final StarFortuneService starFortuneService;
    private final StarFortuneStore starFortuneStore; // 내부 저장소

    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void updateStarFortunesDaily() {

        int maxRetries = 3;
        int delayMillis = 10000; // 10초 후 재시도

        for (int attempt = 1; attempt <= maxRetries; attempt++) {
            try {
                List<StarResponseDto> fortunes = starFortuneService.getFortune(null); // 인터페이스 호출
                starFortuneStore.update(fortunes); // 저장소 업데이트
                return;
            } catch (Exception e) {
                if (attempt < maxRetries) {
                    try {
                        Thread.sleep(delayMillis); // 재시도 전 대기
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            }
        }
    }
}
