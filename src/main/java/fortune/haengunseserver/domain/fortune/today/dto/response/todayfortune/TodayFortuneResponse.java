package fortune.haengunseserver.domain.fortune.today.dto.response.todayfortune;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "오늘의 운세 결과 응답")
public class TodayFortuneResponse {

    @Schema(description = "총 운세 점수 (100점 만점)", example = "78")
    private int totalScore;

    @Schema(description = "총운 메시지", example = "오늘은 평소보다 에너지가 넘치는 하루입니다.")
    private String generalFortune;

    @Schema(description = "재물운 정보")
    private FortuneDetail wealthFortune;

    @Schema(description = "연애운 정보")
    private FortuneDetail loveFortune;

    @Schema(description = "건강운 정보")
    private FortuneDetail healthFortune;

    @Schema(description = "학업운 정보")
    private FortuneDetail studyFortune;

    @Schema(description = "직장운 정보")
    private FortuneDetail careerFortune;

    @Schema(description = "일일 조언 메시지", example = "오늘은 컨디션 조절이 필요한 하루예요.")
    private String dailyMessage;
}