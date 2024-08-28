package com.groupfour.retrospectivebackend.models;

import org.springframework.data.annotation.Id;

public class Comment {
    @Id
    private String id;
    private String memberId;
    private String text;

    public Comment() {}

    public Comment(String id, String memberId, String text) {
        this.id = id;
        this.memberId = memberId;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
