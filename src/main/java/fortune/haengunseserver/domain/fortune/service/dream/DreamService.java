package fortune.haengunseserver.domain.fortune.service.dream;

import com.fasterxml.jackson.databind.ObjectMapper;
import fortune.haengunseserver.domain.fortune.dto.response.dream.DreamResponse;
import fortune.haengunseserver.global.gpt.service.FortuneRequestService;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DreamService extends FortuneRequestService<List<String>, DreamResponse> {

    public DreamService(OpenAiChatModel chatModel) {
        super(chatModel);
    }

    @Override
    public Prompt generatePrompt(List<String> questions) {

        // 질문이 비어 있는 경우 / 질문 개수가 초과한 경우 -> 예외처리

        StringBuilder contentBuilder = new StringBuilder();
        contentBuilder.append("다음을 보고 꿈을 해석해주세요:\n\n");

        if (questions.size() == 1) {
            contentBuilder.append(String.format("%s\n\n", questions.get(0)));
        } else {
            contentBuilder.append(String.format("기존 꿈:\n%s", questions.get(0)));
            if (questions.size() == 2) {
                contentBuilder.append(String.format("\n\n추가 꿈:\n%s\n\n", questions.get(1)));
            } else if(questions.size() == 3) {
                contentBuilder.append(String.format(", \n%s\n\n 추가 꿈 :\n%s\n\n", questions.get(1), questions.get(2)));
            }
        }

        contentBuilder.append("""
        [답변 규칙]
        - 존댓말로 답변할 것
        - 종합해석만 대답할 것 (너무 길지 않게 부탁해요)
        - 모두 하나의 꿈으로 생각하되, 추가 꿈에 대한 답변을 해주세요.
        - "interpretation" : "꿈해석" 형식으로 답해주세요.
        """);

        System.out.println(contentBuilder.toString());

        return new Prompt(contentBuilder.toString());
    }


    @Override
    protected DreamResponse processResponse(ChatResponse response) {
        String raw = response.getResult().getOutput().getText();

        // JSON 태그 제거: ```json\n{...}\n```
        String cleanJson = raw
                .replaceAll("(?s)^```json\\s*", "") // 시작 백틱 제거
                .replaceAll("\\s*```$", "")         // 종료 백틱 제거
                .trim();

        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(cleanJson, DreamResponse.class);
        } catch (IOException e) {
            throw new RuntimeException("GPT 응답 파싱 실패", e);
        }
    }
}
