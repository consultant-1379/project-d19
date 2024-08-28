package com.groupfour.retrospectivebackend.repositories;

import org.springframework.transaction.annotation.Transactional;
import com.groupfour.retrospectivebackend.models.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends  MongoRepository<Item, String>{
    public List<Item> findAll();
    // get item with specific id
    Item findItemById(String id);

    // add item to team retrospective

    // delete item with specified id
    @Transactional
    boolean deleteItemByid(String id);

    // delete all items
    @Transactional
    void deleteAll();


}
