package project;
import project.annotations.StorageAPI;

public abstract class StorageAPIImpl implements StorageAPI {

    public StorageAPIImpl() {
        // no setup yet
    }

    public boolean saveData(String data) {
        return false;
    }
}
