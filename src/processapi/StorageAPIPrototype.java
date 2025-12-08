package processapi;

import project.annotations.ProcessAPIPrototype;

/**
 * Simple prototype demonstrating how a client would use the StorageAPI.
 */
public class StorageAPIPrototype {

    @ProcessAPIPrototype
    public void prototype(StorageAPI storageapi) {
        // Take in from a user-specified source and capture what was read
        String input = storageapi.readInput("helloworld");

        // Write out a value derived from the input and capture what was written
        String output = storageapi.writeOutput("processed-" + input);

        // For now, just demonstrate the workflow
        System.out.println("Prototype read: " + input);
        System.out.println("Prototype wrote: " + output);
    }
}
