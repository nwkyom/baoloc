package fr.rozan.baoloc.service;

import com.google.common.collect.Sets;
import fr.rozan.baoloc.model.entity.Permutation;
import fr.rozan.baoloc.repository.PermutationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

@Service
public class PermutationService {

    @Autowired
    private PermutationRepository permutationRepository;

    @Transactional
    public Set<Permutation> getAndSaveAllMissingPermutations(Set<Permutation> permutationsToSave) {
        Set persistedPermutations = Sets.newHashSet();
        permutationsToSave.stream().forEach(permutation -> {
            // check if the permutation have been already stored
            Optional<Permutation> optPermutation = this.permutationRepository.findByNumbers(permutation.getNumbers());
            if(optPermutation.isEmpty()) {
                persistedPermutations.add(this.permutationRepository.save(permutation));
            } else {
                persistedPermutations.add(optPermutation.get());
            }
        });
        return persistedPermutations;
    }
}
