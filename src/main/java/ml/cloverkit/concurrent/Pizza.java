package ml.cloverkit.concurrent;

import onjava.Nap;

public class Pizza {
    public enum Step {
        DOUGH(4), ROLLED(1), SAUCED(1), CHEESED(2),
        TOPPED(5), BACKED(2), SLICED(1), BOXED(0);
        ;
        // 需要用来到达下一步
        int effort;

        Step(int effort) {
            this.effort = effort;
        }

        Step forward() {
            if (equals(BOXED)) return BOXED;
            new Nap(effort * 0.1);
            return values()[ordinal() + 1];
        }
    }

    private Step step = Step.DOUGH;
    private final int id;

    public Pizza(int id) {
        this.id = id;
    }

    public Pizza next() {
         step = step.forward();
        System.out.println("Pizza " + id + ": " + step);
        return this;
    }

    public Pizza next(Step previousStep) {
        if (!step.equals(previousStep)) {
            throw new IllegalStateException("Expected " + previousStep + " but found " + step);
        }
        return next();
    }

    public Pizza roll() {
        return next(Step.DOUGH);
    }

    public Pizza sauce() {
        return next(Step.ROLLED);
    }

    public Pizza cheese() {
        return next(Step.SAUCED);
    }

    public Pizza toppings() {
        return next(Step.CHEESED);
    }

    public Pizza bake() {
        return next(Step.TOPPED);
    }

    public Pizza slice() {
        return next(Step.BACKED);
    }

    public Pizza box() {
        return next(Step.SLICED);
    }

    public boolean complete() {
        return step.equals(Step.BOXED);
    }

    @Override
    public String toString() {
        return "Pizza" + id + ": " + ((step.equals(Step.BOXED)) ? "complete" : step);
    }
}
