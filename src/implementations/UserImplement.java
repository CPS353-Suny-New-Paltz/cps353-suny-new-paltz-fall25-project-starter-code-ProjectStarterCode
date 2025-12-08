package implementations;

import conceptualapi.ComputeAPI;
import networkapi.UserAPI;
import processapi.StorageAPI;

/**
 * Basic implementation of the UserAPI that wires together the compute
 * engine and storage. For now it focuses on clearly expressing what a
 * client needs in order to complete a workflow.
 */
public class UserImplement implements UserAPI {

    private final ComputeAPI engine;
    private final StorageAPI storage;

    private String inputUri;
    private String outputUri;
    private String pairDelimiter;
    private String kvDelimiter;

    public UserImplement(ComputeAPI engine, StorageAPI storage) {
        this.engine = engine;
        this.storage = storage;
    }

    /**
     * Delegate prime-number calculation to the underlying compute engine.
     */
    public String findPrimes(int limit) {
        return engine.calculatePrimes(limit);
    }

    @Override
    public String setInput(String sourceUri) {
        this.inputUri = sourceUri;
        return this.inputUri;
    }

    @Override
    public String setOutput(String sinkUri) {
        this.outputUri = sinkUri;
        return this.outputUri;
    }

    @Override
    public String[] setDelimiters(String pairDelimiter, String kvDelimiter) {
        this.pairDelimiter = pairDelimiter;
        this.kvDelimiter = kvDelimiter;
        return new String[] { this.pairDelimiter, this.kvDelimiter };
    }

    @Override
    public String[] useDefaultDelimiters() {
        this.pairDelimiter = ";";
        this.kvDelimiter = "=";
        return new String[] { this.pairDelimiter, this.kvDelimiter };
    }

    /**
     * Example helper that could be used by higher level tests later on.
     */
    public String runEngineTask(ComputeAPI engine, int n) {
        return engine.compute(n);
    }
}
