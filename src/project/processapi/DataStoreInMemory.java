package project.processapi;

import java.util.List;

public class DataStoreInMemory implements DataStoreComputeAPI {

    @Override
    public List<Integer> loadInput(String inputPath, String delimiter) {
        //return empty input
        return List.of();
    }

    @Override
    public StorageResponse saveOutput(String outputPath, String resultContent) {
        // report success
        return new StorageResponse(outputPath, StoreStatus.SUCCESS);
    }
}