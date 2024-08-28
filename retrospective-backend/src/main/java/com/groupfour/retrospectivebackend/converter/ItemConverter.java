package com.groupfour.retrospectivebackend.converter;

import com.groupfour.retrospectivebackend.dto.ItemDTO;
import com.groupfour.retrospectivebackend.models.Item;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

@Component
public class ItemConverter {
    public Item convertDtoToEntity(ItemDTO itemDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(itemDTO, Item.class);
    }
}
