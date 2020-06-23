package com.aer.backend.exception;

/**
 * Thrown when someone try to access wrong url service
 *
 * @author Alberto Egido
 * @version 1.0
 * @date 22/05/20
 */
public class NotValidHttpException extends Exception {

  private static final long serialVersionUID = 4354356123447123492L;

  /**
   * Constructs a {@code NotValidHttpException} with no detail message.
   */
  public NotValidHttpException() {
    super();
  }

  /**
   * Constructs a {@code NotValidHttpException} with the specified
   * detail message.
   *
   * @param msg the detail message.
   */
  public NotValidHttpException(String msg) {
    super(msg);
  }
}
