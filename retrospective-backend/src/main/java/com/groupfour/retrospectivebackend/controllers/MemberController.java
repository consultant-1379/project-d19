package com.groupfour.retrospectivebackend.controllers;

import com.groupfour.retrospectivebackend.converter.MemberConverter;
import com.groupfour.retrospectivebackend.dto.MemberDTO;
import com.groupfour.retrospectivebackend.models.Member;
import com.groupfour.retrospectivebackend.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberConverter memberConverter;

    @GetMapping
    public ResponseEntity<Object> getMembers() {
        List<Member> members = memberService.findAll();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getMemberById(@PathVariable String id) {
        Member member = memberService.getMemberById(id);

        if (member != null) {
            return new ResponseEntity<>(member, HttpStatus.OK);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No member found");
    }

    @PostMapping
    public ResponseEntity<Object> addMember(@RequestBody MemberDTO member) {
        if (member != null) {
            memberService.addMember(member);
            return new ResponseEntity<>(member, HttpStatus.OK);
        }

        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Failed to save member");
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> modifyMemberById(@PathVariable String id, @RequestBody MemberDTO newMember) {
        Member member = memberService.getMemberById(id);

        if (member != null) {
            memberService.saveOrUpdateMember(newMember);
            return new ResponseEntity<>(newMember, HttpStatus.OK);
        }

        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Failed to save member changes");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteMemberById(@PathVariable String id) {
        Member member = memberService.getMemberById(id);

        if (member != null) {
            memberService.deleteMemberById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted user: " + member.getName());
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Something went wrong");
    }
}