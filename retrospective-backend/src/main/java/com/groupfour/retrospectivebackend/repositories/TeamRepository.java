package com.groupfour.retrospectivebackend.repositories;

import com.groupfour.retrospectivebackend.models.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends MongoRepository<Team, String>{
    Team getTeamById(String id);
}
