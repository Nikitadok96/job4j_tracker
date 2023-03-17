package ru.job4j.collection;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

class JobTest {
    @Test
    public void whenJobAscByPriority() {
        int rsl = new JobAscByPriority().compare(
                new Job("Nikita", 26),
                new Job("Ivan", 23)
        );
        assertThat(rsl).isGreaterThan(0);
    }

    @Test
    public void whenJobDescByPriority() {
        int rsl = new JobDescByPriority().compare(
                new Job("Smart", 20),
                new Job("Poor", 3)
        );
        assertThat(rsl).isLessThan(0);
    }

    @Test
    public void whenJobAscByName() {
        int rsl = new JobAscByName().compare(
                new Job("AmigoChan", 54),
                new Job("Smart", 22)
        );
        assertThat(rsl).isLessThan(0);
    }

    @Test
    public void whenJobDescByNameLn() {
        int rsl = new JobDescByNameLn().compare(
                new Job("Nikita", 26),
                new Job("Work", 4)
        );
        assertThat(rsl).isLessThan(0);
    }

    @Test
    public void whenCompatorByNameAndPriority() {
        Comparator<Job> comparator = new JobAscByName().thenComparing(new JobAscByPriority());
        int rsl = comparator.compare(
                new Job("Miss", 14),
                new Job("Miss", 23)
        );
        assertThat(rsl).isLessThan(0);
    }

    @Test
    public void whenCompatorByPriorityAndName() {
        Comparator<Job> comparator = new JobAscByPriority().thenComparing(new JobDescByNameLn());
        int rsl = comparator.compare(
                new Job("Amigo", 50),
                new Job("BrunoBanani", 50)
        );
        assertThat(rsl).isGreaterThan(0);
    }
}