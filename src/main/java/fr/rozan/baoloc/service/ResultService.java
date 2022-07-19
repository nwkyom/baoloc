package fr.rozan.baoloc.service;

import fr.rozan.baoloc.model.entity.Result;
import fr.rozan.baoloc.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;

    public List<Result> getResults() {
        return (List<Result>) this.resultRepository.findAll();
    }

    public Result getResultByExpectedResult(int expectedResult) {
        return this.resultRepository.findByExpectedResult(expectedResult);
    }

    public Result saveResult(Result result) {
        return this.resultRepository.save(result);
    }

    public void deleteResult(Result result) {
        this.resultRepository.delete(result);
    }
}
