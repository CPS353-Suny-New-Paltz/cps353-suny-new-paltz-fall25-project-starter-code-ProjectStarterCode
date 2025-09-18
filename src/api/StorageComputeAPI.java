package api;

@ProcessAPI
public interface StorageComputeAPI {
    int[] readNumbers(String source);
    void writeResult(String destination, int[] results, String delimiter);
}