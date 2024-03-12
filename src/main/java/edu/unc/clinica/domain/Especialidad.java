/*
 * @file Especialidad.java;
 * @Autor Fernando C.J. (c)2024
 * @Created 12 mar. 2024,01:53:40
 */

package edu.unc.clinica.domain;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
// TODO: Auto-generated Javadoc

/**
 * The Class Especialidad.
 */
//Anotación que indica que esta clase es una entidad JPA
@Entity
//Anotación de Lombok que agrega automáticamente los métodos getter, setter, equals, hashCode y toString
@Data
public class Especialidad extends RepresentationModel<Especialidad>{


	/** The id especialidad. */
	// Identificador único generado automáticamente para la especialidad
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEspecialidad;
	
	/** The nombre esp. */
	// Anotación que especifica que el nombre de la especialidad no puede estar en blanco
	private String nombreEsp;
	
	// Anotación que ignora la serialización de la lista de médicos al formato JSON
	
	/** The medicos. */
	// Relación uno a muchos con la clase Medico, mapeada por el campo "especialidad" en la clase Medico
	@OneToMany(mappedBy="especialidad")
	@JsonIgnore
	private List<Medico> medicos=new ArrayList<>();
	
}
