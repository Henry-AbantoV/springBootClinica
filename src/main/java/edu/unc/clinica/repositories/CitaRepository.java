package edu.unc.clinica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unc.clinica.domain.Cita;

public interface CitaRepository extends JpaRepository<Cita, Long> {

}
