package com.groupfour.retrospectivebackend.controllers;

import com.groupfour.retrospectivebackend.converter.ItemConverter;
import com.groupfour.retrospectivebackend.converter.RetrospectiveConverter;
import com.groupfour.retrospectivebackend.dto.ItemDTO;
import com.groupfour.retrospectivebackend.models.Item;
import com.groupfour.retrospectivebackend.models.Retrospective;
import com.groupfour.retrospectivebackend.models.Team;
import com.groupfour.retrospectivebackend.repositories.ItemRepository;
import com.groupfour.retrospectivebackend.repositories.MemberRepository;
import com.groupfour.retrospectivebackend.repositories.RetrospectiveRepository;
import com.groupfour.retrospectivebackend.repositories.TeamRepository;
import com.groupfour.retrospectivebackend.service.RetrospectiveService;
import com.groupfour.retrospectivebackend.service.TeamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/retrospective")
public class RetrospectiveController {


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
    private RetrospectiveService retrospectiveService;

    @Autowired
    private ItemConverter itemConverter;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RetrospectiveConverter retrospectiveConverter;

    @GetMapping
    public ResponseEntity<?> getRetrospectives()
    {
            List<Retrospective> retros = retrospectiveService.findAll();
            if(retros.size()<1)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Team found");

            }
            return new ResponseEntity<>(retros, HttpStatus.OK);
    }

    @GetMapping(value = "/{teamId}")
    public ResponseEntity<?> getRetrospectiveByTeamId(@PathVariable String teamId) {
            Retrospective retrospective = retrospectiveService.getRetrospectiveByTeamId(teamId);
            if(retrospective!=null)
            {
                return new ResponseEntity<>(retrospective, HttpStatus.OK);

            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Team found");
    }



    @PostMapping(value = "/{teamId}")
    public ResponseEntity<?> addItem(@PathVariable String teamId, @RequestBody ItemDTO item) {
            if (item != null) {
                retrospectiveService.addItem(teamId, item);
                return new ResponseEntity<>(
                        "Item: " + item.getId() +
                                " with description: " + item.getDescription() +
                                " successfully added", HttpStatus.OK
                );
            }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Something went wrong");
    }

    @GetMapping(value = "/{teamId}/{category}")
    public ResponseEntity<?> getItemByCategory(@PathVariable String teamId,@PathVariable String category) {
            List<Item> items = retrospectiveService.getAllItemsByCategory(teamId,category);
            if(items.size()<1) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Something went wrong");

            }
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping(value = "/items/{teamId}")
    public ResponseEntity<?> getAllItemsByTeam(@PathVariable String teamId) {
        Retrospective retro = retrospectiveService.getRetrospectiveByTeamId(teamId);
            if(retro != null) {
                List<Item> items = retrospectiveService.getAllItemsByTeam(teamId);
                return new ResponseEntity<>(items, HttpStatus.OK);
            }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Something went wrong");

    }

    @GetMapping(value = "/team/{teamId}")
    public ResponseEntity<?> getTeam(@PathVariable String teamId) {
        Retrospective retro = retrospectiveService.getRetrospectiveByTeamId(teamId);
            if (retro != null) {
                Team team = retrospectiveService.getTeam(teamId);
                return new ResponseEntity<>(team, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Something went wrong");
    }

    @DeleteMapping("/{teamId}/{itemId}")
    public ResponseEntity<?> deleteItem(@PathVariable String teamId,@PathVariable String itemId) {
            Retrospective retrospective= retrospectiveService.getRetrospectiveByTeamId(teamId);
            Item foundItem= retrospective.getItems().stream().filter(i->i.getId().equals(itemId)).collect(Collectors.toList()).get(0);
            if (foundItem!=null) {
                retrospectiveService.deleteItem(teamId,itemId);

                return ResponseEntity.status(HttpStatus.OK).body(
                        "Successfully deleted item: " + itemId
                );
            }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Something went wrong");
    }
}
