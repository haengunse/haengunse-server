package fortune.haengunseserver.global.gpt.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.model.ChatResponse;

public class ChatResponseParser {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T parse(ChatResponse response, Class<T> className) {
        String raw = response.getResult().getOutput().getText();
        String cleaned = raw.replace("```json", "").replace("```", "").trim();

        try {
            return mapper.readValue(cleaned, className);
        } catch (Exception e) {
            throw new IllegalStateException("GPT 응답 파싱 실패: " + cleaned, e);
        }
    }

    public static <T> T parse(ChatResponse response, TypeReference<T> typeRef) {
        String raw = response.getResult().getOutput().getText();
        String cleaned = raw.replace("```json", "").replace("```", "").trim();

        try {
            return mapper.readValue(cleaned, typeRef);
        } catch (Exception e) {
            throw new IllegalStateException("GPT 응답 파싱 실패: " + cleaned, e);
        }
    }
}

