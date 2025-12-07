package testharness;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.datacompute.DataComputeAPIImpl;
import project.intercompute.InterComputeAPIImpl;
import project.networkcompute.MultithreadedNetworkAPI;
import project.usercompute.MultithreadedUserComputeAPIImpl;
import project.usercompute.UserComputeAPI;

public class TestMultiUser {

    private UserComputeAPI coordinator;
    private MultithreadedNetworkAPI networkAPI;

    @BeforeEach
    public void initializeComputeEngine() {
        networkAPI = new MultithreadedNetworkAPI();
        coordinator = new MultithreadedUserComputeAPIImpl(
                new InterComputeAPIImpl(),
                new DataComputeAPIImpl()
        );
    }

    public void cleanup() {
        if (networkAPI != null) {
            networkAPI.shutdown();
        }
    }

    @Test
    public void compareMultiAndSingleThreaded() throws Exception {
        int numThreads = 4;
        List<TestUser> testUsers = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            testUsers.add(new TestUser(coordinator));
        }

        String singleThreadFilePrefix = "testMultiUser.compareMultiAndSingleThreaded.test.singleThreadOut.tmp";
        for (int i = 0; i < numThreads; i++) {
            File singleThreadedOut = new File(singleThreadFilePrefix + i);
            singleThreadedOut.deleteOnExit();
            testUsers.get(i).run(singleThreadedOut.getCanonicalPath());
        }

        ExecutorService threadPool = Executors.newCachedThreadPool();
        List<Future<?>> results = new ArrayList<>();
        String multiThreadFilePrefix = "testMultiUser.compareMultiAndSingleThreaded.test.multiThreadOut.tmp";

        for (int i = 0; i < numThreads; i++) {
            final String multiThreadOutputPath = new File(multiThreadFilePrefix + i).getCanonicalPath();
            final TestUser testUser = testUsers.get(i);

            Callable<Void> task = new Callable<Void>() {
                @Override
                public Void call() {
                    testUser.run(multiThreadOutputPath);
                    return null;
                }
            };
            results.add(threadPool.submit(task));
        }

        for (int i = 0; i < results.size(); i++) {
            try {
                results.get(i).get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        List<String> singleThreaded = loadAllOutput(singleThreadFilePrefix, numThreads);
        List<String> multiThreaded = loadAllOutput(multiThreadFilePrefix, numThreads);
        Assertions.assertEquals(singleThreaded, multiThreaded);
    }

    private List<String> loadAllOutput(String prefix, int numThreads) throws IOException {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            File outputFile = new File(prefix + i);
            result.addAll(Files.readAllLines(outputFile.toPath()));
        }
        return result;
    }

    @Test
    public void smokeTest() {
        List<String> requests = new ArrayList<>();
        requests.add("test1");
        requests.add("test2");
        requests.add("test3");

        List<String> results = networkAPI.processRequests(requests);
        Assertions.assertEquals(requests.size(), results.size());
    }
}
