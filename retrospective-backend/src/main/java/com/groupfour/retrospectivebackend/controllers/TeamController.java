package com.groupfour.retrospectivebackend.controllers;


import com.groupfour.retrospectivebackend.converter.MemberConverter;
import com.groupfour.retrospectivebackend.converter.TeamConverter;
import com.groupfour.retrospectivebackend.dto.MemberDTO;
import com.groupfour.retrospectivebackend.dto.TeamDTO;
import com.groupfour.retrospectivebackend.models.Member;
import com.groupfour.retrospectivebackend.models.Team;
import com.groupfour.retrospectivebackend.service.MemberService;
import com.groupfour.retrospectivebackend.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamservice;

    @Autowired
    private MemberConverter memberConverter;

    @Autowired
    private TeamConverter teamConverter;

    @Autowired
    private MemberService memberservice;

    @GetMapping
    public ResponseEntity<?> getTeams()
    {
        List<Team> teams = teamservice.findAll();
        if(teams.size()<1)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Team found");

        }
            return new ResponseEntity<>(teams, HttpStatus.OK);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getTeamById(@PathVariable String id) {
            Team team = teamservice.getTeamById(id);
            if(team!=null) {
                return new ResponseEntity<>(team, HttpStatus.OK);
            }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Team found");
    }

    @PostMapping
    public ResponseEntity<?> addTeam(@RequestBody TeamDTO team) {
            Team newTeam = teamservice.addTeam(team);
            if (newTeam != null) {
                return new ResponseEntity<>(newTeam, HttpStatus.OK);
            }
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Failed to save member");
    }


    @PostMapping(value = "/{id}")
    public  ResponseEntity<?> addMemberIntoTeam(@PathVariable String id, @RequestBody List<MemberDTO> members)
    {
            Team team = teamservice.getTeamById(id);
            ArrayList<MemberDTO> verifiedMembers = new ArrayList<>();
            if( team != null ) {
                members.forEach(m -> {
                    if (memberservice.getMemberById(m.getId()) != null) {
                        verifiedMembers.add(m);
                    }
                });
                teamservice.addMember(id, verifiedMembers);
                return new ResponseEntity<>(verifiedMembers, HttpStatus.OK);
            }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Something went wrong");
    }

    @DeleteMapping(value = "/{id}")
    public  ResponseEntity<?> deleteTeamById(@PathVariable String id)
    {
            Team team = teamservice.getTeamById(id);
            if(team != null) {
                teamservice.deleteTeamById(id);
                return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted team: " + team.getName());
            }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Something went wrong");
    }


    @DeleteMapping(value = "/{id}/{memberId}")
    public ResponseEntity<?> deleteMemberTeam(@PathVariable String id, @PathVariable String memberId) {
            Team team = teamservice.getTeamById(id);
            Member member = memberservice.getMemberById(memberId);

            if(member != null && team != null ) {
                teamservice.deleteMemberById(id, memberId);
                return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted member: " + member.getName() + " from Team " + team.getName());
            }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Something went wrong");
    }
}







