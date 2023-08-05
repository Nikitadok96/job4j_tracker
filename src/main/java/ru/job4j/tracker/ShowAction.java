package ru.job4j.tracker;

import java.util.List;

public class ShowAction implements UserAction {
    private final Output output;

    public ShowAction(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Show all Item";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        output.println("=== Show all items ===");
        List<Item> items = tracker.findAll();
        if (items.size() > 0) {
            for (Item item : items) {
                output.println(item);
            }
        } else {
            output.println("Хранилище еще не содержит заявок");
        }
        return true;
    }
}
