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
@Schema(description = "질문뽑기/포춘쿠키 응답")
public class MessageResponseDto {

    private String message;
}