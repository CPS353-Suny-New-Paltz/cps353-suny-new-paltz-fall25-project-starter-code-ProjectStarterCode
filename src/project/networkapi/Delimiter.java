package project.networkapi;

public class Delimiter {
    private final char value;

    public Delimiter(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public String getDelimiter() {
        return String.valueOf(value);
    }
}
