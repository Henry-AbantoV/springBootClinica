/*
 * @file MedicoDTO.java;
 * @Autor YerssonC.D (c)2024
 * @Created 5 mar 2024,0:29:16
 */
package edu.unc.clinica.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import edu.unc.clinica.domain.Especialidad;
import edu.unc.clinica.domain.Medico;
import edu.unc.clinica.domain.Paciente;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Clase que representa un DTO (Objeto de Transferencia de Datos) para un
 * médico. Contiene información sobre el médico, incluyendo su ID, nombre,
 * apellidos, teléfono, horario de trabajo, correo electrónico y la especialidad
 * a la que pertenece.
 */
@Data
public class MedicoDTO {

	/**
	 * El ID del médico.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMedico;

	/**
	 * El nombre del médico.
	 */
	@NotBlank(message = "El nombre del Dr. no deben estar en blanco")
	private String nombre;

	/**
	 * Los apellidos del médico.
	 */
	@NotBlank(message = "Los apellidos del Dr. no deben estar en blanco")
	private String apellidos;

	/**
	 * El número de teléfono del médico.
	 */
	@NotBlank(message = "El número de teléfono no puede estar vacío.")
	@Size(min = 9, max = 9, message = "El número de teléfono debe tener 9 caracteres.")
	@Pattern(regexp = "^9[0-9]*$", message = "El número celular debe ser uno valido de Perú")
	private String telefono;

	/**
	 * El horario de trabajo del médico.
	 */
	@NotBlank(message = "El horario de atencion del Dr. no puede estar vacío.")
	private String horario;

	/**
	 * La dirección de correo electrónico del médico.
	 */
	@Column(unique = true)
	@Email(message = "El formato del correo electrónico no es válido")
	@Size(max = 30, message = "El email debe tener max de 30 caracteres.")
	private String correoElectronico;

	/**
	 * La especialidad a la que pertenece el médico.
	 */
	private Especialidad especialidad;

	/**
	 * Relacion recursiva entre un medico y su jefe
	 */
	@JsonIdentityReference(alwaysAsId = true)
	private Medico jefe;

	private List<Paciente> pacientes = new ArrayList<Paciente>();
}
