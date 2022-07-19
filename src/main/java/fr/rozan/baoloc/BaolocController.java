package fr.rozan.baoloc;

import fr.rozan.baoloc.model.entity.Result;
import fr.rozan.baoloc.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BaolocController {

    @Autowired
    private ResultService resultService;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to Bao Lok Resolver project !";
    }

    @GetMapping("/results")
    public ResponseEntity<List<Result>> getResults() {
        return ResponseEntity.ok(this.resultService.getResults());
    }

    @GetMapping("/resultByExpectedResult")
    public ResponseEntity<Result> getResultByExpectedResult(@RequestParam int expectedResult) {
        return ResponseEntity.ok(this.resultService.getResultByExpectedResult(expectedResult));
    }
}
