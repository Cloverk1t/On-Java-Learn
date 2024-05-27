package ml.cloverkit.enumerations;

import java.util.List;

enum Type { TOXIC, FLAMMABLE, NEUTRAL }

record Level(int percent) {
    Level {
        if (percent < 0 || percent > 100)
            throw new IndexOutOfBoundsException(percent + " percent");
    }
}

record Tank(Type type, Level level) {}

public class Tanks {
     static String check(Tank tank) {
         return switch(tank) {
             // case Tank t && (t.type() == Type.TOXIC && t.level().percent() < 50) -> "Toxic, low: " + t;
             // case Tank t && t.type() == Type.TOXIC -> "Toxic: " + t;
             // case Tank t && t.type() == Type.FLAMMABLE -> "Flammable: " + t;
             // 相当于 default:
             case Tank t -> "Other Thank: " + t;
         };
     }

    public static void main(String[] args) {
        List.of(
                new Tank(Type.TOXIC, new Level(49)),
                new Tank(Type.FLAMMABLE, new Level(52)),
                new Tank(Type.NEUTRAL, new Level(75))
        ).forEach(t -> System.out.println(check(t)));
    }
}
