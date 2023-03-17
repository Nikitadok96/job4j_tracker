package ru.job4j.hmap;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        double value = 0;
        for (Pupil pupil : pupils) {
            double pupilValue = 0;
            for (Subject subject : pupil.subjects()) {
                pupilValue += subject.score();
            }
            value += pupilValue / pupil.subjects().size();
        }
        return value / pupils.size();
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> labelList = new ArrayList<>();
        for (Pupil pupil : pupils) {
            double value = 0;
            for (Subject subject : pupil.subjects()) {
                value += subject.score();
            }
            value /= pupil.subjects().size();
            labelList.add(new Label(pupil.name(), value));
        }
        return labelList;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        Map<String, Integer> map = new LinkedHashMap<>();
        List<Label> labelList = new ArrayList<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                Integer value = subject.score();
                map.put(subject.name(), value + map.getOrDefault(subject.name(), 0));
            }
        }
        for (String str : map.keySet()) {
            Label label = new Label(str, (double) map.get(str) / pupils.size());
            labelList.add(label);
        }
        return labelList;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> labelList = new ArrayList<>();
        for (Pupil pupil : pupils) {
            int value = 0;
            for (Subject subject : pupil.subjects()) {
                value += subject.score();
            }
            labelList.add(new Label(pupil.name(), value));
        }
        labelList.sort(Comparator.naturalOrder());
        return labelList.get(labelList.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        Map<String, Integer> map = new LinkedHashMap<>();
        List<Label> labelList = new ArrayList<>();
        for (Pupil pupil : pupils) {
            for (Subject subject : pupil.subjects()) {
                Integer value = subject.score();
                map.put(subject.name(), value + map.getOrDefault(subject.name(), 0));
            }
        }
        for (String str : map.keySet()) {
            Label label = new Label(str, map.get(str));
            labelList.add(label);
        }
        labelList.sort(Comparator.naturalOrder());
        return labelList.get(labelList.size() - 1);
    }
}
