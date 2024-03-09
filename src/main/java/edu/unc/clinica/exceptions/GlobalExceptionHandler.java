/*
 * @file GlobalExceptionHandler.java;
 * @Autor Henry AV (c)2024
 * @Created 5 mar 2024,12:24:14
 */
package edu.unc.clinica.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import edu.unc.clinica.util.ApiResponse;


// TODO: Auto-generated Javadoc
/**
 * The Class GlobalExceptionHandler.
 */
@ControllerAdvice
public class GlobalExceptionHandler  {

	/**
     * Maneja la excepción EntityNotFoundException.
     *
     * @param ex       La excepción EntityNotFoundException lanzada.
     * @param request  La solicitud web en la que se produjo la excepción.
     * @return         Una respuesta de entidad que contiene un mensaje de error y el estado HTTP correspondiente.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> resourceNotFoundException(EntityNotFoundException ex, WebRequest request) {
        ApiResponse<Object> message = new ApiResponse<>(false,ex.getMessage(), null);

        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
   
    /**
     * Maneja la excepción IllegalOperationException.
     *
     * @param ex       La excepción IllegalOperationException lanzada.
     * @param request  La solicitud web en la que se produjo la excepción.
     * @return         Una respuesta de entidad que contiene un mensaje de error y el estado HTTP correspondiente.
     */
    @ExceptionHandler(IllegalOperationException.class)
    public ResponseEntity<ApiResponse<Object>> illegalOperationException(IllegalOperationException ex, WebRequest request) {
    	ApiResponse<Object> message = new ApiResponse<>(false,ex.getMessage(), null);
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
    
    /**
     * Maneja la excepción generales.
     *
     * @param ex la excepción general.
     * @param request la solicitud web asociada.
     * @return una respuesta HTTP personalizada con detalles de error.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request){
        ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
   

}
