package project.processapi;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TestDataStore implements DataStoreComputeAPI {

    @Override
    public List<Integer> loadInput(String inputPath, String delimiter) {
        try {
            String content = Files.readString(Path.of(inputPath));
            String[] parts = content.split(delimiter);
            List<Integer> values = new ArrayList<>();
            for (String p : parts) {
                String trimmed = p.trim();
                if (!trimmed.isEmpty()) {
                    values.add(Integer.parseInt(trimmed));
                }
            }
            return values;
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public StorageResponse saveOutput(String outputPath, String resultContent) {
        try {
            Files.writeString(Path.of(outputPath), resultContent);
            return new StorageResponse(outputPath, StoreStatus.SUCCESS);
        } catch (Exception e) {
            return new StorageResponse(outputPath, StoreStatus.FAILURE_WRITE_ERROR);
        }
    }
}
