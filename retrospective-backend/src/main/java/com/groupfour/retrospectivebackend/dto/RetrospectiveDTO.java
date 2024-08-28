package com.groupfour.retrospectivebackend.dto;

import com.groupfour.retrospectivebackend.models.Item;

import java.util.ArrayList;

public class RetrospectiveDTO {

        private String id;
        private String teamId;
        private ArrayList<Item> items;

        public RetrospectiveDTO() {
        }

        public RetrospectiveDTO(String teamId, ArrayList<Item> items) {
            this.teamId = teamId;
            this.items = items;
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
}
