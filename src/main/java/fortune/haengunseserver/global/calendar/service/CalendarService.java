package fortune.haengunseserver.global.calendar.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import fortune.haengunseserver.global.calendar.dto.response.LunToSolResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final WebClient kasiClient;

    @Value("${kasi.api-key}")
    private String serviceKey;

    public Mono<LunToSolResponse> convertToSolar(String lunYear, String lunMonth, String lunDay) {

        String xml = kasiClient.get()
                .uri(uriBuilder -> uriBuilder.path("/getSolCalInfo")
                        .queryParam("lunYear", lunYear)
                        .queryParam("lunMonth", lunMonth)
                        .queryParam("lunDay", lunDay)
                        .queryParam("ServiceKey", serviceKey)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return parseResponse(xml);
    }

    private Mono<LunToSolResponse> parseResponse(String xml) {
        try {
            // <item> 태그의 시작과 끝 위치 추출
            int start = xml.indexOf("<item>");
            int end = xml.indexOf("</item>");
            if (start == -1 || end == -1) {
                return Mono.error(new RuntimeException("item 태그 없음"));
            }

            // <item> 태그만 추출
            String itemXml = xml.substring(start, end + "</item>".length());

            // XML → DTO 매핑
            XmlMapper xmlMapper = new XmlMapper();
            LunToSolResponse result = xmlMapper.readValue(itemXml, LunToSolResponse.class);

            return Mono.just(result);
        } catch (Exception e) {
            return Mono.error(new RuntimeException("XML 파싱 실패", e));
        }
    }
}
