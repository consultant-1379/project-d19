package com.groupfour.retrospectivebackend.controllers;

import com.groupfour.retrospectivebackend.converter.MemberConverter;
import com.groupfour.retrospectivebackend.converter.TeamConverter;
import com.groupfour.retrospectivebackend.dto.MemberDTO;
import com.groupfour.retrospectivebackend.dto.TeamDTO;
import com.groupfour.retrospectivebackend.models.Member;
import com.groupfour.retrospectivebackend.models.Team;
import com.groupfour.retrospectivebackend.repositories.MemberRepository;
import com.groupfour.retrospectivebackend.repositories.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class TeamControllerTest {
    @Autowired
    TeamController teamController;

    private ArrayList<Team> teams = new ArrayList<>();

    private Team team1;
    private Team team2;

    private Team newTeam;

    private TeamDTO newTeamDTO;

    private Member member1;
    private Member member2;
    private Member member3;
    private Member member4;

    private TeamConverter teamConverter;

    private MemberConverter memberConverter= new MemberConverter();

    private ArrayList<Member> membersTeam1 = new ArrayList<>();
    private ArrayList<Member> membersTeam2 = new ArrayList<>();

    private MemberDTO newMember;
    @MockBean
    private TeamRepository teamRepository;

    @MockBean
    MemberRepository memberRepository;

    @BeforeEach
    public void setup(){
        // initialize variables
        member1 = new Member("M1", "Joe");
        member2 = new Member("M2", "Mary");
        member3 = new Member("M3", "Bob");
        member4 = new Member("M4", "Jane");
        newMember = new MemberDTO("M5", "James");
        membersTeam1.add(member1);
        membersTeam1.add(member2);
        membersTeam2.add(member3);
        membersTeam2.add(member4);

        team1 = new Team("T1", "team 1", membersTeam1);
        team2 = new Team("T2", "team 2", membersTeam2);
        newTeam = new Team("T3", "team 3", null);
        newTeamDTO = new TeamDTO("T3", "team 3", null);
        teams.add(team1);
        teams.add(team2);


        // mock TeamRepository methods used by Team Service
        Mockito.when(teamRepository.findAll()).thenReturn(teams);
        Mockito.when(teamRepository.getTeamById("T1")).thenReturn(team1);
        Mockito.when(teamRepository.getTeamById("T2")).thenReturn(team2);
        //Mockito.when(teamRepository.save(team1)).thenReturn(team1);
        //Mockito.when(teamRepository.save(team2)).thenReturn(team2);
        doAnswer(a -> {
            teams.remove(team1);
            return null;
        }).when(teamRepository).deleteById("T1");
        doAnswer(a -> {
            team1.getMembers().add(memberConverter.convertDtoToEntity(newMember));
            teams.remove(team1);
            teams.add(0, team1);
            return team1;
        }).when(teamRepository).save(team1);
        doAnswer(a -> {
            return team2;
        }).when(teamRepository).save(team2);
        Mockito.when(memberRepository.getMemberById("M3")).thenReturn(member3);

    }

    @Test
    void testGetTeams(){
        ResponseEntity<?>response = teamController.getTeams();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<Team> returnedTeams = (List<Team>) response.getBody();
        assertEquals(2, returnedTeams.size());
        assertEquals(team1, returnedTeams.get(0));
        assertEquals(team2, returnedTeams.get(1));
    }

    @Test
    void testGetTeamById(){
        ResponseEntity<?> response = teamController.getTeamById("T1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Team team = (Team) response.getBody();
        assertEquals("T1", team.getId());
        assertEquals("team 1", team.getName());
        assertEquals(2, team.getMembers().size());
        assertEquals("M1", team.getMembers().get(0).getId());
        assertEquals("M2", team.getMembers().get(1).getId());

        ResponseEntity<?> response2 = teamController.getTeamById("T5");
        assertEquals(HttpStatus.NOT_FOUND, response2.getStatusCode());
        assertEquals("No Team found", response2.getBody());
    }

    @Test
    void testAddTeam(){
        doAnswer(a -> {
            teams.add(newTeam);
            return newTeam;
        }).when(teamRepository).save(any());
        ResponseEntity<?> response = teamController.addTeam(newTeamDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Team team = (Team)response.getBody();
        assertEquals("T3", team.getId());
        assertEquals("team 3", team.getName());
        assertEquals(null, team.getMembers());
    }

    @Test
    void testAddMemberIntoTeam(){
        ArrayList<MemberDTO> m = new ArrayList<>();
        m.add(newMember);
        ResponseEntity<?> response = teamController.addMemberIntoTeam("T1",m);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        m = (ArrayList<MemberDTO>) response.getBody();
        assertEquals(3,team1.getMembers().size());
        assertEquals("M5", team1.getMembers().get(2).getId());
    }

    @Test
    void testDeleteTeamById(){
        ResponseEntity<?> response = teamController.deleteTeamById("T1");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, teams.size());
        assertEquals("T2", teams.get(0).getId());
        response = teamController.deleteTeamById("T5");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteMemberTeam(){
        assertEquals(2, team2.getMembers().size());
        ResponseEntity<?> response = teamController.deleteMemberTeam("T2", "M3");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, team2.getMembers().size());
        assertEquals("M4", team2.getMembers().get(0).getId());
        response = teamController.deleteMemberTeam("T5", "M3");
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
