package fortune.haengunseserver.domain.fortune.controller;

import fortune.haengunseserver.domain.fortune.service.FortuneService;
import fortune.haengunseserver.domain.fortune.service.todayfortune.ManseCalculator;
import fortune.haengunseserver.domain.fortune.dto.request.DreamRequest;
import fortune.haengunseserver.domain.fortune.dto.request.LuckyMatchRequest;
import fortune.haengunseserver.domain.fortune.dto.request.TodayFortuneRequest;
import fortune.haengunseserver.domain.fortune.dto.response.DreamResponse;
import fortune.haengunseserver.domain.fortune.dto.response.FortuneMatchResponse;
import fortune.haengunseserver.domain.fortune.dto.response.todayfortune.TodayFortuneResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/lucky")
public class FortuneController {

    private final ManseCalculator manseCalculator;
    private final FortuneService<TodayFortuneRequest, TodayFortuneResponse> todayLuckyService;

    @Operation( summary = "오늘의 운세 조회", description = "사용자의 정보 및 사주를 기반으로 오늘의 운세를 반환")
    @ApiResponse(
            responseCode = "200",
            description = "운세 데이터 정상 반환",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = TodayFortuneResponse.class))
    )
    @PostMapping("/today")
    public ResponseEntity<TodayFortuneResponse> getTodayFortune(@RequestBody TodayFortuneRequest request) {
        TodayFortuneResponse response = todayLuckyService.getFortune(request);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "별자리 운세 조회")
    @ApiResponse(
            responseCode = "200",
            description = "별자리 운세 데이터 정상 반환",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = FortuneMatchResponse.class))
    )
    @PostMapping("/star-lucky")
    public ResponseEntity<FortuneMatchResponse> getStarFortune(@RequestBody LuckyMatchRequest request) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "띠 운세 조회")
    @ApiResponse(
            responseCode = "200",
            description = "띠 운세 데이터 정상 반환",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = FortuneMatchResponse.class))
    )
    @PostMapping("/age-lucky")
    public ResponseEntity<FortuneMatchResponse> getAgeFortune(@RequestBody LuckyMatchRequest request) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "꿈 해몽", description = "사용자의 꿈 질문을 기반으로 꿈 해몽을 반환합니다.")
    @ApiResponse(
            responseCode = "200",
            description = "꿈 해몽 정상 반환",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = FortuneMatchResponse.class))
    )
    @PostMapping("/dream")
    public ResponseEntity<DreamResponse> getDreamLucky(@RequestBody DreamRequest request) {
        return ResponseEntity.ok().build();
    }

    // 만세력 계산 테스트용 API
    @GetMapping("/calculate")
    public String calculateManse(
            @RequestParam String birthDate,
            @RequestParam boolean isSolar,
            @RequestParam String birthTime
    ) {
        return manseCalculator.calculateManse(birthDate, isSolar, birthTime);
    }
}
