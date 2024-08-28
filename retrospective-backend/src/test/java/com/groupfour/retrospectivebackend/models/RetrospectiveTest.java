package com.groupfour.retrospectivebackend.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RetrospectiveTest {

    private Retrospective retro;
    private Item item;
    private ArrayList<Comment> commentList;
    private ArrayList<Item> itemList;

    @BeforeEach
    void setUp() {
        commentList = new ArrayList<>();
        itemList = new ArrayList<>();

        commentList.add(new Comment("633bf100a34a276ff0d49dad", "633bf100a34a276ff0d49dad", "Very useful ticket"));
        item = new Item(
                "633bf100af4ss26ff0d49dad",
                "sad",
                3,
                "633bf100a34a276ff0d49dad",
                "sad item :(",
                commentList
        );
        itemList.add(item);

        retro = new Retrospective("55a1", itemList);
    }

    @Test
    void getTeamId() {
        assertEquals("55a1", retro.getTeamId());
    }

    @Test
    void setTeamId() {
        retro.setTeamId("66a1");
        assertEquals("66a1", retro.getTeamId());
    }

    @Test
    void getItems() {
        assertEquals("sad item :(", retro.getItems().get(0).getDescription());
    }

    @Test
    void setItems() {
        retro.getItems().get(0).setDescription("not so sad text :(");
        assertEquals("not so sad text :(", retro.getItems().get(0).getDescription());
    }
}