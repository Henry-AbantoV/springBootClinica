package edu.unc.clinica.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import edu.unc.clinica.domain.Cita;
import edu.unc.clinica.domain.HistorialMedico;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

/**
 * Clase que representa un DTO (Objeto de Transferencia de Datos) para un
 * paciente. Contiene información sobre el paciente, incluyendo su ID, nombres,
 * apellidos, fecha de nacimiento, género, número de documento de identidad
 * (DNI), dirección, número de teléfono, dirección de correo electrónico, lista
 * de citas asociadas y el historial médico del paciente.
 */
@Data
public class PacienteDTO {
	/**
	 * El ID del paciente.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPaciente;
	/**
	 * Los nombres del paciente.
	 */
	@NotBlank(message = "El nombre del paciente  no puede estar vacío.")
	private String nombres;

	/**
	 * Los apellidos del paciente.
	 */
	@NotBlank(message = "Los apellidos del paciente  no puede estar vacío.")
	private String apellidos;

	/**
	 * La fecha de nacimiento del paciente.
	 */
	@Temporal(TemporalType.DATE)
	@PastOrPresent(message = "La fecha debe ser aanterior al dia de hoy")
	@Past(message = "La fecha de inscripción debe ser en el pasado")
	private Date fechaNacimiento;

	/**
	 * El género del paciente.
	 */
	@NotBlank(message = "El genero del paciente  no puede estar vacío.")
	private String genero;

	/**
	 * El número de documento de identidad (DNI) del paciente.
	 */
	@Column(unique = true)
	@NotBlank(message = "El Documento de Identidad no puede estar vacío.")
	@Size(min = 8, max = 8, message = "El Documento de Identidad debe tener entre 8 caracteres.")
	@Pattern(regexp = "^[0-9]*$", message = "El Documento de Identidad solo puede contener números.")
	private String dni;

	/**
	 * La dirección del paciente.
	 */
	@NotBlank(message = "La direccion del cliente es obigatoria")
	private String direccion;

	/**
	 * El número de teléfono del paciente.
	 */
	@NotBlank(message = "El número de teléfono no puede estar vacío.")
	@Size(min = 9, max = 9, message = "El número de teléfono debe tener 9 caracteres.")
	@Pattern(regexp = "^9[0-9]*$", message = "El telefono debe contener numeros del 1 al 9")
	private String telefono;

	/**
	 * La dirección de correo electrónico del paciente.
	 */
	@Column(unique = true)
	@Email(message = "El formato del correo electrónico no es válido")
	@Size(max = 30, message = "El email debe tener max de 30 caracteres.")
	private String correoElectronico;

	/**
	 * Lista de citas asociadas al paciente.
	 */
	private List<Cita> citas = new ArrayList<>();

	/**
	 * El historial médico del paciente.
	 */
	@JsonIgnore
	private HistorialMedico historialMedico;
}
