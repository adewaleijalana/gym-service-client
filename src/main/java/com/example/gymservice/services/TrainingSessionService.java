package com.example.gymservice.services;

import com.example.gymservice.model.requests.SessionQuery;

/**
 * @author: adewaleijalana
 * @email: adewaleijalana@gmail.com
 * @date: 11/26/23
 **/


public interface TrainingSessionService {
    void getTrainingSessions(SessionQuery sessionQuery);
}
