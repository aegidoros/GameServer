/*
 *   NotValidHttpException.java
 * 
 * Copyright(c) 2014 Christian Delgado. All Rights Reserved.
 * This software is the proprietary information of Christian Delgado
 * 
 */
package com.king.backend.core.exceptions;

/**
 * Thrown when someone try to access wrong url service
 *
 * @author Christian Delgado
 * @version 1.0
 * @date 12/27/14
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
