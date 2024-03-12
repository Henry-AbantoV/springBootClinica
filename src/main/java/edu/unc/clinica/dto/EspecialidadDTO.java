/*
 * @file EspecialidadDTO.java;
 * @Autor Fernando C.J. (c)2024
 * @Created 5 mar. 2024,01:38:32
 */
package edu.unc.clinica.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.unc.clinica.domain.Medico;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Clase que representa un DTO (Objeto de Transferencia de Datos) para una
 * especialidad médica. Contiene información sobre la especialidad médica,
 * incluyendo su ID, nombre y lista de médicos asociados.
 */
@Data
public class EspecialidadDTO {

	/**
	 * El ID de la especialidad médica.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEspecialidad;

	/**
	 * El nombre de la especialidad médica.
	 */
	@NotBlank(message = "El nombre de la especialidad no puede estar vacío.")
	private String nombreEsp;

	/**
	 * Lista de médicos asociados a la especialidad médica.
	 */
	@JsonIgnore
	private List<Medico> medicos = new ArrayList<>();

}
