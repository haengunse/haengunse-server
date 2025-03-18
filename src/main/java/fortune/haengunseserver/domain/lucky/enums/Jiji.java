package fortune.haengunseserver.domain.lucky.enums;

public enum Jiji {

    자("자", "子", "수", "양"),
    축("축", "丑", "토", "음"),
    인("인", "寅", "목", "양"),
    묘("묘", "卯", "목", "음"),
    진("진", "辰", "토", "양"),
    사("사", "巳", "화", "음"),
    오("오", "午", "화", "양"),
    미("미", "未", "토", "음"),
    신("신", "申", "금", "양"),
    유("유", "酉", "금", "음"),
    술("술", "戌", "토", "양"),
    해("해", "亥", "수", "음");

    private final String name;
    private final String hanja;
    private final String element;
    private final String yinYang;

    Jiji(String name, String hanja, String element, String yinYang) {
        this.name = name;
        this.hanja = hanja;
        this.element = element; // 오행
        this.yinYang = yinYang; // 음양
    }

    public String getName() { return name; }
    public String getHanja() { return hanja; }
    public String getElement() { return element; }
    public String getYinYang() { return yinYang; }

    public static Jiji fromIndex(int index) {
        return values()[index % 12];
    }
}
