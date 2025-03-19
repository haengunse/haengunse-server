package fortune.haengunseserver.domain.lucky.enums;

import java.util.HashMap;
import java.util.Map;

public enum SolarTerm {
    입춘(2, 4, "寅"),  // 2월 4일경
    경칩(3, 6, "卯"),  // 3월 6일경
    청명(4, 5, "辰"),  // 4월 5일경
    입하(5, 6, "巳"),  // 5월 6일경
    망종(6, 6, "午"),  // 6월 6일경
    소서(7, 7, "未"),  // 7월 7일경
    입추(8, 8, "申"),  // 8월 8일경
    백로(9, 8, "酉"),  // 9월 8일경
    한로(10, 8, "戌"), // 10월 8일경
    입동(11, 7, "亥"), // 11월 7일경
    대설(12, 7, "子"), // 12월 7일경
    소한(1, 6, "丑");  // 1월 6일경

    private static final Map<Integer, SolarTerm> MONTH_MAP = new HashMap<>();

    static {
        for (SolarTerm term : values()) {
            MONTH_MAP.put(term.month, term);
        }
    }

    private final int month;
    private final int day;
    private final String zhi;

    SolarTerm(int month, int day, String zhi) {
        this.month = month;
        this.day = day;
        this.zhi = zhi;
    }

    public int getMonth() { return month; }
    public int getDay() { return day; }
    public String getZhi() { return zhi; }

    // 특정 날짜가 속하는 월주의 지지를 반환
    public static String getZhiForDate(int month, int day) {
        for (SolarTerm term : values()) {
            if (month == term.month && day >= term.day) {
                return term.zhi;
            }
        }
        return 소한.zhi; // 기본값: 1월 (丑)
    }
}