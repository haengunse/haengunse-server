package fortune.haengunseserver.domain.manse.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "만세력 계산 후, 응답")
public class ManseResponse {

    @Schema(description = "사용자 만세력")
    private String manseInfo;

    @Schema(description = "성별 (M/F)", example = "M")
    private String gender;

    @Schema(description = "사용자 이름", example = "홍길동")
    private String name;
}
