package implementations;

import processapi.StorageAPI;

/**
 * Basic stub implementation of the StorageAPI. For now it simply echoes
 * values back to the caller so that the API can be exercised in tests
 * and prototypes without committing to a particular storage technology.
 */
public class StorageImplement implements StorageAPI {

    public StorageImplement() {
        // no setup yet
    }

    /**
     * Example helper method that would eventually be implemented to persist data.
     */
    public boolean saveData(String data) {
        // not implemented yet
        return false;
    }

    @Override
    public String readInput(String source) {
        // In a real implementation, this would fetch data from the given source.
        // For now, treat the source string as the data itself.
        return source;
    }

    @Override
    public String writeOutput(String destination) {
        // In a real implementation, this would persist data to the destination.
        // For now, simply echo the destination so the caller can see what was written.
        return destination;
    }
}

