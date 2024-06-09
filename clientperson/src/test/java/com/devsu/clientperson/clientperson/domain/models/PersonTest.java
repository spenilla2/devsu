package com.devsu.clientperson.clientperson.domain.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PersonTest {
    @Test
    public void testPersonGettersAndSetters() {
        Person person = new Person();

        person.setId(1L);
        person.setName("Jane Doe");
        person.setGender("Female");
        person.setAge(25);
        person.setIdentification("987654321");
        person.setAddress("456 Avenue");
        person.setPhone("555-5678");

        assertEquals(1L, person.getId());
        assertEquals("Jane Doe", person.getName());
        assertEquals("Female", person.getGender());
        assertEquals(25, person.getAge());
        assertEquals("987654321", person.getIdentification());
        assertEquals("456 Avenue", person.getAddress());
        assertEquals("555-5678", person.getPhone());
    }

    @Test
    public void testPersonAllArgsConstructor() {
        Person person = new Person(1L, "Jane Doe", "Female", 25, "987654321", "456 Avenue", "555-5678");

        assertEquals(1L, person.getId());
        assertEquals("Jane Doe", person.getName());
        assertEquals("Female", person.getGender());
        assertEquals(25, person.getAge());
        assertEquals("987654321", person.getIdentification());
        assertEquals("456 Avenue", person.getAddress());
        assertEquals("555-5678", person.getPhone());
    }

    @Test
    public void testPersonRequiredArgsConstructor() {
        Person person = new Person();

        assertNull(person.getId());
        assertNull(person.getName());
        assertNull(person.getGender());
        assertEquals(0, person.getAge());
        assertNull(person.getIdentification());
        assertNull(person.getAddress());
        assertNull(person.getPhone());
    }
}
