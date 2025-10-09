package project.checkpointtests;

import project.datacompute.DataComputeAPI;
import project.datacompute.DataComputeAPIFileImpl;
import project.intercompute.InterComputeAPI;
import project.intercompute.InterComputeAPIImpl;
import project.usercompute.UserComputeAPI;
import project.usercompute.UserComputeAPIImpl;


public class ManualTestingFramework {
    
    public static final String INPUT = "manualTestInput.txt";
    public static final String OUTPUT = "manualTestOutput.txt";

    public static void main(String[] args) {
        // TODO 1:
        // Instantiate a real (ie, class definition lives in the src/ folder) implementation 
        // of all 3 APIs
        
    	DataComputeAPI data = new DataComputeAPIFileImpl();
    	InterComputeAPI inter = new InterComputeAPIImpl(data);
    	UserComputeAPI user = new UserComputeAPIImpl(inter, data);
    	
        // TODO 2:
        // Run a computation with an input file of <root project dir>/manualTestInput.txt
        // and an output of <root project dir>/manualTestOutput.txt, with a delimiter of ',' 
    	
    	//this references datacomputeapi
    	user.handle(INPUT, OUTPUT);
    	
        // Helpful hint: the working directory of this program is <root project dir>,
        // so you can refer to the files just using the INPUT/OUTPUT constants. You do not 
        // need to (and should not) actually create those files in your repo
    }
}