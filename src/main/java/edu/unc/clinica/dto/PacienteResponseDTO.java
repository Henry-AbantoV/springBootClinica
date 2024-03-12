package edu.unc.clinica.dto;

import lombok.Data;

@Data
public class PacienteResponseDTO {
	// Add the fields you want to include in the response
	private String nombres;
	private String apellidos;
	
	
	public PacienteResponseDTO(String nombres, String apellidos) {
		super();
		this.nombres = nombres;
		this.apellidos = apellidos;
	}
	
	
}
