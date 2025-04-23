package fortune.haengunseserver.domain.fortune.today.service.todayfortune;

import fortune.haengunseserver.domain.fortune.today.dto.request.todayfortune.TodayFortuneRequest;
import fortune.haengunseserver.domain.fortune.today.dto.response.todayfortune.TodayFortuneResponse;
import fortune.haengunseserver.global.gpt.service.FortuneRequestService;
import fortune.haengunseserver.global.gpt.utils.ChatResponseParser;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

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
            [사주 정보]
            성별: %s
            만세력: %s
            오늘 날짜: %s

            오늘 일진이 사주에 미치는 영향을 분석하여 아래 항목에 대해 운세를 분석해주세요:

            - 총 점수 (100점 만점)
            - 총운 해석 (하루의 전체적인 흐름)
            - 재물운, 연애운, 건강운, 학업운, 직장운

            [작성 규칙]
            - 각 항목 점수는 5점 만점 (소수점 1자리까지)
            - 각 항목 해석은 4줄 이상의 서술형 문장으로 작성 (오늘 흐름, 주의할 점, 충/형/합 등 사주 기반 해석 포함)
            - 마지막에 25자 이하의 짧은 조언을 'dailyMessage' 필드로 포함

            [응답 형식 (JSON)]으로 응답해 주세요 (설명 없이 JSON만 출력):

            {
                "totalScore": ,
                "generalFortune": "...",
                "wealthFortune": {"score": , "description": "..."},
                "loveFortune": {"score": , "description": "..."},
                "healthFortune": {"score": , "description": "..."},
                "studyFortune": {"score": , "description": "..."},
                "careerFortune": {"score": , "description": "..."},
                "dailyMessage": "..."
            }
            """, gender, manseInfo, todayDate);

        OpenAiChatOptions chatOptions = new OpenAiChatOptions();
        chatOptions.setMaxTokens(800);
        chatOptions.setTemperature(0.7);

        return new Prompt(content, chatOptions);
    }

    /**
     * GPT 응답을 처리하여 TodayLuckyResponse 객체로 변환
     */
    @Override
    protected TodayFortuneResponse processResponse(ChatResponse response) {
        return ChatResponseParser.parse(response, TodayFortuneResponse.class);
    }
}
