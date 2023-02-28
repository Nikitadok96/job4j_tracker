package ru.job4j;

import java.util.Calendar;

public class Format {
    public static void getGreeting(String name) {
        System.out.println(String.format("Привет! я %s", name));
    }

    public static void getGreetingAndTime(String name) {
        Calendar instance  = Calendar.getInstance();
        System.out.println(String.format("Привет, %s! Текущая дата %tF, Текущее время %tT", name, instance, instance));
    }

    public static void main(String[] args) {
        getGreeting("Елена");
        getGreetingAndTime("Никита");
    }
}
