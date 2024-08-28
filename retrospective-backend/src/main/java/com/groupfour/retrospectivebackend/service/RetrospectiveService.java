package com.groupfour.retrospectivebackend.service;

import com.groupfour.retrospectivebackend.dto.ItemDTO;
import com.groupfour.retrospectivebackend.dto.RetrospectiveDTO;
import com.groupfour.retrospectivebackend.models.Item;
import com.groupfour.retrospectivebackend.models.Retrospective;
import com.groupfour.retrospectivebackend.models.Team;

import java.util.ArrayList;
import java.util.List;

public interface RetrospectiveService {

    List<Retrospective> findAll();

    Retrospective getRetrospectiveByTeamId(String teamId);

    Team getTeam(String id);

    Retrospective addRetrospectiveByTeamId(String id);

    Retrospective addRetrospective(RetrospectiveDTO retrospective);

    void deleteRetrospectiveById(String id);

    ArrayList<Item> getAllItemsByTeam(String id);


    ArrayList<Item> getAllItemsByCategory(String teamId,String Category);

    ArrayList<Item> addItem(String teamId,ItemDTO item);

    ArrayList<Item> deleteItem(String teamId,String itemId);

    Retrospective saveOrUpdateRetrospective(RetrospectiveDTO retrospective);















}
