package project.processapi;

import project.annotations.ProcessAPI;
import java.util.List;

@ProcessAPI
public interface DataStoreComputeAPI {
    List<Integer> loadInput(String inputPath, String delimiter);
    StorageResponse saveOutput(String outputPath, String resultContent);
}