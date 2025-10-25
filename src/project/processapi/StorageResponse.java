package project.processapi;

public class StorageResponse {
    private String id;
    private StoreStatus status;

    public StorageResponse(String id, StoreStatus status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public StoreStatus getStatus() {
        return status;
    }
}