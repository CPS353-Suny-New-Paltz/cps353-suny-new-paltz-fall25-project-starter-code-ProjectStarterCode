package project.datacompute;

public class DataRequest {
    private final int number;

    public DataRequest(int number) {
        
        if (number < 0) {
            throw new IllegalArgumentException("number cannot be negative.");
        }
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
