package fortune.haengunseserver.domain.fortune.service.agefortune;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fortune.haengunseserver.domain.fortune.dto.response.agefortune.AgeResponseDto;
import fortune.haengunseserver.global.gpt.service.FortuneRequestService;
import fortune.haengunseserver.global.gpt.utils.ChatResponseParser;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AgeFortuneService extends FortuneRequestService<Void, List<AgeResponseDto>> {

    public AgeFortuneService(OpenAiChatModel chatModel) {
        super(chatModel);
    }

    @Override
    public Prompt generatePrompt(Void input) {
        String todayDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);

        String content = String.format("""
                        오늘의 12지신 띠 운세(쥐띠, 소띠, 호랑이띠, 토끼띠, 용띠, 뱀띠, 말띠, 양띠, 원숭이띠, 닭띠, 개띠, 돼지띠)를 봐주세요.
                                        
                        오늘의 날짜는 다음과 같습니다:
                                        
                        %s
                                        
                        JSON 형식은 다음과 같이 보내주세요.
                                        
                        ```json
                        [
                          {
                            "ageName": "띠 이름",
                            "content": {
                              "mainMessage": "띠의 오늘의 운세 메시지",
                              "bestMatch": "가장 궁합이 잘 맞는 띠",
                              "worstMatch": "가장 궁합이 안 맞는 띠"
                            }
                          }
                        ]
                                        
                        운세 메시지는 4줄 이상으로 작성해주세요.
                """,
                todayDate
        );

        return new Prompt(content);
    }

    @Override
    protected List<AgeResponseDto> processResponse(ChatResponse response) {
        return ChatResponseParser.parse(response, new TypeReference<>() {});
    }
}
