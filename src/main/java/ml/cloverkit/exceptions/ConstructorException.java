package ml.cloverkit.exceptions;

class CE extends Exception {}

class SecondExcept extends Reporter {
    SecondExcept() throws CE {
        super();
        throw new CE();
    }
}

public class ConstructorException {
    public static void main(String[] args) {
        try (First f = new First();
             SecondExcept s = new SecondExcept();
             Second s2 = new Second()
        ) {
            System.out.println("In Body");;
        } catch (CE e) {
            System.out.println("Caught: " + e);
        }
    }
}