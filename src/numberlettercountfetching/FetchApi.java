package numberlettercountfetching;

import java.math.BigInteger;
import java.util.List;
import project.annotations.NetworkAPI;

@NetworkAPI
public interface FetchApi {
	List<BigInteger> insertRequest(FetchRequest fetchRequest);
	boolean validateNumber(BigInteger number);

	// Process an input file end-to-end: read input, compute, and write output
	boolean processFile(String inputPath, String outputPath);
}