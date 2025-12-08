package networkapi;

import project.annotations.NetworkAPIPrototype;

/**
 * Simple prototype demonstrating how a client would configure the UserAPI
 * before starting any network activity.
 */
public class UserAPIPrototype {

    @NetworkAPIPrototype
    public void prototype(UserAPI userapi) {
        String input = userapi.setInput("file://inputs.txt");
        String output = userapi.setOutput("file://outputs.txt");
        String[] delimiters = userapi.useDefaultDelimiters();

        // Prototype output to illustrate the workflow
        System.out.println("Prototype configured input: " + input);
        System.out.println("Prototype configured output: " + output);
        System.out.println("Prototype using delimiters: pair='"
                + delimiters[0] + "', kv='" + delimiters[1] + "'");
    }
}
