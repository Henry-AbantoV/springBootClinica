package edu.unc.clinica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.unc.clinica.domain.Especialidad;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {

}
