package ml.cloverkit.enumerations;

import java.util.function.Consumer;

public class CaseNull {
    static void old(String s) {
        if (s == null) {
            System.out.println("null");
            return;
        }
        switch (s) {
            case "XX" -> System.out.println("XX");
            default   -> System.out.println("default");
        }
    }

    static void checkNull(String s) {
        switch (s) {
            case "XX" -> System.out.println("XX");
            case null -> System.out.println("null");
            default   -> System.out.println("default");
        }

        // 也可以用于冒号语法:
        switch (s) {
            case "XX":
                System.out.println("XX");
                break;
            case null:
                System.out.println("null");
                break;
            default:
                System.out.println("default");
        }
    }

    static void defaultOnly(String s) {
        switch (s) {
            case "XX" -> System.out.println("XX");
            default   -> System.out.println("default");
        }
    }

    static void combineNullAndCase(String s) {
        switch (s) {
            // case "XX", null -> System.out.println("XX|null");
            default         -> System.out.println("default");
        }
    }

    static void combineNullAndDefault(String s) {
        switch (s) {
            case "XX"          -> System.out.println("XX");
            case null, default -> System.out.println("both");
        }
    }

    static void test(Consumer<String> cs) {
        cs.accept("XX");
        cs.accept("YY");
        try {
            cs.accept(null);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        test(CaseNull::old);
        test(CaseNull::checkNull);
        test(CaseNull::defaultOnly);
        test(CaseNull::combineNullAndCase);
        test(CaseNull::combineNullAndDefault);
    }
}
