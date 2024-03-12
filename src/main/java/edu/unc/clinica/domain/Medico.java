/*
 * @file Medico.java;
 * @Autor YerssonC.D (c)2024
 * @Created 5 mar 2024,0:27:00
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

/**
 * La clase Medico representa a un profesional médico en el sistema.
 * Esta clase utiliza la anotación Lombok @Data para generar automáticamente los métodos getter, setter, toString, equals y hashCode.
 * Está marcada con la anotación @Entity para indicar que es una entidad de base de datos.
 */
@Data
@Entity
public class Medico extends RepresentationModel<Medico>{
	
	// Identificador único del médico.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMedico;
	
	// Nombre del médico.
	private String nombre;
	
	// Apellidos del médico.
	private String apellidos;
	
	// Número de teléfono del médico.
	private String telefono;
	
	// Horario de atención del médico.
	private String horario;
	
	// Correo electrónico del médico.
	private String correoElectronico;

	// Relación muchos a muchos con la clase Paciente, mapeada por el campo "medicos" en la clase Paciente.
	 @ManyToMany
	private List<Paciente>pacientes= new ArrayList<>(); 
	
	// Relación muchos a uno con la clase Especialidad, mapeada por el campo "medicos" en la clase Especialidad.
	@ManyToOne
    @JoinColumn(name="medicos")
   	private Especialidad especialidad;
	
	//Relacion entre medico jefe
	@ManyToOne
	@JoinColumn(name = "id_jefe")
	
    @JsonIdentityReference(alwaysAsId = true)
	private Medico jefe;
	
	
	////comunicacion con otro microservicio
	
	
	@Transient
	private List<Departamento> departamentos;
	
	public Medico() {
		departamentos=new ArrayList<Departamento>();
	}
	
	
	@OneToMany
    @JoinColumn(name = "curso_id")
	private List<MedicoDepartamento> medicDep;
	
	 public void addmedicoDepa(MedicoDepartamento medicDepa) {
		 medicDep.add(medicDepa);
	    }

	    public void removeMedicoDepa(MedicoDepartamento medicDepa) {
	    	medicDep.remove(medicDepa);
	    }
}
