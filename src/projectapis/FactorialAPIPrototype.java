package projectapis;

import project.annotations.ConceptualAPIPrototype;

public class FactorialAPIPrototype {

    @ConceptualAPIPrototype
    public static void prototype(FactorialAPI api) {
        // pretend client usage
        long result = api.computeDigitFactorialSum(0);
    }
}
