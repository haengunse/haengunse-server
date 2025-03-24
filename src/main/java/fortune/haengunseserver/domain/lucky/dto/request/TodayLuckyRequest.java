package fortune.haengunseserver.domain.lucky.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "오늘의 운세 요청")
public class TodayLuckyRequest {

    @Schema(description = "사용자 생년월일 (YYYY-MM-DD)", example = "1995-06-15")
    private String birthDate;

    @Schema(description = "양력(true) / 음력(false)", example = "true")
    private boolean solar;

    @Schema(description = "출생 시간", example = "자시 (23:30~1:30)")
    private String birthTime;

    @Schema(description = "성별 (M/F)", example = "M")
    private String gender;

    @Schema(description = "사용자 이름", example = "홍길동")
    private String name;
}
