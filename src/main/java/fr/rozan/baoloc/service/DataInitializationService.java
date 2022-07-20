package fr.rozan.baoloc.service;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import fr.rozan.baoloc.BaolocResolver;
import fr.rozan.baoloc.model.entity.Permutation;
import fr.rozan.baoloc.model.entity.Result;
import fr.rozan.baoloc.repository.PermutationRepository;
import fr.rozan.baoloc.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DataInitializationService {

    @Autowired
    private PermutationService permutationService;

    @Autowired
    private PermutationRepository permutationRepository;

    @Autowired
    private ResultRepository resultRepository;

    @PostConstruct
    @Transactional
    private void init() {
        // fill the database with some random data
        Stream.of(0, 1, 66, 100, 500, 1000).forEach(expectedResult -> {

            // instantiate resolver with the given expected result
            BaolocResolver baolocResolver = BaolocResolver.builder()
                    .expectedResult(expectedResult)
                    .build();

            // process algorithm to find all matching permutations
            List<List<Integer>> matchingPermutationsMatrix = baolocResolver.findMatchingPermutations();

            // convert integer matrix to permutation entities
            Set<Permutation> matchingPermutations = matchingPermutationsMatrix.stream()
                    .map(list -> Permutation.builder()
                            .numbers(Joiner.on(',').join(list))
                            .build())
                    .collect(Collectors.toSet());

            // save all permutations which are not already persisted
            final Set<Permutation> persistedPermutations = Sets.newHashSet(
                    this.permutationService.getAndSaveAllMissingPermutations(matchingPermutations));

            // build and save result entities
            Result result = Result.builder()
                    .expectedResult(expectedResult)
                    .duration(baolocResolver.getDuration())
                    .permutations(persistedPermutations.size() > 0 ? persistedPermutations :  null)
                    .build();
            this.resultRepository.save(result);
        });
        // this.resultRepository.findAll().forEach(System.out::println);
    }
}
