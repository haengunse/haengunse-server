package fortune.haengunseserver.domain.fortune.dto.response.starfortune;

import fortune.haengunseserver.domain.fortune.dto.response.FortuneMatchResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "오늘의 별자리 운세 결과 응답")
public class StarResponseDto {

    @Schema(description = "별자리 이름", example = "쌍둥이자리")
    private String starName;

    @Schema(description = "별자리 생일", example = "양력 5월 21일~6월 21일")
    private String dateRange;

    @Schema(description = "결과 메세지 및 별자리 매치")
    private FortuneMatchResponse content;
}
