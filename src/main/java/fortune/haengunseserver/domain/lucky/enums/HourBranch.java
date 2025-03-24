package fortune.haengunseserver.domain.lucky.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum HourBranch {

    자시(0, true),  // 23:30~01:30 → 다음 날로 간주
    축시(1, false),
    인시(2, false),
    묘시(3, false),
    진시(4, false),
    사시(5, false),
    오시(6, false),
    미시(7, false),
    신시(8, false),
    유시(9, false),
    술시(10, false),
    해시(11, false);

    private final int index;
    private final boolean isNextDay;

    public boolean isNextDay() {
        return isNextDay;
    }

    public static HourBranch fromLabel(String label) {
        return Arrays.stream(values())
                .filter(branch -> label.startsWith(branch.name()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("올바르지 않은 시지 입력: " + label));
    }
}


