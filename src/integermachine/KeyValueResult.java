package integermachine;

public class KeyValueResult {
    private final int input;
    private final String result;

    public KeyValueResult(int input, String result) {
        this.input = input;
        this.result = result;
    }

    public int getInput() {
        return input;
    }

    public String getResult() {
        return result;
    }
}
