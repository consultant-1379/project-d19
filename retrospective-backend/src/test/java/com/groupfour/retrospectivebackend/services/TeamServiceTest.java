package com.groupfour.retrospectivebackend.services;

import com.groupfour.retrospectivebackend.converter.MemberConverter;
import com.groupfour.retrospectivebackend.dto.MemberDTO;
import com.groupfour.retrospectivebackend.models.Member;
import com.groupfour.retrospectivebackend.models.Team;
import com.groupfour.retrospectivebackend.repositories.TeamRepository;
import com.groupfour.retrospectivebackend.service.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class TeamServiceTest {

    @Autowired
    private TeamService tService;

    @Autowired
    private MemberConverter memberConverter;
    @MockBean
    private TeamRepository teamRepository;

    private final List<Team> teams = new ArrayList<>();
    MemberDTO m1 = new MemberDTO("abc123", "John Doe");
    MemberDTO m2 = new MemberDTO("bcd234", "Peter File");
    MemberDTO m3 = new MemberDTO("cde345", "Jane Doe");
    MemberDTO m4 = new MemberDTO("def456", "Bob Smith");

    ArrayList<MemberDTO> membersT1 = new ArrayList<>();


    ArrayList<MemberDTO> membersT2 = new ArrayList<>();

    ArrayList<Member> mT1 = new ArrayList<>();
    ArrayList<Member> mT2 = new ArrayList<>();


    Team team1;
    Team team2;
    @BeforeEach
    public void setup(){
        membersT1.add(m1);
        membersT1.add(m2);


        membersT2.add(m3);
        membersT2.add(m4);
        mT1.add(memberConverter.convertDtoToEntity(m1));
        mT1.add(memberConverter.convertDtoToEntity(m2));
        mT2.add(memberConverter.convertDtoToEntity(m3));
        mT2.add(memberConverter.convertDtoToEntity(m4));
        team1 = new Team("T001", "Team ENIAC", mT1);
        team2 = new Team("T002", "Team Apollo", mT2);
        teams.add(team1);
        teams.add(team2);

        Mockito.when(teamRepository.findAll()).thenReturn(teams);
        Mockito.when(teamRepository.getTeamById("T001")).thenReturn(team1);
        Mockito.when(teamRepository.getTeamById("T002")).thenReturn(team2);
        Mockito.when(teamRepository.save(team1)).thenReturn(team1);
        Mockito.when(teamRepository.save(team2)).thenReturn(team2);
        doAnswer(a -> {
            teams.remove(team1);
            return null;
        }).when(teamRepository).deleteById("T001");
    }

    @Test
    public void testFindById(){
        Team team = tService.getTeamById("T001");
        assertEquals("T001", team.getId());
        assertEquals("Team ENIAC", team.getName());
        assertEquals(2, team.getMembers().size());
    }

    @Test
    public void testFindAll(){
        List<Team> teams = tService.findAll();
        assertNotNull(teams);
        assertEquals(2, teams.size());
        assertEquals("T001", teams.get(0).getId());
        assertEquals("T002", teams.get(1).getId());
    }

    @Test
    public void testGetMembers(){
        assertEquals(2, tService.getTeamById("T001").getMembers().size());
    }

    @Test
    public void testDeleteTeam(){
        assertEquals(2, tService.findAll().size());
        tService.deleteTeamById("T001");
        verify(teamRepository).deleteById("T001");
        assertEquals(1, tService.findAll().size());
    }



    @Test
    public void testAddMember(){
        // add m1 to team 2

        tService.addMember("T002", membersT2);
        assertEquals(4, tService.getTeamById("T002").getMembers().size());
    }

    /*@Test void testDeleteMember(){
        tService.deleteMemberById("T002", "abc123");
        assertEquals(2, tService.getTeamById("T002").getMembers().size());
    }*/
    /*@Test
    public void testDeleteAll(){
        teamService.deleteAll();
        assertEquals(0, teamService.findAll().size());
    }*/
}
