package project.checkpoint4;

import java.nio.file.Path;
import java.util.List;

import project.StorageAPIImpl;           
import project.annotations.NetworkAPI;
import project.annotations.StorageAPI;

@NetworkAPI
public class CoordinationComponent {

    private final StorageAPI storage;
    private final ComputationComponent compute;

    public CoordinationComponent() {
        this.storage = new StorageAPIImpl();
        this.compute = new ComputationComponent();
    }

    public String startComputation(Path inputFile, Path outputFile) {

        try {
            List<Integer> rawNumbers = storage.readInput(inputFile);
            int limit = storage.parseInput(rawNumbers);

            if (limit < 0) {
                return "ERROR: Invalid input format.";
            }

            List<Integer> results = compute.compute(limit);
            String formatted = storage.formatOutput(results);

            boolean written = storage.writeOutput(outputFile, formatted);

            if (!written) {
                return "ERROR: Failed to write output file.";
            }

            return "SUCCESS: Computation complete.";

        } catch (Exception e) {
            return "ERROR: Unexpected failure - " + e.getMessage();
        }
    }
}