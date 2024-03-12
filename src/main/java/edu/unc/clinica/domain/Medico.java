/*
 * @file Medico.java
 * @Autor Yersson.C.D(c)2024
 * @Created 12 mar 2024, 1:52:01
 *  
 */
package edu.unc.clinica.domain;
import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import edu.unc.clinica.domainModels.Departamento;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * La clase Medico representa a un profesional médico en el sistema.
 * Esta clase utiliza la anotación Lombok @Data para generar automáticamente los métodos getter, setter, toString, equals y hashCode.
 * Está marcada con la anotación @Entity para indicar que es una entidad de base de datos.
 */
@Data
@Entity
public class Medico extends RepresentationModel<Medico>{
	
	/** The id medico. */
	// Identificador único del médico.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMedico;
	
	/** The nombre. */
	// Nombre del médico.
	private String nombre;
	
	/** The apellidos. */
	// Apellidos del médico.
	private String apellidos;
	
	/** The telefono. */
	// Número de teléfono del médico.
	private String telefono;
	
	/** The horario. */
	// Horario de atención del médico.
	private String horario;
	
	/** The correo electronico. */
	// Correo electrónico del médico.
	private String correoElectronico;

	/** The pacientes. */
	// Relación muchos a muchos con la clase Paciente, mapeada por el campo "medicos" en la clase Paciente.
	 @ManyToMany
	private List<Paciente>pacientes= new ArrayList<>(); 
	
	/** The especialidad. */
	// Relación muchos a uno con la clase Especialidad, mapeada por el campo "medicos" en la clase Especialidad.
	@ManyToOne
    @JoinColumn(name="medicos")
   	private Especialidad especialidad;
	
	/** The jefe. */
	//Relacion entre medico jefe
	@ManyToOne
	@JoinColumn(name = "id_jefe")
	
    @JsonIdentityReference(alwaysAsId = true)
	private Medico jefe;
	
	
	////comunicacion con otro microservicio
	
	
	/** The departamentos. */
	@Transient
	private List<Departamento> departamentos;
	
	/**
	 * Instantiates a new medico.
	 */
	public Medico() {
		departamentos=new ArrayList<Departamento>();
	}
	
	
	/** The medic dep. */
	@OneToMany
    @JoinColumn(name = "curso_id")
	private List<MedicoDepartamento> medicDep;
	
	 /**
 	 * Addmedico depa.
 	 *
 	 * @param medicDepa the medic depa
 	 */
 	public void addmedicoDepa(MedicoDepartamento medicDepa) {
		 medicDep.add(medicDepa);
	    }

	    /**
    	 * Removes the medico depa.
    	 *
    	 * @param medicDepa the medic depa
    	 */
    	public void removeMedicoDepa(MedicoDepartamento medicDepa) {
	    	medicDep.remove(medicDepa);
	    }
}
