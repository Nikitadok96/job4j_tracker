package ru.job4j.io;

import java.util.Random;

public class MagicBall {
    public static void main(String[] args) {
        System.out.print("Ты завтра пойдёшь на работу? ");
        int answer = new Random().nextInt(3);
        switch (answer) {
            case 1 -> System.out.println("Да");
            case 2 -> System.out.println("Нет");
            default -> System.out.println("Может быть");
        }
    }
}
