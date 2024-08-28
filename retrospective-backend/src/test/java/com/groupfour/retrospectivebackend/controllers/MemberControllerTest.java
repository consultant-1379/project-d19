package com.groupfour.retrospectivebackend.controllers;

import com.groupfour.retrospectivebackend.converter.MemberConverter;
import com.groupfour.retrospectivebackend.dto.MemberDTO;
import com.groupfour.retrospectivebackend.models.Member;
import com.groupfour.retrospectivebackend.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class MemberControllerTest {

    @Autowired
    MemberController memberController;

    @MockBean
    MemberRepository memberRepository;

    @Autowired
    private MemberConverter memberConverter;

    private Member member1;
    private Member member2;
    private Member member3;

    private Member m= new Member("999", "Sean");

    private MemberDTO newMember;

    private ArrayList<Member> members;

    @BeforeEach
    void setUp() {
        member1 = new Member("1", "Joe");
        member2 = new Member("2", "Mary");
        member3 = new Member("3", "Bob");
        //m = new Member("999", "Sean");
        newMember = new MemberDTO("4", "Robert");

        members = new ArrayList<>();
        members.add(member1);
        members.add(member2);
        members.add(member3);

        when(memberRepository.findAll()).thenReturn(members);
        when(memberRepository.getMemberById("1")).thenReturn(member1);
        when(memberRepository.getMemberById("2")).thenReturn(member2);
        when(memberRepository.getMemberById("3")).thenReturn(member3);

        doAnswer(a -> {
            members.remove(member1);
            return null;
        }).when(memberRepository).deleteById("1");

        doAnswer(a -> {
            members.get(0).setName(newMember.getName());
            return null;
        }).when(memberRepository).save(member1);
    }

    @Test
    void getMembers() {
        ResponseEntity<?> response = memberController.getMembers();
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<Member> returnedMembers = (List<Member>) response.getBody();
        assert returnedMembers != null;

        assertEquals(3, returnedMembers.size());
        assertEquals(member1, returnedMembers.get(0));
        assertEquals(member2, returnedMembers.get(1));
    }

    @Test
    void getMemberById() {
        ResponseEntity<?> response = memberController.getMemberById("2");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(member2, response.getBody());

        ResponseEntity<?> res = memberController.getMemberById("9999");
        assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());
    }

    @Test
    void addMember() {
        MemberDTO nm = new MemberDTO("999", "Sean");
        ResponseEntity<?> response = memberController.addMember(nm);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        MemberDTO m = (MemberDTO) response.getBody();
        assertEquals(nm.getId(), m.getId());

        ResponseEntity<?> res = memberController.addMember(null);
        assertEquals(HttpStatus.EXPECTATION_FAILED, res.getStatusCode());
    }

    @Test
    void modifyMemberById() {
        newMember = new MemberDTO("1", "Timur");
        ResponseEntity<?> response = memberController.modifyMemberById("1", newMember);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(newMember, response.getBody());

        ResponseEntity<?> res = memberController.modifyMemberById("555", newMember);
        assertEquals(HttpStatus.EXPECTATION_FAILED, res.getStatusCode());
    }

    @Test
    void deleteMemberById() {
        ResponseEntity<?> response = memberController.deleteMemberById("2");
        assertEquals(HttpStatus.OK, response.getStatusCode());

        ResponseEntity<?> res = memberController.deleteMemberById("4444");
        assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());
    }
}