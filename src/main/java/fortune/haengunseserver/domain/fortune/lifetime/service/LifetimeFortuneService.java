package fortune.haengunseserver.domain.fortune.lifetime.service;

import fortune.haengunseserver.domain.fortune.common.dto.request.SajuRequest;
import fortune.haengunseserver.domain.fortune.common.util.GenderConverter;
import fortune.haengunseserver.domain.fortune.common.util.ManseInfoFormatter;
import fortune.haengunseserver.domain.fortune.lifetime.dto.response.LifetimeSajuResponse;
import fortune.haengunseserver.global.gpt.service.FortuneRequestService;
import fortune.haengunseserver.global.gpt.util.ChatResponseParser;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;

@Service
public class LifetimeFortuneService extends FortuneRequestService<SajuRequest, LifetimeSajuResponse> {

    public LifetimeFortuneService(OpenAiChatModel chatModel) {
        super(chatModel);
    }

    @Override
    public Prompt generatePrompt(SajuRequest input) {
        String manseInfo = ManseInfoFormatter.format(input.getManseInfo());
        String gender = GenderConverter.toKorean(input.getGender());

        String content = String.format("""
                [사주 정보]
                성별: %s
                만세력: 
                %s
                            
                다음 네 가지 항목에 대해 각 항목당 5문장 이상으로 분석해 주세요. 
                * 사주 구성 요소(일간, 지장간, 지지 충극합, 오행 비율, 십성 등)를 고려하여 전문적이고 구체적인 설명을 포함해 주세요.

                1. 전체 운세 흐름 요약 : 인생 전반의 운세 흐름을 종합적으로 분석해 주세요.
                2. 성격 및 성향 분석: 일간 중심으로 성격을 해석하되, 월지·시주·연지의 영향을 함께 고려해주세요.
                3. 오행 분석: 오행(목화토금수)의 강약과 균형 여부, 상생/상극 관계, 기운의 흐름을 분석해 주세요.
                4. 십성 분석: 일간 기준 십성(비견, 겁재, 식신, 상관, 편재, 정재, 편관, 정관, 편인, 정인)의 분포와 의미를 사주 구성에 따라 구체적으로 설명해 주세요.

                응답은 아래 JSON 형식으로 출력해주세요. (설명 없이 JSON만 출력)
                {
                  "summary": "...",
                  "personality": "...",
                  "fiveElements": "...",
                  "tenGods": "..."
                }
            """, gender, manseInfo);

        OpenAiChatOptions chatOptions = new OpenAiChatOptions();
        chatOptions.setMaxTokens(800);
        chatOptions.setTemperature(0.7);

        return new Prompt(content, chatOptions);
    }

    @Override
    protected LifetimeSajuResponse processResponse(ChatResponse response) {
        return ChatResponseParser.parse(response, LifetimeSajuResponse.class);
    }
}
