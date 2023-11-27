package com.example.gymservice.services.impl;

import com.example.gymservice.model.requests.SessionQuery;
import com.example.gymservice.model.response.SessionSearchResponse;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

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
    public List<SessionSearchResponse> getTrainingSessions(SessionQuery sessionQuery) {
        String fullSearchEndpoint = baseUrl + searchEndpoint;
        log.info("fullSearchEndpoint: {}", fullSearchEndpoint);

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(fullSearchEndpoint)
                        .queryParam("coachName", sessionQuery.getCoachName())
                        .queryParam("weekDays", sessionQuery.getWeekDays())
                        .build())
                .retrieve()
                .bodyToMono(TrainingSessions.class)
                .map(this::buildSearchResponse)
                .block();
    }

    private List<SessionSearchResponse> buildSearchResponse(TrainingSessions trainingSessions1) {
        log.info("training session: {}", gson.toJson(trainingSessions1));
        List<SessionSearchResponse> sessionSearchResponseList = new ArrayList<>();
        trainingSessions1.getTrainingSessions()
                .forEach(singleTrainingSession -> {
                    SessionSearchResponse sessionSearchResponse = SessionSearchResponse.builder()
                            .coachName(singleTrainingSession.getTrainingSession().getCoach().getName())
                            .gymName(singleTrainingSession.getTrainingSession().getGym().getName())
                            .weekDay(singleTrainingSession.getTrainingSession().getWeekday())
                            .memberNames(singleTrainingSession.getTrainingSession().getMember().getName())
                            .build();

                    sessionSearchResponseList.add(sessionSearchResponse);
                });

        Map<String, Map<String, Map<String, String>>> stringMapMap = sessionSearchResponseList.stream().
                collect(groupingBy(SessionSearchResponse::getWeekDay,
                        groupingBy(SessionSearchResponse::getGymName,
                                groupingBy(SessionSearchResponse::getCoachName,
                                        mapping(SessionSearchResponse::getMemberNames, joining(", "))
                                ))));

        List<SessionSearchResponse> sessionSearchResponses = new ArrayList<>();
        for (Map.Entry<String, Map<String, Map<String, String>>> entry: stringMapMap.entrySet()){
            SessionSearchResponse sessionSearchResponse = SessionSearchResponse.builder()
                    .weekDay(entry.getKey())
                    .build();
            for (Map.Entry<String, Map<String, String>> entryValue : entry.getValue().entrySet()){
                sessionSearchResponse.setGymName(entryValue.getKey());
                    for (Map.Entry<String, String> coachMember : entryValue.getValue().entrySet()){
                    sessionSearchResponse.setCoachName(coachMember.getKey());
                    sessionSearchResponse.setMemberNames(coachMember.getValue());
                }
            }
            sessionSearchResponses.add(sessionSearchResponse);

        }

        log.info("training session search response: {}", gson.toJson(sessionSearchResponses));
        return sessionSearchResponses;
    }
}
