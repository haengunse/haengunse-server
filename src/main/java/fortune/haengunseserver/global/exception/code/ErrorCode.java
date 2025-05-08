package fortune.haengunseserver.global.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 정상 처리
    OK(HttpStatus.OK, "요청이 성공적으로 처리되었습니다."),

    // 클라이언트 요청 오류
    TOO_MANY_QUESTIONS(HttpStatus.UNPROCESSABLE_ENTITY, "질문은 최대 3개까지만 입력할 수 있습니다."),
    INVALID_DATE_FORMAT(HttpStatus.BAD_REQUEST, "날짜 형식은 yyyy-MM-dd이어야 합니다."),
    INVALID_MANSE_FORMAT(HttpStatus.BAD_REQUEST, "만세정보는 8개의 항목이어야 합니다."),

    // 행운세 서버 오류
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다."),
    XML_PARSE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "양력 응답 파싱에 실패했습니다."),
    PARSE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "GPT 응답 파싱에 실패했습니다."),

    // KASI(달력 서버) 오류
    KASI_API_FAILURE(HttpStatus.BAD_GATEWAY, "달력 서버(KASI) 응답에 실패했습니다. 잠시 후 다시 시도해주세요."), // 1, 4, 99
    KASI_API_DEPRECATED(HttpStatus.GONE, "더 이상 제공되지 않는 달력 API입니다."), // 12
    KASI_ACCESS_DENIED(HttpStatus.FORBIDDEN, "달력 서버 접근이 거부되었습니다. API 키 또는 권한을 확인해주세요."), // 20
    KASI_QUOTA_EXCEEDED(HttpStatus.TOO_MANY_REQUESTS, "달력 서버의 하루 요청 한도를 초과했습니다."), // 22
    KASI_RPS_LIMIT_EXCEEDED(HttpStatus.TOO_MANY_REQUESTS, "달력 서버의 초당 요청 수를 초과했습니다."), // 23
    KASI_INVALID_SERVICE_KEY(HttpStatus.UNAUTHORIZED, "달력 서버 API 키가 잘못되었습니다."), // 30
    KASI_KEY_EXPIRED(HttpStatus.FORBIDDEN, "달력 서버 API 키의 사용 기간이 만료되었습니다."), // 31
    KASI_UNREGISTERED_IP(HttpStatus.FORBIDDEN, "등록되지 않은 IP 주소입니다. 달력 서버에 IP 등록이 필요합니다."), // 32

    // GPT API 오류
    GPT_API_FAILURE(HttpStatus.BAD_GATEWAY, "GPT 서버 응답에 실패했습니다. 잠시 후 다시 시도해주세요."),

    GPT_OVERLOADED(HttpStatus.BAD_GATEWAY, "GPT 서버가 혼잡합니다. 잠시 후 다시 시도해주세요."),
    GPT_RATE_LIMIT_EXCEEDED(HttpStatus.BAD_GATEWAY, "요청이 너무 많습니다. 잠시 후 다시 시도해주세요."),

    GPT_QUOTA_EXCEEDED(HttpStatus.BAD_GATEWAY, "GPT 사용한도를 초과했습니다. 관리자에게 문의해주세요."),
    GPT_UNAUTHORIZED(HttpStatus.BAD_GATEWAY, "GPT 인증에 실패했습니다. 관리자에게 문의해주세요."),
    GPT_ORG_RESTRICTED(HttpStatus.BAD_GATEWAY, "현재 계정으로는 서비스를 이용할 수 없습니다."),
    GPT_UNSUPPORTED_REGION(HttpStatus.BAD_GATEWAY, "해당 지역에서는 서비스를 이용할 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
