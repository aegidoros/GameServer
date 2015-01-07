/*
 *   SessionCleanerScheduler.java
 * 
 * Copyright(c) 2014 Christian Delgado. All Rights Reserved.
 * This software is the proprietary information of Christian Delgado
 * 
 */
package com.king.backend.core.schedulers;

import com.king.backend.core.managers.SessionManager;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Class created to be execute in a thread to clean all the sessions expired.
 *
 * @author Christian Delgado
 * @version 1.0
 * @date 12/28/14
 */
public class SessionCleanerScheduler implements Runnable {

    /**
     * Instance for the SessionManager,
     * where all the session data are stored.
     */
    private final SessionManager sessionManager;

    /**
     * Creates a new instance of SessionCleanerScheduler
     *
     * @param sessionManager
     */
    public SessionCleanerScheduler(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    /**
     * Method to start the Thread to clean all the Invalid Sessions
     */
    public void startService() {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(this, SessionManager.TIME_TO_LIVE, SessionManager.TIME_TO_LIVE, TimeUnit.MILLISECONDS);
    }

    @Override
    public void run() {
        sessionManager.removeInvalidSessions();
    }
}

