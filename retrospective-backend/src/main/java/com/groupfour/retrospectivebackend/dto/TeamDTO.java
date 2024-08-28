package com.groupfour.retrospectivebackend.dto;
import com.groupfour.retrospectivebackend.models.Member;
import java.util.ArrayList;

public class TeamDTO {

    private String id;
    private String name;
    private ArrayList<Member> members = new ArrayList<>();

    public TeamDTO() {
    }

    public TeamDTO(String id, String name, ArrayList<Member> members) {
        this.id = id;
        this.name = name;
        this.members = members;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }


}
