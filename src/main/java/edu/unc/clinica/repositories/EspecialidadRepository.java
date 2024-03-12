/*
 * @file EspecialidadRepository.java;
 * @Autor Fernando C.J. (c)2024
 * @Created 12 mar. 2024,02:00:57
 */
package edu.unc.clinica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unc.clinica.domain.Especialidad;

/**
 * The Interface EspecialidadRepository.
 */
public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {

}
