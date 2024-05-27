package ml.cloverkit.enumerations;

import java.util.List;

record XX() {}

public class ObjectMatch {
    static String match(Object o) {
        return switch (o) {
            case Dog d -> "Walk the dog";
            case Fish f -> "Change the fish water";
            case Pet sp -> "Not dog or fish";
            case String s -> "String " + s;
            case Integer i -> "Integer " + i;
            // case null, XX xx -> "null or XX: " + xx;
            default -> "Something else";
        };
    }

    public static void main(String[] args) {
        List.of(new Dog(), new Fish(), new Pet(), "Oscar", Integer.valueOf(12), Double.valueOf("47.74"),
                new String[] { "to", "the", "point" }, new XX())
                .forEach(p -> System.out.println(match(p)));
    }
}
