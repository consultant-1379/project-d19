package com.groupfour.retrospectivebackend.converter;

import com.groupfour.retrospectivebackend.dto.MemberDTO;
import com.groupfour.retrospectivebackend.models.Member;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

@Component
public class MemberConverter {
    public Member convertDtoToEntity(MemberDTO memberDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(memberDTO, Member.class);
    }
}
