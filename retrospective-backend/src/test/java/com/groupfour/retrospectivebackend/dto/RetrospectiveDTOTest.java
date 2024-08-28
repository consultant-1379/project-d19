package com.groupfour.retrospectivebackend.dto;

import com.groupfour.retrospectivebackend.models.Comment;
import com.groupfour.retrospectivebackend.models.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RetrospectiveDTOTest {

    private RetrospectiveDTO rDTO;
    private Item item1;
    private Item item2;
    private Item item3;
    private ArrayList<Item> itemList = new ArrayList<>();
    private Comment comment;
    private ArrayList<Comment> commentList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        comment = new Comment("1", "2", "test123");
        commentList.add(comment);

        item1 = new Item("1", "MAD", 2, "999", "Desc123", commentList);
        item2 = new Item("2", "SAD", 3, "998", "newDesc", commentList);

        itemList.add(item1);
        itemList.add(item2);

        rDTO = new RetrospectiveDTO("1", itemList);
    }

    @Test
    void getTeamId() {
        assertEquals("1", rDTO.getTeamId());
    }

    @Test
    void setTeamId() {
        rDTO.setTeamId("2");
        assertEquals("2", rDTO.getTeamId());
    }

    @Test
    void getItems() {
        assertEquals(item1, rDTO.getItems().get(0));
    }

    @Test
    void setItems() {
        Item newItem1 = new Item("4", "MAD", 5, "777", "Cool", commentList);
        itemList.add(newItem1);
        rDTO.setItems(itemList);
        assertEquals(newItem1, rDTO.getItems().get(2));
    }
}