/*
 *   ScoreManager.java
 * 
 * Copyright(c) 2014 Christian Delgado. All Rights Reserved.
 * This software is the proprietary information of Christian Delgado
 * 
 */
package com.king.backend.core.managers;

import com.king.backend.core.models.HighScore;
import com.king.backend.core.models.UserScore;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Manager Class where all the score data are stored.
 *
 * @author Christian Delgado
 * @version 1.0
 * @date 12/27/14
 */
public class ScoreManager {

    /**
     * ConcurrentMap<levelId, HighScore> with all the HighScore for all the Levels
     */
    private ConcurrentMap<Integer, HighScore> highScoresMap;

    /**
     * Creates a new instance of ScoreManager
     */
    public ScoreManager() {
        highScoresMap = new ConcurrentHashMap<>();
    }

    /**
     * Method where save a new UserScore in a Level
     * @param userScore new UserScore to save
     * @param levelId level to add the UserScore
     */
    public synchronized void saveScore(final UserScore userScore, final Integer levelId) {
        HighScore highScore = highScoresMap.get(levelId);
        if (highScore == null) {
            highScore = new HighScore();
            highScoresMap.putIfAbsent(levelId, highScore);
        }
        highScore.add(userScore);
    }

    /**
     * Method to get HighScore for a specific level
     * @param levelId level to get the HighScore
     * @return the HighScore for the selected level
     */
    public HighScore getHighScore(final int levelId) {
        if (!highScoresMap.containsKey(levelId)) {
            return new HighScore();
        }
        return highScoresMap.get(levelId);
    }


}
