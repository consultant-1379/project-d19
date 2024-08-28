package com.groupfour.retrospectivebackend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document("Retrospective")
public class Retrospective {

    @Id
    private String id;
    private String teamId;
    private ArrayList<Item> items;

    public Retrospective() {
    }

    public Retrospective(String teamId, ArrayList<Item> items) {
        this.teamId = teamId;
        this.items = items;
    }
    public Retrospective(String teamId) {
        this.teamId = teamId;
        ArrayList<Item> items = new ArrayList<> ();
        this.items=items ;

    }
    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Retrospective{" +
                "teamId='" + teamId + '\'' +
                ", items=" + items +
                '}';
    }
}
