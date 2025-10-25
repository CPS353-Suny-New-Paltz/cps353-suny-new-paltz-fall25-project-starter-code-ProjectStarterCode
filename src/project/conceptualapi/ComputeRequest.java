package project.conceptualapi;

public class ComputeRequest {

    private int base;     // int 2
    private int exponent; // int 15

    public ComputeRequest(int base, int exponent) {
        this.base = base;
        this.exponent = exponent;
    }

    public ComputeRequest(int n) {
        this.base = n;
        this.exponent = n;
    }

    public int getBase() {
        return base;
    }

    public int getExponent() {
        return exponent;
    }
}
