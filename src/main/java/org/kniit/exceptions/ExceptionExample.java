package org.kniit.exceptions;

public class ExceptionExample {
    public static int sqrt(int number) throws NegativeNumberException {
        if (number < 0) {
            throw new NegativeNumberException("Число не должно быть отрицательным: " + number);
        }
        return (int)Math.sqrt(number);
    }

    public static void main(String[] args) {
        try {
            int result = sqrt(-25);
            System.out.println("Корень равен: " + result);
        } catch (NegativeNumberException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            System.out.println("Завершение работы программы.");
        }
    }
}

class NegativeNumberException extends Exception {
    public NegativeNumberException(String message) {
        super(message);
    }
}
