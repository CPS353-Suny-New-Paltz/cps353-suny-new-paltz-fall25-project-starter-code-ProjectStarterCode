package project;
import project.annotations.EngineAPI;
import project.annotations.StorageAPI;
import project.annotations.UserAPI;

public abstract class UserAPIImpl implements UserAPI {

    private EngineAPI engineApi;
    private StorageAPI storageApi;

    public UserAPIImpl(EngineAPI engineApi, StorageAPI storageApi) {
        this.engineApi = engineApi;
        this.storageApi = storageApi;
    }

    // Example empty method, return default values
    public String findPrimes(int input) {
        return "";
    }
}
