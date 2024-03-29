package fr.rozan.baoloc.repository;

import fr.rozan.baoloc.model.entity.Result;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResultRepository  extends CrudRepository<Result, Long> {
    Optional<Result> findByExpectedResult(int expectedResult);
}
