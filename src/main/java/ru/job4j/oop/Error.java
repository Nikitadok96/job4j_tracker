package ru.job4j.oop;

public class Error {
    private boolean active;
    private int status;
    private String message;

    public Error() {

    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void printInfo() {
        System.out.println("Активный " + active);
        System.out.println("Статус " + status);
        System.out.println("Сообщение " + message);
    }

    public static void main(String[] args) {
        Error errorFirst = new Error();
        errorFirst.printInfo();
        Error errorSecond = new Error(false, 500, "Not found");
        errorSecond.printInfo();
        Error errorThird = new Error(true, 200, "Correct");
        errorThird.printInfo();
    }
}
