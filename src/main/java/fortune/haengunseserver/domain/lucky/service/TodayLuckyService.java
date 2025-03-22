package fortune.haengunseserver.domain.lucky.service;

import fortune.haengunseserver.domain.lucky.dto.request.TodayLuckyRequest;
import fortune.haengunseserver.domain.lucky.dto.response.TodayLuckyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 오늘의 운세 서비스 클래스
 * FortuneService 인터페이스를 구현하여 운세 조회 기능 제공
 */
@Service
public class TodayLuckyService extends FortuneRequestService<TodayLuckyRequest, TodayLuckyResponse> {

    private final ManseCalculator manse;

    public TodayLuckyService(OpenAiChatModel chatModel, ManseCalculator manse) {
        super(chatModel);
        this.manse = manse;
    }

    //  사용자의 생년월일을 만세력으로 변환하여 GPT에 요청할 프롬프트 생성
    @Override
    public Prompt generatePrompt(TodayLuckyRequest input) {
        String mansaeInfo = manse.calculateManse(input.getBirthDate(), input.isSolar(), input.getBirthTime());
        String todayDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);

        String content = String.format("""
                        사주를 기반으로 한 오늘의 운세를 볼 예정입니다.

                        사용자의 정보는 다음과 같습니다:
                                        
                        - 이름: %s
                        - 성별: %s
                        - 생년월일: %s (%s)
                        - 태어난 시간: %s
                        - 오늘의 날짜: %s

                        만세력은 다음과 같습니다:
                                        
                        %s

                        사용자의 정보와 만세력을 참고하여 오늘의 운세를 봐주세요.

                        Response로는 다음을 보내주세요:
                                        
                        - 총 점수 (100점 만점으로)
                        - 총운해석
                        - 재물운
                        - 연애운
                        - 건강운
                        - 학업운
                        - 직장운
                        - 오늘 운세에 기반한 간단 메시지(ex: 오늘은 컨디션 조절이 필요한 하루에요)
                        - 행운의 아이템과 색상

                        각 운세 항목(재물운, 연애운, 건강운, 학업운, 직장운)은 5점 만점으로 점수도 반환해주세요.
                        운세 항목별로 3줄 이상 설명해주세요.

                        다음과 같은 형식(json)으로 반환해주세요:

                        {
                            "totalScore": 86,
                            "generalFortune": "...",
                            "wealthFortune": {
                                "score": 3.5,
                                "description": "..."
                            },
                            "loveFortune": {
                                "score": 2.6,
                                "description": "..."
                            },
                            "healthFortune": {
                                "score": 3.2,
                                "description": "..."
                            },
                            "studyFortune": {
                                "score": 3.9,
                                "description": "..."
                            },
                            "careerFortune": {
                                "score": 3.3,
                                "description": "..."
                            },
                            "dailyMessage": "오늘은 컨디션 조절이 필요한 하루예요.",
                            "luckyItem": "블루투스 이어폰",
                            "luckyColor": "네이비 블루"
                        }
                        """,
                input.getName(),
                input.getGender(),
                input.getBirthDate(),
                input.isSolar() ? "양력" : "음력",
                input.getBirthTime(),
                todayDate,
                mansaeInfo
        );

        return new Prompt(content);
    }

    /**
     * GPT 응답을 처리하여 TodayLuckyResponse 객체로 변환
     */
    @Override
    protected TodayLuckyResponse processResponse (ChatResponse response) {
        String fortuneText = response.getResult().getOutput().getText();
        return new TodayLuckyResponse(fortuneText);
    }
}
