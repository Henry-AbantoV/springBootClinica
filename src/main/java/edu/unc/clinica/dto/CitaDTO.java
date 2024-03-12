/*
 * @file CitaDTO.java;
 * @Autor Daniela Torres (c)2024
 * @Created 5 mar 2024,0:24:53
 */
package edu.unc.clinica.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
<<<<<<< HEAD
=======
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
>>>>>>> a8e2ebf10d7539d153162a4f450fb7cca014f138
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import edu.unc.clinica.domain.Factura;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

//Declaración de la clase CitaDTO
@Data
public class CitaDTO {

	// Campo para almacenar el identificador único de la cita
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCita;

	// Campo para almacenar la fecha y hora de la cita
	@Temporal(TemporalType.DATE)
	@PastOrPresent(message = "La fecha debe ser anterior al dia de hoy")
	@Past(message = "La fecha de inscripción debe ser en el pasado")
	private Date fechaHoraCita;

	// Campo para almacenar el motivo o razón de la cita
	@NotBlank(message = "El motivo no puede estar vacío.")
	private String motivoCita;

	// Campo para almacenar el estado actual de la cita
	@NotBlank(message = "El estado de la cita no puede estar vacío.")
	private String estadoCita;

	// Campo para almacenar la factura asociada a la cita
	private Factura factura;
}
