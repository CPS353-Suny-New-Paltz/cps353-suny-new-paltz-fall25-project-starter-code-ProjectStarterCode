package assets;

public class UserRequest {
    private final String inputSource;
    private final String outputDestination;
    private final String delimiter;
    private final boolean useDefaultDelimiter;

    // Private constructor
    private UserRequest(Builder builder) {
        this.inputSource = builder.inputSource;
        this.outputDestination = builder.outputDestination;
        this.delimiter = builder.delimiter;
        this.useDefaultDelimiter = builder.useDefaultDelimiter;
    }

    // Getters
    public String getInputSource() {
        return inputSource;
    }

    public String getOutputDestination() {
        return outputDestination;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public boolean isUseDefaultDelimiter() {
        return useDefaultDelimiter;
    }
    //This will check if the userRequest is valid
    public boolean isValid() {
        return inputSource != null && !inputSource.isEmpty()
            && outputDestination != null && !outputDestination.isEmpty()
            && delimiter != null && !delimiter.isEmpty();
    }

    // ✅ Builder Class
    public static class Builder {
        private String inputSource;
        private String outputDestination;
        private String delimiter;
        private boolean useDefaultDelimiter = false; // default to false

        public Builder inputSource(String inputSource) {
            this.inputSource = inputSource;
            return this;
        }

        public Builder outputDestination(String outputDestination) {
            this.outputDestination = outputDestination;
            return this;
        }

        public Builder delimiter(String delimiter) {
            this.delimiter = delimiter;
            return this;
        }

        public Builder useDefaultDelimiter(boolean useDefaultDelimiter) {
            this.useDefaultDelimiter = useDefaultDelimiter;
            return this;
        }

        public UserRequest build() {
            return new UserRequest(this);
        }
    }
}
