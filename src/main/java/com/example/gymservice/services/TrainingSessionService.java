package com.example.gymservice.services;

import com.example.gymservice.model.requests.SessionQuery;
import com.example.gymservice.model.response.SessionSearchResponse;

import java.util.List;

/**
 * @author: adewaleijalana
 * @email: adewaleijalana@gmail.com
 * @date: 11/26/23
 **/


public interface TrainingSessionService {
    List<SessionSearchResponse> getTrainingSessions(SessionQuery sessionQuery);
}
