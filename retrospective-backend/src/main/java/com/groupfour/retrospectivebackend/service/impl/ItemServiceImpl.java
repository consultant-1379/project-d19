package com.groupfour.retrospectivebackend.service.impl;

import com.groupfour.retrospectivebackend.converter.ItemConverter;
import com.groupfour.retrospectivebackend.dto.ItemDTO;
import com.groupfour.retrospectivebackend.models.Comment;
import com.groupfour.retrospectivebackend.models.Item;
import com.groupfour.retrospectivebackend.models.Member;
import com.groupfour.retrospectivebackend.models.Retrospective;
import com.groupfour.retrospectivebackend.repositories.ItemRepository;
import com.groupfour.retrospectivebackend.repositories.MemberRepository;
import com.groupfour.retrospectivebackend.repositories.RetrospectiveRepository;
import com.groupfour.retrospectivebackend.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    // public Item(String id, String category,
    // int vote, String memberId, String description, ArrayList<Comment> comments)
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private RetrospectiveRepository retrospectiveRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ItemConverter itemConverter;

    @Autowired
    private ModelMapper modelMapper;

    public List<Item> findAll(){
        return itemRepository.findAll();
    }
    @Override
    public List<Item> findItemsByTeamId(String teamID) {
        List<Retrospective> retros= retrospectiveRepository.findAll();
        for(int i=0;i<retros.size();i++){
            if(retros.get(i).getTeamId().equals(teamID)) return retros.get(i).getItems();
        }
        return null;
    }

    @Override
    public Item addItem(ItemDTO item, String teamID) {
        List<Retrospective> retros= retrospectiveRepository.findAll();
        for(int i=0;i<retros.size();i++){
            if(retros.get(i).getTeamId().equals(teamID)){
                retros.get(i).getItems().add(itemConverter.convertDtoToEntity(item));
                return itemConverter.convertDtoToEntity(item);
            }
        }
        return null;
    }

    @Override
    public Item findItemById(String id) {
        return itemRepository.findItemById(id);
    }

    @Override
    public void deleteItemById(String id) {
        itemRepository.deleteById(id);
    }


    @Override
    public Member getItemAuthor(String itemID) {
        String memberID = itemRepository.findItemById(itemID).getMemberId();
        return memberRepository.getMemberById(memberID);
    }

    @Override
    public Comment addComment(Comment comment, String itemID) {
        Item item = itemRepository.findItemById(itemID);
        item.getComments().add(comment);
        itemRepository.save(item);
        return comment;
    }

    @Override
    public Comment deleteComment(String commentID, String itemID) {
        Item item = itemRepository.findItemById(itemID);
        ArrayList<Comment> comments = item.getComments();
        Comment comm = null;
        for(int i=0;i< comments.size();i++){
            if(comments.get(i).getId().equals(commentID)){
                comm = comments.get(i);
            }
        }
        item.getComments().remove(comm);
        return comm;
    }

    @Override
    public Item saveOrUpdateMember(ItemDTO item) {
        return itemRepository.save(itemConverter.convertDtoToEntity(item));
    }
}
