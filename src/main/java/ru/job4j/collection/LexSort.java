package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {
    @Override
    public int compare(String left, String right) {
        String stringsLeft = left.split("\\.")[0];
        String stringsRight = right.split("\\.")[0];
        int range = Math.min(stringsLeft.length(), stringsRight.length());
        for (int i = 0; i < range; i++) {
            if (Character.isDigit(stringsLeft.charAt(i)) && Character.isDigit(stringsRight.charAt(i))) {
                int leftNum = Integer.parseInt(stringsLeft);
                int rightNum = Integer.parseInt(stringsRight);
                if (leftNum != rightNum) {
                    return Integer.compare(leftNum, rightNum);
                }
            }
        }
        return Integer.compare(stringsLeft.length(), stringsRight.length());
    }
}
