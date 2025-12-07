package project.usercompute;

import java.util.List;

public class ComputeRequest {

    private final List<Integer> numbers;
    private final String inputPath;
    private final String outputPath;

    public ComputeRequest(List<Integer> numbers, String outputPath) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("numbers cannot be null or empty");
        }
        this.numbers = numbers;
        this.inputPath = null;
        this.outputPath = outputPath;
    }

    public ComputeRequest(String inputPath, String outputPath) {
        if (inputPath == null || inputPath.isBlank()) {
            throw new IllegalArgumentException("inputPath cannot be null or blank");
        }
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.numbers = null;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public String getInputPath() {
        return inputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }
}
