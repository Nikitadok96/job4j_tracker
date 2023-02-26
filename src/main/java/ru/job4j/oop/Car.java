package ru.job4j.oop;

public class Car {
    private String brand;
    private String model;

    public Car(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public static TripComputer getTripComputer() {
        Car car = new Car("Марка", "Модель");
        return car.new TripComputer();
    }

    public void startEngine() {
        System.out.println("Двигатель запущен");
    }

    public class Transmission {

        public void accelerate() {
            System.out.println("Ускорение");
        }

    }

    public class Brakes {

        public void brake() {
            System.out.println("Торможение");
        }

    }

    public class TripComputer {
        public String tripData = "Бортовой компьютер";
        private String model = "Модель TripComputer";

        public void getInfo() {
            System.out.println("Марка: " + brand);
            System.out.println("Модель: " + Car.this.model);
            System.out.println("TripComputer модель: " + this.model);
        }

    }
}
