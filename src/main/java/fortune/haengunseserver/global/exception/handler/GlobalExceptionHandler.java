package fortune.haengunseserver.global.exception.handler;

import fortune.haengunseserver.global.exception.CustomException;
import fortune.haengunseserver.global.exception.GptException;
import fortune.haengunseserver.global.exception.code.ErrorCode;
import fortune.haengunseserver.global.response.ApiResponse;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse> handleCustomException(CustomException ex) {
        ErrorCode errorCode = ex.getErrorCode();

        ApiResponse errorResponse =
                new ApiResponse(errorCode.getHttpStatus().value(), errorCode.getMessage());

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(GptException.class)
    public ResponseEntity<ApiResponse> handleGptException(GptException ex) {
        ErrorCode errorCode = ex.getErrorCode();

        ApiResponse errorResponse =
                new ApiResponse(errorCode.getHttpStatus().value(), errorCode.getMessage());

        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(errorResponse);
    }

    // 필드 validation 실패
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getDefaultMessage())
                .findFirst()
                .orElse("입력값이 올바르지 않습니다.");

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ApiResponse(HttpStatus.BAD_REQUEST.value(), errorMessage));
    }
}
