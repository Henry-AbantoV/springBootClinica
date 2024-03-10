
/*
* @Autor Fernando C.J. (c)2024
 * @Created 5 mar. 2024,00:39:05
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
//Anotación que indica que esta clase es una entidad JPA
@Entity
//Anotación de Lombok que agrega automáticamente los métodos getter, setter, equals, hashCode y toString
@Data
public class Especialidad extends RepresentationModel<Especialidad>{


	// Identificador único generado automáticamente para la especialidad
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEspecialidad;
	
	// Anotación que especifica que el nombre de la especialidad no puede estar en blanco
	private String nombreEsp;
	
	// Anotación que ignora la serialización de la lista de médicos al formato JSON
	
	// Relación uno a muchos con la clase Medico, mapeada por el campo "especialidad" en la clase Medico
	@OneToMany(mappedBy="especialidad")
	@JsonIgnore
	private List<Medico> medicos=new ArrayList<>();
	
}
