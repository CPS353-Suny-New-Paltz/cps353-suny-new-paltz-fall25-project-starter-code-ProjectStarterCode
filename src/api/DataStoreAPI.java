package api;

import java.util.List;

public interface DataStoreAPI {

	List<Integer> read(String loc);

	void write(String loc, List<String> lines);
	 	
}
