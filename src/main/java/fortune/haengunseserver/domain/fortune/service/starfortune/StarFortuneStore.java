package fortune.haengunseserver.domain.fortune.service.starfortune;

import fortune.haengunseserver.domain.fortune.dto.response.starfortune.StarResponseDto;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class StarFortuneStore {

    private static final AtomicReference<List<StarResponseDto>> starFortunes =
            new AtomicReference<>(Collections.emptyList());

    public void update(List<StarResponseDto> newFortunes) {
        starFortunes.set(Collections.unmodifiableList(newFortunes));
    }

    public List<StarResponseDto> get() {
        return starFortunes.get();
    }
}
