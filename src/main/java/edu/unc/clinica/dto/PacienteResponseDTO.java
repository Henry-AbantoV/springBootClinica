/*
 * @file PacienteResponseDTO.java;
 * @Autor Daniela Torres (c)2024
 * @Created 12 mar 2024,2:09:31
 */
package edu.unc.clinica.dto;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * The Class PacienteResponseDTO.
 */
@Data
public class PacienteResponseDTO {
	
	/** The nombres paciente. */
	// Add the fields you want to include in the response
	private String nombres_paciente;
	
	/** The apellidos. */
	private String apellidos;
	
	
	/**
	 * Instantiates a new paciente response DTO.
	 *
	 * @param nombres_paciente the nombres paciente
	 * @param apellidos the apellidos
	 */
	public PacienteResponseDTO(String nombres_paciente, String apellidos) {
		super();
		this.nombres_paciente = nombres_paciente;
		this.apellidos = apellidos;
	}
	
	
}
