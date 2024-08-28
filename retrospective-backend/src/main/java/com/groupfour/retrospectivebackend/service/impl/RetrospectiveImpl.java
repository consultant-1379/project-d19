package com.groupfour.retrospectivebackend.service.impl;


import com.groupfour.retrospectivebackend.converter.ItemConverter;
import com.groupfour.retrospectivebackend.converter.RetrospectiveConverter;
import com.groupfour.retrospectivebackend.dto.ItemDTO;
import com.groupfour.retrospectivebackend.dto.RetrospectiveDTO;
import com.groupfour.retrospectivebackend.models.Item;
import com.groupfour.retrospectivebackend.models.Retrospective;
import com.groupfour.retrospectivebackend.models.Team;
import com.groupfour.retrospectivebackend.repositories.ItemRepository;
import com.groupfour.retrospectivebackend.repositories.MemberRepository;
import com.groupfour.retrospectivebackend.repositories.RetrospectiveRepository;
import com.groupfour.retrospectivebackend.repositories.TeamRepository;
import com.groupfour.retrospectivebackend.service.RetrospectiveService;
import com.groupfour.retrospectivebackend.service.TeamService;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RetrospectiveImpl implements RetrospectiveService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private RetrospectiveRepository retrospectiveRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamService teamService;

    @Autowired
    private ItemConverter itemConverter;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RetrospectiveConverter retrospectiveConverter;

    @Override
    public List<Retrospective> findAll() {
        return retrospectiveRepository.findAll();
    }

    @Override
    public Retrospective getRetrospectiveByTeamId(String teamId) {
        return retrospectiveRepository.getRetrospectiveByTeamId(teamId);
    }

    @Override
    public Team getTeam(String id) {
        return teamService.getTeamById(id);
    }

    @Override
    public Retrospective addRetrospectiveByTeamId(String teamId) {

            Retrospective retro = new Retrospective(teamId);
            retrospectiveRepository.save(retro);
             return retro;
    }

    @Override
    public Retrospective addRetrospective(RetrospectiveDTO retrospective) {
        return null;
    }

    @Override
    public void deleteRetrospectiveById(String id) {
        if(retrospectiveRepository.getRetrospectiveByTeamId(id)!= null)
        {
            retrospectiveRepository.deleteById(id);
        }
    }

    @Override
    public ArrayList<Item> getAllItemsByTeam(String teamId) {
        Retrospective retro = retrospectiveRepository.getRetrospectiveByTeamId(teamId);
        if(retro != null)
        {
            return retro.getItems();
        }
        return null;
    }

    @Override
    public ArrayList<Item> getAllItemsByCategory(String teamId,String category) {
        Retrospective retro = retrospectiveRepository.getRetrospectiveByTeamId(teamId);
        if(retro != null)
        {
            return (ArrayList<Item>) retro.getItems().stream().filter(i->i.getCategory().startsWith(category)).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public ArrayList<Item> addItem(String teamId,ItemDTO item) {

        Retrospective retro = retrospectiveRepository.getRetrospectiveByTeamId(teamId);
        if(retro != null)
        {
            ArrayList<Item> items= retro.getItems();
            ObjectId id = new ObjectId();
            Item newItem= itemConverter.convertDtoToEntity(item);
            newItem.setId(String.valueOf(id));
            items.add(newItem);
            retrospectiveRepository.save(retro);
            return items;
        }
        return null;

    }

    @Override
    public ArrayList<Item> deleteItem(String teamId,String itemId) {

        Retrospective retro = retrospectiveRepository.getRetrospectiveByTeamId(teamId);
        if(retro != null)
        {
            ArrayList<Item> items = retro.getItems();
            Iterator i = items.iterator();
            Item x ;
            while (i.hasNext()) {
                x = (Item) i.next();
                if (x.getId().equals(itemId)) {
                    i.remove();
                    break;
                }
            }
            retro.setItems(items);
            retrospectiveRepository.save(retro);
       }
        return null;

    }

    @Override
    public Retrospective saveOrUpdateRetrospective(RetrospectiveDTO retrospective) {
        return retrospectiveRepository.save(retrospectiveConverter.convertDtoToEntity(retrospective));
    }


}
