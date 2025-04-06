package fortune.haengunseserver.domain.fortune.scheduler;

import fortune.haengunseserver.domain.fortune.dto.response.starfortune.StarResponseDto;
import fortune.haengunseserver.domain.fortune.service.starfortune.StarFortuneService;
import fortune.haengunseserver.domain.fortune.service.starfortune.StarFortuneStore;
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
        try {
            List<StarResponseDto> fortunes = starFortuneService.getFortune(null);
            starFortuneStore.update(fortunes);
        } catch (Exception e) {
            System.out.println("저장 실패");
        }
    }
}
