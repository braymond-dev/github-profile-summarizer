package com.braymond.summarizer.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SummarizerController {

    @GetMapping("/username")
    public ResponseEntity<Map<String, String>> summarize(@RequestParam("username") String username) {
        if (username == null || username.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "username is required"));
        }

        // Placeholder response until GitHub profile summarization is wired up.
        return ResponseEntity.ok(Map.of(
                "username", username,
                "summary", "GitHub profile summary not implemented yet"));
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
