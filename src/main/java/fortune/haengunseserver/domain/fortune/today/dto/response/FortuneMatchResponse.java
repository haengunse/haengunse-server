package fortune.haengunseserver.domain.fortune.today.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "띠/별자리 운세 결과 응답")
public class FortuneMatchResponse {

    @Schema(description = "운세 결과 메시지")
    private String mainMessage;

    @Schema(description = "잘 맞는 별자리/띠")
    private String bestMatch;

    @Schema(description = "안 맞는 별자리/띠")
    private String worstMatch;
}
