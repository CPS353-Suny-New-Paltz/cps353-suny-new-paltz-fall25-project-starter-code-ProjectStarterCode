package project.checkpointtests;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import conceptualapi.ComputeAPI;
import implementations.ComputeImplement;
import implementations.StorageImplement;
import implementations.UserImplement;
import networkapi.UserAPI;
import processapi.StorageAPI;

public class ManualTestingFramework {

    public static final String INPUT = "manualTestInput.txt";
    public static final String OUTPUT = "manualTestOutput.txt";

    public static void main(String[] args) {
        try {
            runComputation();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runComputation() throws IOException {
        // Use the real implementations from src/
        ComputeAPI engine = new ComputeImplement();
        StorageAPI storage = new StorageImplement();
        UserAPI user = new UserImplement(engine, storage);

        // Example configuration through UserAPI
        user.setInput(INPUT);
        user.setOutput(OUTPUT);
        user.setDelimiters(",", "=");

        Path inputPath = Paths.get(INPUT);
        Path outputPath = Paths.get(OUTPUT);

        List<String> lines = Files.readAllLines(inputPath);
        List<String> outputs = new ArrayList<>();

        for (String line : lines) {
            String trimmed = line.trim();
            if (trimmed.isEmpty()) {
                continue;
            }

            try {
                int value = Integer.parseInt(trimmed);

                // Use the compute engine, but make sure EACH result
                // is a single comma-free token.
                String result = engine.calculatePrimes(value);

                // Remove commas INSIDE the result so split(",") only
                // sees the commas we add between results.
                if (result.isEmpty()) {
                    // still produce a token so outputs count == inputs count
                    result = "NO_RESULT";
                } else {
                    result = result.replace(",", ";");
                }

                outputs.add(result);
            } catch (NumberFormatException e) {
                // Non-numeric line: still create one token
                outputs.add("INVALID");
            }
        }

        // Always create the output file; write ONE comma-separated line
        if (outputs.isEmpty()) {
            Files.write(outputPath, List.of(""));
        } else {
            String singleLine = String.join(",", outputs);
            Files.write(outputPath, List.of(singleLine));
        }
    }
}
