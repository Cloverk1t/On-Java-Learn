package ml.cloverkit.concurrent;

import java.util.concurrent.CompletableFuture;

public class CompletableExceptions {
    static CompletableFuture<Breakable> test(String id, int failcount) {
        return CompletableFuture.completedFuture(
                new Breakable(id, failcount))
                .thenApply(Breakable::work)
                .thenApply(Breakable::work)
                .thenApply(Breakable::work)
                .thenApply(Breakable::work);
    }

    public static void main(String[] args) {
        // 异常不会显露出来....
        test("A", 1);
        test("B", 2);
        test("C", 3);
        test("D", 4);
        test("E", 5);
        // ...... 直到你尝试获取结果:
        try {
            test("F", 2).get(); // 或者 join()
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // 测试异常:
        System.out.println(test("G", 2).isCompletedExceptionally());
        // 算做 “成功”
        System.out.println(test("H", 2).isDone());
        // 强制产生异常
        CompletableFuture<Integer> cfi  = new CompletableFuture<>();
        System.out.println("done? " + cfi.isDone());
        cfi.completeExceptionally(new RuntimeException("forced"));
        try {
            cfi.get();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
