package com.groupfour.retrospectivebackend.services;

import com.groupfour.retrospectivebackend.models.*;
import com.groupfour.retrospectivebackend.repositories.ItemRepository;
import com.groupfour.retrospectivebackend.repositories.RetrospectiveRepository;
import com.groupfour.retrospectivebackend.service.ItemService;
import com.groupfour.retrospectivebackend.service.impl.ItemServiceImpl;
import com.groupfour.retrospectivebackend.service.impl.TeamServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private RetrospectiveRepository retrospectiveRepository;

    @MockBean
    private ItemRepository itemRepository;

    private ArrayList<Item> items = new ArrayList<>();
    private Item item1;
    private Item item2;
    private Item item3;

    private Team team;

    private Retrospective retrospective;


    private final ArrayList<Comment> comments = new ArrayList<>();

    @BeforeEach
    public void setup(){
        //public Item(String id, String category, int vote, String memberId, String description,
        // ArrayList<Comment> comments)
        item1 = new Item("I001", "sad", 0, "M005", "item 1", comments);
        item2 = new Item("I002", "mad", 2, "M005", "item 2", comments);
        item3 = new Item("I003", "glad", -1, "M005", "item 3", comments);
        Member member = new Member("M005", "Bob");
        items.add(item1);
        items.add(item2);
        items.add(item3);
        Comment comment = new Comment("C1", "M005", "be more specific");

        team = new Team("T001", "Team Name", null);
        //public Retrospective(String teamId, ArrayList<Item> items)
        //retrospective = new Retrospective("T001", items);

        Mockito.when(itemRepository.findAll()).thenReturn(items);
        Mockito.when(itemRepository.findItemById("I001")).thenReturn(item1);
        /*Mockito.when(itemRepository.a("T001",item1)).then(a -> {
            retrospective.getItems().add(item1);
            return true;
        });*/
        /*Mockito.when(itemRepository.deleteItemByid("I001")).then(a -> {
            items.remove(item1);
            System.out.println(itemRepository.findAll().size());
            return true;
        });*/
        doAnswer(a -> {
            items.remove(item1);
            return null;
        }).when(itemRepository).deleteById("I001");



    }

    // Can't test when Retrospective is not fully implemented
    /*@Test
    public void testAddItem(){
        itemService.addItem(item1, "T001");
        assertEquals(1, itemService.findItemsByTeamId("T001").size());
    }*/

    @Test
    public void testfindItemById(){
        Item item = itemService.findItemById("I001");
        assertEquals("item 1", item.getDescription());
    }

    // CAN'T TEST WITHOUT RETROSPECTIVE REPOSITORY
    /*@Test
    public void testFindItemsByTeamId(){
        List<Item> teamItems = itemService.findItemsByTeamId("T001");
        assertEquals(3, teamItems.size());
    }*/

    @Test
    public void testDeleteItemById(){
        assertEquals(3, itemService.findAll().size());
        itemService.deleteItemById("I001");
        verify(itemRepository).deleteById("I001");
        assertEquals(2, itemService.findAll().size());
    }


    @Test
    public void testGetItemAuthor(){
        itemService.getItemAuthor("I001");
        verify(itemRepository).findItemById("I001");
    }

    // *** COMMENT IS OUT OF BOUNDS OF MOCK ITEM REPOSITORY
    /*@Test
    public void testAddComment(){
        itemService.addComment(comments.get(0), "I001");
        assertEquals(1, itemService.findItemsByTeamId("T001").size());
    }

    @Test void testDeleteComment(){
        itemService.deleteComment("C1", "I003");
        verify(itemRepository).findItemById("C1");

    }*/
}

