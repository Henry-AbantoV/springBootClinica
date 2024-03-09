/*
 * @file IllegalOperationException.java;
 * @Autor Henry AV (c)2024
 * @Created 5 mar 2024,12:23:59
 */
package edu.unc.clinica.exceptions;
// TODO: Auto-generated Javadoc

/**
 * Excepción que se lanza cuando se realiza una operación ilegal.
 */
public class IllegalOperationException extends Exception{
	/**
     * Serial único de la clase, se utiliza durante la deserialización para verificar la compatibilidad de la clase.
     * Este valor ayuda a garantizar que la clase utilizada para serializar los objetos es compatible con la clase utilizada para deserializarlos.
     * Si se realiza algún cambio en la clase (como agregar métodos o propiedades), este valor debe generarse nuevamente.
     * La anotación @Serial se utiliza para realizar comprobaciones en tiempo de compilación de este campo.
     */
	
	private static final long serialVersionUID = 1L;
	 /**
     * Constructor para la clase IllegalOperationException.
     *
     * @param message Mensaje detallado del error, proporciona información adicional sobre la excepción.
     */
	public IllegalOperationException(String message) {
		super (message);
	}
}
