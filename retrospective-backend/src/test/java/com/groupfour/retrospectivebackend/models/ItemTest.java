package com.groupfour.retrospectivebackend.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    private ArrayList<Comment> comments;
    private Item item;

    @BeforeEach
    void setUp() {
        comments = new ArrayList<>();
        comments.add(new Comment("633bf100a34a276ff0d49dad", "633bf100a34a276ff0d49dad", "Very useful ticket"));
        item = new Item(
                "633bf100af4ss26ff0d49dad",
                "mad",
                2,
                "633bf100a34a276ff0d49dad",
                "Sample item description",
                comments
        );
    }

    @Test
    void getId() {
        assertEquals("633bf100af4ss26ff0d49dad", item.getId());
    }

    @Test
    void setId() {
        item.setId("123");
        assertEquals("123", item.getId());
    }

    @Test
    void getCategory() {
        assertEquals("mad", item.getCategory());
    }

    @Test
    void setCategory() {
        item.setCategory("glad");
        assertEquals("glad", item.getCategory());
    }

    @Test
    void getVote() {
        assertEquals(2, item.getVote());
    }

    @Test
    void setVote() {
        item.setVote(10);
        assertEquals(10, item.getVote());
    }

    @Test
    void getMemberId() {
        assertEquals("633bf100a34a276ff0d49dad", item.getMemberId());
    }

    @Test
    void setMemberId() {
        item.setMemberId("999");
        assertEquals("999", item.getMemberId());
    }

    @Test
    void getDescription() {
        assertEquals("Sample item description", item.getDescription());
    }

    @Test
    void setDescription() {
        item.setDescription("this item is glad :D");
        assertEquals("this item is glad :D", item.getDescription());
    }

    @Test
    void getComments() {
        assertEquals("Very useful ticket", comments.get(0).getText());
    }

    @Test
    void setComments() {
        comments.get(0).setText("New comment");
        assertEquals("New comment", comments.get(0).getText());
    }
}