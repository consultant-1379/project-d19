package com.groupfour.retrospectivebackend.converter;

import com.groupfour.retrospectivebackend.converter.ItemConverter;
import com.groupfour.retrospectivebackend.converter.TeamConverter;
import com.groupfour.retrospectivebackend.dto.ItemDTO;
import com.groupfour.retrospectivebackend.dto.TeamDTO;
import com.groupfour.retrospectivebackend.models.Item;
import com.groupfour.retrospectivebackend.models.Member;
import com.groupfour.retrospectivebackend.models.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeamConverterTest {
    private TeamConverter teamConverter = new TeamConverter();

    private TeamDTO teamDTO;

    private Team team;

    Member member;
    ArrayList<Member> members = new ArrayList<>();

    @BeforeEach
    public void setup(){
        member = new Member("M1", "Bob");
        members.add(member);
        teamDTO = new TeamDTO("T1", "team 1", members);
    }

    @Test
    void testConverter(){
        team = teamConverter.convertDtoToEntity(teamDTO);
        assertEquals("T1", team.getId());
        assertEquals("team 1", team.getName());
        assertEquals(1, team.getMembers().size());
        assertEquals("M1", team.getMembers().get(0).getId());
        assertEquals("Bob", team.getMembers().get(0).getName());
    }
}
