package project.datacompute;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DataComputeAPIFileImpl implements DataComputeAPI {

    @Override
    public List<Integer> readInput(String inputPath) {
        List<Integer> result = new ArrayList<>();
        if (inputPath == null) {
            return result;
        }

        Path path = Path.of(inputPath);
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String trimmed = line.trim();
                if (!trimmed.isEmpty()) {
                    try {
                        result.add(Integer.parseInt(trimmed));
                    } catch (NumberFormatException expected) {  
                        // skip malformed lines
                    }
                }
            }
        } catch (Exception expected) {                           
            
        }
        return result;
    }

    @Override
    public void writeOutput(List<String> out, String outputPath) {
        if (outputPath == null || out == null) {
            return;
        }
        
        Path path = Path.of(outputPath);
        String line = String.join(",", out) + System.lineSeparator();
        try {
            Files.writeString(path, line, StandardCharsets.UTF_8);
        } catch (Exception expected) {                           
            
        }
    }

    @Override
    public void insertRequest(DataRequest dataRequest) {
        // not needed for this checkpoint
    }
}