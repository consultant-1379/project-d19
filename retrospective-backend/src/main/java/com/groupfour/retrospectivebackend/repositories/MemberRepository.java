package com.groupfour.retrospectivebackend.repositories;

import com.groupfour.retrospectivebackend.models.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends MongoRepository<Member, String> {
    // get member with specified id
    Member getMemberById(String id);
}
