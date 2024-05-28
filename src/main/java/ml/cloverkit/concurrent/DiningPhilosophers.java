package ml.cloverkit.concurrent;

import onjava.Nap;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class DiningPhilosophers {
    private final StickHolder[] sticks;
    private final Philosopher[] philosophers;

    public DiningPhilosophers(int n) {
        sticks = new StickHolder[n];
        Arrays.setAll(sticks, i -> new StickHolder());

        philosophers = new Philosopher[n];
        Arrays.setAll(philosophers, i -> new Philosopher(i, sticks[i], sticks[(i + 1) % n]));
        // 通过颠倒筷子的顺序来修正死锁:
        philosophers[1] = new Philosopher(0, sticks[0], sticks[1]);

        Arrays.stream(philosophers)
                .forEach(CompletableFuture::runAsync);
    }

    public static void main(String[] args) {
        // 立即返回
        new DiningPhilosophers(5);
        // 保持 main() 不退出:
        new Nap(10, "Shutdown");
    }
}
