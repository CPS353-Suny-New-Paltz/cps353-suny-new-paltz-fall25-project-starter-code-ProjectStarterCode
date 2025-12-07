package project.usercompute;

public class ComputeResponse {

    public enum Status {
        SUCCESS,
        FAIL
    }

    private final int result;
    private final Status status;

    public ComputeResponse(int result, Status status) {
        this.result = result;
        this.status = status;
    }

    public int getResult() {
        return result;
    }

    public Status getStatus() {
        return status;
    }

    public boolean isSuccess() {
        return status == Status.SUCCESS;
    }
}
