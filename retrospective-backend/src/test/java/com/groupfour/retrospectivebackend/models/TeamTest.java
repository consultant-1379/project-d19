package com.groupfour.retrospectivebackend.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {

    private Team team;
    private ArrayList<Member> memberList;

    @BeforeEach
    void setUp() {
        memberList = new ArrayList<>();

        memberList.add(new Member("6ad1", "Timur"));
        memberList.add(new Member("6ad2", "Sultanov"));

        team = new Team("678a1", "wolves", memberList);
    }

    @Test
    void getId() {
        assertEquals("678a1", team.getId());
    }

    @Test
    void setId() {
        team.setId("a11");
        assertEquals("a11", team.getId());
    }

    @Test
    void getName() {
        assertEquals("wolves", team.getName());
    }

    @Test
    void setName() {
        team.setName("Chris");
        assertEquals("Chris", team.getName());
    }

    @Test
    void getMembers() {
        assertEquals("Sultanov", team.getMembers().get(1).getName());
    }

    @Test
    void setMembers() {
        team.getMembers().get(1).setName("Tester");
        assertEquals("Tester", team.getMembers().get(1).getName());
    }
}