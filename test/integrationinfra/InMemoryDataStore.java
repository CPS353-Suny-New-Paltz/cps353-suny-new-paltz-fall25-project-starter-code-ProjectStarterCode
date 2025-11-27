package integrationinfra;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import process.DataValue;
import process.DataValueImpl;
import process.MultiDataValue;
import process.MultiDataValueImpl;
import process.StorageComputeAPI;

public class InMemoryDataStore implements StorageComputeAPI {

	private final InMemoryInputConfig inputConfig;
	private final InMemoryOutputConfig outputConfig;
	
	
	public InMemoryDataStore(InMemoryInputConfig inputConfig, InMemoryOutputConfig outputConfig) {
		this.inputConfig = inputConfig;
		this.outputConfig = outputConfig;
	}
	
	public InMemoryDataStore() {
        this.inputConfig = new InMemoryInputConfig();
        this.outputConfig = new InMemoryOutputConfig();
    }


	@Override
	public DataValue readInput(String source) {
		if (inputConfig.getInput().isEmpty()) {
			return new DataValueImpl(-1);
		}
		return new DataValueImpl(inputConfig.getInput().remove(0));
	}

	@Override
	public boolean writeOutput(String destination, DataValue data) {
		try {
	        java.nio.file.Path path = java.nio.file.Paths.get(destination);
	        java.nio.file.Files.createDirectories(path.getParent() != null ? path.getParent() : java.nio.file.Paths.get("."));
	        String line = "Value " + data.getValue();
	        java.nio.file.Files.write(path, java.util.List.of(line));
	        outputConfig.getOutput().add(line);
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	@Override
	public MultiDataValue readAllInputs(String filePath) {
		MultiDataValueImpl multi = new MultiDataValueImpl(inputConfig.getInput());
		
		inputConfig.getInput().clear();
		
		return multi;
	}

	@Override
	public boolean writeAllOutputs(String filePath, List<Integer> values) {
		try (PrintWriter writer = new PrintWriter(filePath)) {
	        for (int v : values) {
	            writer.println(v);
	        }
	        return true;
	    } catch (IOException e) {
	        return false;
	    }
	}

}