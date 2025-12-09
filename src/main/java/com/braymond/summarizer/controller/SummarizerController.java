package com.braymond.summarizer.controller;

import com.braymond.summarizer.model.GitHubSummary;
import com.braymond.summarizer.service.SummarizerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@RestController
public class SummarizerController {

    private static final Logger log = LoggerFactory.getLogger(SummarizerController.class);

    private final SummarizerService summarizerService;

    public SummarizerController(SummarizerService summarizerService) {
        this.summarizerService = summarizerService;
    }

    @GetMapping("/summary")
    public ResponseEntity<GitHubSummary> summarize(@RequestParam("username") String username) {
        if (username == null || username.isBlank()) {
            log.warn("summary requested with missing/blank username");
            return ResponseEntity.badRequest().body(null);
        }
        try {
            log.info("summary request for user={}", username);
            GitHubSummary summary = summarizerService.summarizeProfile(username);
            return ResponseEntity.ok(summary);
        } catch (Exception e) {
            Throwable cause = e.getCause();
            if (cause instanceof WebClientResponseException wcre && wcre.getStatusCode().value() == 404) {
                // pass-through a 404 to the user
                log.info("github user not found: {}", username);
                return ResponseEntity.status(wcre.getStatusCode()).body(null);
            }
            log.error("failed to summarize user={}", username, e);
            return ResponseEntity.internalServerError().body(null);
        }
    }

}
