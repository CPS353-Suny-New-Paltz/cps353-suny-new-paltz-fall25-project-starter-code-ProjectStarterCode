//conceptual api
package projectapis;
import project.annotations.ConceptualAPI;

@ConceptualAPI
public interface FactorialAPI{
	//doing the actual computation
	long computeDigitFactorialSum(int number);
}
