package com.aer.backend.controller;

import com.aer.backend.exception.AuthorizationException;
import com.aer.backend.model.HighScore;
import com.aer.backend.model.Session;
import com.aer.backend.model.UserScore;
import com.aer.backend.scheduler.SessionCleanerScheduler;
import com.aer.backend.service.ScoreService;
import com.aer.backend.service.SessionService;

/**
 * Singleton Class used as DataController to manage all the Service Request
 * and all the data for the Mini-Game BackEnd server.
 *
 * @author Alberto Egido
 * @version 1.0
 * @date 22/05/20
 */
public class GameController {

  /**
   * Singleton instance
   */
  public volatile static GameController instance;
  /**
   * Instance for the ScoreService,
   * where all the score data are stored.
   */
  private final ScoreService scoreService;
  /**
   * Instance for the SessionService,
   * where all the session data are stored.
   */
  private final SessionService sessionService;
  /**
   * Instance for the SessionCleanerScheduler,
   * executing thread to clean all the sessions expired.
   */
  private final SessionCleanerScheduler sessionCleaner;

  /**
   * Creates a new instance of GameController
   */
  public GameController() {
    scoreService = new ScoreService();
    sessionService = new SessionService();
    sessionCleaner = new SessionCleanerScheduler(sessionService);
    sessionCleaner.startService();
  }

  /**
   * Obtain the instance for the singleton
   *
   * @return the instance initialized
   */
  public static GameController getInstance() {
    if (instance == null) {
      synchronized (GameController.class) {
        if (instance == null) {
          instance = new GameController();
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
    Session session = sessionService.createNewSession(userId);
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
    if (!sessionService.isSessionValid(sessionKey)) {
      throw new AuthorizationException();
    }
    Session session = sessionService.getSessionActive(sessionKey);
    if (session == null) {
      throw new AuthorizationException();
    }
    UserScore userScore = new UserScore(session.getUserId(), score);
    scoreService.saveScore(userScore, levelId);
  }

  /**
   * HighScoreList Service Request
   *
   * @param levelId level to print the High Score List
   * @return CSV of <userid>=<score>
   */
  public String highScoreList(int levelId) {
    HighScore highScore = scoreService.getHighScore(levelId);
    return highScore.toString();
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException();
  }
}
