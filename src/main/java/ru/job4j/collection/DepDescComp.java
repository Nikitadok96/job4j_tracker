package ru.job4j.collection;

import java.util.Comparator;
import java.util.List;

public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        String[] leftArray = left.split("/");
        String[] rightArray = right.split("/");
        if (rightArray[0].compareTo(leftArray[0]) != 0) {
            return rightArray[0].compareTo(leftArray[0]);
        } else {
            int range = Math.min(leftArray.length, rightArray.length);
            for (int i = 1; i < range; i++) {
                int rsl = leftArray[i].compareTo(rightArray[i]);
                if (rsl != 0) {
                    return rsl;
                }
            }
        }
        return Integer.compare(leftArray.length, rightArray.length);
    }
}
