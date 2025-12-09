import com.braymond.summarizer.client.GithubClient;
import com.braymond.summarizer.model.UserProfile;
import org.springframework.stereotype.Service;

package com.braymond.summarizer.service;


@Service
public class SummarizerService {

    private final GithubClient githubClient;

    public SummarizerService(GithubClient githubClient) {
        this.githubClient = githubClient;
    }

    public UserProfile summarizeProfile(String username) {
        return githubClient.getUser(username);
    }
}