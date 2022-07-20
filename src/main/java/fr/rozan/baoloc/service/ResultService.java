package fr.rozan.baoloc.service;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import fr.rozan.baoloc.BaolocResolver;
import fr.rozan.baoloc.model.entity.Permutation;
import fr.rozan.baoloc.model.entity.Result;
import fr.rozan.baoloc.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private PermutationService permutationService;

    public List<Result> getResults() {
        return (List<Result>) this.resultRepository.findAll();
    }

    public Result getResultByExpectedResult(int expectedResult) {
        return this.resultRepository.findByExpectedResult(expectedResult).orElseThrow();
    }

    public void deleteResultById(Long resultId) {
        this.resultRepository.deleteById(resultId);
    }

    public Result retrieveOrProcess(int expectedResult) {
        return this.resultRepository.findByExpectedResult(expectedResult)
                .orElse(this.process(expectedResult));
    }

    public Result process(int expectedResult) {
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
        return this.resultRepository.save(result);
    }
}
