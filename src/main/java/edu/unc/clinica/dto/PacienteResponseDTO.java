package edu.unc.clinica.dto;

import lombok.Data;

@Data
public class PacienteResponseDTO {
	// Add the fields you want to include in the response
	private String nombres_paciente;
	private String apellidos;
	
	
	public PacienteResponseDTO(String nombres_paciente, String apellidos) {
		super();
		this.nombres_paciente = nombres_paciente;
		this.apellidos = apellidos;
	}
	
	
}
