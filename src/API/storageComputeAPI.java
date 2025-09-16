package API;

import java.util.List;

public interface storageComputeAPI {

	List<Integer> read(String source);

	void write(String destination, List<Integer> data, String delimiters);

}
