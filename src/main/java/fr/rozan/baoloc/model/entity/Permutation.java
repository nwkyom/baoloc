package fr.rozan.baoloc.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "permutation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = { "results"})
@ToString(exclude = { "results"})
@Builder
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "results" })
public class Permutation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * Values of the current permutation, formatted as following:
     * val1,val2...,valn
     * Each permutation found by the algorithm will be stored only once
     * and will be easily comparable.
     */
    @Column(name = "numbers", unique = true, nullable = false)
    private String numbers;

    @ManyToMany(mappedBy = "permutations")
    private Set<Result> results;
}
