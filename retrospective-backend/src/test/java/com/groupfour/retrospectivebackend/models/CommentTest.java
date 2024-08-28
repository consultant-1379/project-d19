package com.groupfour.retrospectivebackend.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    private Comment comment;

    @BeforeEach
    void setUp() {
        comment = new Comment("222", "6ad1", "my comment");
    }

    @Test
    void getId() {
        assertEquals("222", comment.getId());
    }

    @Test
    void setId() {
        comment.setId("333");
        assertEquals("333", comment.getId());
    }

    @Test
    void getMemberId() {
        assertEquals("6ad1", comment.getMemberId());
    }

    @Test
    void setMemberId() {
        comment.setMemberId("6a2");
        assertEquals("6a2", comment.getMemberId());
    }

    @Test
    void getText() {
        assertEquals("my comment", comment.getText());
    }

    @Test
    void setText() {
        comment.setText("text");
        assertEquals("text", comment.getText());
    }
}