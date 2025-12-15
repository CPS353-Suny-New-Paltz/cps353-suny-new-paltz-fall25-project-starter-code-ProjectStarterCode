package integermachine;

public class JobConfig {
    private final InputSourceRef inputSource;
    private final OutputSinkRef outputSink;
    private final Delimiters delimiters; // may be null => use defaults

    public JobConfig(InputSourceRef inputSource,
                     OutputSinkRef outputSink,
                     Delimiters delimiters) {
        this.inputSource = inputSource;
        this.outputSink = outputSink;
        this.delimiters = delimiters;
    }

    public InputSourceRef getInputSource() {
        return inputSource;
    }

    public OutputSinkRef getOutputSink() {
        return outputSink;
    }

    public Delimiters getDelimiters() {
        return delimiters;
    }
}
