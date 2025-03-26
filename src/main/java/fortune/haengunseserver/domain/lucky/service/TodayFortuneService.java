package fortune.haengunseserver.domain.lucky.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import fortune.haengunseserver.domain.lucky.dto.request.TodayLuckyRequest;
import fortune.haengunseserver.domain.lucky.dto.response.TodayLuckyResponse;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 오늘의 운세 서비스 클래스
 * FortuneService 인터페이스를 구현하여 운세 조회 기능 제공
 */
@Service
public class TodayFortuneService extends FortuneRequestService<TodayLuckyRequest, TodayLuckyResponse> {

    private final ManseCalculator manse;

    public TodayFortuneService(OpenAiChatModel chatModel, ManseCalculator manse) {
        super(chatModel);
        this.manse = manse;
    }

    //  사용자의 생년월일을 만세력으로 변환하여 GPT에 요청할 프롬프트 생성
    @Override
    public Prompt generatePrompt(TodayLuckyRequest input) {
        String mansaeInfo = manse.calculateManse(input.getBirthDate(), input.isSolar(), input.getBirthTime());
        String todayDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);

        String content = String.format("""
                        당신은 유능한 역술가입니다.
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
                        - 오늘 운세에 기반한 간단한 조언(ex: 오늘은 컨디션 조절이 필요한 하루에요)

                        각 운세 항목(재물운, 연애운, 건강운, 학업운, 직장운)은 5점 만점으로 점수도 반환해주세요. (ex: 2.0)
                        운세 항목 별로 6줄 이상 설명해주세요.

                        다음과 같은 형식(json)으로 반환해주세요:

                        {
                            "totalScore": ,
                            "generalFortune": "...",
                            "wealthFortune": {
                                "score": ,
                                "description": "..."
                            },
                            "loveFortune": {
                                "score": ,
                                "description": "..."
                            },
                            "healthFortune": {
                                "score": ,
                                "description": "..."
                            },
                            "studyFortune": {
                                "score": ,
                                "description": "..."
                            },
                            "careerFortune": {
                                "score": ,
                                "description": "..."
                            },
                            "dailyMessage": ".." ,
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
    protected TodayLuckyResponse processResponse(ChatResponse response) {
        String raw = response.getResult().getOutput().getText();

        // JSON 태그 제거: ```json\n{...}\n```
        String cleanJson = raw
                .replaceAll("(?s)^```json\\s*", "") // 시작 백틱 제거
                .replaceAll("\\s*```$", "")         // 종료 백틱 제거
                .trim();

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(cleanJson, TodayLuckyResponse.class);
        } catch (IOException e) {
            throw new RuntimeException("GPT 응답 파싱 실패", e);
        }
    }
}
