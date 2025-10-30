package apiengine;

import apiimplementation.ConceptualAPIImpl;
import apinetwork.ComputationInput;
import org.junit.jupiter.api.Test;
import testhelpers.TestOutputConfig;
import testhelpers.TestInputConfig;
import testhelpers.TestProcessDataStore;

import java.util.Random;

public class FuzzFactorizationTest {
    @Test
    public void fuzz_test_printSeed() {
        long seed = System.currentTimeMillis();
        System.out.println("Fuzz seed: " + seed);
        Random rnd = new Random(seed);

        TestInputConfig in = new TestInputConfig(java.util.Collections.emptyList());
        TestOutputConfig out = new TestOutputConfig();
        TestProcessDataStore store = new TestProcessDataStore(in, out);
        ConceptualAPIImpl engine = new ConceptualAPIImpl(store);

        for (int i = 0; i < 500; i++) {
            int v = 1 + rnd.nextInt(100000);
            engine.compute(new ComputationInput(v, ":"));
        }
    }
}
