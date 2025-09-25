package api;

import project.annotations.ProcessAPI;

@ProcessAPI
public interface StorageComputeAPI {
    int[] readNumbers(String source);
<<<<<<< HEAD
    void writeResult(String destination, String[] results, String delimiter); // Changed to String[]
    String[] computeFactorial(int[] numbers); // Changed to return String[]
=======
    void writeResult(String destination, int[] results, String delimiter);

    int[] computeFactorial(int[] numbers);
>>>>>>> 2ff78b6c0eb83c3cf2c08a4982d94e123e2f0c36
}