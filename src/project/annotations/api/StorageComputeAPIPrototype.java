package project.annotations.api;

import project.annotations.ProcessAPIPrototype;

public class StorageComputeAPIPrototype implements StorageComputeAPI {

    @Override
    @ProcessAPIPrototype
    public int[] readInput() {
        System.out.println("[Prototype] Reading input data...");
        return new int[] { 5, 10, 15 };
    }

    @Override
    public void writeOutput(int[] results) {
        System.out.print("[Prototype] Writing output data: ");
        if (results != null) {
            for (int val : results) {
                System.out.print(val + " ");
            }
        }
        System.out.println();
    }
}