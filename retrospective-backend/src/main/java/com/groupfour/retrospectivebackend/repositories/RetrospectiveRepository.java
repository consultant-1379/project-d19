package com.groupfour.retrospectivebackend.repositories;

import com.groupfour.retrospectivebackend.models.Retrospective;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RetrospectiveRepository extends MongoRepository<Retrospective, String> {
    // get retrospective by team Id
    Retrospective getRetrospectiveByTeamId(String teamId);


}
