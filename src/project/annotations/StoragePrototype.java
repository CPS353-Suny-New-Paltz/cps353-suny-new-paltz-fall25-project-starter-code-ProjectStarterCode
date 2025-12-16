package project.annotations;

import java.nio.file.Path;
import java.util.List;

public class StoragePrototype {

    @ProcessAPIPrototype
    public String prototype(StorageAPI storage) {

        Path fakePath = Path.of("demoInput.txt");

        List<Integer> raw = storage.readInput(fakePath);
        int limit = storage.parseInput(raw);

        if (limit < 0) {
            return "Error: invalid input";
        }

        String formatted = storage.formatOutput(raw);

        boolean ok = storage.writeOutput(Path.of("demoOutput.txt"), formatted);

        return ok ? "Prototype success" : "Prototype failure";
    }
}
