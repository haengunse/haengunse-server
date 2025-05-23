package fortune.haengunseserver.domain.message.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "행운의 아이템 응답")
public class ItemResponseDto {

    @Schema(description = "행운의 색상")
    private String color;

    @Schema(description = "행운의 장소")
    private String place;

    @Schema(description = "행운의 숫자")
    private String number;

    @Schema(description = "행운의 물건")
    private String object;

    @Schema(description = "행운의 음식")
    private String meal;
}
