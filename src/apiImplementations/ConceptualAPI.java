package apiImplementations;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import conceptual.api.ConceptualApi;
import conceptual.api.Job;
import conceptual.api.JobRequest;
import conceptual.api.JobResponse;
import conceptual.api.JobStatus;

/**
 * Implementation of the ConceptualApi interface
 */
public class ConceptualAPI<T> implements ConceptualApi {

  // Tracks submitted jobs jobId -> Job instance
  private Map<String, Job<?>> jobRegistry;

  // Tracks status of each job jobId -> JobStatus
  private Map<String, JobStatus> jobStatuses;

  public ConceptualAPI() {
    this.jobRegistry = new HashMap<>();
    this.jobStatuses = new HashMap<>();
  }

  @Override
  public JobResponse<T> submitJob(JobRequest request) {

    return new JobResponse<T>(UUID.randomUUID().toString(), JobStatus.FAILED,
        null);
  }

  @Override
  public JobResponse<T> checkStatus(String jobId) {
    return new JobResponse<T>(UUID.randomUUID().toString(), JobStatus.FAILED,
        null, null);
  }
}
