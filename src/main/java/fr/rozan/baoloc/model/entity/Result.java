package fr.rozan.baoloc.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Entity class used to store the Bao Loc problem's results depending
 * on a given expected result.
 */
@Entity
@Table(name = "result")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = { "permutations"})
@ToString(exclude = { "permutations"})
@Builder
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Expected algorithm result.
     */
    @Column(name = "expected_result", unique = true, nullable = false)
    private Integer expectedResult;

    /**
     * Duration in milliseconds of the problem's resolution.
     */
    @Column(name = "duration", nullable = false)
    private Long duration;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "permutation_result",
            joinColumns = @JoinColumn(name = "result_id"),
            inverseJoinColumns = @JoinColumn(name = "permutation_id"))
    @Column(name = "permutations")
    private Set<Permutation> permutations;

//    @Column(name = "permutations", columnDefinition = "JSON")
//    private String permutations;


}
