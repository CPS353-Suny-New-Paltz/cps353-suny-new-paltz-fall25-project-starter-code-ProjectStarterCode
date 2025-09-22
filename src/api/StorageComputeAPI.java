package api;

import project.annotations.ProcessAPI;

@ProcessAPI
public interface StorageComputeAPI {
    int[] readNumbers(String source);
    void writeResult(String destination, int[] results, String delimiter);
    int[] computeFactorial(int[] numbers);
}