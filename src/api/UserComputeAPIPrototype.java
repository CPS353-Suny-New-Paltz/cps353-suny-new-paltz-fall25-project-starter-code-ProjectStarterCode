package api;

import project.annotations.NetworkAPIPrototype;

public class UserComputeAPIPrototype implements UserComputeAPI {

    private String inputSource;
    private String outputDestination;
    private String delimiters;
    private static final String DEFAULT_DELIMITERS = ",;|";
    
    
    public UserComputeAPIPrototype() {
        // Constructor
    }
    @NetworkAPIPrototype
    @Override
    public String getInputSource() {
        return inputSource;
    }
    
    @Override
    public String getOutputDestination() {
        return outputDestination;
    }
    
    @Override
    public String getDelimiters() {
        return delimiters;
    }
    
    @Override
    public void setInputSource(String inputSource) {
        this.inputSource = inputSource;
    }
    
    @Override
    public void setOutputDestination(String outputDestination) {
        this.outputDestination = outputDestination;
    }
    
    @Override
    public void setDelimiters(String delimiters) {
        this.delimiters = delimiters;
    }
    
    @Override
    public void useDefaultDelimiters() {
        this.delimiters = DEFAULT_DELIMITERS;
    }
}