package project.conceptualapi;

public class ComputeResponse {

    private String result;           // Computed digit sum
    private ComputeStatus status;    // Enums

    public ComputeResponse(String result, ComputeStatus status) {
        this.result = result;
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public ComputeStatus getStatus() {
        return status;
    }
}
