package project.checkpointtests;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;

public class TestStatusCheckPR {

    private static final String COMPLETED = "completed";
    private static final int NUM_CHECKS = 2;
    private static final String SUCCESS = "success";
    private static final String APPROVED = "APPROVED";

    @Test
    public void testPullRequest() throws Exception {
        String baseApiPath = getBaseApiPath();
        String toCurl = baseApiPath + "pulls?state=all";
        String pullRequests = curl(toCurl);

        // Skip if GitHub API is unavailable
        if (pullRequests.isEmpty() || pullRequests.equals("{}")) {
            System.out.println("️Skipping testPullRequest — GitHub API unavailable or rate-limited.");
            return;
        }

        boolean foundPullRequest = false;

        JsonArray prs = JsonParser.parseString(pullRequests).getAsJsonArray();
        for (JsonElement pr : prs) {
            String prNumber = pr.getAsJsonObject().get("number").getAsString();

            if (hasStatusChecks(baseApiPath, prNumber) &&
                hasReviewerApproval(baseApiPath, prNumber)) {
                foundPullRequest = true;
                break;
            }
        }

        // JUnit assertion
        Assertions.assertTrue(foundPullRequest, "No pull request met the criteria.");
    }

    // Query the git remote to find the repo URL
    private String getBaseApiPath() throws Exception {
        Process getRemote = new ProcessBuilder("git", "remote", "get-url", "origin", "--push").start();
        getRemote.waitFor();
        String output = new String(getRemote.getInputStream().readAllBytes());
        String ownerRepo = output.substring("https://github.com/".length());
        int removeTrailingGit = ownerRepo.lastIndexOf(".");
        if (removeTrailingGit < 0) {
            removeTrailingGit = ownerRepo.length() - 1;
        }
        ownerRepo = ownerRepo.substring(0, removeTrailingGit);

        return "https://api.github.com/repos/" + ownerRepo + "/";
    }

    private boolean hasReviewerApproval(String baseApiPath, String prNumber) throws Exception {
        String getReviews = baseApiPath + "pulls/" + prNumber + "/reviews";
        String reviewResult = curl(getReviews);

        if (reviewResult.isEmpty() || reviewResult.equals("{}")) return false;

        JsonArray reviews = JsonParser.parseString(reviewResult).getAsJsonArray();
        for (JsonElement review : reviews) {
            if (review.getAsJsonObject().get("state").getAsString().equals(APPROVED)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasStatusChecks(String baseApiPath, String prNumber) throws Exception {
        String getCommits = baseApiPath + "pulls/" + prNumber + "/commits";
        String commitResult = curl(getCommits);

        if (commitResult.isEmpty() || commitResult.equals("{}")) return false;

        JsonArray commitsArray = JsonParser.parseString(commitResult).getAsJsonArray();
        List<JsonElement> commits = new ArrayList<>();
        commitsArray.forEach(commits::add);

        if (commits.isEmpty()) {
            return false;
        }

        sortCommits(commits);

        JsonElement latestCommit = commits.get(0);
        Map<String, String> latestStatus = getStatusCheckResult(baseApiPath, latestCommit);

        if (latestStatus.size() != NUM_CHECKS) {
            return false;
        }
        for (String status : latestStatus.values()) {
            if (!status.equals(SUCCESS)) {
                return false;
            }
        }

        // Look for earlier failed checks
        Set<String> failuresFound = new HashSet<>();
        for (JsonElement commit : commits) {
            Map<String, String> result = getStatusCheckResult(baseApiPath, commit);
            result.forEach((check, status) -> {
                if (!status.equals(SUCCESS)) {
                    failuresFound.add(check);
                }
            });
        }

        return failuresFound.size() == NUM_CHECKS;
    }

    private void sortCommits(List<JsonElement> commits) {
        Collections.sort(commits, (c1, c2) -> {
            try {
                return -1 * getCommitDate(c1).compareTo(getCommitDate(c2));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private Date getCommitDate(JsonElement commit) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            .parse(commit.getAsJsonObject()
            .get("commit").getAsJsonObject()
            .get("committer").getAsJsonObject()
            .get("date").getAsString());
    }

    private Map<String, String> getStatusCheckResult(String baseApiPath, JsonElement commit) throws Exception {
        String sha = commit.getAsJsonObject().get("sha").getAsString();
        String getStatusChecks = baseApiPath + "commits/" + sha + "/check-runs";
        String statusCheckResult = curl(getStatusChecks);
        Map<String, String> checkToStatus = new HashMap<>();

        if (statusCheckResult.isEmpty() || statusCheckResult.equals("{}")) return checkToStatus;

        JsonArray checks = JsonParser.parseString(statusCheckResult)
            .getAsJsonObject().get("check_runs").getAsJsonArray();

        for (JsonElement check : checks) {
            String name = check.getAsJsonObject().get("name").getAsString();
            String status = check.getAsJsonObject().get("status").getAsString();
            if (status.equals(COMPLETED)) {
                String result = check.getAsJsonObject().get("conclusion").getAsString();
                checkToStatus.put(name, result);
            }
        }

        return checkToStatus;
    }

    private String curl(String toCurl) throws Exception {
        try {
            URL url = new URI(toCurl).toURL();
            StringBuilder result = new StringBuilder();

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(url.openStream(), "UTF-8"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line).append("\n");
                }
            }
            return result.toString();
        } catch (IOException e) {
            System.err.println("Unable to fetch URL: " + toCurl);
            System.err.println("Error: " + e.getMessage());
            // Return empty object to prevent crash
            return "{}";
        }
    }
}

