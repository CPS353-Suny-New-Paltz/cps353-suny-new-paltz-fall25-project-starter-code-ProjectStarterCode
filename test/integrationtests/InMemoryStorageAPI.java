package integrationtests;
import project.annotations.StorageAPI;

//Reads and writes data from simple in memory fields.
public class InMemoryStorageAPI implements StorageAPI {

    private String inputData;
    private String outputData;

    public InMemoryStorageAPI() {
        this.inputData = "";
        this.outputData = "";
    }

    @Override
    public void readInput(String source) {
        this.inputData = source;
    }

    @Override
    public void writeOutput(String destination) {
        this.outputData = destination;
    }

    //Test helper methods
    public String getInputData() {
        return inputData;
    }

    public String getOutputData() {
        return outputData;
    }
}
