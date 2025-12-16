package project;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import project.annotations.EngineAPI;
import project.annotations.StorageAPI;
import project.annotations.UserAPI;

public class UserAPIImpl implements UserAPI {

    private final EngineAPI engine;
    private final StorageAPI storage;
    private String inputSource;
    private String outputDestination;

    public UserAPIImpl(EngineAPI engine, StorageAPI storage) {
        this.engine = engine;
        this.storage = storage;
    }

    @Override
    public void setInput(String sourceUri) {
        this.inputSource = sourceUri;
    }

    @Override
    public void setOutput(String sinkUri) {
        this.outputDestination = sinkUri;
    }

    @Override
    public void setDelimiters(String pairDelimiter, String kvDelimiter) {
    }

    @Override
    public void useDefaultDelimiters() {
    }

    @Override
    public String run() {
        Path outputPath = Paths.get(outputDestination);
        String outputData;

        try {
            Path inputPath = Paths.get(inputSource);
            List<Integer> inputs = storage.readInput(inputPath);

            List<Integer> results = new ArrayList<>();

            for (int limit : inputs) {
                if (limit < 0) {
                    results.add(-1);
                } else {
                    String primes = engine.calculatePrimes(limit);
                    results.add(primes.split(",").length);
                }
            }

            outputData = storage.formatOutput(results);

        } catch (Exception e) {
            outputData = "ERROR: Unexpected failure.";
        }

        storage.writeOutput(outputPath, outputData);
        return "SUCCESS";
    }

    public String findPrimes1(int limit) {
        return engine.calculatePrimes(limit);
    }

    public String runEngineTask(EngineAPI mockEngine, int i) {
        return mockEngine.compute(i);
    }

    public String findPrimes(int input) {
        return engine.calculatePrimes(input);
    }

    public String displayComputationSummary() {
        return "Computation complete for " + inputSource + " → " + outputDestination;
    }
}