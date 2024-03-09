
package edu.unc.clinica.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import edu.unc.clinica.domain.Paciente;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

/**
 * Clase que representa un DTO (Objeto de Transferencia de Datos) para un historial médico.
 * Contiene información sobre el historial médico, incluyendo su ID, fecha de registro, diagnósticos anteriores,
 * tratamientos previos, procedimientos realizados, medicamentos recetados, resultados de pruebas médicas
 * y el paciente asociado.
 */
@Data
public class HistorialDTO {
	 /**
     * El ID del historial médico.
     */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idHistorialMedico;
	
	/**
     * La fecha de registro del historial médico.
     */
	@Temporal(TemporalType.DATE)
	@PastOrPresent(message = "La fecha debe ser anterior o  al dia de hoy")
    private Date fechaRegistro;
    
    /**
     * Los diagnósticos anteriores registrados en el historial médico.
     */
	@NotBlank(message = "Los diagnosticos anteriores no deben estar en blanco")
	private String diagnosticosAnteriores;
	
	/**
     * Los tratamientos previos registrados en el historial médico.
     */
	@NotBlank(message = "Los tratamientos previos no deben estar en blanco")
    private String tratamientosPrevios;
    
    /**
     * Los procedimientos realizados registrados en el historial médico.
     */
	@NotBlank(message = "Los procedimientos realizados no deben estar en blanco")
    private String procedimientosRealizados;
    
    /**
     * Los medicamentos recetados registrados en el historial médico.
     */
	@NotBlank(message = "Los medicamentos a recetar no deben estar en blanco")
    private String medicamentosRecetados;
    
    /**
     * Los resultados de las pruebas médicas registrados en el historial médico.
     */
	@NotBlank(message = "Los resultados de las pruebas medicas no deben estar en blanco")
    private String ResultadosPruebasMedicas;
    
    /**
     * El paciente asociado al historial médico.
     */
    private Paciente paciente;
    
    
}
