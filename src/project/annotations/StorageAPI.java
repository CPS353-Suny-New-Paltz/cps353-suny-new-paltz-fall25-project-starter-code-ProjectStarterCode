package project.annotations;

import java.nio.file.Path;
import java.util.List;

@ProcessAPI
public interface StorageAPI {
    List<Integer> readInput(Path filePath);
    int parseInput(List<Integer> rawData);
    String formatOutput(List<Integer> primes);
    boolean writeOutput(Path destination, String formattedOutput);
}