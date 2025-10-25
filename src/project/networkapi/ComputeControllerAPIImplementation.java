package project.networkapi;

import project.conceptualapi.ComputeControllerAPI;
import project.conceptualapi.ComputeRequest;
import project.conceptualapi.ComputeResponse;
import project.processapi.DataStoreComputeAPI;

import java.util.List;

public class ComputeControllerAPIImplementation {

    private final DataStoreComputeAPI dataStore;
    private final ComputeControllerAPI computeEngine;

    public ComputeControllerAPIImplementation(DataStoreComputeAPI dataStore, ComputeControllerAPI computeEngine) {
        this.dataStore = dataStore;
        this.computeEngine = computeEngine;
    }

    public void runComputation(UserSubmission submission) {
        try {
            String inputPath = submission.getInput().getSourceName();
            String outputPath = submission.getOutput().getSourceName();
            String delimiter = submission.getDelimiter().getDelimiter();

            // Load integers from the data storage component
            List<Integer> intInputs = dataStore.loadInput(inputPath, delimiter);
            if (intInputs.isEmpty()) {
                throw new IllegalArgumentException("Expected a single positive integer input");
            }

            int n = intInputs.get(0);

            // Build request for n^n digit sum
            ComputeRequest request = new ComputeRequest(n);
            ComputeResponse response = computeEngine.compute(request);

            // Save the single result string
            String outputString = response.getResult();
            dataStore.saveOutput(outputPath, outputString);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UserSubResponse submit(UserSubmission submission) {
        return null;
    }

    public SubmissionStatus getStatus(String id) {
        return null;
    }
}