package project.networkapi;

import project.annotations.NetworkAPIPrototype;

public class NetworkAPIClient {
    @NetworkAPIPrototype
    public void prototype(UserComputeAPI api) {
        // construct a submission and call submit
        InputSource input = new InputSource("file", "input.txt");
        OutputSource output = new OutputSource("output.txt");
        Delimiter delimiter = new Delimiter(',');
        UserSubmission submission = new UserSubmission(input, output, delimiter);


        api.submit(submission);
    }
}