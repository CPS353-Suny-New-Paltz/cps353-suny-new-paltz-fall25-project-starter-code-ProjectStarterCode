package project;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import project.annotations.StorageAPI;

public class StorageAPIImpl implements StorageAPI {

    @Override
    public List<Integer> readInput(Path filePath) {
        List<Integer> values = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                line = line.trim();
                if (!line.isEmpty()) {
                    values.add(Integer.parseInt(line));
                }
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }
        return values;
    }

    @Override
    public int parseInput(List<Integer> rawData) {
        if (rawData.isEmpty()) {
            return -1;  
        }
        return rawData.get(0);
    }

    @Override
    public String formatOutput(List<Integer> primes) {
        if (primes == null || primes.isEmpty()) {
            return "";
        }
        return primes.toString();
    }

    @Override
    public boolean writeOutput(Path destination, String formattedOutput) {
        try {
            Files.writeString(destination, formattedOutput);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}