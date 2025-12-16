package integrationtests;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import project.annotations.StorageAPI;

public class InMemoryStorageAPI implements StorageAPI {

    private List<Integer> storedInput = new ArrayList<>();
    private String storedFormattedOutput;
    private boolean writeWasCalled = false;

    @Override
    public List<Integer> readInput(Path source) {
        return new ArrayList<>(storedInput);
    }

    @Override
    public int parseInput(List<Integer> rawData) {
        if (rawData == null || rawData.isEmpty()) {
            return -1;
        }
        return rawData.get(0);
    }

    @Override
    public String formatOutput(List<Integer> primes) {
        if (primes == null) {
            return "";
        }
        storedFormattedOutput = primes.toString();
        return storedFormattedOutput;
    }

    @Override
    public boolean writeOutput(Path destination, String formattedOutput) {
        writeWasCalled = true;
        storedFormattedOutput = formattedOutput;
        return true; 
    }

    public void setMockInput(List<Integer> numbers) {
        storedInput = new ArrayList<>(numbers);
    }

    public String getStoredFormattedOutput() {
        return storedFormattedOutput;
    }

    public boolean wasWriteCalled() {
        return writeWasCalled;
    }
}