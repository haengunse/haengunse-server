package fortune.haengunseserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    // 한국천문연구원 URL
    @Bean
    public WebClient kasiClient(WebClient.Builder builder) {
        return builder.baseUrl("http://apis.data.go.kr/B090041/openapi/service/LrsrCldInfoService")
                .build();
    }
}
