package ru.job4j.oop;

public class Calculator {
    private static int x = 5;

    public int sumAllOperation(int y) {
        return divide(y) + minus(y) + sum(y) + multiply(y);
    }

    public int divide(int y) {
        return y / x;
    }

    public static int minus(int y) {
        return y - x;
    }

    public static int sum(int y) {
        return x + y;
    }

    public int multiply(int a) {
        return x * a;
    }

    public static void main(String[] args) {
        int result = sum(10);
        result = minus(5);
        Calculator calculator = new Calculator();
        result = calculator.multiply(10);
        result = calculator.divide(5);
        result = calculator.sumAllOperation(10);
        System.out.println(result);
    }
}
