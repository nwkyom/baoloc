package fr.rozan.baoloc.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Entity class used to store the Bao Loc problem's results depending
 * on a given expected result.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "expected_result", unique = true, nullable = false)
    private Integer expectedResult;

//    @ElementCollection(fetch = FetchType.EAGER)
//    @Column(name = "permutations", columnDefinition = "ARRAY")
//    private List<List<Integer>> permutations;

    @Column(name = "permutations", columnDefinition = "JSON")
    private String permutations;
}
