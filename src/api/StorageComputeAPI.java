package api;

import project.annotations.ProcessAPI;

@ProcessAPI
public interface StorageComputeAPI {
    int[] readNumbers(String source);
    void writeResult(String destination, String[] results, String delimiter); // Changed to String[]
    String[] computeFactorial(int[] numbers); // Changed to return String[]
}