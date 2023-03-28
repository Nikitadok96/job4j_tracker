package ru.job4j.stream;

import ru.job4j.search.Person;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductLabel {
    public List<String> generateLabels(List<Product> products) {
        return products.stream()
                .flatMap(Stream::ofNullable)
                .filter(p -> p.getStandard() - p.getActual() <= 3)
                .filter(p -> p.getStandard() - p.getActual() >= 0)
                .map(p -> new Label(p.getName(), p.getPrice() / 2))
                .map(Label::toString)
                .collect(Collectors.toList());
    }
}
