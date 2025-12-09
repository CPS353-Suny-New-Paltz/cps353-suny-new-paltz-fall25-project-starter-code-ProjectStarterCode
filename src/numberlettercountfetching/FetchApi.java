package numberlettercountfetching;

import java.util.List;
import project.annotations.NetworkAPI;

@NetworkAPI
public interface FetchApi {
	List<Integer> insertRequest(FetchRequest fetchRequest);
	boolean validateNumber(int number);
}