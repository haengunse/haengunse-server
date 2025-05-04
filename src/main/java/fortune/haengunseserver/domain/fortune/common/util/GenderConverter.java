package fortune.haengunseserver.domain.fortune.common.util;

public class GenderConverter {

    public static String toKorean(String gender) {
        return "M".equals(gender) ? "남성" : "여성";
    }
}
