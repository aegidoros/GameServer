/*
 *   GameManager.java
 * 
 * Copyright(c) 2014 Christian Delgado. All Rights Reserved.
 * This software is the proprietary information of Christian Delgado
 * 
 */
package com.king.backend.core.managers;

import com.king.backend.core.exceptions.AuthorizationException;
import com.king.backend.core.models.HighScore;
import com.king.backend.core.models.Session;
import com.king.backend.core.models.UserScore;
import com.king.backend.core.schedulers.SessionCleanerScheduler;

/**
 * Singleton Class used as DataManager where who manage all the Service Request
 * and all the data for the Mini-Game BackEnd server.
 *
 * @author Christian Delgado
 * @version 1.0
 * @date 12/27/14
 */
public class GameManager {

    /**
     * Singleton instance
     */
    public volatile static GameManager instance;
    /**
     * Instance for the ScoreManager,
     * where all the score data are stored.
     */
    private final ScoreManager scoreManager;
    /**
     * Instance for the SessionManager,
     * where all the session data are stored.
     */
    private final SessionManager sessionManager;
    /**
     * Instance for the SessionCleanerScheduler,
     * executing thread to clean all the sessions expired.
     */
    private final SessionCleanerScheduler sessionCleaner;


    /**
     * Creates a new instance of GameManager
     */
    public GameManager() {
        scoreManager = new ScoreManager();
        sessionManager = new SessionManager();
        sessionCleaner = new SessionCleanerScheduler(sessionManager);
        sessionCleaner.startService();
    }


    /**
     * Obtain the instance for the singleton
     *
     * @return the instance initialized
     */
    public static GameManager getInstance() {
        if (instance == null) {
            synchronized (GameManager.class) {
                if (instance == null) {
                    instance = new GameManager();
                }
            }
        }
        return instance;
    }

    /**
     * Login Service Request
     *
     * @param userId userId who want to make a login
     * @return String with the sessionKey for the active session
     */
    public String login(int userId) {
        Session session = sessionManager.createNewSession(userId);
        return session.getSessionKey();
    }

    /**
     * Score Service Request
     *
     * @param sessionKey sessionKey for a valid and active Session
     * @param levelId    level to insert the score
     * @param score      point to insert in the level
     * @throws AuthorizationException throw this if the session in invalid
     */
    public void score(String sessionKey, int levelId, int score) throws AuthorizationException {
        if (!sessionManager.isSessionValid(sessionKey)) {
            throw new AuthorizationException();
        }
        Session session = sessionManager.getSessionActive(sessionKey);
        if (session == null) {
            throw new AuthorizationException();
        }
        UserScore userScore = new UserScore(session.getUserId(), score);
        scoreManager.saveScore(userScore, levelId);
    }

    /**
     * HighScoreList Service Request
     *
     * @param levelId level to print the High Score List
     * @return  CSV of <userid>=<score>
     */
    public String highScoreList(int levelId) {
        HighScore highScore = scoreManager.getHighScore(levelId);
        return highScore.toString();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
