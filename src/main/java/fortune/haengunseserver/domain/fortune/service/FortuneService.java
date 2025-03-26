package fortune.haengunseserver.domain.fortune.service;

import org.springframework.ai.chat.prompt.Prompt;

public interface FortuneService<T, R> {

    // 입력 데이터를 기반으로 GPT 프롬프트를 생성
    Prompt generatePrompt(T input);

    // GPT API를 호출하여 운세를 조회
    R getFortune(T input);
}
