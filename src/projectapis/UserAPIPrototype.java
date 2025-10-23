package projectapis;

import project.annotations.NetworkAPIPrototype;

public class UserAPIPrototype {

    @NetworkAPIPrototype
    public static void prototype(UserAPI api) {
        // pretend client usage
    	api.setInput("input.txt");
        api.setOutput("output.txt");
        
        //not useful to my project but optional
        api.setDelimiter(",");
        api.executeComputation();

    }
}
