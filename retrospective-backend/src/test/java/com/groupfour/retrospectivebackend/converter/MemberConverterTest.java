package com.groupfour.retrospectivebackend.converter;

import com.groupfour.retrospectivebackend.converter.ItemConverter;
import com.groupfour.retrospectivebackend.converter.MemberConverter;
import com.groupfour.retrospectivebackend.dto.ItemDTO;
import com.groupfour.retrospectivebackend.dto.MemberDTO;
import com.groupfour.retrospectivebackend.models.Item;
import com.groupfour.retrospectivebackend.models.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MemberConverterTest {
    private MemberConverter memberConverter = new MemberConverter();
    private MemberDTO memberDTO;
    private Member member;

    @BeforeEach
    public void setup(){
        memberDTO = new MemberDTO("M1", "John");
    }

    @Test
    void testConverter(){
        member = memberConverter.convertDtoToEntity(memberDTO);
        assertEquals("M1", member.getId());
        assertEquals("John", member.getName());
    }
}
