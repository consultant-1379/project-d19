package com.groupfour.retrospectivebackend.service.impl;

import com.groupfour.retrospectivebackend.converter.MemberConverter;
import com.groupfour.retrospectivebackend.converter.TeamConverter;
import com.groupfour.retrospectivebackend.dto.MemberDTO;
import com.groupfour.retrospectivebackend.dto.TeamDTO;
import com.groupfour.retrospectivebackend.models.Member;
import com.groupfour.retrospectivebackend.models.Team;
import com.groupfour.retrospectivebackend.repositories.MemberRepository;
import com.groupfour.retrospectivebackend.repositories.TeamRepository;
import com.groupfour.retrospectivebackend.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TeamConverter teamConverter;

    @Autowired
    private MemberConverter memberConverter;

    @Override
    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    @Override
    public Team addTeam(TeamDTO team) {
    return teamRepository.save(teamConverter.convertDtoToEntity(team));
    }


    @Override
    public Team getTeamById(String id) {
        return teamRepository.getTeamById(id);
    }

    @Override
    public void deleteTeamById(String id) {
        teamRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        teamRepository.deleteAll();
    }

    @Override
    public boolean addMember(String teamId, List<MemberDTO> member) {
        Team team = teamRepository.getTeamById(teamId);

        for (MemberDTO e : member) {
            team.getMembers().add(memberConverter.convertDtoToEntity(e));

        }
        teamRepository.save(team);
        return true;
    }


    @Override
    public boolean deleteMemberById(String teamId, String memberId) {
        Team team = teamRepository.getTeamById(teamId);

        for (Member e : team.getMembers()) {
            if(e.getId().equals(memberId))
            {

                ArrayList<Member> memberList = team.getMembers();
                memberList.remove(e);
                team.setMembers(memberList);
                teamRepository.save(team);
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<Member> getMembers(String teamId) {
        return teamRepository.getTeamById(teamId).getMembers();
    }



}
