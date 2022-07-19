package fr.rozan.baoloc;

import fr.rozan.baoloc.model.entity.Result;
import fr.rozan.baoloc.repository.ResultRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class BaolocApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaolocApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ResultRepository resultRepository) {
		return args -> {
			Stream.of(0, 1, 66).forEach(expectedResult -> {
				BaolocResolver baolocResolver = BaolocResolver.builder()
						.expectedResult(expectedResult)
						.build();
				Result result = Result.builder()
						.expectedResult(expectedResult)
						.permutations(baolocResolver.findMatchingPermutations().toString())
						.build();
				resultRepository.save(result);
			});
			resultRepository.findAll().forEach(System.out::println);
		};
	}
}
