package numberlettercountfetching;

import java.util.Arrays;
import java.util.List;

public class ListFetchRequest extends FetchRequest {
    public ListFetchRequest(List<Integer> numbers) {
    	super(numbers);
        
    }
    
    
    public List<Integer> getListData() {
        return (List<Integer>) getData();
    }
    
    
}
