package com.groupfour.retrospectivebackend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document("Item")
public class Item {
    @Id
    private String id;
    private String category;
    private int vote;
    private String memberId;
    private String description;
    private ArrayList<Comment> comments = new ArrayList<>();

    public Item() {}

    public Item(String id, String category, int vote, String memberId, String description, ArrayList<Comment> comments) {
        this.id = id;
        this.category = category;
        this.vote = vote;
        this.memberId = memberId;
        this.description = description;
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
}
