package fortune.haengunseserver.domain.fortune.today.dto.response.todayfortune;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "운세 항목별 점수 및 설명")
public class FortuneDetail {

    @Schema(description = "운세 점수 (5점 만점)", example = "4.0")
    private double score;

    @Schema(description = "운세 설명", example = "재물운이 좋은 편입니다.")
    private String description;
}
