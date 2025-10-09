package project.usercompute;

public class UserRequest {
    private final byte[] bytes;

    public UserRequest(byte[] bytes) {
        this.bytes = bytes;
    }

    public byte[] getBytes() {
        return bytes;
    }
}