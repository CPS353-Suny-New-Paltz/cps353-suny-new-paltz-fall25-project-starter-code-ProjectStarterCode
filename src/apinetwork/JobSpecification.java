package apinetwork;

public class JobSpecification {
    private final int inputNumber;
    private final Delimiters delimiters;

    public JobSpecification(int inputNumber, Delimiters delimiters) {
        this.inputNumber = inputNumber;
        this.delimiters = delimiters;
    }

    public int getInputNumber() { 
    	return inputNumber; 
    	}
    public Delimiters getDelimiters() { 
    	return delimiters; 
    	}
}
