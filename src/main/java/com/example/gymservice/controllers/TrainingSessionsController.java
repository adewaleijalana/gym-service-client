package com.example.gymservice.controllers;

import com.example.gymservice.model.requests.SessionQuery;
import com.example.gymservice.services.TrainingSessionService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author: adewaleijalana
 * @email: adewaleijalana@gmail.com
 * @date: 11/26/23
 **/

@Slf4j
@Controller
@RequiredArgsConstructor
public class TrainingSessionsController {

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final TrainingSessionService trainingSessionService;

    @GetMapping("/sessions")
    public String showSession(Model model) {
        return "sessions";
    }

    @PostMapping("/searchSession")
    public String searchSession(SessionQuery sessionQuery, Model model) {
        log.info("sessionQuery in searchSession: {}", gson.toJson(sessionQuery));
        trainingSessionService.getTrainingSessions(sessionQuery);
        return "sessions";
    }

    @GetMapping("/search")
    public String search(SessionQuery sessionQuery) {
        log.info("sessionQuery in search: {}", gson.toJson(sessionQuery));
        return "search-coach";
    }
}
