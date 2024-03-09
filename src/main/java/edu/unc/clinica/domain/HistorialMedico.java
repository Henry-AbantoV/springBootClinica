/*
 * @file HistorialMedico.java;
 * @Autor Henry AV (c)2024
 * @Created 5 mar 2024,0:36:03
 */

package edu.unc.clinica.domain;

import java.util.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

/**
 * La clase Historial Medico representa un historial medico en el sistema. Esta clase está
 * marcada con la anotación @Entity para indicar que es una entidad de base de
 * datos. También utiliza las anotaciones Lombok @Data para generar
 * automáticamente los métodos getter, setter, toString, equals y hashCode.

// TODO: Auto-generated Javadoc
/**
 * The Class HistorialMedico.
 */
@Entity
@Data
public class HistorialMedico {
	
	/** The id historial medico. */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idHistorialMedico;
	
	/**
     * Fecha en la que se registra el historial médico.
     */
    private Date fechaRegistro;
	
	/**
    * Diagnósticos previos del paciente.
    */
	private String diagnosticosAnteriores;
	
	/**
     * Tratamientos médicos previos del paciente.
     */
    private String tratamientosPrevios;
	 
	/** The procedimientos realizados. */
    private String procedimientosRealizados;
	 
	/** The medicamentos recetados. */
    private String medicamentosRecetados;
	 
	/** The Resultados pruebas medicas. */
    private String ResultadosPruebasMedicas;
    
    
    /** The paciente. */
    @OneToOne
    @JoinColumn(name="id_Paciente")
    private Paciente paciente;
  
}
