package ml.cloverkit.enumerations;

import java.util.List;

sealed interface Shape {
    double area();
}

record Circle(double radius) implements Shape {
    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

record Rectangle(double side1, double side2) implements Shape {

    @Override
    public double area() {
        return side1 * side2;
    }
}

public class Shapes {
    static void classify(Shape s) {
        // System.out.println(switch (s) {
        //     case Circle c && c.area() < 100.0 -> "Small Circle: " + c;
        //     case Circle c -> "Large Circle: " + c;
        //     case Rectangle r && r.side1() == r.side2() -> "Square: " + r;
        //     case Rectangle r -> "Rectangle: " + r;
        // });
    }

    public static void main(String[] args) {
        List.of(
                new Circle(5.0),
                new Circle(25.0),
                new Rectangle(12.0, 12.0),
                new Rectangle(12.0, 15.0)
        ).forEach(Shapes::classify);
    }
}
