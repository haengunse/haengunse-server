package fortune.haengunseserver.domain.fortune.today.dto.response.agefortune;

import fortune.haengunseserver.domain.fortune.today.dto.response.FortuneMatchResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "오늘의 띠 운세 결과 응답")
public class AgeResponseDto {

    @Schema(description = "띠 이름")
    private String ageName;

    @Schema(description = "결과 메세지 및 띠 매치")
    private FortuneMatchResponse content;
}