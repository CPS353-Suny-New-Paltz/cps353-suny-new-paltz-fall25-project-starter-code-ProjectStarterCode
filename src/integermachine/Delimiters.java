package integermachine;

public class Delimiters {
    private final String pairDelimiter;
    private final String keyValueDelimiter;

    public Delimiters(String pairDelimiter, String keyValueDelimiter) {
        this.pairDelimiter = pairDelimiter;
        this.keyValueDelimiter = keyValueDelimiter;
    }

    public String getPairDelimiter() {
        return pairDelimiter;
    }

    public String getKeyValueDelimiter() {
        return keyValueDelimiter;
    }

    public static Delimiters defaults() {
        return new Delimiters(";", ":");
    }
}
