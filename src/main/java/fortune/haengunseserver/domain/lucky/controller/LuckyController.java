package fortune.haengunseserver.domain.lucky.controller;

import fortune.haengunseserver.domain.lucky.dto.request.LuckyMatchRequest;
import fortune.haengunseserver.domain.lucky.dto.request.TodayLuckyRequest;
import fortune.haengunseserver.domain.lucky.dto.response.LuckyMatchResponse;
import fortune.haengunseserver.domain.lucky.dto.response.TodayLuckyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lucky")
public class LuckyController {

    @Operation( summary = "오늘의 운세 조회", description = "사용자의 정보 및 사주를 기반으로 오늘의 운세를 반환")
    @ApiResponse(
            responseCode = "200",
            description = "운세 데이터 정상 반환",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TodayLuckyResponse.class))
    )
    @PostMapping("/today")
    public ResponseEntity<TodayLuckyResponse> getTodayLucky(@RequestBody TodayLuckyRequest request) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "별자리 운세 조회")
    @ApiResponse(
            responseCode = "200",
            description = "별자리 운세 데이터 반환",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = LuckyMatchResponse.class))
    )
    @PostMapping("/star-lucky")
    public ResponseEntity<LuckyMatchResponse> getStarLucky(@RequestBody LuckyMatchRequest request) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "띠 운세 조회")
    @ApiResponse(
            responseCode = "200",
            description = "띠 운세 데이터 반환",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = LuckyMatchResponse.class))
    )
    @PostMapping("/age-lucky")
    public ResponseEntity<LuckyMatchResponse> getAgeLucky(@RequestBody LuckyMatchRequest request) {
        return ResponseEntity.ok().build();
    }
}
