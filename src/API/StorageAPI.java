package API;

import project.annotations.ProcessAPI;

@ProcessAPI
public interface StorageAPI {
    void readInput(String source);
    void writeOutput(String destination);
}
