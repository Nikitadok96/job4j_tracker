package ru.job4j.ex;

public class FindEl {

    public static int indexOf(String[] value, String key) throws ElementNotFoundException {
        int rsl = -1;
        for (int i = 0; i < value.length; i++) {
            if (key.equals(value[i])) {
                rsl = i;
                break;
            }
        }
        if (rsl < 0) {
            throw new ElementNotFoundException("Element not found");
        }
        return rsl;
    }

    public static void main(String[] args) {
        String[] values = {"Petya", "Vova", "Stanislav", "Denis"};
        int value = 0;
        try {
            value = indexOf(values, "Nikita");
        } catch (ElementNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
