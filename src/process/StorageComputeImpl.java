package process;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StorageComputeImpl implements StorageComputeAPI {

	private static final int MAX_ITERATIONS = 1000;
	
	@Override
    public DataValue readInput(String inputSource) {
        try {
            List<String> inputLines = Files.readAllLines(Paths.get(inputSource))
                                           .stream()
                                           .filter(line -> !line.trim().isEmpty())
                                           .collect(Collectors.toList());

            if (inputLines.isEmpty()) {
                return new DataValueImpl(-1);
            }

            String line = inputLines.remove(0).trim();
            int value = Integer.parseInt(line);

            Files.write(Paths.get(inputSource), inputLines);

            return new DataValueImpl(value);

        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
            return new DataValueImpl(-1);
        } catch (NumberFormatException e) {
            return new DataValueImpl(-1);
        }
    }
	
	@Override
	public MultiDataValue readAllInputs(String inputSource) {
        try {
            List<String> inputLines = Files.readAllLines(Paths.get(inputSource))
                                           .stream()
                                           .filter(line -> !line.trim().isEmpty())
                                           .collect(Collectors.toList());

            List<Integer> values = inputLines.stream()
                                             .map(line -> {
                                                 try {
                                                     return Integer.parseInt(line.trim());
                                                 } catch (NumberFormatException e) {
                                                     return -1;
                                                 }
                                             })
                                             .collect(Collectors.toList());

            return new MultiDataValueImpl(values);

        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
            return new MultiDataValueImpl(Collections.emptyList());	// fallback
        }
    }

    @Override
    public boolean writeAllOutputs(String outputDestination, List<Integer> results) {
        if (results == null || results.isEmpty()) {
        	return false;
        }

        try {
            StringBuilder outputLine = new StringBuilder();
            int iterations = 0;

            for (Integer result : results) {
                if (iterations >= MAX_ITERATIONS) {
                	break;
                }
                if (outputLine.length() > 0) {
                	outputLine.append(",");
                }
                outputLine.append(result);
                iterations++;
            }

            Files.write(Paths.get(outputDestination),
                        Collections.singletonList(outputLine.toString()));

            return true; 

        } catch (IOException e) {
            System.err.println("Error writing output file: " + e.getMessage());
            return false; 
        }
    }
    
    @Override
    public boolean writeOutput(String filePath, DataValue value) {
        if (value == null) {
        	return false;
        }

        try {
            Files.write(Paths.get(filePath),
                        Collections.singletonList(String.valueOf(value.getValue())));
            return true;
        } catch (IOException e) {
            System.err.println("Error writing output file: " + e.getMessage());
            return false;
        }
    }
}