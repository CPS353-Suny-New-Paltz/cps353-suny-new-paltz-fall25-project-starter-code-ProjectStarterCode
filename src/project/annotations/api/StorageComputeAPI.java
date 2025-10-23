package project.annotations.api;

import project.annotations.ProcessAPI;

@ProcessAPI
public interface StorageComputeAPI {
    int[] readInput();
    void writeOutput(int[] results);
}