package project.intercompute;

public class InterRequest {
    private final byte[] bytes;

    public InterRequest(byte[] bytes) {
        this.bytes = bytes;
    }

    public byte[] getBytes() {
        return bytes;
    }
}
