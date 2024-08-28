package com.groupfour.retrospectivebackend.converter;
import com.groupfour.retrospectivebackend.dto.TeamDTO;
import com.groupfour.retrospectivebackend.models.Team;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TeamConverter {

    public Team convertDtoToEntity(TeamDTO teamDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(teamDTO, Team.class);
    }
}
