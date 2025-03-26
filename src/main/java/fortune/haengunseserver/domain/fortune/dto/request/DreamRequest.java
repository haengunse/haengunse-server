package fortune.haengunseserver.domain.fortune.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "해몽 요청")
public class DreamRequest {

    @Schema(description = "사용자의 질문", example = "오늘 돼지꿈 꿨는데 무슨 뜻이야?")
    private String question;
}
