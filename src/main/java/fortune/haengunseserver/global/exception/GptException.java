package fortune.haengunseserver.global.exception;

import fortune.haengunseserver.global.exception.code.ErrorCode;
import lombok.Getter;

@Getter
public class GptException extends RuntimeException {

    private final ErrorCode errorCode;

    public GptException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
