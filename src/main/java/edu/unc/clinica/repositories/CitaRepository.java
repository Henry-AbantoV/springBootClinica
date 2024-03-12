/*
 * @file CitaRepository.java;
 * @Autor Daniela Torres (c)2024
 * @Created 12 mar 2024,2:09:45
 */
package edu.unc.clinica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unc.clinica.domain.Cita;

/**
 * The Interface CitaRepository.
 */
public interface CitaRepository extends JpaRepository<Cita, Long> {

}
