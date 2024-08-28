package com.groupfour.retrospectivebackend.service;
import com.groupfour.retrospectivebackend.dto.MemberDTO;
import com.groupfour.retrospectivebackend.models.Member;
import java.util.List;

public interface MemberService {
    List<Member> findAll();

    Member getMemberById(String id);

    void deleteMemberById(String id);

    void deleteAll();

    Member addMember(MemberDTO member);

    Member saveOrUpdateMember(MemberDTO member);
}
