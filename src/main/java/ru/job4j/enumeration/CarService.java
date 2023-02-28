package ru.job4j.enumeration;

public class CarService {
    public static void main(String[] args) {
        Order order = new Order(1, "Lada", Status.IN_WORK);
        System.out.printf("Number: %s | Car: %s | Status: %s | Description: %s%n",
                order.getNumber(), order.getCar(), order.getStatus().getInfo(), order.getStatus().getMessage());
    }
}
