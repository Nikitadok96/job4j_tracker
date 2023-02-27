package ru.job4j.pojo;

import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student student = new Student();
        student.setFullName("Nikita Vozhegov");
        student.setGroup("Java");
        student.setDateOfReceipt(new Date());
        System.out.println(student.getFullName() + " enrolled " + student.getDateOfReceipt() + " to the group "
                + student.getGroup());
    }
}
