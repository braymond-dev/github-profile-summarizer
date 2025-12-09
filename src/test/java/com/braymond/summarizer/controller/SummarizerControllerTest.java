package com.braymond.summarizer.controller;

import com.braymond.summarizer.model.GitHubSummary;
import com.braymond.summarizer.service.SummarizerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SummarizerController.class)
class SummarizerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SummarizerService summarizerService;

    @Test
    @DisplayName("returns summary for valid username")
    void returnsSummary() throws Exception {
        GitHubSummary summary = new GitHubSummary(
                "octocat",
                "The Octocat",
                "https://avatars.githubusercontent.com/u/583231?v=4",
                "San Francisco",
                null,
                "https://github.com/octocat",
                "2011-01-25T18:44:36Z",
                List.of(new GitHubSummary.Repo("Hello-World", "https://github.com/octocat/Hello-World"))
        );

        when(summarizerService.summarizeProfile("octocat")).thenReturn(summary);

        mockMvc.perform(get("/summary").param("username", "octocat"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("octocat"))
                .andExpect(jsonPath("$.repos[0].name").value("Hello-World"));
    }

    @Test
    @DisplayName("returns 404 when service raises not found")
    void returnsNotFound() throws Exception {
        when(summarizerService.summarizeProfile("missing"))
                .thenThrow(new ExecutionException("Error",
                        WebClientResponseException.create(
                                404, "Not Found", HttpHeaders.EMPTY, new byte[0], StandardCharsets.UTF_8)));

        mockMvc.perform(get("/summary").param("username", "missing"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("returns 400 when username missing")
    void returnsBadRequestForMissingUsername() throws Exception {
        mockMvc.perform(get("/summary"))
                .andExpect(status().isBadRequest());
    }
}
