/*
 * @file ErrorResponse.java;
 * @Autor Henry AV (c)2024
 * @Created 5 mar 2024,12:24:27
 */
package edu.unc.clinica.exceptions;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * The Class ErrorResponse.
 */
@Data
public class ErrorResponse {

	/** The message. */
	private String message;

    /**
     * Instantiates a new error response.
     *
     * @param message the message
     */
    public ErrorResponse(String message) {
        this.message = message;
    } 
}
