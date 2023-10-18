package ml.cloverkit.concurrent;

public class CatchCompletableExceptions {
    static void handleException(int failcount) {
        // 只在有异常时裁掉用该函数，必须生成和输入相同的类型:
        CompletableExceptions
                .test("exceptionally", failcount)
                .exceptionally((ex) -> {
                    if (ex == null)
                        System.out.println("I don't get it yet");
                    return new Breakable(ex.getMessage(), 0);
                })
                .thenAccept(str -> System.out.println("result: " + str));

        // 创建新结果(恢复):
        CompletableExceptions
                .test("handle", failcount)
                .handle((result, fail) -> {
                    if (fail != null)
                        return "Failure recovery object";
                    else
                        return result + " is good";
                })
                .thenAccept(str -> System.out.println("result: " + str));

        // 做了一些逻辑处理，但仍然向下传递相同的结果:
        CompletableExceptions
                .test("whenComplete", failcount)
                .whenComplete((result, fail) -> {
                    if (fail != null)
                        System.out.println("It failed");
                    else
                        System.out.println("result" + " OK");
                })
                .thenAccept(r -> System.out.println("result: " + r));
    }

    public static void main(String[] args) {
        System.out.println("**** Failure Mode *****");
        handleException(2);
        System.out.println("**** Success Mode *****");
        handleException(0);
    }
}
