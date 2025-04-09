package fortune.haengunseserver.domain.message.service;

import fortune.haengunseserver.domain.message.dto.MessageResponseDto;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MessageService {

    public MessageResponseDto getRandomQuestion() {
        List<String> questions = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new ClassPathResource("/data/question_list.csv").getInputStream(), StandardCharsets.UTF_8))) {

            String line;
            boolean isFirstLine = true;
            while((line = br.readLine()) != null) {
                if(isFirstLine) {
                    isFirstLine = false; // 헤더는 건너뜀
                    continue;
                }
                questions.add(line.replaceAll("^\"|\"$", "")); // 따옴표 제거
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (questions.isEmpty()) {
            return new MessageResponseDto();
        }

        Random random = new Random();
        String randomQuestion = questions.get(random.nextInt(questions.size()));

        return new MessageResponseDto(randomQuestion);
    }
}
