package project.networkcompute;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultithreadedNetworkAPI {

    private final ExecutorService executor;

    public MultithreadedNetworkAPI() {
        int maxThreads = 4; // DOCUMENT THIS IN README
        this.executor = Executors.newFixedThreadPool(maxThreads);
    }

    public List<String> processRequests(List<String> requests) {
        List<Future<String>> futures = new ArrayList<>();

        for (final String request : requests) {
            Callable<String> task = new Callable<String>() {
                @Override
                public String call() {
                    return "processed:" + request;
                }
            };
            futures.add(executor.submit(task));
        }

        List<String> results = new ArrayList<>();
        for (Future<String> future : futures) {
            try {
                results.add(future.get());
            } catch (Exception e) {
                results.add("error");
            }
        }

        return results;
    }

    public void shutdown() {
        executor.shutdown();
    }
}
