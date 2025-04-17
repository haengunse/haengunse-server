package fortune.haengunseserver.domain.fortune.dto.request.dream;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "해몽 요청")
public class DreamRequest {

    @NotBlank(message = "사용자의 꿈 내용을 보내주세요.")
    @Schema(description = "사용자의 질문", example = "오늘 돼지꿈 꿨는데 무슨 뜻이야?")
    private List<String> question;
}
