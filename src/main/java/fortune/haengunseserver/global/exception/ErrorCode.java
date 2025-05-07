package fortune.haengunseserver.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 정상
    OK(HttpStatus.OK, "요청이 성공적으로 처리되었습니다."),

    // 클라이언트 요청 오류
    TOO_MANY_QUESTIONS(HttpStatus.UNPROCESSABLE_ENTITY, "질문은 최대 3개까지만 입력할 수 있습니다."),
    INVALID_DATE_FORMAT(HttpStatus.BAD_REQUEST, "날짜 형식은 yyyy-MM-dd이어야 합니다."),
    UNAUTHORIZED_API_KEY(HttpStatus.UNAUTHORIZED, "API 키가 유효하지 않거나 누락되었습니다."),
    UNAUTHORIZED_IP(HttpStatus.FORBIDDEN, "등록되지 않은 IP 주소입니다."),
    DEPRECATED_API(HttpStatus.GONE, "해당 API는 더 이상 제공되지 않거나 사용 중지되었습니다."),
    TOO_MANY_REQUESTS(HttpStatus.TOO_MANY_REQUESTS, "짧은 시간에 너무 많은 요청을 보냈습니다. 잠시 후 다시 시도해주세요."), // 요청 수 과다 (일일 or 초당)
    INVALID_MANSE_FORMAT(HttpStatus.BAD_REQUEST, "만세정보는 8개의 항목이어야 합니다."),

    // 행운세 서버 오류
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다."),
    XML_PARSE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "양력 응답 파싱에 실패했습니다."),
    PARSE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "GPT 응답 파싱에 실패했습니다."),

    // 외부 서버 오류
    KASI_SERVER_ERROR(HttpStatus.BAD_GATEWAY, "달력 서버 응답에 문제가 발생했습니다."), // KASI 서버 오류
    GPT_SERVER_ERROR(HttpStatus.BAD_GATEWAY, "GPT 서버 응답에 문제가 발생했습니다."), // GPT API 서버 오류
    EXTERNAL_API_TIMEOUT(HttpStatus.GATEWAY_TIMEOUT, "외부 서버 응답이 지연되고 있습니다."); // 외부 타임아웃

    private final HttpStatus httpStatus;
    private final String message;
}
