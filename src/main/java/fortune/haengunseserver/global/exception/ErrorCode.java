package fortune.haengunseserver.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    OK(HttpStatus.OK, "요청이 성공적으로 처리되었습니다."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    TOO_MANY_QUESTIONS(HttpStatus.UNPROCESSABLE_ENTITY, "질문은 최대 3개까지만 입력할 수 있습니다."),
    INVALID_DATE_FORMAT(HttpStatus.BAD_REQUEST, "yyyy-MM-dd 형식으로 보내주세요."),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류");

    private final HttpStatus httpStatus;
    private final String message;
}
