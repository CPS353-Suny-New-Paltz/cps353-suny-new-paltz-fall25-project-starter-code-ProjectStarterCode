package project.networkapi;

import project.annotations.NetworkAPIPrototype;

/**
 * Prototype implementation of the UserComputeEngineAPI interface.
 * This class provides a mock implementation that demonstrates how the API
 * would be used without performing actual computations. It's annotated with
 * @NetworkAPIPrototype to indicate it's a prototype implementation.
 * 
 * Purpose:
 * - Demonstrates the API usage pattern and workflow
 * - Returns mock responses for testing and development
 * - Allows clients to test the submission flow without real computation
 * - Serves as a template for the real implementation
 * 
 * Note: This implementation does not perform actual factorial calculations
 * or other computations - it returns predetermined mock responses.
 */
public class UserComputeEngineAPIPrototype implements UserComputeEngineAPI {
	/**
	 * This method demonstrates the job submission flow without performing
	 * actual computations. It handles delimiter defaults and returns a
	 * mock JobResponse with data about the submitted job.
	 *
	 * @param jobRequest The job request containing input, output, and delimiter specifications
	 * @return JobResponse with a success message and job configuration details
	 */
	@Override 
	@NetworkAPIPrototype
	public JobResponse submitJob(JobRequest jobRequest) {
		// If no custom delimiters were provided, fall back to system defaults
		// This ensures we always have valid delimiters for formatting
		DelimiterPair delimiters = jobRequest.getDelimitersOrDefault();

		// Return a mock response - in a real implementation, this would contain
		// actual computation results and execution details
		return new JobResponse(
				"Prototype job submitted successfully - no actual computation performed",
				jobRequest.getInputSource().toString(),
				jobRequest.getOutputSource().toString(),
				delimiters
				);
	}
}