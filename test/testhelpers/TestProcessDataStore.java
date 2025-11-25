package testhelpers;

import apistorage.ProcessAPI;

import java.util.ArrayList;
import java.util.List;

public class TestProcessDataStore implements ProcessAPI {

    private final TestInputConfig inputConfig;
    private final TestOutputConfig outputConfig;

    public TestProcessDataStore(TestInputConfig inputConfig, TestOutputConfig outputConfig) {
        this.inputConfig = inputConfig;
        this.outputConfig = outputConfig;
    }

    @Override
    public List<Integer> readInputs() {
    	List<Integer> src = inputConfig == null ? null : inputConfig.getInputs();
    	if (src == null) {
    	    return new ArrayList<>();
    	}
    	return new ArrayList<>(src);
    }

 //will write a string output to the TestOutputConfig
 
    @Override
    public boolean writeOutput(String data) {
        try {
            if (outputConfig == null) {
            	return false;
            }
            outputConfig.write(data);
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    //Test helper to access the TestOutputConfig for assertions in tests.

    public TestOutputConfig getOutputConfig() {
        return outputConfig;
    }
}
