package project.datacompute;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DataComputeAPIImpl implements DataComputeAPI {

    @Override
    public List<Integer> readInput(String inputPath) {
        if (inputPath == null || inputPath.isBlank()) {
            System.err.println("readInput: inputPath cannot be empty.");
            return new ArrayList<>();
        }

        Path path = Path.of(inputPath);
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            System.err.println("readInput: file does not exist or is not a regular file: " + inputPath);
            return new ArrayList<>();
        }
        if (!Files.isReadable(path)) {
            System.err.println("readInput: file is not readable: " + inputPath);
            return new ArrayList<>();
        }

        List<Integer> ns = new ArrayList<>();
        try (BufferedReader r = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = r.readLine()) != null) {
                if (!line.isBlank()) {
                    try {
                        ns.add(Integer.parseInt(line.trim()));
                    } catch (NumberFormatException e) {
                        System.err.println("readInput: skipping invalid number: |" + line + "|");
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("readInput: error while reading file: " + e.getMessage());
            return new ArrayList<>();
        }
        return ns;
    }

    @Override
    public void writeOutput(List<Integer> results, String outputPath) {
    	if (results == null || results.isEmpty()) {
    	    System.err.println("writeOutput: no data to write.");
    	    return;
    	}
    	if (outputPath == null || outputPath.isBlank()) {
    	    System.err.println("writeOutput: outputPath cannot be empty.");
    	    return;
    	}

        Path path = Path.of(outputPath);
        try {
            Path parent = path.getParent();
            if (parent != null && !Files.exists(parent)) {
                Files.createDirectories(parent);
            }

            String csv = results.stream()
                .map(i -> i == null ? "" : Integer.toString(i))
                .collect(java.util.stream.Collectors.joining(","));  // ← NO newline, NO spaces

            Files.writeString(
                path, csv, StandardCharsets.UTF_8,
                java.nio.file.StandardOpenOption.CREATE,
                java.nio.file.StandardOpenOption.TRUNCATE_EXISTING
            );
        } catch (Exception e) {
            System.err.println("writeOutput error: " + e.getMessage());
        }
    }

}
