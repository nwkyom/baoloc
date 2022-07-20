package fr.rozan.baoloc;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import fr.rozan.baoloc.model.entity.Permutation;
import fr.rozan.baoloc.model.entity.Result;
import fr.rozan.baoloc.repository.PermutationRepository;
import fr.rozan.baoloc.repository.ResultRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class BaolocApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaolocApplication.class, args);
    }
}
