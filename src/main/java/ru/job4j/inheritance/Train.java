package ru.job4j.inheritance;

public class Train implements Vehicle {
    @Override
    public void move() {
        System.out.println("Поезд едет по ж/д путям.");
    }
}
