package ru.job4j.poly;

public class MemStore implements Store {
    private String[] mem = new String[100];
    private int size = 0;

    @Override
    public void save(String data) {
        mem[size++] = data;
    }

    @Override
    public String read() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(mem[i]);
        }
        return stringBuilder.toString();
    }
}
