package project.checkpoint4;

import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import project.annotations.NetworkAPI;

@NetworkAPI
public class MultiThreadedCoordinationComponent {

    private final CoordinationComponent coordinator;
    private final ExecutorService pool;
    private static final int MAX_THREADS = 4;

    public MultiThreadedCoordinationComponent() {
        this.coordinator = new CoordinationComponent();
        this.pool = Executors.newFixedThreadPool(MAX_THREADS);
    }

    public String startComputation(Path inputFile, Path outputFile) {
        try {
            Future<String> task = pool.submit(
                    () -> coordinator.startComputation(inputFile, outputFile)
            );
            return task.get();
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }

    public void shutdown() {
        pool.shutdown();
    }
}