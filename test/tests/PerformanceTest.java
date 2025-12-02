package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import api.implementations.ConceptualAPI;
import api.implementations.MultithreadedNetworkAPI;
import api.implementations.ProcessAPI;
import network.api.ComputationRequest;
import network.api.ComputationResponse;
import network.api.Delimiter;
import shared.stuff.ApiStatus;
import shared.stuff.Resource;
import shared.stuff.ResourceType;
import shared.stuff.TimingLogger;

public class PerformanceTest {

  @Test
  public void testComputeVsComputeFast() {
    // Prepare input data
    List<Integer> inputData = new ArrayList<>();
    for (int i = 0; i < 20; i++) { // Increase iterations for better measurement
      inputData.add(i);
    }

    Resource inputResource = new Resource(ResourceType.CUSTOM, inputData);
    Resource outputResource = new Resource(ResourceType.CUSTOM,
        new ArrayList<>());

    ComputationRequest request = new ComputationRequest(inputResource,
        outputResource, Delimiter.defaultDelimiter());

    // Setup coordinator with real APIs
    MultithreadedNetworkAPI coordinator = new MultithreadedNetworkAPI();
    coordinator.setCompute(new ConceptualAPI());
    coordinator.setReadWrite(new ProcessAPI());

    // normal version
    TimingLogger.reset();
    int runs = 5; // number of times to run
    for (int i = 0; i < runs; i++) {
      ComputationResponse resp = coordinator.compute(request);
      if (resp.getStatus() != ApiStatus.SUCCESS) {
        System.out.println(
            "Original compute run " + i + " failed: " + resp.getMessage());
      }
    }
    long originalCompPhaseTotal = TimingLogger
        .getTotalTime("Computation Phase");

    // fast
    TimingLogger.reset();
    for (int i = 0; i < runs; i++) {
      ComputationResponse resp = coordinator.computeFast(request);
      if (resp.getStatus() != ApiStatus.SUCCESS) {
        System.out
            .println("Fast compute run " + i + " failed: " + resp.getMessage());
      }
    }
    long fastCompPhaseTotal = TimingLogger.getTotalTime("Computation Phase");

    // Calculate percentage improvement
    double improvement = ((double) (originalCompPhaseTotal - fastCompPhaseTotal)
        / originalCompPhaseTotal) * 100.0;
    System.out.println("Computation Phase improvement: " + improvement + "%");

    // at least 10% improvement
    assertTrue(improvement >= 10.0,
        "Fast compute should be at least 10% faster than original compute");
  }
}
