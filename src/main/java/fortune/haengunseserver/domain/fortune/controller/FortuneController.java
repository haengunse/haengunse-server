package fortune.haengunseserver.domain.fortune.controller;

import fortune.haengunseserver.domain.fortune.dto.response.agefortune.AgeResponseDto;
import fortune.haengunseserver.domain.fortune.dto.response.starfortune.StarResponseDto;
import fortune.haengunseserver.domain.fortune.service.agefortune.AgeFortuneService;
import fortune.haengunseserver.domain.fortune.service.starfortune.StarFortuneStore;
import fortune.haengunseserver.domain.fortune.service.todayfortune.ManseCalculator;
import fortune.haengunseserver.domain.fortune.dto.request.DreamRequest;
import fortune.haengunseserver.domain.fortune.dto.request.todayfortune.TodayFortuneRequest;
import fortune.haengunseserver.domain.fortune.dto.response.DreamResponse;
import fortune.haengunseserver.domain.fortune.dto.response.todayfortune.TodayFortuneResponse;
import fortune.haengunseserver.domain.fortune.service.todayfortune.TodayFortuneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/fortune")
public class FortuneController {

    private final ManseCalculator manseCalculator;
    private final TodayFortuneService todayLuckyService;
    private final StarFortuneStore starFortuneStore;
    private final AgeFortuneService ageFortuneService;

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
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = StarResponseDto.class))
            )
    )
    @GetMapping("/star")
    public ResponseEntity<List<StarResponseDto>> getStarFortune() {
        List<StarResponseDto> fortunes = starFortuneStore.get();

        if (fortunes == null || fortunes.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }

        return ResponseEntity.ok(fortunes);
    }

    @Operation(summary = "띠 운세 조회")
    @ApiResponse(
            responseCode = "200",
            description = "띠 운세 데이터 정상 반환",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = AgeResponseDto.class))
            )
    )
    @GetMapping("/age")
    public ResponseEntity<List<AgeResponseDto>> getAgeFortune() {
        List<AgeResponseDto> response = ageFortuneService.getFortune(null);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "꿈 해몽")
    @ApiResponse(
            responseCode = "200",
            description = "꿈 해몽 정상 반환",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DreamResponse.class))
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
