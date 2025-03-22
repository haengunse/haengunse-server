package fortune.haengunseserver.domain.lucky.dto.response;

import fortune.haengunseserver.domain.lucky.dto.FortuneDetail;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "오늘의 운세 결과 응답")
public class TodayLuckyResponse {

    private String response;
}