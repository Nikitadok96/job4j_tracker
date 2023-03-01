package ru.job4j.inheritance;

public class Airplane implements Vehicle {
    @Override
    public void move() {
        System.out.println("Самолёт летит");
    }
}
