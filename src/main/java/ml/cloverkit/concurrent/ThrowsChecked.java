package ml.cloverkit.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class ThrowsChecked {
    class Checked extends Exception {}

    static ThrowsChecked nochecked(ThrowsChecked tc) {
        return tc;
    }

    static ThrowsChecked withchecked(ThrowsChecked tc) throws Checked {
        return tc;
    }

    static void testStream() {
        Stream.of(new ThrowsChecked())
                .map(ThrowsChecked::nochecked)
                .map(tc -> {
                    try {
                        return withchecked(tc);
                    } catch (Checked e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    static void testCompletableFuture() {
        CompletableFuture
                .completedFuture(new ThrowsChecked())
                .thenApply(ThrowsChecked::nochecked)
                .thenApply(tc -> {
                    try {
                        return withchecked(tc);
                    } catch (Checked e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}
