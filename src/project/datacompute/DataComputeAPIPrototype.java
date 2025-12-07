package project.datacompute;

import java.util.ArrayList;
import java.util.List;
import project.annotations.ProcessAPIPrototype;

public class DataComputeAPIPrototype {

    @ProcessAPIPrototype
    public void prototype(DataComputeAPI storage) {
        if (storage == null) {
            throw new IllegalArgumentException("DataComputeAPI instance cannot be null.");
        }

        final String inputPath = "data/input.txt";
        final String outputPath = "data/output.csv";

        List<Integer> input = storage.readInput(inputPath);

        int sum = 0;
        for (Integer v : input) {
            if (v != null) {
                sum += v;
            }
        }

        List<Integer> out = new ArrayList<>();
        out.add(sum);
        storage.writeOutput(out, outputPath);
    }
}
