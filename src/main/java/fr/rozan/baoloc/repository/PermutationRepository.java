package fr.rozan.baoloc.repository;

import fr.rozan.baoloc.model.entity.Permutation;
import fr.rozan.baoloc.model.entity.Result;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermutationRepository extends CrudRepository<Permutation, Long> {

    Optional<Permutation> findByNumbers(String numbers);
}
