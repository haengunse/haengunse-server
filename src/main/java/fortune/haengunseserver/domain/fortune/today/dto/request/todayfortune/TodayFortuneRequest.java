package fortune.haengunseserver.domain.fortune.today.dto.request.todayfortune;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "오늘의 운세 요청")
public class TodayFortuneRequest {

    @NotBlank(message = "사용자의 만세력 정보를 보내주세요.")
    @Schema(description = "만세력 정보")
    private String manseInfo;

    @NotBlank(message = "정확한 해석을 위해 성별을 보내주세요.")
    @Schema(description = "성별 (M/F)", example = "M")
    private String gender;
}
