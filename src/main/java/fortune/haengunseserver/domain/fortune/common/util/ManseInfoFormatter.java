package fortune.haengunseserver.domain.fortune.common.util;

import fortune.haengunseserver.global.exception.CustomException;
import fortune.haengunseserver.global.exception.ErrorCode;

public class ManseInfoFormatter {

    public static String format(String manseInfo) {
        String[] parts = manseInfo.trim().split(" ");
        if (parts.length != 8) {
            throw new CustomException(ErrorCode.INVALID_MANSE_FORMAT);
        }

        return String.format(
                "연주: %s %s\n월주: %s %s\n일주: %s %s\n시주: %s %s",
                parts[0], parts[1],
                parts[2], parts[3],
                parts[4], parts[5],
                parts[6], parts[7]
        );
    }
}

