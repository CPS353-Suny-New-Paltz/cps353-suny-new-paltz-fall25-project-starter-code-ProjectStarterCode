package apinetwork;

public class ComputationInput {
    private final int inputNumber;
    private final Delimiters delimiters;

    public ComputationInput(int inputNumber, Delimiters delimiters) {
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
