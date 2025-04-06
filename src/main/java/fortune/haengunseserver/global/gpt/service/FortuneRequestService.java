package fortune.haengunseserver.global.gpt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;

@RequiredArgsConstructor
public abstract class FortuneRequestService<T, R> implements FortuneService<T, R> {

    protected final OpenAiChatModel chatModel; // Spring AI GPT 모델

    @Override
    public R getFortune(T input) {
        Prompt prompt = generatePrompt(input);
        ChatResponse response = chatModel.call(prompt);
        return processResponse(response);
    }

    // GPT API 응답을 가공하여 반환합니다.
    protected abstract R processResponse(ChatResponse response);
}

