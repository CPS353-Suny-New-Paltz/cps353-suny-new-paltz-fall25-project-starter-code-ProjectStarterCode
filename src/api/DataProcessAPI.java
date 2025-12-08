package api;
import java.util.ArrayList;

import project.annotations.ProcessAPI;

@ProcessAPI
public interface DataProcessAPI {


	ReadInput input(ArrayList<Integer> sendList);

	WriteOutput sendData(ArrayList<Integer> dataOut);

}

