package com.groupfour.retrospectivebackend.converter;

import com.groupfour.retrospectivebackend.dto.ItemDTO;
import com.groupfour.retrospectivebackend.dto.RetrospectiveDTO;
import com.groupfour.retrospectivebackend.models.Comment;
import com.groupfour.retrospectivebackend.models.Item;
import com.groupfour.retrospectivebackend.models.Retrospective;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RetrospectiveConverterTest {

    private RetrospectiveConverter retrospectiveConverter = new RetrospectiveConverter();
    private Retrospective retrospective;
    private RetrospectiveDTO retrospectiveDTO;
    private Item item1;
    private Comment comment1;
    private ArrayList<Item> itemList = new ArrayList<>();
    private ArrayList<Comment> commentList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        comment1 = new Comment("1", "6a1", "teasda");
        commentList.add(comment1);

        item1 = new Item("1", "MAD", 2, "6a1", "asdasda", commentList);
        itemList.add(item1);

        retrospectiveDTO = new RetrospectiveDTO("1", itemList);
    }

    @Test
    void convertDtoToEntity() {
        retrospective = retrospectiveConverter.convertDtoToEntity(retrospectiveDTO);
        assertEquals("1", retrospective.getTeamId());
        assertEquals(item1, retrospective.getItems().get(0));
        assertEquals(comment1, retrospective.getItems().get(0).getComments().get(0));
    }

}