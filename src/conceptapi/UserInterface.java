package conceptapi;

import java.io.IOException;
import java.util.HashMap;

public interface UserInterface {
HashMap<Integer, String>getValues(int jobID) throws IOException;
}
