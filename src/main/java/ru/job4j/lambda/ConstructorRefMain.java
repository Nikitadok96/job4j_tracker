package ru.job4j.lambda;

public class ConstructorRefMain {
    public static void main(String[] args) {
        FuncInterface func = Model::new;
        Model model = func.function("Nikita");
        System.out.println("Значение имени: " + model.getName());
    }
}
