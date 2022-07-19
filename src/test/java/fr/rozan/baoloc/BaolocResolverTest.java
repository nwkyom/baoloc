package fr.rozan.baoloc;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BaolocResolverTest {

    @Test
    public void baolocResolverWith66ShouldReturn128Results() {
        this.processAndVerifyBaolocResolverResults(66, 128);
    }

    @Test
    public void baolocResolverWith0ShouldReturn8Results() {
        this.processAndVerifyBaolocResolverResults(0, 8);
    }

    @Test
    public void baolocResolverWith1ShouldReturn4Results() {
        this.processAndVerifyBaolocResolverResults(1, 4);
    }

    @Test
    public void baolocResolverWith3ShouldReturn0Results() {
        this.processAndVerifyBaolocResolverResults(-3, 0);
    }

    private void processAndVerifyBaolocResolverResults(int expectedResult, int expectedMatchingPermutations) {
        // setup
        BaolocResolver baolocResolver = BaolocResolver.builder()
                .expectedResult(expectedResult)
                .build();

        // exercise
        List<List<Integer>> results = baolocResolver.findMatchingPermutations();

        // verify
        Assertions.assertThat(results.size()).isEqualTo(expectedMatchingPermutations);

        // tear-down: nothing to do here
    }
}
