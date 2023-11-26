package com.example.gymservice.services.impl;

import com.example.gymservice.model.requests.SessionQuery;
import com.example.gymservice.model.response.TrainingSessions;
import com.example.gymservice.services.TrainingSessionService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author: adewaleijalana
 * @email: adewaleijalana@gmail.com
 * @date: 11/26/23
 **/

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainingSessionServiceImpl implements TrainingSessionService {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final WebClient webClient;

    @Value("${gym.service.baseUrl}")
    private String baseUrl;

    @Value("${gym.service.searchSession}")
    private String searchEndpoint;

    @Override
    public void getTrainingSessions(SessionQuery sessionQuery) {
        String fullSearchEndpoint = baseUrl + searchEndpoint;
        TrainingSessions trainingSessions = webClient
                .method(HttpMethod.GET)
                .uri(fullSearchEndpoint)
                .body(Mono.just(sessionQuery), SessionQuery.class)
                .retrieve()
                .bodyToMono(TrainingSessions.class)
                .block();

        log.info("training session: {}", gson.toJson(trainingSessions));
    }
}
