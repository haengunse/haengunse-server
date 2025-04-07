package fortune.haengunseserver.domain.fortune.dto.request.todayfortune;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "오늘의 운세 요청")
public class TodayFortuneRequest {

    @Schema(description = "만세력 정보")
    private String manseInfo;

    @Schema(description = "성별 (M/F)", example = "M")
    private String gender;
}
