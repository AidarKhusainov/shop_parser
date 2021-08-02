package ru.aidarkhusainov.parser.model.ali;

import java.util.List;

public class AliModel {
    private List<Result> results;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "AliModel{" +
                "results=" + results +
                '}';
    }
}
