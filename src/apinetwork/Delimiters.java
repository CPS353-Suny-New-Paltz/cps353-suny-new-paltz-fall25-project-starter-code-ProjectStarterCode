package apinetwork;

public class Delimiters {
    private final String inputResultDelimiter;
    private final String pairDelimiter;

    public Delimiters(String inputResultDelimiter, String pairDelimiter) {
        this.inputResultDelimiter = inputResultDelimiter;
        this.pairDelimiter = pairDelimiter;
    }

    public String getInputResultDelimiter() { 
    	return inputResultDelimiter; 
    	}
    public String getPairDelimiter() { 
    	return pairDelimiter; 
    	}
}
