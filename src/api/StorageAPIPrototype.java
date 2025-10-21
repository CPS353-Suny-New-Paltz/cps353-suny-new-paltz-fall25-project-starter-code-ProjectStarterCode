package api;
import project.annotations.ProcessAPIPrototype;
public class StorageAPIPrototype {
	 @ProcessAPIPrototype
    public void prototype(StorageAPI storageapi) {
        // Take in From User specified Source
        storageapi.readInput("helloworld");

        // Write out from user specified source
        storageapi.writeOutput(null);
    }
}
