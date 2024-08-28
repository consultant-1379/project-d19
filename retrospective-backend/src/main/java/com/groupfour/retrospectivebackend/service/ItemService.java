package com.groupfour.retrospectivebackend.service;

import com.groupfour.retrospectivebackend.dto.ItemDTO;
import com.groupfour.retrospectivebackend.models.Comment;
import com.groupfour.retrospectivebackend.models.Item;
import com.groupfour.retrospectivebackend.models.Member;

import java.util.List;

public interface ItemService {
    public List<Item> findAll();

    // return all items for a team
    List<Item> findItemsByTeamId(String teamID);

    Item addItem(ItemDTO item, String teamID);

    // find item, given its ID
    Item findItemById(String id);

    // delete item, given the item ID
    void deleteItemById(String id);

    // get member that created the item
    Member getItemAuthor(String itemID);

    // add comment
    Comment addComment(Comment comment, String itemID);

    // delte comment
    Comment deleteComment(String commentID, String itemID);

    Item saveOrUpdateMember(ItemDTO item);
}
