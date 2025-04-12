package fortune.haengunseserver.global.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse {

    private final int status;
    private final String message;
}
