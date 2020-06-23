package com.aer.backend.exception;

/**
 * Thrown as a generic Exception in the BackEnd Game Server
 *
 * @author Alberto Egido
 * @version 1.0
 * @date 22/05/20
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


}
