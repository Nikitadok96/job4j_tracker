package ru.job4j.collection;

import java.util.Comparator;

public class StringCompare implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        for (int i = 0; i < o1.length(); i++) {
            char left = o1.charAt(i);
            char right = o2.charAt(i);
            if (left != right) {
                return Character.compare(left, right);
            }
            if (i == o1.length() - 1) {
                return Integer.compare(o1.length(), o2.length());
            }
        }
        return 0;
    }
}
