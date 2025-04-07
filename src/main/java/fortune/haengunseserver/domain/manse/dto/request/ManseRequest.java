package fortune.haengunseserver.domain.manse.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "만세력 계산 요청")
public class ManseRequest {

    @Schema(description = "사용자 생년월일 (YYYY-MM-DD)", example = "1995-06-15")
    private String birthDate;

    @Schema(description = "양력(true) / 음력(false)", example = "true")
    private boolean solar;

    @Schema(description = "출생 시간", example = "자시 (23:30~1:30)")
    private String birthTime;

    @Schema(description = "성별 (M/F)", example = "M")
    private String gender; // 여기선 안 받아도 됨

    @Schema(description = "사용자 이름", example = "홍길동")
    private String name; // 여기선 안 받아도 됨
}