package project.networkapi;

public class UserSubmission {

    private final InputSource input;
    private final OutputSource output;
    private final Delimiter delimiter;

    public UserSubmission(InputSource inputSource, OutputSource outputSource, Delimiter delimiter) {
        this.input = inputSource;
        this.output = outputSource;
        this.delimiter = delimiter;
    }

    public InputSource getInput() {
        return input;
    }
    public OutputSource getOutput() {
        return output;
    }
    public Delimiter getDelimiter() {
        return delimiter;
    }
}
