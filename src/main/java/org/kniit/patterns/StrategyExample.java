package org.kniit.patterns;

public class StrategyExample {

    public static void main(String[] args) {
        Calculator calc = new Calculator();

        calc.setStrategy(new AddStrategy());
        System.out.println("5 + 3 = " + calc.calculate(5, 3));

        calc.setStrategy(new SubtractStrategy());
        System.out.println("5 - 3 = " + calc.calculate(5, 3));

        calc.setStrategy(new MultiplyStrategy());
        System.out.println("5 * 3 = " + calc.calculate(5, 3));

        calc.setStrategy(new DivideStrategy());
        System.out.println("10 / 2 = " + calc.calculate(10, 2));
    }
}

interface OperationStrategy {
    double apply(double a, double b);
}
class AddStrategy implements OperationStrategy {
    @Override
    public double apply(double a, double b) {
        return a + b;
    }
}

class SubtractStrategy implements OperationStrategy {
    @Override
    public double apply(double a, double b) {
        return a - b;
    }
}

class MultiplyStrategy implements OperationStrategy {
    @Override
    public double apply(double a, double b) {
        return a * b;
    }
}

class DivideStrategy implements OperationStrategy {
    @Override
    public double apply(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Деление на ноль");
        }
        return a / b;
    }
}
class Calculator {
    private OperationStrategy strategy;

    public void setStrategy(OperationStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculate(double a, double b) {
        return strategy.apply(a, b);
    }
}


