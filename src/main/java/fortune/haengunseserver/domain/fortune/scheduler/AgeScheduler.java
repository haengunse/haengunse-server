package fortune.haengunseserver.domain.fortune.scheduler;

import fortune.haengunseserver.domain.fortune.dto.response.agefortune.AgeResponseDto;
import fortune.haengunseserver.domain.fortune.service.agefortune.AgeFortuneService;
import fortune.haengunseserver.domain.fortune.service.agefortune.AgeFortuneStore;
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
        try {
            List<AgeResponseDto> fortunes = ageFortuneService.getFortune(null);
            ageFortuneStore.update(fortunes);
        } catch (Exception e) {
            System.out.println("저장 실패");
        }
    }
}
