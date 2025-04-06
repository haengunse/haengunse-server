package fortune.haengunseserver.domain.fortune.service.starfortune;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fortune.haengunseserver.domain.fortune.dto.response.starfortune.StarResponseDto;
import fortune.haengunseserver.global.gpt.service.FortuneRequestService;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class StarFortuneService extends FortuneRequestService<Void, List<StarResponseDto>> {


    public StarFortuneService(OpenAiChatModel chatModel, StarFortuneStore starFortuneStore) {
        super(chatModel);
    }

    // 오늘의 별자리 운세 프롬프트
    @Override
    public Prompt generatePrompt(Void input) {
        String todayDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);

        String content = String.format("""
                        오늘의 12별자리 운세를 봐주세요.
                                                
                        오늘의 날짜는 다음과 같습니다:
                                                
                        %s
                                                
                        JSON 형식은 다음과 같이 보내주세요:
                                                
                        {
                          "starName": "별자리 이름",
                          "dateRange": "양력 기준 날짜 범위",
                          "content": {
                            "mainMessage": "오늘의 운세 메시지",
                            "bestMatch": "오늘 가장 잘 맞는 별자리",
                            "worstMatch": "오늘 가장 안 맞는 별자리"
                          }
                        }
                                                
                        결과를 생성할 때는 다음의 규칙에 맞게 생성해 주세요:
                                                
                        1. 반드시 12개의 별자리를 순서대로 포함하고, 물병자리부터 시작해주세요.
                        2. 오늘의 운세 메시지는 4줄 이상의 문장으로 대답해주세요.
                        3. 암별자리라는 말은 사용하지말고, 게자리라고 표현해주세요.
                        """,
                todayDate
        );

        return new Prompt(content);
    }

    @Override
    protected List<StarResponseDto> processResponse(ChatResponse response) {
        try {
            String raw = response.getResult().getOutput().getText();

            String cleanJson = raw
                    .replaceAll("(?s)^```json\\s*", "") // 시작 백틱 제거
                    .replaceAll("\\s*```$", "")         // 종료 백틱 제거
                    .trim();

            ObjectMapper mapper = new ObjectMapper();

            return mapper.readValue(cleanJson, new TypeReference<List<StarResponseDto>>() {});
        } catch (Exception e) {
            throw new RuntimeException("JSON 파싱 실패", e);
        }
    }
}
