package apinetwork;

public class ComputationInput {
    private final int inputNumber;
    private final Delimiters delimiters;

    public ComputationInput(int inputNumber, Delimiters delimiters) {
        this.inputNumber = inputNumber;
        this.delimiters = delimiters;
    }

    // convenience constructor
    public ComputationInput(int inputNumber, String delimiter) {
        this(inputNumber, new Delimiters(delimiter, delimiter));
    }

    public int getInputNumber() { 
    	return inputNumber; 
    }
    public Delimiters getDelimiters() { 
    	return delimiters; 
    }
}
