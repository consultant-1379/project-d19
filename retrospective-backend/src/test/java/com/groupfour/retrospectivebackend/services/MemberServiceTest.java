package com.groupfour.retrospectivebackend.services;

import com.groupfour.retrospectivebackend.converter.MemberConverter;
import com.groupfour.retrospectivebackend.dto.MemberDTO;
import com.groupfour.retrospectivebackend.models.*;
import com.groupfour.retrospectivebackend.repositories.ItemRepository;
import com.groupfour.retrospectivebackend.repositories.MemberRepository;
import com.groupfour.retrospectivebackend.repositories.RetrospectiveRepository;
import com.groupfour.retrospectivebackend.service.ItemService;
import com.groupfour.retrospectivebackend.service.MemberService;
import com.groupfour.retrospectivebackend.service.impl.ItemServiceImpl;
import com.groupfour.retrospectivebackend.service.impl.TeamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberConverter memberConverter;
    @MockBean
    private MemberRepository memberRepository;

    private ArrayList<Member> members = new ArrayList<>();

    private Member member1;
    private Member member2;
    private Member member3;

    private MemberDTO nm;
    Member newMember;

    @BeforeEach
    public void setup(){
        // public Member(String id, String name) {
        member1 = new Member("M001", "John Doe");
        member2 = new Member("M002", "Mary Delaney");
        member3 = new Member("M003", "Bobby Brown");
        members.add(member1);
        members.add(member2);
        members.add(member3);
        nm = new MemberDTO("M004", "Timmy Timmons");
        newMember = memberConverter.convertDtoToEntity(nm);
        //Mockito.when(itemRepository.findAll()).thenReturn(items);
        // doAnswer(a -> {
        //            items.remove(item1);
        //            return null;
        //        }).when(itemRepository).deleteById("I001");
        Mockito.when(memberRepository.findAll()).thenReturn(members);
        Mockito.when(memberRepository.getMemberById("M001")).thenReturn(member1);
        doAnswer(a -> {
            members.remove(member1);
            return null;
        }).when(memberRepository).deleteById("M001");
        Mockito.when(memberRepository.save(any(Member.class))).then(a->{
            members.add(newMember);
            return newMember;
        });
        //Mockito.when(memberRepository.save(memberConverter.convertDtoToEntity(nm))).thenReturn(newMember);
    }

    @Test
    public void testFindAll(){
        assertEquals(3, memberService.findAll().size());
        assertEquals("M001", memberService.findAll().get(0).getId());
        assertEquals("Bobby Brown", memberService.findAll().get(2).getName());
    }

    @Test
    public void testGetMemberById(){
        assertEquals(member1, memberService.getMemberById("M001"));
    }

    @Test
    public void testDeleteMemberById(){
        assertEquals(3, members.size());
        memberService.deleteMemberById("M001");
        assertEquals(2, members.size());
    }


    @Test
    public void testAddMember(){
        assertEquals(3, memberService.findAll().size());
        Member m = memberService.addMember(nm);
        assertEquals(4, memberService.findAll().size());
        assertEquals(nm.getId(), m.getId());
        assertEquals(nm.getName(), m.getName());


    }

    @Test
    public void testSaveOrUpdate(){
        MemberDTO memberDTO = new MemberDTO("M004", "Timmy Timmons");
        memberService.saveOrUpdateMember(memberDTO);
        assertEquals(4, memberService.findAll().size());
        assertEquals("Timmy Timmons", memberService.findAll().get(3).getName());
    }
}
