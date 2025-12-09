package com.braymond.summarizer.client;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.braymond.summarizer.model.GitHubRepo;
import com.braymond.summarizer.model.GitHubUser;

import reactor.core.publisher.Mono;

@Component
public class GitHubClient {

    private final WebClient webClient;

    public GitHubClient(WebClient.Builder builder) {
        this.webClient = builder
                .baseUrl("https://api.github.com")
                .defaultHeader("User-Agent", "github-profile-summarizer")
                .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public Mono<GitHubUser> getUser(String username) {
        return webClient
                .get()
                .uri("/users/{username}", username)
                .retrieve()
                .bodyToMono(GitHubUser.class);
    }

    public Mono<List<GitHubRepo>> getUserRepos(String username) {
        return webClient
                .get()
                .uri("/users/{username}/repos", username)
                .retrieve()
                .bodyToFlux(GitHubRepo.class)
                .collectList();
    }
}
