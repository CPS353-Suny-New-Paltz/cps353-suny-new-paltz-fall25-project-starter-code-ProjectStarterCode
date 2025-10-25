package project.networkapi;

public class UserSubResponse {

    private final String subId;
    private final SubmissionStatus status;

    public UserSubResponse(String subId, SubmissionStatus status) {
        this.subId = subId;
        this.status = status;
    }

    public String getSubId() {
        return subId;
    }

    public SubmissionStatus getStatus() {
        return status;
    }
}
