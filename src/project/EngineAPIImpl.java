package project;
import java.util.List;
import project.annotations.EngineAPI;
import project.checkpoint4.ComputationComponent;

public class EngineAPIImpl implements EngineAPI {

    @Override
    public String compute(int n) {
        if (n <= 2) {
            return "";
        }
        return calculatePrimes(n);
    }

    @Override
    public String calculatePrimes(int limit) {
        ComputationComponent computation = new ComputationComponent();
        List<Integer> primes = computation.compute(limit);
        if (primes == null || primes.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < primes.size(); i++) {
            builder.append(primes.get(i));
            if (i < primes.size() - 1) {
                builder.append(", ");
            }
        }
        return builder.toString().trim();
    }
}
