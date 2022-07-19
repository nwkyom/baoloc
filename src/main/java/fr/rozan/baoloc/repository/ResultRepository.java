package fr.rozan.baoloc.repository;

import fr.rozan.baoloc.model.entity.Result;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository  extends CrudRepository<Result, Long> {

    Result findByExpectedResult(int expectedResult);
}
