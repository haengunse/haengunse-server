package fortune.haengunseserver.domain.fortune.service.todayfortune;

import com.fasterxml.jackson.databind.ObjectMapper;
import fortune.haengunseserver.domain.fortune.dto.request.todayfortune.TodayFortuneRequest;
import fortune.haengunseserver.domain.fortune.dto.response.todayfortune.TodayFortuneResponse;
import fortune.haengunseserver.domain.fortune.service.FortuneRequestService;
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
public class TodayFortuneService extends FortuneRequestService<TodayFortuneRequest, TodayFortuneResponse> {

    private final ManseCalculator manse;

    public TodayFortuneService(OpenAiChatModel chatModel, ManseCalculator manse) {
        super(chatModel);
        this.manse = manse;
    }

    //  사용자의 생년월일을 만세력으로 변환하여 GPT에 요청할 프롬프트 생성
    @Override
    public Prompt generatePrompt(TodayFortuneRequest input) {
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
                                        
                        - 총 점수 
                        - 총운해석
                        - 재물운
                        - 연애운
                        - 건강운
                        - 학업운
                        - 직장운
                        - 운세 결과에 기반한 간단한 한 줄 조언

                        결과를 생성할 때는 다음의 규칙에 맞게 생성해 주세요:
                        
                        1. 총 점수는 100점 만점으로 점수를 반환해주세요. 
                        2. 총 점수를 계산할 때는 운세 항목 및 총운 해석에 기반하여 점수를 채점해 주세요.
                        3. 각 운세 항목(재물운, 연애운, 건강운, 학업운, 직장운)은 해석과 점수를 반환해야합니다.
                        3-1. 운세 항목 별로 6줄 이상의 문장으로 대답해주세요. 
                        3-2. 항복 별 해석을 생성할 때, 사용자의 정보와 만세력을 참고하여 작성해주세요.
                        3-3. 해석에 포함되어야 할 내용은 다음과 같습니다: 오늘의 운세 흐름, 유의할 점
                        4. 다음과 같은 형식(json)으로 반환해주세요:

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
    protected TodayFortuneResponse processResponse(ChatResponse response) {
        String raw = response.getResult().getOutput().getText();

        // JSON 태그 제거: ```json\n{...}\n```
        String cleanJson = raw
                .replaceAll("(?s)^```json\\s*", "") // 시작 백틱 제거
                .replaceAll("\\s*```$", "")         // 종료 백틱 제거
                .trim();

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(cleanJson, TodayFortuneResponse.class);
        } catch (IOException e) {
            throw new RuntimeException("GPT 응답 파싱 실패", e);
        }
    }
}
