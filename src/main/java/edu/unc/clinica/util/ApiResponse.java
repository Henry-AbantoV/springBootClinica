/*
 * @file ApiResponse.java;
 * @Autor Henry AV (c)2024
 * @Created 9 mar 2024,15:25:10
 */
package edu.unc.clinica.util;


import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * The Class ApiResponse.
 *
 * @param <T> the generic type
 */
@Data
public class ApiResponse<T> {


    /** The success. */
    // Indica si la operación fue exitosa.
    private boolean success;

    /** The message. */
    // Mensaje asociado con la operación.
    private String message;

    /** The data. */
    // Datos relacionados con la operación.
    private T data;

    /**
     * Constructor de la clase ApiResponse.
     * @param success Indica si la operación fue exitosa.
     * @param message Mensaje asociado con la operación.
     * @param data Datos relacionados con la operación.
     */
    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}
