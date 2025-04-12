package fortune.haengunseserver.domain.manse.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "만세력 계산 요청")
public class ManseRequest {

    @NotBlank(message = "생년월일은 필수입니다.")
    @Schema(description = "사용자 생년월일 (YYYY-MM-DD)", example = "1995-06-15")
    private String birthDate;

    @NotNull(message = "양력 여부를 입력해주세요.")
    @Schema(description = "양력(true) / 음력(false)", example = "true")
    private boolean solar;

    @NotBlank(message = "출생 시간은 필수입니다. 출생 시간을 모른다면 모름 으로 보내주세요.")
    @Schema(description = "출생 시간", example = "자시 (23:30~1:30)")
    private String birthTime;

    @NotBlank(message = "성별은 필수입니다.")
    @Schema(description = "성별 (M/F)", example = "M")
    private String gender;

    @NotBlank(message = "이름은 필수입니다.")
    @Schema(description = "사용자 이름", example = "홍길동")
    private String name;
}