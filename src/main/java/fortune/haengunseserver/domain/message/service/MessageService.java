package fortune.haengunseserver.domain.message.service;

import fortune.haengunseserver.domain.message.dto.ItemResponseDto;
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
        List<String> questions = loadCsv("/data/question_list.csv", true);

        return questions.isEmpty() ? new MessageResponseDto() :
                new MessageResponseDto(getRandomItem(questions));
    }

    public MessageResponseDto getFortuneCookie() {
        List<String> fortunes = loadCsv("/data/fortune_cookie.csv", false);

        return fortunes.isEmpty() ? new MessageResponseDto() :
                new MessageResponseDto(getRandomItem(fortunes));
    }

    public ItemResponseDto getLuckyItem() {
        List<String> luckyColors = loadCsv("/data/lucky_colors.csv", false);
        List<String> luckyPlaces = loadCsv("/data/lucky_places.csv", false);
        List<String> luckyObject = loadCsv("/data/lucky_items.csv", false);
        List<String> luckyMeals = loadCsv("/data/lucky_meals.csv", false);

        String color = getRandomItem(luckyColors);
        String place = getRandomItem(luckyPlaces);
        String object = getRandomItem(luckyObject);
        String meal = getRandomItem(luckyMeals);
        String number = String.valueOf(new Random().nextInt(100) + 1);

        return new ItemResponseDto(color, place, number, object, meal);
    }

    // csv 파일 읽기
    private List<String> loadCsv(String filePath, boolean stripQuotes) {
        List<String> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new ClassPathResource(filePath).getInputStream(), StandardCharsets.UTF_8))) {

            String line;
            boolean isFirstLine = true; // 헤더는 안 읽음

            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                result.add(stripQuotes ? line.replaceAll("^\"|\"$", "") : line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    // csv 파일에서 랜덤으로 뽑아오기
    private String getRandomItem(List<String> list) {
        return list.get(new Random().nextInt(list.size()));
    }
}
