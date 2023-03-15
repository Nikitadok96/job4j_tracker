package ru.job4j.collection;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PassportOfficeTest {
    @Test
    public void whenTestAddMethod() {
        Citizen citizen = new Citizen("2f44a", "Petr Arsentev");
        PassportOffice office = new PassportOffice();
        office.add(citizen);
        assertThat(office.get(citizen.getPassport())).isEqualTo(citizen);
    }

    @Test
    public void whenTestAddMethodFalse() {
        Citizen citizenOfficial = new Citizen("2f44a", "Petr Arsentev");
        Citizen citizenNew = new Citizen("2f44a", "Nikita Vozhegov");
        PassportOffice office = new PassportOffice();
        office.add(citizenOfficial);
        assertThat(office.add(citizenNew)).isFalse();
    }
}