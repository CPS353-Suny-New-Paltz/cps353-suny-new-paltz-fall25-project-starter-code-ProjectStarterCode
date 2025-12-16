package testharness;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import project.checkpoint4.MultiThreadedCoordinationComponent;

public class TestMultiUser {

    // TODO 1: change the type of this variable to the name you're using for your @NetworkAPI
    // interface
    private MultiThreadedCoordinationComponent coordinator;

    @BeforeEach
    public void initializeComputeEngine() {
        // TODO 2: create an instance of the implementation of your @NetworkAPI; this is the component
        // that the user will make requests to
        // Store it in the 'coordinator' instance variable
        coordinator = new MultiThreadedCoordinationComponent();
    }

    @Test
    public void compareMultiAndSingleThreaded() throws Exception {
        int numThreads = 4;
        List<TestUser> testUsers = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            testUsers.add(new TestUser(coordinator));
        }

        // Run single threaded
        String singleThreadFilePrefix =
                "testMultiUser.compareMultiAndSingleThreaded.singleThreadOut.tmp";

        for (int i = 0; i < numThreads; i++) {
            File outFile = new File(singleThreadFilePrefix + i);
            outFile.deleteOnExit();
            testUsers.get(i).run(outFile.getCanonicalPath());
        }

        // Run multi threaded
        ExecutorService threadPool = Executors.newCachedThreadPool();
        List<Future<?>> results = new ArrayList<>();

        String multiThreadFilePrefix =
                "testMultiUser.compareMultiAndSingleThreaded.multiThreadOut.tmp";

        for (int i = 0; i < numThreads; i++) {
            File outFile = new File(multiThreadFilePrefix + i);
            outFile.deleteOnExit();
            String outputPath = outFile.getCanonicalPath();
            TestUser testUser = testUsers.get(i);
            results.add(threadPool.submit(() -> testUser.run(outputPath)));
        }

        for (Future<?> future : results) {
            future.get();
        }

        List<String> singleThreaded = loadAllOutput(singleThreadFilePrefix, numThreads);
        List<String> multiThreaded = loadAllOutput(multiThreadFilePrefix, numThreads);

        assertEquals(singleThreaded, multiThreaded);
    }

    private List<String> loadAllOutput(String prefix, int numThreads) throws IOException {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            File outFile = new File(prefix + i);
            result.addAll(Files.readAllLines(outFile.toPath()));
        }
        return result;
    }
}