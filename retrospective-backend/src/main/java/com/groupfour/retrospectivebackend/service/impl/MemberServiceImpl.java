package com.groupfour.retrospectivebackend.service.impl;


import com.groupfour.retrospectivebackend.converter.MemberConverter;
import com.groupfour.retrospectivebackend.dto.MemberDTO;
import com.groupfour.retrospectivebackend.models.Member;
import com.groupfour.retrospectivebackend.repositories.MemberRepository;
import com.groupfour.retrospectivebackend.service.MemberService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MemberConverter memberConverter;

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Member getMemberById(String id) {
        return memberRepository.getMemberById(id);
    }

    @Override
    public void deleteMemberById(String id) {
        memberRepository.deleteById(id);

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Member addMember(MemberDTO member) {
       return memberRepository.save(memberConverter.convertDtoToEntity(member));
    }

    @Override
    public Member saveOrUpdateMember(MemberDTO member) {
        return memberRepository.save(memberConverter.convertDtoToEntity(member));
    }

}
