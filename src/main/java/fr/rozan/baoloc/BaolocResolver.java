package fr.rozan.baoloc;

import com.google.common.collect.Collections2;
import lombok.Builder;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/*
 * Resolver class for the Bao Loc problem:
 * Inputs = permutations of [1,2,3,4,5,6,7,8,9]
 * Equations to resolve:
 * var1 + (13 * var2) / var3 + var4 + (12 * var5) - var6 - 11 + (var7 * var8) / var9 - 10 = 66
 * or var1 + (13 * var2) / var3 + var4 + (12 * var5) - var6 + (var7 * var8) / var9 = 87
 */
@Log4j2
@Data
@Builder
public class BaolocResolver {

    private int expectedResult = 66;

    /**
     * Duration in milliseconds of the last resolver execution.
     */
    private long duration;

    private static final List<Integer> inputNumbers = IntStream.range(1,10).boxed().toList();

    public List<List<Integer>> findMatchingPermutations() {
        long start = System.currentTimeMillis();
        log.info("Resolving Bao Loc problem with expected result: {}", this.getExpectedResult());
        List<List<Integer>> matchingPermutations = new ArrayList<>();
        Collections2.permutations(inputNumbers).stream().forEach(list -> {
            if((list.get(0) + ((float) 13 * list.get(1) / list.get(2)) + list.get(3) + 12 * list.get(4) - list.get(5) - 11 + ((float)list.get(6) * list.get(7) / list.get(8)) - 10) == expectedResult){
                log.trace("Matching permutation found: {}", list.toString());
                matchingPermutations.add(list);
            }
        });
        this.duration = System.currentTimeMillis() - start;
        log.info("{} matching permutations have been found for expected result {} in {}ms.", matchingPermutations.size(), this.getExpectedResult(), this.duration);
        return matchingPermutations;
    }
}
