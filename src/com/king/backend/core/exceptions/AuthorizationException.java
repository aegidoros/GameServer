/*
 *   AuthorizationException.java
 * 
 * Copyright(c) 2014 Christian Delgado. All Rights Reserved.
 * This software is the proprietary information of Christian Delgado
 * 
 */
package com.king.backend.core.exceptions;

/**
 * Thrown when someone try to access a service without a valid SessionKey
 *
 * @author Christian Delgado
 * @version 1.0
 * @date 12/27/14
 */
public class AuthorizationException extends Exception {

    private static final long serialVersionUID = 5163433612347123492L;


    public static final String AUTHORIZATION_ERROR = "Authorization Error";

    /**
     * Constructs a {@code AuthenticationException} with no detail message.
     */
    public AuthorizationException() {
        super(AUTHORIZATION_ERROR);
    }

    /**
     * Constructs a {@code AuthenticationException} with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public AuthorizationException(String msg) {
        super(msg);
    }
}
