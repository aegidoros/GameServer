package com.aer.backend.httpserver;

/**
 * Class used to store all the common constants
 *
 * @author Alberto Egido
 * @version 1.0
 * @date 22/05/20
 */
public class Constants {

  /*
   *  Value for the Parameters previously treated in the BackEndHttpFilter
   */
  public static final String PARAMETER_ATTRIBUTE = "parameters";
  public static final String REQUEST_PARAMETER = "request";
  public static final String LEVEL_ID_PARAMETER = "levelid";
  public static final String SESSION_KEY_PARAMETER = "sessionkey";
  public static final String SCORE_PARAMETER = "score";
  public static final String USER_ID_PARAMETER = "userid";

  /*
   *  Request for the different services
   */
  public static final String LOGIN_REQUEST = "login";
  public static final String SCORE_REQUEST = "score";
  public static final String HIGH_SCORE_LIST_REQUEST = "highscorelist";
}
