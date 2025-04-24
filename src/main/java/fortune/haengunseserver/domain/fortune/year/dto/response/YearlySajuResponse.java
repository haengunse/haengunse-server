package fortune.haengunseserver.domain.fortune.year.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@Schema(description = "신년운세 결과 응답")
public class YearlySajuResponse {

    @Schema(description = "총론")
    private String generalFortune;

    @Schema(description = "재물운 정보")
    private String wealthFortune;

    @Schema(description = "연애운 정보")
    private String loveFortune;

    @Schema(description = "건강운 정보")
    private String healthFortune;

    @Schema(description = "학업운 정보")
    private String studyFortune;

    @Schema(description = "직업운/사업운 정보")
    private String careerFortune;
}
