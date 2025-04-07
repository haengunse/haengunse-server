package fortune.haengunseserver.domain.fortune.service.todayfortune;

import com.fasterxml.jackson.databind.ObjectMapper;
import fortune.haengunseserver.domain.fortune.dto.request.todayfortune.TodayFortuneRequest;
import fortune.haengunseserver.domain.fortune.dto.response.todayfortune.TodayFortuneResponse;
import fortune.haengunseserver.global.gpt.service.FortuneRequestService;
import fortune.haengunseserver.global.gpt.utils.ChatResponseParser;
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

    public TodayFortuneService(OpenAiChatModel chatModel) {
        super(chatModel);
    }

    //  사용자의 생년월일을 만세력으로 변환하여 GPT에 요청할 프롬프트 생성
    @Override
    public Prompt generatePrompt(TodayFortuneRequest input) {
        String manseInfo = input.getManseInfo();
        String gender = input.getGender();
        String todayDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);

        String content = String.format("""
                        다음은 사용자(성별: %s)의 사주 만세력 정보입니다:
                        
                        %s
                        
                        오늘의 날짜는 %s 입니다.
                        오늘 일진이 사용자의 사주에 미치는 영향을 함께 고려해 주세요.
                                                
                        <운세 분석 항목>
                        - 총 점수: 100점 만점 기준으로 채점합니다.
                        - 총운 해석: 오늘 하루의 전체적인 흐름을 서술해 주세요.
                        - 각 항목별 운세:
                            - 재물운
                            - 연애운
                            - 건강운
                            - 학업운
                            - 직장운
                                                
                        각 항목별로 아래 규칙에 따라 작성해 주세요:
                                                
                        [작성 규칙]
                        1. 각 항목은 5점 만점 기준으로 점수를 매깁니다 (소수점 한 자리까지도 가능합니다), 그리고 해석(description)을 포함합니다.
                        2. description은 6줄 이상의 서술형 문장으로 작성하며, 다음 내용을 반드시 포함합니다:
                            - 오늘의 운세 흐름
                            - 유의할 점 또는 조언
                            - 사주 내 천간지지의 충, 형, 합 등 관계 기반 해석
                        3. 총 점수는 항목 점수를 기반으로 산정하되, 총운 흐름을 고려하여 약간의 가중치를 적용해 주세요.
                        4. 가장 마지막에는 오늘 하루를 위한 짧고 간단한 조언(25자 이하)을 `dailyMessage` 필드로 전달해 주세요.
                                                
                        최종 결과는 아래 JSON 형식으로 반환해 주세요:
                                                
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
                          "dailyMessage": "..."
                        }
                        """,
                gender,
                manseInfo,
                todayDate
        );

        return new Prompt(content);
    }

    /**
     * GPT 응답을 처리하여 TodayLuckyResponse 객체로 변환
     */
    @Override
    protected TodayFortuneResponse processResponse(ChatResponse response) {
        return ChatResponseParser.parse(response, TodayFortuneResponse.class);
    }
}
