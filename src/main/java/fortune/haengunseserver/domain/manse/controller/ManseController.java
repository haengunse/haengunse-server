package fortune.haengunseserver.domain.manse.controller;

import fortune.haengunseserver.domain.manse.dto.request.ManseRequest;
import fortune.haengunseserver.domain.manse.dto.response.ManseResponse;
import fortune.haengunseserver.domain.manse.service.ManseCalculator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/manse")
public class ManseController {

    private final ManseCalculator manseCalculator;

    @Operation(summary = "만세력 계산", description = "사용자의 정보를 입력 받아 만세력 계산하여 반환")
    @ApiResponse(
            responseCode = "200",
            description = "만세력 정상 반환",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ManseResponse.class))
    )
    @PostMapping("/calculate")
    public ResponseEntity<ManseResponse> calculateManse(@RequestBody ManseRequest request) {
        ManseResponse manseInfo = manseCalculator.calculateManse(request);
        return ResponseEntity.ok(manseInfo);
    }
}
