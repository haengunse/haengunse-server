package fortune.haengunseserver.domain.fortune.lifetime.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "평생사주 결과 응답")
public class LifetimeSajuResponse {

    @Schema(description = "전체적인 운세 요약")
    private String summary;

    @Schema(description = "성격분석 정보")
    private String personality;

    @Schema(description = "오행분석 정보")
    private String fiveElements;

    @Schema(description = "십성분석 정보")
    private String tenGods;
}
