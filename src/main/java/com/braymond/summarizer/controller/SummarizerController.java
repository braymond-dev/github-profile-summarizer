package com.braymond.summarizer.controller;

import com.braymond.summarizer.model.GitHubSummary;
import com.braymond.summarizer.service.SummarizerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RestController
public class SummarizerController {

    private final SummarizerService summarizerService;

    public SummarizerController(SummarizerService summarizerService) {
        this.summarizerService = summarizerService;
    }

    @GetMapping("/summary")
    public ResponseEntity<GitHubSummary> summarize(@RequestParam("username") String username) {
        if (username == null || username.isBlank()) {
            return ResponseEntity.badRequest().body(null);
        }
        try {
            GitHubSummary summary = summarizerService.summarizeProfile(username);
            return ResponseEntity.ok(summary);
        } catch (Exception e) {
            Throwable cause = e.getCause();
            if (cause instanceof WebClientResponseException.NotFound) {
                //pass-through a 404 to the user
                return ResponseEntity.status(((WebClientResponseException.NotFound) cause).getStatusCode()).body(null);
            }
            return ResponseEntity.internalServerError().body(null);
        }
    }

}
