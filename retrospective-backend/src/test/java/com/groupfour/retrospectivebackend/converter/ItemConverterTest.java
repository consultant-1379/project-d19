package com.groupfour.retrospectivebackend.converter;

import com.groupfour.retrospectivebackend.converter.ItemConverter;
import com.groupfour.retrospectivebackend.dto.ItemDTO;
import com.groupfour.retrospectivebackend.models.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


class ItemConverterTest {


    private ItemConverter itemConverter = new ItemConverter();

    private ItemDTO itemDTO;

    private Item item;


    @BeforeEach
    public void setup(){
        itemDTO = new ItemDTO("I1", "mad", 0,  "M1", "desc", null);
    }

    @Test
    void testConvert(){
        item = itemConverter.convertDtoToEntity(itemDTO);
        assertEquals("I1", item.getId());
        assertEquals("mad", item.getCategory());
        assertEquals(0, item.getVote());
        assertEquals("M1", item.getMemberId());
        assertEquals("desc", item.getDescription());
        assertEquals(null, item.getComments());
    }
}
