package fortune.haengunseserver.domain.manse.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Tenkan {

    갑("갑", "甲", "목", "양"),
    을("을", "乙", "목", "음"),
    병("병", "丙", "화", "양"),
    정("정", "丁", "화", "음"),
    무("무", "戊", "토", "양"),
    기("기", "己", "토", "음"),
    경("경", "庚", "금", "양"),
    신("신", "辛", "금", "음"),
    임("임", "壬", "수", "양"),
    계("계", "癸", "수", "음");

    private final String name;
    private final String hanja;
    private final String element;
    private final String yinYang;

    public static Tenkan fromIndex(int index) {
        return values()[index % 10];
    }
}
