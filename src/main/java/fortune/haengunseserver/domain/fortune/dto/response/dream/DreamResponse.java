package fortune.haengunseserver.domain.fortune.dto.response.dream;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "해몽 응답")
public class DreamResponse {

    @Schema(description = "꿈 해석 메시지", example = "돼지꿈은 재물이 들어오는 좋은 꿈입니다!")
    private String interpretation;
}
