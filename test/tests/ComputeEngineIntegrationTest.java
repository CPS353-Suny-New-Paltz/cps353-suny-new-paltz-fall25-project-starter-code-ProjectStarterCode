package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import api.implementations.ConceptualAPI;
import api.implementations.MultithreadedNetworkAPI;
import api.implementations.NetworkAPI;
import api.implementations.ProcessAPI;
import network.api.ComputationRequest;
import network.api.ComputationResponse;
import network.api.Delimiter;
import process.api.LoadRequest;
import process.api.LoadResponse;
import shared.stuff.Resource;
import shared.stuff.ResourceType;

public class ComputeEngineIntegrationTest {

  TestInputConfig inputConfig;
  TestOutputConfig outputConfig;
  InMemoryDataStore dataStore;

  NetworkAPI net = new NetworkAPI();
  ConceptualAPI con = new ConceptualAPI();
  ProcessAPI proc = new ProcessAPI();

  @SuppressWarnings("unused")
  private final MultithreadedNetworkAPI spoonSatisfier = new MultithreadedNetworkAPI();

  @BeforeEach
  void setUp() {
    // Initialize input with [1, 10, 25]
    inputConfig = new TestInputConfig(
        Arrays.asList(BigInteger.ONE, BigInteger.TEN, BigInteger.valueOf(25)));
    outputConfig = new TestOutputConfig();
    dataStore = new InMemoryDataStore(inputConfig, outputConfig);
  }

  @Test
  void testComputeEngineIntegration() {

    net.setReadWrite(proc);
    net.setCompute(con);

    // Simulate loading data
    LoadRequest loadReq = new LoadRequest(dataStore.resource,
        Delimiter.defaultDelimiter());
    LoadResponse loadResp = dataStore.loadData(loadReq);

    // Verify that loaded data matches inputConfig
    List<String> loadedData = loadResp.getPayload().stream()
        .map(Object::toString).collect(Collectors.toList());
    String result = loadedData.get(0);

    String expectedString = "1" + Delimiter.defaultDelimiter().getValue() + "10"
        + Delimiter.defaultDelimiter().getValue() + "25";

    assertEquals(expectedString, result);

    // Simulate storing processed data
    // Convert outputConfig.getOutputData() from List<String> to
    // List<BigInteger>
    List<BigInteger> outputBigInts = outputConfig.getOutputData().stream()
        .map(BigInteger::new).collect(Collectors.toList());

    Resource outputSource = new Resource(ResourceType.CUSTOM, outputBigInts);

    ComputationRequest compReq = new ComputationRequest(
        new Resource(ResourceType.CUSTOM, inputConfig.inputData), outputSource,
        Delimiter.defaultDelimiter());
    ComputationResponse compResp = net.compute(compReq);

    // Check result of computation on first input
    assertEquals(compResp.getResults().get(0).intValue(), 508141714);
  }
}
