package com.groupfour.retrospectivebackend.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    private Member member;

    @BeforeEach
    void setUp() {
        member = new Member("6ad1", "Timur");
    }

    @Test
    void getId() {
        assertEquals("6ad1", member.getId());
    }

    @Test
    void setId() {
        member.setId("555");
        assertEquals("555", member.getId());
    }

    @Test
    void getName() {
        assertEquals("Timur", member.getName());
    }

    @Test
    void setName() {
        member.setName("Banar");
        assertEquals("Banar", member.getName());
    }
}