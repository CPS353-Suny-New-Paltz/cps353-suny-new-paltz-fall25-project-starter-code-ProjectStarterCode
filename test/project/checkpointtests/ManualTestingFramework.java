package project.checkpointtests;

import project.conceptualapi.ComputeControllerAPI;
import project.conceptualapi.ComputeRequest;
import project.conceptualapi.ComputeResponse;
import project.conceptualapi.PowerDigitSumController;
import project.processapi.DataStoreComputeAPI;
import project.processapi.DataStoreImplementation;

import java.util.List;
import java.util.stream.Collectors;

public class ManualTestingFramework {

    public static final String INPUT = "TestInput.txt";
    public static final String OUTPUT = "TestOutput.txt";

    public static void main(String[] args) {
        
        DataStoreComputeAPI dataStore = new DataStoreImplementation();
        ComputeControllerAPI computeEngine = new PowerDigitSumController();

        // Load newline integers
        List<Integer> inputs = dataStore.loadInput(INPUT, "\n");

        // Compute one result per input
        List<String> results = inputs.stream()
                .map(n -> computeEngine.compute(new ComputeRequest(n)))
                .map(ComputeResponse::getResult)
                .collect(Collectors.toList());

        // Write a single line to output
        String outputLine = String.join(",", results);
        dataStore.saveOutput(OUTPUT, outputLine);
    }
}