package api;

import java.util.List;

import project.annotations.ProcessAPI;

@ProcessAPI
public interface StorageComputeAPI {

	List<Integer> read(String source);

	void write(String destination, List<Integer> data, String delimiters);

}
