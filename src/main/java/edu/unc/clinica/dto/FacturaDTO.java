package edu.unc.clinica.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Bag;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import edu.unc.clinica.domain.Cita;
import edu.unc.clinica.domain.Paciente;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

/**
 * Clase que representa un DTO (Objeto de Transferencia de Datos) para una
 * factura. Contiene información sobre la factura, incluyendo su ID, fecha de
 * emisión, descripción de servicios, pagos realizados, saldo pendiente, costo y
 * listas de citas y pacientes asociados.
 */
@Data
public class FacturaDTO {

	/**
	 * El ID de la factura.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFactura;
	/**
	 * La fecha de emisión de la factura.
	 */
	@Temporal(TemporalType.DATE)
	@PastOrPresent(message = "La fecha debe ser anterior al dia de hoy")
	@Past(message = "La fecha de inscripción debe ser en el pasado")
	private Date fechaEmision;

	/**
	 * La descripción de los servicios proporcionados en la factura.
	 */
	@NotBlank(message = "Descripcion de la factura no puede estar vacío.")
	private String descripServicios;

	/**
	 * Los pagos realizados hasta la fecha de la factura.
	 */
	@NotBlank(message = "El pago realizado no puede estar vacío.")
	private String pagoRealizados;

	/**
	 * El saldo pendiente de la factura.
	 */
	@NotBlank(message = "El saldo pendiente no puede estar vacío.")
	@Size(min = 1, max = 6, message = "El saldo pendiente debe tener hasta 6 caracteres.")
	@Pattern(regexp = "^[0-9]*$", message = "El saldo pendiente debe contener numeros del 1 al 9")
	private double saldoPendiente;

	/**
	 * El costo total de los servicios facturados.
	 */
	@NotBlank(message = "El costo no puede estar vacío.")
	@Size(min = 1, max = 6, message = "El costo debe tener hasta 6 caracteres.")
	@Pattern(regexp = "^[0-9]*$", message = "El costo debe contener numeros del 1 al 9")
	private double costo;

	/**
	 * Lista de citas asociadas a la factura.
	 */
	private List<Cita> citas = new ArrayList<>();
	/**
	 * Lista de pacientes asociados a la factura.
	 */
	private List<Paciente> pacientes = new ArrayList<>();
}
