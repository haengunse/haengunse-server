package fortune.haengunseserver.domain.fortune.service.agefortune;

import fortune.haengunseserver.domain.fortune.dto.response.agefortune.AgeResponseDto;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class AgeFortuneStore {

    private static final AtomicReference<List<AgeResponseDto>> ageFortunes =
            new AtomicReference<>(Collections.emptyList());

    public void update(List<AgeResponseDto> newFortunes) {
        ageFortunes.set(Collections.unmodifiableList(newFortunes));
    }

    public List<AgeResponseDto> get() {
        return ageFortunes.get();
    }
}
