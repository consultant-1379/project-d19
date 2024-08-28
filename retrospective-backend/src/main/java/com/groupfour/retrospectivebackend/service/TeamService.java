package com.groupfour.retrospectivebackend.service;

import com.groupfour.retrospectivebackend.dto.MemberDTO;
import com.groupfour.retrospectivebackend.dto.TeamDTO;
import com.groupfour.retrospectivebackend.models.Member;
import com.groupfour.retrospectivebackend.models.Team;

import java.util.ArrayList;
import java.util.List;

public interface TeamService {
    // return all teams
    List<Team> findAll();

    Team addTeam(TeamDTO team);

    // get team with specific id
    Team getTeamById(String id);

    // delete team with given id
    void deleteTeamById(String id);

    // delete all teams
    void deleteAll();

    // add member with given id to a specified team
    boolean addMember(String teamId,List<MemberDTO> members);

    // remove member from team
    boolean deleteMemberById(String teamId, String memberId);

    // return all members in a team
    ArrayList<Member> getMembers(String teamId);




}
