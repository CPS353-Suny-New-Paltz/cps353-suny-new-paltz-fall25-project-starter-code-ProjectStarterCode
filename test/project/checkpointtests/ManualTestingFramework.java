package project.checkpointtests;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;

import apiimplementation.ConceptualAPIImpl;
import apinetwork.ComputationInput;
import apinetwork.ComputationOutput;


// ManualTestingFramework required by the checkpoint tests.
// Reads manualTestInput.txt, computes a result per input using ConceptualAPIImpl,
//and writes a single comma-separated line to manualTestOutput.txt

public class ManualTestingFramework {
    
    public static final String INPUT = "manualTestInput.txt";
    public static final String OUTPUT = "manualTestOutput.txt";

    public static void main(String[] args) {
        try {
            Path inputPath = Paths.get(INPUT);

            // Read raw lines from the input file (test creates this file before calling main)
            List<String> rawLines = Files.readAllLines(inputPath);

            // Prepare compute engine (pure computation)
            ConceptualAPIImpl conceptual = new ConceptualAPIImpl();

            List<String> outputs = new ArrayList<>();

            // Parse each line, compute, and collect the result strings
            for (String raw : rawLines) {
                if (raw == null) {
                    continue;
                }
                String t = raw.trim();
                if (t.isEmpty()) {
                    continue;
                }
                try {
                    int v = Integer.parseInt(t);
                    // Use the convenience constructor that accepts a String delimiter
                    ComputationInput ci = new ComputationInput(v, ",");
                    ComputationOutput co = conceptual.compute(ci);
                    String res = (co == null || co.getResult() == null) ? "null" : co.getResult();
                    outputs.add(res);
                } catch (NumberFormatException nfe) {
                    // skip malformed lines
                }
            }

            // Join results with commas and write a single line to the output file
            String singleLine = String.join(",", outputs);
            Path outputPath = Paths.get(OUTPUT);
            Files.write(outputPath, Arrays.asList(singleLine));
        } catch (IOException e) {
            // Re-throw as unchecked to fail the test (so CI shows a clear error)
            throw new RuntimeException("ManualTestingFramework failed: " + e.getMessage(), e);
        }
    }
}
