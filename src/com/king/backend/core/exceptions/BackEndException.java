/*
 *   BackEndException.java
 * 
 * Copyright(c) 2014 Christian Delgado. All Rights Reserved.
 * This software is the proprietary information of Christian Delgado
 * 
 */
package com.king.backend.core.exceptions;

/**
 * Thrown as a generic Exception in the BackEnd Game Server
 *
 * @author Christian Delgado
 * @version 1.0
 * @date 12/27/14
 */
public class BackEndException extends Exception {

    private static final long serialVersionUID = 2345365634347123492L;

    public static final String GENERIC_ERROR_MESSAGE = "Something wrong happened.";

    /**
     * Constructs a {@code BackEndException} with no detail message.
     */
    public BackEndException() {
        super();
    }

    /**
     * Constructs a {@code BackEndException} with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public BackEndException(String msg) {
        super(msg);
    }


    /**
     * Creates a new instance of ParkingException from an existed Exception
     *
     * @param ex
     * @param code
     * @param description
     */
    public BackEndException(Exception ex) {
        super(ex);
    }
}
