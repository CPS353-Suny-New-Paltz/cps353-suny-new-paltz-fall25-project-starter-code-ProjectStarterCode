package benchmark;

import java.io.File;
import java.io.PrintWriter;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import conceptual.ComputeEngineImpl;
import conceptual.FastComputeEngineImpl;
import network.UserComputeImpl;
import process.StorageComputeImpl;

public class ComputeEngineBenchmarkTest {

    private static final int NUM_VALUES = 1_000_000; 
    private static final int MAX_VALUE = 1_000_000;

    private File createTempInputFile() throws Exception {
        File f = File.createTempFile("bench_input", ".txt");
        Random rand = new Random();
        try (PrintWriter pw = new PrintWriter(f)) {
            for (int i = 0; i < NUM_VALUES; i++) {
                int v = 2 + rand.nextInt(MAX_VALUE - 1);
                pw.println(v);
            }
        }
        f.deleteOnExit();
        return f;
    }

    private long runWithEngine(conceptual.ComputeEngineAPI engine, File inputFile, File outputFile) {
        UserComputeImpl uc = new UserComputeImpl(engine, new StorageComputeImpl());
        network.JobRequest req = new network.JobRequestImpl(inputFile.getAbsolutePath(), outputFile.getAbsolutePath(), ",");
        long start = System.nanoTime();
        network.JobResponse res = uc.submitJob(req);
        long end = System.nanoTime();
        if (!res.isSuccess()) {
            System.err.println("Run failed: " + res.getMessage());
        }
        return end - start;
    }

    @Test
    public void benchmarkOriginalVsFast() throws Exception {
        File input = createTempInputFile();
        File output1 = File.createTempFile("bench_out1", ".txt");
        File output2 = File.createTempFile("bench_out2", ".txt");
        output1.deleteOnExit();
        output2.deleteOnExit();

        System.out.println("Running original engine...");
        long t1 = runWithEngine(new ComputeEngineImpl(), input, output1);

        Thread.sleep(500); // allow threads to settle

        System.out.println("Running fast sieve-based engine...");
        long t2 = runWithEngine(new FastComputeEngineImpl(), input, output2);

        double seconds1 = t1 / 1e9;
        double seconds2 = t2 / 1e9;

        System.out.println(String.format("Original time: %.3f s, Fast time: %.3f s", seconds1, seconds2));

        // assert at least 10% faster
        Assertions.assertTrue(t2 <= 0.9 * t1,
                String.format("Fast implementation was not at least 10%% faster: original=%.3fs fast=%.3fs",
                        seconds1, seconds2));
    }
}
