package conceptapi;

import java.io.IOException;
import java.util.HashMap;

import project.annotations.ConceptualAPIPrototype;



public class ConceptAPIPrototype {
@ConceptualAPIPrototype
	public void prototype(ConceptAPI component) {
//User Component user component will take in the processAPI and the userRequest as parameters and
//create readable data for the Computation component to complete the computation
	int rand = (int) ((Math.random() * 2000) + 1);// create random jobID between 1 -2000
	int jobID = rand;
	HashMap<Integer, String> values ;
	try {
		UserInterface userComponent = new UserComponent();
		values = userComponent.getValues(jobID);

		ComputeComponent computation = new ComputeValues();
		int result=computation.computeValues(values, jobID);
		System.out.println("Computation result is: "+ result);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}//creates hashmap with valueB generated along with jobID assigned
	//Computation Component
}
}
