package fortune.haengunseserver.domain.fortune.lifetime.controller;

import fortune.haengunseserver.domain.fortune.common.dto.request.SajuRequest;
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
@RequestMapping("/api/fortune/lifetime")
public class LifetimeFortuneController {

    @Operation( summary = "평생운세 조회")
    @ApiResponse(
            responseCode = "200",
            description = "평생운세 정상 반환",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = LifetimeFortuneController.class))
    )
    @PostMapping("/saju")
    public ResponseEntity<LifetimeFortuneController> getYearlySaju(@RequestBody SajuRequest request) {
        return ResponseEntity.ok(null);
    }
}
