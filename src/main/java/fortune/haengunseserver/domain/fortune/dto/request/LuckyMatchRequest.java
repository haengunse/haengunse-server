package fortune.haengunseserver.domain.fortune.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "띠/별자리 운세 요청")
public class LuckyMatchRequest {

    @Schema(description = "사용자 생년월일 (YYYY-MM-DD)", example = "1995-06-15")
    private String birthDate;
}
