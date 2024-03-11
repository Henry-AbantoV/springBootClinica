package edu.unc.clinica.domainModels;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Departamento {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long IdDepartamento;
	
	private String nombreDepartamento;
}
