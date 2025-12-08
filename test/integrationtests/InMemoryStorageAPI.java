package integrationtests;

import processapi.StorageAPI;

// Reads and writes data from simple in-memory fields.
public class InMemoryStorageAPI implements StorageAPI {
    private String inputData;
    private String outputData;

    public InMemoryStorageAPI() {
        this.inputData = "";
        this.outputData = "";
    }

    @Override
    public String readInput(String source) {
        // Treat the provided source string as the actual input data
        this.inputData = source;
        return this.inputData;
    }

    @Override
    public String writeOutput(String destination) {
        // Store the destination string as the output data and return it
        this.outputData = destination;
        return this.outputData;
    }

    // Test helper methods
    public String getInputData() {
        return inputData;
    }

    public String getOutputData() {
        return outputData;
    }
}

