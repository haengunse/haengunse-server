package fortune.haengunseserver.domain.fortune.service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public abstract class FortuneStore<T> {

    private final AtomicReference<List<T>> fortunes =
            new AtomicReference<>(Collections.emptyList());

    public void update(List<T> newFortunes) {
        fortunes.set(Collections.unmodifiableList(newFortunes));
    }

    public List<T> get() {
        return fortunes.get();
    }
}