package ru.job4j.inheritance;

public class Bus implements Vehicle {
    @Override
    public void move() {
        System.out.println("Автобус едет по дороге");
    }
}
