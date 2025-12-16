package project.annotations;

public class EnginePrototype {

    @ConceptualAPIPrototype
    public String prototype(EngineAPI computeApi) {
        String result = computeApi.compute(42);
        return result;
    }
}
