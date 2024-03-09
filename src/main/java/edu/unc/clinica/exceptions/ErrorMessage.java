/*
 * @file ErrorMessage.java;
 * @Autor Henry AV (c)2024
 * @Created 9 mar 2024,14:54:42
 */
package edu.unc.clinica.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

// TODO: Auto-generated Javadoc
/**
 * The Class ErrorMessage.
 */
@Getter
@Setter
public class ErrorMessage {	
	
    /** The status code. */
    // El código de estado HTTP asociado con el error.
    private int statusCode;

    /** The timestamp. */
    // La marca de tiempo en la que se produjo el error.
    private LocalDateTime timestamp;

    /** The message. */
    // El mensaje descriptivo del error.
    private String message;

    /** The description. */
    // La descripción detallada del error.
    private String description;

    /**
     * Constructor que crea una nueva instancia de ErrorMessage.
     *
     * @param statusCode   El código de estado HTTP asociado con el error.
     * @param message      El mensaje descriptivo del error.
     * @param description  La descripción detallada del error.
     */
    public ErrorMessage(HttpStatus statusCode, String message, String description) {
        // Establece el código de estado, la marca de tiempo, el mensaje y la descripción del error.
        this.statusCode = statusCode.value();
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.description = description;
    }
}
