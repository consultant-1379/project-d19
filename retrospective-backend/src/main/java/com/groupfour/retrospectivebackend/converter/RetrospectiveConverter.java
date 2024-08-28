package com.groupfour.retrospectivebackend.converter;
import com.groupfour.retrospectivebackend.dto.RetrospectiveDTO;
import com.groupfour.retrospectivebackend.models.Retrospective;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class RetrospectiveConverter {
    public Retrospective convertDtoToEntity(RetrospectiveDTO retrospectiveDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(retrospectiveDTO, Retrospective.class);
    }
}
