/*
 *   SessionManager.java
 * 
 * Copyright(c) 2014 Christian Delgado. All Rights Reserved.
 * This software is the proprietary information of Christian Delgado
 * 
 */
package com.king.backend.core.managers;

import com.king.backend.core.models.Session;

import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Manager Class where all the Session data are stored.
 *
 * @author Christian Delgado
 * @version 1.0
 * @date 12/27/14
 */
public class SessionManager {

    /**
     * Static variable with the value for 10 minutes in millis,
     * for the max time for a session to be active.
     */
    public static final int TIME_TO_LIVE = 600000;

    /**
     * ConcurrentMap<sessionKey, Session> with all the Sessions actives in the server.
     */
    private final ConcurrentMap<String, Session> sessionActives;

    /**
     * Creates a new instance of SessionManager
     */
    public SessionManager() {
        sessionActives = new ConcurrentHashMap<>();
    }

    /**
     * Method used to create a new Session for the selected userId
     *
     * @param userId user to create the new Session
     * @return the Session created for the user selected
     */
    public synchronized Session createNewSession(final Integer userId) {
        final long now = System.currentTimeMillis();
        final String newSessionKey = UUID.randomUUID().toString().replace("-", "");
        final Session session = new Session(userId, newSessionKey, now);
        sessionActives.put(newSessionKey, session);
        return session;
    }

    /**
     * Method used to validate if an sessionKey is associated
     * with a Valid and Active Session in the Server
     *
     * @param sessionKey key for the Session to validate
     * @return a true if the sessionKey has a valid Session associated
     */
    public synchronized boolean isSessionValid(final String sessionKey) {
        Session sessionActive = sessionActives.get(sessionKey);
        if (sessionActive != null) {
            if ((System.currentTimeMillis() - sessionActive.getCreatedTime()) > TIME_TO_LIVE) {
                sessionActives.remove(sessionKey);
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * Method used to get the Session from the Map for the sessionKey selected
     *
     * @param sessionKey key for the Session to get
     * @return the Session for the sessionKey selected
     */
    public Session getSessionActive(final String sessionKey) {
        return sessionActives.get(sessionKey);
    }


    /**
     * Method used to remove all the invalid session from the server.
     */
    public synchronized void removeInvalidSessions() {
        long now = System.currentTimeMillis();
        for (Session session : new ArrayList<>(sessionActives.values())) {
            if ((now - session.getCreatedTime()) > TIME_TO_LIVE) {
                sessionActives.remove(session.getSessionKey());
            }
        }
    }
}
