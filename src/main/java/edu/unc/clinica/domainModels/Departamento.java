/*
 * @file Departamento.java;
 * @Autor Henry AV (c)2024
 * @Created 12 mar 2024,1:51:50
 */
package edu.unc.clinica.domainModels;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * The Class Departamento.
 */
@Data
@Entity
public class Departamento {

	/** The Id departamento. */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long IdDepartamento;
	
	/** The nombre departamento. */
	private String nombreDepartamento;
}
