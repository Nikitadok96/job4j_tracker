package ru.job4j.poly;

public class Bus implements Transport {

    @Override
    public void drive() {
        System.out.println("Автобус едет");
    }

    @Override
    public void passengers(int count) {
        System.out.println("Автобус внестил " + count);
    }

    @Override
    public double refuel(int count) {
        return count * 50;
    }

}
