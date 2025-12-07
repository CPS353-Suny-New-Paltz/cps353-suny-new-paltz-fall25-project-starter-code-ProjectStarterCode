package project.usercompute;

import project.annotations.NetworkAPIPrototype;

/**
 * Demonstrates how to use the UserComputeAPI with both direct input (in-memory)
 * and file-based input sources.
 */
public class UserComputeAPIPrototype {

    @NetworkAPIPrototype
    public void prototype(UserComputeAPI user) {
        if (user == null) {
            throw new IllegalArgumentException("UserComputeAPI instance cannot be null.");
        }

        System.out.println("=== Running in-memory computation ===");

        user.compute(new ComputeRequest(
                java.util.List.of(10, 25, 97),
                "data/output_inmemory.csv"
        ));

        System.out.println("=== Running file-based computation ===");

        user.compute(new ComputeRequest(
                "data/input_numbers.txt",
                "data/output_file.csv"
        ));

        System.out.println("Prototype test completed.");
    }
}
