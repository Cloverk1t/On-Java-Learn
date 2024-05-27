package ml.cloverkit.enumerations;

import java.util.List;

record Person(String name, int age) {}

public class People {
    static String categorize(Person person) {
        return switch (person) {
            // case Person p && p.age() > 40 -> p + " is middle aged";
            // case Person p && (p.name().contains("D") || p.age() == 14) -> p + "D or 14";
            // case Person p && !(p.age() >= 100) -> p + " is not a centenarian";
            case Person p -> p + " Everyone else";
        };
    }

    public static void main(String[] args) {
        List.of(
                new Person("Dorothy", 15),
                new Person("John Bigboote", 42),
                new Person("Morty", 14),
                new Person("Morty Jr.", 1),
                new Person("Jose", 39),
                new Person("Kane", 118)
        ).forEach(p -> System.out.println(categorize(p)));
    }
}
